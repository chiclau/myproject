package com.lyht.business.environment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.environment.dao.EnvironmentDao;

/**
 * 环保统计
 * @author 刘魁
 *@创建时间 2018/10/10
 */
@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("all")
public class EnvironmentService {
	@Resource
	private EnvironmentDao enDao;
	
	/**
	 * 环保统计图表切换功能
	 */
	public List<Map> echartsAndTable(String tableId, String arr) {
		return enDao.echartsAndTable(tableId,arr);
	}
	
	public HashMap<String, Object> queryHuanBao(String xzqhdm){
		HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("sfsjzrbhq",enDao.queryHuanBao3(xzqhdm)); // 是否涉及自然保护区
			map.put("sjzrbhqqk",enDao.sjzrbhqqk(xzqhdm)); // 涉及自然保护区情况 核心区，试验区，缓冲区
			map.put("kzxmhpqk",enDao.kzxmhpqkS(xzqhdm)); // 开展项目环评情况统计
			map.put("tcqwcspqk",enDao.tcqWanCheng(xzqhdm)); // 投产前完成环评审批情况统计
			map.put("llxfTotal", enDao.llxfTotal(xzqhdm)); // 生态环境流量泄放设施统计
			map.put("lljkTotal", enDao.lljkTotal(xzqhdm)); // 生态流量监控措施统计
			map.put("gycsTotal", enDao.gycsTotal(xzqhdm)); // 过鱼设施统计
			map.put("zzlfTotal", enDao.zzlfTotal(xzqhdm)); // 增殖放流措施统计
			map.put("fhghTotal", enDao.fhghTotal(xzqhdm)); // 是否符合规划
			map.put("kzxmhpqkTotal", enDao.kzxmhpqkTotal(xzqhdm)); // 是否开展项目环评
			map.put("sfyhpspTotal", enDao.sfyhpspTotal(xzqhdm)); // 是否有环评审批
			map.put("ghhpTotal", enDao.ghhpTotal(xzqhdm)); // 是否符合规划环评
			map.put("hbysTotal", enDao.hbysTotal(xzqhdm)); // 是否通过竣工环保验收
			map.put("tsgkTotal", enDao.tsgkTotal(xzqhdm)); // 是否存在坝下脱水干涸情况
		return map;
	}
	public HashMap<String, Object> queryHuanBao1(String sheng,String shi,String xian){
		HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("sfsjzrbhq",enDao.queryHuanBao3_1(sheng,shi,xian)); // 是否涉及自然保护区
			map.put("sjzrbhqqk",enDao.sjzrbhqqk_1(sheng,shi,xian)); // 涉及自然保护区情况 核心区，试验区，缓冲区
			map.put("kzxmhpqk",enDao.kzxmhpqkS_1(sheng,shi,xian)); // 开展项目环评情况统计
			map.put("tcqwcspqk",enDao.tcqWanCheng_1(sheng,shi,xian)); // 投产前完成环评审批情况统计
			map.put("llxfTotal", enDao.llxfTotal_1(sheng,shi,xian)); // 生态环境流量泄放设施统计
			map.put("lljkTotal", enDao.lljkTotal_1(sheng,shi,xian)); // 生态流量监控措施统计
			map.put("gycsTotal", enDao.gycsTotal_1(sheng,shi,xian)); // 过鱼设施统计
			map.put("zzlfTotal", enDao.zzlfTotal_1(sheng,shi,xian)); // 增殖放流措施统计
			map.put("fhghTotal", enDao.fhghTotal(sheng,shi,xian)); // 是否符合规划
			map.put("kzxmhpqkTotal", enDao.kzxmhpqkTotal(sheng,shi,xian)); // 是否开展项目环评
			map.put("sfyhpspTotal", enDao.sfyhpspTotal(sheng,shi,xian)); // 是否有环评审批
			map.put("ghhpTotal", enDao.ghhpTotal(sheng,shi,xian)); // 是否符合规划环评
			map.put("hbysTotal", enDao.hbysTotal(sheng,shi,xian)); // 是否通过竣工环保验收
			map.put("tsgkTotal", enDao.tsgkTotal(sheng,shi,xian)); // 是否存在坝下脱水干涸情况
			return map;
	}
	
	//环保措施情况统计
	public HashMap<String, Object> hbcsqktj(String xzqhdm){
		HashMap<String, Object> map=new HashMap<String, Object>();
		List  listy=new ArrayList();
		List  listMax=new ArrayList();
		HashMap mapo=new  HashMap<>();
		Iterator it =  enDao.hbcstjYou(xzqhdm).get(0).entrySet().iterator();
		  while (it.hasNext()) {
		   Map.Entry entry = (Map.Entry) it.next();
		   Object key = entry.getKey();
		   Object value = entry.getValue();
		   if(key.equals("zuida")) {
			   listMax.add(value);
		   }else {
			   listy.add(value);
		   }
		  }
		  mapo.put("zuida", listMax);
		  mapo.put("y", listy);
		map.put("hbcsqktjYou", mapo);
		//无
		List  listw=new ArrayList();
		List  listMax1=new ArrayList();
		HashMap mapo1=new  HashMap<>();
		Iterator it1 =  enDao.hbcstjWu(xzqhdm).get(0).entrySet().iterator();
		  while (it1.hasNext()) {
		   Map.Entry entry = (Map.Entry) it1.next();
		   Object key = entry.getKey();
		   Object value = entry.getValue();
		   if(key.equals("zuida")) {
			   listMax1.add(value);
		   }else {
			   listw.add(value);
		   }
		  }
		  mapo1.put("zuida", listMax1);
		  mapo1.put("w", listw);
		map.put("hbcsqktjWu", mapo1);
		return map;
	}
	
}
