package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;
import com.sun.tools.doclets.internal.toolkit.resources.doclets;

/**
 *作者： 刘魁
 *脚本日期:2018年6月2日 18:19:11
 *说明:  产流计算结果第三步   表
*/
@Entity
@Table(name = "C_RESULT_THRID")
public class ResultThrid  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="时间")
	private String tm;
	
	@Description(key="primary",value="时差")
	private Integer interval;
	
	@Description(key="primary",value="流量")
	private double q;
	
	@Description(key="primary",value="Q*T")
	private double qt;
	
	@Description(key="primary",value="流量合计")
	private double sumQ;

	@Description(key="primary",value="R实(mm)")
	private double  r;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="主键")
	private String id;
	@Description(key="primary",value="Q*T合计")
	private double sumQt;
	
	
	
	@Column(name = "SUMQT",length=50 )
	public double getSumQt() {
		return sumQt;
	}

	public void setSumQt(double sumQt) {
		this.sumQt = sumQt;
	}

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
	@Column(name = "Q",length=50 )
	public double getQ() {
		return q;
	}

	public void setQ(double q) {
		this.q = q;
	}
	@Column(name = "QT",length=50 )
	public double getQt() {
		return qt;
	}

	public void setQt(double qt) {
		this.qt = qt;
	}
	@Column(name = "SUMQ",length=50 )
	public double getSumQ() {
		return sumQ;
	}

	public void setSumQ(double sumQ) {
		this.sumQ = sumQ;
	}
	@Column(name = "R",length=50 )
	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
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

	
	
	@Override
	public String toString() {
		return "ResultThrid [tm=" + tm + ", interval=" + interval + ", q=" + q + ", qt=" + qt + ", sumQ=" + sumQ
				+ ", r=" + r + ", stcd=" + stcd + ", id=" + id + ", sumQt=" + sumQt + "]";
	}

	public ResultThrid() {
		this.interval=0;
		this.q=0;
		this.tm="";
		this.qt=0;
		this.sumQ=0;
		this.stcd="";
		this.r=0;
		this.id="";
		this.sumQt=0;
	}
	
	public ResultThrid(String tm,double q,double qt,double sumQ,double r,Integer interval,String id
			,String stcd,double sumQt) {
		this.tm=tm;
		this.q=q;
		this.qt=qt;
		this.r=r;
		this.sumQ=sumQ;
		this.stcd=stcd;
		this.interval=interval;
		this.sumQt=sumQt;
	}
	
}
