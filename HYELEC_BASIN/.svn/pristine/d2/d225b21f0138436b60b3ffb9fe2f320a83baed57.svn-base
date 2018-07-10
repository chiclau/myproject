package com.lyht.business.analysisCalculation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.control.ResultFourthControl;
import com.lyht.business.analysisCalculation.control.ResultThridControl;
import com.lyht.business.analysisCalculation.formbean.ResultFourthFormBean;
import com.lyht.business.analysisCalculation.service.ResultFourthService;
import com.lyht.business.analysisCalculation.service.ResultThridService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONObject;
/**
 * 汇流计算Action
 * @author 刘魁
 *@时间  2018/6/27    9:43
 */
@Namespace("/huiliu")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("/huiliu")
public class HuiLiuAction   extends BaseLyhtAction{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("HuiLiuAction");
	@Resource  
	private ResultThridControl  resultThridControl;//第三步的control
	@Resource  
	private ResultFourthControl resultFourthControl;//第四步的control
	@Resource private ResultThridService resultThridService;//第三步
	@Resource private ResultFourthService resultFourthService; //第四步
	
	/**
	 * 汇流计算第一步查询
	 * @return
	 */
	public String huiliuStep1() {
		log.info("HuiLiuAction=list: 步骤一 Table,json1的数据来源");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 0);
		ResultFourthFormBean rf=new ResultFourthFormBean();
		mRetMessage= resultFourthControl.getHuiLiuStep1(rf, mPageResults);//第二步需要的sql查询方法
		List<HashMap<String, Object>> listdata=mPageResults.getResults();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("DATE", "Q净");
		map.put("LLDX", "为最大流量减去对应时段的流量（地下）");
		listdata.add(map);
		mHashMap.put("DATA", listdata);//给DATA添加数据
		List<HashMap<String, Object>>list =new ArrayList<HashMap<String, Object>>();//EXTRADATA
		HashMap<String, Object> listMap=new HashMap<String, Object>();	
		listMap.put("STNM", "站名");
		listMap.put("PCH", "洪号");
		listMap.put("LYMJ", "流域面积");
		list.add(listMap);//添加数据	
		mHashMap.put("EXTRADATA", list);
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	
	/**
	 * 汇计算第二步
	 */
	public String  huiLiuStep2() {
		
		return null;
	}
}
