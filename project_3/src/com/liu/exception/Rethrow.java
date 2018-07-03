package com.liu.exception;

public class Rethrow {
		public static void re() throws Exception {//定义方法，抛出Exception类的异常
			System.out.println("old exception in rt()");
			throw new Exception("throw from rt()");
		}
		
		public static void wq() throws Exception {
			try {
				re();
			} catch (Exception e) {
				System.out.println("Inside wq(),	e.printStackTrace();");
				e.printStackTrace();//输出堆栈使用情况
				throw e;					//重新抛出异常
			}
		}
		
		
		public static void main(String[] args) {
			try {
				wq();		//产生被重新抛出的对象
			} catch (Exception e) {
				System.out.println("Caught in main,e.printStackTrace();");
				e.printStackTrace();		//再次输出堆栈信息
			}
		}
}
