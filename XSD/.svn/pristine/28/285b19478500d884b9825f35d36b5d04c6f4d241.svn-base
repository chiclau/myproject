package com.lyht.business.system.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.bean.SysOperRef;
import com.lyht.business.system.formbean.SysOperRefFormBean;
import com.lyht.util.Randomizer;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysOperRefDao extends HibernateBaseDao<SysOperRef>{

	/**
	 * 根据角色编号查询关联表信息
	 * */
	public PageResults getSysOperRefByRoleCode(SysOperRefFormBean mSysOperRefFormBean){
		String role=mSysOperRefFormBean.getmSysRoleBean().getRoleCode();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,REF_CODE,REF_A_PK, REF_A_NAME,REF_B_PK,");
		sql.append("REF_B_NAME FROM SYS_OPER_REF WHERE REF_A_PK='"+role+"'");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 1000000000, null);
	}
	
	/**
	 * 根据角色编号与菜单编码查询权限表
	 * */
	public PageResults getSysRefByMenuCode(SysMenu mSysMenu,SysOperRefFormBean mSysOperRefFormBean){
		String roleCode=mSysOperRefFormBean.getmSysOperRefBean().getRefApk();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,REF_CODE,REF_A_PK,REF_A_NAME,REF_B_PK,");
		sql.append("REF_B_NAME FROM SYS_OPER_REF ");
	    sql.append("WHERE REF_B_PK='"+mSysMenu.getMenuCode()+"' ");
	    sql.append("AND REF_A_PK='"+roleCode+"'");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 1000000000, null);
	}
	
	/**
	 * 根据角色编号与菜单编码查询权限表
	 * */
	@SuppressWarnings("unchecked")
	public List<SysOperRef> getSysRefByMenuCode(String menuCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,REF_CODE,REF_A_PK,REF_A_NAME,REF_B_PK,");
		sql.append("REF_B_NAME,STATE FROM SYS_OPER_REF ");
	    sql.append("WHERE REF_B_PK='"+menuCode+"' ");
	    sql.append("AND REF_B_NAME='SYS_MENU'");
	    return this.getSession().createSQLQuery(sql.toString()).addEntity(SysOperRef.class).list();
	}
	
	/**
	 * 根据角色编号与角色表名查询权限表
	 * */
	@SuppressWarnings("unchecked")
	public List<SysOperRef> getSysRefByRoleCode(String roleCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,REF_CODE,REF_A_PK,REF_A_NAME,REF_B_PK,");
		sql.append("REF_B_NAME,STATE FROM SYS_OPER_REF ");
	    sql.append("WHERE REF_A_PK='"+roleCode+"' ");
	    sql.append("AND REF_A_NAME='SYS_ROLE'");
	    return this.getSession().createSQLQuery(sql.toString()).addEntity(SysOperRef.class).list();
	}
	
	/**
	 * 根据角色编号与菜单编号删除权限表
	 * */
	public int deleteSysRefByMenuRoleCode(SysMenu mSysMenu,SysOperRefFormBean mSysOperRefFormBean){
		String roleCode=mSysOperRefFormBean.getmSysOperRefBean().getRefApk();
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_OPER_REF ");
		sql.append("WHERE REF_A_PK='"+roleCode+"' AND REF_B_PK='"+mSysMenu.getMenuCode()+"'");
		return this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据菜单编号删除权限表
	 * */
	public void deleteSysRefByMenuCode(SysOperRef mSysOperRef){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_OPER_REF ");
		sql.append("WHERE REF_B_PK='"+mSysOperRef.getRefBpk()+"' ");
		sql.append("AND REF_B_NAME='"+mSysOperRef.getRefBname()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据角色编号删除权限表
	 * */
	public void deleteSysRefByRoleCode(SysOperRef mSysOperRef){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_OPER_REF ");
		sql.append("WHERE REF_A_PK='"+mSysOperRef.getRefApk()+"' ");
		sql.append("AND REF_A_NAME='"+mSysOperRef.getRefAname()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据角色编号与菜单编码为菜单授权
	 * */
	public int sysMenuSqOperation(SysMenu mSysMenu,SysOperRefFormBean mSysOperRefFormBean){
		String refAname=mSysOperRefFormBean.getmSysOperRefBean().getRefAname();
		String refBname=mSysOperRefFormBean.getmSysOperRefBean().getRefBname();
		String refCode=Randomizer.nextNumber("rm", 6);
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_OPER_REF (REF_CODE,REF_A_PK,REF_A_NAME,REF_B_PK,REF_B_NAME,STATE)");
		sql.append(" VALUES ('"+refCode+"','"+mSysOperRefFormBean.getmSysOperRefBean().getRefApk()+"',");
		sql.append("'"+refAname+"','"+mSysMenu.getMenuCode()+"','"+refBname+"',0)");
		return this.exectueSQL(sql.toString());
	}
	
}
