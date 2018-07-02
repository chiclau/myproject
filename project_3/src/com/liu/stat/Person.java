package com.liu.stat;

public abstract class Person {//抽象类声明
		public Person(String n) {
			name=n;
		}
		
		public abstract String getDescription();//没有方法体的抽象方法
		
		public String getName() {
			return name;
		}
		
		private String name;
}
