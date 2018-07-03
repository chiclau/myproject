package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.consumer.hydrologicalData.bean.Runoff;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RunoffDao extends HibernateBaseDao<Runoff>{

	/**
	 * 获取曲线数据
	 * */
	public List<Map> getRunoffListData(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,USERNAME,Pa,P,R FROM ST_JYJLXGT_B WHERE 1 = 1 ORDER BY STCD,Pa ");
		return this.createSQLQuerybyMap(sql.toString());
	}
}
