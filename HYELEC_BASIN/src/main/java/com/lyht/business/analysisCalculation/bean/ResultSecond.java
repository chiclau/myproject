package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;

/**
 * 第二部保存的表
 * @author 刘魁
 * @时间 2018/7/11
 */
@Entity
@Table(name = "C_RESULT_SECOND")
public class ResultSecond implements Serializable {

	private static final long serialVersionUID = 1L;

	@Description(key="primary",value="STCD")
	private String STCD;
	
	@Description(key="primary",value="流域面积")
	private Double LLMJ;
	
	@Description(key="primary",value="PCH")
	private String pch;
	
	@Description(key="primary",value="ID")
	private String id;
	@Description(key="primary",value="PA")
	private Double pa;
	
	@Description(key="primary",value="蒸发站编码 ")
	private String zstcd;
	@Description(key="primary",value="IM ")
	private Double im;
	@Description(key="primary",value="B ")
	private Double b;
	@Description(key="primary",value="方案编码")
	private String fanm;
	@Description(key="primary",value="第五步Em症发站编码")
	private String emstcd;
	
	@Column(name="FANM")
	public String getFanm() {
		return fanm;
	}

	public void setFanm(String fanm) {
		this.fanm = fanm;
	}
	@Column(name="EMSTCD")
	public String getEmstcd() {
		return emstcd;
	}

	public void setEmstcd(String emstcd) {
		this.emstcd = emstcd;
	}

	@Column(name="ZSTCD")
	public String getZstcd() {
		return zstcd;
	}

	public void setZstcd(String zstcd) {
		this.zstcd = zstcd;
	}
	@Column(name="IM")
	public Double getIm() {
		return im;
	}

	public void setIm(Double im) {
		this.im = im;
	}
	@Column(name="B")
	public Double getB() {
		return b;
	}

	public void setB(Double b) {
		this.b = b;
	}
	
	@Column(name="RC")
	public Double getRc() {
		return rc;
	}

	public void setRc(Double rc) {
		this.rc = rc;
	}

	@Description(key="primary",value="RS")
	private Double rs;
	@Description(key="primary",value="RX")
	private Double rx;
	private Double sqt3;
	private Double sqt4;
	private Double rc;//第六步计算tc时的r和
	private Double i1;
    private Double i2;
    private Double q1;
    private Double q2;
    
    @Column(name="I1")
    public Double getI1() {
		return i1;
	}
	public void setI1(Double i1) {
		this.i1 = i1;
	}
	@Column(name="I2")
	public Double getI2() {
		return i2;
	}
	public void setI2(Double i2) {
		this.i2 = i2;
	}
	@Column(name="Q1")
	public Double getQ1() {
		return q1;
	}
	public void setQ1(Double q1) {
		this.q1 = q1;
	}
	@Column(name="Q2")
	public Double getQ2() {
		return q2;
	}
	public void setQ2(Double q2) {
		this.q2 = q2;
	}
	@Column(name="FCJ")
	public Double getFcj() {
		return fcj;
	}

	public void setFcj(Double fcj) {
		this.fcj = fcj;
	}

	@Column(name="FCS")
	public Double getFcs() {
		return fcs;
	}

	public void setFcs(Double fcs) {
		this.fcs = fcs;
	}

	@Column(name="FC")
	public Double getFc() {
		return fc;
	}

	public void setFc(Double fc) {
		this.fc = fc;
	}

	@Column(name="TC")
	public Double getTc() {
		return tc;
	}

	public void setTc(Double tc) {
		this.tc = tc;
	}

	private Double fcj;//fc计算
	
	private Double fcs;//fc试算
	
	private Double fc;//fc
	
	private Double tc;
	
	@Column(name = "STCD",length=50 )
	public String getSTCD() {
		return STCD;
	}

	public void setSTCD(String sTCD) {
		STCD = sTCD;
	}
	@Column(name = "LYMJ",length=50 )
	public Double getLLMJ() {
		return LLMJ;
	}

	public void setLLMJ(Double lLMJ) {
		LLMJ = lLMJ;
	}
	@Column(name = "PCH",length=50 )
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}
	@Id
	@Column(name = "ID",length=50 )
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "PA",length=50 )
	public Double getPa() {
		return pa;
	}

	public void setPa(Double pa) {
		this.pa = pa;
	}
	@Column(name = "RS",length=50 )
	public Double getRs() {
		return rs;
	}

	public void setRs(Double rs) {
		this.rs = rs;
	}
	@Column(name = "RX",length=50 )
	public Double getRx() {
		return rx;
	}

	public void setRx(Double rx) {
		this.rx = rx;
	}
	
	@Column(name="SQT3")
	public Double getSqt3() {
		return sqt3;
	}

	public void setSqt3(Double sqt3) {
		this.sqt3 = sqt3;
	}
	@Column(name="SQT4")
	public Double getSqt4() {
		return sqt4;
	}

	public void setSqt4(Double sqt4) {
		this.sqt4 = sqt4;
	}

	public ResultSecond() {
		
	}
	
	
	
}
