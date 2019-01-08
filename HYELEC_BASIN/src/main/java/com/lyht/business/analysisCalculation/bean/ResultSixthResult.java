package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "C_RESULT_SIXTH_RESULT")
public class ResultSixthResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nm;
	
	private String stcd;
	
	private String pch;
	
	private Double tc;
	
	private Double rc;
	
	private Double rctc;
	
	private Double fc;
	
	private Double fcc;
	
	private Double fcs;

	@Id
	@Column(name = "NM",length=100,unique = true,nullable = false)
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
	@Column(name="STCD",nullable = false)
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Column(name="PCH",nullable = false)
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}
	@Column(name="TC")
	public Double getTc() {
		return tc;
	}

	public void setTc(Double tc) {
		this.tc = tc;
	}
	@Column(name="RC")
	public Double getRc() {
		return rc;
	}

	public void setRc(Double rc) {
		this.rc = rc;
	}
	@Column(name="RCTC")
	public Double getRctc() {
		return rctc;
	}

	public void setRctc(Double rctc) {
		this.rctc = rctc;
	}
	@Column(name="FC")
	public Double getFc() {
		return fc;
	}

	public void setFc(Double fc) {
		this.fc = fc;
	}
	@Column(name="FCC")
	public Double getFcc() {
		return fcc;
	}

	public void setFcc(Double fcc) {
		this.fcc = fcc;
	}
	@Column(name="FCS")
	public Double getFcs() {
		return fcs;
	}

	public void setFcs(Double fcs) {
		this.fcs = fcs;
	}

}
