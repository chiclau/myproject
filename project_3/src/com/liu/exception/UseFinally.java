package com.liu.exception;

public class UseFinally {
	static Switch sw=new Switch();
	public static void main(String[] args) {
		try {
			sw.on();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			sw.off();
		}
		
	}
}
