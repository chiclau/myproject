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
@Table(name = "ST_RIVER_R")
@SuppressWarnings("restriction")
public class River implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="主键内码")
	private String nm;
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="时间")
	private String tm;
	
	@Description(key="primary",value="水位")
	private Double z;
	
	@Description(key="primary",value="流量")
	private Double q;
	
	@Description(key="primary",value="断面过水面积")
	private Double xsa;
	
	@Description(key="primary",value="断面平均流速")
	private Double xsavv;
	
	@Description(key="primary",value="断面最大流速")
	private Double xsmxv;
	
	@Description(key="primary",value="河水特征码")
	private String flwchrcd;
	
	@Description(key="primary",value="水势")
	private String wptn;
	
	@Description(key="primary",value="测流方法")
	private String msqmt;
	
	@Description(key="primary",value="测积方法")
	private String msamt;
	
	@Description(key="primary",value="测速方法")
	private String msvmt;
	
	public River() {
		
	}

	public River( 
			String stcd, 
			String tm, 
			Double z, 
			Double q,
			Double xsa,
			Double xsavv,
			Double xsmxv,
			String flwchrcd,
			String wptn,
			String msqmt,
			String msamt,
			String msvmt
			) {
		this.stcd=stcd;
		this.tm=tm;
		this.z=z;
		this.q=q;
		this.xsa=xsa;
		this.xsavv=xsavv;
		this.xsmxv=xsmxv;
		this.flwchrcd=flwchrcd;
		this.wptn=wptn;
		this.msqmt=msqmt;
		this.msamt=msamt;
		this.msvmt=msvmt;
	}

	@Override
	public String toString() {
		return "River [stcd=" + stcd + ", tm=" + tm + ", z=" + z + ", q=" + q + ", xsa=" + xsa + ", xsavv=" + xsavv
				+ ", xsmxv=" + xsmxv + ", flwchrcd=" + flwchrcd + ", wptn=" + wptn + ", msqmt=" + msqmt + ", msamt="
				+ msamt + ", msvmt=" + msvmt + "]";
	}
	@Id
	@Column(name = "NM", length=8 )
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
	@Column(name = "STCD", length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Column(name = "TM" )
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Column(name = "Z", length=7 )
	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}
	
	@Column(name = "Q", length=9 )
	public Double getQ() {
		return q;
	}

	public void setQ(Double q) {
		this.q = q;
	}

	@Column(name = "XSA", length=9 )
	public Double getXsa() {
		return xsa;
	}

	public void setXsa(Double xsa) {
		this.xsa = xsa;
	}
	@Column(name = "XSAVV", length=5 )
	public Double getXsavv() {
		return xsavv;
	}

	public void setXsavv(Double xsavv) {
		this.xsavv = xsavv;
	}
	@Column(name = "XSMXV", length=5 )
	public Double getXsmxv() {
		return xsmxv;
	}

	public void setXsmxv(Double xsmxv) {
		this.xsmxv = xsmxv;
	}
	@Column(name = "FLWCHRCD", length=1 )
	public String getFlwchrcd() {
		return flwchrcd;
	}

	public void setFlwchrcd(String flwchrcd) {
		this.flwchrcd = flwchrcd;
	}
	@Column(name = "WPTN", length=1 )
	public String getWptn() {
		return wptn;
	}

	public void setWptn(String wptn) {
		this.wptn = wptn;
	}
	@Column(name = "MSQMT", length=1 )
	public String getMsqmt() {
		return msqmt;
	}

	public void setMsqmt(String msqmt) {
		this.msqmt = msqmt;
	}
	@Column(name = "MSAMT", length=1 )
	public String getMsamt() {
		return msamt;
	}

	public void setMsamt(String msamt) {
		this.msamt = msamt;
	}
	@Column(name = "MSVMT", length=1 )
	public String getMsvmt() {
		return msvmt;
	}

	public void setMsvmt(String msvmt) {
		this.msvmt = msvmt;
	}
	
	
}
