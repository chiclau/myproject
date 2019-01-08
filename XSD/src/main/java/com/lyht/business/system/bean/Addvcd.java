package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SD_ADDVCD_DIC")
@SuppressWarnings("restriction")
public class Addvcd implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="行政区划码")
	private String addvcd;
	
	@Description(key="display",value="行政区划名")
	private String name;
	
	@Description(key="display",value="关联省份")
	private String paddvcd;
	
	public Addvcd() {
        this.addvcd = "";
        this.name = "";
        this.paddvcd = "";
    }

	public Addvcd(
			String addvcd,
			String name,
			String paddvcd){
		this.addvcd = addvcd;
        this.name = name;
        this.paddvcd = paddvcd;
	}
	
	@Override
	public String toString() {
		String s="";
    	s=	"{";
        s=s+"addvcd="+addvcd;
        s=s+",name="+name;
        s=s+",paddvcd="+paddvcd;
    	s=s+"}";
    	return s;
	}
	
	@Id
	@Column(name = "ADDVCD",length=6 )
	public String getAddvcd() {
		return addvcd;
	}

	public void setAddvcd(String addvcd) {
		this.addvcd = addvcd;
	}

	@Column(name = "NAME",length=60 )
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PADDVCD",length=6 )
	public String getPaddvcd() {
		return paddvcd;
	}

	public void setPaddvcd(String paddvcd) {
		this.paddvcd = paddvcd;
	}
	
}
