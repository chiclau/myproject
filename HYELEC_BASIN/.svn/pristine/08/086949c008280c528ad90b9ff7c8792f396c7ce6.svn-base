package com.lyht.util;

import java.util.ArrayList;
import java.util.List;

public class LinearRegression {

	public static void main(String[] args) {
		List<Point> pointList = new ArrayList<Point>();
		pointList.add(new Point(0,23));
		pointList.add(new Point(1,44));
		pointList.add(new Point(2,32));
		pointList.add(new Point(3,56));
		pointList.add(new Point(4,33));
		pointList.add(new Point(5,34));
		pointList.add(new Point(6,55));
		pointList.add(new Point(7,65));
		pointList.add(new Point(8,45));
		pointList.add(new Point(9,55));
		System.out.println(estimateY(pointList, pointList.size()));
	}
	/**
	 * 获取对数函数线性
	 * @param pointList
	 * @param x
	 * @return
	 */
	public static double getExponentialY(List<Point> pointList, double x){
		if(pointList==null||pointList.size()<1){
			return 0;
		}
		List<Point> npointList = new ArrayList<Point>();
		for(int i=0;i<pointList.size();i++){
			npointList.add(new Point(pointList.get(i).getX(),Math.log(pointList.get(i).getY())));
		}
		double ey=estimateY(npointList,x);
		return ey;
	}
	/**
	 * 获取对数函数线性
	 * @param pointList
	 * @param y
	 * @return
	 */
	public static double getExponentialX(List<Point> pointList, double y){
		if(pointList==null||pointList.size()<1){
			return 0;
		}
		List<Point> npointList = new ArrayList<Point>();
		for(int i=0;i<pointList.size();i++){
			npointList.add(new Point(pointList.get(i).getX(),Math.log(pointList.get(i).getY())));
		}
		double ex=estimateX(npointList,y);
		return ex;
	}
	/**
	 * 预测
	 * 
	 * @param pointList
	 * @param y
	 * @return
	 */
	public static double estimateY(List<Point> pointList, double x) {
		if(pointList==null||pointList.size()<1){
			return 0;
		}
		double a = getXc(pointList);
		double b = getC(pointList, a);
		return Math.pow(Math.E, b)*Math.pow(Math.E, a*x);
	}
	/**
	 * 预测
	 * 
	 * @param pointList
	 * @param x
	 * @return
	 */
	public static double estimateX(List<Point> pointList, double y) {
		if(pointList==null||pointList.size()<1){
			return 0;
		}
		double a = getXc(pointList);
		double b = getC(pointList, a);
		return (a!=0?(Math.log(y)-b)/a:0);
	}
	/**
	 * 计算 x 的系数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static double getXc(List<Point> pointList) {
		int n = pointList.size();
		double ps=pSum(pointList);
		double sx=sumX(pointList);
		double sy=sumY(pointList);
		double fz=(n * ps - sx * sy);
		double ss=sqSumX(pointList);
		double fm=(n * ss - Math.pow(sx, 2));
		return (fm!=0)?fz/fm:0;
	}

	/**
	 * 计算常量系数
	 * 
	 * @param x
	 * @param y
	 * @param a
	 * @return
	 */
	public static double getC(List<Point> pointList, double a) {
		int n = pointList.size();
		return sumY(pointList) / n - a * sumX(pointList) / n;
	}

	/**
	 * 计算常量系数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static double getC(List<Point> pointList) {
		int n = pointList.size();
		double a = getXc(pointList);
		return sumY(pointList) / n - a * sumX(pointList) / n;
	}

	private static double sumX(List<Point> pointList) {
		double s = 0;
		for (Point p : pointList)
			s = s + p.getX();
		return s;
	}
	private static double sumY(List<Point> pointList) {
		double s = 0;
		for (Point p : pointList)
			s = s + p.getY();
		return s;
	}

	private static double sqSumX(List<Point> pointList) {
		double s = 0;
		for (Point p : pointList)
			s = s + Math.pow(p.getX(), 2);
		return s;
	}
	private static double sqSumY(List<Point> pointList) {
		double s = 0;
		for (Point p : pointList)
			s = s + Math.pow(p.getY(), 2);
		return s;
	}
	private static double pSum(List<Point> pointList) {
		double s = 0;
		for (int i = 0; i < pointList.size(); i++)
			s = s + pointList.get(i).getX() * pointList.get(i).getY();
		return s;
	}
}
