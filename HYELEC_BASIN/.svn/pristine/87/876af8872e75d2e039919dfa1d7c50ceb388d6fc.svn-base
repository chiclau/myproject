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
@Table(name = "ST_TSQX_B")
@SuppressWarnings("restriction")
public class Tsqx implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="站码")
	private String stcd;
	
	@Description(key="primary",value="用户名称")
	private String username;
	
	@Description(key="primary",value="洪峰")
	private float qm;
	
	@Description(key="primary",value="流量")
	private float q;
	
	@Description(key="primary",value="时段")
	private float t;
	
	public Tsqx() {
		this.stcd="";
		this.username="";
		this.qm=0;
		this.q=0;
		this.t=0;
	}

	public Tsqx( 
			String stcd, 
			String username, 
			float qm, 
			float q, 
			float t) {
		this.stcd=stcd;
		this.username=username;
		this.qm=qm;
		this.q=q;
		this.t=t;
	}

	@Override
	public String toString() {
		return "Tsqx [stcd=" + stcd + ", username=" + username + ", qm=" + qm + ", q=" + q + ", t=" + t + "]";
	}

	@Id
	@Column(name = "STCD", length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Column(name = "USERNAME" ,length=30)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Qm")
	public float getQm() {
		return qm;
	}

	public void setQm(float qm) {
		this.qm = qm;
	}
	
	@Column(name = "Q" )
	public float getQ() {
		return q;
	}

	public void setQ(float q) {
		this.q = q;
	}

	@Column(name = "T" )
	public float getT() {
		return t;
	}

	public void setT(float t) {
		this.t = t;
	}
}
