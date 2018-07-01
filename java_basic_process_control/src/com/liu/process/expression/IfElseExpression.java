package com.liu.process.expression;
/**
 * 随机产生一个年份(1~2000)，判断是否为闰年
 * @author chic
 *
 */
public class IfElseExpression {
	public static void main(String[] args) {
		/**
		 * 分析:闰年的年份可被4整除而不能被100整除，或者被400整除。因此,首先产生的
		 * 年份存放到变量year中，
		 * 如果表达式:year%4==0&&year%100!=0||year%400==0的值为true,则为闰年
		 * 否则就不是闰年
		 */
		int year=(int) (Math.random()*2000)+1;
		//用If-else结构判断year中的年份是否为闰年
		if(year%4==0&&year%100!=0||year%400==0) {
			System.out.println(year+" 年是闰年");
		}else {
			System.out.println(year+" 年不是闰年");
		}
	}
}
