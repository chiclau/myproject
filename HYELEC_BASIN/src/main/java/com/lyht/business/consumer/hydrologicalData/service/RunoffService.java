package com.lyht.business.consumer.hydrologicalData.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.consumer.hydrologicalData.dao.RunoffDao;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class RunoffService{

	@Resource
	private RunoffDao mRunoffDao;
	
	/**
	 *	获取曲线数据
	 * */
	@SuppressWarnings("unchecked")
	public List<Map> getRunoffListData() {
		//获取数据
		List<Map> list = mRunoffDao.getRunoffListData();
		HashSet<Object> hashSet = new HashSet<>();
		//拿到Pa的值放入set中去重复
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			hashSet.add(map.get("Pa"));
		}
		//将set转成array
		Object[] array = hashSet.toArray();
		//存储结果用的集合
		ArrayList RunoffListData = new ArrayList();
		//循环获取array的值
		for (int i = 0; i < array.length; i++) {
			Map map2 = new HashMap<>();
			//将相同的Pa下对应的P和R的值放入集合中
			ArrayList RList = new ArrayList();
			ArrayList PList = new ArrayList();
			for (int j = 0; j < list.size(); j++) {
				Map map = list.get(j);
				if (array[i].equals(map.get("Pa"))) {
					//封装结果集
					map2.put("Pa", array[i]);
					PList.add(map.get("P"));
					RList.add(map.get("R"));
					map2.put("P", PList);
					map2.put("R", RList);
				}
			}
			RunoffListData.add(map2);
		}
		return RunoffListData;
	}
}
