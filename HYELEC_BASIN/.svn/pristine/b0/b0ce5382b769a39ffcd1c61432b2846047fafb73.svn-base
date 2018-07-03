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
@Table(name = "ST_ZVARL_B")
@SuppressWarnings("restriction")
public class Zvarl implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="施测时间")
	private String mstm;
	
	@Description(key="primary",value="曲线序号")
	private double ptno;
	
	@Description(key="primary",value="库位")
	private double rz;
	
	@Description(key="primary",value="蓄水量")
	private double w;
	
	@Description(key="primary",value="水面面积")
	private double wsfa;
	
	@Description(key="primary",value="修改时间戳")
	private String moditime;
	
	public Zvarl() {
		this.stcd="";
		this.mstm="";
		this.ptno=0;
		this.rz=0;
		this.w=0;
		this.wsfa=0;
		this.moditime="";
	}

	public Zvarl( 
			String stcd, 
			String mstm, 
			double ptno, 
			double rz, 
			double w, 
			double wsfa, 
			String moditime) {
		this.stcd=stcd;
		this.mstm=mstm;
		this.ptno=ptno;
		this.rz=rz;
		this.w=w;
		this.wsfa=wsfa;
		this.moditime=moditime;
	}


	@Override
	public String toString() {
		return "Zvarl [stcd=" + stcd + ", mstm=" + mstm + ", ptno=" + ptno + ", rz=" + rz + ", w=" + w + ", wsfa="
				+ wsfa + ", moditime=" + moditime + "]";
	}

	@Id
	@Column(name = "STCD", length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Column(name = "MSTM" )
	public String getMstm() {
		return mstm;
	}

	public void setMstm(String mstm) {
		this.mstm = mstm;
	}

	@Column(name = "PTNO", length=4 )
	public double getPtno() {
		return ptno;
	}

	public void setPtno(double ptno) {
		this.ptno = ptno;
	}
	
	@Column(name = "RZ", length=7 )
	public double getRz() {
		return rz;
	}

	public void setRz(double rz) {
		this.rz = rz;
	}

	@Column(name = "W", length=9 )
	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}
	
	@Column(name = "WSFA", length=8 )
	public double getWsfa() {
		return wsfa;
	}

	public void setWsfa(double wsfa) {
		this.wsfa = wsfa;
	}
	
	@Column(name = "MODITIME")
	public String getModitime() {
		return moditime;
	}

	public void setModitime(String moditime) {
		this.moditime = moditime;
	}
	
}
