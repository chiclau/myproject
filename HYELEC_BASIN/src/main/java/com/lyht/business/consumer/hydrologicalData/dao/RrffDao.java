package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Rrff;
import com.lyht.business.consumer.hydrologicalData.formbean.RrffFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RrffDao extends HibernateBaseDao<Rrff>{
	/**
	 * 获取列表数据
	 * */
	public PageResults getRrffListData(RrffFormBean mRrffFormBean) {
		StringBuilder sql = new StringBuilder();
		if(mRrffFormBean.getmRrffInfoBean().getStcd()!=null&&!"".equals(mRrffFormBean.getmRrffInfoBean().getStcd().trim())){
			sql.append(" SELECT a.STCD,a.USERNAME,a.Pa,a.P,a.R,b.STNM FROM ST_JYJLXGT_B a LEFT JOIN ST_STBPRP_B b ON a.STCD = b.STCD WHERE 1=1  ");
			sql.append(" AND a.STCD = '"+mRrffFormBean.getmRrffInfoBean().getStcd()+"' AND a.USERNAME='"+mRrffFormBean.getmRrffInfoBean().getUserName()+"'  ");
		}else{
			sql.append(" SELECT a.STCD,a.USERNAME,b.STNM FROM ST_JYJLXGT_B a LEFT JOIN ST_STBPRP_B b ON a.STCD = b.STCD WHERE 1 =1  ");
			if(mRrffFormBean.getSearchName()!=null&&!"".equals(mRrffFormBean.getSearchName().trim())){
				sql.append(" and b.STNM LIKE '%" + mRrffFormBean.getSearchName().trim() + "%'  ");
			}
			sql.append(" GROUP BY a.STCD,a.USERNAME,b.STNM ");
		}
		return this.findPageByFetchedSql(sql.toString(), null
				,mRrffFormBean.getPageBean().getOffset()
				,mRrffFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据ID获取降雨径流数据
	 * */
	@SuppressWarnings("unchecked")
	public Rrff getRrffInfoById(RrffFormBean mRrffFormBean) {
		StringBuilder sql = new StringBuilder();
		Rrff mRrff = new Rrff();
		sql.append(" SELECT a.STCD,a.USERNAME,a.P,a.Pa,a.R FROM ST_JYJLXGT_B a WHERE 1 = 1 ");
		List<Rrff> mRrffs = null;
		if (mRrffFormBean.getmRrffInfoBean().getStcd() != null
				&& !"".equals(mRrffFormBean.getmRrffInfoBean().getStcd().trim())) {
			sql.append(" AND a.STCD = '" + mRrffFormBean.getmRrffInfoBean().getStcd().trim() + "' ");
		}
		sql.append(" ORDER BY  a.STCD ");
		try {
			mRrffs = this.getSession().createSQLQuery(sql.toString()).addEntity(Rrff.class).list();
			for (int i = 0; i < mRrffs.size(); i++) {
				mRrff = mRrffs.get(0);
			}
			return mRrff;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return mRrff;
	}

	/*
	 * 添加实体对象
	 */
	public void saveRrffInfo(Rrff mRrff) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO [HYELEC_BASIN].[dbo].[ST_JYJLXGT_B] ([STCD], [USERNAME], [Pa], [P], [R]) ");
		sql.append(" VALUES ('"+CommonUtil.trim(mRrff.getStcd())+"',");
		sql.append(" ' "+CommonUtil.trim(mRrff.getUserName())+"','"+CommonUtil.trim(mRrff.getPa())+"',");
		sql.append(" ' "+CommonUtil.trim(mRrff.getP())+"',");
		sql.append(" ' "+CommonUtil.trim(mRrff.getR())+"')");
		this.exectueSQL(sql.toString());
	}
	/*
	 * 修改实体对象
	 */
	public void updateRrffInfo(Rrff mRrff) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_JYJLXGT_B SET STCD = '"+CommonUtil.trim(mRrff.getStcd())+"', USERNAME = '"+CommonUtil.trim(mRrff.getUserName())+ "', ");
		sql.append(" Pa = '"+CommonUtil.trim(mRrff.getPa())+"', P = '"+CommonUtil.trim(mRrff.getP())+"', R = '"+CommonUtil.trim(mRrff.getR())+ " ' ");
		sql.append(" WHERE STCD = '"+CommonUtil.trim(mRrff.getStcd())+"' USERNAME ='"+CommonUtil.trim(mRrff.getUserName())+"'");
		this.exectueSQL(sql.toString());
	}
	
	/*
	 * 根据ID删除
	 */
	public void deletRrffInfoByIds(String stcd, String userName) {
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_JYJLXGT_B WHERE STCD = '"+stcd+"' AND USERNAME='"+userName+"' ");
		this.exectueSQL(sql.toString());
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getRrffListData_export(RrffFormBean mRrffFormBean) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT a.STCD,a.USERNAME,a.Pa,a.P,a.R FROM ST_JYJLXGT_B a WHERE 1=1 ");
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mRrffFormBean.getPageBean().getOffset()
				,mRrffFormBean.getPageBean().getLimit()
				,null);
	}

	/*
	 * 修改之前先删除要修改的数据
	 */
	public void del(RrffFormBean mRrffFormBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ST_JYJLXGT_B WHERE STCD = '"+mRrffFormBean.getmRrffInfoBean().getStcd()+ "' "
				+ "	AND USERNAME='"+mRrffFormBean.getmRrffInfoBean().getUserName()+"' " );
		this.exectueSQL(sql.toString());
	}

	/*
	 * 插入修改的数据
	 */
	public void saveTsqxInfo(RrffFormBean mRrffFormBean, String pa, String p, String r) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO ST_JYJLXGT_B (STCD, USERNAME, Pa, P, R) VALUES  ");
		sql.append("('"+CommonUtil.trim(mRrffFormBean.getmRrffInfoBean().getStcd())+"','"+CommonUtil.trim(mRrffFormBean.getmRrffInfoBean().getUserName())+"',");
		sql.append("'"+Float.parseFloat(pa)+"','"+Float.parseFloat(p)+"',");
		sql.append("'"+Float.parseFloat(r)+"')");
		this.exectueSQL(sql.toString());		
	}
}
