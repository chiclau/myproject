package com.liu.awt.Frame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class PanelInFrame extends Frame {
	public PanelInFrame(String string) {
		super(string);
	}
	public static void main(String[] args) {
		PanelInFrame fr=new PanelInFrame("Frame with Panel");
		Panel pan=new Panel();
		fr.setSize(500, 500);
		fr.setBackground(Color.blue);
		fr.setLayout(null);
		pan.setSize(100,100);
		pan.setBackground(Color.green);
		fr.add(pan);
		fr.setVisible(true);
	}
	
}
