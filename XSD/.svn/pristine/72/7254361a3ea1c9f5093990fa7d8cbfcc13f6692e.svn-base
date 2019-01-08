package com.lyht.business.baseinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.baseinfo.dao.AreaDao;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class AreaService {
	@Resource
	private AreaDao areaDao;
	
	public List<Map> getData(String pid) {
		return areaDao.getData(pid);
	}
	
	public List<Map> getCj(String pid) {
		List <Map>list=new ArrayList<Map>();
		list.addAll(areaDao.getCj(pid));//添加市信息
		//list.addAll(areaDao.getCjxian(pid));//添加县的信息
		return list;
	}

}
