package com.liu.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
	
	//private IService iservice;
	public static void main(String[] args) throws ClassNotFoundException {
		//����һ�����ȫ�����ַ��������һ����������
		Class<?> clazz=Class.forName("java.lang.String");
		
		//��ô��ݹ�����������з���
		Method [] me=clazz.getDeclaredMethods();
		for(Method ma:me) {
			//System.out.println(ma);
		}
		
		System.out.println("--------------------");
		//��������������
		Field[] fileds=clazz.getFields();
		for(Field f:fileds) {
			//System.out.println(f);
		}
		System.out.println("--------------------");
		//���������й�����
		Constructor<?>[] con=clazz.getDeclaredConstructors();
		for(Constructor co:con){
			//System.out.println(co);
		}
		
		
		
	}
}
