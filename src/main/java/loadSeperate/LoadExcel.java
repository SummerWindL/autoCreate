package loadSeperate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadExcel {
	
	public static Workbook getWorkbook(String excelPath) {
		Workbook wb=null;
		try {
			File excel = new File(excelPath);
			if (excel.isFile() && excel.exists()) {
				System.out.println(excel.getName());
				String[] split = excel.getName().split("\\.");
				// 根据文件后缀（xls/xlsx）进行判断
				FileInputStream fis = new FileInputStream(excel); // 文件流对象
				if ("xls".equals(split[1])) {
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(fis);
				} else {
					System.out.println("文件类型错误!");
				}

			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}
	
	/**
	 * 取得某个单元格内容,全部按照String返回
	 * @return
	 */
	public static String getValue(Sheet sheet,int row,int col) {
		
		Cell cell=sheet.getRow(row).getCell(col);
		if(cell==null) {
			return "";
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue().trim();
	}
	
	
	

}
