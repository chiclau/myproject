package com.liu.file.randomfile;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Employee {
	public Employee() {}
	public Employee(String n,double s,int year, int month, int day) {
		name=n;
		salary=s;
		GregorianCalendar calendar= new  GregorianCalendar(year,month-1,day);
		hireDay=calendar.getTime();
	}
	
	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}
	
	public void raiseSalary(double byPercent) {
		double raise=salary*byPercent/100;
		salary+=raise;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}
	
	public void writeData(DataOutput out) throws IOException {
		DataIo.writerFixedString(name, NAME_SIZE, out);
		out.writeDouble(salary);
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(hireDay);
		out.writeInt(calendar.get(calendar.YEAR));
		out.writeInt(calendar.get(calendar.MONTH)+1);
		out.writeInt(calendar.get(calendar.DAY_OF_MONTH));
	}
	
	public void readData(DataInput in) throws IOException {
		name=DataIo.readFixedString(NAME_SIZE, in);
		salary=in.readDouble();
		int y=in.readInt();
		int m=in.readInt();
		int d=in.readInt();
		GregorianCalendar calendar=new GregorianCalendar();
		hireDay=calendar.getTime();
	}

	public static final int NAME_SIZE=40;// 姓名按照40个字符设计
	public static final int RECORD_SIZE=2*NAME_SIZE+8+4+4+4;
	public String name;	//2*40=80个字节
	public double salary;//64个bit,占8个字节
	private Date hireDay;//(3*32bit),3*4=12个字节
	
}
