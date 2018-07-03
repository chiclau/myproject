package com.liu.array;

public class ArrayCopy {
	public static void main(String[] args) {
		int [] smallPrimes= {2,3,4,7,11,13};
		int [] luckyNumbers= {1001,1002,1003,1004,1005,1006,1007};
		System.arraycopy(smallPrimes, 2, luckyNumbers, 3, 4);
		for(int i=0;i<luckyNumbers.length;i++)
			System.out.println(i+ " entry afer copy is " +luckyNumbers[i]);
	}
}
