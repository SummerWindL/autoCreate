package controlCenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import loadEntity.DetailEntity;
import loadEntity.MainMsgEntity;
import loadSeperate.LoadExcel;
import loadSeperate.LoadHeadOrBodySheet;
import loadSeperate.LoadMainMsg;
import utils.FileUtils;
import writer.channelWriter.ChannelWriter;
import writer.channelWriter.ChooseFormat;
import writer.sqlWriter.DelSqlWriter;
import writer.sqlWriter.InsertSqlWriter;
import writer.xsdWriter.ReceiveXsdWriter;
import writer.xsdWriter.SendXsdWriter;

/**
 * 此程序只适用于解析xsdAndSql.xls特定格式文件,并生成需要的目标文件
 * JDK1.8
 * @author 深圳华润国际结算项目组
 */
public class Controller {

	public static void main(String[] args) {
		//FIXME excel文件位置和名称
		String excelPath = "C:\\Users\\18153\\Desktop\\0911111111104.xls";
		
		/**
		 * 每个表格对应一个list对象
		 */
		MainMsgEntity mainMsg = new MainMsgEntity();
		List<DetailEntity> sendHead = new ArrayList<>();
		List<DetailEntity> sendBody = new ArrayList<>();
		List<DetailEntity> receiveHead = new ArrayList<>();
		List<DetailEntity> receiveBody = new ArrayList<>();

		Workbook wb = LoadExcel.getWorkbook(excelPath);
		Sheet mainMsgSheet = wb.getSheet("mainMsg");
		Sheet sendHeadSheet = wb.getSheet("发出头");
		Sheet receiveHeadSheet = wb.getSheet("接收头");
		Sheet sendBodySheet = wb.getSheet("发出body");
		Sheet receiveBodySheet = wb.getSheet("接收body");
		/**
		 * 删除发出body空白行
		 */
		int i = sendBodySheet.getLastRowNum();
		HSSFRow tempRow;
		while(i>0) {
			i--;
			tempRow = (HSSFRow) sendBodySheet.getRow(i);
			if(tempRow == null) {
				sendBodySheet.shiftRows(i+1, sendBodySheet.getLastRowNum(), -1);
			}
		}
		System.out.println("++++++++++发出body:"+sendBodySheet.getLastRowNum());
		/**
		 * 删除发出接收body空白行
		 */
		int k;
		k= receiveBodySheet.getLastRowNum();
		HSSFRow tempRowReceive;
		while(k>0) {
			k--;
			tempRowReceive = (HSSFRow) receiveBodySheet.getRow(k);
			if(tempRowReceive == null) {
				receiveBodySheet.shiftRows(k+1, receiveBodySheet.getLastRowNum(), -1);
			}
		}
		System.out.println("||||||||||接收body:"+receiveBodySheet.getLastRowNum());

		/**
		 * 读取工作簿中的每个表格,并将读到的内容赋值给对应list对象
		 */
		LoadMainMsg loadMainMsg = new LoadMainMsg();
		LoadHeadOrBodySheet loadSheet = new LoadHeadOrBodySheet();
		loadMainMsg.load(mainMsg, mainMsgSheet);
		loadSheet.load(sendHead, sendHeadSheet);
		loadSheet.load(sendBody, sendBodySheet);
		loadSheet.load(receiveHead, receiveHeadSheet);
		loadSheet.load(receiveBody, receiveBodySheet);

//		System.out.println(mainMsg.toString());
//		System.out.println(sendHead.toString());
		
		/**
		 * 将list组装成需要的字符串
		 */
		SendXsdWriter sWriter =  new SendXsdWriter();
		String sendXsd=sWriter.getSendXsd(mainMsg, sendHead, sendBody);
//		System.out.println(sendXsd);
		
		ReceiveXsdWriter rWriter =  new ReceiveXsdWriter();
		String receiveXsd=rWriter.getReceiveXsd(mainMsg, receiveHead, receiveBody);
//		System.out.println(receiveXsd);
		
		//channel.xml
		ChannelWriter cWriter=new ChannelWriter();
		String channelXml=cWriter.getChannelXml(ChooseFormat.WHOLE, mainMsg, sendBody, receiveBody);
		//System.out.println(channelXml);
		
		//需要插入数据库的sql
		InsertSqlWriter insertSqlWriter=new InsertSqlWriter();
		String insertSql=insertSqlWriter.getInsertSql(mainMsg, sendHead, sendBody, receiveHead, receiveBody);
		//System.out.println(insertSql);
		
		//删除数据库中本次插入sql
		DelSqlWriter del= new DelSqlWriter();
		String delSql=del.getInsertSql(mainMsg, sendHead, sendBody, receiveHead, receiveBody);
		
		/**
		 * 将字符串生成文件
		 */
		//FIXME 生成文件的路径,默认C盘guojie文件夹
		String filePath="C:\\guojie\\";
		//发出xsd
		FileUtils.createFile(filePath, mainMsg.getSendXsdName(), ".xsd",sendXsd );
		//接收xsd
		FileUtils.createFile(filePath, mainMsg.getReceiveXsdName(), ".xsd",receiveXsd );
		//channel.xml
		FileUtils.createFile(filePath, mainMsg.getChannelName(), ".xml",channelXml );
		//需要插入数据库的sql
		FileUtils.createFile(filePath, "sql"+mainMsg.getSrcTransCode(), ".sql",insertSql );
		//删除数据库中本次插入sql
		//未实现
		
	}
}
