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
import com.lyht.business.consumer.hydrologicalData.bean.Pptn;
import com.lyht.business.consumer.hydrologicalData.dao.PptnDao;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.business.search.formBean.SearchFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class PptnService {

	@Resource
	private PptnDao mPptnDao;
	
	public List<Map> getPptnListByRain(SearchFormBean searchFormBean){
		return mPptnDao.getPptnListByRain(searchFormBean);
		
	}
	
	/**
	 * 获取降水量列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getPptnListData(PptnFormBean mPptnFormBean){
		return mPptnDao.getPptnListData(mPptnFormBean);
	}
	
	/**
	 * 导出降水量列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(PptnFormBean mPptnFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="降水量_"+year + "年" + method + "月" +day + "日";
		String title="降水量";
		String [] tab = {"序号","测站编码","时间","时段降水量","时段长","降水历时","日降水量","天气状况"};
		String [] val = {"STCD","TM","DRP","INTV","PDR","DYP","WTH"};
		List result=mPptnDao.getPptnListData(mPptnFormBean).getResults();
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
            for(int a=1;a<dataLst.size();a++){
            	List<String> list=dataLst.get(a);
            	Pptn mPptn=new Pptn();
            	mPptn.setTm(list.get(2));
            	mPptn.setDrp(Double.parseDouble(list.get(3)));
            	mPptn.setIntv(Double.parseDouble(list.get(4)));
            	mPptn.setPdr(Double.parseDouble(list.get(5)));
            	mPptn.setDyp(Double.parseDouble(list.get(6)));
            	mPptn.setWth(list.get(7));
            	mPptnDao.savePptnInfo(mPptn);
//            	mDemand.setDemandUnit(dataLst.get(a).get(5));
            	
//            	dMaterialDetailDao.save(mMaterialDetail);
//            	int nub=dDemandDao.queryPlayCode(mDemand.getPlanCode());
//            	if(nub==0){
//            		dDemandDao.save(mDemand);
//            	}
            }
	            	
		}
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(Pptn mPptn){
		mPptnDao.savePptnInfo(mPptn);
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Pptn mPptn){
		mPptnDao.updatePptnInfo(mPptn);	
	}
	
	/**
	 * 根据主键ID删除降水量
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletPptnInfoByIds(PptnFormBean mPptnFormBean){
		String[] idary=mPptnFormBean.getmPptnInfoBean().getStcd().split(",");
		String[] idary_=mPptnFormBean.getmPptnInfoBean().getTm().split(",");
		for(int i=0,j=0;i<idary.length && j<idary_.length;i++,j++){
			mPptnDao.deletPptnInfoByIds(idary[i],idary_[j]);
		}
	}
	
	/**
	 * 根据主键ID查询降水量
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Pptn getPptnInfoById(PptnFormBean mPptnFormBean){
		return mPptnDao.getPptnInfoById(mPptnFormBean);
	}
	
}
