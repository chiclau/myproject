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
	 * POI����Excel�ļ�
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] title= {"id","name","sex"};
		
		//����Excel������
		HSSFWorkbook workbook=new HSSFWorkbook();
		//����һ��������sheet
		HSSFSheet sheet=workbook.createSheet("��ȱ���");
		//������һ��
		HSSFRow row=sheet.createRow(0);
		HSSFCell cell=null;
		//�����һ������id,name,sex
		for(int i=0;i<title.length;i++) {
			cell=row.createCell(i);
			cell.setCellValue(title[i]);;
		}
		//׷������ 
		for(int i=1;i<=10;i++) {
			HSSFRow nextRow=sheet.createRow(i);
			HSSFCell cell2=nextRow.createCell(0);
			cell2.setCellValue("a"+i);
			cell2=nextRow.createCell(1);
			cell2.setCellValue("name"+i);
			cell2=nextRow.createCell(2);
			cell2.setCellValue("��"+i);
			
		}
		//����һ���ļ�
		File file=new File("d:/poi_tesx.xls");
		try {
			file.createNewFile();
			//��excel����
			FileOutputStream stream=FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
