package com.lyht.business.baseinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.baseinfo.bean.Area;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class AreaDao extends HibernateBaseDao< Area>{
	public List<Map> getData(String pid) {
		StringBuilder sql = new StringBuilder();
		if(pid==null) {
			pid="0";
		}
		sql.append(" SELECT id,name,codeID,parentID FROM Area ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 长江经济带到市
	 * @param pid
	 * @return
	 */
	public List<Map> getCj(String pid) {
		StringBuilder sql = new StringBuilder();
		if(pid==null) {
			pid="0";
		}
		sql.append(" SELECT * FROM Area WHERE name='长江经济带'   or parentID='2' ");
		sql.append(" or parentID like '31%' ");
		sql.append(" or parentID like '32%' ");
		sql.append(" or parentID like '33%' ");
		sql.append(" or parentID like '34%' ");
		sql.append(" or parentID like '36%' ");
		sql.append(" or parentID like '42%' ");
		sql.append(" or parentID like '43%' ");
		sql.append(" or parentID like '50%' ");
		sql.append(" or parentID like '51%' ");
		sql.append(" or parentID like '52%' ");
		sql.append(" or parentID like '53%' ORDER BY codeID ASC ");
		
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 长江经济带到县
	 * @param pid
	 * @return
	 */
	public List<Map> getCjxian(String pid) {
		StringBuilder sql = new StringBuilder();
		if(pid==null) {
			pid="0";
		}
		sql.append(" SELECT * FROM Area WHERE  parentID in(");
		sql.append(" SELECT codeID FROM Area) AND  codeID != '2'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
}
