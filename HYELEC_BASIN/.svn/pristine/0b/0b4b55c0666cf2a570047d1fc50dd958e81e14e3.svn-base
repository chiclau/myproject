package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Dayev;
import com.lyht.business.consumer.hydrologicalData.formbean.DayevFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class DayevDao extends HibernateBaseDao<Dayev>{

	/**
	 * 获取列表数据
	 * */
	public PageResults getDayevListData(DayevFormBean mDayevFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrDayev(mDayevFormBean);
		sql.append("SELECT dayev.STCD,dayev.TM,dayev.EPTP,dayev.DYE,b.STNM FROM ST_DAYEV_R AS dayev ");
		sql.append(" LEFT JOIN ST_STBPRP_B AS b ON b.STCD = dayev.STCD WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY dayev.STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mDayevFormBean.getPageBean().getOffset()
				,mDayevFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getDayevListData_export(DayevFormBean mDayevFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrDayev(mDayevFormBean);
		sql.append("SELECT STCD,TM,EPTP,DYE FROM ST_DAYEV_R WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mDayevFormBean.getPageBean().getOffset()
				,mDayevFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	public void deletDayevInfoByIds(String ids,String ids_){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_DAYEV_R WHERE STCD = '"+ids+"' AND TM='"+ids_+"' ");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveDayevInfo(Dayev mDayev){
		String sql=execQL(mDayev); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateDayevInfo(Dayev mDayev){
		String sql = updateById(mDayev);//执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public Dayev getDayevInfoById(DayevFormBean mDayevFormBean){
		Dayev mDayev=new Dayev();
		String sql=execById(mDayevFormBean);//执行sql语句操作
		List<Dayev> mDayevList=null;
		try{
			mDayevList=this.getSession().createSQLQuery(sql.toString()).addEntity(Dayev.class).list();
			for(int i=0;i<mDayevList.size();i++){
				mDayev=mDayevList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mDayev;
	}
	
	
	/**
	 * 根据条件查询 
	 * */
	private String spliceStrDayev(DayevFormBean mDayevFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mDayevFormBean){
			if(CommonUtil.trim(mDayevFormBean.getSearchName()).length()>0){
				sql.append("AND ((dayev.STCD LIKE '%"+CommonUtil.trim(mDayevFormBean.getSearchName())+"%') ");
				sql.append("OR (dayev.TM LIKE '%"+CommonUtil.trim(mDayevFormBean.getSearchName())+"%') ");
				sql.append("OR (dayev.EPTP LIKE '%"+CommonUtil.trim(mDayevFormBean.getSearchName())+"%') ");
				sql.append("OR (dayev.DYE  LIKE '%"+CommonUtil.trim(mDayevFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM  LIKE '%"+CommonUtil.trim(mDayevFormBean.getSearchName())+"%')) ");
			}
			if(null!=mDayevFormBean.getmDayevInfoBean()){
				if(CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getStcd()).length()>0){
					sql.append(" AND dayev.STCD = '"+CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getTm()).length()>0){
					sql.append(" AND dayev.TM = '"+CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getTm())+"'");
				}
				if(CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getEptp()).length()>0){
					sql.append(" AND dayev.EPTP = "+CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getEptp()));
				}
				/*if(CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getPtno()).length()>0){
					sql.append(" AND PTNO = "+CommonUtil.trim(mDayevFormBean.getmDayevInfoBean().getPtno()));
				}*/
			}
		}
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(Dayev mDayev){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_DAYEV_R (STCD,TM,EPTP,DYE) ");
		sql.append("VALUES ('"+CommonUtil.trim(mDayev.getStcd())+"',");
		sql.append("'"+DateUtil.ConvertTimestamp(mDayev.getTm())+"','"+CommonUtil.trim(mDayev.getEptp())+"',");
		sql.append("'"+CommonUtil.trim(mDayev.getDye())+"')");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById(DayevFormBean mDayevFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,TM,EPTP,DYE FROM ST_DAYEV_R ");
		sql.append("WHERE STCD='"+mDayevFormBean.getmDayevInfoBean().getStcd()+"' ");
		sql.append("AND TM='"+mDayevFormBean.getmDayevInfoBean().getTm()+"' ");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行修改语句
	 * */
	private String updateById(Dayev mDayev){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_DAYEV_R SET STCD = '"+CommonUtil.trim(mDayev.getStcd())+"', TM = '"+CommonUtil.trim(mDayev.getTm())+"', ");
		sql.append(" EPTP = '"+CommonUtil.trim(mDayev.getEptp())+"', DYE = '"+CommonUtil.trim(mDayev.getDye())+"' ");
		sql.append("WHERE STCD = '"+CommonUtil.trim(mDayev.getStcd())+"' AND TM='"+CommonUtil.trim(mDayev.getTm())+"'");
		return sql.toString();
	}
}
