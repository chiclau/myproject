package com.liu.terminate;
/**
 * 从一个线程中终止另一个线程
 * @author Administrator
 *运行时将有2个线程，一个是主线程，另一个是使用无限循环输出Hello1、Hello2等的线程t,t每输出两行
 *Hello,就休眠10ms。t的优先级要高于主线程。而当主线程的CPU被t强占后再次运行时，将首先通过对t
 *设置停止标志，使t结束。
 */
public class ThreadTerminate {
		public static void main(String[] args) {
			int i=0;
			Hello h=new Hello();
			Thread t=new Thread(h);
			t.setPriority(Thread.MAX_PRIORITY);
			t.start();
			System.out.println("Please stop saying Hello and say good morning!!");
			h.stopRunning();	//设置线程t的终止标志
			while(i<5) System.out.println("Good Morning " +i++);
		}
}
