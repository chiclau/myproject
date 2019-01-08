package com.lyht.business.homepage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.homepage.dao.HomePageDao;
import com.lyht.business.system.formbean.SysStaffFormBean;

/**
 * 基础信息	
 * @author 刘魁
 *@创建时间 2018/10/10
 */

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("all")
public class HomePageService {

	@Resource
	private HomePageDao homePageDao;

	/**
	 * 查询电站信息
	 * @param limit 
	 * @param page 
	 * @param address 
	 * @param name 
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getDzListData(String bj, String name, String shi,String address, int page, int limit){
		return homePageDao.getDzListData(bj,name,shi,address,page,limit);
	}
	

	/**
	 * 获取电站信息list_zz(首页折线图统计)
	 * */
	public PageResults list_zz(String bj, String name, String address, int page, int limit) {
		return homePageDao.list_zz(bj,name,address,page,limit);
	}
	
	/**
	 * 统计全国每个省得水电站个数
	 * @param address 
	 * @param bj 
	 * @return map
	 */
	public List<Map> countQg(String address, String bj) {
		return homePageDao.countQg(address,bj);
	}

	/**
	 * 统计长江经济带每个省得水电站个数
	 * @param bj 
	 * @param address 
	 * @return map
	 */
	public List<Map> countCj(String address, String bj) {
		return homePageDao.countCj(address,bj);
	}

	/**
	 * 统计全国电站数量（十年）
	 * @return
	 */
	/*public List<Map> countDz(String address, String bj) {
		List<Map> list = homePageDao.countDz(address,bj);
		return this.clCountZjrl(list);
	}*/
	/**
	 * 统计全国电站数量（每年）
	 * @return
	 */
	public List<Map> countDz(String address, String bj) {
		return homePageDao.countDz(address,bj);
	}
	/**
	 * 统计全国装机容量数量(每年)
	 * @return
	 */
	public List<Map> countZjrl(String address, String bj) {
		return homePageDao.countZjrl(address,bj);
	}
	
	/**
	 * 统计全国装机容量数量(十年)
	 * @return
	 */
	/*public List<Map> countZjrl(String address, String bj) {
		List<Map> list = homePageDao.countZjrl(address,bj);
		return this.clCountZjrl(list);
	}*/
	
	/**
	 * 统计全国建设状态
	 * @return
	 */
	
	public List<Map> countJszt(String address, String bj) {
		return homePageDao.countJszt(address,bj);
	}
	
	/**
	 * 统计全国开发方式
	 * @return
	 */
	public List<Map> countKffs(String address, String bj) {
		return homePageDao.countKffs(address,bj);
	}
	/**
	 * 统计全国电站数量(长江-----十年)
	 * @return
	 */
	/*public List<Map> countDz_cj(String address, String bj) {
		List<Map> list = homePageDao.countDz_cj(address,bj);
		return this.clCountZjrl(list);
	}*/
	/**
	 * 统计全国电站数量(长江-----每年)
	 * @return
	 */
	public List<Map> countDz_cj(String address, String bj) {
		return homePageDao.countDz_cj(address,bj);
	}
	
	/**
	 * 统计全国装机容量数量(长江----十年)
	 * @return
	 */
	/*public List<Map> countZjrl_cj(String address, String bj) {
		List<Map> list = homePageDao.countZjrl_cj(address,bj);
		return this.clCountZjrl(list);
	}*/
	/**
	 * 统计全国装机容量数量(长江----每年)
	 * @return
	 */
	public List<Map> countZjrl_cj(String address, String bj) {
		return homePageDao.countZjrl_cj(address,bj);
	}
	
	/**
	 * 统计全国建设状态(长江)
	 * @return
	 */
	public List<Map> countJszt_cj(String address, String bj) {
		return homePageDao.countJszt_cj(address,bj);
	}
	
	/**
	 * 统计全国开发方式(长江)
	 * @return
	 */
	public List<Map> countKffs_cj(String address, String bj) {
		return homePageDao.countKffs_cj(address,bj);
	}

	/**
	 * 统计水电站——右
	 * @param css 
	 * @return
	 */
	public List<Map> countDz_right(String address, String bj, String css) {
		return homePageDao.countDz_right(address,bj,css);
	}

	/**
	 * 统计水装机总规模——右
	 * @return
	 */
	public List<Map> countZjzgm_right(String address, String bj, String css) {
		return homePageDao.countZjzgm_right(address,bj,css);
	}
	/**
	 * 统计水电站——右（长江）
	 * @param css 
	 * @return
	 */
	public List<Map> countDz_right_cj(String address, String bj, String css) {
		return homePageDao.countDz_right_cj(address,bj,css);
	}
	
	/**
	 * 统计水装机总规模——右（长江）
	 * @return
	 */
	public List<Map> countZjzgm_right_cj(String address, String bj, String css) {
		return homePageDao.countZjzgm_right_cj(address,bj,css);
	}
	
	/**
	 * 地图搜索键盘抬起事件
	 * @return
	 */
	public List<Map> keyUp(String bj) {
		return homePageDao.keyUp(bj);
	}
	
	/**
	 * 根据电站状态查询各地区电站数量
	 * @param address 
	 * @return
	 */
	public List<Map> countDzslByYxzt(String bj, String name, String address) {
		return homePageDao.countDzslByYxzt(bj,name,address);
	}

	/**
	 * 根据电站状态查询各地区电站装机容量
	 * @param address 
	 * @return
	 */
	public List<Map> countDzzjrlByYxzt(String bj, String name, String address) {
		return homePageDao.countDzzjrlByYxzt(bj,name,address);
	}
	
	/**
	 * 小水电发展历程数量统计
	 * @return
	 */
	public List<Map> countDzByDate(String bj, String name, String address) {
		return homePageDao.countDzByDate(bj,name,address);
	}

	/**
	 * 小水电发展历程装机统计
	 * @return
	 */
	public List<Map> countZjByDate(String bj, String name, String address) {
		return homePageDao.countZjByDate(bj,name,address);
	}
	
	/**
	 * 首页图表转换
	 * @param address 
	 * @return
	 */
	public List<Map> countTbzh(String bj, String address) {
		return homePageDao.countTbzh(bj,address);
	}
	
	/**
	 * 图表切换总计
	 */
	public List<Map> countTbzh_zj(String bj, String address) {
		return homePageDao.countTbzh(bj,address);
	}
	
	
	
	
	/**
	 * 统计每十年数据转换
	 * @param list
	 * @return
	 */
	@SuppressWarnings("all")
	private static List<Map> clCountZjrl(List<Map> list){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String format = sdf.format(date);
		Integer yer = Integer.parseInt(format)-60;
		HashMap<String,Object> mMap = new HashMap<>();
		List<Map> rel = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			map.put("yer",yer);
			rel.add(map);
			yer += 10;
		}
		List<Map> lists = new ArrayList<>();
		Integer one = 0;
		for (int i = 0; i < rel.size(); i++) {
			Map map = rel.get(i);
			Object sum =  map.get("sun");
			if(sum == null && one == 0){
				continue;
			}else{
				one = 1;
				lists.add(map);
			}
		}
		return lists;
	}
}
