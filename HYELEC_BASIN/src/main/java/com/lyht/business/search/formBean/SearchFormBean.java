package com.lyht.business.search.formBean;

import com.lyht.business.consumer.hydrologicalData.bean.Stbprp;

/**
 * 查询表单
 */
public class SearchFormBean {
	
	//测站编码
	private String stcd ;
	
	//实时查询 时段  1 3 6 12 小时
	private int time ;
	
	//时间范围  开始时间、结束范围
	private String startTime;
	private String endTime;
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	private Stbprp sbprp = new Stbprp();

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Stbprp getSbprp() {
		return sbprp;
	}

	public void setSbprp(Stbprp sbprp) {
		this.sbprp = sbprp;
	}

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
}
