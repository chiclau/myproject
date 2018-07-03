package com.lyht.business.modelManage.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.business.modelManage.bean.ModelInfo;
import com.lyht.business.modelManage.formbean.ModelInfoFormBean;
import com.lyht.business.modelManage.service.ModelParamenterService;
/**
 *作者： 刘魁
 *脚本日期:2018年5月5日 10:41:11
 *说明:  参数值Control
*/
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ModelParamenterControl {
	
	@Resource
	private ModelParamenterService modelParamenterService;
	/**
	 * 根据modelcode获取modelparamter
	 */
	@Optlog(menuflag="modelParaView", opttype = "view") 
	public List getModelParaMess(ModelInfoFormBean modelInfoFormBean,ModelInfo modelInfo) {
		RetMessage mRetMessage=new RetMessage();
		List list=new ArrayList();
		try {
			 list=modelParamenterService.getParaListData(modelInfoFormBean).getResults();
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return list;
	}
	
	@Optlog(menuflag="depPara", opttype = "delete") 
	public RetMessage depPara(String ids) {
		RetMessage mRetMessage=new RetMessage();
		try {
			 modelParamenterService.delParaById(ids);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
}
