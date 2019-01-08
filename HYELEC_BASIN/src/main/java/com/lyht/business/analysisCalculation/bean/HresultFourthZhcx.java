package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "H_RESULT_FOURTH_ZHCX")
public class HresultFourthZhcx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nm;
	
	private String stcd;
	
	private String pch;
	
	private Double n;
	
	private Double k;
	
	private Double m1;
	
	private Double a13am;
	
	private Double m1c;
	
	private Double kj;
	
	private Double qmj;
	
	private Double qms;
	
	private Double wc;

	@Id
	@Column(name = "NM",length=50,unique = true,nullable = false)
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
	@Column(name="PCH")
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}
	@Column(name="N")
	public Double getN() {
		return n;
	}

	public void setN(Double n) {
		this.n = n;
	}
	@Column(name="K")
	public Double getK() {
		return k;
	}

	public void setK(Double k) {
		this.k = k;
	}
	@Column(name="M1")
	public Double getM1() {
		return m1;
	}

	public void setM1(Double m1) {
		this.m1 = m1;
	}
	@Column(name="A13AM")
	public Double getA13am() {
		return a13am;
	}

	public void setA13am(Double a13am) {
		this.a13am = a13am;
	}
	@Column(name="M1C")
	public Double getM1c() {
		return m1c;
	}

	public void setM1c(Double m1c) {
		this.m1c = m1c;
	}
	@Column(name="QMJ")
	public Double getQmj() {
		return qmj;
	}

	public void setQmj(Double qmj) {
		this.qmj = qmj;
	}
	@Column(name="QMS")
	public Double getQms() {
		return qms;
	}

	public void setQms(Double qms) {
		this.qms = qms;
	}

	@Column(name="KJ")
	public Double getKj() {
		return kj;
	}

	public void setKj(Double kj) {
		this.kj = kj;
	}
	@Column(name="WC")
	public Double getWc() {
		return wc;
	}

	public void setWc(Double wc) {
		this.wc = wc;
	}

}
