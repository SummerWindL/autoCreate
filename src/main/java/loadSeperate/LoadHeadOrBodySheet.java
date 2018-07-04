package loadSeperate;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import loadEntity.DetailEntity;
import utils.UUIDCreater;

public class LoadHeadOrBodySheet {

	public void load(List<DetailEntity> list, Sheet sheetName) {

		// 获得总行数
		int firstRowIndex = sheetName.getFirstRowNum() + 1; // 第一行是列名，所以不读
		int lastRowIndex = sheetName.getLastRowNum();// 最后一行

		System.out.println(sheetName.getSheetName()+"共: "+(lastRowIndex+1)+"行!");

		for (int i = firstRowIndex; i <= lastRowIndex; i++) {
			DetailEntity entity = new DetailEntity();
			String parentName = LoadExcel.getValue(sheetName, i, 0);
			String parentName2= LoadExcel.getValue(sheetName, i, 1);
			String selfName = LoadExcel.getValue(sheetName, i, 2);
			String selfDesc = LoadExcel.getValue(sheetName, i, 3);
			String key = LoadExcel.getValue(sheetName, i, 4);
			String isParentNode = LoadExcel.getValue(sheetName, i, 5);
			String attrLength = LoadExcel.getValue(sheetName, i, 6);
			String attrScale = LoadExcel.getValue(sheetName, i, 7);
			String attrType = LoadExcel.getValue(sheetName, i, 8);
			String type = LoadExcel.getValue(sheetName, i, 9);
			String needParentId = LoadExcel.getValue(sheetName, i, 10);
			String path = LoadExcel.getValue(sheetName, i, 11);
			String bindMapId=UUIDCreater.getUUID32();
					
			entity.setParentName(parentName);
			entity.setParentName2(parentName2);
			entity.setSelfName(selfName);
			entity.setSelfDesc(selfDesc);
			entity.setKey(key);
			entity.setIsParentNode(isParentNode);
			entity.setAttrLength(attrLength);
			entity.setAttrScale(attrScale);
			entity.setAttrType(attrType);
			entity.setType(type);
			entity.setNeedParentId(needParentId);
			entity.setPath(path);
			entity.setBindMapId(bindMapId);
			list.add(entity);
		}

	}

}
