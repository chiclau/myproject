package com.lyht.business.analysisCalculation.formbean;

import java.io.Serializable;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ModelProgValue;
import com.lyht.business.analysisCalculation.bean.ModelProgram;
/**
 *作者： 刘魁
 *脚本日期:2018年5月12日 15:41:11
 *说明:  模型方案FormBean
*/
public class ModelProgramFromBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
    private ModelProgram modelprogramFormBean=new ModelProgram();
	private ModelProgValue modelParaValueBean=new ModelProgValue();
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
	
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	public ModelProgram getModelprogramFormBean() {
		return modelprogramFormBean;
	}
	public void setModelprogramFormBean(ModelProgram modelprogramFormBean) {
		this.modelprogramFormBean = modelprogramFormBean;
	}
	public ModelProgValue getModelParaValueBean() {
		return modelParaValueBean;
	}
	public void setModelParaValueBean(ModelProgValue modelParaValueBean) {
		this.modelParaValueBean = modelParaValueBean;
	}
    
    
}
