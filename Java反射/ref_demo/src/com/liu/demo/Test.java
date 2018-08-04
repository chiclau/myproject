package com.liu.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
	
	//private IService iservice;
	public static void main(String[] args) throws ClassNotFoundException {
		//根据一个类的全名称字符串来获得一个类的类对象
		Class<?> clazz=Class.forName("java.lang.String");
		
		//获得传递过来的类的所有方法
		Method [] me=clazz.getDeclaredMethods();
		for(Method ma:me) {
			//System.out.println(ma);
		}
		
		System.out.println("--------------------");
		//获得类的所有属性
		Field[] fileds=clazz.getFields();
		for(Field f:fileds) {
			//System.out.println(f);
		}
		System.out.println("--------------------");
		//获得类的所有构造器
		Constructor<?>[] con=clazz.getDeclaredConstructors();
		for(Constructor co:con){
			//System.out.println(co);
		}
		
		
		
	}
}
