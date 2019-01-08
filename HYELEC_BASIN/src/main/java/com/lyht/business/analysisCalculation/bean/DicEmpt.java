package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "DIC_EMPT")
public class DicEmpt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String empt;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID",unique = true,nullable = false 
		           )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="EMPT")
	public String getEmpt() {
		return empt;
	}

	public void setEmpt(String empt) {
		this.empt = empt;
	}

}
