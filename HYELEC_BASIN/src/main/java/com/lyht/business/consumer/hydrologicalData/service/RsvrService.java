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
import com.lyht.business.consumer.hydrologicalData.bean.Rsvr;
import com.lyht.business.consumer.hydrologicalData.dao.RsvrDao;
import com.lyht.business.consumer.hydrologicalData.formbean.RsvrFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class RsvrService {

	@Resource
	private RsvrDao mRsvrDao;
	
	/**
	 * 获取水库水情列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getRsvrListData(RsvrFormBean mRsvrFormBean){
		return mRsvrDao.getRsvrListData(mRsvrFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Rsvr getRsvrInfoById(RsvrFormBean mRsvrFormBean){
		return mRsvrDao.getRsvrInfoById(mRsvrFormBean);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(Rsvr mRsvr){
		mRsvrDao.saveRsvrInfo(mRsvr);
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Rsvr mRsvr){
		mRsvrDao.updateRsvrInfo(mRsvr);	
	}
	
	/**
	 * 根据主键ID获取 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getRsvrInfoListByIds(String ids){
		return mRsvrDao.getRsvrInfoListByIds(ids);
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletRsvrInfoByIds(RsvrFormBean mRsvrFormBean){
		String[] idary=mRsvrFormBean.getmRsvrInfoBean().getStcd().split(",");
		String[] idary_=mRsvrFormBean.getmRsvrInfoBean().getTm().split(",");
		for(int i=0,j=0;i<idary.length&&j<idary_.length;i++,j++){
			mRsvrDao.deletRsvrInfoByIds(idary[i],idary_[j]);
		}
	}
	/**
	 * 导出水库水情列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(RsvrFormBean mRsvrFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="水库水情_"+year + "年" + method + "月" +day + "日";
		String title="水库水情";
		String [] tab = {"序号","测站编码","时间","库水位","入库流量","出库流量","库水特征码","库水水势"};
		String [] val = {"STCD","TM","RZ","INQ","OTQ","RWCHRCD","RWPTN"};
		List result=mRsvrDao.getRsvrListData_export(mRsvrFormBean).getResults();
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
            	Rsvr mRsvr=new Rsvr();
            	mRsvr.setTm(list.get(2));
            	mRsvr.setRz(Double.parseDouble(list.get(3)));
            	mRsvr.setInq(Double.parseDouble(list.get(4)));
            	mRsvr.setOtq(Double.parseDouble(list.get(5)));
            	mRsvr.setRwchrcd(list.get(6));
            	mRsvr.setRwptn(list.get(7));
            	mRsvrDao.saveRsvrInfo(mRsvr);
            }
		}
	}
}
