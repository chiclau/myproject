package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "C_RESULT_SIX_PPA")
public class ResultSixPpa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nm;
	
	private String stcd;
	
	private String pch;
	
	private Double r;
	
	private Double pa;
	
	private Double ppa;

	@Id
	@Column(name="NM",length=50,unique = true,nullable = false)
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	@Column(name="STCD")
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Column(name="PCH",length=50)
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	@Column(name="R")
	public Double getR() {
		return r;
	}

	public void setR(Double r) {
		this.r = r;
	}
	@Column(name="PPA")
	public Double getPpa() {
		return ppa;
	}

	public void setPpa(Double ppa) {
		this.ppa = ppa;
	}
	
	@Column(name="PA")
	public Double getPa() {
		return pa;
	}

	public void setPa(Double pa) {
		this.pa = pa;
	}

}
