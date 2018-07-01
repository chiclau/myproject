package com.liu.process.expression;
/**
 * if -else -if选择语句
 * @author Administrator
 *随机产生10位同学的成绩(0~100)，然后统计90~100、80~89、70~79、60~69
 *和60分以下的各个成绩段的同学的人数。
 */
public class IfElseIf {
	public static void main(String[] args) {
		int score90Number=0,score80Number=0,score70Number=0;
		int score60Number=0,scoreUnder60Number=0;
		int score;
		System.out.print("十位同学的成绩分别为");
		for(int i=0;i<10;i++) {
			score =(int) (Math.random()*100);
			System.out.print(score+",");
			if(score>=90) {
				score90Number++;
			}else if (score>=80) {
				score80Number++;
			}else if (score>=70) {
				score70Number++;
			}else if (score>=60) {
				score60Number++;
			}else {
				scoreUnder60Number++;//60分以下
			}
		}
		System.out.println();
		System.out.println("90分以上的人数:"+score90Number);
		System.out.println("80分以上的人数:"+score80Number);
		System.out.println("70分以上的人数:"+score70Number);
		System.out.println("60分以上的人数:"+score60Number);
		System.out.println("60分以下的人数:"+scoreUnder60Number);
	}
}
