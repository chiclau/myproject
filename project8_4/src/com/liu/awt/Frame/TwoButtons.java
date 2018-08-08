package com.liu.awt.Frame;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class TwoButtons {
	private Frame f;
	private Button b1;
	private Button b2;
	public static void main(String[] args) {
		TwoButtons two=new TwoButtons();
		two.go();
	}
	
	public void go() {
		f=new Frame("FlowLayout");
		f.setLayout(new FlowLayout());//设置布局管理容器为FlowLayout
		b1=new Button("press me");	//按钮上显示字符"press me"
		b2=new Button("Dont Press me");
		f.add(b1);
		f.add(b2);
		f.pack();	//紧凑排列，其作用相当于setSize(),即让窗口尽量小
					//小到刚刚能够包住b1、b2两个按钮
		f.setVisible(true);
	}
}
