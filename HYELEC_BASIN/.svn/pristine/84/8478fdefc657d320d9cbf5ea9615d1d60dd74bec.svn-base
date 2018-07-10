package com.lyht.business.consumer.hydrologicalData.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "ST_PPTN_R")
@SuppressWarnings("restriction")
public class Pptn implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="display",value="时间")
	private String tm;
	
	@Description(key="display",value="时段降水量")
	private double drp;
	
	@Description(key="display",value="时段长")
	private double intv;
	
	@Description(key="display",value="降水历时")
	private double pdr;
	
	@Description(key="display",value="日降水量")
	private double dyp;
	
	@Description(key="display",value="天气状况")
	private String wth;
	
	public Pptn() {
        this.stcd = "";
        this.tm = "";
        this.wth = "";
    }
	
	@Id
	@Column(name = "STCD",length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	
	@Id
	@Column(name = "TM")
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Column(name = "DRP")
	public double getDrp() {
		return drp;
	}

	public void setDrp(double drp) {
		this.drp = drp;
	}

	@Column(name = "INTV")
	public double getIntv() {
		return intv;
	}

	public void setIntv(double intv) {
		this.intv = intv;
	}

	@Column(name = "PDR")
	public double getPdr() {
		return pdr;
	}

	public void setPdr(double pdr) {
		this.pdr = pdr;
	}

	@Column(name = "DYP")
	public double getDyp() {
		return dyp;
	}

	public void setDyp(double dyp) {
		this.dyp = dyp;
	}

	@Column(name = "WTH",length=1)
	public String getWth() {
		return wth;
	}

	public void setWth(String wth) {
		this.wth = wth;
	}
	
}
