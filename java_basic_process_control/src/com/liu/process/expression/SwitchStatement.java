package com.liu.process.expression;
/**
 * switch表达式
 * @author Administrator
 *如果本月的第一天是星期一，随机产生本月某一天的日期(1~30),并显示该天是星期几。
 */
public class SwitchStatement {
	public static void main(String[] args) {
		int date=(int) (Math.random()*30);
		switch(date%7) {
			case 0:
				System.out.println("本月"+date+"日是星期天");
				break;
			case 1:
				System.out.println("本月"+date+"日是星期一");
				break;
			case 2:
				System.out.println("本月"+date+"日是星期二");
				break;
			case 3:
				System.out.println("本月"+date+"日是星期三");
				break;
			case 4:
				System.out.println("本月"+date+"日是星期四");
				break;
			case 5:
				System.out.println("本月"+date+"日是星期五");
				break;
			case 6:
				System.out.println("本月"+date+"日是星期六");
				break;
				
		}
	}
}
