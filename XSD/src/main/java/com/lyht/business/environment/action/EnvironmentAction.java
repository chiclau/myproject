package com.lyht.business.environment.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.business.baseinfo.service.AreaService;
import com.lyht.business.baseinfo.service.BaseInfoService;
import com.lyht.business.environment.service.EnvironmentService;
import com.lyht.business.system.bean.SysRole;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONObject;

/**
 * 环保统计Action
 * @author 刘魁
 *@创建时间 2018/10/08
 */
@Namespace("/hbtj")
@Controller
@Scope("prototype")
@SuppressWarnings("all")
@Action("/hbtj")
public class EnvironmentAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("EnvironmentAction");
	@Resource private EnvironmentService enService;
	@Resource private AreaService areaService;
	@SuppressWarnings({ "rawtypes", "unchecked", "null" })
	public String queryHbtjData(){
		log.info("EnvironmentAction=list:环保统计，长江经济带");
		String xzqhdm =this.getRequest().getParameter("xzlyqh");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		try {	
			mHashMap =enService.queryHuanBao(xzqhdm);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	public String queryHbtjData1(){
		String xzqhdm =this.getRequest().getParameter("xzlyqh");
		String sheng = this.getRequest().getParameter("sheng");
		String shi = this.getRequest().getParameter("shi");
		String xian = this.getRequest().getParameter("xian");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		try {	
			mHashMap =enService.queryHuanBao1(sheng,shi,xian);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		log.info("EnvironmentAction=list:环保统计:省"+sheng+",市："+shi+"，县："+xian+"");
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	public String getZtree() {
		log.info("BaseInfoAction=list:环保统计-获取ztree");
		String xzqhdm =this.getRequest().getParameter("xzlyqh");
		String pid =this.getRequest().getParameter("parentId");
		//String xzqhdm="";
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		try {	
			List list=areaService.getCj(pid);
			mHashMap.put("treeData", list);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	/**
	 * 环保统计图表切换功能
	 * @return
	 */
	public String echartsAndTable(){
		log.info("BaseInfoAction=echartsAndTable:环保统计图表切换功能");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		try {	
			List<Map> list=enService.echartsAndTable(tableId,arr);
			mHashMap.put("data", list);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	
	private String tableId; //加载的是哪个表格的数据
	private String arr;	//传来的地区数组
	
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}
}
