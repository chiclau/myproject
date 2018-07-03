package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SYS_ROLE")
@SuppressWarnings("restriction")
public class SysRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="主键")
	private int id;
	@Description(key="display",value="角色编号")
	private String roleCode;
	@Description(key="display",value="角色名称")
	private String roleName;
	@Description(key="display",value="已分配分组")
	private String groupCode;
	@Description(key="display",value="已分配用户")
	private String staffCode;
	@Description(key="display",value="已分配菜单")
	private String menuCode;
	@Description(key="display",value="状态")
	private int state;
	@Description(key="display",value="备注")
	private String remark;
	
	public SysRole() {
		this.id=0;
        this.roleCode = "";
        this.roleName = "";
        this.groupCode = "";
        this.staffCode="";
        this.menuCode = "";
        this.state = 1;
        this.remark = "";
    }

	public SysRole(
			int id,
			String roleCode,
			String roleName,
			String groupCode,
			String staffCode,
			String menuCode,
			int state,
			String remark){
		this.id=0;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.groupCode = groupCode;
        this.staffCode = staffCode;
        this.menuCode = menuCode;
        this.state = state;
        this.remark = remark;
	}
	
	@Override
	public String toString() {
		String s="";
    	s=	"{";
    	s=s+"id="+id;
    	s=s+",roleCode="+roleCode;
        s=s+",roleName="+roleName;
        s=s+",groupCode="+groupCode;
        s=s+",staffCode="+staffCode;
        s=s+",menuCode="+menuCode;
        s=s+",state="+state;
        s=s+",remark="+remark;
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
	@Column(name = "ROLE_CODE",length=10 )
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "ROLE_NAME",length=20 )
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "GROUP_CODE" )
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	@Column(name = "STAFF_CODE",length=30 )
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	@Column(name = "MENU_CODE" )
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	@Column(name = "STATE" )
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	@Column(name = "REMARK",length=500 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
