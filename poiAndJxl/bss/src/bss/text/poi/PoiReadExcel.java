package bss.text.poi;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * POI����Excel
 * @author Administrator
 *
 */
public class PoiReadExcel {

	public static void main(String[] args) {
		//��Ҫ������Excel
		File file=new File("d:/poi_tesx.xls");
		
		try {
			//����Excel,��ȡ�ļ�����
			HSSFWorkbook workbook=new HSSFWorkbook(FileUtils.openInputStream(file));
			//��ȡ��һ��������sheet���ƣ�   ���ַ�ʽ��ȡ������
			//HSSFSheet sheet=workbook.getSheet("");
			//��ȡĬ�ϵ�һ��������sheet
			HSSFSheet sheet=workbook.getSheetAt(0);
			int firstRowNum=0;
			int lastRowNum=sheet.getLastRowNum();//��ȡ���һ��
			for(int i=firstRowNum;i<=lastRowNum;i++) {
				HSSFRow row=sheet.getRow(i);
				//��ȡ��ǰ�����Ԫ���к�
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
