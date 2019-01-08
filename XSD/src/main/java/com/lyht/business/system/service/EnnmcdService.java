package com.lyht.business.system.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.dao.EnnmcdDao;
import com.lyht.business.system.dao.SysGroupDao;
import com.lyht.business.system.formbean.EnnmcdFormBean;
import com.lyht.util.DesUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class EnnmcdService {

	@Resource
	private EnnmcdDao mEnnmcdDao;
	@Resource
	private SysGroupDao mSysGroupDao;
	
	/** 查询流域树形 
	 * @param pid
	 * @return
	 */
	public List<Map> searchLyTree(String pid){
		return mEnnmcdDao.searchLyTree(pid);
	}
	
	/**
	 * 获取根节点数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getListRootData(EnnmcdFormBean mEnnmcdFormBean){
		return mEnnmcdDao.getListRootData(mEnnmcdFormBean);
	}
	
	/**
	 * 获取流域水系代码列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getEnnmcdListData(EnnmcdFormBean mEnnmcdFormBean){
		return mEnnmcdDao.getEnnmcdListData(mEnnmcdFormBean);
	}
	
	/**
	 * 根据主键ID获取流域水系代码信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED) 
	public Ennmcd getEnnmcdInfoById(String rvcd){
		Ennmcd mEnnmcd=new Ennmcd();
		if(!"".equals(rvcd)){
			mEnnmcd=mEnnmcdDao.getEnnmcdInfoById(rvcd);
		}
		return mEnnmcd;
	}
	
	/**
	 * 根据主键ID获取流域水系代码信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED) 
	public Ennmcd getEnnmcdInfoById_(String rvcd){
		Ennmcd mEnnmcd=new Ennmcd();
		if(!"".equals(rvcd)){
			mEnnmcd=mEnnmcdDao.getEnnmcdInfoById_(rvcd);
		}
		return mEnnmcd;
	}
	
	/**
	 * 根据属性及属性值查找对象实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Ennmcd getEnnmcdInfoByProp(String PropName,Object PropValue){
		return mEnnmcdDao.getEnnmcdInfoByProp(PropName,PropValue);
	}
	
	/**
	 * 增加实体对象（根节点）
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Ennmcd create(Ennmcd mEnnmcd){
		mEnnmcd.setPath(mEnnmcd.getRvcd());
		mEnnmcdDao.saveEnnmcdInfo(mEnnmcd);
		return mEnnmcd;
	}
	
	/**
	 * 增加实体对象（子节点）
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Ennmcd create(Ennmcd mEnnmcd,Ennmcd mPEnnmcd){
		String path;
		Ennmcd mEnnmcd_ = mEnnmcdDao.getEnnmcdInfoById(mPEnnmcd.getRvcd());
		if("".equals(mEnnmcd_.getPrvcd())){
			path=mPEnnmcd.getRvcd()+","+mEnnmcd.getRvcd();
		}else{
			path=mEnnmcd_.getRvcd()+","+mEnnmcd.getRvcd();
			path = dgcx(path,mEnnmcd_.getPrvcd());
		}
		mEnnmcd.setPath(path);
		mEnnmcd.setPrvcd(mPEnnmcd.getRvcd());
		mEnnmcdDao.saveEnnmcdInfo(mEnnmcd);
		return mEnnmcd;
	}
	
	public String dgcx(String path,String prvcd){
		Ennmcd mEnnmcd_ = mEnnmcdDao.getEnnmcdInfoById(prvcd);
		if(!"".equals(mEnnmcd_.getPrvcd())){
			path=mEnnmcd_.getPrvcd()+","+path;
			dgcx(path,prvcd);
		}else{
			path=mEnnmcd_.getRvcd()+","+path;
		}
		return path;
	}
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateEnnmcdInfo(Ennmcd mEnnmcd){
		mEnnmcdDao.updateEnnmcdInfo(mEnnmcd);
	}
	
	/**
	 * 根据流域编号获取全部流域及子流域信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getRootOrChildEnnmcdInfoByRvcd(EnnmcdFormBean mEnnmcdFormBean){
      return mEnnmcdDao.getEnnmcdListData(mEnnmcdFormBean);
	}
	
	/**
	 * 根据流域编号(1,2,3,4,5)删除多个对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean deleteEnnmcdInfoByRvcd(Ennmcd mEnnmcd){
		return mEnnmcdDao.deleteEnnmcdInfoByRvcd(mEnnmcd);
	}
	
	/**
	 * 根据流域编号获取流域信息
	 * */
	public void removeGroupInfoByRvcd(String rvcd){
		List<Map> list= mEnnmcdDao.getEnnmcdInfoByRvcd(rvcd);
		if(list.size()>0){
			String rvcd_="";
			Object obj=null;
			for(int i=0;i<list.size();i++){
				obj=list.get(i).get("RVCD");
				if(null!=obj){
					rvcd_+=obj.toString()+","; 
				}
			}
			if(null!=obj){
				if(",".equals(rvcd_.substring(rvcd_.length()-1))){
					rvcd_=rvcd_.substring(0,rvcd_.length()-1);
				}
				List<Map> list_=mSysGroupDao.getSysGroupInfoByRvcd(rvcd_);
				if(list_.size()>0){
					for(int j=0;j<list_.size();j++){
						Object obj_=list_.get(j).get("GROUP_CODE");
						Object _obj_=list_.get(j).get("BASIN_CODE");
						if(null!=obj_ && null!=_obj_){
							String key=_obj_.toString(); 
							String basinCode_=DesUtils.removeListElement(key.split(","),rvcd_);
							mSysGroupDao.updateSysRoleByBasinCode(basinCode_,obj_.toString());
						}
					}
				}
			}
		}
	}
	
	/**
	 * 导入数据
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@Transactional(propagation=Propagation.REQUIRED)
	public void importPptn(File[] file,String[] fileFileName) throws IOException{
		File[] srcFiles = file;
		InputStream in = null;
		ExcelVersionUtil ev=new ExcelVersionUtil();
		ImportExeclUtil importExeclUtil=new ImportExeclUtil();
		for(int i = 0; i < srcFiles.length; i++){
			in = new BufferedInputStream(new FileInputStream(srcFiles[i]));
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
            if (ev.isExcel2007(fileFileName[i])) {  
                isExcel2003 = false;  
            }
            //通过工具栏ImportExeclUtil中的read方法解析excel
            List<List<String>> dataLst =importExeclUtil.read(in,isExcel2003);
            for(int j=1;j<dataLst.size();j++){
            	List<String> list=dataLst.get(j);
            	Ennmcd enn = new Ennmcd();
            	enn.setRvcd(list.get(0));
            	enn.setRvnm(list.get(1));
            	enn.setPrvcd(list.get(2));
            	enn.setPath(list.get(3));
            	enn.setPaixu(list.get(4));
            	enn.setLgtd(list.get(5));
            	enn.setLttd(list.get(6));
            	mEnnmcdDao.save(enn);
            }
		}
	}
	
}
