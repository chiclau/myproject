package com.liu.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionCatch {
	public static void main(String[] args) {
		try {
			FileInputStream fis= new FileInputStream("text");
			System.out.println("content of text is:");
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.out.println("message:"+e.getMessage());
			e.printStackTrace(System.out);
		}catch (IOException e) {
			System.out.println(e);
		}
	
	}
}
