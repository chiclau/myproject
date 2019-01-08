package com.lyht.business.baseinfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.baseinfo.dao.BaseInfoDao;

/**
 * 基础信息	
 * @author 刘魁
 *@创建时间 2018/10/10
 */

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("all")
public class BaseInfoService {

	@Resource
	private BaseInfoDao baseInfoDao;
	public List<Map> countDz(String address, String bj) {
		return baseInfoDao.countDz(address,bj);
	}
	
	//切换表格
	public List<Map> countTbzh(String bj, String address) {
		return baseInfoDao.countTbzh(bj,address);
	}
	
	
	
	public HashMap< String, Object> sfyxmhz(String xzqhdm) {
		HashMap< String, Object> map=new HashMap< String, Object>();
		List <Map> list1= baseInfoDao.sfyxmhz1(xzqhdm);
		List <Map> list2=baseInfoDao.sfyxmhz2(xzqhdm);
		map.put("sfyxmhz1", list1);
		map.put("sfyxmhz2", list2);
		return map;
	}
	public HashMap< String, Object> bwqk(String xzqhdm) {
		HashMap< String, Object> map=new HashMap< String, Object>();
		List <Map> list1= baseInfoDao.bwqk1(xzqhdm);
		List <Map> list2=baseInfoDao.bwqk2(xzqhdm);
		List  list3=new ArrayList();
		List  list4=new ArrayList();
		for(int i=0;i<list1.size();i++) {
			list3.add(list1.get(i).get("value")) ;
			list4.add(list2.get(i).get("value"));
		}
		map.put("bwqk1", list1);
		map.put("bwqk2",list2);
		map.put("bwqk1_sum", list3);
		map.put("bwqk2_sum", list4);
		return map;
	}
	
	
	public HashMap< String, Object> zjly(String xzqhdm) {
		HashMap< String, Object> map=new HashMap< String, Object>();
		List <Map> list1= baseInfoDao.zjyl_count(xzqhdm);
		List <Map> list2=baseInfoDao.zjyl_sum(xzqhdm);
		List  list3=new ArrayList();
		List  list4=new ArrayList();
		for(int i=0;i<list1.size();i++) {
			list3.add(list1.get(i).get("value")) ;
			list4.add(list2.get(i).get("value"));
		}
		map.put("zjly1",baseInfoDao.tzxz(xzqhdm));
		map.put("zjly2", baseInfoDao.tzxz2(xzqhdm));
		map.put("zjly1_sum", list3);
		map.put("zjly2_sum", list4);
		return map;
	}
	

	
	public List<Map> countJszt(String address, String bj) {
		return baseInfoDao.countJszt(address,bj);
	}
	
	public List<Map> countJszt(String sheng,String shi,String xian) {
		return baseInfoDao.countJszt(sheng,shi,xian);
	}
	
	public List<Map> countKffs(String address, String bj) {
		return baseInfoDao.countKffs(address,bj);
	}
	public List<Map> countKffs(String sheng,String shi,String xian) {
		return baseInfoDao.countKffs(sheng,shi,xian);
	}
	
