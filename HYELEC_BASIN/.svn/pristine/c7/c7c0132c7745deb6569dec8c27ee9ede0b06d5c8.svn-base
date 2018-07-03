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
import com.lyht.business.consumer.hydrologicalData.bean.River;
import com.lyht.business.consumer.hydrologicalData.dao.RiverDao;
import com.lyht.business.consumer.hydrologicalData.formbean.RiverFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class RiverService {

	@Resource
	private RiverDao mRiverDao;
	
	/**
	 * 获取河道水情列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getRiverListData(RiverFormBean mRiverFormBean){
		return mRiverDao.getRiverListData(mRiverFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public River getRiverInfoById(RiverFormBean mRiverFormBean){
		return mRiverDao.getRiverInfoById(mRiverFormBean);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(River mRiver){
		mRiverDao.saveRiverInfo(mRiver);
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(River mRiver){
		mRiverDao.updateRiverInfo(mRiver);	
	}
	
	/**
	 * 根据主键ID获取 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getRiverInfoListByIds(String ids){
		return mRiverDao.getRiverInfoListByIds(ids);
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletRiverInfoByIds(RiverFormBean mRiverFormBean){
		String[] idary=mRiverFormBean.getmRiverInfoBean().getStcd().split(",");
		String[] idary_=mRiverFormBean.getmRiverInfoBean().getTm().split(",");
		for(int i=0,j=0;i<idary.length&&j<idary_.length;i++,j++){
			mRiverDao.deletRiverInfoByIds(idary[i],idary_[j]);
		}
	}
	/**
	 * 导出（湖）容曲线列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(RiverFormBean mRiverFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="河道水情_"+year + "年" + method + "月" +day + "日";
		String title="河道水情";
		String [] tab = {"序号","测站编码","时间","水位","流量","河水特征码","水势"};
		String [] val = {"STCD","TM","Z","Q","FLWCHRCD","WPTN"};
		List result=mRiverDao.getRiverListData_export(mRiverFormBean).getResults();
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
            	River mRiver=new River();
            	mRiver.setTm(list.get(2));
            	mRiver.setZ(Double.parseDouble(list.get(3)));
            	mRiver.setQ(Double.parseDouble(list.get(4)));
            	mRiver.setFlwchrcd(list.get(5));
            	mRiver.setWptn(list.get(6));
            	mRiverDao.saveRiverInfo(mRiver);
            }
		}
	}
}
