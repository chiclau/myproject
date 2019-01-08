package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "ST_PPTN_DAY_R")
public class StPptnDayR implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="编码")
	private String nm;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="display",value="开始时间")
	private String tm;
	
	@Description(key="display",value="日降水量")
	private Double dyp;

	@Id
	@Column(name="NM")
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	@Column(name = "STCD",length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Column(name = "TM")
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Column(name = "DYP")
	public Double getDyp() {
		return dyp;
	}

	public void setDyp(Double dyp) {
		this.dyp = dyp;
	}

}
