package com.lyht.business.analysisCalculation.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.ResultJg;
import com.lyht.util.Randomizer;
/**
 * ResultJgDao
 * @author 刘魁
 *
 */
@Repository
@Scope("prototype")
public class ResultJgDao extends HibernateBaseDao<ResultJg>{
	//保存
	public void saveResultJg(ResultJg rj) {
		this.delResultJg(rj);
		StringBuilder sql=new StringBuilder();
		String id=java.util.UUID.randomUUID().toString();
		sql.append("INSERT INTO C_RESULT_JG (ID,STCD,PCH,SJJG)  ");
		sql.append("VALUES('"+id+"','"+rj.getSTCD()+"','"+rj.getPch()+"','"+rj.getSJJG()+"')  ");
		this.exectueSQL(sql.toString());
	}
	/**
	 * 删除
	 */
	public void delResultJg(ResultJg rj) {
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM C_RESULT_JG WHERE SJJG="+rj.getSJJG()+"AND STCD='"+rj.getSTCD()+"' AND PCH='"+rj.getPch()+"' ");
		this.exectueSQL(sql.toString());
	}
	
}
