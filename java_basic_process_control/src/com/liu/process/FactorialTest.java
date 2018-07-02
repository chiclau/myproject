package com.liu.process;
/**
 * 递归的基本思想是"自己调用自己",
 * @author Administrator
 *利用递归结构求n!
 */
public class FactorialTest {
	static long Factorial(int n) { //定义Factorial()方法
		if(n==1) return 1;
		else {
			return n*Factorial(n-1);
		}
	}
	
	public static void main(String[] args) { //main()方法
		int n=8;
		System.out.println(n+" != "+Factorial(n)); //调用Factorial()方法，参数为n
	}
}
