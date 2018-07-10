package com.lyht.business.consumer.hydrologicalData.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "ST_RSVR_R")
@SuppressWarnings("restriction")
public class Rsvr implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="时间")
	private String tm;
	
	@Description(key="primary",value="库水位")
	private double rz;
	
	@Description(key="primary",value="入库流量")
	private double inq;
	
	@Description(key="primary",value="蓄水量")
	private double w;
	
	@Description(key="primary",value="出库流量")
	private double otq;
	
	@Description(key="primary",value="库水特征码")
	private String rwchrcd;
	
	@Description(key="primary",value="库水水势")
	private String rwptn;
	
	@Description(key="primary",value="入流时段长")
	private double inqdr;
	
	@Description(key="primary",value="测流方法")
	private String msqmt;
	
	public Rsvr() {
		this.stcd="";
		this.tm="";
		this.rz=0;
		this.inq=0;
		this.w=0;
		this.otq=0;
		this.rwchrcd="";
		this.rwptn="";
		this.inqdr=0;
		this.msqmt="";
	}

	public Rsvr(
			String stcd, 
			String tm, 
			double rz, 
			double inq,
			double w,
			double otq,
			String rwchrcd,
			String rwptn,
			double inqdr,
			String msqmt
			) {
		this.stcd=stcd;
		this.tm=tm;
		this.rz=rz;
		this.inq=inq;
		this.w=w;
		this.otq=otq;
		this.rwchrcd=rwchrcd;
		this.rwptn=rwptn;
		this.inqdr=inqdr;
		this.msqmt=msqmt;
	}





	@Override
	public String toString() {
		return "Rsvr [stcd=" + stcd + ", tm=" + tm + ", rz=" + rz + ", inq=" + inq + ", w=" + w + ", otq=" + otq
				+ ", rwchrcd=" + rwchrcd + ", rwptn=" + rwptn + ", inqdr=" + inqdr + ", msqmt=" + msqmt + "]";
	}

	@Id
	@Column(name = "STCD", length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Column(name = "TM" )
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Column(name = "RZ", length=7 )
	public double getRz() {
		return rz;
	}

	public void setRz(double rz) {
		this.rz = rz;
	}
	
	@Column(name = "INQ", length=9 )
	public double getInq() {
		return inq;
	}

	public void setInq(double inq) {
		this.inq = inq;
	}

	@Column(name = "W", length=9 )
	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
	@Column(name = "OTQ", length=9 )
	public double getOtq() {
		return otq;
	}

	public void setOtq(double otq) {
		this.otq = otq;
	}
	
	@Column(name = "RWCHRCD", length=1 )
	public String getRwchrcd() {
		return rwchrcd;
	}

	public void setRwchrcd(String rwchrcd) {
		this.rwchrcd = rwchrcd;
	}
	@Column(name = "RWPTN", length=1 )
	public String getRwptn() {
		return rwptn;
	}

	public void setRwptn(String rwptn) {
		this.rwptn = rwptn;
	}
	
	@Column(name = "INQDR", length=5 )
	public double getInqdr() {
		return inqdr;
	}

	public void setInqdr(double inqdr) {
		this.inqdr = inqdr;
	}
	
	@Column(name = "MSQMT", length=1 )
	public String getMsqmt() {
		return msqmt;
	}

	public void setMsqmt(String msqmt) {
		this.msqmt = msqmt;
	}
	
	
}
