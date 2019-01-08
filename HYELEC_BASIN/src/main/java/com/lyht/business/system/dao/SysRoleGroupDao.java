package com.lyht.business.system.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.system.bean.SysRoleGroup;
@Repository
@Scope("prototype")
public class SysRoleGroupDao extends HibernateBaseDao<SysRoleGroup> {
	public int deleteByRoleIds(String roleIds){
		StringBuffer sql = new StringBuffer("delete from SYS_ROLE_GROUP WHERE ROLE_CODE IN (SELECT ROLE_CODE FROM SYS_ROLE WHERE ID IN ("+roleIds+"))");
		return this.excuteSql(sql.toString(), new Object[]{});
	}
	public int batchInsert(String roleids,String groupCode){
		StringBuffer sql = new StringBuffer("INSERT INTO SYS_ROLE_GROUP(ROLE_CODE,GROUP_CODE) "
				+ "SELECT ROLE_CODE,'"+groupCode+"' as STAFF_CODE FROM SYS_ROLE WHERE ID IN ("+roleids+")");
		return this.excuteSql(sql.toString(), new Object[]{});
	}
}
