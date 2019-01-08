package com.lyht.business.analysisCalculation.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.Result;
import com.lyht.business.analysisCalculation.bean.ResultFifPa;
import com.lyht.business.analysisCalculation.bean.ResultFifth;
import com.lyht.business.analysisCalculation.bean.ResultFourth;
import com.lyht.business.analysisCalculation.bean.ResultSecond;
import com.lyht.business.analysisCalculation.bean.ResultSixth;
import com.lyht.business.analysisCalculation.bean.ResultSixthResult;
import com.lyht.business.analysisCalculation.bean.ResultThrid;
import com.lyht.business.analysisCalculation.control.ResultControl;
import com.lyht.business.analysisCalculation.control.ResultFourthControl;
import com.lyht.business.analysisCalculation.control.ResultSecondControl;
import com.lyht.business.analysisCalculation.control.ResultThridControl;
import com.lyht.business.analysisCalculation.formbean.ResultFormBean;
import com.lyht.business.analysisCalculation.formbean.ResultFourthFormBean;
import com.lyht.business.analysisCalculation.formbean.ResultThridFormBean;
import com.lyht.business.analysisCalculation.service.ResultFifthService;
import com.lyht.business.analysisCalculation.service.ResultFourthService;
import com.lyht.business.analysisCalculation.service.ResultSecondService;
import com.lyht.business.analysisCalculation.service.ResultService;
import com.lyht.business.analysisCalculation.service.ResultSixthService;
import com.lyht.business.analysisCalculation.service.ResultThridService;
import com.lyht.business.consumer.hydrologicalData.bean.Pptn;
import com.lyht.business.consumer.hydrologicalData.control.PptnControl;
import com.lyht.business.consumer.hydrologicalData.control.StbprpControl;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.RiverFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.business.consumer.hydrologicalData.service.RiverService;
import com.lyht.business.consumer.hydrologicalData.service.StbprpService;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.bean.SysUser;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.Line;
import com.lyht.util.LineDifference;
import com.lyht.util.Point;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	private PptnFormBean mPptnFormBean=new PptnFormBean();//降水量
	private StbprpFormBean mStbprpFormBean=new StbprpFormBean();//测站
	private ResultFormBean resultFormBean=new ResultFormBean();//计算结果
	@Resource
	private StbprpControl mStbprpControl;//测站
	@Resource
	private ResultControl resultControl;//计算结果
	@Resource
	private PptnControl mPptnControl;//降水量
	@Resource  
	private ResultThridControl  resultThridControl;//第三步的control
	@Resource  
	private ResultFourthControl resultFourthControl;//第四步的control
	@Resource 
	private ResultSecondControl resultSecondControl; //第二步保存流域面积
	
	@Resource private StbprpService mStbprpService; //测站
	@Resource private  ResultService resultService;//产流计算结果
	@Resource private RiverService riverService;//河道水清
	@Resource private ResultSecondService rsService;
	@Resource private ResultThridService resultThridService;//第三步
	@Resource private ResultFourthService resultFourthService; //第四步
	@Resource private ResultFifthService resultFifthService; //第五步
	@Resource private ResultSixthService resultSixService;
	private String quanzhong; //权重
	private String yu;//降雨量
	private String time;//时间
	private String result;//雨面量
	private String czmc;//测站名称
	private String pch; //批次号 时间年月日
	private String stcd; //测站编码
	private String start; //开始时间
	private String end;   //结束时间
	private String beginDate;
	private String endDate;
	private String  STCD;
	private String DATE;
	private Integer JYL; //降雨量
	private double LL;//流量
	private String stationNum;//雨量站个数
	//第三步传过来的数据
	private String LYMJ;
	private String BEGINDATE;//开始时间
	private String ENDDATE;//结束时间
	private String INTERVAL;//时差
	private String   DATA;//数据
	private String username;
	//第五步pa
	private String Em;
	private String paf;
	//第六步传过来的数据
	private String colData;//列数据
	private String rs;//R上
	private String rx; //R下
	private String x ; //x轴
	private String pa; //Pa
	private String stcds;//选择的测站编码
	private String stnms;//选择的测站名称
	private String qzs;//权重
	
	public String getQzs() {
		return qzs;
	}
	public void setQzs(String qzs) {
		this.qzs = qzs;
	}
	public String getStcds() {
		return stcds;
	}
	public void setStcds(String stcds) {
		this.stcds = stcds;
	}
	public String getStnms() {
		return stnms;
	}
	public void setStnms(String stnms) {
		this.stnms = stnms;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	private String P; //Y轴
	private String paData;
	private String zfzstcd;//蒸发站stcd
	private Double Im;//
	private Double b;
	
	private String planCode;//方案编码
	
	private String emstcd;//蒸发关联站码
	
	public String getEmstcd() {
		return emstcd;
	}
	public void setEmstcd(String emstcd) {
		this.emstcd = emstcd;
	}
	public String getZfzstcd() {
		return zfzstcd;
	}
	public void setZfzstcd(String zfzstcd) {
		this.zfzstcd = zfzstcd;
	}
	public Double getIm() {
		return Im;
	}
	public void setIm(Double im) {
		Im = im;
	}
	public Double getB() {
		return b;
	}
	public void setB(Double b) {
		this.b = b;
	}
	public String deleteAllDataResult(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			resultService.deleteAllDataResult(stcd, DATA);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "删除整场洪水计算结果信息出错!");
		}
		CommonFunction.writeResponse(this.getResponse(), JSONObject.fromObject(table));
		return null;
	}
	public String deleteChanliuStep3Result(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			resultService.deleteStep3Result(stcd, pch);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "删除上次计算结果出错!");
		}
		CommonFunction.writeResponse(this.getResponse(), JSONObject.fromObject(table));
		return null;
	}
	public String getStbprpByName(){
		log.info("ChanLiuAction=list:根据测站名称查询测站信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mStbprpControl.getStbprpByNameMes(mStbprpFormBean, mPageResults);
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
		SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		mHashMap.put("mSysStaff", mSysStaff);
	//mHashMap.put("dataList", mPageResults.getResults());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 根据测站名称查询测站信息
	 * @return
	 */
	public String getStbprp() {
		log.info("ChanLiuAction=list:根据测站名称查询测站信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mStbprpControl.getStbprpMesMoHu(mStbprpFormBean, mPageResults);
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
		SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		mHashMap.put("mSysStaff", mSysStaff);
	//mHashMap.put("dataList", mPageResults.getResults());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String getStep1Jyl(){
		List<Map> jylDataList = this.resultService.queryStep1Jyl(stcd, start, end,INTERVAL);
		CommonFunction.writeResponse(getResponse(), JSONArray.fromObject(jylDataList).toString());
		return null;
	}
	/**
	 * 模糊查询
	 * @return
	 */
	public String getStbprpMoHu() {
		log.info("ChanLiuAction=list:根据测站名称模糊查询测站信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		String searchName = this.getRequest().getParameter("searchText");
		mStbprpFormBean.getmStbprpInfoBean().setStnm(searchName);
		mRetMessage= mStbprpControl.getStbprpMesMoHu(mStbprpFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put("dataList", mPageResults.getResults());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String getStbprpMoHu1() {
		log.info("ChanLiuAction=list:111根据测站名称模糊查询测站信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		String searchName = this.getRequest().getParameter("searchText");
		mStbprpFormBean.getmStbprpInfoBean().setStnm(searchName);
		mRetMessage= mStbprpControl.getStbprpMesMoHu1(mStbprpFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put("dataList", mPageResults.getResults());
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
		Result cresult=new Result();
		Result result1=new Result();
		SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		cresult.setYmlTime(time);
		cresult.setPch(pch);
		cresult.setJyl(yu);
		cresult.setStnm(czmc);
		cresult.setQz(quanzhong);
		cresult.setStcd(stcd);
		cresult.setCreateStaff(mSysStaff.getStaffCode());//存入当前用户code
		mRetMessage=resultControl.create(cresult, result1,result,INTERVAL,stcds,stnms,start,end);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		mHashMap.put("pch", pch);		
		mHashMap.put("stcd", stcd);		
		mHashMap.put("ylz", stationNum);		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	
	/**
	 * 获取降水量
	 * @return
	 */
	public String list(){
		log.info("ChanLiuAction=list: 根据测站名称获取降水量列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mStbprpControl.getStbprpMes(mStbprpFormBean, mPageResults);
		mPageResults.getResults();
		String stcd=mStbprpFormBean.getmStbprpInfoBean().getStcd();
		Pptn pptn=new Pptn();
		pptn.setStcd(stcd);
		mPptnFormBean.setmPptnInfoBean(pptn);  
		mRetMessage= mPptnControl.getPptnMes(mPptnFormBean, mPageResults); 	//查
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
	 * 获取计算结果历史记录，数据到前台select
	 * @return
	 */
	public String getSelect() {
		log.info("ChanLiuAction=list: 获取计算结果历史记录");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		resultFormBean.getResultBean().setCreateStaff(mSysStaff.getStaffCode()); //用户编码
		resultFormBean.getResultBean().setStcd(stcd); //测站编码
		mRetMessage= resultControl.getResult(resultFormBean,mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);	
		}else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());	
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	//导出到excel
	public String chanLiuExportExcel() {
		log.info("ChanLiuAction=根据计算结果编号查询计算数据导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		resultFormBean.getResultBean().setPch(pch);//设置批次号
		resultFormBean.getResultBean().setStcd(stcd);
		resultControl.export(resultFormBean,prs,req,res);
		return null;
	}
	//第二步导出到excel
	public String chanLiuExportExcel2() {
		log.info("ChanLiuAction=第二步数据导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		resultFormBean.getResultBean().setPch(pch);//设置批次号
		resultFormBean.getResultBean().setStcd(stcd);
		resultControl.export2(resultFormBean,beginDate,endDate,prs,req,res);
		return null;
	}
	
	public String chanLiuExportExcel3() {
		log.info("ChanLiuAction=第三步数据导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		String llhj = this.getRequest().getParameter("llhj");
		String rs3 = this.getRequest().getParameter("rs3");
		ResultThridFormBean resultThrid=new ResultThridFormBean();
		//计算求和步骤
		resultThrid.getResultThrid().setStcd(stcd);
		resultThrid.getResultThrid().setPch(pch);
		resultControl.export3(resultThrid,llhj,rs3,beginDate,endDate,prs,req,res);
		return null;
	}
	
	public String chanLiuExportExcel4() {
		log.info("ChanLiuAction=第四步数据导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		String llhjdx = this.getRequest().getParameter("llhjdx");
		String rs4 = this.getRequest().getParameter("rs4");
		ResultFourth rFourth=new ResultFourth();
		rFourth.setPch(pch);//设置批次号
		rFourth.setStcd(stcd);
		resultControl.export4(rFourth,llhjdx,rs4,beginDate,endDate,prs,req,res);
		return null;
	}
	public String chanliuStep4ExportExcel4(){
		Hashtable<String,Object> table =new Hashtable<String,Object>();
		try {
			table = resultService.exportChanliuStep4Excel4(pch, DATA, this.getRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "导出excel出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	public String chanLiuExportExcel5() {
		log.info("ChanLiuAction=第5步数据导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		resultFormBean.getResultBean().setPch(pch);//设置批次号
		resultFormBean.getResultBean().setStcd(stcd);
		resultFormBean.getResultBean().setId("5");
		resultControl.export(resultFormBean,prs,req,res);
		return null;
	}
	
	public String chanLiuExportExcel6() {
		log.info("ChanLiuAction=第6步数据导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		String hjp1 = this.getRequest().getParameter("hjp1");
		String hje1 = this.getRequest().getParameter("hje1");
		String hjsdr1 = this.getRequest().getParameter("hjsdr1");
		String hjrg1 = this.getRequest().getParameter("hjrg1");
		String hjrgpx1 = this.getRequest().getParameter("hjrgpx1");
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("P", hjp1);
		map.put("E", hje1);
		map.put("SDRC", hjsdr1);
		map.put("R", hjrg1);
		map.put("RGDX", hjrgpx1);
		map.put("DATE","合计");
		Hashtable table = resultService.queryStep6InitData(stcd,pch,start,end,INTERVAL);
		ResultSixthResult resultBean=(ResultSixthResult) table.get("resultBean");
		Map<String, Object> table2= resultThridService.beanToMap(resultBean);
		resultControl.export6(map,table2,stcd,pch,start,end,prs,req,res);
		return null;
	}
	//第六步单站综合及误差统计导出到excel
	public String chanliu6Excel() {
		log.info("ChanLiuAction=第六步单站综合及误差统计导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		try {
			Hashtable table = resultFourthService.chanliuStep6Search(stcd);
			List list=(List) table.get("cxDataList");
			resultService.chanliu6Excel(list,prs,req,res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public String chanLiuExportExcel7() {
		log.info("ChanLiuAction=计算结果数据导出到excel");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		
		resultControl.export7(stcd,pch,start,end,prs,req,res);
		return null;
	}
	//查询方案列表
	public String getStep5HistoryData(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
			SysUser sysUser = (SysUser) getSession().getAttribute(Constants.SESSION_ACCT);
			if (sysUser.getUserName().equals("admins")) {//如果是管理员，则可以看到所有方案信息，不过滤
				mSysStaff=null;
			}
			table = resultService.queryStep5HistoryData(stcd, pch,mSysStaff);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "初始化数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	public String queryStep5HistoryData(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			table = resultService.queryStep5HistoryData(stcd, pch, start, end, INTERVAL, stcds, stnms, qzs, emstcd);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "初始化数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	//查询第五步日数据
	public String queryStep5DayData(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
			SysUser sysUser = (SysUser) getSession().getAttribute(Constants.SESSION_ACCT);
			if (sysUser.getUserName().equals("admins")) {//如果是管理员，则可以看到所有方案信息，不过滤
				mSysStaff=null;
			}
			table = resultService.queryStep5DayData(stcd, pch,start,end,INTERVAL,mSysStaff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "初始化数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	//查询某个方案的参数值
	public String queryPlanParamValue(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			table = resultService.queryPlanParamValue(planCode);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "获取方案参数数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	public String queryStep1TableDataByStcdAndPch(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			table = resultService.queryStep1TableDataByStcdAndPch(stcd, pch);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "加载数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	public String getStep1TableData(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			table = resultService.queryStep1TableData(stcd, pch, start, end, INTERVAL, stcds, stnms,qzs);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "加载数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	/**
	 * 根据选择的历史记录查找相应的计算结果信息
	 * @return
	 */
	public String getHistory() {
		log.info("ChanLiuAction=list: 根据计算结果编号查询计算数据");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		resultFormBean.getResultBean().setPch(pch);//设置批次号
		resultFormBean.getResultBean().setStcd(stcd);
		mRetMessage= resultControl.getHistory(resultFormBean,mPageResults);
		RetMessage mRetMessage2=new RetMessage();
		PageResults mPageResults2=new PageResults();
		ResultFifth rf=new ResultFifth();
		rf.setPch(pch);
		rf.setStcd(stcd);
		mRetMessage2=resultControl.getFif(rf, mPageResults2);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);	
		}else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());	
			mHashMap.put("row", mPageResults2.getResults());	
		}
		List<HashMap<String, Object>> list=  mPageResults.getResults();
		for(int i=0;i<list.size();i++) {
			 String name=(String) list.get(i).get("STNM");
			 String[] nStrings=name.split(",");
			 if(nStrings.length==1) {
				 mHashMap.put("ylz", 1);
				 break;
			 }else {
				 mHashMap.put("ylz", nStrings.length);
				 break;
			}
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String deleteChanliuStep2Data(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			resultService.deleteChanliuStep2Data(stcd, pch);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "删除数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
	}
	public String queryStep2InitData(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			table = resultService.queryStep2InitData(stcd,pch,start,end,INTERVAL);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "查询初始化数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
	}
	/**
	 * 步骤2提供json1入口 ,左边的table
	 * @return
	 */
	public String step2() {
		log.info("ChanLiuAction=list: 步骤一.2 Table,json1的数据来源");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 0);
		resultFormBean.getResultBean().setStcd(stcd);
		resultFormBean.getResultBean().setPch(pch);
		String jiange=INTERVAL;
		Date date=new Date();
		mRetMessage= resultControl.step2(resultFormBean,start,end,mPageResults);//第二步需要的sql查询方法
		List listdata=mPageResults.getResults();
		if(listdata.size()==0) {//无数据
		}
		mHashMap.put("DATA", listdata);//给DATA添加数据
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		//getResponse().setContentType("json;charset=utf-8");
		return null;
	}
	
	public String saveStep2Result() {
		log.info("ChanLiuAction=list:步骤二.保存流域面积");
		Hashtable<String, Object> table=new Hashtable<String,Object>();
		try {
			ResultSecond resultSecond=rsService.saveResultStep2(stcd, pch, LYMJ,start,end,INTERVAL, DATA);
			table.put("lymj", resultSecond.getLLMJ());
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "保存出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
		
	}
	/**
	 * 第六步关联蒸发站
	 * @return
	 */
	public String queryZfzCombox(){
		Hashtable<String, Object> table=new Hashtable<String,Object>();
		try {
			String searchName = this.getRequest().getParameter("searchText");
			List<Map> mapList = resultService.queryStep6ZfzList(start, end,searchName);
			table.put("dataList", mapList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
	}
	/**
	 * 第五步关联蒸发量
	 * @return
	 */
	public String queryStep5Edata(){
		Hashtable<String, Object> table=new Hashtable<String,Object>();
		try {
			Hashtable<String,String> edata = resultService.queryStep5EdataTable(start, end,stcd,INTERVAL);
			table.put("edata", edata);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "获取日蒸发量数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
	}
	public String queryEdata(){
		Hashtable<String, Object> table=new Hashtable<String,Object>();
		try {
			table = resultService.queryEdata(start, end,stcd,INTERVAL,DATA);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "获取日蒸发量数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
	}
	@SuppressWarnings("unchecked")
	public String chanliuStep3ChartData(){
		SysUser sysUser=(SysUser) getSession().getAttribute(Constants.SESSION_ACCT);
		String name=sysUser.getUserName();
		Hashtable echartTable=new Hashtable();
		try {
			echartTable=resultService.queryChanliuStep3EchartData(stcd,pch,start,end,INTERVAL,name);//流量最大值查询接口
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			echartTable.put("reflag", "0");
			echartTable.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(echartTable);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	/**
	 * JSON2入口 testsyq_1-n.json Echarts图数据来源
	 * @return
	 */
	public String step2chart() {
		log.info("ChanLiuAction=list:步骤一.2 Echarts, json2的数据来源");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 2);
		List<HashMap<String, Object>>dataList =new ArrayList<HashMap<String, Object>>();//DATA
		HashMap<String, Object> map=new HashMap<String, Object>();// DATA 
		map.put("STCD", "");
		map.put("STNM", "");
		map.put("NAME", "流量");
		map.put("TYPE", "line");
		resultFormBean.getResultBean().setStcd(stcd);
		resultFormBean.getResultBean().setPch(pch);
		List list=resultService.maxLL(resultFormBean, start, end);//流量最大值查询接口
		HashMap<String, Object> lmax= (HashMap<String, Object>) list.get(0);
		Object max=  lmax.get("maxLL");
		double llmax=0;
		if(max!=null) {
			 llmax=Double.parseDouble(max.toString());//最大值*2
		}else {
				llmax=0;
		}
		map.put("MAX",	llmax);//流量的最大值
		dataList.add(map);
		resultFormBean.getResultBean().setStcd(stcd);
		resultFormBean.getResultBean().setPch(pch);
		mRetMessage= resultControl.getLiuliang(resultFormBean, start, end, mPageResults);//流量
		List<HashMap<String, Object>> liuLiangList=	mPageResults.getResults();//获得流量的list去遍历封装数据
		HashMap<String, Object> liuLiangmap=new HashMap<String, Object>();
		List<Object[]> newList=new ArrayList<Object[]>();//流量数据的list
		for(int i=0;i<liuLiangList.size();i++) {
			liuLiangmap.put((String) liuLiangList.get(i).get("DATE"), liuLiangList.get(i).get("LL"));//这个map键是时间，值对应的就是流量
			Object [] ma=new Object [2];
			ma[0]=liuLiangList.get(i).get("DATE");//时间
			ma[1]=liuLiangList.get(i).get("LL");//流量
			newList.add(ma);//添加数据
		}
		 Object[] array =newList.toArray();
		map.put("DATA", array); //流量
		HashMap<String, Object> jiangYuMap=new HashMap<String, Object>();//降雨量
		jiangYuMap.put("STCD", "");
		jiangYuMap.put("STNM", "");
		jiangYuMap.put("NAME", "降雨量");
		jiangYuMap.put("TYPE", "bar");
		List ymllist=resultService.maxYml(resultFormBean, start, end);
		HashMap<String, Object> ymllistmax= (HashMap<String, Object>) ymllist.get(0);
		Object JYL=  ymllistmax.get("JYL");
		double maxJyl=0;
		if(JYL!=null) {
			maxJyl=Double.parseDouble(JYL.toString());
		}else {
			maxJyl=0;
		}
		jiangYuMap.put("MAX", maxJyl);//降雨量的最大值
		mRetMessage= resultControl.getyml(resultFormBean, start, end, mPageResults);//降雨量
		List<HashMap<String, Object>> jList=	mPageResults.getResults();
		HashMap<String, Object> jyLmap=new HashMap<String, Object>();
		List<Object[]> newList1=new ArrayList<Object[]>();
		for(int i=0;i<jList.size();i++) {
			jyLmap.put((String) jList.get(i).get("DATE"), jList.get(i).get("JYL"));
			Object [] jy=new Object [2];
			jy[0]=jList.get(i).get("DATE");//时间
			jy[1]=jList.get(i).get("JYL");//降雨量
			newList1.add(jy);
		}
		//JSONObject json2 = JSONObject.fromObject(jyLmap);
		 Object[]  strArray = newList1.toArray();
		jiangYuMap.put("DATA", strArray);//降雨量
		dataList.add(jiangYuMap);
		mHashMap.put("DATA", dataList); //给DATA数据
		//结尾数据
		List<HashMap<String, Object>>exta =new ArrayList<HashMap<String, Object>>();//EXTRADATA
		HashMap<String, Object> map1=new HashMap<String, Object>();//EXTRADATA map
		map1.put("START", "2018-06-05 00:00");
		map1.put("END", "2018-06-06 00:00");
		map1.put("CHARTTITLE", "降雨量流量关系图");
		exta.add(map1);//添加数据
		mHashMap.put("EXTRADATA", exta);
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * 步骤2修改table数据
	 * @return
	 */
	public String updateData() {
		log.info("ChanLiuAction=update:步骤一.2修改table数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		RiverFormBean rb=new RiverFormBean();
		rb.getmRiverInfoBean().setQ(LL);
		rb.getmRiverInfoBean().setStcd(STCD);
		rb.getmRiverInfoBean().setTm(DATE);
		String nm=STCD+DATE.replace("-", "").replace(" ", "").replace(":", "");
		rb.getmRiverInfoBean().setNm(nm);
		if(JYL!=null) {
			resultFormBean.getResultBean().setYml(JYL);
			resultFormBean.getResultBean().setYmlTime(DATE);
		}
		mRetMessage= resultControl.updateTableData(resultFormBean,rb);//修改方法
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 步骤一.3退水曲线json
	 * @return
	 */
	public String  getTsqx() {
		log.info("ChanLiuAction=getTsqx:获取退水曲线");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		TsqxFormBean mTsqxFormBean=new TsqxFormBean ();
		resultFormBean.getResultBean().setStcd(stcd);//测站编码
		//获取退水曲线的数据
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 2);
		List<HashMap<String, Object>> newList1=new ArrayList<HashMap<String, Object>> ();//DATA
		List<HashMap<String, Object>> list=resultService.numHongFeng(resultFormBean);//得到洪峰的值，去重
		mRetMessage=resultControl.getTsqx(mPageResults, resultFormBean, mTsqxFormBean);//查询退水曲线
		List<HashMap<String, Object>> list2=mPageResults.getResults();//退水曲线结果list
		for(int i=0;i<list.size();i++) {//退水曲线的洪峰，遍历之后去结果去匹配
			HashMap< String, Object> dataMap=new HashMap< String, Object>();
			dataMap.put("NAME", list.get(i).get("QM"));
			dataMap.put("MAX","25");
			dataMap.put("TYPE", "line");
			Double qm=	(Double) list.get(i).get("QM"); //得到洪峰
			List list3=new ArrayList();
			for(int j=0;j<list2.size();j++) {
				Double list2qm= (Double) list2.get(j).get("QM");
				if(qm.equals(list2qm)) {//如果洪峰相等 那么就添加相应的流量和时间段到集合里，这个集合是json数组的数据来源
					Object [] data=new Object [2];
					data[0]=list2.get(j).get("T");
					data[1]=list2.get(j).get("Q");
					list3.add(data); //添加到list里
				}
			}
			dataMap.put("DATA", list3);//DATA的数据是list3就是上面添加的T和Q
			newList1.add(dataMap);
		}
		 Object[]  strArray = newList1.toArray();//list转JSON
		 mHashMap.put("DATA", strArray);//将拼装好的Object数组添加到map里
		List<HashMap<String, Object>>exta =new ArrayList<HashMap<String, Object>>();//EXTRADATA
		HashMap<String, Object> map1=new HashMap<String, Object>();//EXTRADATA map
		ResultThridFormBean resultThrid=new ResultThridFormBean();
		resultThrid.getResultThrid().setStcd(stcd);
		SysRole sysRole=(SysRole) getSession().getAttribute(Constants.SESSION_ROLE);
		String name=sysRole.getRoleCode();
		List<Double> tlist1=new ArrayList<Double>();
		List<HashMap<String, Object>> tlist=resultThridService.sd(resultThrid, name);
		for(int i=0;i<tlist.size();i++) {
		 double t=(double) tlist.get(i).get("T");
		 tlist1.add(t);
		}
		map1.put("XDATA", tlist1.toArray());
		map1.put("CHARTTITLE", "退水曲线图");
		exta.add(map1);//添加数据
		mHashMap.put("EXTRADATA", exta);//添加EXTRADATA
		mHashMap.put("x", tlist1.toArray());
		JSONObject json = JSONObject.fromObject(mHashMap);//将hashmap转json
		CommonFunction.writeResponse(getResponse(), json);
		return  null;
	}
	
	/**
	 *步骤3，保存
	 * @return
	 */
	public String saveLineFor3() {
		log.info("ChanLiuAction=saveLineFor3:步骤3保存流量数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		//保存数据步骤
		int lymj=0;
		if(!LYMJ.equals("")) {
			lymj=Integer.parseInt(LYMJ);
		}
		String data=DATA;
		String sc=INTERVAL;
		int shicha=Integer.parseInt(sc);
		List<Double> list1=new ArrayList<Double>();//流量
		List<String> list2=new ArrayList<String>(); //时间
		String  []data1=data.split(",");
		for(int i=0;i<data1.length;i++) {
			if(i%2==0){
				list2.add(data1[i]);//时间
				}else{
					if(Double.parseDouble(data1[i])<0) {//流量如果小于0
						list1.add(0.0);
						continue;
					}else {
						list1.add(Double.parseDouble(data1[i]));
					}
				
				 }
		}
	
		ResultThrid  resultThrid1=new ResultThrid();
		resultThrid1.setStcd(STCD);
		resultThrid1.setPch(pch);
		resultThridService.delResultThrid(resultThrid1);//先删除
		for(int i=0;i<list1.size();i++) {//这个循环执行插入或更新河道水清流量信息
			ResultThrid  resultThrid=new ResultThrid();
			int j=i+1;//后1个数据的下标
			double qt=0;
			if(i<list1.size()-1){
				double q1=list1.get(i);
				double q2=list1.get(j);
				qt=(q1+q2)*shicha*60/2;
			}
			//list3.add(Double.valueOf(String.valueOf(qt))); //Q*T
			resultThrid.setQ(list1.get(i));
			resultThrid.setTm(list2.get(i));
			resultThrid.setStcd(STCD);
			resultThrid.setR(qt/lymj/1000); //每一个R实 
			resultThrid.setQt(qt);
			resultThrid.setPch(pch);
			resultThrid.setInterval(Integer.parseInt(INTERVAL));
			resultThridService.saveResultThrid(resultThrid);//保存第三步的数据
		}
		ResultThridFormBean resultThrid=new ResultThridFormBean();
		resultThrid.getResultThrid().setStcd(STCD);
		resultThrid.getResultThrid().setPch(pch);
		List<HashMap<String, Object>> qtlist=resultThridService.sumQT(resultThrid);//QT合计
		HashMap<String, Object> qt= (HashMap<String, Object>) qtlist.get(0);
		Object sumqt=  qt.get("QT");
		Double sumQt=Double.parseDouble(sumqt.toString());//QT合计
		double qt1=sumQt/lymj/1000;
		double qt2=new BigDecimal(qt1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
		ResultSecond rSecond=new ResultSecond();
		rSecond.setPch(pch);
		rSecond.setSTCD(STCD);
		rSecond.setRs(qt2);
		rSecond.setSqt3(sumQt);
		ResultSecond rSecond2=rsService.saveRs(rSecond);
		mHashMap.put("rs", rSecond2.getRs());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 第三步，数据表格
	 * @return
	 */
	public String qiuHe() {
		log.info("ChanLiuAction=qiuHe:计算求和");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		ResultThridFormBean resultThrid=new ResultThridFormBean();
		//计算求和步骤
		resultThrid.getResultThrid().setStcd(STCD);
		resultThrid.getResultThrid().setPch(pch);
		mRetMessage=resultThridControl.step3(resultThrid, mPageResults);
		List<HashMap<String, Object>> listdata=mPageResults.getResults();
		List<HashMap<String, Object>> listdata1=new 	ArrayList<HashMap<String, Object>>();
	
		for(int i=0;i<listdata.size();i++) {
			listdata1.add(listdata.get(i));
			if(i==listdata.size()-1) {//最后一个的QT""
				HashMap<String , Object> map=new 	HashMap<String , Object> ();
				map.put("SC", listdata.get(i).get("SC"));
				map.put("LL", listdata.get(i).get("LL"));
				map.put("DATE", listdata.get(i).get("DATE"));
				map.put("QT","");
				listdata1.add(map);
			}
		}
		HashMap<String, Object>  sumQ=new HashMap<String, Object>();
		sumQ.put("DATE"," 流量合计");
	/*	sumQ.put("SC",INTERVAL);*/
		List list=resultThridService.sumQ(resultThrid);
		HashMap<String, Object> ll= (HashMap<String, Object>) list.get(0);
		Object sumLL=  ll.get("LL");
		double llmax=Double.parseDouble(sumLL.toString());//流量合计
		/*sumQ.put("LL",	llmax);//流量合计
*/		//** Q*T合计
		List<HashMap<String, Object>> qtlist=resultThridService.sumQT(resultThrid);//QT合计
		
		HashMap<String, Object> qt= (HashMap<String, Object>) qtlist.get(0);
		Object sumqt=  qt.get("QT");
		Double sumQt=Double.parseDouble(sumqt.toString());//QT合计
		sumQ.put("QT",	sumQt);//QT合计
		listdata1.add(sumQ);
		//R实
		double lymj=0;
		if(LYMJ!=null) {
			lymj=Double.parseDouble(LYMJ);
		}
		HashMap<String, Object> rshi=new HashMap<String, Object>();
		rshi.put("DATE"," R实(mm)");
		double qt1=sumQt/lymj/1000;
		double qt2=new BigDecimal(qt1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
		ResultSecond rSecond=new ResultSecond();
		rSecond.setPch(pch);
		rSecond.setSTCD(STCD);
		rSecond.setRs(qt2);
		ResultSecond rSecond2=rsService.saveRs(rSecond);
		mHashMap.put("rs", rSecond2.getRs());
		rshi.put("QT",qt2);
		listdata1.add(rshi);
		mHashMap.put("DATA", listdata1);//给DATA添加数据
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 1);
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	public String queryStepLlechartData(){
		Hashtable<String, Object> table = new Hashtable<String, Object>();
		try {
			table = resultThridService.queryStep4LlEchartData(stcd, pch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "产流计算第四步初始化出错!");
		}
		JSONObject json = JSONObject.fromObject(table);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	/**
	 * 第四步左边的图数据查询 testsyq_1-n2.json
	 * @return
	 */
	public String getStep4() {
		log.info("ChanLiuAction=list:步骤一.4 Echarts, 第四步的数据来源");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "查询数据成功");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 2);
		ResultThridFormBean resultThrid=new ResultThridFormBean();
		resultThrid.getResultThrid().setStcd(stcd);
		resultThrid.getResultThrid().setPch(pch);
		mRetMessage=resultThridControl.getStep4List(resultThrid, mPageResults);
		HashMap<String, Object> step4map=new HashMap<String, Object>();
		List<HashMap<String, Object>> step4List=mPageResults.getResults();//数据库查询的数据结果集
		HashMap<String, Object> liuLiangmap=new HashMap<String, Object>();
		List<Object[]> newList=new ArrayList<Object[]>();//流量数据的list
		String endtime="";
		for(int i=0;i<step4List.size();i++) {
			liuLiangmap.put((String) step4List.get(i).get("DATE"), step4List.get(i).get("LL"));//这个map键是时间，值对应的就是流量
			Object [] ma=new Object [2];
			ma[0]=step4List.get(i).get("DATE");//时间
			endtime=(String) step4List.get(i).get("DATE");//结束时间
			ma[1]=step4List.get(i).get("LL");//流量
			newList.add(ma);//添加数据
		}
		 Object[]  strArray = newList.toArray();
		 step4map.put("DATA", strArray);
		 step4map.put("STCD", stcd);
		 step4map.put("STNM", "");
		 step4map.put("NAME", "流量");
		 step4map.put("TYPE", "line");
		List ymllist=resultThridService.maxQ(resultThrid);
		HashMap<String, Object> ymllistmax= (HashMap<String, Object>) ymllist.get(0);
		Object jylMax=  ymllistmax.get("MAXJYL");
		Object jylmin=  ymllistmax.get("MINJYL");
		double maxQ=0;
		double minQ=0;
		if(jylMax!=null) {
			maxQ=Double.parseDouble(jylMax.toString());//最大值
		}else {
			maxQ=0;
		}
		if(jylmin!=null) {
			minQ=Double.parseDouble(jylmin.toString());//最大值
		}else {
			minQ=0;
		}
		 step4map.put("MAX", maxQ);//最大值
		 step4map.put("MIN", minQ);//最小值
		 List<HashMap<String, Object>> dataList= new ArrayList<HashMap<String, Object>>();//定义一个list
			//List<Object[]> newDataList=new ArrayList<Object[]>();//流量数据的list
		 dataList.add(step4map);//将map添加到List里面
		 Object[]  dataArray = dataList.toArray();
		 mHashMap.put("DATA", dataArray);//要将DATA转化为数组
			HashMap<String, Object> map1=new HashMap<String, Object>();//EXTRADATA map
			map1.put("START", start);
			map1.put("END", endtime);
			map1.put("CHARTTITLE", "洪水过程摘录图");
		mHashMap.put("EXTRADATA", map1);
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	
	/**
	 * 第四部求对数  test_tsd2.json
	 * @return
	 */
	public String getLog() {
		log.info("ChanLiuAction=qiuLog:求对数的方法");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		ResultThridFormBean resultThrid=new ResultThridFormBean();
		resultThrid.getResultThrid().setStcd(stcd);
		resultThrid.getResultThrid().setPch(pch);
		mRetMessage=resultThridControl.getStep4List(resultThrid, mPageResults);
		List<HashMap<String, Object>> step4List=mPageResults.getResults();//数据库查询的数据结果集
		HashMap<String, Object> liuLiangmap=new HashMap<String, Object>();
		List<Object[]> newList=new ArrayList<Object[]>();//流量数据的list
		String endtime="";
		String  min="";
		//min=(String) step4List.get(0).get("LL");
		for(int i=0;i<step4List.size();i++) {
			endtime=(String) step4List.get(i).get("DATE");
			liuLiangmap.put((String) step4List.get(i).get("DATE"), step4List.get(i).get("LL"));//这个map键是时间，值对应的就是流量
			Object [] ma=new Object [2];
			ma[0]=step4List.get(i).get("DATE");//时间
			if(((BigDecimal) step4List.get(i).get("LL")).doubleValue()==0) {
				ma[1]=0;
			}else {
				Object ma1=Math.log(((BigDecimal) step4List.get(i).get("LL")).doubleValue());//流量 求对数
				ma[1]=	new BigDecimal(ma1.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
			}
			newList.add(ma);//添加数据
		}
		 Object[]  strArray = newList.toArray();
		 HashMap<String, Object> dataMap=new HashMap<String, Object>();
		 dataMap.put("DATA", strArray);//时间-对数
		 dataMap.put("STCD", stcd);
		 dataMap.put("STNM", "");
		 dataMap.put("NAME", "对数线");
		 dataMap.put("TYPE", "line");
			List ymllist=resultThridService.maxQ(resultThrid);
			HashMap<String, Object> ymllistmax= (HashMap<String, Object>) ymllist.get(0);
			Object jylMax=  ymllistmax.get("MAXJYL");
			Object jylmin=  ymllistmax.get("MINJYL");
			double maxQ=0;
			double minQ=0;
			if(jylMax!=null) {
				maxQ=Double.parseDouble(jylMax.toString());//最大值
			}else {
				maxQ=0;
			}
			if(jylmin!=null) {
				minQ=Double.parseDouble(jylmin.toString());//最大值
			}else {
				minQ=0;
			}
		dataMap.put("MAX", Math.log(maxQ));//最大值求对数
		dataMap.put("MIN",  Math.log(minQ));//最小值对数
		List<HashMap<String, Object>> dataList=new ArrayList<HashMap<String, Object>>();
		dataList.add(dataMap);
		 Object[]  dataArray = dataList.toArray();
			mHashMap.put("CODE", 1);
			mHashMap.put("MESSAGE", "");
			mHashMap.put("PAGESIZE", 0);
			mHashMap.put("PAGEINDEX", 0);
			mHashMap.put("TOTALAMOUNT", 2);
		mHashMap.put("DATA", dataArray);
		HashMap<String, Object> map1=new HashMap<String, Object>();//EXTRADATA map
		map1.put("START", start);
		map1.put("END", endtime);
		map1.put("CHARTTITLE", "");
		mHashMap.put("EXTRADATA", map1);
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	public String saveStep3Result(){
		String data=DATA;
		Hashtable<String,Object> table = resultFourthService.saveStep3Result(stcd,pch,data);
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	public String saveStep4Data(){
		String data=DATA;
		Hashtable<String,Object> table = resultFourthService.saveStep4Data(stcd,pch,data);
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table).toString());
		return null;
	}
	/**
	 * 保存第四步
	 * @return
	 */
	public String saveLineFor4() {
		log.info("ChanLiuAction=step4:保存选择之后的数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			String data=DATA;
			String sc=INTERVAL;
			int shicha=Integer.parseInt(sc);
			List<Double> list1=new ArrayList<Double>();//流量
			List<String> list2=new ArrayList<String>(); //时间
			List<Double> list3= new ArrayList<Double>();//Q*T
			String  []data1=data.split(",");
			for(int i=0;i<data1.length;i++) {
				if(i%2==0){
					list2.add(data1[i]);//时间
					}else{
						if(Double.parseDouble(data1[i])<0) {//流量如果小于0
							list1.add(0.0);
							continue;
						}else {
							list1.add(Double.parseDouble(data1[i]));
						}
					 }
			}
			ResultFourth  resultFourth1=new ResultFourth();
			resultFourth1.setStcd(STCD);
			resultFourth1.setPch(pch);
			resultFourthService.delResultFourth(resultFourth1);
			for(int i=0;i<list1.size();i++) {//这个循环执行插入或更新河道水清流量信息
				int j=i+1;//后1个数据的下标
				double q1=list1.get(i);
				if(i==list1.size()-1) {
					j=i;
				}
				double q2=list1.get(j);
				double qt=(q1+q2)*shicha*60/2;
				ResultFourth  resultFourth=new ResultFourth();
				resultFourth.setQdx(list1.get(i));
				resultFourth.setTm(list2.get(i));
				resultFourth.setStcd(STCD);
				resultFourth.setQt(qt);
				resultFourth.setPch(pch);
				resultFourth.setInterval(Integer.parseInt(INTERVAL));
					resultFourthService.saveResultFourth(resultFourth);//保存第四步的数据
			}
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "保存失败!");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 第四步table
	 * @return
	 */
	public String getStep4Table() {
		log.info("ChanLiuAction=getStep4Table:第四步下面的table");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		ResultFourthFormBean resultFourth=new ResultFourthFormBean();
		//计算求和步骤
		resultFourth.getResultFourth().setStcd(STCD);
		resultFourth.getResultFourth().setPch(pch);
		mRetMessage=resultFourthControl.getStep4(resultFourth, mPageResults);
		List<HashMap<String, Object>> listdata=mPageResults.getResults();
		List<HashMap<String, Object>> listdata1=new 	ArrayList<HashMap<String, Object>>();
		
		for(int i=0;i<listdata.size();i++) {
			listdata1.add(listdata.get(i));
			if(i==listdata.size()-1) {//最后一个的QT""
				HashMap<String , Object> map=new 	HashMap<String , Object> ();
				map.put("SC", listdata.get(i).get("SC"));
				map.put("LL", listdata.get(i).get("LL"));
				map.put("DATE", listdata.get(i).get("DATE"));
				map.put("QT","");
				listdata1.add(map);
			}
		}
		HashMap<String, Object>  sumQ=new HashMap<String, Object>();
		sumQ.put("DATE"," 流量(地下)合计");
		//sumQ.put("SC",Integer.parseInt(INTERVAL)  *60);
		List list=resultFourthService.sumQdx(resultFourth);
		HashMap<String, Object> ll= (HashMap<String, Object>) list.get(0);
		Object sumLL=  ll.get("LL");
		double llmax=0;
		if(sumLL!=null) {
			llmax=Double.parseDouble(sumLL.toString());//流量合计
		}
	//	sumQ.put("LL",	llmax);//流量合计
		//** Q*T合计
		List qtlist=resultFourthService.sumQT(resultFourth);//QT合计
		HashMap<String, Object> qt= (HashMap<String, Object>) qtlist.get(0);
		Object sumqt=  qt.get("QT");
		double sumQt=0;
		if(sumqt!=null) {
			 sumQt=Double.parseDouble(sumqt.toString());//QT合计
		}
		sumQ.put("QT",	sumQt);//QT合计
		listdata1.add(sumQ);
		//R实
		HashMap<String, Object> rshi=new HashMap<String, Object>();
		rshi.put("DATE"," R实(mm)");
		double lymj=0;
		if(LYMJ!=null) {
			lymj=Double.parseDouble(LYMJ);
		}
		double qt2=new BigDecimal(sumQt/lymj/1000).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
		ResultSecond rSecond=new ResultSecond();
		rSecond.setPch(pch);
		rSecond.setSTCD(STCD);
		rSecond.setRx(qt2);//R下
		ResultSecond rSecond2=rsService.saveRx(rSecond);
		mHashMap.put("rxia", rSecond2.getRx());
		rshi.put("QT",qt2);
		listdata1.add(rshi);
		mHashMap.put("DATA", listdata1);//给DATA添加数据
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 1);
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	public String saveStep5Result(){
		Hashtable<String,Object> table = resultFifthService.saveStep5Result(stcd,pch,DATA);
		JSONObject json = JSONObject.fromObject(table);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	/**
	 * 产流计算第五步 保存数据
	 * @return
	 */
	public String saveStep5() {
		log.info("ChanLiuAction=step5:产流第5步保存");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String [] da=time.split(",");
		String []jyl=yu.split(",");
		String[]num=quanzhong.split(",");
		String [] yStrings=result.split(",");
	String [] eStrings=Em.split(",");
		String []pString=paf.split(","); 
		String [] yml=result.split(",");
	     List <String>list=new ArrayList<String>();//创建一个集合保存降雨量，
	        int x=jyl.length/num.length;
	        for(int i=0;i<x;i++) {
	        	//数组截取，按照每一行几个降雨量截取后放入集合，方便保存
	        	 String[] 	newData = Arrays.copyOfRange(jyl, num.length*i, num.length+num.length*i);
	        	list.add( ArrayUtils.toString(newData, ","));
	        }
		for(int i=0;i<da.length;i++) {
			ResultFifth resultFifth=new ResultFifth();
			resultFifth.setStcd(stcd);
			resultFifth.setDate(da[i]);
			resultFifth.setEm(Double.parseDouble(CommonUtil.trim(eStrings[i])));
			resultFifth.setPa(Double.parseDouble(CommonUtil.trim(pString[i])));
			resultFifth.setPch(pch);
			resultFifth.setQz(quanzhong);//权重
			resultFifth.setStnm(czmc);//测站名称
			resultFifth.setJyl(list.get(i).toString().replace("{", "").replace("}", ""));//每个测站下面的降雨量,去掉大括号
			resultFifth.setInterval(INTERVAL);//时间间隔
			resultFifth.setYml(Double.parseDouble(CommonUtil.trim(yml[i])));
			resultFifthService.saveResutFifth(resultFifth);//保存
		}
      String paz=pString[pString.length-1];//最后一个pa
  	ResultSecond rSecond=new ResultSecond();
	rSecond.setPch(pch);
	rSecond.setSTCD(STCD);
	rSecond.setPa(Double.parseDouble(paz));
	ResultSecond rSecond2=rsService.savePa(rSecond);
	mHashMap.put("rs", rSecond2.getRs());
		ResultFifPa rfPa=new ResultFifPa();
		if(pa!=null&&!pa.equals("")) {
			rfPa.setPa(Double.parseDouble(pa));
			rfPa.setStcd(stcd);
			rfPa.setPch(pch);
			resultFifthService.savePa(rfPa); //保存Pa值
		}else {
			
		}
		mHashMap.put("state", 1);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String deleteStep6Result(){
		Hashtable<String, Object> table = new Hashtable<String,Object>();
		try {
			int result = resultService.deleteStep6Result(stcd, pch);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "删除数据失败!");
		}
		CommonFunction.writeResponse(getResponse(), table);
		return null;
	}
	public String initLoadStep6Data(){
		Hashtable<String, Object> table = new Hashtable<String,Object>();
		try {
			table = resultService.queryStep6InitData(stcd,pch,start,end,INTERVAL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "查询初始化数据失败!");
		}
		CommonFunction.writeResponse(getResponse(), table);
		return null;
	}
	public String queryStep7ResultTableData(){
		List<Map> step7List = resultService.queryStep7ResultData(stcd);
		CommonFunction.writeResponse(getResponse(), JSONArray.fromObject(step7List).toString());
		return null;
	}
	@SuppressWarnings("unchecked")
	public String chanliuStep6Zhcx(){
		Hashtable table = new Hashtable();
		try {
			table = resultFourthService.chanliuStep6Search(stcd);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "综合查询出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
	}
	public String queryStep7ResultData(){
		List<Map> step7List = resultService.queryStep7ResultData(stcd,pch);
		List<HashMap<String, Object>>list=resultService.queryStep7startEnd(stcd,pch);
		ResultThridFormBean resultThrid=new ResultThridFormBean();
		resultThrid.getResultThrid().setStcd(stcd);
		resultThrid.getResultThrid().setPch(pch);
		List ymllist=resultThridService.maxQ(resultThrid);//最大Q
		HashMap<String, Object> ymllistmax= (HashMap<String, Object>) ymllist.get(0);
		Object jylMax=  ymllistmax.get("MAXJYL");
		String start=(String) list.get(0).get("DATE");
		String end=(String) list.get(list.size()-1).get("DATE");
		Object p=list.get(0).get("PA");
		Object rs=list.get(0).get("RS");
		double rs1=Double.parseDouble(rs.toString());
		for(int i=0;i<step7List.size();i++) {
			step7List.get(i).put("START", start);//
			step7List.get(i).put("END", end);
			step7List.get(i).put("Q", jylMax);//Qm
			step7List.get(i).put("PA", p);//Qm
			step7List.get(i).put("RS", rs);//rs
			double rc=	Double.parseDouble(step7List.get(i).get("ERC").toString());
			step7List.get(i).put("SJR", rs1-rc);//△R
			double SJRBRS1=(rs1-rc)/rs1;
			double SJRBRS2=new BigDecimal(SJRBRS1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
			step7List.get(i).put("SJRBRS", SJRBRS2);//△R/RS
		}
		
		CommonFunction.writeResponse(getResponse(), JSONArray.fromObject(step7List).toString());
		return null;
	}
	/**
	 * 产流计算第六步
	 * @return
	 */
	public String step6() {
		log.info("ChanLiuAction=list: 第六步， Table,json1的数据来源");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 0);
		resultFormBean.getResultBean().setStcd(stcd);
		resultFormBean.getResultBean().setPch(pch);
		mRetMessage=resultControl.step6(resultFormBean,start,end,mPageResults);//第六步查询sql
		//mRetMessage= resultControl.step2(resultFormBean,start,end,mPageResults);//第二步需要的sql查询方法
		List listdata=mPageResults.getResults();
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
	public String queryRsffEchartsData(){
		List<Map> rrffList = resultService.queryStep6RrffEcharts(stcd);
		CommonFunction.writeResponse(getResponse(), JSONArray.fromObject(rrffList).toString());
		return null;
	}
	public String calcStep6Data(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		table = resultService.caclStep6Data(stcd, pch, rs, rx, pa, LYMJ, DATA);
		JSONObject json = JSONObject.fromObject(table);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	public String saveCalcServiceResult(){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		table = resultService.saveCalcServiceResult(stcd, pch, rs, rx, pa, LYMJ,zfzstcd,Im,b, DATA);
		JSONObject json = JSONObject.fromObject(table);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	/**
	 * 第六步计算
	 * @return
	 */
	public String step6Reckon() {
		log.info("ChanLiuAction=step6Reckon:产流第六步计算");
		double pa1=0;
		if(pa.equals("")) {
			 pa1=0;
		}else {
			 pa1= Double.parseDouble(pa); //pa的值
		}
		String col= this.getRequest().getParameter("colData"); //列的值
		Object cObject=col;
		JSONArray array=JSONArray.fromObject(col);
		for(int i=0;i<array.size();i++) {
			JSONObject object=array.getJSONObject(i);
			/*if(object.get("P")instanceof Integer&&object.get("P")!=null) {
				Integer pString= (Integer) object.get("P");
			}else {
				double pString= (double) object.get("P");
			}
			if(object.get("EM")instanceof Integer&&object.get("EM")!=null) {
				Integer pString= (Integer) object.get("EM");
			}else {
				double pString= (double) object.get("EM");
			}
			if(object.get("EPE")instanceof Integer&&object.get("EPE")!=null) {
				Integer pString= (Integer) object.get("EPE");
			}else {
				double pString= (double) object.get("EPE");
			}*/
		}
		List ecrList=new ArrayList();  
		String[] pval1=P.split(","); //Y轴数据
		String [] xIntegers1=x.split(","); //x轴数据
		String  [] pval = new String[pval1.length];
		String [] xIntegers=new String[pval1.length]; //
		for(int i =0;i<pval.length;i++) {
			pval[i]=pval1[i];
			xIntegers[i]=xIntegers1[i];
		}
		List<Point> pointList=new ArrayList<Point>();
		for(int i=0;i<pval.length;i++) { //遍历Y轴数据求ER查
			Point point=new Point(Double.parseDouble(xIntegers[i]), Double.parseDouble(pval[i]));
			pointList.add(point);
			Line line=new Line();
			line.setPa(Double.parseDouble(pa)); //pa
			line.setPointList(pointList);
			List<Line> lineList=new ArrayList<Line>();
			lineList.add(line);
		 double xdian=	LineDifference.getX(lineList, Double.parseDouble(pa), Double.parseDouble(pval[i]));
		 ecrList.add(xdian);
		}
		List<Map<String,Object>> mapListJson = (List<Map<String,Object>>)array;
		//	//R改计算
		double[] arr=new double[ecrList.size()]; //创建1个数组，用来做R改大->小的排序
		double rshi=Double.parseDouble(rs);   
		double s=rshi- (double)ecrList.get(ecrList.size()-1);//为R实减去 最后一行值
		int  j=(int)s/ecrList.size(); //得到一个修正值
		for(int i=0;i<ecrList.size();i++) {
			mapListJson.get(i).put("ERC", ecrList.get(i)); //∑R查
			mapListJson.get(i).put("R", (double)ecrList.get(i) -j); //R改
			if(i==0) {
				mapListJson.get(i).put("SDRC", ecrList.get(i)); //时段R查
				arr[0]=(double) ecrList.get(i);
			}else {
				mapListJson.get(i).put("SDRC", (double)ecrList.get(i) -(double)ecrList.get(i-1) ); //时段R查= 后1个减前一个
				mapListJson.get(i).put("R", (double)ecrList.get(i) -(double)ecrList.get(i-1) - j); //R改
				arr[i]=(double)ecrList.get(i) -(double)ecrList.get(i-1) - j;
			}
		}
		double temp;
		  for (int i = 0; i < arr.length; i++) { //排序
	            for (int j1 = i+1; j1 < arr.length; j1++) {
	                if (arr[i] < arr[j1]) {
	                    temp = arr[i];
	                    arr[i] = arr[j1];
	                    arr[j1] = temp;  // 两个数交换位置
	                }
	            }
	        }
		  double sum=0.0;
		  for(int i=0;i<ecrList.size();i++) {
				mapListJson.get(i).put("RGDX", arr[i]); //R改
				sum+=arr[i];
				mapListJson.get(i).put("ER", sum); //R改
		  }
		List epeList=new ArrayList();
		for(int i=0;i<mapListJson.size();i++) {
			
		double  p=Double.valueOf(mapListJson.get(i).get("P").toString());
		String date=	(String) mapListJson.get(i).get("DATE");
		double pe=	Double.valueOf(mapListJson.get(i).get("PE").toString());
		double epe;
		double rc;
		if(i==0) {//第一个
			epe=pe;
		}else {
			epe=pe+Double.valueOf(mapListJson.get(i-1).get("PE").toString());//值为当前的值加上前面一个值
		}
			epeList.add(epe);
			mapListJson.get(i).put("XH", i+1); //∑(P-E雨)
			mapListJson.get(i).put("EPE", epe); //∑(P-E雨)
			mapListJson.get(i).put("PAPE", pa1+epe);//Pa+∑(P-E雨)
		}
	
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mHashMap.put("CODE", "1");
		mHashMap.put("MESSAGE", "0");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 0);
		mHashMap.put("DATA", mapListJson);//给DATA添加数据
		List<HashMap<String, Object>>list1 =new ArrayList<HashMap<String, Object>>();//EXTRADATA
		HashMap<String, Object> listMap=new HashMap<String, Object>();	
		listMap.put("STNM", "站名");
		listMap.put("PCH", "洪号");
		listMap.put("LYMJ", "流域面积");
		list1.add(listMap);//添加数据
		mHashMap.put("EXTRADATA", list1);
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	public String saveStep6Result(){
		Hashtable<String,Object> table = resultSixService.saveStep6ResultData(stcd,pch,DATA);
		JSONObject json = JSONObject.fromObject(table);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	/**
	 * 第六步保存
	 * @return
	 */
	public String step6Save() {
		log.info("ChanLiuAction=step6Save:产流第六步保存");
	 JSONArray  array= JSONArray.fromObject(colData);
	 for(int i=0;i<array.size();i++) {
		 JSONObject jObject=(JSONObject) array.get(i);
		 ResultSixth  rSixth=new ResultSixth();
		 rSixth.setTm((String)jObject.get("DATE"));
		 if(jObject.get("E")instanceof Integer) {
			 rSixth.setEr((Integer)jObject.get("E"));
		 }else {
			 rSixth.setEr((double)jObject.get("E"));
		}
		 if(jObject.get("EPE")instanceof Integer) {
			 rSixth.setEr((Integer)jObject.get("EPE"));
		 }else {
			 rSixth.setEr((double)jObject.get("EPE"));
		}
		 if(jObject.get("ER")instanceof Integer) {
			 rSixth.setEr((Integer)jObject.get("ER"));
		 }else {
			 rSixth.setEr((double)jObject.get("ER"));
		}
		if(jObject.get("ERC")instanceof Integer) {
			 rSixth.setErc((Integer)jObject.get("ERC"));
		}else {
			 rSixth.setErc((double)jObject.get("ERC"));
		}
		if(jObject.get("P")instanceof Integer) {
			 rSixth.setP((Integer)jObject.get("P"));
		}else {
			 rSixth.setP((double)jObject.get("P"));
		}
		if(jObject.get("PAPE")instanceof Integer) {
			 rSixth.setP((Integer)jObject.get("PAPE"));
		}else {
			 rSixth.setP((double)jObject.get("PAPE"));
		}
		 //rSixth.setPape((Integer)jObject.get("PAPE"));
		 rSixth.setPch(pch);
		 if(jObject.get("PE")instanceof Integer) {
			 rSixth.setR((Integer)jObject.get("PE"));
		 }else {
			 rSixth.setR((double)jObject.get("PE"));
		}
		 if(jObject.get("R")instanceof Integer) {
			 rSixth.setR((Integer)jObject.get("R"));
		 }else {
			 rSixth.setR((double)jObject.get("R"));
		}
		if(jObject.get("RGDX")instanceof Integer ) {
			 rSixth.setRgdx((Integer)jObject.get("RGDX"));
		}else {
			 rSixth.setRgdx((double)jObject.get("RGDX"));
		}
		if(jObject.get("SDRC")instanceof Integer) {
			 rSixth.setSdrc((Integer)jObject.get("SDRC"));
		}else {
			 rSixth.setSdrc((double)jObject.get("SDRC"));
		}
		
		 rSixth.setStcd(stcd);
		 resultSixService.saveResultSixth(rSixth);
	 }
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mHashMap.put("MESSAGE", "SUCCESS!");
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	
	}
	//========GET SET

	public String getStart() {
		
		return start;
	}

	

	
//从大到小排列
	public void reverse(double[] nums,int start,int end) {
		Arrays.sort(nums);
		while(start<end) {
			double temp=nums[start];
			nums[start]=nums[end];
			nums[end]=temp;
			start++;
			end--;
		}
		System.out.println(Arrays.toString(nums));
	}


	public int[] StringToInt(String[] arrs){
	    int[] ints = new int[arrs.length];
	    for(int i=0;i<arrs.length;i++){
	        ints[i] = Integer.parseInt(arrs[i]);
	    }
	    return ints;
	}
	

	
	



	public String getEm() {
		return Em;
	}

	public void setEm(String em) {
		Em = em;
	}

	public String getPaf() {
		return paf;
	}

	public void setPaf(String paf) {
		this.paf = paf;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getP() {
		return P;
	}

	public void setP(String p) {
		P = p;
	}

	public String getPaData() {
		return paData;
	}

	public void setPaData(String paData) {
		this.paData = paData;
	}

	public String getLYMJ() {
		return LYMJ;
	}

	public void setLYMJ(String lYMJ) {
		LYMJ = lYMJ;
	}

	public String getColData() {
		return colData;
	}

	public void setColData(String colData) {
		this.colData = colData;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getRx() {
		return rx;
	}

	public void setRx(String rx) {
		this.rx = rx;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public ResultFormBean getResultFormBean() {
		return resultFormBean;
	}

	public void setResultFormBean(ResultFormBean resultFormBean) {
		this.resultFormBean = resultFormBean;
	}

	public String getCzmc() {
		return czmc;
	}

	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getQuanzhong() {
		return quanzhong;
	}

	public void setQuanzhong(String quanzhong) {
		this.quanzhong = quanzhong;
	}

	public String getYu() {
		return yu;
	}

	public void setYu(String yu) {
		this.yu = yu;
	}


	public StbprpFormBean getmStbprpFormBean() {
		return mStbprpFormBean;
	}

	public void setmStbprpFormBean(StbprpFormBean mStbprpFormBean) {
		this.mStbprpFormBean = mStbprpFormBean;
	}

	public PptnFormBean getmPptnFormBean() {
		return mPptnFormBean;
	}

	public void setmPptnFormBean(PptnFormBean mPptnFormBean) {
		this.mPptnFormBean = mPptnFormBean;
	}

	public String getPch() {
		return pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSTCD() {
		return STCD;
	}

	public void setSTCD(String sTCD) {
		STCD = sTCD;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public Integer getJYL() {
		return JYL;
	}

	public void setJYL(Integer jYL) {
		JYL = jYL;
	}

	public double getLL() {
		return LL;
	}

	public void setLL(double lL) {
		LL = lL;
	}

	public String getBEGINDATE() {
		return BEGINDATE;
	}

	public void setBEGINDATE(String bEGINDATE) {
		BEGINDATE = bEGINDATE;
	}

	public String getENDDATE() {
		return ENDDATE;
	}

	public void setENDDATE(String eNDDATE) {
		ENDDATE = eNDDATE;
	}

	public String getINTERVAL() {
		return INTERVAL;
	}

	public void setINTERVAL(String iNTERVAL) {
		INTERVAL = iNTERVAL;
	}

	public String getDATA() {
		return DATA;
	}

	public void setDATA(String dATA) {
		DATA = dATA;
	}

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
	
	
	
}