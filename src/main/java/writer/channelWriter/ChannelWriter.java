package writer.channelWriter;

import java.util.List;

import loadEntity.DetailEntity;
import loadEntity.MainMsgEntity;
/**
 * 生成channel.xml文件
 */
public class ChannelWriter {
	/**
	 * 
	 * @param format 如果是whole则生成完整格式的xml,如果是part则生成<message-mapping/>部分
	 * @return
	 */
	public String getChannelXml(ChooseFormat format,MainMsgEntity mainMsg, List<DetailEntity> sendBody, List<DetailEntity> receiveBody) {
		String xmlDeclare = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";//xml文件声明
		String rootStart="<mappings>\r\n";//根节点开始标签
		String rootEnd="</mappings>\r\n";//根节点结束标签
		
		String channelXml="";
		StringBuffer mapping =new StringBuffer();
		mapping.append("	<!--"+mainMsg.getWorkName()+"  -->\r\n");//注释
		//send-mapping
		mapping.append("    <message-mapping key=\""+mainMsg.getSrcTransCode()+"\" desc=\""+mainMsg.getWorkName()+"\">\r\n");
		mapping.append("		<send-mapping class=\"ChannelMessage\" desc=\""+mainMsg.getWorkName()+"请求\">\r\n");
		mapping.append("			<field set=\"COMMON_SEND_HEADER_KEY\" value=\"'crcb_common'\" /><!-- esb header 公共字段取值 -->\r\n");
		for(int i=0;i<sendBody.size();i++) {
			DetailEntity e= sendBody.get(i);
			if("n".equals(e.getIsParentNode().toLowerCase())) {
				mapping.append("		    <field set=\""+e.getSelfName()+"\" value=\""+e.getKey()+"\" />\r\n");
			}
		}
		mapping.append("		</send-mapping>\r\n");
		//recv-mapping
		mapping.append("		<recv-mapping class=\"ChannelMessage\" desc=\""+mainMsg.getWorkName()+"应答\">\r\n");
		mapping.append("			<field set=\"COMMON_SEND_HEADER_KEY\" value=\"'crcb_common'\" /><!-- esb header 公共字段取值 -->\r\n");
		for(int i=0;i<receiveBody.size();i++) {
			DetailEntity e= receiveBody.get(i);
			if("n".equals(e.getIsParentNode().toLowerCase())) {
				mapping.append("		    <field set=\""+e.getSelfName()+"\" value=\""+e.getKey()+"\" />\r\n");
			}
		}
		mapping.append("		</recv-mapping>\r\n");
		mapping.append("	</message-mapping>\r\n");
		
		if(format.equals(ChooseFormat.WHOLE)) {
			channelXml=xmlDeclare+rootStart+mapping+rootEnd;
		}else if(format.equals(ChooseFormat.PART)) {
			channelXml=""+mapping;
		}else {
			//TODO 应抛出异常
			System.out.println("调用getChannelXml()方法的参数有误,应为WHOLE或者PART...");
		}
		
		return channelXml;
	}
}
