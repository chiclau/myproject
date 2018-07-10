package com.lyht.business.consumer.hydrologicalData.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Zqrl;
import com.lyht.business.consumer.hydrologicalData.dao.ZqrlDao;
import com.lyht.business.consumer.hydrologicalData.formbean.ZqrlFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class ZqrlService {

	@Resource
	private ZqrlDao mZqrlDao;
	
	/**
	 * 获取水位流量关系曲线列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getZqrlListData(ZqrlFormBean mZqrlFormBean){
		return mZqrlDao.getZqrlListData(mZqrlFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Zqrl> getZqrlInfoById(ZqrlFormBean mZqrlFormBean){
		return mZqrlDao.getZqrlInfoById(mZqrlFormBean);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Zqrl create(Zqrl mZqrl){
		int i=mZqrlDao.count();
		mZqrlDao.saveZqrlInfo(mZqrl,i);
		return mZqrl;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void createX(ZqrlFormBean mZqrlFormBean,String []ptno,String []z,String []q){
		if(!"".equals(ptno) && !"".equals(z) && !"".equals(q)){
			String str ="";
			ArrayList<String> arrayList = new ArrayList<>();//获取页面传来的ptno序号
			for (int i = 0; i < ptno.length; i++) {
				arrayList.add(ptno[i]);
			}
			List lists=mZqrlDao.getZqrlInfoByParams(mZqrlFormBean);
			for (Object object : lists) {//获取数据库中所有对应的序号。判断是否包含。不包含删除掉。
				String[] split = object.toString().split("=");
				String[] split2 = split[1].toString().split("}");
				str = split2[0].toString();
				if(!arrayList.contains(str)){
					mZqrlDao.delptno(mZqrlFormBean,str);
				}
			}
			for(int i=0,j=0,k=0;i<ptno.length&&j<z.length&&k<q.length;i++,j++,k++){
				List<Map> list=mZqrlDao.getZqrlInfoByParams(mZqrlFormBean,ptno[i]);
				if(list.size()>0){
					mZqrlDao.updateZqrlInfoByParams(mZqrlFormBean,ptno[i],z[j],q[k]);
				}else{
					mZqrlDao.insertZqrlInfoByParams(mZqrlFormBean,ptno[i],z[j],q[k]);
				}
			}
		}
	}
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Zqrl update(Zqrl mZqrl){
		mZqrlDao.updateZqrlInfo(mZqrl);	
		return mZqrl;
	}
	
	/**
	 * 根据主键ID获取行政区代码实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Zqrl> getZqrlInfoListByIds(ZqrlFormBean mZqrlFormBean){
		List<Zqrl> mZqrlList=new ArrayList<Zqrl>();
		Zqrl mZqrl = new Zqrl();
		String[] idary=mZqrlFormBean.getmZqrlInfoBean().getStcd().split(",");
		String[] idary_=mZqrlFormBean.getmZqrlInfoBean().getLnnm().split(",");
		String[] _idary=Double.toString(mZqrlFormBean.getmZqrlInfoBean().getPtno()).split(",");
		for(int i=0,j=0,k=0;i<idary.length&&j<idary_.length&&k<_idary.length;i++,j++,k++){
			 mZqrl = mZqrlDao.getZqrlInfoById_(idary[i],idary_[j],_idary[k]);
			 mZqrlList.add(mZqrl);
		}
		return mZqrlList;
	}
	
	/**
	 * 根据主键ID删除行政区代码实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletZqrlInfoByIds(String []stcd,String []lnnm,String []ptno,String flag){
		if(null==flag || "".equals(flag)){
			String stcd_="";
			String lnnm_="";
			String ptno_="";
			for(int i=0,j=0,k=0;i<stcd.length && j<lnnm.length && k<ptno.length;i++,j++,k++){
				stcd_+=stcd[i]+",";
				lnnm_+=lnnm[j]+",";
				ptno_+=ptno[k]+",";
			}
			if(!"".equals(stcd_) && !"".equals(lnnm_) && !"".equals(ptno_)){
				if(",".equals(stcd_.substring(stcd_.length()-1)) 
						&& ",".equals(lnnm_.substring(lnnm_.length()-1)) 
							&& ",".equals(ptno_.substring(ptno_.length()-1))){
					stcd_=stcd_.substring(0,stcd_.length()-1);
					lnnm_=lnnm_.substring(0,lnnm_.length()-1);
					ptno_=ptno_.substring(0,ptno_.length()-1);
					String []_stcd=stcd_.split(",");
					String []_lnnm=lnnm_.split(",");
					String []_ptno=ptno_.split(",");
					for(int ii=0,jj=0,kk=0;ii<_stcd.length && jj<_lnnm.length 
							&& kk<_ptno.length;ii++,jj++,kk++){
						mZqrlDao.deletZqrlInfoByIds(stcd[ii],lnnm[jj],ptno[kk]);
					}
				}
			}
		}else{
			for(int i=0,j=0;i<stcd.length && j<lnnm.length;i++,j++){
				mZqrlDao.deletZqrlInfoByIds(stcd[i],lnnm[j]);
			}
		}
	}
	/**
	 * 导出降水量列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(ZqrlFormBean mZqrlFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="水位流量关系曲线_"+year + "年" + method + "月" +day + "日";
		String title="水位流量关系曲线";
		String [] tab = {"序号","测站编码","曲线名称","曲线序号","水位","流量","修改时间"};
		String [] val = {"STCD","LNNM","PTNO","Z","Q","MODITIME"};
		List result=mZqrlDao.getZqrlListData_export(mZqrlFormBean).getResults();
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
		String stcd=Randomizer.nextNumber("zqrl", 4);
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
            	Zqrl mZqrl=new Zqrl();
            	/*mZqrl.setStcd(stcd);*/
            	mZqrl.setLnnm(list.get(2));
            	//mZqrl.setPtno(Double.parseDouble(list.get(3)));
            	//mZqrl.setZ(Double.parseDouble(list.get(4)));
            	//mZqrl.setQ(Double.parseDouble(list.get(5)));
            	mZqrl.setModitime(list.get(6));
            	mZqrlDao.saveZqrlInfo(mZqrl);
            }
		}
	}
}
