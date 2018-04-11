package bss.text.jxl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlUtils {
	/**
	 * ����Excel
	 * @param args
	 */
	public static void main(String[] args) {
		String [] title= {"id","name","sex"};
		//����excel�ļ�
		File file =new File("d:/jxl_text.xls");
		try {
			file.createNewFile();
			//����������
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			//����sheet
			WritableSheet sheet=workbook.createSheet("��һ��sheet", 0);
			Label label=null;
			//��һ����������
			for (int i = 0; i < title.length; i++) {
				label=new Label(i,0,title[i]);
				sheet.addCell(label);
			}
			//׷������
			for (int i = 1; i < 10; i++) {
				label=new Label(0,i,"a"+i);
				sheet.addCell(label);
				label=new Label(1,i,"user"+i);
				sheet.addCell(label);
				label=new Label(2,i,"��"+1);
				sheet.addCell(label);
			}
			//д������
			workbook.write();
			workbook.close();//�ر�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
