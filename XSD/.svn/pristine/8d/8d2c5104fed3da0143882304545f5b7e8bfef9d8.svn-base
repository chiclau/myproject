package com.lyht.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Least square method class.
 */
public class LeastSquareMethod {
	/**
	 * Used to calculate value by given x.
	 * 
	 * @param x
	 *            x
	 * @return y
	 */
	public static double fit(double[] coefficient,double x) {
		if (coefficient == null) {
			return 0;
		}
		double sum = 0;
		for (int i = 0; i < coefficient.length; i++) {
			sum += Math.pow(x, i) * coefficient[i];
		}
		return sum;
	}
 
	/**
	 * Use Newton's method to solve equation.
	 * 
	 * @param y
	 *            y
	 * @return x
	 */
	public static double solve(double[] coefficient,double y) {
		return solve(coefficient,y, 1.0d);
	}
 
	/**
	 * Use Newton's method to solve equation.
	 * 
	 * @param y
	 *            y
	 * @param startX
	 *            The start point of x
	 * @return x
	 */
	public static double solve(double[] coefficient,double y, double startX) {
		final double EPS = 0.0000001d;
		if (coefficient == null) {
			return 0;
		}
		double x1 = 0.0d;
		double x2 = startX;
		do {
			x1 = x2;
			x2 = x1 - (fit(coefficient,x1) - y) / calcReciprocal(coefficient,x1);
		} while (Math.abs((x1 - x2)) > EPS);
		return x2;
	}
 
	/*
	 * Calculate the reciprocal of x.
	 * 
	 * @param x x
	 * 
	 * @return the reciprocal of x
	 */
	private static double calcReciprocal(double[] coefficient,double x) {
		if (coefficient == null) {
			return 0;
		}
		double sum = 0;
		for (int i = 1; i < coefficient.length; i++) {
			sum += i * Math.pow(x, i - 1) * coefficient[i];
		}
		return sum;
	}
 
	/*
	 * This method is used to calculate each elements of augmented matrix.
	 */
	private static double[] compute(List<Point> pointList,int n,double[] weight) {
		double[] s = new double[(n - 1) * 2 + 1];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < pointList.size(); j++) {
				s[i] += Math.pow(pointList.get(j).getX(), i) * weight[j];
			}
		}
		double[] b = new double[n];
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < pointList.size(); j++) {
				b[i] += Math.pow(pointList.get(j).getX(), i) * pointList.get(j).getY() * weight[j];
			}
		}
		double[][] a = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = s[i + j];
			}
		}
 
		// Now we need to calculate each coefficients of augmented matrix
		return calcLinearEquation(a, b);
	}
 
	/*
	 * Calculate linear equation.
	 * 
	 * The matrix equation is like this: Ax=B
	 * 
	 * @param a two-dimensional array
	 * 
	 * @param b one-dimensional array
	 * 
	 * @return x, one-dimensional array
	 */
	private static double[] calcLinearEquation(double[][] a, double[] b) {
		if (a == null || b == null || a.length == 0 || a.length != b.length) {
			return null;
		}
		for (double[] x : a) {
			if (x == null || x.length != a.length)
				return null;
		}
 
		int len = a.length - 1;
		double[] result = new double[a.length];
 
		if (len == 0) {
			result[0] = b[0] / a[0][0];
			return result;
		}
 
		double[][] aa = new double[len][len];
		double[] bb = new double[len];
		int posx = -1, posy = -1;
		for (int i = 0; i <= len; i++) {
			for (int j = 0; j <= len; j++)
				if (a[i][j] != 0.0d) {
					posy = j;
					break;
				}
			if (posy != -1) {
				posx = i;
				break;
			}
		}
		if (posx == -1) {
			return null;
		}
 
		int count = 0;
		for (int i = 0; i <= len; i++) {
			if (i == posx) {
				continue;
			}
			bb[count] = b[i] * a[posx][posy] - b[posx] * a[i][posy];
			int count2 = 0;
			for (int j = 0; j <= len; j++) {
				if (j == posy) {
					continue;
				}
				aa[count][count2] = a[i][j] * a[posx][posy] - a[posx][j]
						* a[i][posy];
				count2++;
			}
			count++;
		}
 
		// Calculate sub linear equation
		double[] result2 = calcLinearEquation(aa, bb);
 
		// After sub linear calculation, calculate the current coefficient
		double sum = b[posx];
		count = 0;
		for (int i = 0; i <= len; i++) {
			if (i == posy) {
				continue;
			}
			sum -= a[posx][i] * result2[count];
			result[i] = result2[count];
			count++;
		}
		result[posy] = sum / a[posx][posy];
		return result;
	}
	public static double calcX(List<Point> pointList,int n,double y){
		if(pointList==null ||pointList.size()<1){
			return 0;
		}
		double[] weight = new double[pointList.size()];
		for(int i=0;i<pointList.size();i++){
			weight[i]=1;
		}
		double[] coefficient = compute(pointList,n,weight);
		return solve(coefficient,y);
	}
	public static double calcY(List<Point> pointList,int n,double x){
		if(pointList==null ||pointList.size()<1){
			return 0;
		}
		double[] weight = new double[pointList.size()];
		for(int i=0;i<pointList.size();i++){
			weight[i]=1;
		}
		double[] coefficient = compute(pointList,n,weight);
		return fit(coefficient,x);
	}
	public static void main(String[] args) {
		List<Point> pointList = new ArrayList<Point>();
		pointList.add(new Point(0.5, 1.75));
		pointList.add(new Point(1, 2.45));
		pointList.add(new Point(1.5, 3.81));
		pointList.add(new Point(2, 4.8));
		pointList.add(new Point(2.5, 7.0));
		pointList.add(new Point(3, 8.6));
		System.out.println(LeastSquareMethod.calcY(pointList, 3, 4));
		
		List<Point> pointList1 = new ArrayList<Point>();
		pointList1.add(new Point(0.5, 1.75));
		pointList1.add(new Point(1, 2.45));
		pointList1.add(new Point(1.5, 3.81));
		pointList1.add(new Point(2, 4.8));
		pointList1.add(new Point(2.5, 7.0));
		pointList1.add(new Point(3, 8.6));
		System.out.println(LeastSquareMethod.calcX(pointList, 2, 100));
 
	}
}
