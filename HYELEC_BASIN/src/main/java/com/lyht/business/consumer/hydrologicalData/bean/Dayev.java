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
@Table(name = "ST_DAYEV_R")
@SuppressWarnings("restriction")
public class Dayev implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="时间")
	private String tm;
	
	@Description(key="primary",value="蒸发器类型")
	private String eptp;
	
	@Description(key="primary",value="日蒸发量")
	private double dye;
	
	public Dayev() {
		this.stcd="";
		this.tm="";
		this.eptp="";
		this.dye=0;
	}

	public Dayev(
			String stcd, 
			String tm, 
			String eptp, 
			double dye
			) {
		this.stcd=stcd;
		this.tm=tm;
		this.eptp=eptp;
		this.dye=dye;
	}


	

	@Override
	public String toString() {
		return "Dayev [stcd=" + stcd + ", tm=" + tm + ", eptp=" + eptp + ", dye=" + dye + "]";
	}

	@Id
	@Column(name = "STCD", length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Id
	@Column(name = "TM" )
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Column(name = "EPTP", length=1 )
	public String getEptp() {
		return eptp;
	}

	public void setEptp(String eptp) {
		this.eptp = eptp;
	}
	
	@Column(name = "DYE", length=7 )
	public double getDye() {
		return dye;
	}

	public void setDye(double dye) {
		this.dye = dye;
	}
	
}
