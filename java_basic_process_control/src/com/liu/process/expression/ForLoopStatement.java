package com.liu.process.expression;
/**
 * for循环
 * @author Administrator
 *打印9*9乘法表
 */
public class ForLoopStatement {
	public static void main(String[] args) {
		int i,j;
		for(i=1;i<10;i++) {
			for(j=1;j<=i;j++) {
				System.out.print(i+"*"+j+"="+i*j+"     ");
			}
			System.out.println();
		}
	}
}
