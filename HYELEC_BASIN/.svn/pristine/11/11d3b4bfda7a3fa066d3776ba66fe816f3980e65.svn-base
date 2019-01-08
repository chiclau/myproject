package com.lyht.business.analysisCalculation.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.StPptnDayR;
import com.lyht.business.analysisCalculation.formbean.StPptnDayRFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class StPptnDayRDao extends HibernateBaseDao<StPptnDayR> {

	public int deleteByIds(String nms){
		StringBuffer sql=new StringBuffer("delete from ST_PPTN_DAY_R where NM IN ('"+nms+"')");
		return this.excuteSql(sql.toString(), new Object[]{});
	}
	/**
	 * 获取列表数据
	 * */
	public PageResults getStPptnDayRListData(StPptnDayRFormBean stPptnDayRFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT dayev.NM as NM,dayev.STCD AS STCD,dayev.TM AS TM,dayev.DYP AS DYP,b.STNM AS STNM FROM ST_PPTN_DAY_R AS dayev ");
		sql.append(" LEFT JOIN ST_STBPRP_B AS b ON b.STCD = dayev.STCD WHERE 1=1 ");
		//模糊查询
		if(stPptnDayRFormBean.getSearchName() != null && !"".equals(stPptnDayRFormBean.getSearchName().trim())){
			sql.append(" and b.STNM LIKE '%" + stPptnDayRFormBean.getSearchName().trim() + "%'  ");
		}
		//根据测站查询
		if(stPptnDayRFormBean.getStcd() != null && !"".equals(stPptnDayRFormBean.getStcd().trim())){
			sql.append(" and  b.STCD='"+stPptnDayRFormBean.getStcd()+" '");
		}
		//添加开始查询时间
		if(CommonUtil.getLength(CommonUtil.trim(stPptnDayRFormBean.getStartTime())) > 0){
			sql.append(" and dayev.TM >= '" + stPptnDayRFormBean.getStartTime() + "'  ");
		}
		//添加结束查询时间
		if(CommonUtil.getLength(CommonUtil.trim(stPptnDayRFormBean.getEndTime())) > 0){
			sql.append(" and dayev.TM <= '" + stPptnDayRFormBean.getEndTime() + "'  ");
		}
		sql.append(" ORDER BY dayev.STCD,dayev.TM ");
		System.out.println(sql.toString());
		int pageNo=stPptnDayRFormBean.getPageBean().getLimit()>0?stPptnDayRFormBean.getPageBean().getOffset()/stPptnDayRFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		stPptnDayRFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,stPptnDayRFormBean.getPageBean().getPageNo()
				,stPptnDayRFormBean.getPageBean().getLimit()
				,null);
	}
	
}
