package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SYS_GROUP")
@SuppressWarnings("restriction")
public class SysGroup implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="主键")
	private int id;
	
	@Description(key="primary",value="分组编码")
	private String groupCode;
	
	@Description(key="display",value="分组名称")
	private String groupName;
	
	@Description(key="display",value="已分配流域")
	private String basinCode;
	
	@Description(key="display",value="已分配人员")
	private String staffCode;
	
	@Description(key="display",value="分组状态")
	private int state;
	
	@Description(key="display",value="备注")
	private String remark;

	public SysGroup() {
		this.id=0;
        this.groupCode = "";
        this.groupName = "";
        this.basinCode ="";
        this.staffCode = "";
        this.state=1;
        this.remark="";
    }

	public SysGroup(
			int id,
	        String groupCode,
	        String groupName,
	        String basinCode,
	        String staffCode,
	        int state,
	        String remark){
		this.id=id;
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.basinCode = basinCode;
        this.staffCode = staffCode;
        this.state= state;
        this.remark= remark;
	}
	
	@Override
	public String toString() {
		String s="";
    	s="{";
    	s=s+"id="+id;
        s=s+",groupCode="+groupCode;
        s=s+",groupName="+groupName;
        s=s+",basinCode="+basinCode;
        s=s+",staffCode="+staffCode;
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
	@Column(name = "GROUP_CODE",length=10 )
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@Column(name = "GROUP_NAME",length=50 )
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "BASIN_CODE" )
	public String getBasinCode() {
		return basinCode;
	}

	public void setBasinCode(String basinCode) {
		this.basinCode = basinCode;
	}

	@Column(name = "STAFF_CODE" )
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
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
