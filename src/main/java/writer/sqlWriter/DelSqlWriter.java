package writer.sqlWriter;

import java.util.List;

import loadEntity.DetailEntity;
import loadEntity.MainMsgEntity;
/**
 * 生成删除sql语句
 */
public class DelSqlWriter {
	
	public String getDelSql(MainMsgEntity mainMsg, 
			List<DetailEntity> sendHead, List<DetailEntity> sendBody,
			List<DetailEntity> receiveHead, List<DetailEntity> receiveBody,
			String sendUUID, String recvUUID) {
			
		String innerCode = mainMsg.getSrcTransCode();
		String outerCode = mainMsg.getDestTransCode();
		String nameSpaceSend = mainMsg.getSendXsdName();
		String nameSpaceRecv = mainMsg.getReceiveXsdName();
		String delSql = "";
		String deleteCMappingSql = "DELETE FROM RTTP_TRANS_CODE_MAPPING WHERE SRC_TRANS_CODE IN ('"+innerCode+"','"+outerCode+"');\r\n";
		String deleteSchemaSql = "DELETE FROM RTTP_SCHEMA WHERE TRANS_CODE IN ('"+innerCode+"','"+outerCode+"');\r\n";
		String deleteNsSql = "DELETE FROM RTTP_SCHEMA_NS WHERE NAMESPACES IN ('"+nameSpaceSend+"','"+nameSpaceRecv+"');\r\n";
		String deleteBMapeSql = "DELETE FROM RTTP_BIND_MAP WHERE NS_PID IN ('"+sendUUID+"','"+recvUUID+"');\r\n";
		String deleteLocateSql = "DELETE FROM RTTP_LOCATOR WHERE TRANS_CODE IN ('"+innerCode+"','"+outerCode+"');\r\n";
		String deleteOuterSql = "DELETE FROM RTTP_OUTER_ATTRIBUTE WHERE ATTR_GROUP ='"+innerCode+"';\r\n";
		delSql = deleteCMappingSql+deleteSchemaSql+deleteNsSql+deleteBMapeSql+deleteLocateSql+deleteOuterSql;
		delSql = delSql+"commit;";
		return delSql;
	}
	
}
