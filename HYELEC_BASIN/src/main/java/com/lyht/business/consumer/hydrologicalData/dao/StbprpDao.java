package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Stbprp;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.Randomizer;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class StbprpDao extends HibernateBaseDao<Stbprp>{
	
	public List<Map> getStbprpList(StbprpFormBean formBean){
		StringBuffer sb = new StringBuffer("select STCD,STNM,LGTD1,LTTD1,STTP from ST_STBPRP_B"
				+ " where LGTD1 IS NOT NULL AND LTTD1 IS NOT NULL"
				+ " and STTP!='PP' ");
		//查询条件 
		return this.createSQLQuerybyMap(sb.toString());
	}
	public List<Map> queryStbprpList(StbprpFormBean formBean){
		StringBuffer sb = new StringBuffer("select STCD,STNM,LGTD1,LTTD1,STTP from ST_STBPRP_B"
				+ " where LGTD1 IS NOT NULL AND LTTD1 IS NOT NULL ");
		//查询条件 
		return this.createSQLQuerybyMap(sb.toString());
	}
	public List<Map> queryAddvcdTreeList(String paddvcd){
		StringBuffer sql = new StringBuffer("SELECT A.ADDVCD,A.NAME,ISNULL(A.PADDVCD,'0') AS PADDVCD,(case when SUBSTRING(A.ADDVCD,LEN(A.ADDVCD)-1,2)='00' then 1 else 0 end) as isParent FROM SD_ADDVCD_DIC A WHERE ISNULL(A.PADDVCD,'0')='"+paddvcd+"' order by ADDVCD ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 获取列表数据根据名称模糊查询
	 * */
	public PageResults getStbprpByNameMes(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT STCD,STNM,RVNM,HNNM,BSNM,LGTD1,LTTD1,STLC,ADDVCD,DTMNM,"
				+ "DTMEL,DTPR,STTP,FRGRD,ESSTYM,BGFRYM,ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,"
				+ "PHCD,FIELDCAP FROM ST_STBPRP_B WHERE 1=1 ");
		if(null!=mStbprpFormBean){
			if(null!=mStbprpFormBean.getmStbprpInfoBean()){
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStnm()).length()>0){
					sql.append(" and STNM='"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStnm())+"'");
				}
			}
		}
		sql.append(" AND STTP!='PP' ");
		sql.append(" ORDER BY STCD");
		int pageNo=mStbprpFormBean.getPageBean().getLimit()>0?mStbprpFormBean.getPageBean().getOffset()/mStbprpFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mStbprpFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mStbprpFormBean.getPageBean().getPageNo()
				,mStbprpFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据根据名称模糊查询
	 * */
	public PageResults getStbprpListMoHu(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		String str=spliceStrStbprp1(mStbprpFormBean);
		sql.append("SELECT STCD,STNM,RVNM,HNNM,BSNM,LGTD1,LTTD1,STLC,ADDVCD,DTMNM,"
				+ "DTMEL,DTPR,STTP,FRGRD,ESSTYM,BGFRYM,ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,"
				+ "PHCD,FIELDCAP FROM ST_STBPRP_B WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" AND STTP!='PP' ");
		sql.append(" ORDER BY STCD");
		int pageNo=mStbprpFormBean.getPageBean().getLimit()>0?mStbprpFormBean.getPageBean().getOffset()/mStbprpFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mStbprpFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mStbprpFormBean.getPageBean().getPageNo()
				,mStbprpFormBean.getPageBean().getLimit()
				,null);
	}
	public PageResults getStbprpListMoHu1(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		String str=spliceStrStbprp1(mStbprpFormBean);
		sql.append("SELECT STCD,STNM,RVNM,HNNM,BSNM,LGTD1,LTTD1,STLC,ADDVCD,DTMNM,"
				+ "DTMEL,DTPR,STTP,FRGRD,ESSTYM,BGFRYM,ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,"
				+ "PHCD,FIELDCAP FROM ST_STBPRP_B WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" AND STTP not in ('ZQ','ZS','ZB') ");
		sql.append(" ORDER BY STCD");
		int pageNo=mStbprpFormBean.getPageBean().getLimit()>0?mStbprpFormBean.getPageBean().getOffset()/mStbprpFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mStbprpFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mStbprpFormBean.getPageBean().getPageNo()
				,mStbprpFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据
	 * */
	public PageResults getStbprpListData(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		String str=spliceStrStbprp(mStbprpFormBean);
		sql.append("SELECT STCD as STCD,STNM as STNM,"
				+ "RVNM as RVNM,HNNM as HNNM,BSNM as BSNM,"
				+ "LGTD1 as LGTD1,LTTD1 as LTTD1,STLC as STLC,"
				+ "ADDVCD as ADDVCD,DTMNM as DTMNM,"
				+ "DTMEL as DTMEL,DTPR as DTPR,STTP as STTP,"
				+ "FRGRD as FRGRD,ESSTYM as ESSTYM,"
				+ "BGFRYM as BGFRYM,ATCUNIT as ATCUNIT,"
				+ "ADMAUTH as ADMAUTH,LOCALITY as LOCALITY,"
				+ "STBK as STBK,STAZT as STAZT,DSTRVM as DSTRVM,"
				+ "DRNA as DRNA,"
				+ "PHCD as PHCD,FIELDCAP as FIELDCAP FROM ST_STBPRP_B WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		int pageNo=mStbprpFormBean.getPageBean().getLimit()>0?mStbprpFormBean.getPageBean().getOffset()/mStbprpFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mStbprpFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mStbprpFormBean.getPageBean().getPageNo()
				,mStbprpFormBean.getPageBean().getLimit()
				,null);
	}
	public List<Map> queryStbprpListData(String searchText){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT STCD as STCD,STNM as STNM,"
				+ "RVNM as RVNM,HNNM as HNNM,BSNM as BSNM,"
				+ "LGTD1 as LGTD1,LTTD1 as LTTD1,STLC as STLC,"
				+ "ADDVCD as ADDVCD,DTMNM as DTMNM,"
				+ "DTMEL as DTMEL,DTPR as DTPR,STTP as STTP,"
				+ "FRGRD as FRGRD,ESSTYM as ESSTYM,"
				+ "BGFRYM as BGFRYM,ATCUNIT as ATCUNIT,"
				+ "ADMAUTH as ADMAUTH,LOCALITY as LOCALITY,"
				+ "STBK as STBK,STAZT as STAZT,DSTRVM as DSTRVM,"
				+ "DRNA as DRNA,"
				+ "PHCD as PHCD,FIELDCAP as FIELDCAP FROM ST_STBPRP_B"
				+ " WHERE 1=1 AND STTP NOT IN ('PP') ");
		if(CommonUtil.trim(searchText).length()>0 || !CommonUtil.trim(searchText).equals("*")){
			sql.append(" and STNM LIKE '%"+CommonUtil.trim(searchText)+"%'");
		}
		sql.append(" ORDER BY STCD");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryAllAddvcd(){
		StringBuffer sql = new StringBuffer("SELECT * FROM SD_ADDVCD_DIC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getStbprpListData_export(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		String str=spliceStrStbprp(mStbprpFormBean);
		sql.append("SELECT STCD,STNM,DTMNM,DTMEL,STTP,ADMAUTH,ESSTYM,FRGRD FROM ST_STBPRP_B WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		int pageNo=mStbprpFormBean.getPageBean().getLimit()>0?mStbprpFormBean.getPageBean().getOffset()/mStbprpFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mStbprpFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mStbprpFormBean.getPageBean().getPageNo()
				,mStbprpFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取测站信息实体
	 * */
	public PageResults getStbprpInfoListByIds(String ids){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT STCD,STNM,RVNM,HNNM,BSNM,LGTD1,LTTD1,STLC,ADDVCD,DTMNM,"
				+ "DTMEL,DTPR,STTP,FRGRD,ESSTYM,BGFRYM,ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,"
				+ "PHCD,FIELDCAP FROM ST_STBPRP_B WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND STCD IN ('"+ids+"')");
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	public PageResults getStbprpInfoListByStcd(String stcd){
		StringBuffer hql = new StringBuffer("from Stbprp where stcd=?");
		return this.findPageByFetchedHql(hql.toString(), null, 1, 2, new Object[]{stcd});
	}
	public Map queryAddvcdBeanByAddvcd(String addvcd){
		StringBuffer sql = new StringBuffer("SELECT * FROM SD_ADDVCD_DIC WHERE ADDVCD='"+addvcd+"'");
		List<Map> relist = this.createSQLQuerybyMap(sql.toString());
		if(relist!=null && relist.size()>0){
			return relist.get(0);
		}
		return null;
	}
	/**
	 * 根据主键ID删除行政区代码实体
	 * */
	public void deletStbprpInfoByIds(String ids){
		StringBuffer sql=new StringBuffer();
		sql.append("DELETE FROM ST_STBPRP_B WHERE STCD IN ('"+ids+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveStbprpInfo(Stbprp mStbprp){
		String sql=execQL(mStbprp); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateStbprpInfo(Stbprp mStbprp){
		String sql = updateById(mStbprp);//执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public Stbprp getStbprpInfoById(StbprpFormBean mStbprpFormBean){
		Stbprp mStbprp=new Stbprp();
		String sql=execById(mStbprpFormBean);//执行sql语句操作
		List<Stbprp> mStbprpList=null;
		try{
			mStbprpList=this.getSession().createSQLQuery(sql).addEntity(Stbprp.class).list();
			for(int i=0;i<mStbprpList.size();i++){
				mStbprp=mStbprpList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mStbprp;
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public Stbprp getStbprpInfoById_(String stcd){
		Stbprp mStbprp=new Stbprp();
		String sql=execById_(stcd);//执行sql语句操作
		List<Stbprp> mStbprpList=null;
		try{
			mStbprpList=this.getSession().createSQLQuery(sql.toString()).addEntity(Stbprp.class).list();
			for(int i=0;i<mStbprpList.size();i++){
				mStbprp=mStbprpList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mStbprp;
	}
	
	
	/**
	 * 根据条件查询行政区代码
	 * */
	private String spliceStrStbprp(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		if(null!=mStbprpFormBean){
			if(CommonUtil.trim(mStbprpFormBean.getSearchName()).length()>0){
				sql.append("AND ((STCD LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (DTMNM LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (DTMEL LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (STTP  LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (ADMAUTH LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (ESSTYM LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (FRGRD LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%')) ");
			}
			if(null!=mStbprpFormBean.getmStbprpInfoBean()){
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStcd()).length()>0){
					sql.append(" AND STCD ='"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStnm()).length()>0){
					sql.append(" AND STNM = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStnm())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmnm()).length()>0){
					sql.append(" AND DTMNM = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmnm())+"'");
				}
				/*if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmel()).length()>0){
					sql.append(" AND DTMEL LIKE '%"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmel())+"%'");
				}*/
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getSttp()).length()>0){
					sql.append(" AND STTP = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getSttp())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getAdmauth()).length()>0){
					sql.append(" AND ADMAUTH = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getAdmauth())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getEsstym()).length()>0){
					sql.append(" AND ESSTYM = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getEsstym())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getFrgrd()).length()>0){
					sql.append(" AND FRGRD = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getFrgrd())+"'");
				}
			}
		}
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(Stbprp mStbprp){
		String modiTime=DateUtil.getDateTime(); //获取当前系统时间
		StringBuffer sql=new StringBuffer();
		sql.append("INSERT INTO ST_STBPRP_B (STCD,STNM,RVNM,HNNM,BSNM,LGTD1,");
		sql.append("LTTD1,STLC,ADDVCD,DTMNM,DTMEL,DTPR,STTP,FRGRD,ESSTYM,BGFRYM,");
		sql.append("ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,PHCD,FIELDCAP,");
		sql.append("MODITIME,USESYMB,COMMENTS) ");
		sql.append("VALUES ('"+CommonUtil.trim(mStbprp.getStcd())+"','"+CommonUtil.trim(mStbprp.getStnm())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getRvnm())+"','"+CommonUtil.trim(mStbprp.getHnnm())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getBsnm())+"','"+CommonUtil.trim(mStbprp.getLgtd1())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getLttd1())+"','"+CommonUtil.trim(mStbprp.getStlc())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getAddvcd())+"','"+CommonUtil.trim(mStbprp.getDtmnm())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getDtmel())+"','"+CommonUtil.trim(mStbprp.getDtpr())+"',");
		sql.append( "'"+CommonUtil.trim(mStbprp.getSttp())+"','"+CommonUtil.trim(mStbprp.getFrgrd())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getEsstym())+"','"+CommonUtil.trim(mStbprp.getBgfrym())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getAtcunit())+"','"+CommonUtil.trim(mStbprp.getAdmauth())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getLocality())+"','"+CommonUtil.trim(mStbprp.getStbk())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getStazt())+"','"+CommonUtil.trim(mStbprp.getDstrvm())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getDrna())+"','"+CommonUtil.trim(mStbprp.getPhcd())+"',");
		sql.append("'"+CommonUtil.trim(mStbprp.getFieldcap())+"','"+modiTime+"',");
		sql.append("'0','"+CommonUtil.trim(mStbprp.getComments())+"')");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT STCD,STNM,RVNM,HNNM,BSNM,LGTD1,LTTD1,STLC,ADDVCD,DTMNM,");
		sql.append("DTMEL,DTPR,ltrim(rtrim(STTP)) AS STTP,FRGRD,ESSTYM,BGFRYM,ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,");
		sql.append("PHCD,FIELDCAP,USESYMB,COMMENTS,MODITIME FROM ST_STBPRP_B WHERE STCD='"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStcd())+"'");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById_(String stcd){
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT STCD,STNM,RVNM,HNNM,BSNM,LGTD1,LTTD1,STLC,ADDVCD,DTMNM,");
		sql.append("DTMEL,DTPR,ltrim(rtrim(STTP)) AS STTP,FRGRD,ESSTYM,BGFRYM,ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,");
		sql.append("PHCD,FIELDCAP,USESYMB,COMMENTS,MODITIME FROM ST_STBPRP_B WHERE STCD='"+CommonUtil.trim(stcd)+"'");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行修改语句
	 * */
	private String updateById(Stbprp mStbprp){
		StringBuffer sql=new StringBuffer();
		sql.append("UPDATE ST_STBPRP_B SET  STNM = '"+CommonUtil.trim(mStbprp.getStnm())+"' , RVNM = '"+CommonUtil.trim(mStbprp.getRvnm())+"', ");
		sql.append(" HNNM = '"+CommonUtil.trim(mStbprp.getHnnm())+"', BSNM = '"+CommonUtil.trim(mStbprp.getBsnm())+"', LGTD1 = '"+CommonUtil.trim(mStbprp.getLgtd1())+"',");
		sql.append(" LTTD1 = '"+CommonUtil.trim(mStbprp.getLttd1())+"' , STLC = '"+CommonUtil.trim(mStbprp.getStlc())+"', ");
		sql.append(" ADDVCD = '"+CommonUtil.trim(mStbprp.getAddvcd())+"', DTMNM = '"+CommonUtil.trim(mStbprp.getDtmnm())+"', ");
		sql.append(" DTMEL = '"+CommonUtil.trim(mStbprp.getDtmel())+"', DTPR = '"+CommonUtil.trim(mStbprp.getDtpr())+"',");
		sql.append(" STTP = '"+CommonUtil.trim(mStbprp.getSttp())+"', FRGRD = '"+CommonUtil.trim(mStbprp.getFrgrd())+"', ");
		sql.append(" ESSTYM = '"+CommonUtil.trim(mStbprp.getEsstym())+"', BGFRYM = '"+CommonUtil.trim(mStbprp.getBgfrym())+"', ");
		sql.append(" ATCUNIT = '"+CommonUtil.trim(mStbprp.getAtcunit())+"', ADMAUTH = '"+CommonUtil.trim(mStbprp.getAdmauth())+"',");
		sql.append(" LOCALITY = '"+CommonUtil.trim(mStbprp.getLocality())+"', STBK = '"+CommonUtil.trim(mStbprp.getStbk())+"', ");
		sql.append(" STAZT = '"+CommonUtil.trim(mStbprp.getStazt())+"', DSTRVM = '"+CommonUtil.trim(mStbprp.getDstrvm())+"', ");
		sql.append(" DRNA = '"+CommonUtil.trim(mStbprp.getDrna())+"', PHCD = '"+CommonUtil.trim(mStbprp.getPhcd())+"', ");
		sql.append(" FIELDCAP = '"+CommonUtil.trim(mStbprp.getFieldcap())+"', COMMENTS = '"+CommonUtil.trim(mStbprp.getComments())+"' WHERE STCD = '"+CommonUtil.trim(mStbprp.getStcd())+"'");
		return sql.toString();
	}
	
	/**
	 * 获取下拉列表数据
	 * */
	public PageResults getUpstcdData(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		String str=spliceStrStbprp(mStbprpFormBean);
		sql.append("SELECT STCD,STNM,RVNM,HNNM,BSNM,LGTD1,LTTD1,STLC,ADDVCD,DTMNM,"
				+ "DTMEL,DTPR,STTP,FRGRD,ESSTYM,BGFRYM,ATCUNIT,ADMAUTH,LOCALITY,STBK,STAZT,DSTRVM,DRNA,"
				+ "PHCD,FIELDCAP FROM ST_STBPRP_B WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		int pageNo=mStbprpFormBean.getPageBean().getLimit()>0?mStbprpFormBean.getPageBean().getOffset()/mStbprpFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mStbprpFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mStbprpFormBean.getPageBean().getPageNo()
				,mStbprpFormBean.getPageBean().getLimit()
				,null);
	}
	
	
	
	
	
	/**
	 * 根据站名模糊查询
	 */
	private String spliceStrStbprp1(StbprpFormBean mStbprpFormBean){
		StringBuffer sql=new StringBuffer();
		if(null!=mStbprpFormBean){
			if(CommonUtil.trim(mStbprpFormBean.getSearchName()).length()>0){
				sql.append("AND ((STCD LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (DTMNM LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (DTMEL LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (STTP  LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (ADMAUTH LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (ESSTYM LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%') ");
				sql.append("OR (FRGRD LIKE '%"+CommonUtil.trim(mStbprpFormBean.getSearchName())+"%')) ");
			}
			if(null!=mStbprpFormBean.getmStbprpInfoBean()){
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStcd()).length()>0){
					sql.append(" AND STCD ='"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStnm()).length()>0){
					sql.append(" AND STNM LIKE '%"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getStnm())+"%'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmnm()).length()>0){
					sql.append(" AND DTMNM = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmnm())+"'");
				}
				/*if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmel()).length()>0){
					sql.append(" AND DTMEL LIKE '%"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getDtmel())+"%'");
				}*/
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getSttp()).length()>0){
					sql.append(" AND STTP = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getSttp())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getAdmauth()).length()>0){
					sql.append(" AND ADMAUTH = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getAdmauth())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getEsstym()).length()>0){
					sql.append(" AND ESSTYM = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getEsstym())+"'");
				}
				if(CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getFrgrd()).length()>0){
					sql.append(" AND FRGRD = '"+CommonUtil.trim(mStbprpFormBean.getmStbprpInfoBean().getFrgrd())+"'");
				}
			}
		}
		return sql.toString();
	}
}
