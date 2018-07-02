package com.liu.stat;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee extends Person { //继承抽象类的子类

	public Employee(String n,double s, int year, int month,int day) {
		super(n);
		salary=s;
		GregorianCalendar calendar=new GregorianCalendar(year, month-1, day);
		hireDay=calendar.getTime();
	}


	@Override
	public String getDescription() { //实现抽象方法
		return String.format("公司雇员，年薪是Y.2f。", salary);
	}
	
	private double salary;
	private Date hireDay;

}
