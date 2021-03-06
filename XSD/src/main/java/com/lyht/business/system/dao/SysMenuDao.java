package com.lyht.business.system.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.formbean.SysMenuFormBean;
import com.lyht.business.system.formbean.SysOperRefFormBean;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings({ "rawtypes" })
public class SysMenuDao extends HibernateBaseDao<SysMenu>{

	/**
	 * 获取根节点数据
	 * */
	public PageResults getListRootData(SysMenuFormBean mSysMenuFormBean){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,MENU_CODE,MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,");
		sql.append("MENU_ICON,STATE FROM SYS_MENU WHERE SUPER_CODE='' ");
		sql.append("ORDER BY ID");
	    return this.findPageByFetchedSql(sql.toString(), null
			,mSysMenuFormBean.getPageBean().getOffset()
			,mSysMenuFormBean.getPageBean().getLimit()
			,null);
	}
	
	/**
	 * 获取节点数据
	 * */
	public PageResults getListData(SysMenuFormBean mSysMenuFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrSysMenu(mSysMenuFormBean);
		sql.append("SELECT ID,MENU_CODE,MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,");
		sql.append("MENU_ICON,STATE FROM SYS_MENU WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY FCODE");
		return this.findPageByFetchedSql(sql.toString(), null
				,mSysMenuFormBean.getPageBean().getOffset()
				,mSysMenuFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据全编码获取菜单信息
	 * */
	public PageResults getSysMenuByFcode(SysOperRefFormBean mSysOperRefFormBean,SysMenuFormBean mSysMenuFormBean){
		String fCode=mSysMenuFormBean.getmSysMenuInfoBean().getfCode();
		String roleCode=mSysOperRefFormBean.getmSysRoleBean().getRoleCode();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT MENU_CODE,MENU_NAME,SCODE,FCODE,SUPER_CODE,RELAID FROM ");
		sql.append("(SELECT A.MENU_CODE,A.MENU_NAME,A.SCODE,A.FCODE,A.SUPER_CODE,B.ID as RELAID ");
		sql.append("FROM SYS_MENU A LEFT JOIN SYS_OPER_REF B ON A.MENU_CODE=B.REF_B_PK  ");
		sql.append("AND B.REF_A_NAME='SYS_ROLE' AND B.REF_B_NAME='SYS_MENU'  ");
		if(!"".equals(roleCode)){
			sql.append("AND B.REF_A_PK='"+roleCode+"' ");
		}
		sql.append(") C ");
		if(fCode.length()>0){
			sql.append("where C.FCODE LIKE '"+fCode+"%'");
		}
		sql.append(" ORDER BY FCODE");
		return this.findPageByFetchedSql(sql.toString(), null
				,mSysMenuFormBean.getPageBean().getOffset()
				,mSysMenuFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据菜单编号获取菜单信息
	 * */
	@SuppressWarnings("unchecked")
	public SysMenu getSysMenuInfoById(String fcode){
		SysMenu mSysMenu=new SysMenu();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,MENU_CODE,MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,");
		sql.append("MENU_ICON,STATE FROM SYS_MENU WHERE FCODE='"+fcode+"'");
		List<SysMenu> mSysMenuList=null;
		try{
			mSysMenuList=this.getSession().createSQLQuery(sql.toString()).addEntity(SysMenu.class).list();
			if(mSysMenuList.size()>0){
				mSysMenu=mSysMenuList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSysMenu;
	}
	
	/**
	 * 根据实体属性及值获取实体
	 * */
	public SysMenu getSysMenuInfoByProp(String PropName,Object PropValue){
		return this.findByObject(PropName, PropValue);
	}
	
	/**
	 * 修改实体对象
	 * */
	public void update(SysMenu mSysMenu){
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_MENU SET MENU_NAME='"+mSysMenu.getMenuName()+"',FCODE='"+mSysMenu.getfCode()+"',");
		sql.append("SCODE='"+mSysMenu.getsCode()+"',SUPER_CODE='"+mSysMenu.getSuperCode()+"',");
		sql.append("MENU_URL='"+mSysMenu.getMenuUrl()+"',MENU_ICON='"+mSysMenu.getMenuIcon()+"'");
		sql.append(" WHERE MENU_CODE='"+mSysMenu.getMenuCode()+"'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据菜单编号获取菜单信息及子菜单信息
	 * */
	public PageResults getSysMenuInfoByFCode(String fCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,MENU_CODE,MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,");
		sql.append("MENU_ICON,STATE FROM SYS_MENU WHERE 1=1");
		sql.append(" AND FCODE LIKE '"+fCode+"%'");
		sql.append(" ORDER BY FCODE");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 1000000000, null);
	}
	
	/**
	 * 根据menuCode(1,2,3,4,5)删除多个对象
	 * */
	public void deleteSysMenuInfoByFCode(String fCode){
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_MENU WHERE FCODE LIKE '"+fCode+"%'");
		this.exectueSQL(sql.toString());
	}
	
	/**
	 * 根据菜单编号查询上级编码
	 * */
	@SuppressWarnings("unchecked")
	public List<SysMenu> getFCodeByMenuCode(SysMenu mSysMenu){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,MENU_CODE,MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,");
		sql.append("MENU_ICON,STATE FROM SYS_MENU ");
		sql.append("WHERE FCODE LIKE '"+mSysMenu.getfCode()+"%'");
		return this.getSession().createSQLQuery(sql.toString()).addEntity(SysMenu.class).list();
	}
	
	/**
	 * 根据菜单编号获取菜单信息
	 * */
	@SuppressWarnings("unchecked")
	public SysMenu getSysMenuInfoByMenuCode(String menuCode){
		SysMenu mSysMenu=new SysMenu();
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,MENU_CODE,MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,");
		sql.append("MENU_ICON,STATE FROM SYS_MENU ");
		sql.append("WHERE MENU_CODE = '"+menuCode+"'");
		List<SysMenu> mSysMenuList=null;
		try{
			mSysMenuList=this.getSession().createSQLQuery(sql.toString()).addEntity(SysMenu.class).list();
			if(mSysMenuList.size()>0){
				mSysMenu=mSysMenuList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSysMenu;
	}
	
	/**
	 * 获取菜单信息
	 * */
	@SuppressWarnings("unchecked")
	public List<SysMenu> getSysMenuInfoList(){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT ID,MENU_CODE,MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,");
		sql.append("MENU_ICON,STATE FROM SYS_MENU ");
		return this.getSession().createSQLQuery(sql.toString()).addEntity(SysMenu.class).list();
	}
	
	/**
	 * 根据角色编号获取菜单信息
	 * */
	public PageResults getSysMenuInfoByRoleCode(String RoleCode){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT DISTINCT A.ID,A.MENU_CODE,A.MENU_NAME,A.FCODE,A.SCODE,");
		sql.append("A.SUPER_CODE,A.MENU_URL,A.MENU_ICON,A.STATE FROM (SELECT ID,MENU_CODE,");
		sql.append("MENU_NAME,FCODE,SCODE,SUPER_CODE,MENU_URL,MENU_ICON,STATE FROM SYS_MENU ");
		/*if(RoleCode.equals("admins")) {//如果是admins，不过滤菜单
			sql.append("WHERE  MENU_CODE IN (SELECT REF_B_PK FROM SYS_OPER_REF ");
		}else {//如果是普通用户，对菜单过滤->不显示用户列表等菜单
*/			
		sql.append("WHERE STATE=1 AND  MENU_CODE IN (SELECT REF_B_PK FROM SYS_OPER_REF ");
	/*	}*/
		sql.append("WHERE REF_A_NAME='SYS_ROLE' and REF_A_PK='"+RoleCode+"' ");
		sql.append("AND REF_B_NAME='SYS_MENU')) A LEFT JOIN SYS_OPER_REF B ");
		sql.append("ON A.MENU_CODE=B.REF_B_PK ORDER BY A.FCODE");
		System.out.println(sql.toString());
		return this.findPageByFetchedSql(sql.toString(), null, 0,1000000000,null);
	}
	
	/**
	 * 根据条件获取菜单信息
	 * */
	private String spliceStrSysMenu(SysMenuFormBean mSysMenuFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mSysMenuFormBean){
			if(CommonUtil.trim(mSysMenuFormBean.getSearchName()).length()>0){
				sql.append("AND ((MENU_CODE LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"%') ");
				sql.append("OR (MENU_NAME LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"%') ");
				sql.append("OR (FCODE LIKE '"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"%') ");
				sql.append("OR (SCODE LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"%') ");
				sql.append("OR (SUPER_CODE LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"%') ");
				sql.append("OR (MENU_URL LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"%') ");
				sql.append("OR (MENU_ICON LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"%') ");
				sql.append("OR (STATE='"+CommonUtil.trim(mSysMenuFormBean.getSearchName())+"'))");
			}
			if(null!=mSysMenuFormBean.getmSysMenuInfoBean()){
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuCode()).length()>0){
					sql.append(" AND MENU_CODE LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuCode())+"%'");
				}
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuName()).length()>0){
					sql.append(" AND MENU_NAME LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuName())+"%'");
				}
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getfCode()).length()>0){
					sql.append(" AND FCODE LIKE '"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getfCode())+"%'");
				}
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getsCode()).length()>0){
					sql.append(" AND SCODE LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getsCode())+"%'");
				}
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getSuperCode()).length()>0){
					sql.append(" AND SUPER_CODE LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getSuperCode())+"%'");
				}
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuUrl()).length()>0){
					sql.append(" AND MENU_URL LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuUrl())+"%'");
				}
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuIcon()).length()>0){
					sql.append(" AND MENU_ICON LIKE '%"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getMenuIcon())+"%'");
				}
				if(CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getState()).length()>0){
					sql.append(" AND STATE='"+CommonUtil.trim(mSysMenuFormBean.getmSysMenuInfoBean().getState())+"'");
				}
			}
		}
		return sql.toString();
	}
	
}
