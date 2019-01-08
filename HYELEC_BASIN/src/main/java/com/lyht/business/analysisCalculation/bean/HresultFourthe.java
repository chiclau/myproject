package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="H_RESULT_FOURTH_E")
public class HresultFourthe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nm;
	
	private String pch;
	
	private String stcd;
	
	private String tm;
	
	private Double ii;
	
	private Double mi;
	
	private Double mi2;
	
	private Double iimi;
	
	private Double iimi2;

	@Id
	@Column(name = "NM",length=50,unique = true,nullable = false)
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
	@Column(name="PCH")
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	@Column(name="STCD")
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Column(name="TM")
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Column(name="II")
	public Double getIi() {
		return ii;
	}

	public void setIi(Double ii) {
		this.ii = ii;
	}
	@Column(name="MI")
	public Double getMi() {
		return mi;
	}

	public void setMi(Double mi) {
		this.mi = mi;
	}
	@Column(name="MI2")
	public Double getMi2() {
		return mi2;
	}

	public void setMi2(Double mi2) {
		this.mi2 = mi2;
	}
	@Column(name="IIMI")
	public Double getIimi() {
		return iimi;
	}

	public void setIimi(Double iimi) {
		this.iimi = iimi;
	}
	@Column(name="IIMI2")
	public Double getIimi2() {
		return iimi2;
	}

	public void setIimi2(Double iimi2) {
		this.iimi2 = iimi2;
	}

}
