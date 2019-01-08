package com.lyht.business.homepage.dao;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.datasearch.bean.HpsInfoSum;
import com.lyht.business.system.formbean.SysStaffFormBean;
/**
 * 基础信息DAO
 * @author 刘魁
 * @param <K>
 *@创建时间 2018/10/10
 */
@Repository
@Scope("prototype")
@SuppressWarnings("all")
public class HomePageDao<K> extends HibernateBaseDao<HpsInfoSum>{
	
	
	private static final String cjjjd = "'上海市','安徽省','浙江省','江苏省','湖北省','云南省','贵州省','四川省','重庆市','湖南省','江西省'";
	

	/**
	 * 查询电站信息
	 * @param limit 
	 * @param page 
	 * @param address 
	 * @param name 
	 * @return
	 */
	public PageResults getDzListData(String bj, String name, String shi,String address, int page, int limit) {
		StringBuilder sql=new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			if(!"总数".equals(name)){
				sql.append("  WHERE b.name = '"+name+"' ");
			}
			sql.append(" ORDER BY sheng  ");
		}
		if("1".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs'  ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			if(!"总数".equals(name)){
				sql.append("  WHERE c.name = '"+name+"' ");
			}
			sql.append(" ORDER BY sheng  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append("  WHERE  a.sheng IN ("+cjjjd+") ");
			if(!"总数".equals(name)){
				sql.append("  AND b.name = '"+name+"' ");
			}
			sql.append(" ORDER BY sheng  ");
		}
		if("3".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append("  WHERE  a.sheng IN ("+cjjjd+") ");
			if(!"总数".equals(name)){
				sql.append("  AND c.name = '"+name+"' ");
			}
			sql.append(" ORDER BY sheng  ");
		}
		if("4".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs'  ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append(" WHERE  a.sheng ='"+address+"' ");
			if(!"总数".equals(name)){
				sql.append("  AND c.name = '"+name+"' ");
			}
			sql.append(" ORDER BY shi  ");
		}
		if("5".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append("  WHERE a.sheng ='"+address+"' ");
			if(!"总数".equals(name)){
				sql.append("  AND b.name = '"+name+"' ");
			}
			sql.append(" ORDER BY shi  ");
		}
		if("6".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append(" WHERE a.sheng ='"+address+"'  ");
			sql.append(" ORDER BY shi  ");
		}
		if("7".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs'  ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append(" WHERE  a.shi ='"+address+"' ");
			if(!"总数".equals(name)){
				sql.append("  AND c.name = '"+name+"'  ");
			}
			sql.append(" ORDER BY xian  ");
		}
		if("8".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append("  WHERE  a.shi ='"+address+"' ");
			if(!"总数".equals(name)){
				sql.append("  AND b.name = '"+name+"' ");
			}
			sql.append(" ORDER BY xian  ");
		}
		if("9".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs'  ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append(" WHERE  a.shi ='"+address+"' ");
			sql.append(" ORDER BY xian  ");
		}
		if("10".equals(bj)){//查县，有市辖区，加市过滤
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs'  ");
			sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
			sql.append(" WHERE  a.xian ='"+address+"'  AND a.shi='"+shi+"'");
			sql.append(" ORDER BY jszt DESC  ");
		}
		return this.findPageByFetchedSql(sql.toString(), null,page,limit,null);
	}
	
	/**
	 * 统计全国每个省得水电站个数
	 * @param address 
	 * @param bj 
	 * @return map
	 */
	public List<Map> countQg(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT sheng AS 省份,COUNT(id) AS 个数,Round(SUM(zjrl)/10000,2) AS 装机 FROM hps_info_sum1 GROUP BY sheng  ");
		}
		if("1".equals(bj)){
			sql.append(" SELECT shi AS 省份,COUNT(id) AS 个数,Round(SUM(zjrl)/10000,2) AS 装机 FROM hps_info_sum1  GROUP BY shi  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT xmmc AS 名称,dj AS 经度,bw AS 纬度,zjrl AS 装机 FROM hps_info_sum1 WHERE shi IN ("+address+") and dj is not null and bw is not null ");
		}
		if("3".equals(bj)){
			sql.append(" SELECT dj AS DJ,bw AS BW,COUNT(id) AS SUM FROM hps_info_sum1 WHERE xian = '"+address+"' GROUP BY DJ,BW ORDER BY SUM");
		}
		System.out.println(sql);
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 统计长江经济带每个省得水电站个数
	 * @param bj 
	 * @param address 
	 * @return map
	 */
	public List<Map> countCj(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT sheng AS 省份,COUNT(id) AS 个数,Round(SUM(zjrl)/10000,2) AS 装机 FROM hps_info_sum1 WHERE sheng IN ("+cjjjd+") GROUP BY sheng  ");
		}
		if("1".equals(bj)){
			sql.append(" SELECT shi AS SHENG,COUNT(id) AS SUM FROM hps_info_sum1 WHERE sheng = '"+address+"' GROUP BY shi ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT xian AS SHENG,COUNT(id) AS SUM FROM hps_info_sum1 WHERE shi = '"+address+"' GROUP BY xian ");
		}
		if("3".equals(bj)){
			sql.append(" SELECT dj AS DJ,bw AS BW,COUNT(id) AS SUM FROM hps_info_sum1 WHERE xian = '"+address+"' GROUP BY DJ,BW ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 统计全国电站数量（十年）
	 * @return
	 *//*
	public List<Map> countDz(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -60 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -60 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -50 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -50 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -40 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -40 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -30 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -30 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -20 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -20 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -10 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -10 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, 0 , GETDATE()))   UNION ALL");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -60 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -50 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -40 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -30 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -20 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -10 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT COUNT(dzbh) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, 0 , GETDATE()))   ");
		return this.createSQLQuerybyMap(sql.toString());
	}*/
	/**
	 * 统计全国电站数量（每年）
	 * @return
	 * */
	public List<Map> countDz(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append("  SELECT a.date AS yer,COUNT(b.id) AS sun FROM date_sum a LEFT JOIN (SELECT id,tcny FROM hps_info_sum1 ) b ON a.[date] = DATENAME(yy, b.tcny)   ");
		};
		if("1".equals(bj)){
			sql.append("  SELECT a.date AS yer,COUNT(b.id) AS sun FROM date_sum a LEFT JOIN (SELECT id,tcny FROM hps_info_sum1 WHERE sheng = '"+address+"') b ON a.[date] = DATENAME(yy, b.tcny)   ");
		};
		if("2".equals(bj)){
			sql.append("  SELECT a.date AS yer,COUNT(b.id) AS sun FROM date_sum a LEFT JOIN (SELECT id,tcny FROM hps_info_sum1 WHERE shi = '"+address+"') b ON a.[date] = DATENAME(yy, b.tcny)   ");
		};
		sql.append("  WHERE a.[date] <= DATENAME(yy, GETDATE())    ");
		sql.append(" GROUP BY a.date  ORDER BY a.date ");
		List<Map> tcCount = this.createSQLQuerybyMap(sql.toString());
		int sum = 0;
		for (int i = 0; i < tcCount.size(); i++) {
			Map map = tcCount.get(i);
			sum +=(Integer)map.get("sun");
			map.put("sum", sum);
		}
		return tcCount;
	}

	/**
	 * 统计全国装机容量数量(十年)
	 * @return
	 */
	/*public List<Map> countZjrl(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -60 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -60 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -50 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -50 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -40 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -40 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -30 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -30 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -20 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -20 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -10 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -10 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, 0 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -60 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -50 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -40 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -30 , GETDATE())) UNION ALL  ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -20 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, -10 , GETDATE()))  UNION ALL ");
		sql.append(" SELECT Round(SUM(zjrl)/10000,2) sun FROM hps_info_sum1  WHERE   ");
		if("1".equals(bj)){sql.append("  sheng = '"+address+"' AND ");};
		sql.append(" tcny IS NOT NULL AND DATENAME(yy, tcny) > DATENAME(yy, DATEADD(yy, -70 , GETDATE())) AND DATENAME(yy, tcny) <= DATENAME(yy, DATEADD(yy, 0 , GETDATE()))   ");
		List<Map> createSQLQuerybyMap = this.createSQLQuerybyMap(sql.toString());
		return createSQLQuerybyMap;
	}*/
	/**
	 * 统计全国装机容量数量(每年)
	 * @param address
	 * @param bj
	 * @return
	 */
	public List<Map> countZjrl(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append("  SELECT a.date AS yer,Round(SUM(ISNULL((zjrl/10000) ,0)),2) AS sun FROM date_sum a LEFT JOIN hps_info_sum1 b ON a.[date] = DATENAME(yy, b.tcny)    ");
		};
		if("1".equals(bj)){
		sql.append(" SELECT a.date AS yer,Round(SUM(ISNULL((zjrl/10000) ,0)),2) AS sun FROM date_sum a LEFT JOIN (SELECT zjrl,tcny FROM hps_info_sum1 WHERE sheng = '"+address+"' ) b ON a.[date] = DATENAME(yy, b.tcny) ");		
		}
		if("2".equals(bj)){
			sql.append(" SELECT a.date AS yer,Round(SUM(ISNULL((zjrl/10000) ,0)),2) AS sun FROM date_sum a LEFT JOIN (SELECT zjrl,tcny FROM hps_info_sum1 WHERE shi = '"+address+"' ) b ON a.[date] = DATENAME(yy, b.tcny) ");		
		}
		sql.append("  WHERE a.[date] <= DATENAME(yy, GETDATE())    ");
		sql.append(" GROUP BY a.date  ORDER BY a.date   ");
		List<Map> tcCount = this.createSQLQuerybyMap(sql.toString());
		double sum = 0;
		for (int i = 0; i < tcCount.size(); i++) {
			DecimalFormat df = new DecimalFormat(".00");
			Map map = tcCount.get(i);
			double x = (double)map.get("sun");
			double parseDouble = Double.parseDouble(df.format(x));
			map.put("sun", parseDouble);
			sum +=parseDouble;
			map.put("sum", Double.parseDouble(df.format(sum)));
		}
		return tcCount;
	}

	/**
	 * 统计全国建设状态
	 * 排序方式：运行、在建、拟建、拆除，废弃，未知
	 * @return
	 */
	public List<Map> countJszt(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM sys_dict b LEFT JOIN hps_info_sum1 a ON a.jszt = b.nm WHERE b.listnm_sys_dict_cate = 'jszt' GROUP BY b.name  ,b.nm order by b.nm ASC ");
		}
		if("1".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM sys_dict b LEFT JOIN (SELECT id, jszt FROM hps_info_sum1 WHERE sheng = '"+address+"') a ON a.jszt = b.nm WHERE b.listnm_sys_dict_cate = 'jszt' GROUP BY b.name ,b.nm order by b.nm ASC  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM sys_dict b LEFT JOIN (SELECT id, jszt FROM hps_info_sum1 WHERE shi = '"+address+"') a ON a.jszt = b.nm WHERE b.listnm_sys_dict_cate = 'jszt' GROUP BY b.name ,b.nm order by b.nm ASC ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 统计全国开发方式
	 * 排序方式：引水式、坝式、混合式、未知
	 * @return
	 */
	public List<Map> countKffs(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a  ON a.kffs = b.nm WHERE b.listnm_sys_dict_cate = 'kffs' GROUP BY b.name, b.nm ORDER BY   b.nm   ");
		}
		if("1".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM  sys_dict b LEFT JOIN (SELECT id, kffs FROM hps_info_sum1 WHERE sheng = '"+address+"') a  ON a.kffs = b.nm WHERE b.listnm_sys_dict_cate = 'kffs' GROUP BY b.name  , b.nm ORDER BY   b.nm   ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM  sys_dict b LEFT JOIN (SELECT id, kffs FROM hps_info_sum1 WHERE shi = '"+address+"') a  ON a.kffs = b.nm WHERE b.listnm_sys_dict_cate = 'kffs' GROUP BY b.name , b.nm ORDER BY   b.nm    ");
		}
		List<Map> createSQLQuerybyMap = this.createSQLQuerybyMap(sql.toString());
		return createSQLQuerybyMap;
	}
	/**
	 * 统计全国电站数量(长江----每年)
	 * @return
	 */
	public List<Map> countDz_cj(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.date AS yer,COUNT(b.id) AS sun FROM date_sum a LEFT JOIN (SELECT id,tcny FROM hps_info_sum1 WHERE sheng IN ("+cjjjd+")) b ON a.[date] = DATENAME(yy, b.tcny) ");		
		sql.append(" WHERE a.[date] <= DATENAME(yy, GETDATE()) ");
		sql.append(" GROUP BY a.date  ORDER BY a.date ");
		List<Map> createSQLQuerybyMap = this.createSQLQuerybyMap(sql.toString());
		int sum = 0;
		for (int i = 0; i < createSQLQuerybyMap.size(); i++) {
			Map map = createSQLQuerybyMap.get(i);
			sum +=(int)map.get("sun");
			map.put("sum", sum);
		}
		return createSQLQuerybyMap;
	}
	
	/**
	 * 统计全国装机容量数量(长江----每年)
	 * @return
	 */
	public List<Map> countZjrl_cj(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.date AS yer,Round(SUM(ISNULL((b.zjrl/10000) ,0)),2) AS sun FROM date_sum a LEFT JOIN (SELECT zjrl,tcny FROM hps_info_sum1 WHERE sheng IN ("+cjjjd+")) b ON a.[date] = DATENAME(yy, b.tcny) ");
		sql.append(" WHERE a.[date] <= DATENAME(yy, GETDATE()) ");
		sql.append(" GROUP BY a.date  ORDER BY a.date ");
		List<Map> createSQLQuerybyMap = this.createSQLQuerybyMap(sql.toString());
		double sum = 0;
		for (int i = 0; i < createSQLQuerybyMap.size(); i++) {
			DecimalFormat df = new DecimalFormat(".00");
			Map map = createSQLQuerybyMap.get(i);
			double x = (double)map.get("sun");
			double parseDouble = Double.parseDouble(df.format(x));
			map.put("sun", parseDouble);
			sum +=parseDouble;
			map.put("sum", Double.parseDouble(df.format(sum)));
		}
		return createSQLQuerybyMap;
	}
	/**
	 * 统计全国建设状态(长江)
	 *  排序方式：运行、在建、拟建、拆除，废弃，未知
	 * @return
	 */
	public List<Map> countJszt_cj(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM sys_dict b  LEFT JOIN (SELECT id,jszt FROM hps_info_sum1 WHERE sheng IN ("+cjjjd+")) a ON a.jszt = b.nm WHERE b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" GROUP BY b.name,b.nm ORDER BY b.nm  ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 统计全国开发方式(长江)
	 * 排序方式：引水式、坝式、混合式、未知
	 * @return
	 */
	public List<Map> countKffs_cj(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT b.name AS name, COUNT(a.id) AS value FROM  sys_dict b LEFT JOIN (SELECT id,kffs FROM hps_info_sum1 WHERE sheng IN ("+cjjjd+")) a  ON a.kffs = b.nm WHERE b.listnm_sys_dict_cate = 'kffs'      ");
			sql.append(" GROUP BY b.name ,b.nm  ORDER BY b.nm ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 统计水电站——右
	 * @param css 
	 * @return
	 */
	public List<Map> countDz_right(String address, String bj, String css) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT sheng AS SHENG,COUNT(id) AS SUM, b.dz AS zs FROM hps_info_sum1 ,(SELECT COUNT(id) AS dz FROM hps_info_sum1) b GROUP BY sheng ,b.dz  ");
		}
		if("1".equals(bj)){
			sql.append(" SELECT shi AS SHENG,COUNT(id) AS SUM, b.dz AS zs FROM hps_info_sum1 ,(SELECT COUNT(id) AS dz FROM hps_info_sum1 WHERE sheng = '"+address+"') b   WHERE sheng = '"+address+"' GROUP BY shi ,b.dz  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT xian AS SHENG,COUNT(id) AS SUM, b.dz AS zs FROM hps_info_sum1 ,(SELECT COUNT(id) AS dz FROM hps_info_sum1 WHERE shi = '"+address+"') b   WHERE shi = '"+address+"' GROUP BY xian ,b.dz  ");
		}
		if("1".equals(css)){
			sql.append("  ORDER BY SUM   ");
		}else{
			sql.append("  ORDER BY SUM DESC ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 统计水装机总规模——右
	 * @return
	 */
	public List<Map> countZjzgm_right(String address, String bj, String css) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT sheng AS SHENG,Round(SUM(zjrl)/10000,2) AS SUM, (round((Round(SUM(zjrl)/10000,2)/b.zj * 100),2)) AS bfb FROM hps_info_sum1,(SELECT Round(SUM(zjrl)/10000,2) AS zj FROM hps_info_sum1) b GROUP BY sheng ,b.zj  ");
		}
		if("1".equals(bj)){
			sql.append(" SELECT shi AS SHENG,Round(SUM(zjrl)/10000,2) AS SUM, (round((Round(SUM(zjrl)/10000,2)/b.zj * 100),2)) AS bfb FROM hps_info_sum1,(SELECT Round(SUM(zjrl)/10000,2) AS zj FROM hps_info_sum1 WHERE sheng = '"+address+"')b  WHERE sheng = '"+address+"'  GROUP BY shi ,b.zj  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT xian AS SHENG,ISNULL(Round(SUM(zjrl)/10000,2), 0) AS SUM, ISNULL((round((Round(SUM(zjrl)/10000,2)/b.zj * 100),2)), 0) AS bfb FROM hps_info_sum1,(SELECT Round(SUM(zjrl)/10000,2) AS zj FROM hps_info_sum1 WHERE shi = '"+address+"')b  WHERE shi = '"+address+"'  GROUP BY xian ,b.zj  ");
		}
		if("1".equals(css)){
			sql.append("  ORDER BY SUM  ");
		}else{
			sql.append("  ORDER BY SUM DESC ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 统计水电站——右（长江）
	 * @param css 
	 * @return
	 */
	public List<Map> countDz_right_cj(String address, String bj, String css) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT sheng AS SHENG,COUNT(id) AS SUM, b.dz AS zs FROM hps_info_sum1 ,(SELECT COUNT(id) AS dz FROM hps_info_sum1 WHERE sheng IN ("+cjjjd+")) b WHERE sheng IN ("+cjjjd+") GROUP BY sheng ,b.dz  ");
		}
		if("1".equals(css)){
			sql.append("  ORDER BY SUM   ");
		}else{
			sql.append("  ORDER BY SUM DESC ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	/**
	 * 统计水装机总规模——右（长江）
	 * @return
	 */
	public List<Map> countZjzgm_right_cj(String address, String bj, String css) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append(" SELECT sheng AS SHENG,Round(SUM(zjrl)/10000,2) AS SUM, (round((Round(SUM(zjrl)/10000,2)/b.zj * 100),2)) AS bfb FROM hps_info_sum1,(SELECT Round(SUM(zjrl)/10000,2) AS zj FROM hps_info_sum1 WHERE sheng IN ("+cjjjd+")) b WHERE sheng IN ("+cjjjd+") GROUP BY sheng ,b.zj  ");
		}
		if("1".equals(css)){
			sql.append("  ORDER BY SUM  ");
		}else{
			sql.append("  ORDER BY SUM DESC ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 地图搜索键盘抬起事件
	 * @return
	 */
	public List<Map> keyUp(String bj) {
		StringBuilder sql = new StringBuilder();
	    sql.append(" SELECT xmmc AS 名称,dj AS 经度, bw AS 纬度 ,'电站' AS '级别',shi AS '定位' FROM hps_info_sum1 WHERE xmmc LIKE '%"+bj+"%'  AND dj != 0 AND bw != 0  UNION ALL   ");
	    sql.append(" SELECT NAME AS 名称,LGTD AS 经度 , LTTD  AS 纬度 ,'省' AS '级别','省' AS  '定位' FROM SD_ADDVCD_DIC WHERE NAME LIKE '%"+bj+"%' AND (PADDVCD = '10086 ' OR PADDVCD = '2') UNION ALL   ");
	    sql.append(" SELECT NAME AS 名称,LGTD AS 经度 , LTTD  AS 纬度 ,'市' AS '级别','市' AS  '定位' FROM SD_ADDVCD_DIC WHERE NAME LIKE '%"+bj+"%' AND PADDVCD != '10086 ' AND PADDVCD != '2'   ");
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 根据电站状态查询各地区电站数量
	 * @param address 
	 * @return
	 */
	public List<Map> countDzslByYxzt(String bj, String name, String address) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){//0为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a GROUP BY a.sheng   ");
			}else{
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND b.listnm_sys_dict_cate = 'jszt'  GROUP BY a.sheng   ");
			}
		}
		if("1".equals(bj)){//1为开发方式
			//sql.append(" SELECT a.sheng AS name,COUNT(a.xmmc) AS value FROM hps_info_sum1 a LEFT JOIN zd_sum b ON a.kffs = b.id WHERE b.kffs = '"+name+"' GROUP BY a.sheng  ");
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a  GROUP BY a.sheng  ");
			}else{
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.sheng  ");
			}
		}
		if("2".equals(bj)){//2(长江经济带)为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a  WHERE  a.sheng IN ("+cjjjd+")   GROUP BY a.sheng  ");
			}else{
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND a.sheng IN ("+cjjjd+") AND b.listnm_sys_dict_cate = 'jszt'  GROUP BY a.sheng  ");
			}
		}
		if("3".equals(bj)){//3(长江经济带)为开发方式
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a  WHERE a.sheng IN ("+cjjjd+") GROUP BY a.sheng  ");
			}else{
				sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND a.sheng IN ("+cjjjd+") AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.sheng  ");
			}
		}
		if("4".equals(bj)){//4(省)为开发方式
			if("总数".equals(name)){
				sql.append(" SELECT a.shi AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.shi  ");
			}else{
				sql.append(" SELECT a.shi AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.shi  ");
			}
		}
		if("5".equals(bj)){//5(省)为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.shi AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.shi  ");
			}else{
				sql.append(" SELECT a.shi AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.shi  ");
			}
		}
		if("6".equals(bj)){//6(全国)查询全省
			sql.append(" SELECT a.shi AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE  a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.shi  ");
		}
		if("7".equals(bj)){//7(市)为开发方式
			if("总数".equals(name)){
				sql.append("  SELECT a.xian AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.xian  ");
			}else{
				sql.append("  SELECT a.xian AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.xian  ");
			}
		}
		if("8".equals(bj)){//8(市)为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.xian AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE  a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.xian  ");
			}else{
				sql.append(" SELECT a.xian AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.xian  ");
			}
		}
		if("9".equals(bj)){//6(全国)查询全市
			sql.append(" SELECT a.xian AS name,COUNT(a.id) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE  a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.xian  ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 根据电站状态查询各地区电站装机容量
	 * @param address 
	 * @return
	 */
	public List<Map> countDzzjrlByYxzt(String bj, String name, String address) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){//0为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a  GROUP BY a.sheng  ");
			}else{
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.sheng  ");
			}
		}
		if("1".equals(bj)){//1为开发方式
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a GROUP BY a.sheng  ");
			}else{
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.sheng  ");
			}
		}
		if("2".equals(bj)){//2(长江经济带)为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a WHERE  a.sheng IN ("+cjjjd+") GROUP BY a.sheng  ");
			}else{
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND a.sheng IN ("+cjjjd+") AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.sheng  ");
			}
		}
		if("3".equals(bj)){//3(长江经济带)为开发方式
			if("总数".equals(name)){
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a WHERE  a.sheng IN ("+cjjjd+")  GROUP BY a.sheng  ");
			}else{
				sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND a.sheng IN ("+cjjjd+") AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.sheng  ");
			}
		}
		if("4".equals(bj)){//4(省)为开发方式
			if("总数".equals(name)){
				sql.append(" SELECT a.shi AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.shi  ");
			}else{
				sql.append(" SELECT a.shi AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.shi  ");
			}
		}
		if("5".equals(bj)){//5(省)为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.shi AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE  a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.shi  ");
			}else{
				sql.append(" SELECT a.shi AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.shi  ");
			}
		}
		if("6".equals(bj)){//6(全国)查询全省
			sql.append(" SELECT a.shi AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE a.sheng = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.shi  ");
		}
		if("7".equals(bj)){//7(shi)为开发方式
			if("总数".equals(name)){
				sql.append(" SELECT a.xian AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.xian ");
			}else{
				sql.append(" SELECT a.xian AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.kffs = b.nm WHERE b.name = '"+name+"' AND a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'kffs' GROUP BY a.xian ");
			}
		}
		if("8".equals(bj)){//8(市)为建设状态
			if("总数".equals(name)){
				sql.append(" SELECT a.xian AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.xian  ");
			}else{
				sql.append(" SELECT a.xian AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE b.name = '"+name+"' AND a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.xian  ");
			}
		}
		if("9".equals(bj)){//6(全国)查询全市
			sql.append(" SELECT a.xian AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm WHERE a.shi = '"+address+"' AND b.listnm_sys_dict_cate = 'jszt' GROUP BY a.xian  ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 小水电发展历程数量统计
	 * @return
	 */
	public List<Map> countDzByDate(String bj, String name, String address) {
		StringBuilder sql = new StringBuilder();
		if("1".equals(bj)){
			sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"'  GROUP BY a.sheng  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT a.sheng AS name,COUNT(a.id) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.sheng IN ("+cjjjd+")  GROUP BY a.sheng  ");
		}
		if("3".equals(bj)){
			sql.append(" SELECT a.shi AS name,COUNT(a.id) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.sheng = '"+address+"'  GROUP BY a.shi  ");
		}
		if("4".equals(bj)){
			sql.append(" SELECT a.xian AS name,COUNT(a.id) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.shi = '"+address+"'  GROUP BY a.xian  ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 小水电发展历程装机统计
	 * @return
	 */
	public List<Map> countZjByDate(String bj, String name, String address) {
		StringBuilder sql = new StringBuilder();
		if("1".equals(bj)){
			sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"' GROUP BY a.sheng  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT a.sheng AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.sheng IN ("+cjjjd+")  GROUP BY a.sheng  ");
		}
		if("3".equals(bj)){
			sql.append(" SELECT a.shi AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.sheng = '"+address+"'  GROUP BY a.shi  ");
		}
		if("4".equals(bj)){
			sql.append(" SELECT a.xian AS name,Round(SUM(a.zjrl)/10000,2) AS value FROM hps_info_sum1 a WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.shi = '"+address+"'  GROUP BY a.xian  ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * 获取电站信息list_zz(首页折线图统计)
	 * */
	public PageResults list_zz(String bj, String name, String address, int page, int limit) {
		StringBuilder sql = new StringBuilder();
		if("1".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,a.bwqk as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' WHERE DATENAME(yy, a.tcny) = '"+name+"'   ");
			sql.append(" ORDER BY sheng  ");
		}
		if("2".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,a.bwqk as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.sheng IN ("+cjjjd+")  ");
			sql.append(" ORDER BY sheng  ");
		}
		if("3".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,a.bwqk as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.sheng = '"+address+"'  ");
			sql.append(" ORDER BY sheng  ");
		}
		if("4".equals(bj)){
			sql.append(" SELECT a.id as id, a.xmmc as xmmc,a.xmwz AS xmwz,a.jthl as jthl,a.zjrl as zjrl,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,a.bwqk as bwqk,c.name AS kffs,a.sfyxmhz as sfyxmhz  ");
			sql.append(" FROM hps_info_sum1 a LEFT JOIN sys_dict b ON a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'  ");
			sql.append(" LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs' WHERE DATENAME(yy, a.tcny) = '"+name+"' AND a.shi = '"+address+"'  ");
			sql.append(" ORDER BY sheng  ");
		}
		return this.findPageByFetchedSql(sql.toString(), null,page,limit,null);
	}

	public List<Map> countTbzh(String bj, String address) {
		StringBuilder sql = new StringBuilder();
		if("qg_jszt".equals(bj)){
			sql.append(" select H.sheng AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )AS rl4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )AS rl5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then H.zjrl else 0 end )AS rl6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )AS rl1 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.sheng HAVING COUNT(H.id) > 0  ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("cj_jszt".equals(bj)){
			sql.append(" select H.sheng AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )AS rl4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )AS rl5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then H.zjrl else 0 end )AS rl6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )AS rl1 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.sheng HAVING COUNT(H.id) > 0  AND H.sheng IN ("+cjjjd+")");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("sf_jszt".equals(bj)){
			sql.append(" select H.shi AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )AS rl4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )AS rl5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then H.zjrl else 0 end )AS rl6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )AS rl1 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.shi,H.sheng HAVING COUNT(H.id) > 0  AND H.sheng = '"+address+"' ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("xs_jszt".equals(bj)){
			sql.append(" select H.xian AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )AS rl4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )AS rl5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then H.zjrl else 0 end )AS rl6, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )AS rl1 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.xian,H.shi HAVING COUNT(H.id) > 0  AND H.shi = '"+address+"' ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("qg_kffs".equals(bj)){
			sql.append(" select H.sheng AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.sheng HAVING COUNT(H.id) > 0  ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("cj_kffs".equals(bj)){
			sql.append(" select H.sheng AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
	//		sql.append(" SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
		//	sql.append(" SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.sheng HAVING COUNT(H.id) > 0 AND H.sheng IN ("+cjjjd+") ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("sf_kffs".equals(bj)){
			sql.append(" select H.shi AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.shi,H.sheng HAVING COUNT(H.id) > 0 AND H.sheng = '"+address+"' ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("xs_kffs".equals(bj)){
			sql.append(" select H.xian AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" GROUP BY H.xian,H.shi HAVING COUNT(H.id) > 0 AND H.shi = '"+address+"' ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		if("qg_kffs_date".equals(bj) || "qg_kffs_date_zj".equals(bj)){
			sql.append(" SELECT A.date AS name,ISNULL(SUM(H.zjrl), 0) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.tcny) ");
			sql.append(" GROUP BY A.date HAVING A.date <= GETDATE() ");
			sql.append(" ORDER BY A.date DESC ");
		}
		if("cj_kffs_date".equals(bj) || "cj_kffs_date_zj".equals(bj)){
			sql.append(" SELECT A.date AS name,ISNULL(SUM(H.zjrl), 0) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.tcny) AND H.sheng IN ("+cjjjd+") ");
			sql.append(" GROUP BY A.date HAVING A.date <= GETDATE() ");
			sql.append(" ORDER BY A.date DESC ");
		}
		if("sf_kffs_date".equals(bj) || "sf_kffs_date_zj".equals(bj)){
			sql.append(" SELECT A.date AS name,ISNULL(SUM(H.zjrl), 0) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.tcny) AND H.sheng ='"+address+"' ");
			sql.append(" GROUP BY A.date HAVING A.date <= GETDATE() ");
			sql.append(" ORDER BY A.date DESC ");
		}
		if("xs_kffs_date".equals(bj) || "xs_kffs_date_zj".equals(bj)){
			sql.append(" SELECT A.date AS name,ISNULL(SUM(H.zjrl), 0) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append(" FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.tcny) ");
			sql.append(" GROUP BY A.date,H.shi HAVING A.date <= GETDATE() AND H.shi ='"+address+"'");
			sql.append(" ORDER BY A.date DESC ");
		}
		return this.createSQLQuerybyMap(sql.toString());
		
	}
	
	public List<Map> countTbzh_zj(String bj, String address) {
		StringBuilder sql = new StringBuilder();
		if("qg_jszt".equals(bj)){
			sql.append(" select H.sheng AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )AS rl4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )AS rl5 ");
			sql.append(" from hps_info_sum1 H  ");
			sql.append(" ORDER BY COUNT(H.id) DESC  ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
}
