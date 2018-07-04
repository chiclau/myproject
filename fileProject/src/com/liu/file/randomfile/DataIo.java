package com.liu.file.randomfile;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataIo {
	public static String readFixedString(int size,DataInput in) throws IOException {
		StringBuilder b=new StringBuilder(size);//较StringBuffer效率高，但是不支持多线程
		int i=0;
		boolean more=true;
		while (more&&i<size) {
			char ch=in.readChar();//将二进制数据转换成字符读出
			i++;
			if(ch==0) more=false;
			else {
				b.append(ch);
			}
		}
		in.skipBytes(2*(size-i));//跳过无效字符
		return b.toString();	//将StringBuilder对象中的字符串显示出来
	}
	
	public static void writerFixedString(String s,int size,DataOutput out) throws IOException {
		for(int i=0;i<size;i++) {
			char ch=0;
			if(i<s.length()) ch=s.charAt(i);
			out.writeChar(ch);
		}
	}
}
