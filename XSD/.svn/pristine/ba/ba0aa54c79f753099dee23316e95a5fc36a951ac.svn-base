package com.lyht.business.system.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysUser;
import com.lyht.business.system.formbean.SysAccountFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.MD5;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysUserDao extends HibernateBaseDao<SysUser>{
	
	/**
	 * 获取账户信息列表
	 * */
	public PageResults getSysAccountInfo(SysAccountFormBean mSysAccountFormBean){
		StringBuilder sql=new StringBuilder();
		String str=spliceStrAcct(mSysAccountFormBean);
		sql.append("SELECT A.STAFF_CODE,A.STAFF_NAME,A.TREENM_DEPT,A.STAFF_DEPT,A.LINK_PHONE,");
		sql.append("A.JIG,A.STAFF_ADDRESS,A.STATE AS STAFF_STATE,");
		sql.append("B.USER_CODE,B.USER_NAME,B.USER_PWD,B.STATE AS USER_STATE FROM SYS_USER B ");
		sql.append("LEFT JOIN SYS_STAFF A ON A.STAFF_CODE=B.STAFF_CODE WHERE 1=1 ");
		if(null!=str && !"".equals(str)){
			sql.append(str);
		}
		sql.append(" ORDER BY A.STAFF_CODE");
		return this.findPageByFetchedSql(sql.toString(), null
				,mSysAccountFormBean.getPageBean().getOffset()
				,mSysAccountFormBean.getPageBean().getLimit()
				,null);
	}
	
	/**
	 * 根据账号名称查询是否存在账户信息
	 * */
	public PageResults getSysUserInfoByUserName(SysUser mSysUser){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT USER_CODE,STAFF_CODE,USER_NAME,USER_PWD,STATE FROM SYS_USER ");
		sql.append("WHERE USER_NAME='"+mSysUser.getUserName()+"'");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	public PageResults getUser(String usercode,String psw){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT USER_CODE,STAFF_CODE,USER_NAME,USER_PWD,STATE FROM SYS_USER ");
		sql.append("WHERE USER_CODE='"+usercode+"' and USER_PWD='"+psw+"'");
		return this.findPageByFetchedSql(sql.toString(), null, 0, 100000000, null);
	}
	
	public boolean updatePsw(String usercode,String psw) {
		StringBuilder sql=new StringBuilder();
		boolean flag=false;
		sql.append("UPDATE SYS_USER SET USER_PWD='"+psw+"' WHERE USER_CODE='"+usercode+"' ");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	/**
	 * 新增账户信息
	 * */
	public boolean insertSysUserInfo(SysUser mSysUser,String userCode,String staffCode,SysUser sysUser){
		boolean flag=false;
		String userPwd=encryption(mSysUser.getUserPwd());
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_USER (USER_CODE,STAFF_CODE,USER_NAME,USER_PWD,STATE) ");
		sql.append("VALUES ('"+userCode+"','"+staffCode+"',");
		sql.append("'"+mSysUser.getUserName()+"','"+userPwd+"',");
		if(null==sysUser){
			sql.append("0)");
		}else{
			sql.append("1)");
		}
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 新增账户信息
	 * */
	public boolean insertSysUserInfo(SysUser mSysUser,String userCode,String staffCode){
		boolean flag=false;
		String userPwd=encryption(mSysUser.getUserPwd());
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO SYS_USER (USER_CODE,STAFF_CODE,USER_NAME,USER_PWD,STATE) ");
		sql.append("VALUES ('"+userCode+"','"+staffCode+"',");
		sql.append("'"+mSysUser.getUserName()+"','"+userPwd+"',1)");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 修改用户信息
	 * */
	public boolean updateSysUserInfo(SysUser mSysUser){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_USER SET USER_CODE='"+mSysUser.getUserCode()+"',STAFF_CODE='"+mSysUser.getStaffCode()+"',");
		sql.append("USER_NAME='"+mSysUser.getUserName()+"',USER_PWD='"+mSysUser.getUserPwd()+"',STATE='"+mSysUser.getState()+"' ");
		sql.append("WHERE STAFF_CODE='"+mSysUser.getStaffCode()+"'");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 删除用户信息
	 * */
	public boolean deleteSysUserInfo(String staffCode){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("DELETE FROM SYS_USER WHERE STAFF_CODE IN ('"+staffCode+"')");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 初始化密码
	 * */
	public boolean initPwd(String staffCode){
		boolean flag=false;
		String pwd=encryption("123456");
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_USER SET USER_PWD='"+pwd+"' WHERE STAFF_CODE='"+staffCode+"'");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 审核用户信息
	 * */
	public boolean auditSysUserInfo(SysUser mSysUser){
		boolean flag=false;
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE SYS_USER SET STATE='"+mSysUser.getState()+"' ");
		sql.append("WHERE STAFF_CODE IN ('"+mSysUser.getStaffCode()+"')");
		int i=this.exectueSQL(sql.toString());
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 校验账号与密码是否存在
	 * */
	public SysUser checkAccount(SysUser sysUser){
		String userPwd=encryption(sysUser.getUserPwd());
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT USER_CODE,STAFF_CODE,USER_NAME,USER_PWD,STATE FROM SYS_USER ");
		sql.append("WHERE USER_NAME='"+sysUser.getUserName()+"' AND USER_PWD='"+userPwd+"' ");
		sql.append("AND STATE=1");
		return getUserInfo(sysUser,sql);
	}
	
	/**
	 * 根据人员编号获取用户信息
	 * */
	public SysUser getUserInfoByStaffCode(String staffCode){
		StringBuilder sql=new StringBuilder();
		SysUser mSysUser=new SysUser();
		sql.append("SELECT USER_CODE,STAFF_CODE,USER_NAME,USER_PWD,STATE FROM SYS_USER ");
		sql.append("WHERE STAFF_CODE='"+staffCode+"'");
		return getUserInfo(mSysUser,sql);
	}
	
	/**
	 * 根据条件查询账户信息
	 * */
	private String spliceStrAcct(SysAccountFormBean mSysAccountFormBean){
		StringBuilder sql=new StringBuilder();
		if(null!=mSysAccountFormBean){
			if(CommonUtil.trim(mSysAccountFormBean.getSearchName()).length()>0){
				sql.append("AND ((A.STAFF_CODE LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (A.STAFF_NAME LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (A.TREENM_DEPT LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (A.STAFF_DEPT LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (A.LINK_PHONE LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (A.JIG LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (A.STAFF_ADDRESS LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (A.STATE = '"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"') ");
				sql.append("OR (B.USER_CODE LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%') ");
				sql.append("OR (B.USER_NAME LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getSearchName())+"%')) ");
			}
			if(null!=mSysAccountFormBean.getmSysStaffInfoBean() && null!=mSysAccountFormBean.getmSysUserInfoBean()){
				if(CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffCode()).length()>0){
					sql.append(" AND A.STAFF_CODE LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffCode())+"%'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffName()).length()>0){
					sql.append(" AND A.STAFF_NAME LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffName())+"%'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getTreeNmDept()).length()>0){
					sql.append(" AND A.TREENM_DEPT LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getTreeNmDept())+"%'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffDept()).length()>0){
					sql.append(" AND A.STAFF_DEPT LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffDept())+"%'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getLinkPhone()).length()>0){
					sql.append(" AND A.LINK_PHONE LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getLinkPhone())+"%'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getJig()).length()>0){
					sql.append(" AND A.JIG LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getJig())+"%'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffAddress()).length()>0){
					sql.append(" AND A.STAFF_ADDRESS LIKE '%"+CommonUtil.trim(mSysAccountFormBean.getmSysStaffInfoBean().getStaffAddress())+"%'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysUserInfoBean().getUserCode()).length()>0){
					sql.append(" AND B.USER_CODE = '"+CommonUtil.trim(mSysAccountFormBean.getmSysUserInfoBean().getUserCode())+"'");
				}
				if(CommonUtil.trim(mSysAccountFormBean.getmSysUserInfoBean().getUserName()).length()>0){
					sql.append(" AND B.USER_NAME = '"+CommonUtil.trim(mSysAccountFormBean.getmSysUserInfoBean().getUserName())+"'");
				}
			}
		}
		return sql.toString();
	}
	
	/**
	 * 获取用户信息
	 * */
	@SuppressWarnings("unchecked")
	private SysUser getUserInfo(SysUser mSysUser,StringBuilder sql){
		List<SysUser> mSysUserList=null;
		try{
			mSysUserList=this.getSession().createSQLQuery(sql.toString()).addEntity(SysUser.class).list();
			if(mSysUserList.size()>0){
				mSysUser=mSysUserList.get(0);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mSysUser;
	}
	
	/**
	 * 处理密码（加密）
	 * */
	private String encryption(String pwd){
		MD5 md= MD5.getInstance();
		return md.getMD5ofStr(pwd, 16);
	}
}
