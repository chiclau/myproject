package com.lyht.business.system.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysGroup;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.bean.SysStaff;

@SuppressWarnings("rawtypes")
public class SysRoleFormBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
	private SysRole mSysRoleInfoBean=new SysRole();
	private SysStaff mSysStaffInfoBean=new SysStaff();
	private SysGroup mSysGroupInfoBean=new SysGroup();
	private PageResults pageBean=new PageResults();
	
	public SysGroup getmSysGroupInfoBean() {
		return mSysGroupInfoBean;
	}
	public void setmSysGroupInfoBean(SysGroup mSysGroupInfoBean) {
		this.mSysGroupInfoBean = mSysGroupInfoBean;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public SysRole getmSysRoleInfoBean() {
		return mSysRoleInfoBean;
	}
	public void setmSysRoleInfoBean(SysRole mSysRoleInfoBean) {
		this.mSysRoleInfoBean = mSysRoleInfoBean;
	}
	public SysStaff getmSysStaffInfoBean() {
		return mSysStaffInfoBean;
	}
	public void setmSysStaffInfoBean(SysStaff mSysStaffInfoBean) {
		this.mSysStaffInfoBean = mSysStaffInfoBean;
	}
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
}
