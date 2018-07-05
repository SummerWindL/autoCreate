package writer.xsdWriter;

import java.util.List;

import loadEntity.DetailEntity;
import loadEntity.MainMsgEntity;

/**
 * 组装head部分(包括sys-header,app-header,local-header等)
 */
public class XsdHead {
	public  String getXsdHead(MainMsgEntity mainMsg,List<DetailEntity> sendHead) {
		if(sendHead==null || sendHead.size()<1) {
			System.out.println("head内容为空!");
			return null;
		}
		
		StringBuilder xsdHead=new StringBuilder();
		String tempParentName="";//前一次循环中parentName的值
		for(int i=0;i<sendHead.size();i++) {
			DetailEntity e=sendHead.get(i);
			if("service".equals(e.getSelfName())) {
				xsdHead.append("<xs:element name=\"service\" type=\"service\" />\r\n"); 
				xsdHead.append("	<!-- add by "+mainMsg.getMaker()+ " " +mainMsg.getWorkName()+" 查询  -->\r\n");
				
			}else {
				//TODO 如果e.getParentName()为空字符串  应抛异常
				if("".equals(e.getParentName())) {
					System.out.println("head填写错误: "+e.getSelfName()+"的父节点为空!");
				}
				
				if(tempParentName.equals(e.getParentName()) ) {
					//如果本节点的父节点和上一个节点的父节点相同
					if("simple".equals(e.getType().toLowerCase())) {
						if(e.getSelfName().equals("SERVICE_CODE")) {
							xsdHead.append("			<xs:element name=\""+e.getSelfName()+"\" type=\"TRANS_CODE\"></xs:element>\r\n");
						}else {
							xsdHead.append("			<xs:element name=\""+e.getSelfName()+"\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");
						}
												
					}else if("complex".equals(e.getType().toLowerCase())){
						xsdHead.append("			<xs:element name=\""+e.getSelfName()+"\" maxOccurs=\"1000\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");
					}else {
						//TODO 最好抛异常
						System.out.println("excel错误: simple和complex类型填写错误;填写错误的字符串为:"+e.getType());
					}
				} else {
					//给上一个节点添加结束标签
					if(!"".equals(tempParentName)) {
						xsdHead.append("		</xs:sequence>\r\n");
						xsdHead.append("	</xs:complexType>\r\n");
					}
					//本节点的开始标签和内容
					xsdHead.append("	<xs:complexType name=\""+e.getParentName()+"\">\r\n" ); 
					xsdHead.append("		<xs:sequence>\r\n");					
					if("simple".equals(e.getType().toLowerCase())) {
						if(e.getSelfName().equals("SERVICE_CODE")) {
							xsdHead.append("			<xs:element name=\""+e.getSelfName()+"\" type=\"TRANS_CODE\"></xs:element>\r\n");
						}else {
							xsdHead.append("			<xs:element name=\""+e.getSelfName()+"\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");						
						}
					}else if("complex".equals(e.getType().toLowerCase())){
						xsdHead.append("			<xs:element name=\""+e.getSelfName()+"\" maxOccurs=\"1000\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");
					}else {
						//TODO 最好抛异常
						System.out.println("excel错误: simple和complex类型填写错误;填写错误的字符串为:"+e.getType());
					}
					tempParentName=e.getParentName();
				}
				
			}
			
		}
		
		xsdHead.append("		</xs:sequence>\r\n");
		xsdHead.append("	</xs:complexType>\r\n");
		
		//生成xsdHead的SimpleType
		StringBuilder xsdHeadSimpleType=new StringBuilder();
		for(int i=0;i<sendHead.size();i++) {
			DetailEntity e=sendHead.get(i);
			//TODO 如果excel字段非法,应抛异常
			if("N".equals(e.getIsParentNode().toUpperCase())) {
				if(e.getSelfName().equals("SERVICE_CODE")) {
					xsdHeadSimpleType.append("	<xs:simpleType name=\"TRANS_CODE\">\r\n");
					xsdHeadSimpleType.append("		<xs:restriction base=\"xs:string\">\r\n");
					xsdHeadSimpleType.append("			<xs:length value=\"256\"></xs:length>\r\n");
					xsdHeadSimpleType.append("		</xs:restriction>\r\n");
					xsdHeadSimpleType.append("	</xs:simpleType>\r\n");
				}else{
					xsdHeadSimpleType.append("	<xs:simpleType name=\""+e.getSelfName()+"\">\r\n");
					xsdHeadSimpleType.append("		<xs:restriction base=\"xs:string\">\r\n");
					xsdHeadSimpleType.append("			<xs:length value=\"256\"></xs:length>\r\n");
					xsdHeadSimpleType.append("		</xs:restriction>\r\n");
					xsdHeadSimpleType.append("	</xs:simpleType>\r\n");
				}
			}
		}
		
		
		return ""+xsdHead+xsdHeadSimpleType;
	}
	
	
	
	
}
