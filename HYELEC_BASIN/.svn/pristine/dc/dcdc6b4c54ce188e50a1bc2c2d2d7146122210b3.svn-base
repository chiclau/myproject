package com.lyht.business.consumer.hydrologicalData.control;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.business.consumer.hydrologicalData.service.RunoffService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RunoffControl {

	@Resource private RunoffService mRunoffService;

	public List getBasinProvince() {
		return mRunoffService.getRunoffListData();
	}
}
