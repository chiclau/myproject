package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.formbean.EnnmcdFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class EnnmcdDao extends HibernateBaseDao<Ennmcd>{
	
	/** 查询流域树形 
	 * @param pid 
	 * @return
	 */
	public List<Map> searchLyTree(String pid){
		StringBuffer sb = new StringBuffer("SELECT ID,RVCD AS RVCD,  "
				+ "ISNULL(PRVCD,0)  AS PID,RVNM AS NAME,PATH,PAIXU,isnull(LGTD,'') as LGTD,isnull(LTTD,'') as LTTD  "
				+ "FROM SD_LYSXJGX WHERE 1=1  order by Path");
		return this.createSQLQuerybyMap(sb.toString());
	}
	
	
	/**
	 * 获取流域水系代码列表数据
	 * */
	public PageResults getEnnmcdListData(EnnmcdFormBean mEnnmcdFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrEnnmcd(mEnnmcdFormBean);
		sql.append("SELECT ID,RVCD AS RVCD,");
		sql.append("PRVCD AS PID,RVNM AS NAME,PATH,PAIXU,LGTD,LTTD ");
		sql.append("FROM SD_LYSXJGX WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY PATH");
		return this.findPageByFetchedSql(sql.toString(), null
				,mEnnmcdFormBean.getPageBean().getOffset()
				,mEnnmcdFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 获取根节点数据
	 * */
	public PageResults getListRootData(EnnmcdFormBean mEnnmcdFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,RVCD AS RVCD,");
		sql.append("PRVCD AS PID,RVNM AS NAME,PATH,PAIXU,LGTD,LTTD ");
		sql.append("FROM SD_LYSXJGX WHERE PRVCD IS NULL OR PRVCD ='' ORDER BY PATH ");
	    return this.findPageByFetchedSql(sql.toString(), null
			,mEnnmcdFormBean.getPageBean().getOffset()
			,mEnnmcdFormBean.getPageBean().getLimit()
			,null);
	}
	
	/**
	 * 根据实体属性及值获取实体
	 * */
	public Ennmcd getEnnmcdInfoByProp(String PropName,Object PropValue){
		return this.findByObject(PropName, PropValue);
	}
	
	/**
	 * 根据流域编号获取流域信息
	 * */
	public List<Map> getEnnmcdInfoByRvcd(String rvcd){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,RVCD,PRVCD,RVNM,PATH,PAIXU,LGTD,LTTD ");
		sql.append("FROM SD_LYSXJGX WHERE RVCD LIKE '"+rvcd+"%' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveEnnmcdInfo(Ennmcd mEnnmcd){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SD_LYSXJGX (RVCD,RVNM,PRVCD,PATH,PAIXU,LGTD,LTTD) VALUES ");
		sql.append("('"+mEnnmcd.getRvcd()+"','"+mEnnmcd.getRvnm()+"',");
		sql.append("'"+mEnnmcd.getPrvcd()+"','"+mEnnmcd.getPath()+"',");
		sql.append("'"+mEnnmcd.getPaixu()+"','"+mEnnmcd.getLgtd()+"','"+mEnnmcd.getLttd()+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateEnnmcdInfo(Ennmcd Ennmcd){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SD_LYSXJGX SET RVCD='"+Ennmcd.getRvcd()+"',RVNM='"+Ennmcd.getRvnm()+"',");
		sql.append("PRVCD='"+Ennmcd.getPrvcd()+"',PATH='"+Ennmcd.getPath()+"',PAIXU='"+Ennmcd.getPaixu()+"',");
		sql.append("LGTD='"+Ennmcd.getLgtd()+"',LTTD='"+Ennmcd.getLttd()+"' WHERE RVCD='"+Ennmcd.getRvcd()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据主键ID获取流域水系代码信息
	 * */
	@SuppressWarnings("unchecked")
	public Ennmcd getEnnmcdInfoById(String rvcd){
		Ennmcd mEnnmcd=new Ennmcd();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM SD_LYSXJGX WHERE RVCD='"+rvcd+"'");
		List<Ennmcd> list=null;
		try{
			list=this.getSession().createSQLQuery(sql.toString()).addEntity(Ennmcd.class).list();
			if(list.size()>0){
				mEnnmcd=list.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mEnnmcd;
	}
	
	/**
	 * 根据主键ID获取流域水系代码信息
	 * */
	@SuppressWarnings("unchecked")
	public Ennmcd getEnnmcdInfoById_(String prvcd){
		Ennmcd mEnnmcd=new Ennmcd();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM SD_LYSXJGX WHERE PRVCD='"+prvcd+"'");
		List<Ennmcd> list=null;
		try{
			list=this.getSession().createSQLQuery(sql.toString()).addEntity(Ennmcd.class).list();
			if(list.size()>0){
				mEnnmcd=list.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mEnnmcd;
	}
	
	/**
	 * 根据流域编号(1,2,3,4,5)删除多个对象
	 * */
	public boolean deleteEnnmcdInfoByRvcd(Ennmcd mEnnmcd){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SD_LYSXJGX WHERE PATH like '"+mEnnmcd.getPath()+"%'");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	/**
	 * 根据人员编号获取人员信息
	 * */
	@SuppressWarnings("unchecked")
	public Ennmcd getEnnmcdInfoByBasinCode(String basinCode){
		 Ennmcd mEnnmcd=new Ennmcd();
		 StringBuilder sql=new StringBuilder();
		 sql.append("SELECT * FROM SD_LYSXJGX WHERE RVCD='"+basinCode+"'");
		 List<Ennmcd> mEnnmcdList=null;
		 try{
			 mEnnmcdList=this.getSession().createSQLQuery(sql.toString()).addEntity(Ennmcd.class).list();
		     if(mEnnmcdList.size()>0){
		    	 mEnnmcd=mEnnmcdList.get(0);
		     }
		 }catch (Exception e) {
			e.getStackTrace();
		 }
		 return mEnnmcd;
	}
	
	/**
	 * 根据条件查询流域水系代码
	 * */
	private String spliceStrEnnmcd(EnnmcdFormBean mEnnmcdFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mEnnmcdFormBean){
			if(CommonUtil.trim(mEnnmcdFormBean.getSearchName()).length()>0){
				sql.append("AND ((RVCD LIKE '%"+CommonUtil.trim(mEnnmcdFormBean.getSearchName())+"%') ");
				sql.append("OR (RVNM LIKE '%"+CommonUtil.trim(mEnnmcdFormBean.getSearchName())+"%')) ");
			}
			if(null!=mEnnmcdFormBean.getmEnnmcdInfoBean()){
				if(CommonUtil.trim(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvcd()).length()>0){
					sql.append(" AND PATH LIKE '"+CommonUtil.trim(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvcd())+"%'");
				}
				if(CommonUtil.trim(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvnm()).length()>0){
					sql.append(" AND RVNM LIKE '%"+CommonUtil.trim(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvnm())+"%'");
				}
			}
		}
		return sql.toString();
	}
	
}
