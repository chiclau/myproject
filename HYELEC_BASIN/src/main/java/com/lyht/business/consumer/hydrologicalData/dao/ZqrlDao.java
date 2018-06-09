package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Zqrl;
import com.lyht.business.consumer.hydrologicalData.formbean.ZqrlFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ZqrlDao extends HibernateBaseDao<Zqrl>{

	/**
	 * 获取列表数据
	 * */
	public PageResults getZqrlListData(ZqrlFormBean mZqrlFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrZqrl(mZqrlFormBean);
		sql.append("SELECT zqrl.STCD,zqrl.LNNM,zqrl.PTNO,zqrl.Z,zqrl.Q,zqrl.COMMENTS,zqrl.MODITIME,b.STNM AS STNM FROM ST_ZQRL_B AS zqrl ");
		sql.append(" LEFT JOIN ST_STBPRP_B AS b ON b.STCD = zqrl.STCD WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY zqrl.STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mZqrlFormBean.getPageBean().getOffset()
				,mZqrlFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getZqrlListData_export(ZqrlFormBean mZqrlFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrZqrl(mZqrlFormBean);
		sql.append("SELECT STCD,LNNM,PTNO,Z,Q,MODITIME FROM ST_ZQRL_B WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mZqrlFormBean.getPageBean().getOffset()
				,mZqrlFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取水位流量关系曲线实体
	 * */
	public PageResults getZqrlInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,LNNM,PTNO,Z,Q,COMMENTS,MODITIME FROM ST_ZQRL_B WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND STCD IN ('"+ids+"')");
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID删除行政区代码实体
	 * */
	public void deletZqrlInfoByIds(String stcd,String lnnm,String ptno){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_ZQRL_B WHERE STCD = '"+CommonUtil.trim(stcd)+"'");
		sql.append(" AND LNNM = '"+CommonUtil.trim(lnnm)+"' AND PTNO='"+CommonUtil.trim(ptno)+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据主键ID删除行政区代码实体
	 * */
	public void deletZqrlInfoByIds(String stcd,String lnnm){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_ZQRL_B WHERE STCD = '"+CommonUtil.trim(stcd)+"'");
		sql.append(" AND LNNM = '"+CommonUtil.trim(lnnm)+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveZqrlInfo(Zqrl mZqrl,int i){
		String sql=execQL(mZqrl,i); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据测站编码、曲线名称、曲线序号修改水位流量关系曲线信息
	 * */
	public void updateZqrlInfoByParams(ZqrlFormBean mZqrlFormBean,String ptno,String Z,String Q){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_ZQRL_B SET STCD = '"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getStcd())+"',");
		sql.append("LNNM = '"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getLnnm())+"',");
		sql.append(" PTNO = '"+CommonUtil.trim(ptno)+"', Z = '"+CommonUtil.trim(Z)+"', ");
		sql.append(" Q = '"+CommonUtil.trim(Q)+"',MODITIME='"+modiTime+"' ");
		sql.append("  WHERE STCD = '"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getStcd())+"'");
		sql.append("  AND LNNM = '"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getLnnm())+"'");
		sql.append("  AND PTNO = '"+CommonUtil.trim(ptno)+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据测站编码、曲线名称、曲线序号修改水位流量关系曲线信息
	 * */
	public void insertZqrlInfoByParams(ZqrlFormBean mZqrlFormBean,String ptno,String Z,String Q){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_ZQRL_B (STCD,LNNM,PTNO,Z,Q,COMMENTS,MODITIME) ");
		sql.append("VALUES ('"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getStcd())+"',");
		sql.append("'"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getLnnm())+"','"+CommonUtil.trim(ptno)+"',");
		sql.append("'"+CommonUtil.trim(Z)+"','"+CommonUtil.trim(Q)+"',");
		sql.append("'"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getComments())+"','"+modiTime+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据测站编码、曲线名称、曲线序号获取水位流量关系曲线信息
	 * */
	public List<Map> getZqrlInfoByParams(ZqrlFormBean mZqrlFormBean,String ptno){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,LNNM,PTNO,Z,Q,COMMENTS,MODITIME FROM ST_ZQRL_B ");
		sql.append("WHERE STCD='"+mZqrlFormBean.getmZqrlInfoBean().getStcd()+"' ");
		sql.append("AND LNNM='"+mZqrlFormBean.getmZqrlInfoBean().getLnnm()+"' ");
		sql.append("AND PTNO='"+ptno+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveZqrlInfo(Zqrl mZqrl){
		String sql=execQL(mZqrl); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 查询数量
	 * */
	public int count(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT COUNT(STCD) AS STCD  FROM ST_ZQRL_B");
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		return (int)query.uniqueResult();
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateZqrlInfo(Zqrl mZqrl){
		String sql = updateById(mZqrl);//执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public List<Zqrl> getZqrlInfoById(ZqrlFormBean mZqrlFormBean){
		String sql=execById(mZqrlFormBean);//执行sql语句操作
		return this.getSession().createSQLQuery(sql).addEntity(Zqrl.class).list();
	}
	
	@SuppressWarnings("unchecked")
	public Zqrl getZqrlInfoById_(String ids,String ids_,String _ids){
		Zqrl mZqrl=new Zqrl();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,LNNM,PTNO,Z,Q,COMMENTS,MODITIME FROM ST_ZQRL_B ");
		sql.append("WHERE STCD='"+ids+"' AND LNNM='"+ids_+"' AND PTNO='"+_ids+"'");
		List<Zqrl> mZqrlList= new ArrayList<>();
		try{
			mZqrlList=this.getSession().createSQLQuery(sql.toString()).addEntity(Zqrl.class).list();
			for(int i=0;i<mZqrlList.size();i++){
				mZqrl=mZqrlList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mZqrl;
	}
	/**
	 * 根据条件查询行政区代码
	 * */
	private String spliceStrZqrl(ZqrlFormBean mZqrlFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mZqrlFormBean){
			if(CommonUtil.trim(mZqrlFormBean.getSearchName()).length()>0){
				sql.append("AND ((zqrl.STCD LIKE '%"+CommonUtil.trim(mZqrlFormBean.getSearchName())+"%') ");
				sql.append("OR (zqrl.LNNM LIKE '%"+CommonUtil.trim(mZqrlFormBean.getSearchName())+"%') ");
				sql.append("OR (zqrl.PTNO LIKE '%"+CommonUtil.trim(mZqrlFormBean.getSearchName())+"%') ");
				sql.append("OR (zqrl.Z LIKE '%"+CommonUtil.trim(mZqrlFormBean.getSearchName())+"%') ");
				sql.append("OR (zqrl.Q  LIKE '%"+CommonUtil.trim(mZqrlFormBean.getSearchName())+"%') ");
				sql.append("OR (zqrl.MODITIME  LIKE '%"+CommonUtil.trim(mZqrlFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM  LIKE '%"+CommonUtil.trim(mZqrlFormBean.getSearchName())+"%')) ");
			}
			if(null!=mZqrlFormBean.getmZqrlInfoBean()){
				if(CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getStcd()).length()>0){
					sql.append(" AND zqrl.STCD = '"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getLnnm()).length()>0){
					sql.append(" AND zqrl.LNNM = '"+CommonUtil.trim(mZqrlFormBean.getmZqrlInfoBean().getLnnm())+"'");
				}
			}
		}
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(Zqrl mZqrl,int i){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_ZQRL_B (STCD,LNNM,PTNO,Z,Q,COMMENTS,MODITIME) ");
		sql.append("VALUES ('"+CommonUtil.trim(mZqrl.getStcd())+"',");
		sql.append("'"+CommonUtil.trim(mZqrl.getLnnm())+"','"+CommonUtil.trim(++i)+"',");
		sql.append("'"+CommonUtil.trim(mZqrl.getZ())+"','"+CommonUtil.trim(mZqrl.getQ())+"',");
		sql.append("'"+CommonUtil.trim(mZqrl.getComments())+"','"+modiTime+"')");
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(Zqrl mZqrl){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_ZQRL_B (STCD,LNNM,PTNO,Z,Q,COMMENTS,MODITIME) ");
		sql.append("VALUES ('"+CommonUtil.trim(mZqrl.getStcd())+"',");
		sql.append("'"+CommonUtil.trim(mZqrl.getLnnm())+"','"+CommonUtil.trim(mZqrl.getPtno())+"',");
		sql.append("'"+CommonUtil.trim(mZqrl.getZ())+"','"+CommonUtil.trim(mZqrl.getQ())+"',");
		sql.append("'"+CommonUtil.trim(mZqrl.getComments())+"','"+modiTime+"')");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById(ZqrlFormBean mZqrlFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,LNNM,PTNO,Z,Q,COMMENTS,MODITIME FROM ST_ZQRL_B ");
		sql.append("WHERE STCD='"+mZqrlFormBean.getmZqrlInfoBean().getStcd()+"' ");
		sql.append(" AND LNNM='"+mZqrlFormBean.getmZqrlInfoBean().getLnnm()+"' ");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行修改语句
	 * */
	private String updateById(Zqrl mZqrl){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_ZQRL_B SET  LNNM = '"+CommonUtil.trim(mZqrl.getLnnm())+"', ");
		sql.append(" PTNO = '"+CommonUtil.trim(mZqrl.getPtno())+"', Z = '"+CommonUtil.trim(mZqrl.getZ())+"', Q = '"+CommonUtil.trim(mZqrl.getQ())+"'");
		sql.append("  WHERE STCD = '"+CommonUtil.trim(mZqrl.getStcd())+"'");
		sql.append("  AND LNNM = '"+CommonUtil.trim(mZqrl.getLnnm())+"'");
		return sql.toString();
	}
}
