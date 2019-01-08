package com.lyht.business.policy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.policy.bean.ZcfgInfo;
import com.lyht.business.policy.formbean.ZcfgInfoFormBean;

@Repository
@Scope("prototype")
@SuppressWarnings("all")
public class ZcfgDao extends HibernateBaseDao<ZcfgInfo>{

	/**
	 * 获取政策法规信息
	 * @param searchName 
	 * @param endDate 
	 * @param startDate 
	 * @param ssbm 
	 * @param fgly 
	 * @param mZcfgInfoFormBean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageResults getZcfgListData(int page, int limit, String fgly, String ssbm, String startDate, String endDate, String searchName) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT info.fgbm as fgbm,s.name as ly,ss.name as ssfl,info.zdjg as zdjg,info.wh as wh,info.pbrq as pbrq  ");
		sql.append(",info.ssrq as ssrq,info.bt as bt,info.zt as zt,info.bz as bz,c.ccdz as url  ");
		sql.append(" FROM zcfg_info info LEFT JOIN sys_dict s ON info.ly = s.nm AND s.listnm_sys_dict_cate = 'zcfg'  " );
		sql.append(" LEFT JOIN sys_dict ss ON info.ssfl = ss.nm AND ss.listnm_sys_dict_cate = 'ssbm' left join zcfg_fj c on info.fgbm = c.code WHERE 1 = 1  ");
		if(fgly != null && !"0".equals(fgly))sql.append(" AND  s.nm = '"+fgly+"' ");
		if(ssbm != null && !"0".equals(ssbm))sql.append(" AND  ss.nm = '"+ssbm+"' ");
		if(startDate != null && !"".equals(startDate))sql.append(" AND  info.pbrq >= '"+startDate+"' ");
		if(endDate != null && !"".equals(endDate))sql.append(" AND  info.pbrq <= '"+endDate+"' ");
		if(searchName != null && !"".equals(searchName)){
			sql.append(" AND (s.name like '%"+searchName+"%' or  ss.name like '%"+searchName+"%'  or info.bt like '%"+searchName+"%' or info.wh like '%"+searchName+"%') ");
		}
			
		sql.append("  ORDER BY info.pbrq  DESC");
		return this.findPageByFetchedSql(sql.toString(), null,page,limit,null);
	}
	
	/**
	 * 根据政策法规编码查询政策法规
	 * @param mZcfgInfoFormBean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PageResults findOne(ZcfgInfoFormBean mZcfgInfoFormBean) {
			StringBuilder sql=new StringBuilder();
			sql.append(" SELECT a.bt,a.bz,a.fgbm,a.ly,a.pbrq,a.ssfl,a.ssrq,a.wh,a.zdjg,a.zt,b.fjmc FROM zcfg_info a left join zcfg_fj b on a.fgbm = b.code WHERE a.fgbm = '"+mZcfgInfoFormBean.getZcfgInfo().getFgbm()+"' ");
			return this.findPageByFetchedSql(sql.toString(), null,0,10,null);
	}
	
	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public PageResults getZcfgListData(ZcfgInfoFormBean mZcfgInfoFormBean) {
		StringBuilder sql=new StringBuilder();
		ArrayList parmValue=new ArrayList();
		sql.append(" SELECT info.fgbm as fgbm,info.ly as ly,info.ssfl as ssfl,info.zdjg as zdjg,info.wh as wh,info.pbrq as pbrq ");
		sql.append(",info.ssrq as ssrq,info.bt as bt,info.zt as zt,info.bz as bz FROM zcfg_info info WHERE 1 = 1 ");
		if(mZcfgInfoFormBean.getSearchName() != null && !"".equals(mZcfgInfoFormBean.getSearchName().trim())){
			sql.append(" AND (info.ly LIKE '%"+mZcfgInfoFormBean.getSearchName().trim()+"%' OR info.ssfl LIKE '%"+mZcfgInfoFormBean.getSearchName().trim()+"%' OR ");
			sql.append(" info.zdjg LIKE '%"+mZcfgInfoFormBean.getSearchName().trim()+"%' OR info.wh LIKE '%"+mZcfgInfoFormBean.getSearchName().trim()+"%'  ");
			sql.append(" OR info.bt LIKE '%"+mZcfgInfoFormBean.getSearchName().trim()+"%' OR info.bz LIKE '%"+mZcfgInfoFormBean.getSearchName().trim()+"%') ");
		}
		sql.append(" ORDER BY info.fgbm ");
		PageResults retValue =new PageResults();
		retValue = this.findPageByFetchedSql(sql.toString(), null
				,mZcfgInfoFormBean.getPageBean().getOffset()
				,mZcfgInfoFormBean.getPageBean().getLimit()
				,parmValue.toArray());
		return retValue;
	}

	/**
	 * 根据政策法规编码查询政策法规信息
	 * @param fgbm
	 * @return
	 */
	@SuppressWarnings("all")
	public List<Map> findOne(String fgbm) {
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT a.bt,a.bz,a.fgbm,a.ly,a.pbrq,a.ssfl,a.ssrq,a.wh,a.zdjg,a.zt,b.fjmc FROM zcfg_info a left join zcfg_fj b on a.fgbm = b.code WHERE a.fgbm = '"+fgbm+"' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 查询政策法规文件来源
	 * @param condition 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> initWjly(String condition) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT nm, name FROM sys_dict WHERE listnm_sys_dict_cate = '"+condition+"' ");
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 根据政策法规编码修改政策法规状态
	 * @param zt
	 * @param fgbm
	 */
	public void auditZcfgInfo(int zt,String fgbm) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE zcfg_info SET zt = '"+zt+"' WHERE fgbm = '"+fgbm+"' ");
		this.exectueSQL(sql.toString());
	}

