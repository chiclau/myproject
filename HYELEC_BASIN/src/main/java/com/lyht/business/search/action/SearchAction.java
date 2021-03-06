package com.lyht.business.search.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.control.PptnControl;
import com.lyht.business.consumer.hydrologicalData.control.StbprpControl;
import com.lyht.business.consumer.hydrologicalData.control.ZqrlControl;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.ZqrlFormBean;
import com.lyht.business.consumer.hydrologicalData.service.PptnService;
import com.lyht.business.consumer.hydrologicalData.service.ZqrlService;
import com.lyht.business.search.formBean.SearchFormBean;
import com.lyht.business.search.service.SearchService;
import com.lyht.business.system.control.EnnmcdControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *作者： liuamang
 *脚本日期:2018年5月23日  
 *说明: 地图查询方法
*/

@Namespace("/search")
@Results({ 
	 @Result(name = "station_view", location = "/business/search/station_view.jsp"),
})
@Controller
@Scope("prototype")
@Action("search")
@SuppressWarnings("rawtypes")
public class SearchAction  extends BaseLyhtAction{

	private static Logger log = Logger.getLogger(SearchAction.class);
	private StbprpFormBean formBean = new StbprpFormBean();
	private SearchFormBean searchFormBean = new SearchFormBean();
    private String stcd;
    private String startTime;
    private String  endTime;
    private String sjType;
	//水情
	@Resource private PptnControl pControl;
	//测站
	@Resource private StbprpControl stControl;
	//流域河流
	@Resource private EnnmcdControl mEnnmcdControl;
	//水位流量
	@Resource private ZqrlControl mZqrlControl;
	@Resource private SearchService searchService;
	@Resource private PptnService mPptnService;
	@Resource
	private ZqrlService mZqrlService;
	
	
	/** 查询实时雨情
	 * @return
	 */
	public String searchRain(){
		try {
			//默认查询条件
//			if(CommonUtil.getLength(CommonUtil.trim(searchFormBean.getStartTime())) <=0){
//				searchFormBean.setStartTime(DateUtil.addDay(DateUtil.getDate(), -7) +" 00:00:00");
//			}
//			if(CommonUtil.getLength(CommonUtil.trim(searchFormBean.getEndTime())) <=0){
//				searchFormBean.setEndTime(DateUtil.getDate() +" 23:59:59");
//			}


			//查询时段 (时)
			List list = pControl.getRealTimeRain(searchFormBean);
			JSONArray json =JSONArray.fromObject(list);
			CommonFunction.writeResponse(getResponse(), json.toString());
		} catch (Exception e) {
			log.error("查询实时雨情数据失败！",e);
		}
		return null;
	}
	
	/**
	 * CreateBy :chengdw
	 * Date:2018-06-06
	 * Func:获得省或者水流域数据
	 * */
	public String serchProvinceBasin(){
		try {
			List list = pControl.getBasinProvince(searchFormBean);
			JSONArray json = JSONArray.fromObject(list);
			CommonFunction.writeResponse(getResponse(), json.toString());
		} catch (Exception e) {
			log.error("查询区域或者省数据失败！",e);
		}
		return null;
	}
	
	/**
	 * CreateBy :chengdw
	 * Date:2018-06-07
	 * Func:获得实时水情数据
	 * */
	public String serchRealTimeWaterSituation(){
		try{
			List list = pControl.getRealTimeWaterInformation(searchFormBean);
			JSONArray json = JSONArray.fromObject(list);
			CommonFunction.writeResponse(getResponse(), json.toString());
		} catch (Exception e) {
			log.error("查询实时水情数据失败！",e);
		}
		return null;
		
	}
	/**
	 * CreateBy :chengdw
	 * Date:2018-06-05
	 * Func:获得实时雨情数据
	 * */
	public String serchRainInformation(){
		try{
			List list = pControl.getRealTimeRainInformation(searchFormBean);
			JSONArray json = JSONArray.fromObject(list);
			CommonFunction.writeResponse(getResponse(), json.toString());
		} catch (Exception e) {
			log.error("查询实时雨情数据失败！",e);
		}
		return null;
	}
	public String searchMapStationByName(){
		String searchText = this.getRequest().getParameter("searchText");
		List<Map> mapList = this.searchService.queryCezhan(searchText);
		CommonFunction.writeResponse(getResponse(), JSONArray.fromObject(mapList).toString());
		return null;
	}
	public String searchMapStation(){
		String searchText = this.getRequest().getParameter("searchText");
		List<Map> mapList = this.searchService.queryCezhan(searchText);
		CommonFunction.writeResponse(getResponse(), JSONArray.fromObject(mapList).toString());
		return null;
	}
	/** 查询测站信息
	 * @return
	 */
	public String searchStation(){
		try {
			List list = stControl.queryStbprpList(formBean);
			JSONArray json =JSONArray.fromObject(list);
			CommonFunction.writeResponse(getResponse(), json.toString());
		} catch (Exception e) {
			log.error("查询实时雨情数据失败！",e);
		}
		return null;
	}
	
