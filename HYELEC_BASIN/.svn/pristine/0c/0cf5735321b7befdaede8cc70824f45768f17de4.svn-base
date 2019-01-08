package com.lyht.business.search.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.search.dao.SearchDao;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SearchService {

	@Resource private SearchDao searchDao;
	
	public List<Map> queryCezhan(String searchText){
		return this.searchDao.queryCezhan(searchText);
	}
	public List<Map> queryCezhanByStnm(String searchText){
		return this.searchDao.queryCezhanByStnm(searchText);
	}
}
