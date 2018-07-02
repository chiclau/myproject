package com.liu.stat;

public class Light {
	int wattage;				//瓦特数
	boolean indicator;	//开关
	String location;   		//位置
	//static变量
	static int counter; //创建灯管对象编号NO.
	public Light() {
		// TODO Auto-generated constructor stub
		wattage=50;
		indicator=true;
		location="X";
		++counter;//计数器增量
	}
	
	//static方法
	public static void writeCount() {
		System.out.println("Number of lights : " + counter);
		//瓦数不可赋值的编译:输出瓦数
		//System.out.println("Number of watts:  " +wattage);
	}
}
