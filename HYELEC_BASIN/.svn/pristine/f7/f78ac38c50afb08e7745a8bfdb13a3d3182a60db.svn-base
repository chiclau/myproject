package com.lyht.business.consumer.hydrologicalData.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Zvarl;

@SuppressWarnings("rawtypes")
public class ZvarlFormBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
    private String idsup; //用于批量选中多ids，以","分隔 如：1,2,3,4
	private Zvarl mZvarlInfoBean=new Zvarl();
	private PageResults pageBean=new PageResults();
	
	public String getIdsup() {
		return idsup;
	}
	public void setIdsup(String idsup) {
		this.idsup = idsup;
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
	public Zvarl getmZvarlInfoBean() {
		return mZvarlInfoBean;
	}
	public void setmZvarlInfoBean(Zvarl mZvarlInfoBean) {
		this.mZvarlInfoBean = mZvarlInfoBean;
	}
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	
}
