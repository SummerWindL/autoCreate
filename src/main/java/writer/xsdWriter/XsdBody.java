package writer.xsdWriter;

import java.util.List;

import loadEntity.DetailEntity;
/**
 * 生成xsd中<body/>标签内的内容
 */
public class XsdBody {
	public  String getXsdBody(List<DetailEntity> sendBody) {
		if(sendBody==null || sendBody.size()<1) {
			//TODO 最好抛异常
			System.out.println("Body内容为空!");
			return null;
		}
		
		StringBuilder xsdBody=new StringBuilder();
		String tempParentName="";//前一次循环中parentName的值
		
//		System.out.println("body的总行数: "+sendBody.size());
		
		for(int i=0;i<sendBody.size();i++) {
			DetailEntity e=sendBody.get(i);
			
			//TODO 如果e.getParentName()为空字符串  应抛异常
			System.out.println("body中本字段名称: 第"+(i+1)+"行: "+e.toString());
			if("".equals(e.getParentName())) {
				System.out.println("body填写错误: "+e.getSelfName()+"的父节点为空!");
			}
			
			if(tempParentName.equals(e.getParentName()) ) {
				//如果本节点的父节点和上一个节点的父节点相同
				if("simple".equals(e.getType().toLowerCase())) {
					xsdBody.append("			<xs:element name=\""+e.getSelfName()+"\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");						
				}else if("complex".equals(e.getType().toLowerCase())){
					xsdBody.append("			<xs:element name=\""+e.getSelfName()+"\" maxOccurs=\"1000\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");
				}else {
					//TODO 最好抛异常
					System.out.println("excel错误: simple和complex类型填写错误;填写错误的字符串为:"+e.getType());
				}
			} else {
				//给上一个节点添加结束标签
				if(!"".equals(tempParentName)) {
					xsdBody.append("		</xs:sequence>\r\n");
					xsdBody.append("	</xs:complexType>\r\n");
				}
				//本节点的开始标签和内容
				xsdBody.append("	<xs:complexType name=\""+e.getParentName()+"\">\r\n" ); 
				xsdBody.append("		<xs:sequence>\r\n");					
				if("simple".equals(e.getType().toLowerCase())) {
					xsdBody.append("			<xs:element name=\""+e.getSelfName()+"\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");						
				}else if("complex".equals(e.getType().toLowerCase())){
					xsdBody.append("			<xs:element name=\""+e.getSelfName()+"\" maxOccurs=\"1000\" type=\""+e.getSelfName()+"\"></xs:element>\r\n");
				}else {
					//TODO 最好抛异常
					System.out.println("excel错误: simple和complex类型填写错误;填写错误的字符串为:"+e.getType());
				}
				tempParentName=e.getParentName();
			}
			
		}
		xsdBody.append("		</xs:sequence>\r\n");
		xsdBody.append("	</xs:complexType>\r\n");
		
		//生成xsdHead的SimpleType
		StringBuilder xsdBodySimpleType=new StringBuilder();
		for(int i=0;i<sendBody.size();i++) {
			DetailEntity e=sendBody.get(i);
			//TODO 如果excel字段非法,应抛异常
			if("N".equals(e.getIsParentNode().toUpperCase())) {
				xsdBodySimpleType.append("	<xs:simpleType name=\""+e.getSelfName()+"\">\r\n");
				xsdBodySimpleType.append("		<xs:restriction base=\"xs:string\">\r\n");
				xsdBodySimpleType.append("			<xs:length value=\"256\"></xs:length>\r\n");
				xsdBodySimpleType.append("		</xs:restriction>\r\n");
				xsdBodySimpleType.append("	</xs:simpleType>\r\n");
			}
		}
		
		//body部分增加注释,用于和head做区分
		String start="	<!-- body部分从此开始 -->\r\n" ;
		String end="	<!-- ^^body部分结束^^ -->\r\n" ;
		return start+xsdBody+xsdBodySimpleType+end;
	}
}
