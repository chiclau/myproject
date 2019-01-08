package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;
@Entity
@Table(name = "H_RESULT")
@SuppressWarnings({ "serial", "restriction" })
public class Hresult implements Serializable {

	@Description(key="primary",value="唯一编码")
	private String nm;
	@Description(key="display",value="洪号")
	private String pch;
	@Description(key="display",value="站码")
	private String stcd;
	@Description(key="display",value="Q净流量")
	private Double q;
	@Description(key="display",value="汇流时间（小时）")
	private Double t;
	@Description(key="display",value="地表净雨深")
	private Double r;
	@Description(key="display",value="流域面积")
	private Double mj;
	@Description(key="display",value="L")
	private Double l;
	@Description(key="display",value="J")
	private Double j;
	@Description(key="display",value="J1/3			")
	private Double js1;
	@Description(key="display",value="L/ (J1/3*F)")
	private Double js2;
	@Description(key="display",value="Qm3/4")
	private Double js3;
	@Description(key="display",value="Qm3/4/ R上")
	private Double js4;
	@Description(key="display",value="m计")
	private Double m;
	@Description(key="display",value="历时")
	private Double tc;
	@Description(key="display",value="ER/TC")
	private Double ertc;
	
	@Id
	@Column(name = "NM",length=50,unique = true,nullable = false)
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	@Column(name = "PCH")
	public String getPch() {
		return pch;
	}
	public void setPch(String pch) {
		this.pch = pch;
	}
	@Column(name = "STCD")
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Column(name = "Q")
	public Double getQ() {
		return q;
	}
	public void setQ(Double q) {
		this.q = q;
	}
	@Column(name = "T")
	public Double getT() {
		return t;
	}
	public void setT(Double t) {
		this.t = t;
	}
	@Column(name = "R")
	public Double getR() {
		return r;
	}
	public void setR(Double r) {
		this.r = r;
	}
	@Column(name = "MJ")
	public Double getMj() {
		return mj;
	}
	public void setMj(Double mj) {
		this.mj = mj;
	}
	@Column(name = "L")
	public Double getL() {
		return l;
	}
	public void setL(Double l) {
		this.l = l;
	}
	@Column(name = "J")
	public Double getJ() {
		return j;
	}
	public void setJ(Double j) {
		this.j = j;
	}
	@Column(name = "JS1")
	public Double getJs1() {
		return js1;
	}
	public void setJs1(Double js1) {
		this.js1 = js1;
	}
	@Column(name = "JS2")
	public Double getJs2() {
		return js2;
	}
	public void setJs2(Double js2) {
		this.js2 = js2;
	}
	@Column(name = "JS3")
	public Double getJs3() {
		return js3;
	}
	public void setJs3(Double js3) {
		this.js3 = js3;
	}
	@Column(name = "JS4")
	public Double getJs4() {
		return js4;
	}
	public void setJs4(Double js4) {
		this.js4 = js4;
	}
	@Column(name = "M")
	public Double getM() {
		return m;
	}
	public void setM(Double m) {
		this.m = m;
	}
	@Column(name = "TC")
	public Double getTc() {
		return tc;
	}
	public void setTc(Double tc) {
		this.tc = tc;
	}
	@Column(name = "ERTC")
	public Double getErtc() {
		return ertc;
	}
	public void setErtc(Double ertc) {
		this.ertc = ertc;
	}
}
