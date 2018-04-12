package com.actor;

public class Ma {

	public static void main(String[] args) {
		Thread t=new Thread() {
			public  void run() {
				pong();
			}
		};
		
		System.out.println("ping");
	}
	static void pong() {
		System.out.println("pong");
	}
}
