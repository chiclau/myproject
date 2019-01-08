package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "SYS_ROLE_GROUP")
public class SysRoleGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String roleCode;
	
	private String groupCode;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false )
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="ROLE_CODE")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@Column(name="GROUP_CODE")
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}
