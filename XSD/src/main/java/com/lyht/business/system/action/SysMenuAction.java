package com.lyht.business.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.bean.SysOperRef;
import com.lyht.business.system.control.SysMenuControl;
import com.lyht.business.system.formbean.SysMenuFormBean;
import com.lyht.business.system.service.SysMenuService;
import com.lyht.business.system.service.SysOperRefService;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Controller
@Scope("prototype")
@Action("/sysmenu")
@SuppressWarnings("rawtypes")
public class SysMenuAction extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("SysMenuAction");
	
	private SysMenuFormBean mSysMenuFormBean=new SysMenuFormBean();
	
	@Resource
	private SysMenuControl mSysMenuControl;
	@Resource
	private SysMenuService mSysMenuService;
	@Resource
	private SysOperRefService mSysOperRefService;
	@Resource
	private SysRoleService mSysRoleService;
	
	/**
	 * 获取根节点数据
	 * */
	public String listroot(){
		log.info("SysMenuAction=listroot:查询树状根节点信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mSysMenuControl.getListRootData(mSysMenuFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 获取节点数据
	 * */
	public String list(){
		log.info("SysMenuAction=list:查询树状节点信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mSysMenuControl.getListData(mSysMenuFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化根节点FORM表单数据
	 * */
	public String initRootTreeFormData(){
		log.info("SysMenuAction=initRootTreeFormData:初始化根节点FORM表单数据");
		String fcode=mSysMenuFormBean.getmSysMenuInfoBean().getfCode();
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		
		SysMenu mSysMenu = new SysMenu();
		SysMenu mPSysMenu= new SysMenu();
		
		mRetMessage=mSysMenuControl.view(fcode,mSysMenu,mPSysMenu);
		
		mHashMap.put("mSysMenuInfoBean", mSysMenu);
		mHashMap.put("mPSysMenuInfoBean", mPSysMenu);
		
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化子节点FORM表单数据
	 * */
	public String initChildTreeFormData(){
		log.info("SysMenuAction=initChildTreeFormData:初始化子节点FORM表单数据");
		String fcode=mSysMenuFormBean.getmSysMenuInfoBean().getfCode();
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		SysMenu mSysMenu = new SysMenu();
		SysMenu mPSysMenu= new SysMenu();
		mRetMessage=mSysMenuControl.add(fcode,mPSysMenu);
		mHashMap.put("mSysMenuInfoBean", mSysMenu);
		mHashMap.put("mPSysMenuInfoBean", mPSysMenu);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存节点FORM表单数据
	 * */
	public String saveTreeFormData(){
		log.info("SysMenuAction=saveTreeFormData:保存节点FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		SysMenu mSysMenu = new SysMenu();
		if("1".equals(mSysMenuFormBean.getBj()) || "0".equals(mSysMenuFormBean.getBj())){
			mRetMessage=mSysMenuControl.create(mSysMenuFormBean.getmSysMenuInfoBean(),mSysMenu);
			mHashMap.put("mSysMenuFormBean", mSysMenu);
		}else{
			mRetMessage=mSysMenuControl.update(mSysMenuFormBean.getmSysMenuInfoBean());
			mHashMap.put("mSysMenuFormBean", mSysMenuFormBean.getmSysMenuInfoBean());
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 删除菜单数据
	 * */
	@SuppressWarnings("unchecked")
	public String removeIds(){
		log.info("SysMenuAction=removeIds:删除菜单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults(); 
		String fCode=CommonUtil.trim(mSysMenuFormBean.getIds());
		mPageResults=mSysMenuControl.getSysMenuListById(fCode);
		List<Map> list=mPageResults.getResults();
		deleteSysRefByMenuCode(list);
		mRetMessage= mSysMenuControl.deleteSysMenuInfoByFCode(fCode,list);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 根据菜单编号删除权限信息
	 * */
	private void deleteSysRefByMenuCode(List<Map> list){
		for(int i=0;i<list.size();i++){
			Object obj=list.get(i).get("MENU_CODE");
			if(null!=obj){
				String menuCode=obj.toString();
				List<SysOperRef> mSysOperRefList=mSysOperRefService.getSysRefByMenuCode(menuCode);
				if(mSysOperRefList.size()>0){
					for(int j=0;j<mSysOperRefList.size();j++){
						SysOperRef mSysOperRef=mSysOperRefList.get(j);
						if(!"".equals(mSysOperRef.getRefBpk()) && !"".equals(mSysOperRef.getRefBname())){
							mSysOperRefService.deleteSysRefByMenuCode(mSysOperRef);
						}
					}
				}
			}
		}
	}
	
	public SysMenuFormBean getmSysMenuFormBean() {
		return mSysMenuFormBean;
	}
	
}
