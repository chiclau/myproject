package com.liu.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 循环语句与分支语句可互相嵌套，以实现复杂算法。分支语句的任意一个分支中都可以嵌套
 * 一个完整的循环语句，同样循环体中冶可以包含完整的分支语句。
 * @author Administrator
 * 接收用户输入的任一年的年数，然后由程序在标准输出上打印出该年的日历。
 */
public class Calendar {
	static int year; //打印哪一年的日历
	static int weekDay;	//该年的第一天是星期几
	//由于要求用户输入，考虑可能有I/O异常，需抛出异常
	public static void main(String[] args) throws IOException{
		//下列代码用于从键盘接收用户输入的年份
		System.out.println("请输入年份:");
		InputStreamReader ir;
		BufferedReader in;
		ir=new InputStreamReader(System.in);
		in=new BufferedReader(ir);
		String s =in.readLine();//读取文本的一行
		year=Integer.parseInt(s);
		if(year<1) {
			System.out.println("输入的年份不能小于1！！");
			return;
		}
		weekDay=(int) firstDayOfYear(year);
		System.out.println(" "+year+"年");
		System.out.println("=====================================");		
			for(int i=1;i<=12;i++) {
				System.out.println();
				printMoth(i);
				System.out.println();
			}	
				
				
				
			
	}
	/**打印每月日期 */
	public static void printMoth(int m) {
		printHead(m);
		int days=daysOfMonth(m);
		for(int i=1;i<=days;i++) {
			if(i<10)System.out.println(" "+i);
			else System.out.println("          "+i);
			weekDay=(weekDay+1)%7;
			if(weekDay==0) {
				System.out.println();
				System.out.print("      ");
			}
		}
	}
	/***打印每周星期标题*/	
	public static void printHead(int m) {
		System.out.println("     "+m+"  月    日      一       二      三       四       五   六");
		System.out.println("      ");
		for(int i=0;i<weekDay;i++)System.out.println("  ");
	}
	
	/***计算每年第一天是星期几*/
	public static long firstDayOfYear(int y) {
		long n;
		n=y*365;
		for(int i=1;i<y;i++) {
			if(isLeapYear(i)) n+=1;
		}
		return n%=7;
	}
	
	/**返回每月的天数*/
	public static int daysOfMonth(int m) {
		switch (m) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: return 31;
		case 4:
		case 6:
		case 9:
		case 11:return 30;
		case 2:if(isLeapYear(year)) return 29;
						else  return 28;
		}
		return 0;
	}
	
	
	/***判断是不是闰年
	 * @return */
	public static boolean isLeapYear(int y) {
		return ((y%4==0&&y%100!=0)||y%400==0);
	}
}
