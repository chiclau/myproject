package com.lyht.business.analysisCalculation.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.Result;
import com.lyht.business.analysisCalculation.formbean.ResultFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.CommonUtil;
/**
 *作者： 刘魁
 *脚本日期:2018年6月4日 15:24:11
 *说明:  产流计算结果Dao
*/
@Repository
@Scope("prototype")
public class ResultDao extends HibernateBaseDao<Result> {
	
	public List<Map> queryStep1Jyl(String stcd,String start,String end){
		StringBuffer sql = new StringBuffer("SELECT R.*,CONVERT(CHAR(16),  R.TM, 120) AS BTM,CONVERT(CHAR(16),  R.ETM, 120) AS STM FROM ST_PPTN_R R WHERE R.STCD='"+stcd+"'"
				+ " and ((CONVERT(CHAR(16),  R.TM, 120)<='"+start+"' and CONVERT(CHAR(16),  R.ETM, 120)>'"+start+"') or "
						+ "(CONVERT(CHAR(16),  R.TM, 120)>='"+start+"' and CONVERT(CHAR(16),  R.ETM, 120)<='"+end+"')"
								+ " or (CONVERT(CHAR(16),  R.TM, 120)<'"+end+"' and CONVERT(CHAR(16),  R.ETM, 120)>='"+end+"')) order by R.TM ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
		/**
		 * 保存计算结果
		 */
	public void saveResult(Result result) {
		StringBuilder sql=new StringBuilder();
		Date  date=new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 String dateString = formatter.format(date);
		sql.append("INSERT INTO C_RESULT (YML,CREATE_TIME,YML_TIME,CREATE_STAFF,PCH,STCD,STNM,QZ,JYL,ID) ");
		sql.append("VALUES('"+result.getYml()+"','"+dateString+"','"+result.getYmlTime()+"',");
		sql.append("'"+result.getCreateStaff()+"','"+result.getPch()+"','"+result.getStcd()+"','"+result.getStnm()+"','"+result.getQz()+"', '"+result.getJyl()+"',");
		sql.append("'"+result.getId()+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据id删除
	 */
	public void delResult(Result result) {
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM C_RESULT WHERE PCH ='"+result.getPch()+"' AND STCD='"+result.getStcd()+"' ");
			
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 查询历史记录
	 */
	public PageResults getSelect(ResultFormBean resultFormBean){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT PCH FROM C_RESULT WHERE CREATE_STAFF ='"+resultFormBean.getResultBean().getCreateStaff()+"' ");
		sql.append("AND STCD='"+resultFormBean.getResultBean().getStcd()+"'	GROUP  BY  PCH");
		int pageNo=resultFormBean.getPageBean().getLimit()>0?resultFormBean.getPageBean().getOffset()/resultFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getPageNo(),resultFormBean.getPageBean().getLimit(), null);
	}
	/**
	 * 查询方案参数值
	 * @param planCode
	 * @return
	 */
	public List<Map> queryPlanParamValue(String planCode){
		StringBuffer sql = new StringBuffer("select A.PARA_VALUE,A.PARA_CODE,B.PARA_NAME,B.PARA_SYMBOL"
				+ " from MODEL_PROG_VALUE A,MODEL_PARAMENTER B "
				+ " WHERE A.PARA_CODE=B.PARA_CODE AND A.MODEL_CODE=B.MODEL_CODE"
				+ " AND A.PROG_CODE='"+planCode+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryStep1DataHistory(String stcd,String pch){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, C.STCD,C.PCH,C.JYL,C.YML,C.STNM,C.QZ FROM C_RESULT  C   ");
		sql.append("WHERE C.PCH='"+pch+"' AND C.STCD='"+stcd+"'  ORDER BY C.YML_TIME ASC");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 查询某个时段内，一些电站的降雨量
	 * @param stcds
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map> queryPptnByTime(String stcds,String start,String end){
		StringBuffer sql = new StringBuffer("SELECT A.*,CONVERT(CHAR(16),  A.TM, 120) AS BTM,"
				+ " CONVERT(CHAR(16),  A.ETM, 120) AS STM FROM ST_PPTN_R A WHERE A.STCD IN ('"+stcds.replace(",", "','")+"')"
				+ " and ((CONVERT(CHAR(16),  A.TM, 120)<='"+start+"' and CONVERT(CHAR(16),  A.ETM, 120)>'"+start+"') or "
						+ "(CONVERT(CHAR(16),  A.TM, 120)>='"+start+"' and CONVERT(CHAR(16),  A.ETM, 120)<='"+end+"')"
								+ " or (CONVERT(CHAR(16),  A.TM, 120)<'"+end+"' and CONVERT(CHAR(16),  A.ETM, 120)>='"+end+"')) order by A.stcd asc,A.tm asc");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryDayPptnByTime(String stcds,String start,String end){
		StringBuffer sql = new StringBuffer("SELECT A.NM,A.STCD,A.DYP,(CONVERT(CHAR(10),  A.TM, 23)+' "+CommonUtil.TIMESTR+"') AS TM "
				+ " FROM ST_PPTN_DAY_R A WHERE A.STCD IN ('"+stcds.replace(",", "','")+"')"
				+ " and (CONVERT(CHAR(10),  A.TM, 23)>='"+start+"' and CONVERT(CHAR(10),  A.TM, 23)<='"+end+"') order by A.stcd asc,A.tm asc");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryDayEvByTime(String stcd,String start,String end){
		StringBuffer sql = new StringBuffer("SELECT A.STCD,A.EPTP,A.DYE,(CONVERT(CHAR(10),  A.TM, 23)+' "+CommonUtil.TIMESTR+"') AS TM "
				+ " FROM ST_DAYEV_R A WHERE A.STCD IN ('"+stcd+"')"
				+ " and (CONVERT(CHAR(10),  A.TM, 23)>='"+start.substring(0, 10)+"' and CONVERT(CHAR(10),  A.TM, 23)<='"+end.substring(0, 10)+"') order by A.stcd asc,A.tm asc");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 根据批次号查询
	 */
	public PageResults getHistory(ResultFormBean resultFormBean){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT DISTINCT J.SJJG,  S.LYMJ ,CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, C.* FROM C_RESULT  C   ");
		sql.append("LEFT JOIN C_RESULT_JG  J ON C.STCD=J.STCD   ");
		sql.append("LEFT JOIN C_RESULT_SECOND S ON C.STCD=S.STCD  ");
		sql.append("WHERE C.PCH='"+resultFormBean.getResultBean().getPch()+"' AND C.STCD='"+resultFormBean.getResultBean().getStcd()+"'  ORDER BY C.YML_TIME ASC");
		int pageNo=resultFormBean.getPageBean().getLimit()>0?resultFormBean.getPageBean().getOffset()/resultFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getPageNo(),resultFormBean.getPageBean().getLimit(), null);
	}
	public List<Map> queryStep1History(String stcd,String pch){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT C.STCD,C.PCH,G.SJJG AS INTERVAL,C.STNM,C.QZ,C.JYL,CONVERT(CHAR(16),  C.YML_TIME, 120) AS DATE,0 as EM,null as PA1,null as PA,C.YML FROM C_RESULT C "
				+ " left join C_RESULT_JG G ON C.STCD=G.STCD AND C.PCH=G.PCH WHERE C.STCD='"+stcd+"' and C.PCH='"+pch+"' order by C.YML_TIME ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	@SuppressWarnings("rawtypes")
	public List<Map> queryStep6DayZfdata(String start,String end,String stcd){
		StringBuffer sql = new StringBuffer("SELECT CONVERT(CHAR(16),  A.TM, 120) AS TM,A.DYE,A.STCD FROM ST_DAYEV_R A WHERE A.STCD='"+stcd+"'"
				+ " and CONVERT(CHAR(16),  A.TM, 120)>='"+start+"' and CONVERT(CHAR(16),  A.TM, 120)<='"+end+"' ORDER BY A.TM ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 查询产流计算第六步日蒸发站列表
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map> queryStep6Zfzlist(String start,String end,String searchText){
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STBPRP_B"
				+ " WHERE STCD IN (SELECT STCD FROM ST_DAYEV_R A "
				+ " WHERE CONVERT(CHAR(16),  A.TM, 120)>='"+start+"'"
						+ " and CONVERT(CHAR(16),  A.TM, 120)<='"+end+"' group by A.STCD) ");
		if(CommonUtil.trim(searchText).length()>0
				&& !CommonUtil.trim(searchText).equals("*")){
			sql.append(" and STNM LIKE '%"+CommonUtil.trim(searchText)+"%'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryStep5History(String stcd,String pch){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT C.STCD,C.PCH,C.INTERVAL,C.STNM,C.QZ,C.JYL,CONVERT(CHAR(16),  C.DATE, 120) AS DATE,C.EM,C.PA,P.PA AS PA1,C.YML FROM C_RESULT_FIFTH C"
				+ " left join C_FIFTH_PA P ON C.STCD=P.STCD AND C.PCH=P.PCH WHERE C.STCD='"+stcd+"' and C.PCH='"+pch+"' order by C.DATE ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryStep5InitData(String stcd,String pch,String interval,String beginDate,String endDate,String stcds,String stnms,String qzs,String emstcd){
		StringBuffer sql = new StringBuffer("SELECT (CONVERT(CHAR(10),  A.TM, 23)+' "+CommonUtil.TIMESTR+"') AS DATE");
		if(CommonUtil.trim(emstcd).length()>0){
			sql.append(",B.DYE as EM ");
		}else{
			sql.append(",NULL as EM ");
		}
		String[] stcdList = stcds.split(",");
		for(int i=0;i<stcdList.length;i++){
			if(i==0){
				sql.append(",convert(varchar(20),sum(case when A.STCD='"+stcdList[i]+"' then A.DYP ELSE 0 END))");
			}else{
				sql.append("+','+convert(varchar(20),sum(case when A.STCD='"+stcdList[i]+"' then A.DYP ELSE 0 END))");
			}
		}
		sql.append(" as JYL,'"+stcd+"' as STCD,'"+pch+"' as PCH,"+interval+" as INTERVAL,"
				+ "'"+stnms+"' as STNM,'"+qzs+"' as QZ,NULL AS PA,NULL AS PA1,NULL AS YML"
						+ " FROM ST_PPTN_DAY_R A ");
		if(CommonUtil.trim(emstcd).length()>0){
			sql.append(" left join ST_DAYEV_R B ON CONVERT(varchar(10), A.TM, 23)=CONVERT(varchar(10), B.TM, 23) ");
		}
		sql.append("WHERE A.STCD IN ('"+stcds.replace(",", "','")+"')"
								+ " and CONVERT(CHAR(16),  A.TM, 120)>='"+beginDate+"'"
										+ " and CONVERT(CHAR(16),  A.TM, 120)<='"+endDate+"'"
												+ " group by A.TM ");
		if(CommonUtil.trim(emstcd).length()>0){
			sql.append(" ,B.DYE ");
		}
		sql.append(" order by CONVERT(CHAR(16),  A.TM, 120) asc");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryStep5PlanList(String stcd,SysStaff  mSysStaff){
		StringBuffer sql = new StringBuffer("select * from MODEL_PROGRAM"
				+ " where stcd='"+stcd+"'");
		if(mSysStaff!=null && mSysStaff.getStaffCode()!=null && mSysStaff.getStaffCode().trim().length()>0){
			sql.append(" and CREATE_STAFF='"+mSysStaff.getStaffCode().trim()+"'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 步骤2查询
	 * @param resultFormBean
	 * @return
	 */
	public PageResults step2(ResultFormBean resultFormBean,String start,String end) {
		StringBuffer  sql=new StringBuffer ();
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		sql.append("SELECT 	CONVERT(CHAR(16),  R.TM, 120)DATE, C.YML JYL,ISNULL(R.Q, 0 ) LL FROM ST_RIVER_R R "
				+ " LEFT JOIN (select * from C_RESULT where pch='"+pch+"') C ON C.YML_TIME=R.TM AND C.STCD=R.STCD where 1=1 ");
		if(CommonUtil.trim(start).length()>0 && CommonUtil.trim(end).length()>0) {
			sql.append(" and R.TM BETWEEN '"+start+"' AND '"+end+"' ");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append(" AND R.STCD='"+stcd+"'");
		}
		sql.append("  ORDER BY R.TM ASC  ");
		int pageNo=resultFormBean.getPageBean().getLimit()>0?resultFormBean.getPageBean().getOffset()/resultFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getPageNo(),resultFormBean.getPageBean().getLimit(), null);
	}

	public List<Map> getSecondQl(ResultFormBean resultFormBean) {
		StringBuffer  sql=new StringBuffer ();
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		sql.append("SELECT C.* ,C.Q LL,C.YML JYL,C.TM DATE FROM C_RESULT_SECOND_QL  C WHERE 1=1 ");
		if(stcd!=null||!stcd.equals("")) {
			sql.append(" AND C.STCD='"+stcd+"'");
		}
		if(pch!=null||!pch.equals("")) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	

	public List<Map> queryChanliuStep2Liuliang(String stcd,String pch,String intval){
		StringBuffer sql = new StringBuffer("SELECT CONVERT(CHAR(16),  TM, 120)DATE, ISNULL(Q, 0 ) LL,CONVERT(CHAR(16),  TM, 120) as DT,"+intval+" as INTERVAL,Q,STCD,PCH,0 AS SUMQ,0 AS R,0 AS SUMQT FROM C_RESULT_SECOND_QL WHERE STCD='"+stcd+"' and PCH='"+pch+"' order by TM ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> getLiuLiang(String stcd,String pch,String start,String end,String intval ) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, ISNULL(R.Q, 0 ) LL,ISNULL(R.Q, 0 ) as Q,CONVERT(CHAR(16),  C.YML_TIME, 120) as DT,"+intval+" as INTERVAL,C.STCD,C.PCH,0 AS SUMQ,0 AS R,0 AS SUMQT FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM  AND C.STCD=R.STCD  where 1=1 ");
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	
	public List<Map> getCResultThird(String stcd,String pch){
		StringBuffer sql = new StringBuffer("select A.*,CONVERT(CHAR(16),  A.TM, 120) AS DT from C_RESULT_THRID A where A.stcd='"+stcd+"' and pch='"+pch+"' order by A.TM ASC");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> getCResultThirdLess(String stcd,String pch){
		StringBuffer sql = new StringBuffer("select A.*,CONVERT(CHAR(16),  A.TM, 120) AS DT from C_RESULT_THRID_LESS A where A.stcd='"+stcd+"' and pch='"+pch+"' order by A.TM ASC");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 根据时间查询流量
	 */
	public PageResults getLiuLiang(ResultFormBean resultFormBean,String start,String end ) {
		StringBuffer  sql=new StringBuffer ();
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, ISNULL(R.Q, 0 ) LL FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM  AND C.STCD=R.STCD  where 1=1 ");
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		int pageNo=resultFormBean.getPageBean().getLimit()>0?resultFormBean.getPageBean().getOffset()/resultFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getPageNo(),resultFormBean.getPageBean().getLimit(), null);
	}
	
	/**
	 * 根据时间查询降雨量
	 */
	public PageResults getyml(ResultFormBean resultFormBean,String start,String end ) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, ISNULL(C.YML, 0 )  JYL  FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM  AND C.STCD=R.STCD where 1=1 ");
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		int pageNo=resultFormBean.getPageBean().getLimit()>0?resultFormBean.getPageBean().getOffset()/resultFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getPageNo(),resultFormBean.getPageBean().getLimit(), null);
	}
	public List<Map> queryChanliuStep2Jyl(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT CONVERT(CHAR(16),  TM, 120)DATE, ISNULL(YML, 0 )  JYL FROM C_RESULT_SECOND_QL WHERE STCD='"+stcd+"' and PCH='"+pch+"' order by TM ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 根据时间查询降雨量
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getyml(String stcd,String pch,String start,String end ) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, ISNULL(C.YML, 0 )  JYL  FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM  AND C.STCD=R.STCD where 1=1 ");
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 修改步骤2table中的降雨量
	 * @param resultFormBean
	 */
	public void updateTableData(ResultFormBean resultFormBean) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("UPDATE C_RESULT SET YML='"+resultFormBean.getResultBean().getYml()+"' WHERE YML_TIME='"+resultFormBean.getResultBean().getYmlTime()+"'  ");
		this.exectueSQL(sql.toString());
	}
	public List<Map> queryChanliuStep2MaxLl(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT MAX(Q) AS maxLL,MIN(Q) AS minLL FROM C_RESULT_SECOND_QL WHERE STCD='"+stcd+"' and PCH='"+pch+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> maxLL(String stcd,String pch,String start,String end){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT 	MAX(R.Q) as maxLL,MIN(R.Q) AS minLL FROM C_RESULT C "
				+ "LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM AND C.STCD=R.STCD where 1=1 ");
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 流量最大值
	 * @return
	 */
	public List maxLL(ResultFormBean resultFormBean,String start,String end) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT 	MAX(R.Q) as maxLL,MIN(R.Q) AS minLL FROM C_RESULT C "
				+ "LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM AND C.STCD=R.STCD where 1=1 ");
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 降雨量最大值
	 * @return
	 */
	public List maxYml(ResultFormBean resultFormBean,String start,String end) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT MAX(YML) JYL FROM C_RESULT C where 1=1 ");
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryChanliuStep2MaxJyl(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT MAX(YML) AS JYL FROM C_RESULT_SECOND_QL WHERE STCD='"+stcd+"' and PCH='"+pch+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 降雨量最大值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> maxYml(String stcd,String pch,String start,String end) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT MAX(YML) JYL FROM C_RESULT C where 1=1 ");
		if(CommonUtil.trim(start).length()>0){
			sql.append(" and C.YML_TIME >= '"+start+"'");
		}
		if(CommonUtil.trim(end).length()>0) {
			sql.append(" and C.YML_TIME <= '"+end+"'");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 退水曲线洪峰个数统计，用来生成json用
	 */
	public List numHongFeng(ResultFormBean resultFormBean) {
		String stcd=resultFormBean.getResultBean().getStcd();
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT B.QM,MAX(B.Q) AS MAXQ,MIN(B.Q) AS MINQ FROM  ST_TSQX_B B WHERE B.STCD='"+stcd+"'   GROUP BY B.QM");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 退水曲线洪峰个数统计，用来生成json用
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> numHongFeng(String stcd) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT B.QM,MAX(B.Q) AS MAXQ,MIN(B.Q) AS MINQ FROM  ST_TSQX_B B WHERE B.STCD='"+stcd+"'   GROUP BY B.QM");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 退水曲线x,y轴对应的时段和流浪查询
	 */
	public PageResults getTsqx(ResultFormBean resultFormBean,TsqxFormBean mTsqxFormBean) {
		String stcd=resultFormBean.getResultBean().getStcd();
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT B.T,B.Q,B.QM FROM  ST_TSQX_B B WHERE B.STCD='"+stcd+"'  GROUP BY B.QM,B.T,B.Q");
		int pageNo=mTsqxFormBean.getPageBean().getLimit()>0?mTsqxFormBean.getPageBean().getOffset()/mTsqxFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mTsqxFormBean.getPageBean().getPageNo()
				,mTsqxFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 退水曲线x,y轴对应的时段和流浪查询
	 */
	public List<Map> getTsqx(String stcd) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT B.T,B.Q,B.QM FROM  ST_TSQX_B B WHERE B.STCD='"+stcd+"'  GROUP BY B.QM,B.T,B.Q");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public int deleteStep6Result(String stcd,String pch){
		StringBuffer sql = new StringBuffer("delete from C_RESULT_SIXTH WHERE STCD=? AND PCH=? ");
		return this.excuteSql(sql.toString(), new Object[]{stcd,pch});
	}
	public List<Map> queryStep6EchartData(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT C.* FROM C_RESULT_SIX_PPA C WHERE C.STCD='"+stcd+"' and C.PCH='"+pch+"' order by C.PA ASC,C.R ASC");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryStep6ResultData(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT C.*,CONVERT(CHAR(16),  C.TM, 120) AS DATE FROM C_RESULT_SIXTH C WHERE C.STCD='"+stcd+"' and C.PCH='"+pch+"' order by C.TM ASC");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryStep7ResultData(String stcd){
		StringBuffer sql = new StringBuffer("SELECT AB.*,D.PA,D.RS,E.FC,E.TC,E.RC,E.RCTC,"
				+ " E.FCC,E.FCS FROM (SELECT A.STCD,A.PCH, "
				+ " CONVERT(CHAR(16),  MIN(A.TM), 120) AS BTM,"
				+ " CONVERT(CHAR(16),  MAX(A.TM), 120) AS ETM,"
				+ " MAX(B.Q) AS Q, SUM(A.P) AS P,SUM(A.E) AS E,SUM(A.SDRC) AS ERC"
				+ " FROM C_RESULT_SIXTH A "
				+ " LEFT JOIN ST_RIVER_R B ON A.STCD=B.STCD "
				+ " AND CONVERT(CHAR(16),  A.TM, 120)=CONVERT(CHAR(16),  B.TM, 120)"
				+ " GROUP BY A.STCD,A.PCH) AB"
				+ " LEFT JOIN C_RESULT_SECOND D ON AB.STCD=D.STCD AND AB.PCH=D.PCH"
				+ " LEFT JOIN C_RESULT_SIXTH_RESULT E ON AB.STCD=E.STCD AND AB.PCH=E.PCH "
				+ " WHERE AB.STCD='"+stcd+"'"
						+ " ORDER BY AB.PCH ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public PageResults queryStep7startEnd(ResultFormBean resultFormBean){
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		StringBuffer sql = new StringBuffer("SELECT T. *,CONVERT(CHAR(16),  T.TM, 120) DATE FROM C_RESULT_THRID  T WHERE ");
		sql.append("T.STCD='"+stcd+"'  AND T.PCH='"+pch+"'  ORDER BY T.TM ASC");
		int pageNo=resultFormBean.getPageBean().getLimit()>0?resultFormBean.getPageBean().getOffset()/resultFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getPageNo(),resultFormBean.getPageBean().getLimit(), null);
	}
	
	public List<Map> queryStep6InitData(String stcd,String pch,String start,String end){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT CONVERT(CHAR(16),  C.YML_TIME, 120)DATE ,C.YML as P,ISNULL(F.EM,0) as E,"
				+ " Round((ISNULL(C.YML,0)- ISNULL(F.EM,0)),2) PE,ISNULL(F.PA,0) AS PA FROM C_RESULT C"
				+ " LEFT JOIN C_RESULT_FIFTH F ON C.STCD=F.STCD AND C.PCH=F.PCH"
				+ " AND CONVERT(CHAR(16),  C.YML_TIME, 120)=CONVERT(CHAR(16),  F.DATE, 120)"
				+ " WHERE 1=1 ");
		if(CommonUtil.trim(start).length()>0 && CommonUtil.trim(end).length()>0){
			sql.append(" AND C.YML_TIME BETWEEN '"+start+"' AND '"+end+"' ");
		}
		if(CommonUtil.trim(stcd).length()>0) {
			sql.append(" AND C.STCD='"+stcd+"'");
		}
		if(CommonUtil.trim(pch).length()>0) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> queryStep6RrffEcharts(String stcd){
		StringBuffer sql = new StringBuffer("select STCD,PA,P,R from ST_JYJLXGT_B where stcd='"+stcd+"' order by pa asc,r asc ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 步骤6查询方法
	 */
	public  PageResults getStep6(ResultFormBean resultFormBean,String start,String end) {
		StringBuffer  sql=new StringBuffer ();
		String stcd=resultFormBean.getResultBean().getStcd();
		String pch=resultFormBean.getResultBean().getPch();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE ,ISNULL(C.YML, 0 ) P, ISNULL(F.EM, 0 ) E, (ISNULL(C.YML, 0 )- ISNULL(F.EM, 0 ) ) PE  ");
		sql.append("FROM C_RESULT C  LEFT JOIN C_RESULT_FIFTH F ON C.STCD=F.STCD  AND C.YML=F.YML " );
		sql.append("AND C.YML_TIME=F.DATE ");
		if(start!=null||end!=null) {
			sql.append(" WHERE C.YML_TIME BETWEEN '"+start+"' AND '"+end+"' ");
		}
		if(stcd!=null||!stcd.equals("")) {
			sql.append(" AND C.STCD='"+stcd+"'");
		}
		if(pch!=null||!pch.equals("")) {
			sql.append(" AND C.PCH='"+pch+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		int pageNo=resultFormBean.getPageBean().getLimit()>0?resultFormBean.getPageBean().getOffset()/resultFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		resultFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getPageNo(),resultFormBean.getPageBean().getLimit(), null);
	}
		
}
