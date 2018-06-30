package com.liu.main.superandthis;

public class UniversityStudent extends Student{
	 String university;
		public UniversityStudent(String name, String department,String university) {
			super(name, department);
			this.university=university;
		}
}
