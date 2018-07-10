package com.lyht.business.system.dao;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysLog;
import com.lyht.business.system.formbean.SysLogFormBean;
import com.lyht.util.CommonUtil;

/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 23:41:13
 *说明:  系统日志
*/
@Repository
@Scope("prototype")
public class SysLogDao extends HibernateBaseDao<SysLog>{

	 
	//根据FormBean中的条件查找实体对象List
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResults m_findAll(SysLogFormBean formBean){
      ArrayList parmValue=new ArrayList(); 
      
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.LOGTIME,P.NAME,P.OPERNM,P.MENUFLAG,P.DICTNM_OPTTYPE,P.OLDDATA,P.NEWDATA,P.MEMO,P.FLAG,P.SYSFLAG ";
                sql=sql+" ,SYSDICT_OPTTYPE.NAME AS EXAMINE";    
				sql=sql	+ " FROM SYS_LOG AS P ";
                sql=sql + " LEFT JOIN SYS_DICT AS SYSDICT_OPTTYPE ON  P.DICTNM_OPTTYPE=SYSDICT_OPTTYPE.NM AND SYSDICT_OPTTYPE.LISTNM_SYS_DICT_CATE='OPTTYPE'";
				sql=sql	+ " WHERE 1=1 ";        
					
		if (formBean!=null){
			//字符串字段模糊匹配
			if (CommonUtil.trim(formBean.getSearchName()).length()>0){
					 sql=sql+" AND ( ";
					 
                     sql=sql+"  (P.NM LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.LOGTIME LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.NAME LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.OPERNM LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.MENUFLAG LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (SYSDICT_OPTTYPE.NAME LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%"); 
                     
                     sql=sql+"  OR  (P.OLDDATA LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.NEWDATA LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+"  OR  (P.MEMO LIKE ? )";
                     parmValue.add("%"+CommonUtil.trim(formBean.getSearchName())+"%");
                     
                     sql=sql+" )";
			}
			//字段条件查询，根据需要自己增加
			if (formBean.getInfoBean()!=null){
					if (CommonUtil.trim(formBean.getInfoBean().getNm()).length()>0) 
					{                      
						sql=sql+" AND P.NM LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getNm())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getLogtime()).length()>0) 
					{                      
						sql=sql+" AND P.LOGTIME LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getLogtime())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getName()).length()>0) 
					{                      
						sql=sql+" AND P.NAME LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getName())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getOpernm()).length()>0) 
					{                      
						sql=sql+" AND P.OPERNM LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getOpernm())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getMenuflag()).length()>0) 
					{                      
						sql=sql+" AND P.MENUFLAG LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getMenuflag())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getDictnmOpttype()).length()>0) 
					{                      
						sql=sql+" AND P.DICTNM_OPTTYPE = ? ";
						parmValue.add(CommonUtil.trim(formBean.getInfoBean().getDictnmOpttype()));
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getOlddata()).length()>0) 
					{                      
						sql=sql+" AND P.OLDDATA LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getOlddata())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getNewdata()).length()>0) 
					{                      
						sql=sql+" AND P.NEWDATA LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getNewdata())+"%");
                    }
					if (CommonUtil.trim(formBean.getInfoBean().getMemo()).length()>0) 
					{                      
						sql=sql+" AND P.MEMO LIKE ? ";
						parmValue.add("%"+CommonUtil.trim(formBean.getInfoBean().getMemo())+"%");
                    }
					
					if (CommonUtil.trim(formBean.getInfoBean().getFlag()).length()>0) 
					{                      
						sql=sql+" AND P.FLAG = ? ";
						parmValue.add(formBean.getInfoBean().getFlag());
                    }
                    
					if (CommonUtil.trim(formBean.getInfoBean().getSysflag()).length()>0) 
					{                      
						sql=sql+" AND P.SYSFLAG = ? ";
						parmValue.add(formBean.getInfoBean().getSysflag());
                    }
		    }
		}
		
        String sql_all=sql+" ORDER BY P."+formBean.getPageBean().getSort()+" "+formBean.getPageBean().getSortOrder();
		
		
		PageResults retValue =new PageResults();
		    retValue =this.findPageByFetchedSql(sql_all, null
    			,formBean.getPageBean().getOffset()
    			,formBean.getPageBean().getLimit()
    			,parmValue.toArray());

		return retValue;
        
	}
    
	//根据实体属性及值获取实体
	public SysLog getByProp(String PropName,Object PropValue){
		return  this.findByObject(PropName, PropValue);
	}
    
	@SuppressWarnings({ "rawtypes" })
	public PageResults m_findByIds(String ids){
      ArrayList parmValue=new ArrayList(); 
            
		String  sql="SELECT ";
				sql=sql+" P.ID,P.NM,P.LOGTIME,P.NAME,P.OPERNM,P.MENUFLAG,P.DICTNM_OPTTYPE,P.OLDDATA,P.NEWDATA,P.MEMO,P.FLAG,P.SYSFLAG ";
                sql=sql+" ,SYSDICT_OPTTYPE.NAME ";    
				sql=sql	+ " FROM SYS_LOG AS P  ";
                sql=sql + " LEFT JOIN SYS_DICT AS SYSDICT_OPTTYPE ON  P.DICTNM_OPTTYPE=SYSDICT_OPTTYPE.NM AND SYSDICT_OPTTYPE.LISTNM_SYS_DICT_CATE='OPTTYPE'";
				sql=sql	+ " WHERE 1=1 ";    
					
		if (ids.length()>0){
				sql=sql+" AND P.ID IN ("+ids+")";
		}
			
        String sql_all=sql+" ORDER BY P.ID";
		
		
		PageResults retValue =new PageResults();
		    retValue =this.findPageByFetchedSql(sql_all, sql
    			,0
    			,100000000
    			,parmValue.toArray());
                
		    
		return retValue;
        
	}	
    

}
