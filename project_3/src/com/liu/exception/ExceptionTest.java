package com.liu.exception;

import java.util.Date;
import java.util.Stack;
/**
 * 用异常测试空栈100 0000次所花的时间与用 if(!s.empty()) s.pop()测试空栈100 0000次所花的时间的比较
 * @author Administrator
 *
 */
public class ExceptionTest {
	public static void main(String[] args) {
		int i=0;
		int ntry=1000000;
		Stack s=new Stack();
		long s1;
		long s2;
		System.out.println("Testing for empty stack");
		s1=new Date().getTime();
		for(i=0;i<=ntry;i++)
			if(!s.empty()) s.pop();
		s2=new Date().getTime();
			System.out.println((s2-s1)+"milliseconds");
			System.out.println("Catch EmptyStackException");	
		s1=new Date().getTime();
		for(i=0;i<=ntry;i++) {
			try {
				s.pop();
			} catch (Exception e) {
				}
		}
		
		s2=new Date().getTime();
		System.out.println((s2-s1)+"milliseconds");
	}
}
