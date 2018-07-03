package com.liu.inn;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

public class TalkingClock {
	public void start(int interval,final boolean beep) {
		ActionListener listener=new ActionListener() {//创建匿名内部类对象
			
			@Override
			public void actionPerformed(ActionEvent event) {//实现监听器接口
					Date now=new Date();
					System.out.println("蜂鸣器发声的时间是："+ now);
					if(beep) Toolkit.getDefaultToolkit().beep();
			}
		};
		Timer t=new Timer(interval, listener);//匿名内部类对象作为Timer参数
			t.start();
	}
}
