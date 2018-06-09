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
@Table(name = "ST_FSDR_B")
@SuppressWarnings("restriction")
public class Fsdr implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="上游站码")
	private String upstcd;
	
	@Description(key="primary",value="下游站码")
	private String dwstcd;
	
	@Description(key="primary",value="河段长")
	private double rchlen;
	
	@Description(key="primary",value="安全泄事")
	private double sftq;
	
	@Description(key="primary",value="流量量级")
	private double qmgn;
	
	@Description(key="primary",value="最小传播时间")
	private double mntrtm;
	
	@Description(key="primary",value="最大传播时间")
	private double mxtrtm;
	
	@Description(key="primary",value="平均传播时间")
	private double avtrtm;
	
	@Description(key="primary",value="修改时间戳")
	private String moditime;
	
	public Fsdr() {
		this.upstcd="";
		this.dwstcd="";
		this.rchlen=0;
		this.sftq=0;
		this.qmgn=0;
		this.mntrtm=0;
		this.mxtrtm=0;
		this.avtrtm=0;
		this.moditime="";
	}

	public Fsdr(
		String upstcd,
		String dwstcd,
		double rchlen,
		double sftq,
		double qmgn,
		double mntrtm,
		double mxtrtm,
		double avtrtm,
		String moditime) {
		this.upstcd=upstcd;
		this.dwstcd=dwstcd;
		this.rchlen=rchlen;
		this.sftq=sftq;
		this.qmgn=qmgn;
		this.mntrtm=mntrtm;
		this.mxtrtm=mxtrtm;
		this.avtrtm=avtrtm;
		this.moditime=moditime;
	}

	

	@Override
	public String toString() {
		return "Fsdr [upstcd=" + upstcd + ", dwstcd=" + dwstcd + ", rchlen=" + rchlen + ", sftq=" + sftq + ", qmgn="
				+ qmgn + ", mntrtm=" + mntrtm + ", mxtrtm=" + mxtrtm + ", avtrtm=" + avtrtm + ", moditime=" + moditime
				+ "]";
	}

	@Id
	@Column(name = "UPSTCD", length=8 )
	public String getUpstcd() {
		return upstcd;
	}

	public void setUpstcd(String upstcd) {
		this.upstcd = upstcd;
	}

	@Column(name = "DWSTCD", length=8 )
	public String getDwstcd() {
		return dwstcd;
	}

	public void setDwstcd(String dwstcd) {
		this.dwstcd = dwstcd;
	}

	@Column(name = "RCHLEN", length=3 )
	public double getRchlen() {
		return rchlen;
	}

	public void setRchlen(double rchlen) {
		this.rchlen = rchlen;
	}

	@Column(name = "SFTQ",length=9 )
	public double getSftq() {
		return sftq;
	}

	public void setSftq(double sftq) {
		this.sftq = sftq;
	}

	@Column(name = "QMGN", length=5 )
	public double getQmgn() {
		return qmgn;
	}

	public void setQmgn(double qmgn) {
		this.qmgn = qmgn;
	}

	@Column(name = "MNTRTM", length=5 )
	public double getMntrtm() {
		return mntrtm;
	}

	public void setMntrtm(double mntrtm) {
		this.mntrtm = mntrtm;
	}

	@Column(name = "MXTRTM", length=5 )
	public double getMxtrtm() {
		return mxtrtm;
	}

	public void setMxtrtm(double mxtrtm) {
		this.mxtrtm = mxtrtm;
	}

	@Column(name = "AVTRTM", length=5 )
	public double getAvtrtm() {
		return avtrtm;
	}

	public void setAvtrtm(double avtrtm) {
		this.avtrtm = avtrtm;
	}

	@Column(name = "MODITIME")
	public String getModitime() {
		return moditime;
	}

	public void setModitime(String moditime) {
		this.moditime = moditime;
	}	
	
}
