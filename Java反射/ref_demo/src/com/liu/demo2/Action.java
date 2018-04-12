package com.liu.demo2;

public class Action {
	public static void main(String[] args) {
		String s1="abc"+"def";
		String s2=new String(s1);
		if(s1==s2)
		System.out.println("==succeed");
		else
		System.out.println("fail");
		if(s1.equals(s2))
	    System.out.println(".equals() succeed");
	}
}
