package com.lyht.business.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.formbean.SysRoleFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysRoleDao extends HibernateBaseDao<SysRole>{

	/**
	 * 获取角色信息列表数据
	 * */
	public PageResults getSysRoleListData(SysRoleFormBean mSysRoleFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrSysRole(mSysRoleFormBean);
		sql.append("SELECT ID as ID,ROLE_CODE as ROLE_CODE,"
				+ "ROLE_NAME as ROLE_NAME,GROUP_CODE as GROUP_CODE,"
				+ "MENU_CODE as MENU_CODE,STAFF_CODE as STAFF_CODE,");
		sql.append("STATE as STATE,REMARK as REMARK FROM SYS_ROLE WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY ID");
		int pageNo=mSysRoleFormBean.getPageBean().getLimit()>0?mSysRoleFormBean.getPageBean().getOffset()/mSysRoleFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mSysRoleFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mSysRoleFormBean.getPageBean().getPageNo()
				,mSysRoleFormBean.getPageBean().getLimit()
				,null);
	}
	public PageResults querySysRolePageList(SysRoleFormBean mSysRoleFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT A.ID AS ID,A.ROLE_CODE as ROLE_CODE,A.ROLE_NAME AS ROLE_NAME, "
				+ " STUFF(( SELECT ','+ D.STAFF_NAME "
				+ " FROM (SELECT B.ROLE_CODE,C.STAFF_NAME FROM SYS_ROLE_STAFF B LEFT JOIN SYS_STAFF C ON B.STAFF_CODE=C.STAFF_CODE) D  "
				+ " WHERE D.ROLE_CODE = A.ROLE_CODE "
				+ " FOR XML PATH('')),1 ,1, '') STAFF_NAME,"
				+ " STUFF(( SELECT ','+ D.GROUP_NAME "
				+ " FROM (SELECT B.ROLE_CODE,C.GROUP_NAME FROM SYS_ROLE_GROUP B "
				+ " LEFT JOIN SYS_GROUP C ON B.GROUP_CODE=C.GROUP_CODE) D  WHERE D.ROLE_CODE = A.ROLE_CODE"
				+ " FOR XML PATH('')),1 ,1, '') GROUP_NAME "
				+ " from SYS_ROLE A where 1=1 ");
		if(null!=mSysRoleFormBean){
			if(CommonUtil.trim(mSysRoleFormBean.getSearchName()).length()>0){
				sql.append("AND A.ROLE_NAME LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"%' ");
			}
		}
		sql.append(" ORDER BY A.ID");
		int pageNo=mSysRoleFormBean.getPageBean().getLimit()>0?mSysRoleFormBean.getPageBean().getOffset()/mSysRoleFormBean.getPageBean().getLimit():0;
		pageNo=pageNo+1;
		mSysRoleFormBean.getPageBean().setPageNo(pageNo);
		return this.findPageByFetchedSql(sql.toString(), null
				,mSysRoleFormBean.getPageBean().getPageNo()
				,mSysRoleFormBean.getPageBean().getLimit()
				,null);
	}
	/**
	 * 根据主键ID获取角色信息实体
	 * */
	public PageResults getSysRoleInfoListByIds(String ids){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,GROUP_CODE,MENU_CODE,STAFF_CODE,");
		sql.append("STATE,REMARK FROM SYS_ROLE WHERE 1=1 ");
		if(ids.length()>0){
			sql.append(" AND ID IN ('"+ids+"')");
		}
		sql.append(" ORDER BY ID");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	/**
	 * 根据主键ID获取角色信息
	 * */
	public Object getSysRoleInfoListById(String ids){
		Object obj=null;
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,GROUP_CODE,MENU_CODE,STAFF_CODE,");
		sql.append("STATE,REMARK FROM SYS_ROLE WHERE ID ='"+ids+"' ");
		List<Map> list=this.createSQLQuerybyMap(sql.toString());
		if(list.size()>0){
			obj=list.get(0).get("GROUP_CODE");
		}
		return obj;
	}
	
	/**
	 * 根据主键ID删除角色信息实体
	 * */
	public void deleteSysRoleInfoByIds(int ids){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_ROLE WHERE ID IN ('"+ids+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员编号查询角色信息
	 * */
	public SysRole getSysRoleInfoByStaffCode(String staffCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,GROUP_CODE,STAFF_CODE,");
		sql.append("MENU_CODE,STATE,REMARK FROM SYS_ROLE WHERE STAFF_CODE LIKE '%"+staffCode+"%'");
		return getSysRoleInfo(sql);
	}
	
	/**
	 * 增加实体对象
	 * */
	public void saveSysRoleInfo(SysRole mSysRole){
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_ROLE (ROLE_CODE,ROLE_NAME,STATE,REMARK) VALUES ");
		sql.append("('"+CommonUtil.trim(mSysRole.getRoleCode())+"','"+CommonUtil.trim(mSysRole.getRoleName())+"',");
		sql.append("1,'"+CommonUtil.trim(mSysRole.getRemark())+"')");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 修改实体对象
	 * */
	public void updateSysRoleInfo(SysRole mSysRole){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET ROLE_CODE='"+mSysRole.getRoleCode()+"',");
		sql.append("ROLE_NAME='"+mSysRole.getRoleName()+"',GROUP_CODE='"+mSysRole.getGroupCode()+"',");
		sql.append("MENU_CODE='"+mSysRole.getMenuCode()+"',STATE=1,");
		sql.append("STAFF_CODE='"+mSysRole.getStaffCode()+"',");
		sql.append("REMARK='"+mSysRole.getRemark()+"' WHERE ID='"+mSysRole.getId()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	public SysRole getSysRoleInfoById(int id){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,GROUP_CODE,STAFF_CODE,");
		sql.append("MENU_CODE,STATE,REMARK FROM SYS_ROLE WHERE ID='"+id+"'");
		return getSysRoleInfo(sql);
	}
	
	/**
	 * 根据ID修改角色信息
	 * */
	public boolean updateSysRoleInfoById(String ids,String staffCode){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET STAFF_CODE='"+staffCode+"' WHERE ID='"+ids+"'");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	public List<Map> getMenuNumByRoleCode(String roleCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT COUNT(ID) AS MENU_CODE FROM SYS_OPER_REF ");
		sql.append("WHERE REF_A_PK='"+roleCode+"' AND REF_A_NAME='SYS_ROLE' ");
		sql.append("AND REF_B_NAME='SYS_MENU'");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public void updateMenuNumByRoleCode(String roleCode, int menuCode){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET MENU_CODE='"+menuCode+"' WHERE ROLE_CODE='"+roleCode+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据角色编号修改角色信息的人员编号
	 * */
	public void updateSysRoleByStaffCode(String roleCode,String staffCode){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET STAFF_CODE='"+staffCode+"'");
		sql.append(" WHERE ROLE_CODE = '"+roleCode+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据角色编号修改角色信息的分组编号
	 * */
	public void updateSysRoleByGroupCode(String roleCode,String groupCode){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET GROUP_CODE='"+groupCode+"'");
		sql.append(" WHERE ROLE_CODE = '"+roleCode+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据人员编号查询角色信息
	 * */
	public List<Map> getSysRoleByStaffCode(String staffCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,GROUP_CODE,MENU_CODE,STAFF_CODE,");
		sql.append("STATE,REMARK FROM SYS_ROLE WHERE STAFF_CODE like '%"+staffCode+"%' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据分组编号查询角色信息
	 * */
	public List<Map> getSysRoleByGroupCode(String groupCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,GROUP_CODE,MENU_CODE,STAFF_CODE,");
		sql.append("STATE,REMARK FROM SYS_ROLE WHERE GROUP_CODE like '%"+groupCode+"%' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据ID修改角色信息
	 * */
	public boolean updateSysRoleInfoByIds(String ids,String groupCode){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET GROUP_CODE='"+groupCode+"' WHERE ID='"+ids+"'");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据角色编号查询角色信息
	 * */
	public Object getSysRoleInfoByRoleCode(String roleCode){
		Object obj=null;
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,STAFF_CODE,GROUP_CODE,MENU_CODE,STATE,");
		sql.append("REMARK FROM SYS_ROLE WHERE ROLE_CODE='"+roleCode+"' ");
		List<Map> list=this.createSQLQuerybyMap(sql.toString());
		for(int i=0;i<list.size();i++){
			obj=list.get(i).get("MENU_CODE");
		}
		return obj;
	}
	
	/**
	 * 根据角色编号修改菜单编号
	 * */
	public void updateSysRoleByMenuCode(SysMenu mSysMenu,String roleCode){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET MENU_CODE='"+mSysMenu.getMenuCode()+"'");
		sql.append(" WHERE ROLE_CODE='"+roleCode+"'");
	    this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据角色编号修改菜单编号
	 * */
	public void updateSysRoleByMenuCode(String menuCode,String roleCode){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_ROLE SET MENU_CODE='"+menuCode+"'");
		sql.append(" WHERE ROLE_CODE='"+roleCode+"'");
	    this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据菜单编号查询角色信息
	 * */
	public List<Map> getSysRoleByMenuCode(String menuCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,ROLE_CODE,ROLE_NAME,GROUP_CODE,MENU_CODE,STAFF_CODE,");
		sql.append("STATE,REMARK FROM SYS_ROLE WHERE MENU_CODE like '%"+menuCode+"%' ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 根据sql语句获取角色信息
	 * */
	@SuppressWarnings("unchecked")
	private SysRole getSysRoleInfo(StringBuilder sql){
		List<SysRole> mSysRoleList=null;
		SysRole mSysRole=new SysRole();
		try{
			mSysRoleList=this.getSession().createSQLQuery(sql.toString()).addEntity(SysRole.class).list();
			for(int i=0;i<mSysRoleList.size();i++){
				mSysRole=mSysRoleList.get(i);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSysRole;
	}
	
	/**
	 * 根据条件获取角色信息
	 * */
	private String spliceStrSysRole(SysRoleFormBean mSysRoleFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mSysRoleFormBean){
			if(CommonUtil.trim(mSysRoleFormBean.getSearchName()).length()>0){
				sql.append("AND ((ROLE_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"%') ");
				sql.append("OR (ROLE_NAME LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"%') ");
				sql.append("OR (GROUP_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"%') ");
				sql.append("OR (STAFF_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"%') ");
				sql.append("OR (MENU_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"%') ");
				sql.append("OR (STATE = '"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"') ");
				sql.append("OR (REMARK LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getSearchName())+"%')) ");
			}
			if(null!=mSysRoleFormBean.getmSysRoleInfoBean()){
				if(CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getRoleCode()).length()>0){
					sql.append(" AND ROLE_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getRoleCode())+"%'");
				}
				if(CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getRoleName()).length()>0){
					sql.append(" AND ROLE_NAME LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getRoleName())+"%'");
				}
				if(CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getGroupCode()).length()>0){
					sql.append(" AND GROUP_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getGroupCode())+"%'");
				}
				if(CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getStaffCode()).length()>0){
					sql.append(" AND STAFF_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getStaffCode())+"%'");
				}
				if(CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getMenuCode()).length()>0){
					sql.append(" AND MENU_CODE LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getMenuCode())+"%'");
				}
				if(CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getState()).length()>0){
					sql.append(" AND STATE = '"+CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getState())+"'");
				}
				if(CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getRemark()).length()>0){
					sql.append(" AND REMARK LIKE '%"+CommonUtil.trim(mSysRoleFormBean.getmSysRoleInfoBean().getRemark())+"%'");
				}
			}
		}
		return sql.toString();
	}
	
}
