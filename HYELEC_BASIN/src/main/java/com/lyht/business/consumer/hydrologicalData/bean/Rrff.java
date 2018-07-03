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
@Table(name = "ST_JYJLXGT_B")
@SuppressWarnings("restriction")
public class Rrff implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="用户名")
	private String userName;
	
	@Description(key="primary",value="影响雨量")
	private Float pa;
	
	@Description(key="primary",value="雨量")
	private Float p;
	
	@Description(key="primary",value="径流")
	private Float r;

	@Id
	@Column(name = "STCD")
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Id
	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "Pa")
	public Float getPa() {
		return pa;
	}

	public void setPa(Float pa) {
		this.pa = pa;
	}
	@Column(name = "P")
	public Float getP() {
		return p;
	}

	public void setP(Float p) {
		this.p = p;
	}
	@Column(name = "R")
	public Float getR() {
		return r;
	}

	public void setR(Float r) {
		this.r = r;
	}

	public Rrff(String stcd, String userName, Float pa, Float p, Float r) {
		super();
		this.stcd = stcd;
		this.userName = userName;
		this.pa = pa;
		this.p = p;
		this.r = r;
	}

	public Rrff() {
	}

	@Override
	public String toString() {
		return "Rrff [stcd=" + stcd + ", userName=" + userName + ", pa=" + pa + ", p=" + p + ", r=" + r + "]";
	}
	
}
