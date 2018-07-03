package com.lyht.business.system.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.base.hibernate.common.ParmBean;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.bean.SysOperRef;
import com.lyht.business.system.bean.SysRole;

@SuppressWarnings("rawtypes")
public class SysOperRefFormBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
	private SysOperRef mSysOperRefBean=new SysOperRef();
	private SysRole mSysRoleBean=new SysRole();
	private PageResults pageBean=new PageResults();
	private ParmBean parmBean=new ParmBean();
	
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
	public SysOperRef getmSysOperRefBean() {
		return mSysOperRefBean;
	}
	public void setmSysOperRefBean(SysOperRef mSysOperRefBean) {
		this.mSysOperRefBean = mSysOperRefBean;
	}
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	public ParmBean getParmBean() {
		return parmBean;
	}
	public void setParmBean(ParmBean parmBean) {
		this.parmBean = parmBean;
	}
	public SysRole getmSysRoleBean() {
		return mSysRoleBean;
	}
	public void setmSysMenuBean(SysRole mSysRole) {
		this.mSysRoleBean = mSysRole;
	}
	
}
