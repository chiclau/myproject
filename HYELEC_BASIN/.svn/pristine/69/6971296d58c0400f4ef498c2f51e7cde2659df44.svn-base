package com.lyht.business.analysisCalculation.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.Result;
import com.lyht.business.analysisCalculation.control.ResultControl;
import com.lyht.business.consumer.hydrologicalData.control.StbprpControl;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

/**
 * 产流计算action
 * @author 刘魁
 * 时间:2018.5.30
 */
@Namespace("/chanliu")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("/chanliu")
public class ChanLiuAction extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("ChanLiuAction");
	
	private StbprpFormBean mStbprpFormBean=new StbprpFormBean();
	@Resource
	private StbprpControl mStbprpControl;
	@Resource
	private ResultControl resultControl;
	
	
	private String name[];
	/**
	 * 根据测站名称查询测站信息
	 * @return
	 */
	public String getStbprp() {
		log.info("ChanLiuAction=list:根据测站名称查询测站信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mStbprpControl.getStbprpMes(mStbprpFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}

	/**
	 * 保存计算结果
	 * @return
	 */
	public String save() {
		log.info("ChanLiuAction=save:保存计算结果");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		Result result=new Result();
		Result result1=new Result();
		mRetMessage=resultControl.create(result, result1);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public StbprpFormBean getmStbprpFormBean() {
		return mStbprpFormBean;
	}

	public void setmStbprpFormBean(StbprpFormBean mStbprpFormBean) {
		this.mStbprpFormBean = mStbprpFormBean;
	}
	
	
	
	
}
