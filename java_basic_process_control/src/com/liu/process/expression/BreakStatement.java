package com.liu.process.expression;
/**
 * break语句
 * @author Administrator
 * 十位同学参加某次团队测试，要求每位同学都必须及格，同时团队平均分不少于80分，
 * 整个团队才能够通。每位同学的成绩可以通过随机数产生(0~100)。
 */
public class BreakStatement {
	public static void main(String[] args) {
		int score,sum=0,i;
		for(i=1;i<=10;i++) {
			score=(int) (Math.random()*100);
			System.out.print(score+",");
			if(score<60) {
				break;
			}
			sum+=score;
			i++;
		}
		System.out.println();
		if((i==10)&&(sum/10)>=80) {
			System.out.println("团队测试通过!");
		}else {
			System.out.println("团队测试不通过!");
		}
	}
}
