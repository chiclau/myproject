package com.liu.array;
/**
 * Array
 * @author Administrator
 *
 */
public class ArrayTest {
		public static void main(String[] args) {
			int i;
			int a[]=new int[5]; //创建数组，并对每个数组元素赋予0初值
			for( i=0;i<5;i++)a[i]=i;	//对每个数组元素的下标赋值
			for( i=a.length-1;i>=0;i--)
				System.out.println("a["+i+"]="+a[i]);//按数组元素下表逆序输出
		}

}
