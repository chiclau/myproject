/**
 * 
 */
package com.lyht.business.datasearch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.datasearch.bean.SysDict;

/**
 * <p>Title: ZdSumDao</p>  
 * <p>Description: </p>  
 * @author 王宇
 * @date 2018年10月18日
 *
 */
@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ZdSumDao extends HibernateBaseDao<SysDict> {

    public List<Map> listZdSum() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM sys_dict ");
        return this.createSQLQuerybyMap(sql.toString());
    }
    
}
