package com.lyht.business.analysisCalculation.service;

import java.math.BigDecimal;
import java.util.Hashtable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.analysisCalculation.bean.ResultSecond;
import com.lyht.business.analysisCalculation.bean.ResultSixPpa;
import com.lyht.business.analysisCalculation.bean.ResultSixth;
import com.lyht.business.analysisCalculation.bean.ResultSixthResult;
import com.lyht.business.analysisCalculation.dao.ResultSecondDao;
import com.lyht.business.analysisCalculation.dao.ResultSixPpaDao;
import com.lyht.business.analysisCalculation.dao.ResultSixthDao;
import com.lyht.business.analysisCalculation.dao.ResultSixthResultDao;
import com.lyht.util.Randomizer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Scope("prototype")
@Transactional
public class ResultSixthService {
	@Resource
	private ResultSixthDao rsDao;
	@Resource
	private ResultSecondDao secondDao;
	@Resource ResultSixthResultDao resultSixthResultDao;
	@Resource ResultSixPpaDao resultSixPpaDao;
	
	public void saveResultSixth(ResultSixth rs) {
		rsDao.delResultSixth(rs);
		rsDao.saveResultSixth(rs);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public Hashtable<String,Object> saveStep6ResultData(String stcd,String pch,String data){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			JSONObject dataJson = JSONObject.fromObject(data);
			JSONArray array= dataJson.getJSONArray("tableData");
			JSONArray echartData = dataJson.getJSONArray("echartData");
			JSONObject fcJson=dataJson.getJSONObject("fcjg");
			Double im = dataJson.getDouble("Im");
			Double b=dataJson.getDouble("b");
			String zstcd=dataJson.getString("zfzstcd");
			Double lymj=dataJson.getDouble("lymj");
	        Double rs=dataJson.getDouble("rs");
	        Double rx=dataJson.getDouble("rx");
	        Double inpa=dataJson.getDouble("pa");
	        //保存输入参数信息
	        ResultSecond second = secondDao.queryResultSecondByStcdAndPch(stcd,pch);
	        if(second==null){
	        	second=new ResultSecond();
	        	second.setId(Randomizer.generCodeWidthDate(32));
	        }
	        second.setSTCD(stcd);
        	second.setPch(pch);
        	second.setRs(rs);
        	second.setRx(rx);
        	second.setB(b);
        	second.setPa(inpa);
        	second.setZstcd(zstcd);
        	second.setIm(im);
        	second.setLLMJ(lymj);
        	secondDao.saveOrUpdate(second);
        	
			rsDao.deleteByStcdAndPch(stcd, pch);
			if(array!=null && array.size()>0){
				for(int i=0;i<array.size();i++) {
					 JSONObject jObject=array.getJSONObject(i);
					 ResultSixth  rSixth=new ResultSixth();
					 rSixth.setTm(jObject.getString("DATE"));
					 rSixth.setEr(jObject.getDouble("ER"));
					 rSixth.setE(jObject.getDouble("E"));
					 rSixth.setEpe(jObject.getDouble("EPE"));
					 rSixth.setErc(jObject.getDouble("ERC"));
					 rSixth.setP(jObject.getDouble("P"));
					 rSixth.setPape(jObject.getDouble("PAPE"));
					 rSixth.setPe(jObject.getDouble("PE"));
					 rSixth.setR(jObject.getDouble("R"));
					 rSixth.setRgdx(jObject.getDouble("RGDX"));
					 rSixth.setSdrc(jObject.getDouble("SDRC"));
					 rSixth.setStcd(stcd);
					 rSixth.setPch(pch);
					 rsDao.saveResultSixth(rSixth);
				 }
			}
			rsDao.deleteResultSixPpaByStcdAndPch(stcd, pch);
			if(echartData!=null && echartData.size()>0){
				for(int j=0;j<echartData.size();j++){
					JSONObject lineJson = echartData.getJSONObject(j);
					if(lineJson!=null){
						JSONArray lineData = lineJson.getJSONArray("RData");
						Double pa = lineJson.getDouble("Pa");
						if(lineData!=null && lineData.size()>0){
							for(int k=0;k<lineData.size();k++){
								JSONObject pjson = lineData.getJSONObject(k);
								if(pjson!=null){
									Double r = pjson.getDouble("R");
									Double ppa=pjson.getDouble("P+Pa");
									if(r!=null && ppa!=null){
										ResultSixPpa rsppa = new ResultSixPpa();
										String nm = Randomizer.generCodeWidthDate(32);
										rsppa.setNm(nm);
										rsppa.setStcd(stcd);
										rsppa.setPch(pch);
										rsppa.setPa(pa);
										rsppa.setR(r);
										rsppa.setPpa(ppa);
										resultSixPpaDao.saveOrUpdate(rsppa);
									}
								}
							}
						}
					}
				}
			}
			if(fcJson.containsKey("FCS") && fcJson.containsKey("FCJ")
					&& fcJson.containsKey("FC") && fcJson.containsKey("TC")){
				ResultSixthResult sixResult = new ResultSixthResult();
				sixResult.setNm(stcd+pch);
				sixResult.setStcd(stcd);
				sixResult.setPch(pch);
				double rc=fcJson.getDouble("RC");
				double tc=fcJson.getDouble("TC");
				sixResult.setRc(rc);
				sixResult.setTc(tc);
				double rctc=tc!=0?rc/tc:0;
				rctc = new BigDecimal(rctc).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				sixResult.setRctc(rctc);
				sixResult.setFc(fcJson.getDouble("FC"));
				resultSixthResultDao.saveOrUpdate(sixResult);
			}
			table.put("reflag", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "保存失败!");
		}
		return table;
	}
}
