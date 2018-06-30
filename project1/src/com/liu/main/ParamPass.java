package com.liu.main;

public class ParamPass {
	static class Object1{
		public String QQ="小猫";
	}
	
	static void paramChange(int x,Object1 obj1) {
		x=18;
		obj1.QQ="小狗";
	}
	
	public static void main(String[] args) {
		int x=28;
		Object1 obj1=new Object1();
		System.out.println("传递前的参数值:X="+x+ "  Q  Q"+obj1.QQ);
		ParamPass.paramChange(x, obj1);
		System.out.println("传递后的参数值:X="+x+ "  Q  Q"+obj1.QQ);
	}
}
