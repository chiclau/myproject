package com.liu.demo1;

public class Test extends FatherTest {
	public Test(String s) {
		super(s);
		System.out.println("how are you,"+s);
	}
	
	public static void main(String[] args) {
		Test t=new Test("Tom");
		
	}

}
