package com.lyht.business.modelManage.formbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.modelManage.bean.ModelInfo;
import com.lyht.business.modelManage.bean.ModelParamenter;
/**
 *作者： 刘魁
 *脚本日期:2018年5月7日 21:41:11
 *说明:  模型信息FormBean
*/
@SuppressWarnings("rawtypes")
public class ModelInfoFormBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String searchName; //用于快速模糊匹配关键字
    private String ids; //用于批量选中多ids，以","分隔 如：1,2,3,4
	private ModelInfo ModelInfoFormBean=new ModelInfo();
	private ModelParamenter modelParaBean=new ModelParamenter();
	private List<ModelParamenter> modelParaList=new ArrayList<ModelParamenter>();
	private PageResults pageBean=new PageResults();
	
	
	
	
	
	public List<ModelParamenter> getModelParaList() {
		return modelParaList;
	}
	public void setModelParaList(List<ModelParamenter> modelParaList) {
		this.modelParaList = modelParaList;
	}
	public ModelParamenter getModelParaBean() {
		return modelParaBean;
	}
	public void setModelParaBean(ModelParamenter modelParaBean) {
		this.modelParaBean = modelParaBean;
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
	public ModelInfo getModelInfoFormBean() {
		return ModelInfoFormBean;
	}
	public void setModelInfoFormBean(ModelInfo modelInfoFormBean) {
		ModelInfoFormBean = modelInfoFormBean;
	}
	public PageResults getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageResults pageBean) {
		this.pageBean = pageBean;
	}
	

}
