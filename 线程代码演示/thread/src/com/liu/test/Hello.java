package com.liu.test;
/**
 * 通过实现Runnable接口创建线程
 * @author Administrator
 *
 */
public class Hello implements Runnable{
	int i;
	@Override
	public void run() {
			while(true) {
				System.out.println("Hello "+ i++);
				if(i==5) break;
			}
	}
	
}
