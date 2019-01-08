package com.lyht.business.analysisCalculation.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.StPptnDayR;

public class StPptnDayRFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
	private String startTime; //查询起始时间
	private String endTime;  //查询结束时间
	private String stcd;  //测站名称查询
	private StPptnDayR stPptnDayRInfoBean=new StPptnDayR();
	private PageResults pageBean=new PageResults();
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
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
	public StPptnDayR getStPptnDayRInfoBean() {
		return stPptnDayRInfoBean;
	}
	public void setStPptnDayRInfoBean(StPptnDayR stPptnDayRInfoBean) {
		this.stPptnDayRInfoBean = stPptnDayRInfoBean;
	}
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}

}