	/** 测站基本信息显示
	 * @return
	 */  
	public String stationView(){
		try {
			searchFormBean.setSbprp(stControl.getObjectByStcd_(CommonUtil.trim(searchFormBean.getStcd())));
		} catch (Exception e) {
			log.error("测站基本信息加载失败！",e);
		}
		return "station_view";
	}
	
	/** 查询加载流域信息
	 * @return
	 */
	public String searchLyTree(){
		try {
			
			List list = mEnnmcdControl.searcLyTree("");
			JSONArray json =JSONArray.fromObject(list);
			CommonFunction.writeResponse(getResponse(), json.toString());
			System.out.println(json.toString());
		} catch (Exception e) {
			log.error("加载流域树形错误。",e);
		}
		return null;
	}
	//数据查询
	public String sjcx() {
		log.info("SearchAction=list: 数据查询雨量,水位流量的数据来源");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		PptnFormBean mPptnFormBean=new PptnFormBean();
		mPptnFormBean.getmPptnInfoBean().setStcd(stcd);
		mPptnFormBean.setStartTime(startTime);
		mPptnFormBean.setEndTime(endTime);
		if(sjType.equals("ZZ")||sjType.equals("PP")||sjType.equals("RR")||sjType.equals("DD")) {//如果测站类型是雨量站，水文站，展示雨量数据
			mRetMessage=pControl.getSearchData(mPptnFormBean, mPageResults);
		}else if(sjType.equals("ZS")||sjType.equals("ZB")||sjType.equals("ZQ")) {//如果是水位站，则显示水位流量的数据
			ZqrlFormBean zf=new ZqrlFormBean();
			zf.setIds(stcd);
			mRetMessage=mZqrlControl.getSwllData(zf, mPageResults);
		}
		
		mHashMap.put("CODE", "1");
		mHashMap.put("PAGESIZE", 0);
		mHashMap.put("PAGEINDEX", 0);
		mHashMap.put("TOTALAMOUNT", 0);
		List listdata=mPageResults.getResults();
		if(listdata.size()==0) {//无数据
			mHashMap.put("MESSAGE", "暂无数据");
		}
		mHashMap.put("DATA", listdata);//给DATA添加数据
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		//getResponse().setContentType("json;charset=utf-8");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String searchMapData(){
		Hashtable table = new Hashtable();
		PageResults pageResults;
		try {
			String searchType = this.getRequest().getParameter("searchType");
			String pageNo=this.getRequest().getParameter("pageIndex");
			String pageSize=this.getRequest().getParameter("pageSize");
			if(CommonUtil.trim(pageNo).length()<1){
				pageNo="1";
			}
			if(CommonUtil.trim(pageSize).length()<1){
				pageSize="15";
			}
			pageResults = new PageResults();
			if(CommonUtil.trim(searchType).equals("jyl")) {//如果测站类型是雨量站，水文站，展示雨量数据
				pageResults=mPptnService.querySearchMapData(stcd, startTime, endTime, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			}else if(CommonUtil.trim(searchType).equals("ll")) {//如果是水位站，则显示水位流量的数据
				pageResults=mZqrlService.querySearchMapData(stcd, startTime, endTime, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			}
			table.put("CODE", 1);
			table.put("MESSAGE", "获取数据成功!");
			table.put("DATA", pageResults.getResults());
			table.put("TOTALAMOUNT", pageResults.getTotalCount());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("CODE", 0);
			table.put("MESSAGE", "获取数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(table));
		return null;
	}
	
	
	public String getSjType() {
		return sjType;
	}

	public void setSjType(String sjType) {
		this.sjType = sjType;
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

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public StbprpFormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(StbprpFormBean formBean) {
		this.formBean = formBean;
	}

	public SearchFormBean getSearchFormBean() {
		return searchFormBean;
	}

	public void setSearchFormBean(SearchFormBean searchFormBean) {
		this.searchFormBean = searchFormBean;
	}

	
}
