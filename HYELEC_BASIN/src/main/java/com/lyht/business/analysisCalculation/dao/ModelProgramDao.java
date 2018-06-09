package com.lyht.business.analysisCalculation.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ModelProgram;
import com.lyht.business.analysisCalculation.formbean.ModelProgramFromBean;
import com.lyht.business.modelManage.bean.ModelInfo;
import com.lyht.business.modelManage.formbean.ModelInfoFormBean;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.CommonUtil;
/**
 *作者： 刘魁
 *脚本日期:2018年5月11日 21:41:11
 *说明:  模型方案Dao
*/
@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ModelProgramDao extends HibernateBaseDao<ModelProgram>  {

	/**
	 * 查找模型方案
	 */
	public PageResults getModelProgram(ModelProgramFromBean mpBean,ModelInfoFormBean modelInfoFormBean ,SysStaff  mSysStaff) {
		StringBuilder sql=new StringBuilder();
		String str=spliceStrModelProg(mpBean);
		String str1=spliceStrModelInfo(modelInfoFormBean);
		sql.append("SELECT A.PROG_CODE,A.MODEL_CODE,A.PROG_NAME,A.CREATE_STAFF,A.CREATE_TIME,A.REMARK, ");
		sql.append("B.MODEL_NAME FROM MODEL_PROGRAM A LEFT JOIN MODEL_INFO B ON A.MODEL_CODE=B.MODEL_CODE WHERE 1=1 ");
		if(mSysStaff!=null) {
			if(mSysStaff.getStaffCode()!=null||!mSysStaff.getStaffCode().equals("")) {
				sql.append("AND  A.CREATE_STAFF='"+mSysStaff.getStaffCode()+"'");
			}
		}
		if(null!=str1 && !"".equals(str1)){
			sql.append(str1);
		}
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY CREATE_TIME DESC");
		return this.findPageByFetchedSql(sql.toString(), null, mpBean.getPageBean().getOffset(),mpBean.getPageBean().getLimit(), null);
	}
	
	/**
	 * 根据主键删除模型方案
	 */
	public void delModelPgm(String ids) {
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM MODEL_PROGRAM WHERE PROG_CODE IN ('"+ids+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据主键ID获取实体
	 * @return PageResults
	 * */
	public PageResults getModelpgm(String id) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT PROG_CODE,MODEL_CODE,PROG_NAME,CREATE_STAFF,CREATE_TIME,REMARK  FROM MODEL_PROGRAM  WHERE 1=1 ");
		if(id.length()>0){
			sql.append(" AND PROG_CODE ='"+id+"'");
		}
		sql.append(" ORDER BY PROG_CODE");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据PROG_CODE获取实体
	 */
	@SuppressWarnings("unchecked")
	public ModelProgram  getModelProgramById(String id) {
		ModelProgram modelProgram=new ModelProgram();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT PROG_CODE,MODEL_CODE,PROG_NAME,CREATE_STAFF,CREATE_TIME,REMARK  FROM MODEL_PROGRAM WHERE PROG_CODE= '"+id+"'");
		List<ModelProgram> modelList=null;
		try{
			modelList=this.getSession().createSQLQuery(sql.toString()).addEntity(ModelInfo.class).list();
			for(int i=0;i<modelList.size();i++){
				modelProgram=modelList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return modelProgram;
	}
	/**
	 * 新增方案
	 */
	public void saveModelpgm(ModelProgram modelProgram) {
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO MODEL_PROGRAM (PROG_CODE,MODEL_CODE,PROG_NAME,CREATE_STAFF,CREATE_TIME,REMARK) ");
		sql.append("VALUES ('"+modelProgram.getProgCode()+"', '"+modelProgram.getModelCode()+"'");
		sql.append(",'"+modelProgram.getProgName()+"', '"+modelProgram.getCreateStaff()+"'");
		sql.append(",'"+modelProgram.getCreateTime()+"','"+modelProgram.getRemark()+"');");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 修改方案
	 */
	public void update(ModelProgram modelProgram) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE MODEL_PROGRAM SET MODEL_CODE='"+modelProgram.getModelCode()+"', ");
		sql.append("PROG_NAME='"+modelProgram.getProgName()+"' ,CREATE_STAFF='"+modelProgram.getCreateStaff()+"',");
	    sql.append("CREATE_TIME='"+modelProgram.getCreateTime()+"',REMARK='"+modelProgram.getRemark()+"'");
		sql.append("WHERE PROG_CODE='"+modelProgram.getProgCode()+"' ");
	    this.exectueSQL(sql.toString());				
	}
	/**
	 * 根据条件查询
	 */
	private String spliceStrModelProg(ModelProgramFromBean mpBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mpBean){
			if(CommonUtil.trim(mpBean.getSearchName()).length()>0){
				sql.append("AND ((A.PROG_NAME LIKE '%"+CommonUtil.trim(mpBean.getSearchName())+"%') ");
				sql.append("OR (A.CREATE_TIME LIKE '%"+CommonUtil.trim(mpBean.getSearchName())+"%') ");
				sql.append("OR (A.REMARK LIKE '%"+CommonUtil.trim(mpBean.getSearchName())+"%')) ");
			}
		}	if(null!=mpBean.getModelprogramFormBean()){
			if(CommonUtil.trim(mpBean.getModelprogramFormBean().getProgName()).length()>0){
				sql.append(" AND A.PROG_NAME LIKE '%"+CommonUtil.trim(mpBean.getModelprogramFormBean().getProgName())+"%'");
				}
			if(CommonUtil.trim(mpBean.getModelprogramFormBean().getCreateTime()).length()>0){
				sql.append(" AND A.CREATE_TIME LIKE '%"+CommonUtil.trim(mpBean.getModelprogramFormBean().getCreateTime())+"%'");
				}
			if(CommonUtil.trim(mpBean.getModelprogramFormBean().getRemark()).length()>0){
				sql.append(" AND A.REMARK LIKE '%"+CommonUtil.trim(mpBean.getModelprogramFormBean().getRemark())+"%'");
				}
		    }
		return sql.toString();
	}
	
	private String spliceStrModelInfo(ModelInfoFormBean mInfoBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mInfoBean){
			if(CommonUtil.trim(mInfoBean.getSearchName()).length()>0){
				sql.append("AND ((B.MODEL_CODE LIKE '%"+CommonUtil.trim(mInfoBean.getSearchName())+"%') ");
		
			}	if(null!=mInfoBean.getModelInfoFormBean()){
					if(CommonUtil.trim(mInfoBean.getModelInfoFormBean().getModelCode()).length()>0){
					sql.append(" AND B.MODEL_CODE LIKE '%"+CommonUtil.trim(mInfoBean.getModelInfoFormBean().getModelCode())+"%'");
					}
			}
		}
		return sql.toString();
	}
}
