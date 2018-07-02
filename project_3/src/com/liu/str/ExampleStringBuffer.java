package com.liu.str;
/**
 * StingBuffer类的实例
 * @author Administrator
 *
 */
public class ExampleStringBuffer { 
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer("test");
		System.out.println("buffer="+sb);
		System.out.println("length="+sb.length());
		System.out.println("capacity= "+sb.capacity());
	}
}
