package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SYS_USER")
@SuppressWarnings("restriction")
public class SysUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="账号编码")
	private String userCode;
	
	@Description(key="display",value="人员编码")
	private String StaffCode;
	
	@Description(key="display",value="账号名称")
	private String userName;
	
	@Description(key="display",value="密码")
	private String userPwd;
	
	@Description(key="display",value="状态")
	private int state;
	
	public SysUser() {
        this.userCode = "";
        this.StaffCode = "";
        this.userName ="";
        this.userPwd = "";
        this.state=0;
    }

	public SysUser(
			String userCode,
			String StaffCode,
			String userName,
			String userPwd,
			int state){
		this.userCode = userCode;
        this.StaffCode = StaffCode;
        this.userName = userName;
        this.userPwd = userPwd;
        this.state= state;
	}
	
	@Override
	public String toString() {
		String s="";
    	s="{";
    	s=s+"userCode="+userCode;
        s=s+",StaffCode="+StaffCode;
        s=s+",userName="+userName;
        s=s+",userPwd="+userPwd;
        s=s+",state="+state;
    	s=s+"}";
    	return s;
	}

	
	@Id
	@Column(name = "USER_CODE",length=10 )
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "STAFF_CODE",length=10 )
	public String getStaffCode() {
		return StaffCode;
	}

	public void setStaffCode(String staffCode) {
		StaffCode = staffCode;
	}

	@Column(name = "USER_NAME",length=20 )
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_PWD",length=50 )
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Column(name = "STATE" )
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
