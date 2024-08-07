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
	private double yml;
	
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

	@Description(key="primary",value="降雨量")
	private String  jyl;
	
	@Description(key="primary",value="主键")
	private String id;
	
	@Description(key="primary",value="测站名称")
	private String stnm;
	
	@Description(key="primary",value="权重")
	private String qz;
	
	
	
	
	@Column(name = "STNM",length=50 )
	public String getStnm() {
		return stnm;
	}

	public void setStnm(String stnm) {
		this.stnm = stnm;
	}
	
	@Column(name = "QZ",length=50 )
	public String getQz() {
		return qz;
	}

	public void setQz(String qz) {
		this.qz = qz;
	}

	@Column(name = "CREATE_STAFF",length=50 )
	public String getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(String createStaff) {
		this.createStaff = createStaff;
	}

	@Column(name = "YML",length=50 )
	public double getYml() {
		return yml;
	}

	public void setYml(double yml) {
		this.yml = yml;
	}

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
	
	@Column(name = "JYL",length=50)
	public String getJyl() {
		return jyl;
	}

	public void setJyl(String jyl) {
		this.jyl = jyl;
	}

	@Id
	@Column(name = "ID",length=50 )
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	

	public Result(){
		this.createTime="";
		this.pch="";
		this.stcd="";
		this.yml=0;
		this.createTime="";
		this.ymlTime="";
		this.createStaff="";
		this.jyl="";
		this.id="";
		this.stnm="";
		this.qz="";
	}
	
	public Result( double yml,String createTime,String ymlTime,String pch,String stcd,String createStaff, String jyl,String id,
			String stnm,String qz) {
		this.yml=yml;
		this.pch=pch;
		this.createTime=createTime;
		this.stcd=stcd;
		this.ymlTime=ymlTime;
		this.createStaff=createStaff;
		this.jyl=jyl;
		this.id=id;
		this.stnm=stnm;
		this.qz=qz;
		
	}
	
	
}
