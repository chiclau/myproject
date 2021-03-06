package com.lyht.business.consumer.hydrologicalData.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.lyht.business.consumer.hydrologicalData.bean.Rvsect;
import com.lyht.business.consumer.hydrologicalData.dao.RvsectDao;
import com.lyht.business.consumer.hydrologicalData.dao.StbprpDao;
import com.lyht.business.consumer.hydrologicalData.formbean.RvsectFormBean;
import com.lyht.util.DateUtil;
import com.lyht.util.ExcelUtils;
import com.lyht.util.ExcelVersionUtil;
import com.lyht.util.ImportExeclUtil;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class RvsectService {

	@Resource
	private RvsectDao mRvsectDao;
	
	/**
	 * 获取断面测试成果列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getRvsectListData(RvsectFormBean mRvsectFormBean){
		return mRvsectDao.getRvsectListData(mRvsectFormBean);
	}
	
	/**
	 * 根据主键ID获取实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Rvsect> getRvsectInfoById(String stcd){
		List<Rvsect> mRvsectList=new ArrayList<Rvsect>();
		if(!"".equals(stcd)){
			mRvsectList=mRvsectDao.getRvsectInfoById(stcd);
		}
		return mRvsectList;
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Rvsect create(Rvsect mRvsect){
		mRvsectDao.saveRvsectInfo(mRvsect);
		return mRvsect;
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Rvsect update(Rvsect mRvsect){
		mRvsectDao.updateRvsectInfo(mRvsect);	
		return mRvsect;
	}
	
	/**
	 * 操作断面测试信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void createX(RvsectFormBean mRvsectFormBean,String []di,String []zb,String []vtno){
		if(di!=null&&!"".equals(di) &&zb!=null&& !"".equals(zb) &&vtno!=null&& !"".equals(vtno)){
			String str ="";
			ArrayList<String> arrayList = new ArrayList<>();//获取前台传过来的所有的vtno序号
			for (int i = 0; i < vtno.length; i++) {
				arrayList.add(vtno[i]);
			}
			List lists=mRvsectDao.getRvsectInfoByParams(mRvsectFormBean);
			for (Object object : lists) {//查询数据库中所有的vtno的序号。
				String[] split = object.toString().split("=");
				String[] split2 = split[1].toString().split("}");
				str = split2[0].toString();
				if(!arrayList.contains(str)){//判断页面传来的序号数据库中是否包含不包含就删除掉
					mRvsectDao.delptno(mRvsectFormBean,str);
				}
			}
			for(int i=0,j=0,k=0;i<di.length&&j<zb.length&&k<vtno.length;i++,j++,k++){
				List<Map> list=mRvsectDao.getRvsectInfoByParams(mRvsectFormBean,vtno[k]);
				if(list.size()>0){
				      mRvsectDao.updateRvsectInfoByParams(mRvsectFormBean,di[i],zb[j],vtno[k]);
				}else{
					  mRvsectDao.insertRvsectInfoByParams(mRvsectFormBean,di[i],zb[j],vtno[k]);
				}
			}
		}else{
			mRvsectDao.deleteById(mRvsectFormBean.getmRvsectInfoBean().getStcd());
		}
	}
	
	/**
	 * 根据主键ID获取 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Rvsect> getRvsectInfoListByIds(String ids){
		List<Rvsect> mRvsectList=new ArrayList<Rvsect>();
		Rvsect mRvsect = new Rvsect();
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			mRvsect = mRvsectDao.getRvsectInfoById_(idary[i]);
			mRvsectList.add(mRvsect);
		}
		return mRvsectList;
	}
	
	/**
	 * 根据主键ID删除 实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletRvsectInfoByIds(String []stcd,String []mstm,String []vtno,String flag){
		String stcd_="";
		String vtno_="";
		if(null==flag || "".equals(flag)){
			for(int i=0,j=0;i<stcd.length&&j<vtno.length;i++,j++){
				stcd_+=""+stcd[i]+",";
				vtno_+=""+vtno[j]+",";
			}
			if(!"".equals(stcd_) && !"".equals(vtno_)){
				if(",".equals(stcd_.substring(stcd_.length()-1)) 
						&& ",".equals(vtno_.substring(vtno_.length()-1))){
					stcd_=stcd_.substring(0,stcd_.length()-1);
					vtno_=vtno_.substring(0,vtno_.length()-1);
					String []_stcd=stcd_.split(",");
					String []_vtno=vtno_.split(",");
					for(int ii=0,jj=0;ii<_stcd.length&&jj<_vtno.length;ii++,jj++){
						mRvsectDao.deletRvsectInfoByIds_(_stcd[ii],_vtno[jj]);
					}
				}
			}
		}else{
			for(int i=0,j=0;i<stcd.length&&j<mstm.length;i++,j++){
				mRvsectDao.deletRvsectInfoByIds(stcd[i],mstm[j]);
			}
		}
	}
	/**
	 * 导出（湖）容曲线列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void export(RvsectFormBean mRvsectFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String year = DateUtil.getYear();//年
		String method = DateUtil.getMonth();//月
		String day = DateUtil.getDay();//日
		String replace="断面测试成果_"+year + "年" + method + "月" +day + "日";
		String title="断面测试成果";
		String [] tab = {"序号","测站编码","施测时间","垂线号","起点距","河底高程","修改时间"};
		String [] val = {"STCD","MSTM","VTNO","DI","ZB","MODITIME"};
		List result=mRvsectDao.getRvsectListData_export(mRvsectFormBean).getResults();
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
		String stcd=Randomizer.nextNumber("rvse", 4);
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
            for(int j=1;j<dataLst.size();j++){
            	List<String> list=dataLst.get(j);
            	Rvsect mRvsect=new Rvsect();
            	if(stbprpList!=null&&stbprpList.size()>0){
            		for (int k = 0; k < stbprpList.size(); k++) {
            			Map map = stbprpList.get(k);
            			String stnm = (String) map.get("STNM");
            			String stcds = (String) map.get("STCD");
            			if(list.get(1).equals(stnm)){
            				mRvsect.setStcd(stcds);
            			}
					}
            	}
            	mRvsect.setMstm(list.get(2).toString());
            	mRvsect.setVtno(list.get(3));
            	mRvsect.setDi(Double.parseDouble(list.get(4)));
            	mRvsect.setZb(Double.parseDouble(list.get(5)));
            	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            	mRvsect.setModitime(df.format(new Date()).toString());
            	mRvsectDao.saveRvsectInfo(mRvsect);
            }
		}
	}
	@Resource
	private StbprpDao mStbprpDao;
}
