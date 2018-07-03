package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Rsvr;
import com.lyht.business.consumer.hydrologicalData.formbean.RsvrFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.Randomizer;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RsvrDao extends HibernateBaseDao<Rsvr>{

	/**
	 * 获取列表数据
	 * */
	public PageResults getRsvrListData(RsvrFormBean mRsvrFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT rsvr.STCD,rsvr.TM,rsvr.RZ,rsvr.INQ,rsvr.W,rsvr.OTQ,rsvr.RWCHRCD, ");
		sql.append("rsvr.RWPTN,rsvr.INQDR,rsvr.MSQMT,b.STNM AS STNM FROM ST_RSVR_R AS rsvr ");
		sql.append("LEFT JOIN ST_STBPRP_B AS b ON b.STCD = rsvr.STCD WHERE 1=1 ");
		//模糊查询
		if(mRsvrFormBean.getSearchName() != null && !"".equals(mRsvrFormBean.getSearchName().trim())){
			sql.append(" and b.STNM LIKE '%" + mRsvrFormBean.getSearchName().trim() + "%'  ");
			String searchName = mRsvrFormBean.getSearchName();
			if(searchName.contains("落")){
				sql.append(" or RWPTN = '"+ 4 +"'  ");
			}
			if(searchName.contains("涨")){
				sql.append(" or RWPTN = '"+ 5 +"'  ");
			}
			if(searchName.contains("平")){
				sql.append(" or RWPTN = '"+ 6 +"'  ");
			}
			if(searchName.contains("干涸")){
				sql.append(" or RWCHRCD = '"+ 1 +"'  ");
			}
			if(searchName.contains("断流")){
				sql.append(" or RWCHRCD = '"+ 2 +"'  ");
			}
			if(searchName.contains("流向不定")){
				sql.append(" or RWCHRCD = '"+ 3 +"'  ");
			}
			if(searchName.contains("逆流")){
				sql.append(" or RWCHRCD = '"+ 4 +"'  ");
			}
			if(searchName.contains("起涨")){
				sql.append(" or RWCHRCD = '"+ 5 +"'  ");
			}
			if(searchName.contains("洪峰")){
				sql.append(" or RWCHRCD = '"+ 6 +"'  ");
			}
			if(searchName.contains("水电厂发电流量")){
				sql.append(" or RWCHRCD = '"+ 7 +"'  ");
			}
			if(searchName.contains("一般情况")){
				sql.append(" or RWCHRCD = '"+ 8 +"'  ");
			}
		}
		//根据测站查询
		if(mRsvrFormBean.getStcd() != null && !"".equals(mRsvrFormBean.getStcd().trim())){
			sql.append(" and  b.STCD='"+mRsvrFormBean.getStcd()+" '");
		}
		//添加开始查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mRsvrFormBean.getStartTime())) > 0){
			sql.append(" and rsvr.TM >= '" + mRsvrFormBean.getStartTime() + "'  ");
		}
		//添加结束查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mRsvrFormBean.getEndTime())) > 0){
			sql.append(" and rsvr.TM <= '" + mRsvrFormBean.getEndTime() + "'  ");
		}
		sql.append(" ORDER BY rsvr.STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mRsvrFormBean.getPageBean().getOffset()
				,mRsvrFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getRsvrListData_export(RsvrFormBean mRsvrFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrRsvr(mRsvrFormBean);
		sql.append("SELECT STCD,TM,RZ,INQ,W,OTQ,RWCHRCD,RWPTN,INQDR,MSQMT FROM ST_RSVR_R WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mRsvrFormBean.getPageBean().getOffset()
				,mRsvrFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取日蒸发量实体
	 * */
	public PageResults getRsvrInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,TM,RZ,INQ,W,OTQ,RWCHRCD,RWPTN,INQDR,MSQMT FROM ST_RSVR_R WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND STCD IN ('"+ids+"')");
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	public void deletRsvrInfoByIds(String ids,String ids_){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_RSVR_R WHERE STCD = '"+ids+"' AND TM='"+ids_+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveRsvrInfo(Rsvr mRsvr){
		String sql=execQL(mRsvr); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateRsvrInfo(Rsvr mRsvr){
		String sql = updateById(mRsvr);//执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public Rsvr getRsvrInfoById(RsvrFormBean mRsvrFormBean){
		Rsvr mRsvr=new Rsvr();
		String sql=execById(mRsvrFormBean);//执行sql语句操作
		List<Rsvr> mRsvrList=null;
		try{
			mRsvrList=this.getSession().createSQLQuery(sql.toString()).addEntity(Rsvr.class).list();
			for(int i=0;i<mRsvrList.size();i++){
				mRsvr=mRsvrList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mRsvr;
	}
	
	
	/**
	 * 根据条件查询 
	 * */
	private String spliceStrRsvr(RsvrFormBean mRsvrFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mRsvrFormBean){
			if(CommonUtil.trim(mRsvrFormBean.getSearchName()).length()>0){
				sql.append("AND ((rsvr.STCD LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%') ");
				sql.append("OR (rsvr.TM LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%') ");
				sql.append("OR (rsvr.RZ LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%') ");
				sql.append("OR (rsvr.INQ  LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%') ");
				sql.append("OR (rsvr.OTQ  LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%') ");
				sql.append("OR (rsvr.RWCHRCD  LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%') ");
				sql.append("OR (rsvr.RWPTN  LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM  LIKE '%"+CommonUtil.trim(mRsvrFormBean.getSearchName())+"%')) ");
			}
			if(null!=mRsvrFormBean.getmRsvrInfoBean()){
				if(CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getStcd()).length()>0){
					sql.append(" AND rsvr.STCD = '"+CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getTm()).length()>0){
					sql.append(" AND rsvr.TM = '"+CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getTm())+"'");
				}
				if(CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getRwchrcd()).length()>0){
					sql.append(" AND rsvr.RWCHRCD = '"+CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getRwchrcd())+"'");
				}
				if(CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getRwptn()).length()>0){
					sql.append(" AND rsvr.RWPTN = '"+CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getRwptn())+"'");
				}
				/*if(CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getPtno()).length()>0){
					sql.append(" AND PTNO = "+CommonUtil.trim(mRsvrFormBean.getmRsvrInfoBean().getPtno()));
				}*/
			}
		}
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(Rsvr mRsvr){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_RSVR_R (STCD,TM,RZ,INQ,W,OTQ,RWCHRCD,RWPTN,INQDR,MSQMT) ");
		sql.append("VALUES ('"+CommonUtil.trim(mRsvr.getStcd())+"',");
		sql.append("'"+DateUtil.ConvertTimestamp(mRsvr.getTm())+"','"+CommonUtil.trim(mRsvr.getRz())+"',");
		sql.append("'"+CommonUtil.trim(mRsvr.getInq())+"','"+CommonUtil.trim(mRsvr.getW())+"',");
		sql.append("'"+CommonUtil.trim(mRsvr.getOtq())+"','"+CommonUtil.trim(mRsvr.getRwchrcd())+"',");
		sql.append("'"+CommonUtil.trim(mRsvr.getRwptn())+"','"+CommonUtil.trim(mRsvr.getInqdr())+"',");
		sql.append("'"+CommonUtil.trim(mRsvr.getMsqmt())+"')");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById(RsvrFormBean mRsvrFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,TM,RZ,INQ,W,OTQ,RWCHRCD,RWPTN,INQDR,");
		sql.append("MSQMT FROM ST_RSVR_R WHERE STCD='"+mRsvrFormBean.getmRsvrInfoBean().getStcd()+"' ");
		sql.append("AND TM='"+mRsvrFormBean.getmRsvrInfoBean().getTm()+"'");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行修改语句
	 * */
	private String updateById(Rsvr mRsvr){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_RSVR_R SET STCD = '"+CommonUtil.trim(mRsvr.getStcd())+"', TM = '"+CommonUtil.trim(mRsvr.getTm())+"', ");
		sql.append(" RZ = '"+CommonUtil.trim(mRsvr.getRz())+"', INQ = '"+CommonUtil.trim(mRsvr.getInq())+"', ");
		sql.append(" W = '"+CommonUtil.trim(mRsvr.getW())+"', OTQ = '"+CommonUtil.trim(mRsvr.getOtq())+"', ");
		sql.append(" RWCHRCD = '"+CommonUtil.trim(mRsvr.getRwchrcd())+"', RWPTN = '"+CommonUtil.trim(mRsvr.getRwptn())+"', ");
		sql.append(" INQDR = '"+CommonUtil.trim(mRsvr.getInqdr())+"', MSQMT = '"+CommonUtil.trim(mRsvr.getMsqmt())+"' ");
		sql.append(" WHERE STCD = '"+CommonUtil.trim(mRsvr.getStcd())+"' AND TM='"+CommonUtil.trim(mRsvr.getTm())+"'");
		return sql.toString();
	}
}
