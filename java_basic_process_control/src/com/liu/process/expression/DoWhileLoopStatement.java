package com.liu.process.expression;
/**
 * do-while 循环
 * @author Administrator
 *随机产生若干字母A~Z,一直到出现字母Z。
 */
public class DoWhileLoopStatement {
	public static void main(String[] args) {
		char c;
		do {
			c=(char)('A'+(Math.random()*26));
			System.out.print(c+",");
		}while(c!='Z');
	}
}
