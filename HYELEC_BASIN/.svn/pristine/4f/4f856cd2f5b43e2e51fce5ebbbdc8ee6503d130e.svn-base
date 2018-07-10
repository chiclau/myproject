package com.lyht.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileUtil {

	public static List<String> readFile(String fileName){
		List<String> contentList = new ArrayList<String>();
		try {
			InputStream in=ReadFileUtil.class.getClassLoader().getResourceAsStream(fileName);
			InputStreamReader reader = new InputStreamReader(in,"UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String lineTxt = null;
			while((lineTxt = bufferedReader.readLine()) != null){
			    if(lineTxt.trim().length()>0){
			    	contentList.add(lineTxt);
			    }
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentList;
	}
	/**
	 * 根据文件获取读取流
	 * @param file
	 * @param charSet
	 * @return
	 */
	public static BufferedReader getReaderByFile(File file,String charSet){
		FileInputStream in = null;
		InputStreamReader reader = null;
		try {
			in = new FileInputStream(file);
			reader = new InputStreamReader(in,charSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(reader==null){
			return null;
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}
	/**
	 * 根据资源文件名称获取读取流
	 * @param fileName
	 * @param charSet
	 * @return
	 */
	public static BufferedReader getReaderByFileName(String fileName,String charSet){
		InputStream in = null;
		InputStreamReader reader = null;
		try {
			in = ReadFileUtil.class.getClassLoader().getResourceAsStream(fileName);
			reader = new InputStreamReader(in,charSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(reader==null){
			return null;
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}
	/**
	 * 
	 * 获取读取文件数据流
	 * @param fileName
	 * @param charSet
	 * @return
	 */
	public static BufferedReader getReaderByFilePath(String filePath,String charSet){
		FileInputStream in = null;
		InputStreamReader reader = null;
		try {
			in = new FileInputStream(filePath);
			reader = new InputStreamReader(in,charSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(reader==null){
			return null;
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}
	/**
	 * 获取读取文件数据流
	 * @param fileName
	 * @param charSet
	 * @return
	 */
	public static BufferedReader getReader(String fileName,String charSet){
		InputStream in=ReadFileUtil.class.getClassLoader().getResourceAsStream(fileName);
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(in,charSet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(reader==null){
			return null;
		}
		BufferedReader bufferedReader = new BufferedReader(reader);
		return bufferedReader;
	}
	/**
	 * 关闭流
	 * @param reader
	 */
	public static void closeReader(BufferedReader reader){
		if(reader!=null){
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String readSqlFile(String fileName){
		String sql = "";
		try {
			InputStream in=ReadFileUtil.class.getClassLoader().getResourceAsStream(fileName);
			InputStreamReader reader = new InputStreamReader(in,"UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String lineTxt = null;
			while((lineTxt = bufferedReader.readLine()) != null){
			    if(lineTxt.trim().length()>0){
			    	sql = sql+lineTxt;
			    }
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sql;
	}
}
