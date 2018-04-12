package com.liu.demo1;

public class FatherTest {
	public FatherTest() {
		System.out.println("Hello");
	}
	public FatherTest(String s) {
		this();
		System.out.println("Hello,"+s);
	}
}
