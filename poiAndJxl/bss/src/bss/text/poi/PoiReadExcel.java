package bss.text.poi;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * POI解析Excel
 * @author Administrator
 *
 */
public class PoiReadExcel {

	public static void main(String[] args) {
		//需要解析的Excel
		File file=new File("d:/poi_tesx.xls");
		
		try {
			//创建Excel,读取文件内容
			HSSFWorkbook workbook=new HSSFWorkbook(FileUtils.openInputStream(file));
			//获取第一个工作表（sheet名称）   两种方式读取工作表
			//HSSFSheet sheet=workbook.getSheet("");
			//读取默认第一个工作表sheet
			HSSFSheet sheet=workbook.getSheetAt(0);
			int firstRowNum=0;
			int lastRowNum=sheet.getLastRowNum();//获取最后一行
			for(int i=firstRowNum;i<=lastRowNum;i++) {
				HSSFRow row=sheet.getRow(i);
				//获取当前行最后单元格列号
				int lastCellNum=row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					HSSFCell cell=row.getCell(j);
					String value=cell.getStringCellValue();
					System.out.print(value+ "  ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
