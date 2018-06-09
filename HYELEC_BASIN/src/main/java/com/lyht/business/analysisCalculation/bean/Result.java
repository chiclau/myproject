package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;

/**
 *作者： 刘魁
 *脚本日期:2018年6月2日 18:19:11
 *说明:  产流计算结果   表
*/
@Entity
@Table(name = "C_RESULT")
public class Result  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Description(key="primary",value="雨面量")
	private String yml;
	
	@Description(key="primary",value="创建时间")
	private String createTime;
	
	@Description(key="primary",value="时间")
	private String ymlTime;
	
	@Description(key="primary",value="创建人")
	private String createStaff;
	
	@Description(key="primary",value="批次号")
	private String pch;
	
	@Description(key="primary",value="测站编码")
	private String stcd;

	@Column(name = "CREATE_STAFF",length=50 )
	public String getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}

	@Column(name = "YML",length=50 )
	public String getYml() {
		return yml;
	}

	public void setYml(String yml) {
		this.yml = yml;
	}
	@Id
	@Column(name = "CREATE_TIME",length=50 )
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "YML_DATE",length=50 )
	public String getYmlTime() {
		return ymlTime;
	}

	public void setYmlTime(String ymlTime) {
		this.ymlTime = ymlTime;
	}
	
	@Column(name = "PCH",length=50 )
	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	@Column(name = "STCD",length=50 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	@Override
	public String toString() {
		return "Result [yml=" + yml + ", createTime=" + createTime + ", ymlTime=" + ymlTime + ", pch=" + pch + ", stcd="
				+ stcd + "]";
	}

	public Result(){
		this.createTime="";
		this.pch="";
		this.stcd="";
		this.yml="";
		this.createTime="";
		this.ymlTime="";
		this.createStaff="";
	}
	
	public Result( String yml,String createTime,String ymlTime,String pch,String stcd,String createStaff) {
		this.yml=yml;
		this.pch=pch;
		this.createTime=createTime;
		this.stcd=stcd;
		this.ymlTime=ymlTime;
		this.createStaff=createStaff;
	}
	
	
}
