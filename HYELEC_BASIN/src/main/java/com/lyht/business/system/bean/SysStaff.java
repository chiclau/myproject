package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SYS_STAFF")
@SuppressWarnings("restriction")
public class SysStaff implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="用户编码")
	private String staffCode;
	
	@Description(key="display",value="用户姓名")
	private String staffName;
	
	@Description(key="display",value="所属单位")
	private String treeNmDept;
	
	@Description(key="display",value="部门")
	private String staffDept;
	
	@Description(key="display",value="联系电话")
	private String linkPhone;
	
	@Description(key="display",value="籍贯")
	private String jig;
	
	@Description(key="display",value="现居地址")
	private String staffAddress;
	
	@Description(key="display",value="状态")
	private int state;
	
	public SysStaff() {
        this.staffCode = "";
        this.staffName = "";
        this.treeNmDept ="";
        this.staffDept = "";
        this.linkPhone="";
        this.jig="";
        this.staffAddress="";
        this.state=0;
    }

	public SysStaff(
			String staffCode,
			String staffName,
			String treeNmDept,
			String staffDept,
			String linkPhone,
			String jig,
			String staffAddress,
			int state){
		this.staffCode = staffCode;
        this.staffName = staffName;
        this.treeNmDept = treeNmDept;
        this.staffDept = staffDept;
        this.linkPhone= linkPhone;
        this.jig= jig;
        this.staffAddress= staffAddress;
        this.state= state;
	}
	
	@Override
	public String toString() {
		String s="";
    	s="{";
    	s=s+"staffCode="+staffCode;
        s=s+",staffName="+staffName;
        s=s+",treeNmDept="+treeNmDept;
        s=s+",staffDept="+staffDept;
        s=s+",linkPhone="+linkPhone;
        s=s+",jig="+jig;
        s=s+",staffAddress="+staffAddress;
        s=s+",state="+state;
    	s=s+"}";
    	return s;
	}

	@Id
	@Column(name = "STAFF_CODE",length=10 )
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	@Column(name = "STAFF_NAME",length=20 )
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Column(name = "TREENM_DEPT",length=50 )
	public String getTreeNmDept() {
		return treeNmDept;
	}

	public void setTreeNmDept(String treeNmDept) {
		this.treeNmDept = treeNmDept;
	}

	@Column(name = "STAFF_DEPT",length=20 )
	public String getStaffDept() {
		return staffDept;
	}

	public void setStaffDept(String staffDept) {
		this.staffDept = staffDept;
	}

	@Column(name = "LINK_PHONE",length=11 )
	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	@Column(name = "JIG",length=20 )
	public String getJig() {
		return jig;
	}

	public void setJig(String jig) {
		this.jig = jig;
	}

	@Column(name = "STAFF_ADDRESS",length=50 )
	public String getStaffAddress() {
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	@Column(name = "STATE" )
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
