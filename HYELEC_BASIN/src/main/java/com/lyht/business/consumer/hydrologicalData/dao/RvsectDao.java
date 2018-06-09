package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Rvsect;
import com.lyht.business.consumer.hydrologicalData.formbean.RvsectFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RvsectDao extends HibernateBaseDao<Rvsect>{

	/**
	 * 获取列表数据
	 * */
	public PageResults getRvsectListData(RvsectFormBean mRvsectFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrRvsect(mRvsectFormBean);
		sql.append("SELECT rvsect.STCD,rvsect.MSTM,rvsect.VTNO,rvsect.DI,rvsect.ZB,rvsect.COMMENTS,rvsect.MODITIME,b.STNM FROM ST_RVSECT_B AS rvsect ");
		sql.append(" LEFT JOIN ST_STBPRP_B AS b ON b.STCD = rvsect.STCD  WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY rvsect.STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mRvsectFormBean.getPageBean().getOffset()
				,mRvsectFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getRvsectListData_export(RvsectFormBean mRvsectFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrRvsect(mRvsectFormBean);
		sql.append("SELECT STCD,MSTM,VTNO,DI,ZB,COMMENTS,MODITIME FROM ST_RVSECT_B WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mRvsectFormBean.getPageBean().getOffset()
				,mRvsectFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取断面测试成果实体
	 * */
	public PageResults getRvsectInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,MSTM,VTNO,DI,ZB,COMMENTS,MODITIME FROM ST_RVSECT_B WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND STCD IN ('"+ids+"')");
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	public void deletRvsectInfoByIds(Rvsect mRvsect){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_RVSECT_B WHERE STCD = '"+CommonUtil.trim(mRvsect.getStcd())+"'");
		sql.append(" AND DI = '"+CommonUtil.trim(mRvsect.getDi())+"' AND ZB = '"+CommonUtil.trim(mRvsect.getZb())+"'");
		sql.append(" AND VTNO = '"+CommonUtil.trim(mRvsect.getVtno())+"' AND MSTM = '"+CommonUtil.trim(mRvsect.getMstm())+"' AND COMMENTS = '"+CommonUtil.trim(mRvsect.getComments())+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveRvsectInfo(Rvsect mRvsect){
		String sql=execQL(mRvsect); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateRvsectInfo(Rvsect mRvsect){
		String sql = updateById(mRvsect);//执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据条件获取断面测试信息
	 * */
	public List<Map> getRvsectInfoByParams(RvsectFormBean mRvsectFormBean,String vtno){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,MSTM,VTNO,DI,ZB,COMMENTS,MODITIME FROM ST_RVSECT_B ");
		sql.append("WHERE STCD='"+mRvsectFormBean.getmRvsectInfoBean().getStcd()+"' ");
		sql.append(" AND MSTM='"+mRvsectFormBean.getmRvsectInfoBean().getMstm()+"' ");
		sql.append(" AND VTNO='"+vtno+"' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据条件修改断面测试信息
	 * */
	public void updateRvsectInfoByParams(RvsectFormBean mRvsectFormBean,String di,String zb,String vtno){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_RVSECT_B SET STCD='"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getStcd())+"',");
		sql.append("MSTM = '"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getMstm())+"',MODITIME ='"+modiTime+"',");
		sql.append("VTNO = '"+CommonUtil.trim(vtno)+"', DI = '"+CommonUtil.trim(di)+"', ZB = '"+CommonUtil.trim(zb)+"'");
		sql.append(" WHERE STCD = '"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getStcd())+"'");
		sql.append(" AND MSTM = '"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getMstm())+"'");
		sql.append(" AND VTNO = '"+CommonUtil.trim(vtno)+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 新增断面测试信息
	 * */
	public void insertRvsectInfoByParams(RvsectFormBean mRvsectFormBean,String di,String zb,String vtno){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_RVSECT_B (STCD,MSTM,VTNO,DI,ZB,COMMENTS,MODITIME) ");
		sql.append("VALUES ('"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getStcd())+"',");
		sql.append("'"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getMstm())+"','"+CommonUtil.trim(vtno)+"',");
		sql.append("'"+CommonUtil.trim(di)+"','"+CommonUtil.trim(zb)+"',");
		sql.append("'"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getComments())+"','"+modiTime+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public List<Rvsect> getRvsectInfoById(String stcd){
		String sql=execById(stcd);//执行sql语句操作
		List<Rvsect> mRvsectList=null;
		try{
			mRvsectList=this.getSession().createSQLQuery(sql.toString()).addEntity(Rvsect.class).list();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mRvsectList;
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public Rvsect getRvsectInfoById_(String stcd){
		Rvsect mRvsect=new Rvsect();
		String sql=execById(stcd);//执行sql语句操作
		List<Rvsect> mRvsectList=null;
		try{
			mRvsectList=this.getSession().createSQLQuery(sql.toString()).addEntity(Rvsect.class).list();
			for(int i=0;i<mRvsectList.size();i++){
				mRvsect=mRvsectList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mRvsect;
	}
	
	/**
	 * 根据条件查询 
	 * */
	private String spliceStrRvsect(RvsectFormBean mRvsectFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mRvsectFormBean){
			if(CommonUtil.trim(mRvsectFormBean.getSearchName()).length()>0){
				sql.append("AND ((rvsect.STCD LIKE '%"+CommonUtil.trim(mRvsectFormBean.getSearchName())+"%') ");
				sql.append("OR (rvsect.MSTM LIKE '%"+CommonUtil.trim(mRvsectFormBean.getSearchName())+"%') ");
				sql.append("OR (rvsect.VTNO LIKE '%"+CommonUtil.trim(mRvsectFormBean.getSearchName())+"%') ");
				sql.append("OR (rvsect.DI LIKE '%"+CommonUtil.trim(mRvsectFormBean.getSearchName())+"%') ");
				sql.append("OR (rvsect.ZB  LIKE '%"+CommonUtil.trim(mRvsectFormBean.getSearchName())+"%') ");
				sql.append("OR (rvsect.MODITIME  LIKE '%"+CommonUtil.trim(mRvsectFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM  LIKE '%"+CommonUtil.trim(mRvsectFormBean.getSearchName())+"%')) ");
			}
			if(null!=mRvsectFormBean.getmRvsectInfoBean()){
				if(CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getStcd()).length()>0){
					sql.append(" AND rvsect.STCD = '"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getMstm()).length()>0){
					sql.append(" AND rvsect.MSTM = '"+CommonUtil.trim(mRvsectFormBean.getmRvsectInfoBean().getMstm())+"' ");
				}
			}
		}
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(Rvsect mRvsect){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_RVSECT_B (STCD,MSTM,VTNO,DI,ZB,COMMENTS,MODITIME) ");
		sql.append("VALUES ('"+CommonUtil.trim(mRvsect.getStcd())+"',");
		sql.append("'"+DateUtil.ConvertTimestamp(mRvsect.getMstm())+"','"+CommonUtil.trim(mRvsect.getVtno())+"',");
		sql.append("'"+CommonUtil.trim(mRvsect.getDi())+"','"+CommonUtil.trim(mRvsect.getZb())+"',");
		sql.append("'"+CommonUtil.trim(mRvsect.getComments())+"','"+modiTime+"')");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById(String stcd){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,MSTM,VTNO,DI,ZB,COMMENTS,MODITIME FROM ST_RVSECT_B WHERE STCD='"+stcd+"'");
		return sql.toString();
	}
	
	/**
	 * 根据条件删除断面测试成果信息
	 * */
	public void deletRvsectInfoByIds_(String stcd,String vtno){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_RVSECT_B WHERE STCD = '"+stcd+"' AND  VTNO = '"+vtno+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据条件删除断面测试成果信息
	 * */
	public void deletRvsectInfoByIds(String stcd,String mstm){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_RVSECT_B WHERE STCD='"+stcd+"' AND MSTM='"+mstm+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据ID执行修改语句
	 * */
	private String updateById(Rvsect mRvsect){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_RVSECT_B SET MSTM = '"+CommonUtil.trim(mRvsect.getMstm())+"', ");
		sql.append(" VTNO = '"+CommonUtil.trim(mRvsect.getVtno())+"', DI = '"+CommonUtil.trim(mRvsect.getDi())+"', ZB = '"+CommonUtil.trim(mRvsect.getZb())+"',");
		sql.append(" COMMENTS = '"+CommonUtil.trim(mRvsect.getComments())+"' WHERE STCD = '"+CommonUtil.trim(mRvsect.getStcd())+"'");
		return sql.toString();
	}
}
