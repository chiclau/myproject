package com.lyht.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 *   读取资源文件
 * @author liuamang
 * @since   Aug 16, 2010
 * @version 1.0
 */
public class PropertiesUtil {
	private static Logger log = Logger.getLogger(PropertiesUtil.class);
	private static Properties propsin = new Properties();
	static{
		try {
			InputStream fin = PropertiesUtil.class.getClassLoader().getResourceAsStream("import_data.properties");
			propsin.load(fin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getValue(String property){
		return propsin.getProperty(property);
	}
	/**
	 * 获取资源文件配置
	 * @param propertyName
	 * @return
	 */
	public static String getPropertyValue(String propertyName){
//		System.out.println(propertyName+"------------------------"+CommonUtil.trim(propsin.get(propertyName)));
		return CommonUtil.trim(propsin.get(propertyName));
	}
	public static String loginType(String filePath,String key){
		InputStream input =null;
		Properties p= null;
		 try {
			  input = new FileInputStream(new File(filePath));
			  p= new Properties();
			  p.load(input);
			  return CommonUtil.trim(p.get(key));
		} catch (Exception e) {
			log.error("读取资源文件出错~~~找不到对用的值");
		}
		return "";
	}
	
	
}
