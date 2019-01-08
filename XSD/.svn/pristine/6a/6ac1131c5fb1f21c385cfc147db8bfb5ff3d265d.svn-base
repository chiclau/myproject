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
import com.lyht.business.system.bean.SysGroup;
import com.lyht.business.system.control.SysGroupControl;
import com.lyht.business.system.formbean.SysGroupFormBean;
import com.lyht.business.system.service.SysGroupService;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/system")
@Controller
@Scope("prototype")
@Action("sysgroup")
@SuppressWarnings("rawtypes")
public class SysGroupAction extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysGroupAction");
	private SysGroupFormBean mSysGroupFormBean=new SysGroupFormBean();
	
	@Resource
	private SysGroupControl mSysGroupControl;
	@Resource
	private SysGroupService mSysGroupService;
	@Resource
	private SysRoleService mSysRoleService;

	/**
	 * 获取分组管理列表
	 * */
	public String list(){
		log.info("SysGroupAction=list: 获取分组管理列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mSysGroupControl.getSysGroupMes(mSysGroupFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", getBasinAndStaffNameByCode(mPageResults));			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化分组管理FORM表单数据
	 * */
	public String edit(){
		log.info("SysGroupAction=edit:初始化分组管理FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		SysGroup mSysGroup = new SysGroup();
		mSysGroup=mSysGroupService.getSysGroupInfoById(mSysGroupFormBean.getmSysGroupInfoBean().getId());
		mHashMap.put("mSysGroupFormBean", mSysGroup);
		mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		mHashMap.put(RetMessage.AJAX_MESSAGE, "查询成功");		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 分组管理保存FORM表单数据
	 * */
	public String save(){
		log.info("SysGroupAction=save:分组管理保存FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		SysGroup mSysGroup = new SysGroup();
		if(mSysGroupFormBean.getmSysGroupInfoBean().getId()==0){
			mRetMessage=mSysGroupControl.create(mSysGroupFormBean.getmSysGroupInfoBean(),mSysGroup);
		}else{
			mSysGroup=mSysGroupService.getSysGroupInfoById(mSysGroupFormBean.getmSysGroupInfoBean().getId());
			mRetMessage=mSysGroupControl.update(mSysGroupFormBean.getmSysGroupInfoBean(),mSysGroup);
		}
		mHashMap.put("mSysGroupFormBean", mSysGroup);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除分组管理列表数据
	 * */
	@SuppressWarnings("unchecked")
	public String removeids(){
		log.info("批量删除分组管理列表数据==SysGroupAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String ids = CommonUtil.trim(mSysGroupFormBean.getIds());
		PageResults mPageResults=new PageResults(); 
		mPageResults=mSysGroupControl.getSysGroupInfoListByIds(ids);
		List<Map> list=mPageResults.getResults();
		removeSysRoleByGroupCode(list);
		mRetMessage=mSysGroupControl.deleteSysGroupInfoByIds(ids, list);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 根据分组信息主键id设置人员名称
	 * */
	public String setStaffNameBySysGroupId(){
		log.info("根据分组信息主键id设置人员名称==SysGroupAction.setStaffNameBySysGroupId");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String []ids=mSysGroupFormBean.getIds().split(",");
		String []staffCode= mSysGroupFormBean.getmSysStaffInfoBean().getStaffCode().split(",");
		boolean flag=mSysGroupService.setStaffNameBySysGroupId(ids,staffCode);
		if(flag){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 设置分组信息（已分配流域）
	 * */
	public String setbasinName(){
		log.info("设置分组信息（已分配流域）==SysGroupAction.setbasinName");
		String []ids=mSysGroupFormBean.getIds().split(",");
		String []basinCode=mSysGroupFormBean.getmSysGroupInfoBean().getBasinCode().split(",");
		HashMap<String,Object> mHashMap=new HashMap<String,Object>();
		boolean flag=mSysGroupService.setSysGroupBasinNameByBasinCode(ids,basinCode);
		if(flag){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 移除角色信息分组编号
	 * */
	private void removeSysRoleByGroupCode(List<Map> list){
		mSysRoleService.removeSysRoleByGroupCode(list);
	}
	
	/**
	 * 根据人员编号与流域编号获取人员姓名与流域名称
	 * */
	private List<Map> getBasinAndStaffNameByCode(PageResults mPageResults){
		return mSysGroupService.getBasinAndStaffNameByCode(mPageResults);
	}

	public SysGroupFormBean getmSysGroupFormBean() {
		return mSysGroupFormBean;
	}
	
}
