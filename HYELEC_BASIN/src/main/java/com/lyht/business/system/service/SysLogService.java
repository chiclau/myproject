package com.lyht.business.system.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysLog;
import com.lyht.business.system.dao.SysLogDao;
import com.lyht.business.system.formbean.SysLogFormBean;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 23:41:13
 *说明:  系统日志
*/
@Service
@Scope("prototype")
@Transactional
public class SysLogService  {
	 
	@Resource private SysLogDao sysLogdao;
    //根据id获取实体对象
	public SysLog s_get(int id){
		SysLog ret_bean= new SysLog();
		if (id>0) {
			ret_bean= sysLogdao.get(id);
		}
		return ret_bean;
	}
    
	//增加实体对象
	public SysLog s_create(SysLog bean){
		UUID uuid = UUID.randomUUID();
		String str=uuid.toString().replaceAll("-", "");
		bean.setNm(str);
        //将内码设置成编码
		//entity.setNm(bean.getCode());
		sysLogdao.save(bean);
		return bean;
	}
    
	//修改实体对象
	public SysLog s_update(SysLog bean){
		sysLogdao.merge(bean);	
		sysLogdao.flush(bean);
		return bean;
	}	
	
    //删除实体对象
	public void s_remove(SysLog bean){
		sysLogdao.delete(bean);
	}
	
    //根据IDS(1,2,3,4,5)删除多个对象
	public void s_delByIds(String ids){
		  String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++)
		  {
			  sysLogdao.deleteById(Integer.parseInt(idary[i]));
		  }
		
	}
	
    //根据IDS(1,2,3,4,5)修改多个对象的Flag数值
	public void s_flagByIds(String ids,Integer flag_new){
		  String[] idary=ids.split(",");
		  for(int i=0;i<idary.length;i++)
		  {
			  SysLog entity = sysLogdao.get(Integer.parseInt(idary[i]));
			  entity.setFlag(flag_new);
			  sysLogdao.update(entity);
		  }
		
	}
	
    //根据FormBean中的条件查找实体对象List
	@SuppressWarnings("rawtypes")
	public PageResults s_findAll(SysLogFormBean formBean){
		return sysLogdao.m_findAll(formBean);
	}
    
    //根据属性及属性值查找对象实体
	public SysLog s_getByProp(String PropName,Object PropValue){
		return sysLogdao.getByProp(PropName, PropValue);
	}
	
	@SuppressWarnings("rawtypes")
	public PageResults s_findByIds(String ids){
		return sysLogdao.m_findByIds(ids);
	}	
}
