package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="H_RESULT_FOURTH_Q")
public class HresultFourthq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nm;
	
	private String stcd;
	
	private String pch;
	
	private String tm;
	
	private Double qs;
	
	private Double qj;
	
	private Double qsqj;
	
	private Double qpj;
	
	private Double mi;
	
	private Double mi2;
	
	private Double miqpj;
	
	private Double mi2qi;

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
	@Column(name="QS")
	public Double getQs() {
		return qs;
	}

	public void setQs(Double qs) {
		this.qs = qs;
	}
	@Column(name="QJ")
	public Double getQj() {
		return qj;
	}

	public void setQj(Double qj) {
		this.qj = qj;
	}
	@Column(name="QSQJ")
	public Double getQsqj() {
		return qsqj;
	}

	public void setQsqj(Double qsqj) {
		this.qsqj = qsqj;
	}
	@Column(name="QPJ")
	public Double getQpj() {
		return qpj;
	}

	public void setQpj(Double qpj) {
		this.qpj = qpj;
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
	@Column(name="MIQPJ")
	public Double getMiqpj() {
		return miqpj;
	}

	public void setMiqpj(Double miqpj) {
		this.miqpj = miqpj;
	}
	@Column(name="MI2QI")
	public Double getMi2qi() {
		return mi2qi;
	}

	public void setMi2qi(Double mi2qi) {
		this.mi2qi = mi2qi;
	}

}
