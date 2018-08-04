package com.liu.awt.Frame;

import java.awt.Color;
import java.awt.Frame;

public class FirstFrame extends Frame{
	public FirstFrame(String string) {
		super(string);
	}

	public static void main(String[] args) {
		FirstFrame fr=new FirstFrame("First contianer!");
		fr.setSize(1240, 1240);
		fr.setBackground(Color.gray);
		fr.setVisible(true);
	}

}
