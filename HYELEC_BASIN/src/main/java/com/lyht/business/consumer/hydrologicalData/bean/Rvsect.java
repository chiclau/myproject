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
@Table(name = "ST_RVSECT_B")
@SuppressWarnings("restriction")
public class Rvsect implements Serializable{
private static final long serialVersionUID = 1L;
	
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="施测时间")
	private String mstm;
	
	@Description(key="primary",value="垂线号")
	private String vtno;
	
	@Description(key="primary",value="起点距")
	private double di;
	
	@Description(key="primary",value="河底高程")
	private double zb;
	
	@Description(key="primary",value="备注")
	private String comments;
	
	@Description(key="primary",value="修改时间戳")
	private String moditime;
	
	public Rvsect() {
		this.stcd="";
		this.mstm="";
		this.vtno="";
		this.di=0;
		this.zb=0;
		this.comments="";
		this.moditime="";
	}

	public Rvsect(
			String stcd, 
			String mstm, 
			String vtno, 
			double di, 
			double zb, 
			String comments, 
			String moditime) {
		this.stcd=stcd;
		this.mstm=mstm;
		this.vtno=vtno;
		this.di=di;
		this.zb=zb;
		this.comments=comments;
		this.moditime=moditime;
	}



	@Override
	public String toString() {
		return "Rvsect [stcd=" + stcd + ", mstm=" + mstm + ", vtno=" + vtno + ", di=" + di + ", zb=" + zb
				+ ", comments=" + comments + ", moditime=" + moditime + "]";
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

	@Column(name = "VTNO", length=4 )
	public String getVtno() {
		return vtno;
	}

	public void setVtno(String vtno) {
		this.vtno = vtno;
	}
	
	@Column(name = "DI", length=8 )
	public double getDi() {
		return di;
	}

	public void setDi(double di) {
		this.di = di;
	}

	@Column(name = "ZB", length=8 )
	public double getZb() {
		return zb;
	}

	public void setZb(double zb) {
		this.zb = zb;
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
