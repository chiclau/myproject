package com.lyht.business.search.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.business.consumer.hydrologicalData.control.PptnControl;
import com.lyht.business.consumer.hydrologicalData.control.StbprpControl;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.business.search.formBean.SearchFormBean;
import com.lyht.business.system.control.EnnmcdControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;
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
	//水情
	@Resource private PptnControl pControl;
	//测站
	@Resource private StbprpControl stControl;
	//流域河流
	@Resource private EnnmcdControl mEnnmcdControl;
	
	
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
	
	/** 查询测站信息
	 * @return
	 */
	public String searchStation(){
		try {
			List list = stControl.getStbprpList(formBean);
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
