package com.lyht.business.analysisCalculation.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.StPptnDayR;
import com.lyht.business.analysisCalculation.dao.StPptnDayRDao;
import com.lyht.business.analysisCalculation.formbean.StPptnDayRFormBean;
import com.lyht.business.consumer.hydrologicalData.bean.Pptn;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
public class StPptnDayRService {

	@Resource private StPptnDayRDao stPptnDayRDao;
	
	public PageResults getStPptnDayRListData(StPptnDayRFormBean stPptnDayRFormBean){
		return stPptnDayRDao.getStPptnDayRListData(stPptnDayRFormBean);
	}
	
	public StPptnDayR queryStPptnDayRByNm(String nm){
		return stPptnDayRDao.get(nm);
	}
	public void saveOrUpdate(StPptnDayR stPptnDayR){
		if(stPptnDayR!=null){
			if(CommonUtil.trim(stPptnDayR.getNm()).length()<1){
				String tm=stPptnDayR.getTm();
				tm=tm.replace("-", "");
				tm=tm.replace(":", "");
				tm=tm.replace(" ", "");
				String nm=stPptnDayR.getStcd()+tm;
				stPptnDayR.setNm(nm);
			}
			stPptnDayRDao.saveOrUpdate(stPptnDayR);
		}
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public int deletByIds(String nms){
		return this.stPptnDayRDao.deleteByIds(nms);
	}
	/**
	 * 导入数据
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@Transactional(propagation=Propagation.REQUIRED)
	public Hashtable<String,String> importPptnLast(File[] file,String[] fileFileName) throws IOException{
		Hashtable<String,String> table = new Hashtable<String,String>();
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
            List<StPptnDayR> dataLst =importExeclUtil.parseDayPptnExcel(in,isExcel2003);
            if(dataLst==null || dataLst.size()<1){
            	table.put("reflag", "0");
            	table.put("message", "Excel表格中未解析到数据!");
            	return table;
            }
            //校验数据
            for(int a=0;a<dataLst.size();a++){
            	StPptnDayR pp = dataLst.get(a);
            	if(pp!=null){
            		String tm = pp.getTm();
            		tm=tm.replace(":", "");
            		tm=tm.replace(" ", "");
            		tm=tm.replace("-", "");
            		String nm=pp.getStcd()+tm;
            		pp.setNm(nm);
            		stPptnDayRDao.saveOrUpdate(pp);
            	}
            }
		}
		table.put("reflag", "1");
    	return table;
	}
}
