package com.lyht.business.analysisCalculation.service;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.analysisCalculation.bean.HresultFourth;
import com.lyht.business.analysisCalculation.bean.HresultFourthe;
import com.lyht.business.analysisCalculation.bean.HresultFourthq;
import com.lyht.business.analysisCalculation.bean.ResultSecond;
import com.lyht.business.analysisCalculation.dao.HresultFourthDao;
import com.lyht.business.analysisCalculation.dao.HresultFourthZhcxDao;
import com.lyht.business.analysisCalculation.dao.HresultFourtheDao;
import com.lyht.business.analysisCalculation.dao.HresultFourthqDao;
import com.lyht.business.analysisCalculation.dao.HresultSecondZhcxDao;
import com.lyht.business.analysisCalculation.dao.HresultThirdZhcxDao;
import com.lyht.business.analysisCalculation.dao.HresultZhcxDao;
import com.lyht.business.analysisCalculation.dao.ResultFourthDao;
import com.lyht.business.analysisCalculation.dao.ResultSecondDao;
import com.lyht.business.analysisCalculation.dao.ResultSixthResultDao;
import com.lyht.business.analysisCalculation.dao.ResultThridDao;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Scope("prototype")
@Transactional
public class HresultService {

	@Resource HresultFourthqDao hresultFourthqDao;
	@Resource HresultFourtheDao hresultFourtheDao;
	@Resource HresultFourthDao hresultFourthDao;
	@Resource ResultFourthDao resultFourthDao;
	@Resource HresultZhcxDao hresultZhcxDao;
	@Resource HresultSecondZhcxDao hresultSecondZhcxDao;
	@Resource HresultThirdZhcxDao hresultThirdZhcxDao;
	@Resource HresultFourthZhcxDao hresultFourthZhcxDao;
	@Resource ResultSecondDao resultSecondDao;
	@Resource ResultThridDao resultThridDao;
	@Resource 
	private ResultSixthResultDao resultSixthResultDao;
	
