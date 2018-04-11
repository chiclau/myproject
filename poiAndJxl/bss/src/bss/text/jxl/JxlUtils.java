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
	 * 创建Excel
	 * @param args
	 */
	public static void main(String[] args) {
		String [] title= {"id","name","sex"};
		//创建excel文件
		File file =new File("d:/jxl_text.xls");
		try {
			file.createNewFile();
			//创建工作簿
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			//创建sheet
			WritableSheet sheet=workbook.createSheet("第一个sheet", 0);
			Label label=null;
			//第一行设置列名
			for (int i = 0; i < title.length; i++) {
				label=new Label(i,0,title[i]);
				sheet.addCell(label);
			}
			//追加数据
			for (int i = 1; i < 10; i++) {
				label=new Label(0,i,"a"+i);
				sheet.addCell(label);
				label=new Label(1,i,"user"+i);
				sheet.addCell(label);
				label=new Label(2,i,"男"+1);
				sheet.addCell(label);
			}
			//写入数据
			workbook.write();
			workbook.close();//关闭
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