	/**
	 * 删除法规政策
	 * @return
	 */
	public boolean delete(String fgbm) {
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM zcfg_info WHERE fgbm = '"+fgbm+"' ");
		int delete = this.exectueSQL(sql.toString());
		if(delete == 1){
			return true;
		}
		return false;
	}


	/**
	 * 保存政策法规信息
	 * @param mZcfgInfoFormBean
	 * @param path
	 * @param name 
	 * @return
	 */
	public boolean saveZcfg(ZcfgInfoFormBean mZcfgInfoFormBean, String path, String name) {
		StringBuilder sql=new StringBuilder();
		sql.append(" INSERT INTO zcfg_info ([fgbm], [ly], [ssfl], [zdjg], [wh], [pbrq], [ssrq], [bt], [zt], [bz]) ");
		sql.append(" VALUES ('"+mZcfgInfoFormBean.getZcfgInfo().getFgbm()+"', '"+mZcfgInfoFormBean.getZcfgInfo().getLy()+"', ");
		sql.append(" '"+mZcfgInfoFormBean.getZcfgInfo().getSsfl()+"', '"+mZcfgInfoFormBean.getZcfgInfo().getZdjg()+"', ");
		sql.append(" '"+mZcfgInfoFormBean.getZcfgInfo().getWh()+"', '"+mZcfgInfoFormBean.getZcfgInfo().getPbrq()+"', ");
		sql.append(" '"+mZcfgInfoFormBean.getZcfgInfo().getSsrq()+"', '"+mZcfgInfoFormBean.getZcfgInfo().getBt()+"', ");
		sql.append(" '"+mZcfgInfoFormBean.getZcfgInfo().getZt()+"', '"+mZcfgInfoFormBean.getZcfgInfo().getBz()+"' ) ");
		int save = this.exectueSQL(sql.toString());
		StringBuilder sql1=new StringBuilder();
		sql1.append(" INSERT INTO [zcfg_fj] ([code], [ccdz],[fjmc]) VALUES ( ");
		sql1.append(" '"+mZcfgInfoFormBean.getZcfgInfo().getFgbm()+"' , '"+path+"' ,'"+name+"') ");
		int save1 = this.exectueSQL(sql1.toString());
		if(save == 1 && save1 == 1){
			return true;
		}
		return false;
	}

	/**
	 * 修改政策法规信息
	 * @param mZcfgInfoFormBean
	 * @param path
	 * @param name 
	 * @return
	 */
	public boolean updateZcfg(ZcfgInfoFormBean mZcfgInfoFormBean, String path, String name) {
		StringBuilder sql=new StringBuilder();
		sql.append(" UPDATE zcfg_info SET  [ly] = '"+mZcfgInfoFormBean.getZcfgInfo().getLy()+"', ");
		sql.append("  [ssfl] = '"+mZcfgInfoFormBean.getZcfgInfo().getSsfl()+"', [zdjg] =  '"+mZcfgInfoFormBean.getZcfgInfo().getZdjg()+"',");
		sql.append("  [wh] = '"+mZcfgInfoFormBean.getZcfgInfo().getWh()+"', [pbrq] = '"+mZcfgInfoFormBean.getZcfgInfo().getPbrq()+"', ");
		sql.append("  [ssrq] = '"+mZcfgInfoFormBean.getZcfgInfo().getSsrq()+"', [bt] = '"+mZcfgInfoFormBean.getZcfgInfo().getBt()+"',");
		sql.append("  [zt] ='"+mZcfgInfoFormBean.getZcfgInfo().getZt()+"' , [bz] = '"+mZcfgInfoFormBean.getZcfgInfo().getBz()+"'");
		sql.append(" WHERE fgbm = '"+mZcfgInfoFormBean.getZcfgInfo().getFgbm()+"' ");
		int save = this.exectueSQL(sql.toString());
		int save1= 0;
		StringBuilder sql1=new StringBuilder();
		if(path != null){
			List<Map> dzListData = this.findOne(mZcfgInfoFormBean.getZcfgInfo().getFgbm());
			if(dzListData.get(0).get("fjmc") != null){
				sql1.append(" UPDATE [zcfg_fj] SET  [ccdz] = '"+path+"' ,[fjmc] = '"+name+"' ");
				sql1.append(" WHERE code = '"+mZcfgInfoFormBean.getZcfgInfo().getFgbm()+"' ");
			}else{
				sql1.append(" INSERT INTO [zcfg_fj] ([code], [ccdz],[fjmc]) VALUES ( ");
				sql1.append(" '"+mZcfgInfoFormBean.getZcfgInfo().getFgbm()+"' , '"+path+"' ,'"+name+"') ");
			}
			save1 = this.exectueSQL(sql1.toString());
		}else{
			save1= 1;
		}
		if(save == 1 && save1 == 1){
			return true;
		}
		return false;
	}



}
