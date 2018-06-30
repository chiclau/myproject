package com.liu.main.excecise;

public class E1 {
	void shortCircuit2(int val) {
		if(val!=0&&(1/val<Double.MAX_VALUE))System.out.println("val的倒数为:"+1/val);
		else {
			System.out.println("val的倒数为无穷大");
		}
	}
	
	public static void main(String[] args) {
		E1 eqE1=new E1();
		eqE1.shortCircuit2(234);
	}
}
