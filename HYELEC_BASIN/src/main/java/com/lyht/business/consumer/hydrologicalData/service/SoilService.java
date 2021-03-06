package com.lyht.business.consumer.hydrologicalData.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
import com.lyht.business.consumer.hydrologicalData.bean.Soil;
import com.lyht.business.consumer.hydrologicalData.dao.SoilDao;
import com.lyht.business.consumer.hydrologicalData.dao.StbprpDao;
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
	@Resource
	private StbprpDao mStbprpDao;
	
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
		String [] tab = {"序号","测站编码","时间","垂线平均含水率","表层含水率",
				"10cm深度含水率","20cm深度含水率","30cm深度含水率","40cm深度含水率","50cm深度含水率","60cm深度含水率","作物种类","作物生长期",
				"作物受灾原因","作物受灾程度","土壤类别","土壤质地","干土层厚度","灌溉相隔天数","降雨相隔天数","土壤含水率测法"};
		String [] val = {"STNM","TM","VTAVSLM","SRLSLM",
				"SLM10","SLM20","SLM30","SLM40","SLM50","SLM60","CRPTY","CRPGRWPRD","HITRSN","HITEXT","SLTP","SLPQ","DRSLD","IRRINTV","PINTV","SLMMMT"};
				
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
            List<Map> stbprpList = mStbprpDao.getStbprpList(null);
            for(int j=1;j<dataLst.size();j++){
            	List<String> list=dataLst.get(j);
            	Soil mSoil=new Soil();
            	if(stbprpList!=null&&stbprpList.size()>0){
            		for (int k = 0; k < stbprpList.size(); k++) {
            			Map map = stbprpList.get(k);
            			String stnm = (String) map.get("STNM");
            			String stcds = (String) map.get("STCD");
            			if(list.get(1).equals(stnm)){
            				mSoil.setStcd(stcds);
            			}
					}
            	}
            	mSoil.setTm(list.get(2));
            	if(list.get(3)!=null&&!"".equals(list.get(3)))mSoil.setVtavslm(Double.parseDouble(list.get(3)));
            	if(list.get(4)!=null&&!"".equals(list.get(4)))mSoil.setSrlslm(Double.parseDouble(list.get(4)));
            	if(list.get(5)!=null&&!"".equals(list.get(5)))mSoil.setSlm10(Double.parseDouble(list.get(5)));
            	if(list.get(6)!=null&&!"".equals(list.get(6)))mSoil.setSlm20(Double.parseDouble(list.get(6)));
            	if(list.get(7)!=null&&!"".equals(list.get(7)))mSoil.setSlm30(Double.parseDouble(list.get(7)));
            	if(list.get(8)!=null&&!"".equals(list.get(8)))mSoil.setSlm40(Double.parseDouble(list.get(8)));
            	if(list.get(9)!=null&&!"".equals(list.get(9)))mSoil.setSlm50(Double.parseDouble(list.get(9)));
            	if(list.get(10)!=null&&!"".equals(list.get(10)))mSoil.setSlm60(Double.parseDouble(list.get(10)));
            	String crpty = list.get(11);
            	if(crpty.equals("白地"))mSoil.setCrpty("0");
            	if(crpty.equals("小麦"))mSoil.setCrpty("1");
            	if(crpty.equals("水稻"))mSoil.setCrpty("2");
            	if(crpty.equals("春播杂粮"))mSoil.setCrpty("3");
            	if(crpty.equals("夏播杂粮"))mSoil.setCrpty("4");
            	if(crpty.equals("薯类"))mSoil.setCrpty("5");
            	if(crpty.equals("棉花"))mSoil.setCrpty("6");
            	if(crpty.equals("油菜"))mSoil.setCrpty("7");
            	if(crpty.equals("甘蔗"))mSoil.setCrpty("8");
            	if(crpty.equals("其它作物"))mSoil.setCrpty("9");
            	String crpgrwprd = list.get(12);
            	if(crpgrwprd.equals("白地"))mSoil.setCrpgrwprd("0");
            	if(crpgrwprd.equals("播种期"))mSoil.setCrpgrwprd("1");
            	if(crpgrwprd.equals("幼苗期"))mSoil.setCrpgrwprd("2");
            	if(crpgrwprd.equals("成长期"))mSoil.setCrpgrwprd("3");
            	if(crpgrwprd.equals("开花结果期"))mSoil.setCrpgrwprd("4");
            	if(crpgrwprd.equals("黄熟收割期"))mSoil.setCrpgrwprd("5");
            	String hitrsn = list.get(13);
            	if(hitrsn.equals("生长正常"))mSoil.setHitrsn("0");
            	if(hitrsn.equals("干旱"))mSoil.setHitrsn("1");
            	if(hitrsn.equals("洪涝"))mSoil.setHitrsn("2");
            	if(hitrsn.equals("大风"))mSoil.setHitrsn("3");
            	if(hitrsn.equals("霜冻"))mSoil.setHitrsn("4");
            	if(hitrsn.equals("冰雹"))mSoil.setHitrsn("5");
            	if(hitrsn.equals("其它"))mSoil.setHitrsn("6");
            	String hitext = list.get(14);
            	if(hitext.equals("未受灾"))mSoil.setHitext("0");
            	if(hitext.equals("轻度受灾"))mSoil.setHitext("1");
            	if(hitext.equals("中度受灾"))mSoil.setHitext("2");
            	if(hitext.equals("严重受灾"))mSoil.setHitext("3");
            	if(hitext.equals("绝收"))mSoil.setHitext("4");
            	String sltp = list.get(15);
            	if(sltp.equals("其他"))mSoil.setSltp("0");
            	if(sltp.equals("沙土"))mSoil.setSltp("1");
            	if(sltp.equals("壤土"))mSoil.setSltp("2");
            	if(sltp.equals("粘土"))mSoil.setSltp("3");
            	if(sltp.equals("壤砂土"))mSoil.setSltp("4");
            	if(sltp.equals("砂壤土"))mSoil.setSltp("5");
            	if(sltp.equals("粘壤土"))mSoil.setSltp("6");
            	String slpq = list.get(16);
            	if(slpq.equals("粗砂土"))mSoil.setSlpq("11");
            	if(slpq.equals("细砂土"))mSoil.setSlpq("12");
            	if(slpq.equals("面砂土"))mSoil.setSlpq("13");
            	if(slpq.equals("砂粉土"))mSoil.setSlpq("21");
            	if(slpq.equals("粉土"))mSoil.setSlpq("22");
            	if(slpq.equals("粉壤土"))mSoil.setSlpq("23");
            	if(slpq.equals("黏壤土"))mSoil.setSlpq("24");
            	if(slpq.equals("砂黏土"))mSoil.setSlpq("25");
            	if(slpq.equals("粉黏土"))mSoil.setSlpq("31");
            	if(slpq.equals("壤黏土"))mSoil.setSlpq("32");
            	if(slpq.equals("黏土"))mSoil.setSlpq("33");
            	if(list.get(17)!=null&&!"".equals(list.get(17)))mSoil.setDrsld(Double.parseDouble(list.get(17)));
            	if(list.get(18)!=null&&!"".equals(list.get(18)))mSoil.setIrrintv(Double.parseDouble(list.get(17)));
            	if(list.get(19)!=null&&!"".equals(list.get(19)))mSoil.setPintv(Double.parseDouble(list.get(17)));
            	String slmmmt = list.get(20);
            	if(slmmmt.equals("烘干法"))mSoil.setSlmmmt("1");
            	if(slmmmt.equals("中子水分仪法"))mSoil.setSlmmmt("2");
            	if(slmmmt.equals("时域反射法"))mSoil.setSlmmmt("3");
            	if(slmmmt.equals("张力计法"))mSoil.setSlmmmt("4");
            	if(slmmmt.equals("频域法"))mSoil.setSlmmmt("5");
            	if(slmmmt.equals("其它方法"))mSoil.setSlmmmt("6");
            	mSoilDao.saveSoilInfo(mSoil);
            }
		}
	}
}
