package com.lyht.business.analysisCalculation.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ResultFourth;
import com.lyht.business.analysisCalculation.formbean.ResultFourthFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.Randomizer;
/**
 *产流计算第4步计算结果保存DAO
 * @author 刘魁
 */
@Repository
@Scope("prototype")
public class ResultFourthDao extends HibernateBaseDao<ResultFourth> {
	
		/**
		 * 保存计算结果3
		 */
	public void saveResutFourth(ResultFourth ResutFourth) {
		StringBuilder sql=new StringBuilder();
			String id=Randomizer.nextNumber("rtf", 5);
		sql.append("INSERT INTO C_RESULT_FOURTH (TM,INTERVAL,QDX,QT,ID,STCD) ");
		sql.append("VALUES('"+ResutFourth.getTm()+"','"+CommonUtil.trim(ResutFourth.getInterval())+"','"+CommonUtil.trim(ResutFourth.getQdx())+"',");
		sql.append("'"+ResutFourth.getQt()+"','"+CommonUtil.trim(id)+"','"+ResutFourth.getStcd()+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据时间获取单条数据
	 * @param ResutFourth
	 * @return
	 */
	public ResultFourth getResutFourthByTm(ResultFourth ResutFourth) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM C_RESULT_FOURTH WHERE TM='"+ResutFourth.getTm()+"'");
		List<ResultFourth> ResutFourthList=null;
		ResultFourth thrid=new ResultFourth();
		try{
			ResutFourthList=this.getSession().createSQLQuery(sql.toString()).addEntity(ResultFourth.class).list();
			for(int i=0;i<ResutFourthList.size();i++){
				thrid=ResutFourthList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return thrid;
	}
	
	/**
	 * 获取table数据
	 */
	public PageResults getStep4Table(ResultFourthFormBean ResutFourth) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT CONVERT(CHAR(16),  C.TM, 120)DATE,C.INTERVAL SC,C.QDX LL,C.QT QT FROM C_RESULT_FOURTH C ORDER BY C.TM ASC");
		return this.findPageByFetchedSql(sql.toString(), null, ResutFourth.getPageBean().getOffset(),ResutFourth.getPageBean().getLimit(), null);
	}
	
	/**
	 * 流量(地下)求和 table
	 */
	public List  sumQdx(ResultFourthFormBean ResutFourth) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT SUM(C.QDX) LL FROM C_RESULT_FOURTH C WHERE C.STCD='"+ResutFourth.getResultFourth().getStcd()+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/** 
	 * Q*T求和 table
	 */
	public List  sumQT(ResultFourthFormBean ResutFourth) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT SUM(C.QT) QT FROM C_RESULT_FOURTH C WHERE C.STCD='"+ResutFourth.getResultFourth().getStcd()+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	

	
	/**
	 * 汇流计算第一步 查询方法
	 */
	public PageResults getHuiLiuStep1(ResultFourthFormBean rf) {
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT  CONVERT(CHAR(16),  C.TM, 120) DATE,ISNULL(B.Q, 0 ) LL,  C.QDX LLDX FROM C_RESULT_FOURTH C LEFT JOIN    ");
		sql.append("C_RESULT_THRID B ON C.STCD=B.STCD AND C.TM=B.TM ORDER BY C.TM ASC");
		 return this.findPageByFetchedSql(sql.toString(), null, rf.getPageBean().getOffset(),rf.getPageBean().getLimit(), null);
	}
}
