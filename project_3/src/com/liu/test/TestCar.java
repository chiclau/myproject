package com.liu.test;

public class TestCar {
	public static void main(String[] args) {
		Car x=new Car("红旗H6", false);
		Car y=new Car("哈弗H6", false);
		System.out.println(x+"等同于"+y+"是:"+x.equals(y));
		System.out.println("x与y是同等的对吗？"+(x==y));
		Car z=y;
		System.out.println(z+"等同于"+y+"是:"+z.equals(y));
		System.out.println("z与y是同等的对吗？"+(z==y));
	}
}
