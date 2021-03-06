package com.lyht.business.consumer.hydrologicalData.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import com.lyht.business.consumer.hydrologicalData.bean.Fsdr;
import com.lyht.business.consumer.hydrologicalData.bean.Tsqx;
import com.lyht.business.consumer.hydrologicalData.dao.StbprpDao;
import com.lyht.business.consumer.hydrologicalData.dao.TsqxDao;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class TsqxService {

	@Resource
	private TsqxDao mTsqxDao;
	@Resource
	private StbprpDao mStbprpDao;
	
	/**
	 * 获取退水曲线列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getTsqxListData(TsqxFormBean mTsqxFormBean){
		return mTsqxDao.getTsqxListData(mTsqxFormBean);
	}
	
	/**
	 * 导出退水曲线列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(TsqxFormBean mTsqxFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="退水曲线_"+year + "年" + method + "月" +day + "日";
		String title="退水曲线";
		String [] tab = {"序号","站码","用户名","洪峰","流量","时段"};
		String [] val = {"STCD","USERNAME","QM","Q","T"};
		List result=mTsqxDao.getTsqxListDatas(mTsqxFormBean).getResults();
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
	public void importTsqx(File[] file,String[] fileFileName) throws IOException{
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
            List<Map> stbprpList = mStbprpDao.getStbprpList(null);
            for(int a=1;a<dataLst.size();a++){
            	List<String> list=dataLst.get(a);
            	Tsqx mTsqx=new Tsqx();
            	if(stbprpList!=null&&stbprpList.size()>0){
            		for (int k = 0; k < stbprpList.size(); k++) {
            			Map map = stbprpList.get(k);
            			String stnm = (String) map.get("STNM");
            			String stcd = (String) map.get("STCD");
            			if(list.get(1).equals(stnm)){
            				mTsqx.setStcd(stcd);
            			}
					}
            	}
            	mTsqx.setUsername(list.get(2));
            	mTsqx.setQm(Float.parseFloat(list.get(3)));
            	mTsqx.setQ(Float.parseFloat(list.get(4)));
            	mTsqx.setT(Float.parseFloat(list.get(5)));
            	mTsqxDao.saveTsqxInfo(mTsqx);
            }
	            	
		}
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Tsqx create(Tsqx mTsqx){
		mTsqxDao.saveTsqxInfo(mTsqx);
		return mTsqx;
	}
	public List<Tsqx> queryByStcd(String stcd){
		return mTsqxDao.queryTsqxByStcd(stcd);
	}
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(TsqxFormBean mTsqxFormBean){
		mTsqxDao.updateTsqxInfo(mTsqxFormBean);	
	}
	
	/**
	 * 根据主键ID获取退水曲线实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getTsqxInfoListByIds(String ids){
		return mTsqxDao.getTsqxInfoListByIds(ids);
	}
	
	/**
	 * 根据主键ID删除退水曲线
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletTsqxInfoByIds(String ids,String userName){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			mTsqxDao.deletTsqxInfoByIds(idary[i],userName);
		}
	}
	
	/**
	 * 根据主键ID查询退水曲线
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Tsqx getTsqxInfoById(String stcd){
		return mTsqxDao.getTsqxInfoById(stcd);
	}

	public void createX(TsqxFormBean mTsqxFormBean, String[] qm, String[] q, String[] t) {
		mTsqxDao.delete(mTsqxFormBean);
		if(qm!=null&&q!=null&&t!=null){
			for(int i=0,j=0,k=0;i<qm.length&&j<q.length&&k<t.length;i++,j++,k++){
				mTsqxDao.saveTsqxInfo(mTsqxFormBean,qm[i],q[j],t[k]);
			}
		}
	}
	
}
