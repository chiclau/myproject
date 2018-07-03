package com.lyht.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesRead {

	private static Properties propsin = new Properties();
	static{
		try {
			        
			propsin.load(new InputStreamReader(PropertiesRead.class.getClassLoader().getResourceAsStream("app.properties"), "UTF-8")); 
			/*InputStream fin = PropertiesRead.class.getClassLoader().getResourceAsStream("app.properties");
			propsin.load(fin);*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getValue(String property){
		return propsin.getProperty(property);
	}
	public static void setValue(String property,String value,OutputStream fout){
		propsin.setProperty(property, value);
		try {
			propsin.store(fout, "升级版本");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
