package com.liu.str;
/**
 * String类
 * @author Administrator
 * 对"test"字符串的各种表示方法。
 */
public class ConstString {
	public static void main(String[] args) {
		char chars1[]= {'t','e','s','t'};
		char chars2[]= {'t','e','s','t','1'};
		String s1=new String(chars1);
		String s2=new String(chars2, 0, 4);
		System.out.println("value of String s1 is "+s1);
		System.out.println("value of String s2 is "+s2);
		System.out.println("都是字符串'test'!");
	}
}
