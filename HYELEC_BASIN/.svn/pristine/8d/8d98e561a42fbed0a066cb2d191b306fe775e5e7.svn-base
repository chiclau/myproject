package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Soil;
import com.lyht.business.consumer.hydrologicalData.formbean.SoilFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;
import com.lyht.util.DesUtils;
import com.lyht.util.Randomizer;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SoilDao extends HibernateBaseDao<Soil>{

	/**
	 * 获取列表数据
	 * */
	public PageResults getSoilListData(SoilFormBean mSoilFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrSoil(mSoilFormBean);
		sql.append("SELECT soil.STCD,soil.TM,soil.EXKEY,soil.VTAVSLM,soil.SRLSLM,soil.SLM10,");
		sql.append(" soil.SLM20,soil.SLM30,soil.SLM40,soil.SLM50,");
		sql.append(" soil.SLM60,soil.CRPTY,soil.CRPGRWPRD,soil.HITRSN,soil.HITEXT,soil.SLTP,");
		sql.append(" soil.SLPQ,soil.DRSLD,soil.IRRINTV,soil.PINTV,soil.SLMMMT,b.STNM AS STNM ");
		sql.append(" FROM ST_SOIL_R AS soil ");
		sql.append(" LEFT JOIN ST_STBPRP_B AS b ON b.STCD = soil.STCD WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY soil.STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mSoilFormBean.getPageBean().getOffset()
				,mSoilFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 获取列表数据用于导出
	 * */
	public PageResults getSoilListData_export(SoilFormBean mSoilFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrSoil(mSoilFormBean);
		sql.append("SELECT STCD,TM,VTAVSLM,CRPTY,SLTP,DRSLD,SLMMMT FROM ST_SOIL_R WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null
				,mSoilFormBean.getPageBean().getOffset()
				,mSoilFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	public PageResults getSoilInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,TM,EXKEY,VTAVSLM,SRLSLM,SLM10,SLM20,SLM30,SLM40,SLM50,"
				+ "SLM60,CRPTY,CRPGRWPRD,HITRSN,HITEXT,SLTP,SLPQ,DRSLD,IRRINTV,PINTV,SLMMMT FROM ST_SOIL_R WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND STCD IN ('"+ids+"')");
		}
		sql.append(" ORDER BY STCD");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID删除实体
	 * */
	public void deletSoilInfoByIds(String ids,String ids_,String _ids){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM ST_SOIL_R WHERE STCD = '"+ids+"' AND TM='"+ids_+"' AND EXKEY='"+_ids+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveSoilInfo(Soil mSoil){
		String sql=execQL(mSoil); //执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateSoilInfo(Soil mSoil){
		String sql = updateById(mSoil);//执行sql语句操作
		this.exectueSQL(sql);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public Soil getSoilInfoById(SoilFormBean mSoilFormBean){
		Soil mSoil=new Soil();
		String sql=execById(mSoilFormBean);//执行sql语句操作
		List<Soil> mSoilList=null;
		try{
			mSoilList=this.getSession().createSQLQuery(sql.toString()).addEntity(Soil.class).list();
			for(int i=0;i<mSoilList.size();i++){
				mSoil=mSoilList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSoil;
	}
	
	
	/**
	 * 根据条件查询代码
	 * */
	private String spliceStrSoil(SoilFormBean mSoilFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mSoilFormBean){
			if(CommonUtil.trim(mSoilFormBean.getSearchName()).length()>0){
				sql.append("AND ((soil.STCD LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%') ");
				sql.append("OR (TM LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%') ");
				sql.append("OR (soil.VTAVSLM LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%') ");
				sql.append("OR (soil.CRPTY LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%') ");
				sql.append("OR (soil.SLTP  LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%') ");
				sql.append("OR (soil.DRSLD LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%') ");
				sql.append("OR (soil.SLMMMT LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%') ");
				sql.append("OR (STNM LIKE '%"+CommonUtil.trim(mSoilFormBean.getSearchName())+"%')) ");
			}
			if(null!=mSoilFormBean.getmSoilInfoBean()){
				if(CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getStcd()).length()>0){
					sql.append(" AND soil.STCD = '"+CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getStcd())+"'");
				}
				if(CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getTm()).length()>0){
					sql.append(" AND soil.TM = '"+CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getTm())+"'");
				}
				if(CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getCrpty()).length()>0){
					sql.append(" AND soil.CRPTY = '"+CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getCrpty())+"'");
				}
				if(CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getSltp()).length()>0){
					sql.append(" AND soil.SLTP = '"+CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getSltp())+"'");
				}
				if(CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getSlmmmt()).length()>0){
					sql.append(" AND soil.SLMMMT = '"+CommonUtil.trim(mSoilFormBean.getmSoilInfoBean().getSlmmmt())+"'");
				}
			}
		}
		return sql.toString();
	}
	
	/**
	 * 执行新增语句
	 * */
	private String execQL(Soil mSoil){
		String EXKEY=Randomizer.nextLowerString(1);
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO ST_SOIL_R (STCD,TM,EXKEY,VTAVSLM,SRLSLM,SLM10,SLM20,SLM30,SLM40,SLM50,");
		sql.append("SLM60,CRPTY,CRPGRWPRD,HITRSN,HITEXT,SLTP,SLPQ,DRSLD,IRRINTV,PINTV,SLMMMT) ");
		sql.append("VALUES ('"+CommonUtil.trim(mSoil.getStcd())+"','"+DateUtil.ConvertTimestamp(mSoil.getTm())+"',");
		sql.append("'"+CommonUtil.trim(EXKEY)+"','"+CommonUtil.trim(mSoil.getVtavslm())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getSrlslm())+"','"+CommonUtil.trim(mSoil.getSlm10())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getSlm20())+"','"+CommonUtil.trim(mSoil.getSlm30())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getSlm40())+"','"+CommonUtil.trim(mSoil.getSlm50())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getSlm60())+"','"+CommonUtil.trim(mSoil.getCrpty())+"',");
		sql.append( "'"+CommonUtil.trim(mSoil.getCrpgrwprd())+"','"+CommonUtil.trim(mSoil.getHitrsn())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getHitext())+"','"+CommonUtil.trim(mSoil.getSltp())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getSlpq())+"','"+CommonUtil.trim(mSoil.getDrsld())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getIrrintv())+"','"+CommonUtil.trim(mSoil.getPintv())+"',");
		sql.append("'"+CommonUtil.trim(mSoil.getSlmmmt())+"')");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行查询语句
	 * */
	private String execById(SoilFormBean mSoilFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT STCD,TM,EXKEY,VTAVSLM,SRLSLM,SLM10,SLM20,SLM30,SLM40,SLM50,");
		sql.append("SLM60,CRPTY,CRPGRWPRD,HITRSN,HITEXT,SLTP,SLPQ,DRSLD,IRRINTV,PINTV,");
		sql.append("SLMMMT FROM ST_SOIL_R WHERE STCD='"+mSoilFormBean.getmSoilInfoBean().getStcd()+"' ");
		sql.append(" AND TM='"+mSoilFormBean.getmSoilInfoBean().getTm()+"' ");
		sql.append(" AND EXKEY='"+mSoilFormBean.getmSoilInfoBean().getExkey()+"'");
		return sql.toString();
	}
	
	/**
	 * 根据ID执行修改语句
	 * */
	private String updateById(Soil mSoil){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE ST_SOIL_R SET STCD = '"+CommonUtil.trim(mSoil.getStcd())+"', TM = '"+CommonUtil.trim(mSoil.getTm())+"' , EXKEY = '"+CommonUtil.trim(mSoil.getExkey())+"', ");
		sql.append(" VTAVSLM = '"+CommonUtil.trim(mSoil.getVtavslm())+"', SRLSLM = '"+CommonUtil.trim(mSoil.getSrlslm())+"', SLM10 = '"+CommonUtil.trim(mSoil.getSlm10())+"',");
		sql.append(" SLM20 = '"+CommonUtil.trim(mSoil.getSlm20())+"' , SLM30 = '"+CommonUtil.trim(mSoil.getSlm30())+"', ");
		sql.append(" SLM40 = '"+CommonUtil.trim(mSoil.getSlm40())+"', SLM50 = '"+CommonUtil.trim(mSoil.getSlm50())+"', ");
		sql.append(" SLM60 = '"+CommonUtil.trim(mSoil.getSlm60())+"', CRPTY = '"+CommonUtil.trim(mSoil.getCrpty())+"',");
		sql.append(" CRPGRWPRD = '"+CommonUtil.trim(mSoil.getCrpgrwprd())+"', HITRSN = '"+CommonUtil.trim(mSoil.getHitrsn())+"', ");
		sql.append(" HITEXT = '"+CommonUtil.trim(mSoil.getHitext())+"', SLTP = '"+CommonUtil.trim(mSoil.getSltp())+"', ");
		sql.append(" SLPQ = '"+CommonUtil.trim(mSoil.getSlpq())+"', DRSLD = '"+CommonUtil.trim(mSoil.getDrsld())+"',");
		sql.append(" IRRINTV = '"+CommonUtil.trim(mSoil.getIrrintv())+"', PINTV = '"+CommonUtil.trim(mSoil.getPintv())+"', ");
		sql.append(" SLMMMT = '"+CommonUtil.trim(mSoil.getSlmmmt())+"' WHERE STCD = '"+CommonUtil.trim(mSoil.getStcd())+"' ");
		sql.append(" AND TM='"+CommonUtil.trim(mSoil.getTm())+"' AND EXKEY='"+CommonUtil.trim(mSoil.getExkey())+"' ");
		return sql.toString();
	}
}
