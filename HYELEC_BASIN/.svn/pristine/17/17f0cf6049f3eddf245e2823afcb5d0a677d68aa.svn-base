package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Tsqx;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.Randomizer;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class TsqxDao extends HibernateBaseDao<Tsqx>{

	/**
	 * 获取退水曲线列表数据
	 * */
	public PageResults getTsqxListData(TsqxFormBean mTsqxFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrTsqx(mTsqxFormBean);
		sql.append("SELECT A.STCD,B.STNM,A.USERNAME,A.QM,A.Q,A.T FROM ST_TSQX_B A ");
		sql.append("LEFT JOIN ST_STBPRP_B B on A.STCD=B.STCD WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY A.STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mTsqxFormBean.getPageBean().getOffset()
				,mTsqxFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取退水曲线实体
	 * */
	public PageResults getTsqxInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,USERNAME,QM,Q,T FROM ST_TSQX_B WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND STCD IN ('"+ids+"')");
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID删除退水曲线实体
	 * */
	public void deletTsqxInfoByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_TSQX_B WHERE STCD IN ('"+ids+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveTsqxInfo(Tsqx mTsqx){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_TSQX_B (STCD,USERNAME,QM,Q,T) VALUES ");
		sql.append("('"+CommonUtil.trim(mTsqx.getStcd())+"','"+CommonUtil.trim(mTsqx.getUsername())+"',");
		sql.append("'"+CommonUtil.trim(mTsqx.getQm())+"','"+CommonUtil.trim(mTsqx.getQ())+"',");
		sql.append("'"+CommonUtil.trim(mTsqx.getT())+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateTsqxInfo(TsqxFormBean mTsqxFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_TSQX_B SET STCD='"+mTsqxFormBean.getIds()+"',");
		sql.append("USERNAME='"+mTsqxFormBean.getmTsqxInfoBean().getUsername()+"',");
		sql.append("QM='"+mTsqxFormBean.getmTsqxInfoBean().getQm()+"',");
		sql.append("Q='"+mTsqxFormBean.getmTsqxInfoBean().getQ()+"',");
		sql.append("T='"+mTsqxFormBean.getmTsqxInfoBean().getT()+"'");
		sql.append("WHERE STCD='"+mTsqxFormBean.getIds()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public Tsqx getTsqxInfoById(String stcd){
		Tsqx mTsqx=new Tsqx();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT A.STCD,B.STNM,A.USERNAME,A.QM,A.Q,A.T FROM ST_TSQX_B A ");
		sql.append("LEFT JOIN ST_STBPRP_B B on A.STCD=B.STCD WHERE A.STCD='"+stcd+"' ");
		List<Tsqx> mTsqxList=null;
		try{
			mTsqxList=this.getSession().createSQLQuery(sql.toString()).addEntity(Tsqx.class).list();
			for(int i=0;i<mTsqxList.size();i++){
				mTsqx=mTsqxList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mTsqx;
	}
	
	/**
	 * 根据条件查询退水曲线
	 * */
	private String spliceStrTsqx(TsqxFormBean mTsqxFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mTsqxFormBean){
			if(CommonUtil.trim(mTsqxFormBean.getSearchName()).length()>0){
				sql.append("AND ((A.STCD LIKE '%"+CommonUtil.trim(mTsqxFormBean.getSearchName())+"%') ");
				sql.append("OR (A.USERNAME LIKE '%"+CommonUtil.trim(mTsqxFormBean.getSearchName())+"%') ");
				sql.append("OR (A.QM LIKE '%"+CommonUtil.trim(mTsqxFormBean.getSearchName())+"%') ");
				sql.append("OR (A.Q LIKE '%"+CommonUtil.trim(mTsqxFormBean.getSearchName())+"%') ");
				sql.append("OR (A.T LIKE '%"+CommonUtil.trim(mTsqxFormBean.getSearchName())+"%')) ");
			}
			if(null!=mTsqxFormBean.getmTsqxInfoBean()){
				if(CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getStcd()).length()>0){
					sql.append(" AND A.STCD = '"+CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getUsername()).length()>0){
					sql.append(" AND A.USERNAME = '"+CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getUsername())+"'");
				}
				/*if(CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getQm()).length()>0){
					sql.append(" AND A.QM = '"+CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getQm())+"'");
				}
				if(CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getQ()).length()>0){
					sql.append(" AND A.Q = '"+CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getQ())+"'");
				}
				if(CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getT()).length()>0){
					sql.append(" AND A.T = '"+CommonUtil.trim(mTsqxFormBean.getmTsqxInfoBean().getT())+"'");
				}*/
			}
		}
		return sql.toString();
	}
	
}
