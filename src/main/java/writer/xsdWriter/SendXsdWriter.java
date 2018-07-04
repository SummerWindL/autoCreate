package writer.xsdWriter;

import java.util.List;

import loadEntity.DetailEntity;
import loadEntity.MainMsgEntity;
import writer.XsdWriter;

/**
 * 将对象信息组装成完整的 发出xsd 文档
 */
public class SendXsdWriter extends XsdWriter {

	public String getSendXsd(MainMsgEntity mainMsg, List<DetailEntity> sendHead, List<DetailEntity> sendBody) {
		// 文档顶部声明等
		String xsTop = xsTopCreater(mainMsg.getSendXsdName());
		// 文档header部分
		XsdHead xsdHead = new XsdHead();
		String xsHeade = xsdHead.getXsdHead(mainMsg, sendHead);
		// 文档body部分
		XsdBody xsdBody = new XsdBody();
		String xsBody = xsdBody.getXsdBody(sendBody);

		return xsTop + xsHeade + xsBody + xsSchema4;
	}

}
