package com.lyht.business.system.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Addvcd;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.dao.AddvcdDao;
import com.lyht.business.system.formbean.AddvcdFormBean;
import com.lyht.util.CommonFunction;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class AddvcdService {

	@Resource
	private AddvcdDao mAddvcdDao;
	
	/**
	 * 获取根节点数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getListRootData(AddvcdFormBean mAddvcdFormBean){
		return mAddvcdDao.getListRootData(mAddvcdFormBean);
	}
	
	/**
	 * 获取行政区域代码所有节点数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getAddvcdListData(AddvcdFormBean mAddvcdFormBean){
		return mAddvcdDao.getAddvcdListData(mAddvcdFormBean);
	}
	
	/**
	 * 根据主键ADDVCD获取行政区域代码信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED) 
	public Addvcd getAddvcdInfoById(String addvcd){
		Addvcd mAddvcd=new Addvcd();
		if(addvcd != null && addvcd != ""){
			mAddvcd=mAddvcdDao.getAddvcdInfoById(addvcd);
		}
		return mAddvcd;
	}
	
	/**
	 * 根据区域代码删除行政区域信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED) 
	public boolean removeAddvcdInfoByAddvcd(String addvcd){
		Addvcd mAddvcd=mAddvcdDao.getAddvcdInfoByAddvcd(addvcd);
		return mAddvcdDao.removeAddvcdInfoByAddvcd(mAddvcd,addvcd);
	}
	
	/**
	 * 根据区域代码获取行政区域信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED) 
	public Addvcd getAddvcdInfoByAddvcd(String addvcd){
		return mAddvcdDao.getAddvcdInfoByAddvcd(addvcd);
	}
	
	/**
	 * 根据属性及属性值查找对象实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Addvcd getAddvcdInfoByProp(String PropName,Object PropValue){
		return mAddvcdDao.getAddvcdInfoByProp(PropName,PropValue);
	}
	
	/**
	 * 增加实体对象（根节点）
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Addvcd create(Addvcd mAddvcd){
		mAddvcdDao.saveAddvcdInfo(mAddvcd);
		return mAddvcd;
	}
	
	/**
	 * 增加实体对象（子节点）
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Addvcd create_(Addvcd mAddvcd){
		mAddvcdDao.saveAddvcdInfo_(mAddvcd);
		return mAddvcd;
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAddvcdInfo(Addvcd mAddvcd){
		mAddvcdDao.updateAddvcdInfo(mAddvcd);
	}
	
	/**
	 * 通过省份编码加载市区数据
	 * */
	public List<Map> loadCityData(String prov){
		return mAddvcdDao.loadCityData(prov);
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
            	
            	Addvcd addvcd = new Addvcd();
            	
            	addvcd.setAddvcd(list.get(0));
            	addvcd.setName(list.get(1));
            	addvcd.setPaddvcd(list.get(2));
            	mAddvcdDao.save(addvcd);
            }
		}
	}
}
