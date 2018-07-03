package com.lyht.util;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@SuppressWarnings({ "deprecation", "rawtypes" })
public class ExcelUtils {
	public static String SellerStat2Excel( List data ,HttpServletRequest request ,String ymonth, String [] tabHead ,String sheetTitle,String[] val) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetTitle);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        sheet.setDefaultColumnWidth((short) 15); 
        // 第四步，创建单元格，并设置值表头 设置表头居中s
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCell cell = null;
        int index = 1;
        for (String str : tabHead) {
			if(cell == null) {
				cell = row.createCell((short) 0);
				cell.setCellValue(str);
		        cell.setCellStyle(style);
			}else{
				cell = row.createCell((short) index);
				cell.setCellValue(str);
		        cell.setCellStyle(style);
		        index ++;
			}
		}
        style.setLocked(true);
        
        int i = 0;
        try {
        	for (Object obj : data) {
        		Map map = null;
        		if(obj instanceof Map){
        			map = (Map)obj;
        		}
                row = sheet.createRow((int) i + 1);
                int num = 1;
                row.createCell((short) 0).setCellValue(i+1);
                // 第四步，创建单元格，并设置值
                for (String str : val) {
                	Object object=map.get(str);
                	if(null==object){
                		row.createCell((short) num).setCellValue("");
                	}else{
                		row.createCell((short) num).setCellValue(object.toString());
                	}
	                num++;
                }
                i++;
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        String realPath = request.getSession().getServletContext().getRealPath("/");
        // 第六步，将文件存到指定位置
        String path = realPath+ymonth+"_"+sheetTitle+".xls";
        try {
        	FileOutputStream  fout = new FileOutputStream(path);
            wb.write(fout);
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ymonth+"_"+sheetTitle+".xls";
    }


}
