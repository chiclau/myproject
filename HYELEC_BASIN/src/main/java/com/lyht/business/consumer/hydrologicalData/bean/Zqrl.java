package com.lyht.business.consumer.hydrologicalData.bean;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "ST_ZQRL_B")
@SuppressWarnings("restriction")
public class Zqrl implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="曲线名称")
	private String lnnm;
	
	@Description(key="primary",value="曲线序号")
	private float ptno;
	
	@Description(key="primary",value="水位")
	private float z;
	
	@Description(key="primary",value="流量")
	private float q;
	
	@Description(key="primary",value="备注")
	private String comments;
	
	@Description(key="primary",value="修改时间戳")
	private String moditime;
	
	public Zqrl() {
		this.stcd="";
		this.lnnm="";
		this.ptno=0;
		this.z=0;
		this.q=0;
		this.comments="";
		this.moditime="";
	}

	public Zqrl(
			String stcd, 
			String lnnm, 
			float ptno, 
			float z, 
			float q, 
			String comments, 
			String moditime) {
		this.stcd = stcd;
		this.lnnm = lnnm;
		this.ptno = ptno;
		this.z = z;
		this.q = q;
		this.comments = comments;
		this.moditime = moditime;
	}

	
	@Override
	public String toString() {
		return "Zqrl [stcd=" + stcd + ", lnnm=" + lnnm + ", ptno=" + ptno + ", z=" + z + ", q=" + q + ", comments="
				+ comments + ", moditime=" + moditime + "]";
	}

	
	@Id
	@Column(name = "STCD", length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Column(name = "LNNM", length=20 )
	public String getLnnm() {
		return lnnm;
	}

	public void setLnnm(String lnnm) {
		this.lnnm = lnnm;
	}

	@Column(name = "PTNO", length=4 )
	public float getPtno() {
		return ptno;
	}

	public void setPtno(float ptno) {
		this.ptno = ptno;
	}
	
	@Column(name = "Z", length=7 )
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	@Column(name = "Q", length=9 )
	public float getQ() {
		return q;
	}

	public void setQ(float q) {
		this.q = q;
	}
	
	@Column(name = "COMMENTS", length=200 )
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(name = "MODITIME")
	public String getModitime() {
		return moditime;
	}

	public void setModitime(String moditime) {
		this.moditime = moditime;
	}
	
}
