package bss.text.jxl;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class JxlReadExcel {
	/**
	 * JXL����Excel
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		//����workbook
		Workbook workbook= Workbook.getWorkbook(new File("d:/jxl_text.xls"));
		//��ȡ������sheet
		Sheet sheet=workbook.getSheet(0);
		//��ȡ����
		for(int i=0;i<sheet.getRows();i++) {
			for(int j=0;j<sheet.getColumns();j++) {
				Cell cell=sheet.getCell(j,i);
				System.out.print(cell.getContents()+"  ");//�õ���Ԫ�������
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
