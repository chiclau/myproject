package com.lyht.business.system.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysUser;

@SuppressWarnings("rawtypes")
public class SysUserFormBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
    private String newPwd;
    private SysUser mSysUserInfoBean=new SysUser();
	private PageResults pageBean=new PageResults();
	
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
	public SysUser getmSysUserInfoBean() {
		return mSysUserInfoBean;
	}
	public void setmSysUserInfoBean(SysUser mSysUserInfoBean) {
		this.mSysUserInfoBean = mSysUserInfoBean;
	}
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
