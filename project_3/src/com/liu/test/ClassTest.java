package com.liu.test;

import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 
 * @author Administrator
 * 根据含有java类库目录结构的类名(完全限定名)显示出该类的修饰符和父类。
 */
public class ClassTest {
	public static void main(String[] args) {
		String name;
		System.out.println("根据类名获取类修饰符及其直接父类名:");
		Scanner in=new Scanner(System.in);
		System.out.println("请输入含有包路径的类名(如 java.util.Date)");
		name =in.next();
	
		try {
			Class cl= Class.forName(name);	
			Class supercl=cl.getSuperclass();
			String modifiers=Modifier.toString(cl.getModifiers());
			if(modifiers.length()>0)
				System.out.println(name+"  的修饰符是: "+modifiers +  " ");
			System.out.print( name );
			if(supercl!=null) System.out.print(" 的直接父类是:"+supercl.getName());
			System.out.print("\n\n");
		} catch (ClassNotFoundException e) {
		System.out.println("对不起，该类不在Java类库。\n\n");
			e.printStackTrace();
		}
		System.out.println("退出系统了！");
		System.exit(0);
		
	}
}
