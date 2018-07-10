package com.lyht.business.analysisCalculation.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.Result;
/**
 * 计算结果formbean
 * @author 刘魁
 * 时间2018/06/11 14:21
 *
 */
public class ResultFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
	private  Result resultBean=new Result();
    @SuppressWarnings("rawtypes")
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
	public Result getResultBean() {
		return resultBean;
	}
	public void setResultBean(Result resultBean) {
		this.resultBean = resultBean;
	}
	@SuppressWarnings("rawtypes")
	public PageResults getPageBean() {
		return pageBean;
	}
	@SuppressWarnings("rawtypes")
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
    
    
    
    
}
