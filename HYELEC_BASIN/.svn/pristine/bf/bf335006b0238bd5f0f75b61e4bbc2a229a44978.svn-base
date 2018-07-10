package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysGroup;
import com.lyht.business.system.formbean.SysGroupFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysGroupDao extends HibernateBaseDao<SysGroup>{

	/**
	 * 获取分组管理列表数据
	 * */
	public PageResults getSysGroupListData(SysGroupFormBean mSysGroupFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,");
		sql.append("REMARK,STATE FROM SYS_GROUP WHERE 1=1 ");
		String str=spliceStrGroup(mSysGroupFormBean);
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY ID");
		return this.findPageByFetchedSql(sql.toString(), null
				,mSysGroupFormBean.getPageBean().getOffset()
				,mSysGroupFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据主键ID获取分组管理实体
	 * */
	public PageResults getSysGroupInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,REMARK,STATE FROM SYS_GROUP WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND ID IN ('"+ids+"')");
		}
		sql.append(" ORDER BY ID");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID删除分组管理实体
	 * */
	public void deleteSysGroupInfoByIds(int ids){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_GROUP WHERE ID IN ('"+ids+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员编号查询分组信息
	 * */
	public List<Map> getSysGroupInfoByStaffCode(String staffCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,REMARK,");
		sql.append("STATE FROM SYS_GROUP WHERE STAFF_CODE like '%"+staffCode+"%' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveSysGroupInfo(SysGroup mSysGroup){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_GROUP (GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,REMARK,STATE) ");
		sql.append("VALUES ('"+mSysGroup.getGroupCode()+"','"+mSysGroup.getGroupName()+"',");
		sql.append("'"+mSysGroup.getBasinCode()+"','"+mSysGroup.getStaffCode()+"',");
		sql.append("'"+mSysGroup.getRemark()+"',1)");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateSysGroupInfo(SysGroup mSysGroup){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_GROUP SET GROUP_CODE='"+mSysGroup.getGroupCode()+"',GROUP_NAME='"+mSysGroup.getGroupName()+"',");
		sql.append("BASIN_CODE='"+mSysGroup.getBasinCode()+"',STAFF_CODE='"+mSysGroup.getStaffCode()+"',");
		sql.append("REMARK='"+mSysGroup.getRemark()+"',STATE='"+mSysGroup.getState()+"' ");
		sql.append("WHERE ID='"+mSysGroup.getId()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员编号修改分组信息的人员编号
	 * */
	public void updateSysGroupByStaffCode(String groupCode,String staffCode_){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_GROUP SET STAFF_CODE='"+staffCode_+"'");
		sql.append(" WHERE GROUP_CODE = '"+groupCode+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据流域编号修改分组信息的流域编号
	 * */
	public void updateSysRoleByBasinCode(String basinCode,String groupCode){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_GROUP SET BASIN_CODE='"+basinCode+"'");
		sql.append(" WHERE GROUP_CODE = '"+groupCode+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	public List<Map> getSysGroupListById(int id){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,");
		sql.append("ltrim(rtrim(REMARK)) AS REMARK,STATE FROM SYS_GROUP WHERE ID='"+id+"'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据分组编号获取实体
	 * */
	public String getSysGroupInfoByCode(String groupCode){
		String str="";
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,");
		sql.append("ltrim(rtrim(REMARK)) AS REMARK,STATE FROM SYS_GROUP ");
		sql.append("WHERE GROUP_CODE='"+groupCode+"'");
		List<Map> list=this.createSQLQuerybyMap(sql.toString());
		if(list.size()>0){
			Object obj=list.get(0).get("GROUP_NAME");
			if(null!=obj || !"".equals(obj)){
				str=obj.toString();
			}
		}
		return str;
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@SuppressWarnings("unchecked")
	public SysGroup getSysGroupInfoById(int id){
		SysGroup mSysGroup=new SysGroup();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,ltrim(rtrim(REMARK)) AS REMARK,STATE FROM SYS_GROUP WHERE ID='"+id+"'");
		List<SysGroup> mSysGroupList=null;
		try{
			mSysGroupList=this.getSession().createSQLQuery(sql.toString()).addEntity(SysGroup.class).list();
			if(mSysGroupList.size()>0){
				mSysGroup=mSysGroupList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSysGroup;
	}
	
	/**
	 * 根据分组信息主键id修改分组信息中的流域编号
	 * */
	public boolean updateSysGroupBasinCodeByBasinCode(String ids,String basinCode){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_GROUP SET BASIN_CODE='"+CommonUtil.trim(basinCode)+"' WHERE ID='"+ids+"'");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据流域编号获取分组信息
	 * */
	public List<Map> getSysGroupInfoByRvcd(String rvcd){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,GROUP_CODE,GROUP_NAME,BASIN_CODE,STAFF_CODE,");
		sql.append("REMARK,STATE FROM SYS_GROUP WHERE BASIN_CODE LIKE '%"+rvcd+"%' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据分组信息主键id修改分组信息中的人员编号
	 * */
	public boolean updateSysGroupStaffCodeBySysGroupId(String ids,String staffCode){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_GROUP SET STAFF_CODE='"+staffCode+"' WHERE ID='"+ids+"'");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	
	/**
	 * 根据条件查询分组信息
	 * */
	private String spliceStrGroup(SysGroupFormBean mSysGroupFormBean){
		StringBuilder sql =new StringBuilder();
		if(null!=mSysGroupFormBean){
			if(CommonUtil.trim(mSysGroupFormBean.getSearchName()).length()>0){
				sql.append("AND ((GROUP_CODE LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getSearchName())+"%') ");
				sql.append("OR (GROUP_NAME LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getSearchName())+"%') ");
				sql.append("OR (BASIN_CODE LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getSearchName())+"%') ");
				sql.append("OR (STAFF_CODE LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getSearchName())+"%') ");
				sql.append("OR (REMARK LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getSearchName())+"%') ");
				sql.append("OR (STATE = '"+CommonUtil.trim(mSysGroupFormBean.getSearchName())+"')) ");
			}
			if(null!=mSysGroupFormBean.getmSysGroupInfoBean()){
				if(CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getGroupCode()).length()>0){
					sql.append(" AND GROUP_CODE LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getGroupCode())+"%'");
				}
				if(CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getGroupName()).length()>0){
					sql.append(" AND GROUP_NAME LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getGroupName())+"%'");
				}
				if(CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getBasinCode()).length()>0){
					sql.append(" AND BASIN_CODE LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getBasinCode())+"%'");
				}
				if(CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getStaffCode()).length()>0){
					sql.append(" AND STAFF_CODE LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getStaffCode())+"%'");
				}
				if(CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getRemark()).length()>0){
					sql.append(" AND REMARK LIKE '%"+CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getRemark())+"%'");
				}
				if(CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getState()).length()>0){
					sql.append(" AND STATE = '"+CommonUtil.trim(mSysGroupFormBean.getmSysGroupInfoBean().getState())+"'");
				}
			}
		}
		return sql.toString();
	}
	
}
