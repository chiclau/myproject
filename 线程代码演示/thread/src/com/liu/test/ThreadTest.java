package com.liu.test;
/**
 * 
 * @author Administrator
 *
 */
public class ThreadTest {
	public static void main(String[] args) {
		Thread t1=new Thread(new Hello());
	//	Thread t2=new Thread(new Hello());
		Thread t3=new Thread(new Hello2());
		t1.start();
	//	t2.start();
		t3.start();
	}
}
