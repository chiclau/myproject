package com.liu.inn;

import javax.swing.JOptionPane;
/**
 * 内部类
 * @author Administrator
 * 程序的功能是每秒钟响一声蜂鸣器，同时显示发声的时间。程序使用了匿名内部类对象:listener
 * 以及Timer监听器接口ActionListener,实现Timer的事件处理。
 */
public class AnonymousInnerClassTest {
	public static void main(String[] args) {
		TalkingClock clock=new TalkingClock();
		clock.start(1000, true);
		JOptionPane.showMessageDialog(null, "退数吗？");
		System.exit(0);
	}
	
	
	

}
