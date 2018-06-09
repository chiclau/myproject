package com.lyht.business.consumer.hydrologicalData.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Stbprp;
import com.lyht.business.consumer.hydrologicalData.dao.StbprpDao;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class StbprpService {

	@Resource private StbprpDao mStbprpDao;
	
	/** 查询 测站信息
	 * @param formBean
	 * @return
	 */
	public List<Map> getStbprpList(StbprpFormBean formBean){
		return mStbprpDao.getStbprpList(formBean);
		
	}
	
	/**
	 * 获取测站信息列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getStbprpListData(StbprpFormBean mStbprpFormBean){
		return mStbprpDao.getStbprpListData(mStbprpFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Stbprp getStbprpInfoById(StbprpFormBean mStbprpFormBean){
		return mStbprpDao.getStbprpInfoById(mStbprpFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Stbprp getStbprpInfoById_(String stcd){
		return mStbprpDao.getStbprpInfoById_(stcd);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Stbprp create(Stbprp mStbprp){
		mStbprpDao.saveStbprpInfo(mStbprp);
		return mStbprp;
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Stbprp update(Stbprp mStbprp){
		mStbprpDao.updateStbprpInfo(mStbprp);	
		return mStbprp;
	}
	
	/**
	 * 根据主键ID获取行政区代码实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getStbprpInfoListByIds(String ids){
		return mStbprpDao.getStbprpInfoListByIds(ids);
	}
	
	/**
	 * 根据主键ID删除行政区代码实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletStbprpInfoByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			mStbprpDao.deletStbprpInfoByIds(idary[i]);
		}
	}
	/**
	 * 导出降水量列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(StbprpFormBean mStbprpFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="测站信息_"+year + "年" + method + "月" +day + "日";
		String title="测站信息";
		String [] tab = {"序号","测站编码","测站名称","基面名称","基面高程","站类","管理单位","建站年月","报汛等级"};
		String [] val = {"STCD","STNM","DTMNM","DTMEL","STTP","ADMAUTH","ESSTYM","FRGRD"};
		List result=mStbprpDao.getStbprpListData_export(mStbprpFormBean).getResults();
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
		String stcd=Randomizer.nextNumber("stcd", 4);
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
            	Stbprp mStbprp=new Stbprp();
            	mStbprp.setStcd(stcd);
            	mStbprp.setStnm(list.get(2));
            	mStbprp.setDtmnm(list.get(3));
            	mStbprp.setDtmel(Double.parseDouble(list.get(4)));
            	mStbprp.setSttp(list.get(5));
            	mStbprp.setAdmauth(list.get(6));
            	mStbprp.setEsstym(list.get(7));
            	mStbprp.setFrgrd(list.get(8));
            	mStbprpDao.save(mStbprp);
            }
		}
	}
	
	/**
	 * 获取下拉数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getUpstcdData(StbprpFormBean mStbprpFormBean){
		return mStbprpDao.getStbprpListData(mStbprpFormBean);
	}
}
