package com.lyht.business.system.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.system.bean.SysRoleMenu;
@Repository
@Scope("prototype")
public class SysRoleMenuDao extends HibernateBaseDao<SysRoleMenu> {

}
