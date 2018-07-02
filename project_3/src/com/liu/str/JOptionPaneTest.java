package com.liu.str;

import javax.swing.JOptionPane;
/**
 * JOptionPane
 * @author Administrator
 *	进行键盘对话框测试。通过该例，读者可了解JOptionPane类的用法，同时对于
 * 串输入、串定义、串类型转换有所了解。
 */
public class JOptionPaneTest {
	public static void main(String[] args) {
		String name=JOptionPane.showInputDialog(" What is your name?");
		String input=JOptionPane.showInputDialog(" How old are you?");
		int age=Integer.parseInt(input);
		System.out.println("Hello,"+ name+ "  . Next year, you'll be "+(age+1)+".");
		System.exit(0);
	}
}
