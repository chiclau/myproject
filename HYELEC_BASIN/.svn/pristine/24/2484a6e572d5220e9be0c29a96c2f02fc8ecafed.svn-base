package com.lyht.business.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.bean.SysGroup;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.dao.EnnmcdDao;
import com.lyht.business.system.dao.SysGroupDao;
import com.lyht.business.system.dao.SysStaffDao;
import com.lyht.business.system.formbean.SysGroupFormBean;
import com.lyht.util.DesUtils;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysGroupService {

	@Resource
	private SysGroupDao mSysGroupDao;
	@Resource
	private SysStaffDao mSysStaffDao;
	@Resource
	private EnnmcdDao mEnnmcdDao;
	
	/**
	 * 获取分组管理列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSysGroupListData(SysGroupFormBean mSysGroupFormBean){
		return mSysGroupDao.getSysGroupListData(mSysGroupFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysGroup getSysGroupInfoById(int id){
		SysGroup mSysGroup=new SysGroup();
		if(id>0){
			mSysGroup=mSysGroupDao.getSysGroupInfoById(id);
		}
		return mSysGroup;
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysGroup create(SysGroup mSysGroup){
		mSysGroupDao.saveSysGroupInfo(mSysGroup);
		return mSysGroup;
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysGroup update(SysGroup mSysGroup){
		mSysGroupDao.updateSysGroupInfo(mSysGroup);	
		return mSysGroup;
	}
	
	/**
	 * 根据主键ID获取分组管理实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSysGroupInfoListByIds(String ids){
		return mSysGroupDao.getSysGroupInfoListByIds(ids);
	}
	
	/**
	 * 根据主键ID删除分组管理实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSysGroupInfoByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			mSysGroupDao.deleteSysGroupInfoByIds(Integer.parseInt(idary[i]));
		}
	}
	
	/**
	 * 移除分组信息人员编号
	 * */
	public void removeSysGroupStaffCode(String staffCode){
		List<Map> list=mSysGroupDao.getSysGroupInfoByStaffCode(staffCode);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object obj_=list.get(i).get("GROUP_CODE");
				Object obj=list.get(i).get("STAFF_CODE");
				if(null!=obj){
					String key=obj.toString(); //数据库查询
					String staffCode_=DesUtils.removeListElement(key.split(","),staffCode);
					mSysGroupDao.updateSysGroupByStaffCode(obj_.toString(),staffCode_);
				}
			}
		}
	}
	
	/**
	 * 根据分组主键id查询人员编号是否有值
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean setStaffNameBySysGroupId(String []ids,String []staffCode){
		String str="";
		boolean flag=false;
		for(int i=0;i<staffCode.length;i++){
			for(int j=0;j<ids.length;j++){
				List<Map> list=mSysGroupDao.getSysGroupListById(Integer.parseInt(ids[i]));
				if(list.size()>0){
					Object obj=list.get(0).get("STAFF_CODE");
					if(null!=obj){
						String staffCode_=obj.toString();  //数据库中的值
						if(null==staffCode_ || "".equals(staffCode_)){
							flag=mSysGroupDao.updateSysGroupStaffCodeBySysGroupId(ids[j],staffCode[i]);
						}else{
							String []_staffCode_=staffCode_.split(",");  //数据库中的值
							for(int k=0;k<_staffCode_.length;k++){
								if(_staffCode_[k].equals(staffCode[i])){
									str=staffCode_;
									break;
								}else{
									str=staffCode_+","+staffCode[i];
								}
							}
							str=DesUtils.removeDuplicate(str);
							flag=mSysGroupDao.updateSysGroupStaffCodeBySysGroupId(ids[j],str);
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 根据人员编号与流域编号获取人员姓名与流域名称
	 * */
	@SuppressWarnings("unchecked")
	public List<Map> getBasinAndStaffNameByCode(PageResults mPageResults){
		String str="";
		List<Map> list=mPageResults.getResults();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object obj=list.get(i).get("STAFF_CODE");
				Object obj_=list.get(i).get("BASIN_CODE");
				if(null!=obj){
					String [] staffCode=obj.toString().split(",");
					for(int j=0;j<staffCode.length;j++){
						SysStaff mSysStaff=mSysStaffDao.getStaffInfoByStaffCode(staffCode[j]);
						str+=mSysStaff.getStaffName()+",";
					}
					if(",".equals(str.substring(str.length()-1))){
						str=str.substring(0,str.length()-1);
					}
					list.get(i).put("STAFF_NAME", str);
				}
				str="";
				if(null!=obj_){
					String [] basinCode=obj_.toString().split(",");
					for(int k=0;k<basinCode.length;k++){
						Ennmcd mEnnmcd=mEnnmcdDao.getEnnmcdInfoByBasinCode(basinCode[k]);
						str+=mEnnmcd.getRvnm()+",";
					}
					if(",".equals(str.substring(str.length()-1))){
						str=str.substring(0,str.length()-1);
					}
					list.get(i).put("RVNM_NAME", str);
				}
				str="";
			}
		}
		return list;
	}
	
	/**
	 * 根据流域编号设置分组信息流域名称
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean setSysGroupBasinNameByBasinCode(String []ids,String []basinCode){
		String str="";
		boolean flag=false;
		for(int i=0;i<basinCode.length;i++){
			for(int j=0;j<ids.length;j++){
				List<Map> list=mSysGroupDao.getSysGroupListById(Integer.parseInt(ids[j]));
				if(list.size()>0){
					Object obj=list.get(0).get("BASIN_CODE");
					if(null!=obj){
						String basinCode_=obj.toString(); //数据库的值
						if(null==basinCode_ || "".equals(basinCode_)){
							flag=mSysGroupDao.updateSysGroupBasinCodeByBasinCode(ids[j],basinCode[i]);
						}else{
							String []_basinCode_= basinCode_.split(",");
							for(int k=0;k<_basinCode_.length;k++){
								if(_basinCode_[k].equals(basinCode[i])){
									str=basinCode_;
									break;
								}else{
									str=basinCode_+","+basinCode[i];
								}
							}
							str=DesUtils.removeDuplicate(str);
							flag=mSysGroupDao.updateSysGroupBasinCodeByBasinCode(ids[j],str);
						}
					}
				}
			}
		}
		return flag;
	}
	
}
