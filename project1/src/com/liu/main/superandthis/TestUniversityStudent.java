package com.liu.main.superandthis;

public class TestUniversityStudent {
	public static void main(String[] args) {
		UniversityStudent uStudent=new UniversityStudent("张三", "计算机", "清华大学");
		System.out.println("名字= "+uStudent.name+ "  专业="+uStudent.department + "  学校 = "+uStudent.university);
	}
}
