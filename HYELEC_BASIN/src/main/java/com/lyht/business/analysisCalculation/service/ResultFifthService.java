package com.lyht.business.analysisCalculation.service;

import java.util.Hashtable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ResultFifPa;
import com.lyht.business.analysisCalculation.bean.ResultFifth;
import com.lyht.business.analysisCalculation.bean.ResultJg;
import com.lyht.business.analysisCalculation.bean.ResultSecond;
import com.lyht.business.analysisCalculation.dao.ResultFifthDao;
import com.lyht.business.analysisCalculation.dao.ResultJgDao;
import com.lyht.business.analysisCalculation.dao.ResultSecondDao;
import com.lyht.business.analysisCalculation.dao.ResutFifPaDao;
import com.lyht.business.analysisCalculation.formbean.ResultFifthFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.Randomizer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 第五步serviece
 * @author 刘魁
 *
 */
@Service
@Scope("prototype")
@Transactional
public class ResultFifthService {
	@Resource
	private ResultFifthDao rfDao;
	@Resource
	private ResutFifPaDao rfPaDao;//PaDao
	@Resource
	private ResultSecondDao rsDao;
	@Resource ResultJgDao rjDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Hashtable<String,Object> saveStep5Result(String stcd,String pch,String data){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			JSONObject dataJson = JSONObject.fromObject(data);
			double inpa = dataJson.getDouble("inpa");
			double outpa = dataJson.getDouble("outpa");
			String emstcd=dataJson.getString("emstcd");
			String planCode=dataJson.getString("planCode");
			String start=dataJson.getString("start");
			String end=dataJson.getString("end");
			String interval=dataJson.getString("interval");
			String stcds=dataJson.getString("stcds");
			String stnms=dataJson.getString("stnms");
			String qzs=dataJson.getString("qzs");
			JSONArray dataList = dataJson.getJSONArray("dataList");
			rfDao.deleteByStcdAndPch(stcd,pch);
			if(dataList!=null && dataList.size()>0){
				for(int i=0;i<dataList.size();i++){
					JSONObject beanJson = dataList.getJSONObject(i);
					ResultFifth resultFifth=new ResultFifth();
					resultFifth.setStcd(beanJson.getString("STCD"));
					resultFifth.setDate(beanJson.getString("DATE"));
					resultFifth.setEm(beanJson.getDouble("EM"));
					resultFifth.setPa(beanJson.getDouble("PA"));
					resultFifth.setPch(beanJson.getString("PCH"));
					resultFifth.setQz(beanJson.getString("QZ"));//权重
					resultFifth.setStnm(beanJson.getString("STNM"));//测站名称
					resultFifth.setJyl(beanJson.getString("JYL"));//每个测站下面的降雨量
					resultFifth.setInterval(beanJson.getString("INTERVAL"));//时间间隔
					resultFifth.setYml(beanJson.getDouble("YML"));
					rfDao.saveResutFifth(resultFifth);
				}
			}
			ResultSecond rSecond=rsDao.queryResultSecondByStcdAndPch(stcd, pch);
			if(rSecond==null){
				rSecond=new ResultSecond();
				rSecond.setId(Randomizer.generCodeWidthDate(32));
			}
			rSecond.setPch(pch);
			rSecond.setSTCD(stcd);
			rSecond.setPa(outpa);
			rSecond.setEmstcd(emstcd);
			rSecond.setFanm(planCode);
			rsDao.saveOrUpdate(rSecond);
			ResultFifPa rfPa=new ResultFifPa();
			rfPa.setPa(inpa);
			rfPa.setStcd(stcd);
			rfPa.setPch(pch);
			rfPaDao.delfPa(rfPa);
			rfPaDao.saveResutFifthPa(rfPa);
			ResultJg resultJg=rjDao.get(stcd+pch);
			if(resultJg==null || CommonUtil.trim(resultJg.getId()).length()<1){
				resultJg=new ResultJg();
				resultJg.setId(stcd+pch);
			}
			resultJg.setKssj5(start);
			resultJg.setJssj5(end);
			resultJg.setSjjg5(Integer.valueOf(interval));
			resultJg.setStcds5(stcds);
			resultJg.setStnms5(stnms);
			resultJg.setQzs5(qzs);
			rjDao.saveOrUpdate(resultJg);
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "保存失败!");
		}
		return table;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveResutFifth(ResultFifth rf) {
		rfDao.delf(rf);//删除
		rfDao.saveResutFifth(rf);
	}
	//保存Pa
	@Transactional(propagation=Propagation.REQUIRED)
	public void savePa(ResultFifPa rfPa) {
		rfPaDao.delfPa(rfPa);
		rfPaDao.saveResutFifthPa(rfPa);
	}
	
	/**
	 * 查询计算历史
	 * @param resultFormBean
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public  PageResults getFif(ResultFifth rf){
		ResultFifthFormBean resultFormBean=new ResultFifthFormBean();
		resultFormBean.setResultFifthFormBean(rf);
		return rfDao.getFif(resultFormBean);
	}
}
