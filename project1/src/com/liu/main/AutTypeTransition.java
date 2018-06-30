package com.liu.main;

public class AutTypeTransition {
	
	public static void main(String[] args) {
			char a='h';
			byte b=6;
			int i=100;
			long l=567L;
			float  f=8.99f;
			double d=4.7788;
			int aa=a+i; 			//a自动转换为int类型后再运算
			long ll=l-aa;		//aa自动转换为long类型后再运算
			float ff=b*f;		//b自动转换为float类型后再运算
			double dd=ff/aa+d ;  //自动转换为float类型后运算ff/aa得float结果后再自动转换为double类型再运算
			
			System.out.println("aa=   "+aa);
			System.out.println("ll=   "+ll);
			System.out.println("ff=  "+ff);
			System.out.println("dd=   "+dd);
	}

}
