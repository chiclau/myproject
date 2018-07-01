package com.liu.process.expression;

/**
 *  while循环
 * @author Administrator
 *	十位同学参加某次团队测试，要求每位同学都不必及格，同时团队平均分不少于80分，
 *整个团队才能够通过。每位同学的成绩可以通过随机数产生(0~100之间)。
 */
public class WhileLoopStatement {
	public static void main(String[] args) {
		int score=60;
		int sum=0;
		int i=1;
		while((score)>=60&&(i<=10)) {
			score=(int) (Math.random()*100);
			System.out.print(score+",");
			sum+=score;
			i++;
		}
		System.out.println();
		if((i==10)&&(sum/10)>=80) {
			System.out.println("团队测试通过！");
		}else {
			System.out.println("团队测试不通过！");
		}
	}
}
