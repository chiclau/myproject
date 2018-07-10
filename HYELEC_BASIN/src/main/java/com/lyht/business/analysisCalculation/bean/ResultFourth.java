package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;
/**
 * 产流计算第四步保存表
 * @author 刘魁
 *@时间 2018/6/26
 */
@Entity
@Table(name = "C_RESULT_FOURTH")
public class ResultFourth implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="时间")
	private String tm;
	
	@Description(key="primary",value="时差")
	private Integer interval;
	
	@Description(key="primary",value="流量地下")
	private double qdx;
	
	@Description(key="primary",value="Q*T")
	private double qt;

	@Description(key="primary",value="主键")
	private String id;

	@Description(key="primary",value="测站编码")
	private String stcd;

	
	@Override
	public String toString() {
		return "ResultFourth [tm=" + tm + ", interval=" + interval + ", qdx=" + qdx + ", qt=" + qt + ", id=" + id + "]";
	}
	//
	@Column(name = "TM",length=50 )
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}
	@Column(name = "INTERVAL",length=50 )
	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	@Column(name = "QDX",length=50 )
	public double getQdx() {
		return qdx;
	}

	public void setQdx(double qdx) {
		this.qdx = qdx;
	}
	@Column(name = "QT",length=50 )
	public double getQt() {
		return qt;
	}

	public void setQt(double qt) {
		this.qt = qt;
	}
	
	
	
	@Column(name = "STCD",length=50 )
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Id
	@Column(name = "ID",length=50 )
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ResultFourth() {
		this.id="";
		this.interval=0;
		this.qdx=0;
		this.qt=0;
		this.tm="";
		this.stcd="";
	}
	
	
	public ResultFourth(String id,String tm,Integer interval,double qdx,double qt,String stcd) {
		this.id=id;
		this.tm=tm;
		this.interval=interval;
		this.qdx=qdx;
		this.qt=qt;
		this.stcd=stcd;
	}
}
