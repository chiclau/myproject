package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.River;
import com.lyht.business.consumer.hydrologicalData.formbean.RiverFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RiverDao extends HibernateBaseDao<River>{

	public List<Map> queryRiverEchartData(String stcd,String beginTime,String endTime){
		StringBuffer sql = new StringBuffer("SELECT A.*,CONVERT(CHAR(16),  A.TM, 120) AS DT FROM ST_RIVER_R A"
				+ " WHERE A.STCD='"+stcd+"' AND CONVERT(CHAR(16),  A.TM, 120) >='"+beginTime+"' AND CONVERT(CHAR(16),  A.TM, 120)<='"+endTime+"' order by CONVERT(CHAR(16),  A.TM, 120) asc ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 获取列表数据
	 * */
	public PageResults getRiverListData(RiverFormBean mRiverFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrRiver(mRiverFormBean);
		sql.append("SELECT river.STCD AS STCD,"
				+ "river.TM AS TM,river.Z AS Z,river.Q AS Q,"
				+ "river.XSA AS XSA,river.XSAVV AS XSAVV,river.XSMXV AS XSMXV, ");
		sql.append(" river.FLWCHRCD AS FLWCHRCD,"
				+ "river.WPTN AS WPTN,river.MSQMT AS MSQMT,"
				+ "river.MSAMT AS MSAMT,river.MSVMT AS MSVMT,"
				+ "b.STNM AS STNM FROM ST_RIVER_R AS river ");
		sql.append(" LEFT JOIN ST_STBPRP_B AS b ON b.STCD = river.STCD WHERE 1=1 ");
		if(mRiverFormBean.getSearchName() != null && !"".equals(mRiverFormBean.getSearchName().trim())){
			sql.append(" and b.STNM LIKE '%" + mRiverFormBean.getSearchName().trim() + "%'  ");
			String searchName = mRiverFormBean.getSearchName();
			if(searchName.contains("落")){
				sql.append(" or WPTN = '"+ 4 +"'  ");
			}
			if(searchName.contains("涨")){
				sql.append(" or WPTN = '"+ 5 +"'  ");
			}
			if(searchName.contains("平")){
				sql.append(" or WPTN = '"+ 6 +"'  ");
			}
			if(searchName.contains("干涸")){
				sql.append(" or FLWCHRCD = '"+ 1 +"'  ");
			}
			if(searchName.contains("断流")){
				sql.append(" or FLWCHRCD = '"+ 2 +"'  ");
			}
			if(searchName.contains("流向不定")){
				sql.append(" or FLWCHRCD = '"+ 3 +"'  ");
			}
			if(searchName.contains("逆流")){
				sql.append(" or FLWCHRCD = '"+ 4 +"'  ");
			}
			if(searchName.contains("起涨")){
				sql.append(" or FLWCHRCD = '"+ 5 +"'  ");
			}
			if(searchName.contains("洪峰")){
				sql.append(" or FLWCHRCD = '"+ 6 +"'  ");
			}
			if(searchName.contains("水电厂发电流量")){
				sql.append(" or FLWCHRCD = '"+ 7 +"'  ");
			}
			if(searchName.contains("一般情况")){
				sql.append(" or FLWCHRCD = '"+ 8 +"'  ");
			}
		}
		//根据测站查询
		if(mRiverFormBean.getStcd() != null && !"".equals(mRiverFormBean.getStcd().trim())){
			sql.append(" and  b.STCD='"+mRiverFormBean.getStcd()+" '");
		}
		//添加开始查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mRiverFormBean.getStartTime())) > 0){
			sql.append(" and river.TM >= '" + mRiverFormBean.getStartTime() + "'  ");
		}
		//添加结束查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mRiverFormBean.getEndTime())) > 0){
			sql.append(" and river.TM <= '" + mRiverFormBean.getEndTime() + "'  ");
		}
		sql.append(" ORDER BY river.STCD,river.TM");
		System.out.println(sql.toString());
		int pageNo=mRiverFormBean.getPageBean().getLimit()>0?mRiverFormBean.getPageBean().getOffset()/mRiverFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mRiverFormBean.getPageBean().setPageNo(pageNo);	
		return this.findPageByFetchedSql(sql.toString(), null
				,mRiverFormBean.getPageBean().getPageNo()
				,mRiverFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getRiverListData_export(RiverFormBean mRiverFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrRiver(mRiverFormBean);
		sql.append(" SELECT a.STCD,b.STNM,a.TM,a.Z,a.Q,a.XSA,a.XSAVV,a.XSMXV, CASE WHEN a.FLWCHRCD = '1' THEN '干涸' WHEN a.FLWCHRCD = '2' THEN '断流' WHEN a.FLWCHRCD = '3' THEN '流向不定'  ");
		sql.append(" WHEN a.FLWCHRCD = '4' THEN '逆流' WHEN a.FLWCHRCD = '5' THEN '起涨' WHEN a.FLWCHRCD = '6' THEN '洪峰' WHEN a.FLWCHRCD = 'P' THEN '水电厂发电流量' WHEN a.FLWCHRCD = ' ' THEN '一般情况' END AS FLWCHRCD, ");
		sql.append(" CASE WHEN a.WPTN = '4' THEN '落' WHEN a.WPTN = '5' THEN '涨' WHEN a.WPTN = '6' THEN '平' END AS WPTN,CASE WHEN a.MSQMT = '1' THEN '水位流量关系曲线' WHEN a.MSQMT = '2' THEN '浮标及溶液测流法' WHEN a.MSQMT = '3' THEN '流速仪及量水建筑物'  ");    
		sql.append(" WHEN a.MSQMT = '4' THEN '估算法' WHEN a.MSQMT = '5' THEN 'ADCP' WHEN a.MSQMT = '6' THEN '电功率反推法' WHEN a.MSQMT = '9' THEN '其它方法' END MSQMT, ");
		sql.append(" CASE WHEN a.MSAMT  = '1' THEN '水位面积关系曲线' WHEN a.MSAMT  = '2' THEN '测深杆或测深锤、铅鱼' WHEN a.MSAMT  = '3' THEN '回声测深仪' WHEN a.MSAMT  = '9' THEN '其它方法' END AS MSAMT, ");
		sql.append(" CASE WHEN a.MSVMT = '1' THEN '流速仪' WHEN a.MSVMT = '2' THEN '浮标法' WHEN a.MSVMT = '3' THEN '声学法' WHEN a.MSVMT = '9' THEN '其它方法' END AS MSVMT ");
		sql.append(" FROM ST_RIVER_R a LEFT JOIN ST_STBPRP_B b ON a.STCD = b.STCD WHERE 1=1 ");
		if(mRiverFormBean.getSearchName() != null && !"".equals(mRiverFormBean.getSearchName().trim())){
			sql.append(" and b.STNM LIKE '%" + mRiverFormBean.getSearchName().trim() + "%'  ");
			String searchName = mRiverFormBean.getSearchName();
			if(searchName.contains("落")){
				sql.append(" or a.WPTN = '"+ 4 +"'  ");
			}
			if(searchName.contains("涨")){
				sql.append(" or a.WPTN = '"+ 5 +"'  ");
			}
			if(searchName.contains("平")){
				sql.append(" or a.WPTN = '"+ 6 +"'  ");
			}
			if(searchName.contains("干涸")){
				sql.append(" or a.FLWCHRCD = '"+ 1 +"'  ");
			}
			if(searchName.contains("断流")){
				sql.append(" or a.FLWCHRCD = '"+ 2 +"'  ");
			}
			if(searchName.contains("流向不定")){
				sql.append(" or a.FLWCHRCD = '"+ 3 +"'  ");
			}
			if(searchName.contains("逆流")){
				sql.append(" or a.FLWCHRCD = '"+ 4 +"'  ");
			}
			if(searchName.contains("起涨")){
				sql.append(" or a.FLWCHRCD = '"+ 5 +"'  ");
			}
			if(searchName.contains("洪峰")){
				sql.append(" or a.FLWCHRCD = '"+ 6 +"'  ");
			}
			if(searchName.contains("水电厂发电流量")){
				sql.append(" or a.FLWCHRCD = '"+ 7 +"'  ");
			}
			if(searchName.contains("一般情况")){
				sql.append(" or a.FLWCHRCD = '"+ 8 +"'  ");
			}
		}
		//根据测站查询
		if(mRiverFormBean.getStcd() != null && !"".equals(mRiverFormBean.getStcd().trim())){
			sql.append(" and  b.STCD='"+mRiverFormBean.getStcd()+" '");
		}
		//添加开始查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mRiverFormBean.getStartTime())) > 0){
			sql.append(" and a.TM >= '" + mRiverFormBean.getStartTime() + "'  ");
		}
		//添加结束查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mRiverFormBean.getEndTime())) > 0){
			sql.append(" and a.TM <= '" + mRiverFormBean.getEndTime() + "'  ");
		}
		sql.append(" ORDER BY a.STCD");
		int pageNo=mRiverFormBean.getPageBean().getLimit()>0?mRiverFormBean.getPageBean().getOffset()/mRiverFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mRiverFormBean.getPageBean().setPageNo(pageNo);	
		return this.findPageByFetchedSql(sql.toString(), null
				,mRiverFormBean.getPageBean().getPageNo()
				,mRiverFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取日蒸发量实体
	 * */
	public PageResults getRiverInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,TM,Z,Q,XSA,XSAVV,XSMXV,FLWCHRCD,WPTN,MSQMT,MSAMT,MSVMT FROM ST_RIVER_R WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND STCD IN ('"+ids+"')");
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	public void deletRiverInfoByIds(String ids,String ids_){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_RIVER_R WHERE STCD = '"+ids+"' AND TM='"+ids_+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveRiverInfo(River mRiver){
		String sql=execQL(mRiver); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateRiverInfo(River mRiver){
		String sql = updateById(mRiver);//执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public River getRiverInfoById(RiverFormBean mRiverFormBean){
		River mRiver=new River();
		String sql=execById(mRiverFormBean);//执行sql语句操作
		List<River> mRiverList=null;
		try{
			mRiverList=this.getSession().createSQLQuery(sql.toString()).addEntity(River.class).list();
			for(int i=0;i<mRiverList.size();i++){
				mRiver=mRiverList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mRiver;
	}
	
	
	/**
	 * 根据条件查询 
	 * */
	private String spliceStrRiver(RiverFormBean mRiverFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mRiverFormBean){
			if(CommonUtil.trim(mRiverFormBean.getSearchName()).length()>0){
				sql.append("AND ((river.STCD LIKE '%"+CommonUtil.trim(mRiverFormBean.getSearchName())+"%') ");
				sql.append("OR (river.TM LIKE '%"+CommonUtil.trim(mRiverFormBean.getSearchName())+"%') ");
				sql.append("OR (river.Z LIKE '%"+CommonUtil.trim(mRiverFormBean.getSearchName())+"%') ");
				sql.append("OR (river.Q  LIKE '%"+CommonUtil.trim(mRiverFormBean.getSearchName())+"%') ");
				sql.append("OR (river.FLWCHRCD  LIKE '%"+CommonUtil.trim(mRiverFormBean.getSearchName())+"%') ");
				sql.append("OR (river.WPTN  LIKE '%"+CommonUtil.trim(mRiverFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM  LIKE '%"+CommonUtil.trim(mRiverFormBean.getSearchName())+"%')) ");
			}
			if(null!=mRiverFormBean.getmRiverInfoBean()){
				if(CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getStcd()).length()>0){
					sql.append(" AND river.STCD = '"+CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getTm()).length()>0){
					sql.append(" AND river.TM = '"+CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getTm())+"'");
				}
				if(CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getFlwchrcd()).length()>0){
					sql.append(" AND river.FLWCHRCD = '"+CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getFlwchrcd())+"'");
				}
				if(CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getWptn()).length()>0){
					sql.append(" AND river.WPTN = '"+CommonUtil.trim(mRiverFormBean.getmRiverInfoBean().getWptn())+"'");
				}
			}
		}
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(River mRiver){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_RIVER_R (STCD,TM,Z,Q,XSA,XSAVV,XSMXV,FLWCHRCD,WPTN,MSQMT,MSAMT,MSVMT) ");
		sql.append("VALUES ('"+CommonUtil.trim(mRiver.getStcd())+"',");
		sql.append("'"+mRiver.getTm()+"','"+CommonUtil.trim(mRiver.getZ())+"',");
		sql.append("'"+CommonUtil.trim(mRiver.getQ())+"','"+CommonUtil.trim(mRiver.getXsa())+"',");
		sql.append("'"+CommonUtil.trim(mRiver.getXsavv())+"','"+CommonUtil.trim(mRiver.getXsmxv())+"',");
		sql.append("'"+CommonUtil.trim(mRiver.getFlwchrcd())+"','"+CommonUtil.trim(mRiver.getWptn())+"',");
		sql.append("'"+CommonUtil.trim(mRiver.getMsqmt())+"','"+CommonUtil.trim(mRiver.getMsamt())+"',");
		sql.append("'"+CommonUtil.trim(mRiver.getMsvmt())+"')");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById(RiverFormBean mRiverFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,TM,Z,Q,XSA,XSAVV,XSMXV,FLWCHRCD,WPTN,MSQMT,");
		sql.append("MSAMT,MSVMT FROM ST_RIVER_R WHERE STCD ='"+mRiverFormBean.getmRiverInfoBean().getStcd()+"' ");
		sql.append(" AND TM='"+mRiverFormBean.getmRiverInfoBean().getTm()+"'");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行修改语句
	 * */
	private String updateById(River mRiver){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_RIVER_R SET STCD = '"+CommonUtil.trim(mRiver.getStcd())+"', TM = '"+CommonUtil.trim(mRiver.getTm())+"', ");
		sql.append(" Z = '"+CommonUtil.trim(mRiver.getZ())+"', Q = '"+CommonUtil.trim(mRiver.getQ())+"', XSA = '"+CommonUtil.trim(mRiver.getXsa())+"',");
		sql.append(" XSAVV = '"+CommonUtil.trim(mRiver.getXsavv())+"', XSMXV = '"+CommonUtil.trim(mRiver.getXsmxv())+"', FLWCHRCD = '"+CommonUtil.trim(mRiver.getFlwchrcd())+"', WPTN = '"+CommonUtil.trim(mRiver.getWptn())+"',");
		sql.append(" MSQMT = '"+CommonUtil.trim(mRiver.getMsqmt())+"', MSAMT = '"+CommonUtil.trim(mRiver.getMsamt())+"', MSVMT = '"+CommonUtil.trim(mRiver.getMsvmt())+"'  WHERE STCD = '"+CommonUtil.trim(mRiver.getStcd())+"'");
		sql.append(" AND TM='"+CommonUtil.trim(mRiver.getTm())+"'");
		return sql.toString();
	}
}
