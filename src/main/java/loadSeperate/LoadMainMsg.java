package loadSeperate;

import org.apache.poi.ss.usermodel.Sheet;

import loadEntity.MainMsgEntity;

public class LoadMainMsg {

	public void load(MainMsgEntity mainMsg, Sheet sheetName) {

		String maker = LoadExcel.getValue(sheetName, 0, 1);
		String workName = LoadExcel.getValue(sheetName, 1, 1);
		String srcTransCode = LoadExcel.getValue(sheetName, 3, 1);
		String destTransCode = LoadExcel.getValue(sheetName, 4, 1);
		String sendXsdName = LoadExcel.getValue(sheetName, 6, 1);
		String receiveXsdName = LoadExcel.getValue(sheetName, 7, 1);
		String channelName = LoadExcel.getValue(sheetName, 9, 1);
		String workFlowName = LoadExcel.getValue(sheetName, 10, 1);

		mainMsg.setMaker(maker);
		mainMsg.setWorkName(workName);
		mainMsg.setSrcTransCode(srcTransCode);
		mainMsg.setDestTransCode(destTransCode);
		mainMsg.setSendXsdName(sendXsdName);
		mainMsg.setReceiveXsdName(receiveXsdName);
		mainMsg.setChannelName(channelName);
		mainMsg.setWorkFlowName(workFlowName);

	}
}
