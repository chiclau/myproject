package com.liu.join;

public class Hello	extends Thread {
	int i;
	@Override
	public void run() {
		while(true) {
			System.out.println("HELLO="+i++);
			if(i==5) break;
		}
		super.run();
	}
}
