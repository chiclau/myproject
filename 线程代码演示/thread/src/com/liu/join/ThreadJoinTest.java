package com.liu.join;
/**
 * join方法实例
 * @author Administrator
 *
 */
public class ThreadJoinTest {
	public static void main(String[] args) throws Exception {
		int i=0;
		Hello t=new Hello();
		t.start();
		while(true) {
			System.out.println("Good Morning "+i++);
			if(i==2 && t.isAlive()) {
				System.out.println("Main wating for Hello");
				t.join();  //等待t运行结束
			}
			if(i==5) break;
		}
		
		
		
		
	}

}
