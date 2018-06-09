package com.lyht.business.consumer.hydrologicalData.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.lyht.business.consumer.hydrologicalData.bean.Fsdr;
import com.lyht.business.consumer.hydrologicalData.dao.FsdrDao;
import com.lyht.business.consumer.hydrologicalData.formbean.FsdrFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class FsdrService {

	@Resource
	private FsdrDao mFsdrDao;
	
	/**
	 * 获取测站信息列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getFsdrListData(FsdrFormBean mFsdrFormBean){
		return mFsdrDao.getFsdrListData(mFsdrFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Fsdr getFsdrInfoById(String upstcd){
		Fsdr mFsdr=new Fsdr();
		if(!"".equals(upstcd)){
			mFsdr=mFsdrDao.getFsdrInfoById(upstcd);
		}
		return mFsdr;
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(Fsdr mFsdr){
		mFsdrDao.saveFsdrInfo(mFsdr);
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Fsdr mFsdr){
		mFsdrDao.updateFsdrInfo(mFsdr);	
	}
	
	/**
	 * 根据主键ID删除行政区代码实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletFsdrInfoByIds(FsdrFormBean mFsdrFormBean){
		String[] idary=mFsdrFormBean.getmFsdrInfoBean().getUpstcd().split(",");
		String[] idary_=mFsdrFormBean.getmFsdrInfoBean().getDwstcd().split(",");
		for(int i=0,j=0;i<idary.length && j<idary_.length;i++,j++){
			mFsdrDao.deletFsdrInfoByIds(idary[i],idary_[j]);
		}
	}
	/**
	 * 导出降水量列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(FsdrFormBean mFsdrFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="洪水传播时间表_"+year + "年" + method + "月" +day + "日";
		String title="洪水传播时间表";
		String [] tab = {"序号","上游站码","下游站码","河段长","安全泄量","流量量级","最小传播时间","最大传播时间","平均传播时间"};
		String [] val = {"UPSTCD","DWSTCD","RCHLEN","SFTQ","QMGN","MNTRTM","MXTRTM","AVTRTM"};
		List result=mFsdrDao.getFsdrListData_export(mFsdrFormBean).getResults();
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
            	Fsdr mFsdr=new Fsdr();
            	mFsdr.setRchlen(Double.parseDouble(list.get(3)));
            	mFsdr.setSftq(Double.parseDouble(list.get(4)));
            	mFsdr.setQmgn(Double.parseDouble(list.get(5)));
            	mFsdr.setMntrtm(Double.parseDouble(list.get(6)));
            	mFsdr.setMxtrtm(Double.parseDouble(list.get(7)));
            	mFsdr.setAvtrtm(Double.parseDouble(list.get(8)));
            	mFsdrDao.saveFsdrInfo(mFsdr);
            }
		}
	}
	
	/**
	 * 根据上下游获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Fsdr getFsdrInfoByud(FsdrFormBean mFsdrFormBean){
		return mFsdrDao.getFsdrInfoByud(mFsdrFormBean);
	}
}
