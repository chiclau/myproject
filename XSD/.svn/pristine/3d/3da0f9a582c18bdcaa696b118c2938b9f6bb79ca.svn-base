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

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysOperRef;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.control.SysRoleControl;
import com.lyht.business.system.formbean.SysRoleFormBean;
import com.lyht.business.system.service.SysOperRefService;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Controller
@Scope("prototype")
@Action("sysrole")
@SuppressWarnings("rawtypes")
public class SysRoleAction extends BaseLyhtAction{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysRoleAction");
	private SysRoleFormBean mSysRoleFormBean=new SysRoleFormBean();
	
	@Resource
	private SysRoleControl mSysRoleControl;
	@Resource
	private SysRoleService mSysRoleService;
	@Resource
	private SysOperRefService mSysOperRefService;
	
	/**
	 * 获取角色信息列表
	 * */
	public String list(){
		log.info("SysRoleAction=list:获取角色信息列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mSysRoleControl.getSysRoleMes(mSysRoleFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);	
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", getMenuAndGroupByCode(mPageResults));			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化角色信息FORM表单数据
	 * */
	public String edit(){
		log.info("SysRoleAction=edit:初始化角色信息FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		SysRole mSysRole = new SysRole();
		mRetMessage=mSysRoleControl.view(mSysRoleFormBean.getmSysRoleInfoBean().getId(),mSysRole);
		mHashMap.put("mSysRoleFormBean", mSysRole);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 角色信息保存FORM表单数据
	 * */
	public String save(){
		log.info("SysRoleAction=save:角色信息保存FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		SysRole mSysRole = new SysRole();
		if(mSysRoleFormBean.getmSysRoleInfoBean().getId()==0){
			mRetMessage=mSysRoleControl.create(mSysRoleFormBean.getmSysRoleInfoBean(),mSysRole);
		}else{
			mSysRole=mSysRoleService.getSysRoleInfoById(mSysRoleFormBean.getmSysRoleInfoBean().getId());
			mRetMessage=mSysRoleControl.update(mSysRoleFormBean.getmSysRoleInfoBean(),mSysRole);
		}
		mHashMap.put("mSysRoleFormBean", mSysRole);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除角色信息列表数据
	 * */
	@SuppressWarnings("unchecked")
	public String removeids(){
		log.info("批量删除角色信息列表数据==SysRoleAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String ids = CommonUtil.trim(mSysRoleFormBean.getIds());
		PageResults mPageResults=new PageResults(); 
		mPageResults=mSysRoleControl.getSysRoleInfoListByIds(ids);
		List<Map> list=mPageResults.getResults();
		deleteSysRefByRoleCode(list);
		mRetMessage=mSysRoleControl.deleteSysRoleInfoByIds(ids, list);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 角色设置人员
	 * */
	public String setStaffNameBySysRoleId(){
		log.info("角色设置人员==SysRoleAction.setStaffNameBySysRoleId");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String ids=mSysRoleFormBean.getIds();
		String staffCode= mSysRoleFormBean.getmSysStaffInfoBean().getStaffCode();
		String[] code=staffCode.split(",");
		boolean flag=mSysRoleService.setStaffNameBySysRoleId(ids,staffCode);
	/*	boolean flag=true;
		for(int i=0;i<code.length;i++) {
			flag	=mSysRoleService.setStaffNameBySysRoleId(ids,code[i]);
		}*/
		if(flag){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 角色设置分组信息
	 * */
	public String setSysRoleInfo(){
		log.info("角色设置分组信息==SysRoleAction.setSysRoleInfo");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String []ids=mSysRoleFormBean.getIds().split(",");
		String []groupCode= mSysRoleFormBean.getmSysGroupInfoBean().getGroupCode().split(",");
		boolean flag=mSysRoleService.setGroupNameBySysRoleId(ids,groupCode);
		if(flag){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 根据角色编号删除权限信息
	 * */
	private void deleteSysRefByRoleCode(List<Map> list){
		for(int i=0;i<list.size();i++){
			Object obj=list.get(i).get("ROLE_CODE");
			if(null!=obj){
				String roleCode=obj.toString();
				List<SysOperRef> mSysOperRefList=mSysOperRefService.getSysRefByRoleCode(roleCode);
				if(mSysOperRefList.size()>0){
					for(int j=0;j<mSysOperRefList.size();j++){
						SysOperRef mSysOperRef=mSysOperRefList.get(j);
						if(!"".equals(mSysOperRef.getRefApk()) && !"".equals(mSysOperRef.getRefAname())){
							mSysOperRefService.deleteSysRefByRoleCode(mSysOperRef);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 根据编号获取菜单、分组名称
	 * */
	private List<Map> getMenuAndGroupByCode(PageResults mPageResults){
		return mSysRoleService.getMenuAndGroupByCode(mPageResults);
	}
	
	public SysRoleFormBean getmSysRoleFormBean() {
		return mSysRoleFormBean;
	}
	
}
