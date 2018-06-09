package com.lyht.business.consumer.hydrologicalData.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Dayev;

@SuppressWarnings("rawtypes")
public class DayevFormBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
	private Dayev mDayevInfoBean=new Dayev();
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
	public Dayev getmDayevInfoBean() {
		return mDayevInfoBean;
	}
	public void setmDayevInfoBean(Dayev mDayevInfoBean) {
		this.mDayevInfoBean = mDayevInfoBean;
	}
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	
}