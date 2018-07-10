package com.lyht.business.consumer.hydrologicalData.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Soil;
import com.lyht.business.consumer.hydrologicalData.dao.SoilDao;
import com.lyht.business.consumer.hydrologicalData.formbean.SoilFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SoilService {

	@Resource
	private SoilDao mSoilDao;
	
	/**
	 * 获取土壤墒情列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSoilListData(SoilFormBean mSoilFormBean){
		return mSoilDao.getSoilListData(mSoilFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Soil getSoilInfoById(SoilFormBean mSoilFormBean){
		return mSoilDao.getSoilInfoById(mSoilFormBean);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(Soil mSoil){
		mSoilDao.saveSoilInfo(mSoil);
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Soil mSoil){
		mSoilDao.updateSoilInfo(mSoil);	
	}
	
	/**
	 * 根据主键ID获取 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSoilInfoListByIds(String ids){
		return mSoilDao.getSoilInfoListByIds(ids);
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletSoilInfoByIds(SoilFormBean mSoilFormBean){
		String[] idary=mSoilFormBean.getmSoilInfoBean().getStcd().split(",");
		String[] idary_=mSoilFormBean.getmSoilInfoBean().getTm().split(",");
		String[] _idary=mSoilFormBean.getmSoilInfoBean().getExkey().split(",");
		for(int i=0,j=0,k=0;i<idary.length&&j<idary_.length&&k<_idary.length;i++,j++,k++){
			mSoilDao.deletSoilInfoByIds(idary[i],idary_[j],_idary[k]);
		}
	}
	/**
	 * 导出土壤墒情列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(SoilFormBean mSoilFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="土壤墒情_"+year + "年" + method + "月" +day + "日";
		String title="土壤墒情";
		String [] tab = {"序号","测站编码","时间","垂线平均含水率","作物种类","土壤类别","干土层厚度","土壤含水率测法"};
		String [] val = {"STCD","TM","VTAVSLM","CRPTY","SLTP","DRSLD","SLMMMT"};
		List result=mSoilDao.getSoilListData_export(mSoilFormBean).getResults();
		String file = ExcelUtils.SellerStat2Excel(result, request, replace,tab,title,val);
		response.setContentType("multipart/form-data");  
		String path = request.getSession().getServletContext().getRealPath("/")+file;
		response.setHeader("Content-Disposition", "attachment;fileName="+new String(file.getBytes("UTF-8"),"ISO8859-1"));
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        File files = new File(path);
        FileInputStream inputStream = new FileInputStream(files);
        ServletOutputStream out= response.getOutputStream();
        int b = 0;  
        byte[] buffer = new byte[1024];  
        while (b != -1){  
            b = inputStream.read(buffer);  
            //4.写到输出流(out)中  
            out.write(buffer,0,b);  
        }  
        inputStream.close();  
        out.close();  
        out.flush();
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
            	Soil mSoil=new Soil();
            	mSoil.setTm(list.get(2));
            	mSoil.setVtavslm(Double.parseDouble(list.get(3)));
            	mSoil.setCrpty(list.get(4));
            	mSoil.setSltp(list.get(5));
            	mSoil.setDrsld(Double.parseDouble(list.get(6)));
            	mSoil.setSlmmmt(list.get(7));
            	mSoilDao.saveSoilInfo(mSoil);
            }
		}
	}
}
