package com.liu.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HrefMatch {
	public static void main(String[] args) {
		try {
		Scanner typeIn=new Scanner(System.in);
		System.out.println("请输入网址:");
		String urlString=typeIn.nextLine();
		InputStreamReader in=new InputStreamReader //输入流阅读器(参数)
		(new URL(urlString).openStream()); 	//参数:URL..openStream()
		StringBuilder input=new StringBuilder();
		int ch;
		while((ch=in.read())!= -1) input.append((char)ch); //循环读网页字符到StringBuilder
		//<a href="...">的模式串
		String pattenString="<a\\s+href\\s*=\\s*(\"[^\"]*\"丨[^\\s>])\\s*>";
		//编译模式串(区分大小写)，生成模式对象
		Pattern pattern =Pattern.compile(pattenString, Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(input);
		while(matcher.find()) {
			int start=matcher.start();
			int end=matcher.end();
			String match=input.substring(start,end);
			System.out.println(match);
		}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
