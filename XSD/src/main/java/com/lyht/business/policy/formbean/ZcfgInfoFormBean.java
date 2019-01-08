package com.lyht.business.policy.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.policy.bean.ZcfgInfo;

@SuppressWarnings("rawtypes")
public class ZcfgInfoFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ZcfgInfo zcfgInfo = new ZcfgInfo();
	
	private PageResults pageBean = new PageResults();
	
	private String searchName;

	public ZcfgInfo getZcfgInfo() {
		return zcfgInfo;
	}

	public void setZcfgInfo(ZcfgInfo zcfgInfo) {
		this.zcfgInfo = zcfgInfo;
	}

	public PageResults getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
}