	public HashMap< String, Object> jcxx2(String xzqhdm){
		HashMap< String, Object> map=new HashMap< String, Object>();
		map.put("jcxx2_1", baseInfoDao.xmszly(xzqhdm));//项目所在流域电站
		map.put("jcxx2_2", baseInfoDao.xmszly2(xzqhdm));//项目所在流域桩基规模
		return map;
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getDzListData(String bj, String name, String sheng,String shi,String xian, int page, int limit,int tab,String seriesName){
		return baseInfoDao.getDzListData(bj, name, sheng,shi,xian, page, limit,tab, seriesName);
	}
	


	/**
	 * 基础查询页面根据用户点击Echarts统计不同数据
	 * @param xzqhdm
	 * @param name
	 * @param bj
	 * @return
	 */
	public List<Map> countSdzByTj(String xzqhdm, String name, String bj) {
		return this.baseInfoDao.countSdzByTj(xzqhdm,name,bj);
	}
	
	
	/**
	 * 查询
	 * @return
	 */
	public HashMap<String, Object> queryBaseInfo(String xzqhdm){
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("sjnfdl", sjnfdl(xzqhdm));//设计年发电量
		map.put("jcxx1", baseInfoDao.zjlxzc(xzqhdm));//装机类型组成
		map.put("jcxx2", baseInfoDao.zjlxzc2(xzqhdm));//装机类型组成
		map.put("szly", jcxx2(xzqhdm));//所在流域
		map.put("zjly", this.zjly(xzqhdm));//资金来源
		map.put("bwqk", this.bwqk(xzqhdm));//并网情况
		map.put("sfyxmhzqk", this.sfyxmhz(xzqhdm));//是否有项目核准
		return map;
	}
	public HashMap<String, Object> queryBaseInfo1(String sheng,String shi,String xian ){
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("sjnfdl", sjnfdl_1(sheng,shi,xian));//设计年发电量
		map.put("jcxx1", baseInfoDao.zjlxzc_1(sheng,shi,xian));//装机类型组成
	map.put("jcxx2", baseInfoDao.zjlxzc2_1(sheng,shi,xian));//装机类型组成
	map.put("szly", jcxx2_1(sheng,shi,xian));//所在流域
	map.put("zjly", this.zjly_1(sheng,shi,xian));//资金来源
		map.put("bwqk", this.bwqk_1(sheng,shi,xian));//并网情况
		map.put("sfyxmhzqk", this.sfyxmhz_1(sheng,shi,xian));//是否有项目核准
		return map;
	}
	
	public HashMap< String, Object> sfyxmhz_1(String sheng,String shi,String xian) {
		HashMap< String, Object> map=new HashMap< String, Object>();
		List <Map> list1= baseInfoDao.sfyxmhz1_1(sheng,shi,xian);
		List <Map> list2=baseInfoDao.sfyxmhz2_1(sheng,shi,xian);
		map.put("sfyxmhz1", list1);
		map.put("sfyxmhz2", list2);
		return map;
	}
	
	public HashMap< String, Object> bwqk_1(String sheng,String shi,String xian) {
		HashMap< String, Object> map=new HashMap< String, Object>();
		List <Map> list1= baseInfoDao.bwqk1_1(sheng,shi,xian);
		List <Map> list2=baseInfoDao.bwqk2_1(sheng,shi,xian);
		List  list3=new ArrayList();
		List  list4=new ArrayList();
		for(int i=0;i<list1.size();i++) {
			list3.add(list1.get(i).get("value")) ;
			list4.add(list2.get(i).get("value"));
		}
		map.put("bwqk1", list1);
		map.put("bwqk2",list2);
		map.put("bwqk1_sum", list3);
		map.put("bwqk2_sum", list4);
		return map;
	}
	
	public HashMap< String, Object> zjly_1(String sheng,String shi,String xian) {
		HashMap< String, Object> map=new HashMap< String, Object>();
		List <Map> list1= baseInfoDao.zjyl_count_1(sheng,shi,xian);
		List <Map> list2=baseInfoDao.zjyl_sum_1(sheng,shi,xian);
		List  list3=new ArrayList();
		List  list4=new ArrayList();
		for(int i=0;i<list1.size();i++) {
			list3.add(list1.get(i).get("value")) ;
			list4.add(list2.get(i).get("value"));
		}
		map.put("zjly1",baseInfoDao.tzxz_1(sheng,shi,xian));
		map.put("zjly2", baseInfoDao.tzxz2_1(sheng,shi,xian));
		map.put("zjly1_sum", list3);
		map.put("zjly2_sum", list4);
		return map;
	}
	public HashMap< String, Object> jcxx2_1(String sheng,String shi,String xian){
		HashMap< String, Object> map=new HashMap< String, Object>();
		map.put("jcxx2_1", baseInfoDao.xmszly_1(sheng,shi,xian));//项目所在流域电站
		map.put("jcxx2_2", baseInfoDao.xmszly2_1(sheng,shi,xian));//项目所在流域桩基规模
		return map;
	}
	/**
	 * 装机类型组成，包含电站数量和装机容量2种统计方式
	 * @param xzqhdm
	 * @return
	 */
	public HashMap< String, Object> zjlx(String xzqhdm) {
		HashMap< String, Object> map=new HashMap< String, Object>();
		List <Map> list1= baseInfoDao.zjlxzc(xzqhdm);
		List <Map> list2=baseInfoDao.zjlxzc2(xzqhdm);
		map.put("jcxx1", list1);
		map.put("jcxx2", list2);
		return map;
	}
	
	//设计年发电量
	public List<Map> sjnfdl(String xzqhdm) {
		return baseInfoDao.sjnfdl(xzqhdm);
	}

	public List<Map> sjnfdl_1(String sheng,String shi,String xian) {
		return baseInfoDao.sjnfdl_1(sheng,shi,xian);
	}



}