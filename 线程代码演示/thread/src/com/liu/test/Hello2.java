package com.liu.test;
/**
 * 通过继承Thread类实现线程
 * @author Administrator
 *
 */
public class Hello2  extends Thread{
	int i;
		@Override
		public void run() {
		while(true) {
			System.out.println("HELLO2="+i++);
			if(i==5) break;
		}
			super.run();
		}
}
