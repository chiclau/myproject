package com.liu.file.randomfile;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *  RandomFileTest展示了以字节文件方式写/读对象数组的三层程序结构。写和读的是二进制的字节流，读文件又是随机文件
 *  流。除了写/读文件的类型以及流的类型有所差别外，由于涉及随机文件流，要求写出/读入的记录等长，因此对于不等长的姓名
 *  字段，程序做了等长处理。
 * @author ChicLau
 *
 */
public class RandomFileTest {
	public static void main(String[] args) {
		Employee[]staff=new Employee[3];
		staff[0]=new Employee("周杰伦", 75000, 1980, 10, 12);
		staff[1]=new Employee("王力宏", 50000, 1979, 12, 1);
		staff[2]=new Employee("王可可", 8000, 1982, 2, 3);
		try {	//字节文件流。实现了DataOutput接口。以二进制文件输出流为参数
			DataOutputStream out=new DataOutputStream(new FileOutputStream("employee.txt"));
			for(Employee e:staff) e.writeData(out);
			out.close();
			//以二进制数据文件为参数，建立RandomAccessFile对象。"r"为"只读"
			RandomAccessFile in=new RandomAccessFile("employee.txt", "r");
			int n= (int) (in.length()/Employee.RECORD_SIZE);
			Employee [] newStaff=new Employee [n] ;
			for(int i=n-1;i>=0;i--) {
				newStaff[i]=new Employee();
				in.seek(i*Employee.RECORD_SIZE);    //寻找
				newStaff[i].readData(in);
			}
			in.close();
			for(Employee e:newStaff) System.out.println(e);//遍历数组newStaff元素e
		} catch (IOException e) {	//捕获IO异常，异常传递链的终点
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
