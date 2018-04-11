package bss.text.poi;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiExpExcel {
	/**
	 * POI生成Excel文件
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] title= {"id","name","sex"};
		
		//创建Excel工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建一个工作表sheet
		HSSFSheet sheet=workbook.createSheet("年度报告");
		//创建第一行
		HSSFRow row=sheet.createRow(0);
		HSSFCell cell=null;
		//插入第一行数据id,name,sex
		for(int i=0;i<title.length;i++) {
			cell=row.createCell(i);
			cell.setCellValue(title[i]);;
		}
		//追加数据 
		for(int i=1;i<=10;i++) {
			HSSFRow nextRow=sheet.createRow(i);
			HSSFCell cell2=nextRow.createCell(0);
			cell2.setCellValue("a"+i);
			cell2=nextRow.createCell(1);
			cell2.setCellValue("name"+i);
			cell2=nextRow.createCell(2);
			cell2.setCellValue("男"+i);
			
		}
		//创建一个文件
		File file=new File("d:/poi_tesx.xls");
		try {
			file.createNewFile();
			//将excel存盘
			FileOutputStream stream=FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
