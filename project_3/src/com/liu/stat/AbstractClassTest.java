package com.liu.stat;
/**
 * 抽象类
 * @author Administrator
 *
 */
public abstract class AbstractClassTest {
	public static void main(String[] args) {
		Person[] people=new Person[2];
		people[0]=new Employee("张三", 5000, 1989, 10, 1);
		people[1]=new Student("砾石", "计算机科学与技术");
		for(Person p:people) System.out.println(p.getName()+" , "+p.getDescription());
	}
}
