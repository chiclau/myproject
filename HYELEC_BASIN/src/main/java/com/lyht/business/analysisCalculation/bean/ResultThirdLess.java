package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "C_RESULT_THRID_LESS")
public class ResultThirdLess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tm;
	
	private Integer interval;
	
	private Double q;
	
	private Double qt;
	
	private String stcd;
	
	private String id;
	
	private String pch;
	
	@Column(name="TM")
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Column(name="INTERVAL")
	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	@Column(name="Q")
	public Double getQ() {
		return q;
	}

	public void setQ(Double q) {
		this.q = q;
	}

	@Column(name="QT")
	public Double getQt() {
		return qt;
	}

	public void setQt(Double qt) {
		this.qt = qt;
	}

	@Column(name="STCD")
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="PCH")
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}
	
}
