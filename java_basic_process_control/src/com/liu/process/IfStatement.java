package com.liu.process;


public class IfStatement {
	public static void main(String[] args) {
		/**
		 * random()返回值是一个伪随机数，该数是带证号的double值，
		 * 在[0.0，1.0]的范围内正太分布
		 */
		int x=(int) (Math.random()*100);
		System.out.println("The score is :"+ x);
		if(x>60) {
			System.out.println("pass the examination!");
			System.out.println("Congratulations!");
			System.out.println("have a good day!");
		}
		System.out.println("bye bye");
	}
}
