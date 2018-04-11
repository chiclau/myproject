package com.itext.file;

import java.io.File;

public class FileAction {

	public static void main(String[] args) {
		File file=new File("D:\\");
		String test[];
		test=file.list();
		for(int i=0;i<test.length;i++)
		{
		System.out.println(test[i]);
		}
	}

}
