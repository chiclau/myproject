package com.lyht.business.system.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysLog;
/**
  * 创建人： 陈震宇 
  * 脚本日期:2017年7月29日 23:41:12
  * 说明:  系统日志
  */
public class SysLogFormBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private SysLog infoBean = new SysLog(); 
    /**
     * 用于快速模糊匹配关键字
     */
    private String searchName;
    
    /**
     * 用于批量选中多ids，以","分隔 如：1,2,3,4
     */
    private String ids;
        
    @SuppressWarnings("rawtypes")
	private PageResults pageBean=new PageResults();

	public SysLog getInfoBean() {
		return infoBean;
	}

	public void setInfoBean(SysLog infoBean) {
		this.infoBean = infoBean;
	}

	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
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
}