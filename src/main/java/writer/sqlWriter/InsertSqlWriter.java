package writer.sqlWriter;

import java.util.List;

import loadEntity.DetailEntity;
import loadEntity.MainMsgEntity;
import utils.UUIDCreater;
/**
 * 用于生成插入数据库的sql文件
 */
public class InsertSqlWriter {

	public String getInsertSql(MainMsgEntity mainMsg, 
			List<DetailEntity> sendHead, List<DetailEntity> sendBody,
			List<DetailEntity> receiveHead, List<DetailEntity> receiveBody) {

		//--4.schema文件配置表 配置2个,发送和接收 
		String sql4="--4.schema文件配置表 配置2个,发送和接收\r\n";
		String uuidSend = UUIDCreater.getUUID32();//发出信息的uuid
		String uuidReceive = UUIDCreater.getUUID32();//接收信息的uuid
		String sql4Head="insert into RTTP_SCHEMA_NS values (";
		String sql4SendSql=sql4Head+"'"+uuidSend+"',"+
				"'classpath:config/xsd/knl/"+mainMsg.getSendXsdName()+".xsd',"+
				"'"+mainMsg.getSendXsdName()+"',"+
				"'1');\r\n";
		String sql4ReceiveSql=sql4Head+"'"+uuidReceive+"',"+
				"'classpath:config/xsd/knl/"+mainMsg.getReceiveXsdName()+".xsd',"+
				"'"+mainMsg.getReceiveXsdName()+"',"+
				"'1');\r\n";
		sql4+=sql4SendSql+sql4ReceiveSql;
		
		//--5.对应schema文件配置表的关联字段 
		String sql5="\r\n--5.对应schema文件配置表的关联字段\r\n";
		sql5+=getBindMapSql(sendHead,uuidSend);
		sql5+=getBindMapSql(sendBody,uuidSend);
		sql5+=getBindMapSql(receiveHead,uuidReceive);
		sql5+=getBindMapSql(receiveBody,uuidReceive);
		
		//--6.命名规则配置表 需要配置4个
		String sql6="--6.命名规则配置表 需要配置4个\r\n";
		String sql6Head="insert into RTTP_SCHEMA values ";
		String sql6_1=sql6Head+"('"+mainMsg.getSrcTransCode()+"','"+mainMsg.getSendXsdName()+"','"+
					UUIDCreater.getUUID32()+"',null,null,'req');\r\n";
		String sql6_2=sql6Head+"('"+mainMsg.getSrcTransCode()+"','"+mainMsg.getReceiveXsdName()+"','"+
				UUIDCreater.getUUID32()+"',null,null,'ans');\r\n";
		String sql6_3=sql6Head+"('"+mainMsg.getDestTransCode()+"','"+mainMsg.getSendXsdName()+"','"+
				UUIDCreater.getUUID32()+"',null,null,'req');\r\n";
		String sql6_4=sql6Head+"('"+mainMsg.getDestTransCode()+"','"+mainMsg.getReceiveXsdName()+"','"+
				UUIDCreater.getUUID32()+"',null,null,'ans');\r\n";
		sql6=sql6+sql6_1+sql6_2+sql6_3+sql6_4;
		
		//--7.交易映射配置表
		String sql7="--7.交易映射配置表\r\n";
		String sql7Head="insert into RTTP_TRANS_CODE_MAPPING values ";
		String sql7_1=sql7Head+"('"+mainMsg.getSrcTransCode()+"','"+mainMsg.getDestTransCode()+"','"+mainMsg.getWorkName()+"','"+UUIDCreater.getUUID32()+"','90');\r\n";
		String sql7_2=sql7Head+"('"+mainMsg.getDestTransCode()+"','"+mainMsg.getSrcTransCode()+"','"+mainMsg.getWorkName()+"','"+UUIDCreater.getUUID32()+"','90');\r\n";
		sql7=sql7+sql7_1+sql7_2;
		
		//--8.流程定位配置表
		String sql8="--8.流程定位配置表\r\n";
		String sql8Head="insert into RTTP_LOCATOR values ";
		String sql8_1=sql8Head+"('"+mainMsg.getSrcTransCode()+"','"+mainMsg.getWorkFlowName()+"','"+mainMsg.getWorkName()+"');\r\n";
		String sql8_2=sql8Head+"('"+mainMsg.getDestTransCode()+"','"+mainMsg.getWorkFlowName()+"','"+mainMsg.getWorkName()+"');\r\n";
		sql8=sql8+sql8_1+sql8_2;
		
		//--9.新加的一张表:用来确定发出字段的属性(发出head是共用的,这里不再生成,只生成发出body部分)
		String sql9="--9.新加的一张表:用来确定发出字段的属性\r\n";
		String sql9Head="insert into RTTP_OUTER_ATTRIBUTE values ";
		for(int i=0;i<sendBody.size();i++) {
			DetailEntity e=sendBody.get(i);
			//如果发出body部分不是父节点
			if("n".equals(e.getIsParentNode().toLowerCase())) {
				sql9=sql9+sql9Head+"('"+UUIDCreater.getUUID32()+"','"+mainMsg.getSrcTransCode()+"','"+e.getKey()+"','"+e.getAttrScale()+"','"+e.getAttrLength()+"','"+e.getAttrType()+"');\r\n";		
			}
		}
		
		//拼接所有sql
		String insertSql=sql4+sql5+sql6+sql7+sql8+sql9;
		return insertSql;
	}
	
	/**
	 * 生成RTTP_BIND_MAP语句
	 */
	private String getBindMapSql(List<DetailEntity> list, String schemaNsId) {
		StringBuffer sqls=new StringBuffer();
		StringBuffer sql5Head=new StringBuffer("insert into RTTP_BIND_MAP values (");
		for(int i=0;i<list.size();i++) {
			DetailEntity e=list.get(i);
			StringBuffer str=new StringBuffer();
			str.append(sql5Head);
			str.append("'"+e.getBindMapId()+"',");
			str.append("'"+e.getPath()+"',");
			str.append("'"+e.getKey()+"',");
			str.append("'"+e.getType()+"',");
			if("y".equals(e.getNeedParentId().toLowerCase()) && "".equals(e.getParentName2())) {
				for(DetailEntity entity:list) {
					//如果e.getParentName()等于父节点名称,取得父节点的id
					if(e.getParentName().equals(entity.getSelfName())) {
						str.append("'"+entity.getBindMapId()+"',");	
						break;
					}
				}
			}else if("y".equals(e.getNeedParentId().toLowerCase()) && (!"".equals(e.getParentName2()))) {
				for(DetailEntity entity:list) {
					//如果e.getParentName()等于父节点名称,取得父节点的id
					if(e.getParentName().equals(entity.getSelfName()) 
							&& entity.getPath().contains(e.getParentName2())) {
						str.append("'"+entity.getBindMapId()+"',");	
						break;
					}
				}
			}else {
				str.append("null,");
			}
			str.append("'"+schemaNsId+"');\r\n");
			sqls.append(str);//将每一条sql加入到sqls
		}
		return ""+sqls;
	}
	
	
}
