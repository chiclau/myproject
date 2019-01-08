package com.lyht.util;

import java.util.List;

public class LineDifference {

	public static double getX(List<Line> lineList,double pa,double y){
		if(lineList==null || lineList.size()<1){
			return 0;
		}
		
		int len = lineList.size();
		Line minPaLine = lineList.get(0);
		Line maxPaLine = lineList.get(len-1);
		
		if(minPaLine!=null && pa<minPaLine.getPa()){
			//pa小于最小pa值线
			if(lineList.size()==1){
				//只有一根线时
				double x = onLineGetX(minPaLine.getPointList(),y);
				return (minPaLine.getPa()!=0)?(pa*x)/minPaLine.getPa():0;
			}else{
				return lessOutLine(minPaLine,lineList.get(1),y,pa);
			}
		}else if(maxPaLine!=null && maxPaLine.getPa()<pa){
			//pa大于最大Pa值线
			if(lineList.size()==1){
				double x = onLineGetX(maxPaLine.getPointList(),y);
				return (maxPaLine.getPa()!=0)?(pa*x)/maxPaLine
						.getPa():0;
			}else{
				return moreOutLine(lineList.get(len-2),maxPaLine,y,pa);
			}
		}else{
			for(int i=0;i<lineList.size();i++){
				Line line = lineList.get(i);
				Line nextLine = (i+1)<lineList.size()?lineList.get(i+1):null;
				if(line!=null && line.getPa()<=pa){
					if(line.getPa()==pa){
						return onLineGetX(line.getPointList(),y);
					}else{
						if(nextLine!=null && pa<=nextLine.getPa()){
							if(nextLine.getPa()==pa){
								return onLineGetX(nextLine.getPointList(),y);
							}else{
								return onBetween(line,nextLine,y,pa);
							}
						}
					}
					
				}
			}
			return 0;
		}
	}
	/**
	 * 两pa之间的线
	 * @param minPaPointList
	 * @param maxPaPointList
	 * @param y
	 * @return
	 */
	public static double onBetween(Line minLine,Line maxLine,double y,double pa){
		double x1=onLineGetX(minLine.getPointList(),y);
		double x2=onLineGetX(maxLine.getPointList(),y);
		double cp=pa-minLine.getPa();
		double r=cp!=0?(maxLine.getPa()-pa)/cp:0;
		double x=(r+1)!=0?(x2+r*x1)/(r+1):0;
		return x;
	}
	/**
	 * 小于最小pa时的延长计算
	 * @param minLine
	 * @param maxLine
	 * @param y
	 * @param pa
	 * @return
	 */
	public static double lessOutLine(Line minLine,Line maxLine,double y,double pa){
		double x1=onLineGetX(minLine.getPointList(),y);
		double x2=onLineGetX(maxLine.getPointList(),y);
		double cp=maxLine.getPa()-minLine.getPa();
		double r=cp!=0?(minLine.getPa()-pa)/cp:0;
		double x=x1-r*(x2-x1);
		return x;
	}
	/**
	 * 大于最大pa时的延长计算
	 * @param minLine
	 * @param maxLine
	 * @param y
	 * @param pa
	 * @return
	 */
	public static double moreOutLine(Line minLine,Line maxLine,double y,double pa){
		double x1=onLineGetX(minLine.getPointList(),y);
		double x2=onLineGetX(maxLine.getPointList(),y);
		double cp=maxLine.getPa()-minLine.getPa();
		double r=cp!=0?(pa-maxLine.getPa())/cp:0;
		double x=x2+r*(x2-x1);
		return x;
	}
	/**
	 * 线数组中存在Pa，已知y求x
	 * @param pointList
	 * @param y
	 * @return
	 */
	public static double onLineGetX(List<Point> pointList,double y){
		if(pointList==null || pointList.size()<2){
			return 0;
		}
		int len = pointList.size();
		Point minPoint = pointList.get(0);
		Point maxPoint = pointList.get(len-1);
		if(minPoint!=null && y<minPoint.getY()){
			//y小于第一个点的Y
			Point p2=pointList.get(1);
			double yc=p2.getY()-minPoint.getY();
			double xc=p2.getX()-minPoint.getX();
			double r = xc!=0?yc/xc:0;
			double x = r!=0?minPoint.getX()-(minPoint.getY()-y)/r:0;
			return x;
		}else if(maxPoint!=null && y>maxPoint.getY()){
			//y大于最后一个点的Y
			Point p1=pointList.get(len-2);
			double yc=maxPoint.getY()-p1.getY();
			double xc=maxPoint.getX()-p1.getX();
			double r = xc!=0?yc/xc:0;
			double x = r!=0?(y-maxPoint.getY())/r+maxPoint.getX():0;
			return x;
		}else{
			for(int i=0;i<pointList.size();i++){
				Point p1 = pointList.get(i);
				Point p2 = i<pointList.size()-1?pointList.get(i+1):null;
				if(p1!=null && p1.getY()<=y){
					if(p1.getY()==y){
						return p1.getX();
					}else{
						if(p2!=null && p2.getY()>=y){
							if(p2.getY()==y){
								return p2.getX();
							}else{
								double xc=p2.getX()-p1.getX();
								double yc=p2.getY()-p1.getY();
								double r = xc!=0?yc/xc:0;
								double qyc=y-p1.getY();
								double x=r!=0?(qyc/r)+p1.getX():0;
								return x;
							}
						}
					}
					
				}
			}
			return 0;
		}
	}
}
