package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SYS_OPER_REF")
@SuppressWarnings("restriction")
public class SysOperRef implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="主键")
	private int id;
	
	@Description(key="display",value="唯一编码")
	private String refCode;
	
	@Description(key="display",value="关联A主键")
	private String refApk;
	
	@Description(key="display",value="关联A表名")
	private String refAname;
	
	@Description(key="display",value="关联B主键")
	private String refBpk;
	
	@Description(key="display",value="关联B表名")
	private String refBname;
	
	@Description(key="display",value="状态")
	private int state;
	
	public SysOperRef() {
		this.id=0;
		this.refCode="";
        this.refApk = "";
        this.refAname = "";
        this.refBpk = "";
        this.refBname = "";
        this.state = 0;
    }

	public SysOperRef(
			int id,
			String refCode,
			String refApk,
			String refAname,
			String refBpk,
			String refBname,
			int state){
		this.id=id;
		this.refCode=refCode;
        this.refApk = refApk;
        this.refAname = refAname;
        this.refBpk = refBpk;
        this.refBname = refBname;
        this.state = 0;
	}
	
	@Override
	public String toString() {
		String s="";
    	s=	"{";
    	s=s+"id="+id;
    	s=s+",refCode="+refCode;
        s=s+",refApk="+refApk;
        s=s+",refAname="+refAname;
        s=s+",refBpk="+refBpk;
        s=s+",refBname="+refBname;
        s=s+",state="+state;
    	s=s+"}";
    	return s;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id" )
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "REF_CODE",length=10 )
	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	@Column(name = "REF_A_PK",length=10 )
	public String getRefApk() {
		return refApk;
	}

	public void setRefApk(String refApk) {
		this.refApk = refApk;
	}

	@Column(name = "REF_A_NAME",length=20 )
	public String getRefAname() {
		return refAname;
	}

	public void setRefAname(String refAname) {
		this.refAname = refAname;
	}

	@Column(name = "REF_B_PK",length=10 )
	public String getRefBpk() {
		return refBpk;
	}

	public void setRefBpk(String refBpk) {
		this.refBpk = refBpk;
	}

	@Column(name = "REF_B_NAME",length=20 )
	public String getRefBname() {
		return refBname;
	}

	public void setRefBname(String refBname) {
		this.refBname = refBname;
	}

	@Column(name = "STATE" )
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