	/**
	 * 删除整场洪水信息
	 * @param stcd
	 * @param params
	 */
	@Transactional
	public void deleteHuiliuDataResultByStep(String stcd,String step,String params){
		JSONArray dataList = JSONArray.fromObject(params);
		String pchs="";
		if(dataList!=null && dataList.size()>0){
			for(int i=0;i<dataList.size();i++){
				JSONObject json = dataList.getJSONObject(i);
				if(json.containsKey("PCH")){
					if(pchs!=null && pchs.trim().length()>0){
						pchs=pchs+",'"+json.getString("PCH")+"'";
					}else{
						pchs="'"+json.getString("PCH")+"'";
					}
				}
			}
		}
		if(pchs!=null && pchs.trim().length()>0){
			String[] tableList=null;
			if(CommonUtil.trim(step).equals("step1")){
				tableList = new String[]{"H_RESULT","H_RESULT_ZHCX"};
			}else if(CommonUtil.trim(step).equals("step2")){
				tableList = new String[]{"H_RESULT_SECOND","H_RESULT_SECOND_ZHCX"};
			}else if(CommonUtil.trim(step).equals("step3")){
				tableList = new String[]{"H_RESULT_THIRD_ZHCX","H_RESULT_THRID"};
			}else if(CommonUtil.trim(step).equals("step4")){
				tableList = new String[]{"H_RESULT_FOURTH", "H_RESULT_FOURTH_E","H_RESULT_FOURTH_Q",
						"H_RESULT_FOURTH_ZHCX"};
			}
			if(tableList!=null && tableList.length>0){
				for(int i=0;i<tableList.length;i++){
					resultSixthResultDao.deleteTableData(tableList[i], stcd, pchs);
				}
			}
		}
	}
	public List<Map> queryHuiliuStep5Table1(String stcd,String pch){
		return hresultZhcxDao.queryStep5Table1Data(stcd, pch);
	}
	public List<Map> queryHuiliuStep5Table2(String stcd,String pch){
		return hresultSecondZhcxDao.queryStep5Table2Data(stcd, pch);
	}
	public List<Map> queryHuiliuStep5Table3(String stcd,String pch){
		return hresultThirdZhcxDao.queryStep5Table3Data(stcd, pch);
	}
	public List<Map> queryHuiliuStep5Table4(String stcd,String pch){
		return hresultFourthZhcxDao.queryStep5Table4Data(stcd, pch);
	}
	public Hashtable<String,Object> calcHuiliuStep4Table1AndSave(String data){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			JSONObject dataJson=JSONObject.fromObject(data);
			double I1=dataJson.getDouble("I1");
			double I2=dataJson.getDouble("I2");
			String stcd=dataJson.getString("stcd");
			String pch=dataJson.getString("pch");
			JSONArray table1Data=dataJson.getJSONArray("table1Data");
			this.hresultFourtheDao.deleteHresultFortheByStcdAndPch(stcd, pch);
			if(table1Data!=null && table1Data.size()>0){
				for(int i=0;i<table1Data.size();i++){
					HresultFourthe ebean=new HresultFourthe();
					JSONObject td1=table1Data.getJSONObject(i);
					String dt=td1.getString("DT");
					dt=dt.replace("-", "");
					dt=dt.replace(" ", "");
					dt=dt.replace(":", "");
					String nm=stcd+pch+dt;
					ebean.setNm(nm);
					ebean.setStcd(stcd);
					ebean.setPch(pch);
					ebean.setTm(td1.getString("DT"));
					double ii=td1.getDouble("II");
					ebean.setIi(ii);
					double tmi=td1.getDouble("MI");
					ebean.setMi(tmi);
					td1.put("MI", tmi);
					double mi2=td1.getDouble("MI2");
					td1.put("MI2", mi2);
					ebean.setMi2(mi2);
					double iimi=td1.getDouble("IIMI");
					td1.put("IIMI", iimi);
					ebean.setIimi(iimi);
					double iimi2=td1.getDouble("IIMI2");
					td1.put("IIMI2", iimi2);
					ebean.setIimi2(iimi2);
					this.hresultFourtheDao.save(ebean);
				}
			}
			ResultSecond second = new ResultSecond();
			second.setI1(I1);
			second.setI2(I2);
			second.setSTCD(stcd);
			second.setPch(pch);
			List<Map> relist=resultSecondDao.querySecondByStcdAndPch(stcd, pch);
			if(relist!=null && relist.size()>0){
				resultSecondDao.updateI1AndI2(second);
			}else{
				resultSecondDao.saveResultSecond(second);
			}
			table.put("reflag", "1");
			table.put("elist", table1Data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "计算保存出错!");
		}
		return table;
	}
	public Hashtable<String,Object> calcHuiliuStep4Table2AndSave(String data){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			JSONObject dataJson=JSONObject.fromObject(data);
			double Q1=dataJson.getDouble("Q1");
			double Q2=dataJson.getDouble("Q2");
			String stcd=dataJson.getString("stcd");
			String pch=dataJson.getString("pch");
			JSONArray table2Data=dataJson.getJSONArray("table2Data");
			this.hresultFourthqDao.deleteHresultFourthqByStcdAndPch(stcd, pch);
			if(table2Data!=null && table2Data.size()>0){
				for(int i=0;i<table2Data.size();i++){
					HresultFourthq qbean=new HresultFourthq();
					JSONObject td2=table2Data.getJSONObject(i);
					String dt=td2.getString("DT");
					dt=dt.replace("-", "");
					dt=dt.replace(" ", "");
					dt=dt.replace(":", "");
					String nm=stcd+pch+dt;
					qbean.setNm(nm);
					qbean.setStcd(stcd);
					qbean.setPch(pch);
					qbean.setTm(td2.getString("DT"));
					double qs=td2.getDouble("QS");
					qbean.setQs(qs);
					double qj=td2.getDouble("QJ");
					qbean.setQj(qj);
					double qsqj=td2.getDouble("QSQJ");
					td2.put("QSQJ", qsqj);
					qbean.setQsqj(qsqj);
					double tqpj=td2.getDouble("QPJ");
					td2.put("QPJ", tqpj);
					qbean.setQpj(tqpj);
					double tmi=td2.getDouble("MI");
					td2.put("MI", tmi);
					qbean.setMi(tmi);
					double mi2=td2.getDouble("MI2");
					td2.put("MI2", mi2);
					qbean.setMi2(mi2);
					double miqpj=td2.getDouble("MIQPJ");
					td2.put("MIQPJ", miqpj);
					qbean.setMiqpj(miqpj);
					double mi2qi=td2.getDouble("MI2QI");
					td2.put("MI2QI", mi2qi);
					qbean.setMi2qi(mi2qi);
					this.hresultFourthqDao.save(qbean);
				}
			}
			ResultSecond second = new ResultSecond();
			second.setQ1(Q1);
			second.setQ2(Q2);
			second.setSTCD(stcd);
			second.setPch(pch);
			List<Map> relist=resultSecondDao.querySecondByStcdAndPch(stcd, pch);
			if(relist!=null && relist.size()>0){
				resultSecondDao.updateQ1AndQ2(second);
			}else{
				resultSecondDao.saveResultSecond(second);
			}
			table.put("reflag", "1");
			table.put("qlist", table2Data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "计算保存出错!");
		}
		return table;
	}
	public Hashtable<String,Object> calcHuiliuStep4Table3AndSave(String data){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			JSONObject dataJson=JSONObject.fromObject(data);
			HresultFourth fourBean = (HresultFourth)JSONObject.toBean(dataJson, HresultFourth.class);
			if(fourBean!=null){
				fourBean.setNm(fourBean.getStcd()+fourBean.getPch());
				double k=fourBean.getK().doubleValue();
				double n=fourBean.getN().doubleValue();
				double m1=k*n;
				m1=new BigDecimal(m1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
				fourBean.setM1(m1);
				hresultFourthDao.saveOrUpdate(fourBean);
			}else{
				fourBean = new HresultFourth();
			}
			table.put("reflag", "1");
			table.put("fourBean", fourBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "计算保存出错!");
		}
		return table;
	}
	public Hashtable<String,Object> calcHuiliuStep4AndSave(String data){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		try {
			JSONObject dataJson=JSONObject.fromObject(data);
			double k=dataJson.getDouble("k");
			double n=dataJson.getDouble("n");
			double m=dataJson.getDouble("m");
			double mi=dataJson.getDouble("mi");
			double qpj=dataJson.getDouble("qpj");
			double qi=dataJson.getDouble("qi");
			double a=dataJson.getDouble("a");
			double am=dataJson.getDouble("am");
			double kj=dataJson.getDouble("kj");
			double m1=dataJson.getDouble("m1");
			double mj=dataJson.getDouble("mj");
			
			String stcd=dataJson.getString("stcd");
			String pch=dataJson.getString("pch");
			JSONArray table1Data=dataJson.getJSONArray("table1Data");
			JSONArray table2Data=dataJson.getJSONArray("table2Data");
			this.hresultFourtheDao.deleteHresultFortheByStcdAndPch(stcd, pch);
			this.hresultFourthqDao.deleteHresultFourthqByStcdAndPch(stcd, pch);
			this.hresultFourthDao.deleteHresultFourthqByStcdAndPch(stcd, pch);
			if(table1Data!=null && table1Data.size()>0){
				for(int i=0;i<table1Data.size();i++){
					HresultFourthe ebean=new HresultFourthe();
					JSONObject td1=table1Data.getJSONObject(i);
					String dt=td1.getString("DT");
					dt=dt.replace("-", "");
					dt=dt.replace(" ", "");
					dt=dt.replace(":", "");
					String nm=stcd+pch+dt;
					ebean.setNm(nm);
					ebean.setStcd(stcd);
					ebean.setPch(pch);
					ebean.setTm(td1.getString("DT"));
					double ii=td1.getDouble("II");
					ebean.setIi(ii);
					double tmi=mi;
					ebean.setMi(tmi);
					td1.put("MI", tmi);
					double mi2=tmi*tmi;
					mi2=new BigDecimal(mi2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td1.put("MI2", mi2);
					ebean.setMi2(mi2);
					double iimi=ii*tmi;
					iimi=new BigDecimal(iimi).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td1.put("IIMI", iimi);
					ebean.setIimi(iimi);
					double iimi2=ii*tmi*tmi;
					iimi2=new BigDecimal(iimi2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td1.put("IIMI2", iimi2);
					ebean.setIimi2(iimi2);
					this.hresultFourtheDao.save(ebean);
				}
			}
			if(table2Data!=null && table2Data.size()>0){
				for(int i=0;i<table2Data.size();i++){
					HresultFourthq qbean=new HresultFourthq();
					JSONObject td2=table2Data.getJSONObject(i);
					String dt=td2.getString("DT");
					dt=dt.replace("-", "");
					dt=dt.replace(" ", "");
					dt=dt.replace(":", "");
					String nm=stcd+pch+dt;
					qbean.setNm(nm);
					qbean.setStcd(stcd);
					qbean.setPch(pch);
					qbean.setTm(td2.getString("DT"));
					double qs=td2.getDouble("QS");
					qbean.setQs(qs);
					double qj=td2.getDouble("QJ");
					qbean.setQj(qj);
					double qsqj=qs-qj;
					qsqj=new BigDecimal(qsqj).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td2.put("QSQJ", qsqj);
					qbean.setQsqj(qsqj);
					double tqpj=qpj;
					tqpj=new BigDecimal(tqpj).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td2.put("QPJ", tqpj);
					qbean.setQpj(tqpj);
					double tmi=mi;
					tmi=new BigDecimal(tmi).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td2.put("MI", tmi);
					qbean.setMi(tmi);
					double mi2=tmi*tmi;
					mi2=new BigDecimal(mi2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td2.put("MI2", mi2);
					qbean.setMi2(mi2);
					double miqpj=tmi*qpj;
					miqpj=new BigDecimal(miqpj).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td2.put("MIQPJ", miqpj);
					qbean.setMiqpj(miqpj);
					double mi2qi=tmi*tmi*qi;
					mi2qi=new BigDecimal(mi2qi).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
					td2.put("MI2QI", mi2qi);
					qbean.setMi2qi(mi2qi);
					this.hresultFourthqDao.save(qbean);
				}
			}
			HresultFourth rf=new HresultFourth();
			rf.setNm(stcd+pch);
			rf.setK(k);
			rf.setM1(m1);
			rf.setN(n);
			rf.setStcd(stcd);
			rf.setPch(pch);
			this.hresultFourthDao.save(rf);
			table.put("reflag", "1");
			table.put("elist", table1Data);
			table.put("qlist", table2Data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			table.put("reflag", "0");
			table.put("message", "计算保存出错!");
		}
		return table;
	}
	public void deleteHuiliuStep4Table2Data(String stcd,String pch){
		this.hresultFourthqDao.deleteHresultFourthqByStcdAndPch(stcd, pch);;
	}
	public void deleteHuiliuStep4Table1Data(String stcd,String pch){
		this.hresultFourtheDao.deleteHresultFortheByStcdAndPch(stcd, pch);
	}
	private double calcA13m(double maxRt,List<Map> rtlist,int interval){
		double a13m=0;
		int count=0;
		if(rtlist!=null && rtlist.size()>0){
			for(int i=0;i<rtlist.size();i++){
				Map map = rtlist.get(i);
				if(map!=null && map.get("Rt")!=null){
					double rt=Double.valueOf(map.get("Rt").toString()).doubleValue();
					if(rt>maxRt/3){
						a13m=a13m+rt;
						count++;
					}
				}
			}
		}
		if(count>0){
			a13m=a13m/count;
		}
		if(interval>0){
			a13m=a13m/(Double.valueOf(interval)/60);
		}
		a13m=new BigDecimal(a13m).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return a13m;
	}
	private double calcChanliuStep3Rs(String stcd,String pch,double lymj){
		double sumqt=this.resultThridDao.queryStep3SumQt(stcd, pch);
		if(lymj!=0){
			double rs=sumqt/lymj/1000;
			rs=new BigDecimal(rs).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return rs;
		}
		return 0;
	}
	private double calcChanliuStep4Rx(String stcd,String pch,double lymj){
		double sumqt=this.resultFourthDao.queryChanliuStep4SumQt(stcd, pch);
		if(lymj!=0){
			double rx=sumqt/lymj/1000;
			rx=new BigDecimal(rx).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return rx;
		}
		return 0;
	}
	public Hashtable<String,Object> deleteHuiliuStep4Table4Data(String stcd,String pch){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		hresultFourthDao.deleteHresultFourthqByStcdAndPch(stcd, pch);
		double maxRt=this.resultFourthDao.queryMaxRt(stcd, pch);
		List<Map> table1Data=this.resultFourthDao.getHuiliuStep2Table1(stcd, pch);
		if(table1Data!=null && table1Data.size()>0){
			Map lymjMap = this.resultFourthDao.getHuiLiuStep1Lymj(stcd, pch);
			double lymj = lymjMap!=null && lymjMap.get("LYMJ")!=null?CommonUtil.getFloatValue(lymjMap.get("LYMJ").toString()):0d;
			double rs=calcChanliuStep3Rs(stcd,pch,lymj);
			double rx=calcChanliuStep4Rx(stcd,pch,lymj);
			for(int i=0;i<table1Data.size();i++){
				Map map = table1Data.get(i);
				if(map!=null && map.get("Rt")!=null){
					double rt = CommonUtil.getFloatValue(map.get("Rt").toString());
					rt=rt-(1-(rs-rx)/rs)*rt;
					rt=new BigDecimal(rt).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
					map.put("Rt", rt);
				}
			}
		}
		List<Map> fourthQList = hresultFourthqDao.queryHresultForthqByStcdAndPch(stcd, pch);
		double maxQs=0;
		if(fourthQList==null || fourthQList.size()<1){
			fourthQList=hresultFourthqDao.queryCresultStep3Result(stcd, pch);
			maxQs=hresultFourthqDao.maxYyQs(stcd, pch);
		}else{
			maxQs=hresultFourthqDao.maxJsQs(stcd, pch);
		}
		Map jgInfo = resultFourthDao.queryChanliuIntervalData(stcd, pch);
		int interval = jgInfo!=null && jgInfo.get("SJJG")!=null?Integer.valueOf(jgInfo.get("SJJG").toString()):0;
		double a13m=calcA13m(maxRt,table1Data,interval);
		HresultFourth resultBean=new HresultFourth();
		resultBean.setPch(pch);
		resultBean.setStcd(stcd);
		a13m=new BigDecimal(a13m).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
		resultBean.setAm13(a13m);
		resultBean.setQms(maxQs);
		table.put("fourBean", resultBean);
		table.put("reflag", "1");
		return table;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Hashtable queryHuiliuStep4InitData(String stcd,String pch){
		Hashtable table = new Hashtable();
		List<Map> fourthEList = hresultFourtheDao.queryHresultFortheByStcdAndPch(stcd, pch);
		if(fourthEList==null || fourthEList.size()<1){
			fourthEList=hresultFourtheDao.queryCresultStep6Result(stcd, pch);
			if(fourthEList!=null && fourthEList.size()>0){
				Map lymjMap = this.resultFourthDao.getHuiLiuStep1Lymj(stcd, pch);
				double lymj = lymjMap!=null && lymjMap.get("LYMJ")!=null?CommonUtil.getFloatValue(lymjMap.get("LYMJ").toString()):0d;
				double rs=calcChanliuStep3Rs(stcd,pch,lymj);
				double rx=calcChanliuStep4Rx(stcd,pch,lymj);
				for(int i=0;i<fourthEList.size();i++){
					Map map = fourthEList.get(i);
					if(map!=null && map.get("II")!=null){
						double rt = CommonUtil.getFloatValue(map.get("II").toString());
						rt=rt-(1-(rs-rx)/rs)*rt;
						rt=new BigDecimal(rt).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
						map.put("II", rt);
					}
				}
			}
		}
		table.put("elist", fourthEList);
		List<Map> fourthQList = hresultFourthqDao.queryHresultForthqByStcdAndPch(stcd, pch);
		double maxQs=0;
		if(fourthQList==null || fourthQList.size()<1){
			fourthQList=hresultFourthqDao.queryCresultStep3Result(stcd, pch);
			maxQs=hresultFourthqDao.maxYyQs(stcd, pch);
		}else{
			maxQs=hresultFourthqDao.maxJsQs(stcd, pch);
		}
		table.put("qlist", fourthQList);
		
		List<Map> table1Data=this.resultFourthDao.getHuiliuStep2Table1(stcd, pch);
		if(table1Data!=null && table1Data.size()>0){
			Map lymjMap = this.resultFourthDao.getHuiLiuStep1Lymj(stcd, pch);
			double lymj = lymjMap!=null && lymjMap.get("LYMJ")!=null?CommonUtil.getFloatValue(lymjMap.get("LYMJ").toString()):0d;
			double rs=calcChanliuStep3Rs(stcd,pch,lymj);
			double rx=calcChanliuStep4Rx(stcd,pch,lymj);
			for(int i=0;i<table1Data.size();i++){
				Map map = table1Data.get(i);
				if(map!=null && map.get("Rt")!=null){
					double rt = CommonUtil.getFloatValue(map.get("Rt").toString());
					rt=rt-(1-(rs-rx)/rs)*rt;
					rt=new BigDecimal(rt).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
					map.put("Rt", rt);
				}
			}
		}
		table.put("rtlist", table1Data);
		double maxRt=this.resultFourthDao.queryMaxRt(stcd, pch);
		Map jgInfo = resultFourthDao.queryChanliuIntervalData(stcd, pch);
		table.put("timeJg", jgInfo);
		int interval = jgInfo!=null && jgInfo.get("SJJG")!=null?Integer.valueOf(jgInfo.get("SJJG").toString()):0;
		double a13m=calcA13m(maxRt,table1Data,interval);
		HresultFourth resultBean=this.hresultFourthDao.queryHresultFourthByStcdAndPch(stcd,pch);
		if(resultBean==null){
			resultBean=new HresultFourth();
			resultBean.setPch(pch);
			resultBean.setStcd(stcd);
			a13m=new BigDecimal(a13m).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留2位小数
			resultBean.setAm13(a13m);
			resultBean.setQms(maxQs);
		}
		table.put("fourBean", resultBean);
		Map lyInfo = resultFourthDao.queryChanliuStep2Data(stcd, pch);
		table.put("second", lyInfo);
		return table;
	}
}
