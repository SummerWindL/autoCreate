package writer.xsdWriter;

import java.util.List;

import loadEntity.DetailEntity;
import loadEntity.MainMsgEntity;
import writer.XsdWriter;

/**
 * 将对象信息组装成完整的 接收xsd 文档
 */
public class ReceiveXsdWriter extends XsdWriter {

	public String getReceiveXsd(MainMsgEntity mainMsg, List<DetailEntity> receiveHead, List<DetailEntity> receiveBody) {
		// 文档顶部声明等
		String xsTop = xsTopCreater(mainMsg.getReceiveXsdName());
		// 文档header部分
		XsdHead xsdHead = new XsdHead();
		String xsHeade = xsdHead.getXsdHead(mainMsg, receiveHead);
		// 文档body部分
		XsdBody xsdBody = new XsdBody();
		String xsBody = xsdBody.getXsdBody(receiveBody);

		return xsTop + xsHeade + xsBody + xsSchema4;
	}
}
