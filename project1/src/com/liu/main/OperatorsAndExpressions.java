package com.liu.main;

public class OperatorsAndExpressions {
	void SingleArithmaticOperator() {//一元算术运算符的应用
		float i=2.0f,j=10.0f;
		int m=20,n=10;
		System.out.println((++i)*(j--));
		System.out.println("i="+i+",j="+j);
		System.out.println((i++)*(j--));
		System.out.println("i="+i+",j="+j);
		System.out.println((--m)*(n++));
		System.out.println("m="+m+",n="+n);
		System.out.println((m--)*(n++));
		System.out.println("m="+m+",n="+n);
	}
	
	void doubleArithmaticOperator() {//二元算术运算符的应用
		System.out.println(9/2);//结果为4，因为9和2都是int型，结果应该也为int类型
		System.out.println(5/2.0);//结果为2.5，因操作数2.0为double型，结果也为double型
		byte x=3,y=4;
		long r=80L;
		System.out.println(r/y);	//结果为20L，因为r为long型，结果也是long型
		System.out.println(x*y);	//结果为12，两个操作数都是byte型，结果也是int型
		float z=12.5f,w=5.5f;
		System.out.println(z+w);	//结果为18.0f,因为两个操作数都是float型，结果是float型
		System.out.println(z-x);		//结果为9.5f,z为float型。结果为float型、
	}
	/**
	 * 取模运算符在整数和浮点数中的应用
	 * @param args
	 */
	void residual() {
		int i=10,j=3;
		float m=213.5f,n=4.0f;
		System.out.println(1%j);
		System.out.println(m%n);
	}
	/**
	 * "+"运算符在字符串中的应用
	 */
	void stringPlus() {
		int x=3,y=4,z=5;
		String s="xyz=";
		System.out.println(x+y+z);
		System.out.println(s+x+y+z);
		System.out.println(x+y+z+s);
		System.out.println("abc"+3);
		System.out.println(3.0+"abc");
	}
	/**
	 * 算术运算符的优先级
	 */
	void priorityOfArithmaticOperator() {
		int a=10,b=4,c=20,d=6;
		System.out.println(a+ b*c+d);
		System.out.println(a+c%b);
		System.out.println(a++*b+c*--d); //
	}
	/**
	 * 用于基本类型数据和复合类型数据的"=="运算符
	 */
	void equalsMethod1() {
			int i=10,j=15;
			System.out.println(i==j);	//基本类型的数据之间的比较
			String s1=new String("how are you");
			String s2=new String("how are you");	
			System.out.println(s1==s2);   //复合数据类型的数据之间的比较
	}
	
	void equalsMethod2(){
		String s1=new String("hello");
		String s2=new String("hello");
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
	}	
	
	class MyValue{
		int i;
	}
	
	public static void main(String[] args) {
		OperatorsAndExpressions as=new OperatorsAndExpressions();
		//as.SingleArithmaticOperator();//一元
		//as.doubleArithmaticOperator();	//二元
		//as.residual();	//取模运算符在整数和浮点数中的应用
		//as.stringPlus();//"+"运算符在字符串中的应用
		//as.priorityOfArithmaticOperator();  //算术运算符的优先级
		as.equalsMethod1();// ==的比较
	} 
}
