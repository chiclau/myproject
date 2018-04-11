package bss.text.jxl;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class JxlReadExcel {
	/**
	 * JXL解析Excel
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		//创建workbook
		Workbook workbook= Workbook.getWorkbook(new File("d:/jxl_text.xls"));
		//获取工作表sheet
		Sheet sheet=workbook.getSheet(0);
		//获取数据
		for(int i=0;i<sheet.getRows();i++) {
			for(int j=0;j<sheet.getColumns();j++) {
				Cell cell=sheet.getCell(j,i);
				System.out.print(cell.getContents()+"  ");//得到单元格的内容
			}
			System.out.println();
		}
		workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
