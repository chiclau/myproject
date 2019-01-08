package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "C_RESULT_SECOND_QL")
public class ResultSecondQl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nm;
	
	private String stcd;
	
	private String pch;
	
	private String tm;
	
	private Double yml;
	
	private Double q;
	
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
	@Column(name="TM")
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}
	@Column(name="YML")
	public Double getYml() {
		return yml;
	}

	public void setYml(Double yml) {
		this.yml = yml;
	}
	@Column(name="Q")
	public Double getQ() {
		return q;
	}

	public void setQ(Double q) {
		this.q = q;
	}

}
