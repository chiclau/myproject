package com.lyht.business.analysisCalculation.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.Result;
import com.lyht.business.analysisCalculation.formbean.ResultFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
/**
 *作者： 刘魁
 *脚本日期:2018年6月4日 15:24:11
 *说明:  产流计算结果Dao
*/
@Repository
@Scope("prototype")
public class ResultDao extends HibernateBaseDao<Result> {
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
	public void delResult(String ids) {
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM C_RESULT WHERE PCH IN ('"+ids+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 查询历史记录
	 */
	public PageResults getSelect(ResultFormBean resultFormBean){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT PCH FROM C_RESULT GROUP  BY  PCH");
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getOffset(),resultFormBean.getPageBean().getLimit(), null);
	}
	
	/**
	 * 根据批次号查询
	 */
	public PageResults getHistory(ResultFormBean resultFormBean){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT * FROM C_RESULT  WHERE PCH='"+resultFormBean.getResultBean().getPch().trim()+"'");
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getOffset(),resultFormBean.getPageBean().getLimit(), null);
	}
	
	/**
	 * 步骤2查询
	 * @param resultFormBean
	 * @return
	 */
	public PageResults step2(ResultFormBean resultFormBean,String start,String end) {
		StringBuffer  sql=new StringBuffer ();
		String stcd=resultFormBean.getResultBean().getStcd();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, C.YML JYL,ISNULL(R.Q, 0 ) LL FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM  ");
		if(start!=null||end!=null) {
			sql.append(" WHERE C.YML_TIME BETWEEN '"+start+"' AND '"+end+"' ");
		}
		if(stcd!=null||!stcd.equals("")) {
			sql.append(" AND C.STCD='"+stcd+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getOffset(),resultFormBean.getPageBean().getLimit(), null);
	}
	
	/**
	 * 根据时间查询流量
	 */
	public PageResults getLiuLiang(ResultFormBean resultFormBean,String start,String end ) {
		StringBuffer  sql=new StringBuffer ();
		String stcd=resultFormBean.getResultBean().getStcd();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, ISNULL(R.Q, 0 ) LL FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM  ");
		if(start!=null||end!=null) {
			sql.append(" WHERE C.YML_TIME BETWEEN '"+start+"' AND '"+end+"' ");
		}
		if(stcd!=null||!stcd.equals("")) {
			sql.append("  AND C.STCD='"+stcd+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getOffset(),resultFormBean.getPageBean().getLimit(), null);
	}
	
	/**
	 * 根据时间查询降雨量
	 */
	public PageResults getyml(ResultFormBean resultFormBean,String start,String end ) {
		StringBuffer  sql=new StringBuffer ();
		String stcd=resultFormBean.getResultBean().getStcd();
		sql.append("SELECT 	CONVERT(CHAR(16),  C.YML_TIME, 120)DATE, ISNULL(C.YML, 0 )  JYL  FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM ");
		if(start!=null||end!=null) {
			sql.append(" WHERE C.YML_TIME BETWEEN '"+start+"' AND '"+end+"' ");
		}
		if(stcd!=null||!stcd.equals("")) {
			sql.append(" AND C.STCD='"+stcd+"'");
		}
		sql.append("  ORDER BY C.YML_TIME ASC  ");
		return this.findPageByFetchedSql(sql.toString(), null, resultFormBean.getPageBean().getOffset(),resultFormBean.getPageBean().getLimit(), null);
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
	

	/**
	 * 流量最大值
	 * @return
	 */
	public List maxLL(ResultFormBean resultFormBean) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT 	MAX(R.Q)  LL FROM C_RESULT C LEFT JOIN ST_RIVER_R R  ON C.YML_TIME=R.TM ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 降雨量最大值
	 * @return
	 */
	public List maxYml(ResultFormBean resultFormBean) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT MAX(YML) JYL FROM C_RESULT");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 退水曲线洪峰个数统计，用来生成json用
	 */
	public List numHongFeng(ResultFormBean resultFormBean) {
		String stcd=resultFormBean.getResultBean().getStcd();
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT B.QM FROM  ST_TSQX_B B WHERE B.STCD='"+stcd+"'   GROUP BY B.QM");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 退水曲线x,y轴对应的时段和流浪查询
	 */
	public PageResults getTsqx(ResultFormBean resultFormBean,TsqxFormBean mTsqxFormBean) {
		String stcd=resultFormBean.getResultBean().getStcd();
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT B.T,B.Q,B.QM FROM  ST_TSQX_B B WHERE B.STCD='"+stcd+"'  GROUP BY B.QM,B.T,B.Q");
		return this.findPageByFetchedSql(sql.toString(), null
				,mTsqxFormBean.getPageBean().getOffset()
				,mTsqxFormBean.getPageBean().getLimit()
				,null);
	}
}
