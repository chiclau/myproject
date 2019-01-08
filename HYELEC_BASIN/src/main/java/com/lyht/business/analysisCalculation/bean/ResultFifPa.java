package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;
/**
 * 第五步保存Pa的值
 * @author Administrator
 *
 */
@Entity
@Table(name = "C_FIFTH_PA")
public class ResultFifPa implements Serializable{
	@Description(key="primary",value="主键")
	private String id;

	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="primary",value="Pa")
	private double pa;
	
	@Description(key="primary",value="批次号")
	private String pch;
	@Id
	@Column(name = "ID",length=50 )
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "STCD",length=50 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Column(name = "PA",length=50 )
	public double getPa() {
		return pa;
	}

	public void setPa(double pa) {
		this.pa = pa;
	}
	@Column(name = "PCH",length=50 )
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}
	
	
	
}
