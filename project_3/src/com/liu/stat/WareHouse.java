package com.liu.stat;
/**
 * static关键字
 * @author Administrator
 *
 */
public class WareHouse {
	public static void main(String[] args) {
		Light.writeCount();
		Light aLight=new Light();
		System.out.println("Value of counter : " +Light.counter);
		Light bLight=new Light();
		bLight.writeCount();
		Light cLight=new Light();
		System.out.println("value of counter :" +cLight.counter);
	}
}
