package com.lyht.business.consumer.hydrologicalData.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.business.consumer.hydrologicalData.control.RunoffControl;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/runoff")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("/runoff")
public class RunoffAction extends BaseLyhtAction {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(RunoffAction.class);
	@Resource private RunoffControl mRunoffControl;

	/**
	 * 雨径流曲线列表
	 * */
	public String list(){
		try {
			List list = mRunoffControl.getBasinProvince();
			JSONArray json = JSONArray.fromObject(list);
			CommonFunction.writeResponse(getResponse(), json.toString());
		} catch (Exception e) {
			log.error("查询雨径流曲线图失败！",e);
		}
		return null;
	}
}
