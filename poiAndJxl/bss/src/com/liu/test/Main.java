package com.liu.test;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
			System.out.println(new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
