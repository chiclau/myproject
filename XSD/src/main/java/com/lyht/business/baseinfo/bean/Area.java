package com.lyht.business.baseinfo.bean;

import java.io.Serializable;

import javax.persistence.Column;

public class Area implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="codeID")
	private String codeID;
	
	@Column(name="name")
	private String name; 
	
	@Column(name="parentID")
	private String parentID;
	public String getCodeID() {
		return codeID;
	}
	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	
	
	
}
