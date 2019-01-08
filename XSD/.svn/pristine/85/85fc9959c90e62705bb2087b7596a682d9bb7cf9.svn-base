package com.lyht.business.system.action;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.control.SysOperRefControl;
import com.lyht.business.system.formbean.SysMenuFormBean;
import com.lyht.business.system.formbean.SysOperRefFormBean;
import com.lyht.business.system.service.SysMenuService;
import com.lyht.business.system.service.SysOperRefService;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

@Namespace("/system")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("sysoperref")
public class SysOperRefAction extends BaseLyhtAction{

private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysOperRefAction");
	private SysOperRefFormBean mSysOperRefFormBean=new SysOperRefFormBean();
	private SysMenuFormBean mSysMenuFormBean=new SysMenuFormBean();
	
	@Resource
	private SysOperRefControl mSysOperRefControl;
	@Resource
	private SysOperRefService mSysOperRefService;
	@Resource
	private SysMenuService mSysMenuService;
	@Resource
	private SysRoleService mSysRoleService;
	
	public String list(){
		log.info("SysOperRefAction=list:查询树状节点信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		PageResults mPageResults=new PageResults();
		boolean flag=mSysOperRefService.getSysOperRefByRoleCode(mSysOperRefFormBean);
		if(flag){
			mPageResults=mSysMenuService.getSysMenuByFcode(mSysOperRefFormBean,mSysMenuFormBean);
		}else{
			mPageResults=mSysMenuService.getListData(mSysMenuFormBean);
		}
		mHashMap.put("total", mPageResults.getTotalCount());
		mHashMap.put("rows", mPageResults.getResults());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 授权操作
	 * */
	public String authorize(){
		log.info("SysOperRefAction=authorize:授权操作（取消）");
		String message="";
		String flag=mSysOperRefFormBean.getParmBean().getParm1();
		String menuCode=mSysOperRefFormBean.getmSysOperRefBean().getRefBpk();
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		boolean bool=sqQxOperation(flag,menuCode);
		if(bool){
			message="操作成功！";
		}else{
			message="操作失败！";
		}
		mHashMap.put("prompt", message);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 授权操作（取消）
	 * */
	private boolean sqQxOperation(String flag,String menuCode){
		boolean bool=false;
		List<SysMenu> mSysMenuList=null;
		try{
			if(!"".equals(menuCode)){
				mSysMenuList=mSysMenuService.getFCodeByMenuCode(menuCode);
			}else{
				mSysMenuList=mSysMenuService.getSysMenuInfoList();
			}
			for(int i=0;i<mSysMenuList.size();i++){
				SysMenu mSysMenu=mSysMenuList.get(i);
				List<Map> list=mSysOperRefService.getSysRefByMenuCode(mSysMenu,mSysOperRefFormBean);
				if(list.size()>0){
					bool=mSysOperRefService.deleteSysRefByMenuRoleCode(mSysMenu,mSysOperRefFormBean);
				}
				if("sq".equals(flag)){
					bool=mSysOperRefService.sysMenuSqOperation(mSysMenu,mSysOperRefFormBean);
				}
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return bool;
	}
	
	public SysOperRefFormBean getmSysOperRefFormBean() {
		return mSysOperRefFormBean;
	}
	public SysMenuFormBean getmSysMenuFormBean() {
		return mSysMenuFormBean;
	}
	
}
