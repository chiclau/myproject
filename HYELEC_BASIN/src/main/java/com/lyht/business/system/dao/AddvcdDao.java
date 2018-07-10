package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Addvcd;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.formbean.AddvcdFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class AddvcdDao extends HibernateBaseDao<Addvcd>{

	/**
	 * 获取根节点数据
	 * */
	public PageResults getListRootData(AddvcdFormBean mAddvcdFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.ADDVCD,A.NAME,(CASE WHEN (A.PADDVCD='0' OR A.PADDVCD IS NULL) THEN '' ELSE A.PADDVCD END) AS PADDVCD,");
		sql.append("(SELECT (CASE WHEN COUNT(B.ADDVCD)>0 THEN 1 ELSE 0 END) FROM SD_ADDVCD_DIC B WHERE B.PADDVCD=A.ADDVCD) AS ISPARENT ");
		sql.append(" FROM SD_ADDVCD_DIC A WHERE A.PADDVCD=0 ORDER BY A.ADDVCD");
	    return this.findPageByFetchedSql(sql.toString(), null
	    	,mAddvcdFormBean.getPageBean().getOffset()
			,mAddvcdFormBean.getPageBean().getLimit()
			,null);
	}
	
	/**
	 * 获取行政区域代码列表数据
	 * */
	public PageResults getAddvcdListData(AddvcdFormBean mAddvcdFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrAddvcd(mAddvcdFormBean);
		sql.append("SELECT A.ADDVCD,A.NAME,(CASE WHEN (A.PADDVCD='0' OR A.PADDVCD IS NULL) THEN '' ELSE A.PADDVCD END) AS PADDVCD,");
		sql.append("(SELECT (CASE WHEN COUNT(B.ADDVCD)>0 THEN 1 ELSE 0 END) FROM SD_ADDVCD_DIC B WHERE B.PADDVCD=A.ADDVCD) AS ISPARENT ");
		sql.append(" FROM SD_ADDVCD_DIC A WHERE 1=1");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY A.ADDVCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mAddvcdFormBean.getPageBean().getOffset()
				,mAddvcdFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据区域代码获取行政区域信息
	 * */
	@SuppressWarnings("unchecked")
	public Addvcd getAddvcdInfoByAddvcd(String addvcd){
		Addvcd mAddvcd=new Addvcd();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.ADDVCD,A.NAME,(CASE WHEN (A.PADDVCD='0' OR A.PADDVCD IS NULL) THEN '' ELSE A.PADDVCD END) AS PADDVCD,");
		sql.append("(SELECT (CASE WHEN COUNT(B.ADDVCD)>0 THEN 1 ELSE 0 END) FROM SD_ADDVCD_DIC B WHERE B.PADDVCD=A.ADDVCD) AS ISPARENT ");
		sql.append(" FROM SD_ADDVCD_DIC A WHERE A.ADDVCD='"+addvcd+"'");
		List<Addvcd> mAddvcdList=null;
		try{
			mAddvcdList=this.getSession().createSQLQuery(sql.toString()).addEntity(Addvcd.class).list();
			for(int i=0;i<mAddvcdList.size();i++){
				mAddvcd=mAddvcdList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mAddvcd;
	}
	
	/**
	 * 根据属性及属性值查找对象实体
	 * */
	public Addvcd getAddvcdInfoByProp(String PropName,Object PropValue){
		return this.findByObject(PropName, PropValue);
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveAddvcdInfo(Addvcd mAddvcd){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SD_ADDVCD_DIC (ADDVCD,NAME,PADDVCD) VALUES ");
		sql.append(" ('"+mAddvcd.getAddvcd()+"','"+mAddvcd.getName()+"','0')");
		this.exectueSQL(sql.toString());
	}
	/**
	 * 增加子节点
	 * */
	public void saveAddvcdInfo_(Addvcd mAddvcd){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SD_ADDVCD_DIC (ADDVCD,NAME,PADDVCD) VALUES ");
		sql.append(" ('"+mAddvcd.getAddvcd()+"','"+mAddvcd.getName()+"','"+mAddvcd.getPaddvcd()+"')");
		this.exectueSQL(sql.toString());
	}
	/**
	 * 修改实体对象
	 * */
	public void updateAddvcdInfo(Addvcd mAddvcd){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SD_ADDVCD_DIC SET ADDVCD='"+mAddvcd.getAddvcd()+"',NAME='"+mAddvcd.getName()+"',");
		sql.append("PADDVCD='"+mAddvcd.getPaddvcd()+"' WHERE ADDVCD='"+mAddvcd.getAddvcd()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 通过省份编码加载市区数据
	 * */
	public List<Map> loadCityData(String prov){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ADDVCD,NAME,PADDVCD FROM SD_ADDVCD_DIC ");
		if(null==prov ||"".equals(prov)){
			sql.append("WHERE PADDVCD!=0 ");
		}else{
			sql.append("WHERE PADDVCD='"+prov+"' ");
		}
		sql.append("ORDER BY ID");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据主键Addvcd获取行政区域代码信息
	 * */
	@SuppressWarnings("unchecked")
	public Addvcd getAddvcdInfoById(String addvcd){
		Addvcd mAddvcd=new Addvcd();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ADDVCD,NAME,PADDVCD FROM SD_ADDVCD_DIC WHERE ADDVCD='"+addvcd+"'");
		List<Addvcd> list=null;
		try{
			list=this.getSession().createSQLQuery(sql.toString()).addEntity(Addvcd.class).list();
			if(list.size()>0){
				mAddvcd=list.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mAddvcd;
	}
	
	/**
	 * 根据行政代码删除节点数据
	 * */
	public boolean removeAddvcdInfoByAddvcd(Addvcd mAddvcd,String addvcd){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		if(!"".equals(CommonUtil.trim(mAddvcd.getPaddvcd()))){
			sql.append("DELETE FROM SD_ADDVCD_DIC WHERE ADDVCD = '"+addvcd+"'");
		}else{
			addvcd=addvcd.substring(0, 2);
			sql.append("DELETE FROM SD_ADDVCD_DIC WHERE ADDVCD LIKE '"+addvcd+"%'");
		}
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据条件查询行政区代码
	 * */
	private String spliceStrAddvcd(AddvcdFormBean mAddvcdFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mAddvcdFormBean){
			if(CommonUtil.trim(mAddvcdFormBean.getSearchName()).length()>0){
				sql.append("AND ((ADDVCD LIKE '%"+CommonUtil.trim(mAddvcdFormBean.getSearchName())+"%') ");
				sql.append("OR (NAME LIKE '%"+CommonUtil.trim(mAddvcdFormBean.getSearchName())+"%') ");
				sql.append("OR (PADDVCD LIKE '%"+CommonUtil.trim(mAddvcdFormBean.getSearchName())+"%')) ");
			}
			if(null!=mAddvcdFormBean.getmAddvcdInfoBean()){
				if(CommonUtil.trim(mAddvcdFormBean.getmAddvcdInfoBean().getAddvcd()).length()>0){
					sql.append(" AND ADDVCD LIKE '"+CommonUtil.trim(mAddvcdFormBean.getmAddvcdInfoBean().getAddvcd().substring(0, 2))+"%'");
				}
				if(CommonUtil.trim(mAddvcdFormBean.getmAddvcdInfoBean().getName()).length()>0){
					sql.append(" AND NAME LIKE '%"+CommonUtil.trim(mAddvcdFormBean.getmAddvcdInfoBean().getName())+"%'");
				}
				if(CommonUtil.trim(mAddvcdFormBean.getmAddvcdInfoBean().getPaddvcd()).length()>0){
					sql.append(" AND PADDVCD LIKE '%"+CommonUtil.trim(mAddvcdFormBean.getmAddvcdInfoBean().getPaddvcd())+"%'");
				}
			}
		}
		return sql.toString();
	}
	
}
