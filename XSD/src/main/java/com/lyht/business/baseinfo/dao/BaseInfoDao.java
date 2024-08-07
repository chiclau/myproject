package com.lyht.business.baseinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.baseinfo.bean.NyjHpsInfo;
/**
 * 基础信息DAO
 * @author 刘魁
 *@创建时间 2018/10/10
 */
@Repository
@Scope("prototype")
@SuppressWarnings("all")
public class BaseInfoDao extends HibernateBaseDao<NyjHpsInfo>{
	private static final String cjjjd = "'上海市','安徽省','浙江省','江苏省','湖北省','云南省','贵州省','四川省','重庆市','湖南省','江西省'";
	public List<Map> countDz(String address, String bj) {
		StringBuilder sql = new StringBuilder();
		if("0".equals(bj)){
			sql.append("  SELECT a.date AS yer,COUNT(b.id) AS sun FROM date_sum a LEFT JOIN (SELECT id,tcny FROM hps_info_sum1 ) b ON a.[date] = DATENAME(yy, b.tcny)   ");
		}else{
			sql.append("  SELECT a.date AS yer,COUNT(b.id) AS sun FROM date_sum a LEFT JOIN (SELECT id,tcny FROM hps_info_sum1 ");
			if(!address.equals("")&&address!=null) {
				String xians=	 address.replace(",", "' , '");	
				sql.append("  WHERE xian  IN (' "+xians+"  '  )");
			}
			sql.append("   ) b ON a.[date] = DATENAME(yy, b.tcny)   ");
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
	 * 基础查询页面根据用户点击Echarts统计不同数据
	 * @param xzqhdm
	 * @param name
	 * @param bj
	 * @return
	 */
	public List<Map> countSdzByTj(String xzqhdm, String name, String bj) {
		StringBuffer sql = new StringBuffer();
		Boolean jyCity = this.jyCity(xzqhdm);
		if(jyCity){//true 表示只有一个省得数据
			if("1".equals(bj)){//表示点击的是首页的装机容量范围Echerts
				sql.append(" SELECT shi AS name, COUNT(dzbh) AS value FROM hps_info_sum1 WHERE sheng = '"+xzqhdm+"' GROUP BY shi   ");
			}
		}else{//表示有多个省得数据
			if("1".equals(bj)){//表示点击的是首页的装机容量范围Echerts
				sql.append(" SELECT sheng AS name, COUNT(dzbh) AS value FROM hps_info_sum1 WHERE sheng IN ("+this.splitCity(xzqhdm)+") GROUP BY sheng  ");
			}
		}
		return this.createSQLQuerybyMap(sql.toString());
	}

	public static String splitCity(String city){
		String[] split = city.split(",");
		String str = "";
		for (int i = 0; i < split.length; i++) {
			if(i == split.length-1){
				str += "'"+split[i]+"'";
			}else{
				str += "'"+split[i]+"',";
			}
		}
		return str;
	}

	public static Boolean jyCity(String city){
		String[] split = city.split(",");
		if(split.length>1){
			return false;
		}else{
			return true;
		}
	}



	//设计年发电量
	public List<Map> sjnfdl(String xzqhdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.date AS yer,ISNULL( SUM(ISNULL(sjnfdl ,0)), 0 )");
		sql.append("  AS sun FROM date_sum a LEFT JOIN hps_info_sum1 b ON a.[date] = DATENAME(yy, b.dysj)    ");
		sql.append("    WHERE a.[date] <= DATENAME(yy, GETDATE())  ");
		if(!xzqhdm.equals("")&&xzqhdm!=null) {
			String[] sheng=xzqhdm.split(",");
			sql.append("  AND  sheng  IN (");
			for(int i=0;i<sheng.length;i++) {
				sql.append("'"+sheng[i]+"' ,");
				if(i==sheng.length-1) {
					sql.append("'"+sheng[i]+"' ");
				}
			}
			sql.append(	"  ) ");
		}
		sql.append(" GROUP BY a.date  ORDER BY a.date   ");
		List<Map> tcCount = this.createSQLQuerybyMap(sql.toString());
		double sum = 0;
		for (int i = 0; i < tcCount.size(); i++) {
			Map map = tcCount.get(i);
			sum +=(double)map.get("sun");
			map.put("sum", sum);
		}
		return tcCount;
	}

	public List<Map> sjnfdl_1(String sheng,String shi,String xian) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.date AS yer,ISNULL( SUM(ISNULL(sjnfdl ,0)), 0 )");
		sql.append("  AS sun FROM date_sum a LEFT JOIN hps_info_sum1 b ON a.[date] = DATENAME(yy, b.dysj)    ");
		sql.append("    WHERE a.[date] <= DATENAME(yy, GETDATE())  ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  b.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  b.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  b.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY a.date  ORDER BY a.date   ");
		List<Map> tcCount = this.createSQLQuerybyMap(sql.toString());
		double sum = 0;
		for (int i = 0; i < tcCount.size(); i++) {
			Map map = tcCount.get(i);
			sum +=(double)map.get("sun");
			map.put("sum", sum);
		}
		return tcCount;
	}

	public PageResults getDzListData(String bj, String name, String sheng,String shi,String xian,int page, int limit,int tab,String seriesName) {
		StringBuilder sql=new StringBuilder();
		sql.append("    SELECT a.id as id, a.xmmc as xmmc,a.sheng AS xmwz,a.jthl as jthl,a.zjrl as zjrl , ");
		sql.append("   e.name as xmszly ,a.ztz as ztz,a.sjnfdl as sjnfdl,b.name AS jszt,a.tzxz as tzxz,ss.name as bwqk,  ");
		sql.append("  c.name AS kffs,a.sfyxmhz as sfyxmhz  FROM hps_info_sum1 a LEFT JOIN sys_dict b ON   ");
		sql.append("  		a.jszt = b.nm AND b.listnm_sys_dict_cate = 'jszt'    ");
		sql.append(" LEFT JOIN sys_dict ss ON a.bwqk = ss.nm AND ss.listnm_sys_dict_cate = 'bwqk' ");
		sql.append(" LEFT JOIN sys_dict e ON a.xmszly = e.nm AND e.listnm_sys_dict_cate = 'xmszly'    ");
		sql.append("   LEFT JOIN sys_dict c ON a.kffs = c.nm AND c.listnm_sys_dict_cate = 'kffs'  where 1=1    ");
		if(name.equals("500以下")) {
			sql.append(" AND   a.zjrl<500  OR a.zjrl IS NULL ");
		}else if(name.equals("500-1万")){
			sql.append(" AND a.zjrl>=500 AND a.zjrl<10000");
		}else if(name.equals("1万-5万")){
			sql.append(" AND a.zjrl>=10000 AND a.zjrl<=50000");
		}
		if(tab==2) {
			if(!name.equals("")) {
				//:['长江流域','珠江流域','淮河流域','黄河流域','东南沿海诸河','其他']
				if(name.equals("长江流域")) {
					sql.append(" AND a.xmszly ='3'");
				}else if(name.equals("黄河流域")) {
					sql.append(" AND a.xmszly ='2'");
				}else if(name.equals("珠江流域")) {
					sql.append(" AND a.xmszly ='1'");
				}else if(name.equals("淮河流域")) {
					sql.append(" AND a.xmszly ='4'");
				}else if(name.equals("东南沿海诸河")) {
					sql.append(" AND a.xmszly ='5'");
				}else if(name.equals("其他")) {
					sql.append(" AND a.xmszly IN('6','东北诸河','雅鲁藏布江及西藏其他河流','',null)");
				}
			}

		}
		if(tab==9||tab==10) {
			if(!name.equals("")) {
				sql.append(" AND a.tzxz ='"+name+"'");
			}
		}
		if(tab==11||tab==12) {
			if(!name.equals("")) {
				if(name.equals("接入电网")) {
					sql.append(" AND a.bwqk ='1'");
				}else if(name.equals("直供电(化工)")) {
					sql.append(" AND a.bwqk ='2'");
				}else if(name.equals("直供电(民用电)")) {
					sql.append(" AND a.bwqk ='3'");
				}else if(name.equals("直供电(其他)")) {
					sql.append(" AND a.bwqk ='4'");
				}else if(name.equals("其他")) {
					sql.append(" AND a.bwqk ='5'");
				}
			}
		}
		if(tab==13||tab==14) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND a.sfyxmhz ='' or  a.sfyxmhz is null");
				}else {
					sql.append(" AND a.sfyxmhz ='"+name+"'");
				}
			}
		}
		if(tab==15) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND   a.stllxfcs is NULL AND A.JSZT IN(1,2)");
				}else if(name.equals("有")){
					sql.append(" AND a.stllxfcs ='有'  AND A.JSZT IN(1,2)");
				}else if(name.equals("无")){
					sql.append(" AND a.stllxfcs ='无'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
			
			}
			sql.append(" AND A.sheng IN ("+cjjjd+") ");
		}
		if(tab==16) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND   a.stlljkss is NULL  AND A.JSZT IN(1,2)");
				}else if(name.equals("有")){
					sql.append(" AND a.stlljkss ='有'  AND A.JSZT IN(1,2)");
				}else if(name.equals("无")){
					sql.append(" AND a.stlljkss ='无'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
				sql.append(" AND A.sheng IN ("+cjjjd+") ");
			}
		}
			if(tab==17) {
				if(!name.equals("")) {
					if(name.equals("未知")) {
						sql.append("  AND   a.gycs is NULL  AND A.JSZT IN(1,2)");
					}else if(name.equals("有")){
						sql.append(" AND a.gycs ='有'  AND A.JSZT IN(1,2)");
					}else if(name.equals("无")){
						sql.append(" AND a.gycs ='无'  AND A.JSZT IN(1,2)");
					}else {
						sql.append("  AND A.JSZT IN(1,2)");
					}
					sql.append(" AND A.sheng IN ("+cjjjd+") ");
				}
		}
		if(tab==18) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND   a.zzflcs is NULL  AND A.JSZT IN(1,2)");
				}else if(name.equals("有")){
					sql.append(" AND a.zzflcs ='有'  AND A.JSZT IN(1,2)");
				}else if(name.equals("无")){
					sql.append(" AND a.zzflcs ='无'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
				sql.append(" AND A.sheng IN ("+cjjjd+") ");
			}
		}
		if(tab==19) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND  a.sffhgh is null AND A.JSZT IN(1,2)");
				}else if(name.equals("是")||name.equals("否")){
					sql.append(" AND a.sffhgh ='"+name+"'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
				sql.append(" AND A.sheng IN ("+cjjjd+") ");
			}
		}
		if(tab==20) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND  a.sffhghhp is null AND A.JSZT IN(1,2)");
				}else if(name.equals("是")||name.equals("否")){
					sql.append(" AND a.sffhghhp ='"+name+"'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
				sql.append(" AND A.sheng IN ("+cjjjd+") ");
			}
		}
		if(tab==21) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND  a.sftgjghbys is null AND A.JSZT IN(1,2)");
				}else if(name.equals("是")||name.equals("否")){
					sql.append(" AND a.sftgjghbys ='"+name+"'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
				sql.append(" AND A.sheng IN ("+cjjjd+") ");
			}
		}
		if(tab==22) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append("  AND  a.bxsfcztsgk is null AND A.JSZT IN(1,2)");
				}else if(name.equals("是")||name.equals("否")){
					sql.append(" AND a.bxsfcztsgk ='"+name+"'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
				sql.append(" AND A.sheng IN ("+cjjjd+") ");
			}
		}
		if(tab==30) {
			if(!name.equals("")) {
				if(name.equals("引水式")) {
					sql.append("  AND  a.kffs ='1'");
				}else if(name.equals("坝式")){
					sql.append(" AND  a.kffs in('2','4' )");
				}else if(name.equals("混合式")){
					sql.append(" AND  a.kffs ='3'");
				}else if(name.equals("未知")){
					sql.append(" AND  a.kffs ='5'");
				}
				
			}
		}
		if(tab==31) {//建设状态
			if(!name.equals("")) {
				if(name.equals("废弃")) {
					sql.append("  AND  a.jszt ='5'");
				}else if(name.equals("拆除")){
					sql.append(" AND  a.jszt ='4'");
				}else if(name.equals("运行")){
					sql.append(" AND  a.jszt ='1'");
				}else if(name.equals("拟建")){
					sql.append(" AND  a.jszt ='3'");
				}else if(name.equals("在建")){
					sql.append(" AND  a.jszt ='2'");
				}else if(name.equals("未知")){
					sql.append(" AND  a.jszt ='6'");
				}
				
			}
		}
		if(tab==32) {//开展项目环评情况
			if(!name.equals("")&&!seriesName.equals("")) {
				if(seriesName.equals("开展环评")) {//是开展环评
					if(name.equals("1986前")) {
						sql.append("  AND  a.dysj<'1986' AND a.sftgxmhp='是' ");
					}else if(name.equals("1986-1989")){
						sql.append(" AND   a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='是' ");
					}else if(name.equals("1989-1998")){
						sql.append(" AND  a.dysj BETWEEN '1989' AND '1998' AND a.sftgxmhp='是' ");
					}else if(name.equals("1998-2003")){
						sql.append("AND  a.dysj  BETWEEN '1998' AND '2003' AND a.sftgxmhp='是' ");
					}else if(name.equals("2003-2006")){
						sql.append(" AND  a.dysj BETWEEN '2003' AND '2006' AND a.sftgxmhp='是' ");
					}else if(name.equals("2006后")){
						sql.append(" AND  a.dysj >'2006' AND a.sftgxmhp='是' ");
					}
				}
				if(seriesName.equals("未开展环评")){
					if(name.equals("1986前")) {
						sql.append("  AND a.dysj<'1986' AND a.sftgxmhp='否'  ");
					}else if(name.equals("1986-1989")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}else if(name.equals("1989-1998")){
						sql.append(" AND   a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}else if(name.equals("1998-2003")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否'  ");
					}else if(name.equals("2003-2006")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}else if(name.equals("2006后")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}
				}
			}
		}
		if(tab==32) {//开展项目环评情况
			if(!name.equals("")&&!seriesName.equals("")) {
				if(seriesName.equals("开展环评")) {//是开展环评
					if(name.equals("1986前")) {
						sql.append("  AND  a.dysj<'1986' AND a.sftgxmhp='是' ");
					}else if(name.equals("1986-1989")){
						sql.append(" AND   a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='是' ");
					}else if(name.equals("1989-1998")){
						sql.append(" AND  a.dysj BETWEEN '1989' AND '1998' AND a.sftgxmhp='是' ");
					}else if(name.equals("1998-2003")){
						sql.append("AND  a.dysj  BETWEEN '1998' AND '2003' AND a.sftgxmhp='是' ");
					}else if(name.equals("2003-2006")){
						sql.append(" AND  a.dysj BETWEEN '2003' AND '2006' AND a.sftgxmhp='是' ");
					}else if(name.equals("2006后")){
						sql.append(" AND  a.dysj >'2006' AND a.sftgxmhp='是' ");
					}
				}
				if(seriesName.equals("未开展环评")){
					if(name.equals("1986前")) {
						sql.append("  AND a.dysj<'1986' AND a.sftgxmhp='否'  ");
					}else if(name.equals("1986-1989")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}else if(name.equals("1989-1998")){
						sql.append(" AND   a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}else if(name.equals("1998-2003")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否'  ");
					}else if(name.equals("2003-2006")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}else if(name.equals("2006后")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND a.sftgxmhp='否' ");
					}
				}
			}
		}
		if(tab==33) {//投产前完成环评审批情况统计
			if(!name.equals("")&&!seriesName.equals("")) {
				if(seriesName.equals("完成环评审批手续")) {//完成环评审批手续
					if(name.equals("1986前")) {
						sql.append("  AND  a.dysj<'1986'   AND a.hpspsj< a.dysj ");
					}else if(name.equals("1986-1989")){
						sql.append(" AND   a.dysj BETWEEN '1986' AND '1989' AND a.hpspsj< a.dysj  ");
					}else if(name.equals("1989-1998")){
						sql.append(" AND  a.dysj BETWEEN '1989' AND '1998' AND a.hpspsj< a.dysj  ");
					}else if(name.equals("1998-2003")){
						sql.append("AND  a.dysj  BETWEEN '1998' AND '2003' AND a.hpspsj< a.dysj  ");
					}else if(name.equals("2003-2006")){
						sql.append(" AND  a.dysj BETWEEN '2003' AND '2006' AND a.hpspsj< a.dysj  ");
					}else if(name.equals("2006后")){
						sql.append(" AND  a.dysj >'2006' AND a.hpspsj< a.dysj  ");
					}
				}
				if(seriesName.equals("未完成环评审批手续")){//未完成环评审批手续
					if(name.equals("1986前")) {
						sql.append("  AND a.dysj<'1986' AND a.hpspsj> a.dysj  ");
					}else if(name.equals("1986-1989")){
						sql.append(" AND    a.dysj BETWEEN '1986' AND '1989' AND  a.hpspsj> a.dysj  ");
					}else if(name.equals("1989-1998")){
						sql.append(" AND   a.dysj BETWEEN '1989' AND '1998'  AND  a.hpspsj> a.dysj  ");
					}else if(name.equals("1998-2003")){
						sql.append(" AND   a.dysj  BETWEEN '1998' AND '2003'  AND  a.hpspsj> a.dysj  ");
					}else if(name.equals("2003-2006")){
						sql.append(" AND  a.dysj BETWEEN '2003' AND '2006' AND a.hpspsj> a.dysj ");
					}else if(name.equals("2006后")){
						sql.append(" AND     a.dysj >'2006'   AND a.hpspsj> a.dysj ");
					}
				}
			}
		}
		if(tab==34) {
			if(!seriesName.equals("")) {
				if(seriesName.equals("涉及自然保护区")) {
					if(name.equals("缓冲区")) {
						sql.append(" AND  a.	hcq='涉及' ");
					}
					if(name.equals("试验区")) {
						sql.append(" AND  a.	sys='涉及' ");
					}
					if(name.equals("核心区（未分区）")) {
						sql.append(" AND  a.	hxq='涉及' or a.wfq='涉及'");	
					}
				}
				if(seriesName.equals("涉及自然保护区情况统计")) {
					if(name.equals("未知")) {
						sql.append(" AND     a.sfsjzrbhq IS NULL ");
					}else {
						sql.append(" AND     a.sfsjzrbhq='"+name+"' ");
					}
				}
			}
		}
		if(tab==35) {//是否开展项目环评
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append(" AND     a.sftgxmhp IS NULL AND A.JSZT IN(1,2) ");
				}else if(name.equals("是")||name.equals("否")){
					sql.append(" AND a.sftgxmhp ='"+name+"'  AND A.JSZT IN(1,2)");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
			}
			sql.append("AND  a.sheng IN ("+cjjjd+") ");
		}
		if(tab==36) {
			if(!name.equals("")) {
				if(name.equals("未知")) {
					sql.append(" AND     a.hpspsj IS NULL AND A.JSZT IN(1,2) ");
				}else if(name.equals("是")) {
					sql.append(" AND     a.hpspsj IS NOT NULL AND A.JSZT IN(1,2)");
				}else if(name.equals("否")) {
					sql.append(" AND     a.hpspsj='' AND A.JSZT IN(1,2) ");
				}else {
					sql.append("  AND A.JSZT IN(1,2)");
				}
			}
			sql.append("AND  a.sheng IN ("+cjjjd+")   ");
		}
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  a.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  a.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  a.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" ORDER BY sheng  ");
		return this.findPageByFetchedSql(sql.toString(), null
				,page
				,limit
				,null);
	}


	public List<Map> jcxx2_1(String xzqhdm){
		String xians=	 xzqhdm.replace(",", "' , '");
		StringBuffer sql = new StringBuffer("SELECT a.xmszly name,count(a.id) value");
		sql.append(" from hps_info_sum1  a ");
		if(xians!=null&&!xians.equals("")) {
			sql.append("  where  xian  IN (' "+xians+"  '  )");
		}
		sql.append(" GROUP BY a.xmszly   ");
		return this.createSQLQuerybyMap(sql.toString());
	}

	public List<Map> jcxx2_2(String xzqhdm){
		String xians=	 xzqhdm.replace(",", "' , '");
		StringBuffer sql = new StringBuffer("SELECT a.xmszly name,sum(a.zjrl) value");
		sql.append(" FROM hps_info_sum1 a   ");
		if(xians!=null&&!xians.equals("")) {
			sql.append("  where  xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY a.xmszly   ");
		return this.createSQLQuerybyMap(sql.toString());
	}

	//建设状态
	public List<Map> countJszt(String address, String bj) {
		String xians=	 address.replace(",", "' , '");
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name, COUNT(a.jszt) AS value FROM sys_dict b LEFT JOIN (SELECT jszt FROM hps_info_sum1   ");
		if(xians!=null&&!xians.equals("")) {
			sql.append("  where  xian  IN ('"+xians+"'  )");
		}
		sql.append(" ) a ON a.jszt = b.nm WHERE b.listnm_sys_dict_cate = 'jszt' GROUP BY b.name   ,b.nm order by b.nm ASC");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public List<Map> countJszt(String sheng,String shi,String xian) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name, COUNT(a.jszt) AS value FROM sys_dict b LEFT JOIN (SELECT jszt FROM hps_info_sum1  WHERE 1=1  ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" ) a ON a.jszt = b.nm WHERE b.listnm_sys_dict_cate = 'jszt' GROUP BY b.name  ,b.nm order by b.nm ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> countKffs(String sheng,String shi,String xian) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name, COUNT(a.kffs) AS value FROM  sys_dict b LEFT JOIN (SELECT kffs FROM hps_info_sum1 WHERE 1=1   ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" ) a  ON a.kffs = b.nm WHERE b.listnm_sys_dict_cate = 'kffs' GROUP BY b.name, b.nm ORDER BY b.nm   ");
		List<Map> createSQLQuerybyMap = this.createSQLQuerybyMap(sql.toString());
		return createSQLQuerybyMap;
	}
	
	public List<Map> countKffs(String address, String bj) {
		String xians=	 address.replace(",", "' , '");
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name, COUNT(a.kffs) AS value FROM  sys_dict b LEFT JOIN (SELECT kffs FROM hps_info_sum1   ");
		if(xians!=null&&!xians.equals("")) {
			sql.append("  where  xian  IN ('"+xians+"'  )");
		}
		sql.append(" ) a  ON a.kffs = b.nm WHERE b.listnm_sys_dict_cate = 'kffs' GROUP BY b.name, b.nm ORDER BY b.nm ");
		List<Map> createSQLQuerybyMap = this.createSQLQuerybyMap(sql.toString());
		return createSQLQuerybyMap;
	}
	
	public  List<Map> zjyl_count(String xzqhdm){
		String xians=	 xzqhdm.replace(",", "','");
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.tzxz name,count(id) value ");
		sql.append(" FROM hps_info_sum1 a  ");
		if(xians!=null&&!xians.equals("")) {
			sql.append("  where  xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY a.tzxz  ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public  List<Map> zjyl_count_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.tzxz name,count(id) value ");
		sql.append(" FROM hps_info_sum1 a  WHERE 1=1 ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  a.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  a.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  a.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY a.tzxz  ");
		return this.createSQLQuerybyMap(sql.toString());
	}


	public  List<Map> zjyl_sum(String xzqhdm){
		String xians=	 xzqhdm.replace(",", "' , '");
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.tzxz name,sum(zjrl) value ");
		sql.append(" FROM hps_info_sum1 a  ");
		if(xians!=null&&!xians.equals("")) {
			sql.append("  where  xian  IN (' "+xians+"  '  )");
		}
		sql.append(" GROUP BY a.tzxz  ");
		return this.createSQLQuerybyMap(sql.toString());
	}

	public  List<Map> zjyl_sum_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.tzxz name,sum(zjrl) value ");
		sql.append(" FROM hps_info_sum1 a WHERE 1=1 ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  a.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  a.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  a.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY a.tzxz  ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public  List<Map> bwqk1(String xzqhdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name,COUNT(a.ID) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a ");
		sql.append(" ON a.bwqk = b.nm WHERE b.listnm_sys_dict_cate = 'bwqk'  ");
		if(!xzqhdm.equals("")&&xzqhdm!=null) {
			String xians=	 xzqhdm.replace(",", "' , '");	
			sql.append("  and a. xian  IN (' "+xians+"  '  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}

	public  List<Map> bwqk1_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name,COUNT(a.ID) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a ");
		sql.append(" ON a.bwqk = b.nm WHERE b.listnm_sys_dict_cate = 'bwqk'  ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  a.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  a.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  a.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public  List<Map> bwqk2(String xzqhdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name,Round(SUM(ISNULL((a.zjrl/10000) ,0)),2) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a  ");
		sql.append(" ON a.bwqk = b.nm WHERE b.listnm_sys_dict_cate = 'bwqk' ");
		if(!xzqhdm.equals("")&&xzqhdm!=null) {
			String xians=	 xzqhdm.replace(",", "' , '");	
			sql.append("  and a. xian  IN (' "+xians+"  '  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public  List<Map> bwqk2_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT b.name AS name,Round(SUM(ISNULL((a.zjrl/10000) ,0)),2) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a  ");
		sql.append(" ON a.bwqk = b.nm WHERE b.listnm_sys_dict_cate = 'bwqk' ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  a.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  a.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  a.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}



//项目核准情况统计，电站数量
	public  List<Map> sfyxmhz1_1(String sheng,String shi,String xian){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT  ");
		sql.append("  SUM(CASE WHEN sfyxmhz='是' THEN 1 ELSE 0 END) as '是', ");
		sql.append("  SUM(CASE WHEN sfyxmhz='否' THEN 1 ELSE 0 END) as '否',");
		sql.append("  SUM(CASE WHEN sfyxmhz IS NULL OR sfyxmhz='' THEN 1  ELSE 0 END) as '未知' ");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append("  )as b UNPIVOT (value FOR name in([是],[否],[未知])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public  List<Map> sfyxmhz1(String xzqhdm){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT  ");
		sql.append("  SUM(CASE WHEN sfyxmhz='是' THEN 1 ELSE 0 END) as '是', ");
		sql.append("  SUM(CASE WHEN sfyxmhz='否' THEN 1 ELSE 0 END) as '否',");
		sql.append("  SUM(CASE WHEN sfyxmhz IS NULL OR sfyxmhz='' THEN 1  ELSE 0 END) as '未知' ");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!xzqhdm.equals("") && xzqhdm != null) {
			String xians = xzqhdm.replace(",", "' , '");
			if (xians != null && !xians.equals("")) {
				sql.append("  AND  xian  IN (' " + xians + "  '  )");
			}
		}
		sql.append("  )as b UNPIVOT (value FOR name in([是],[否],[未知])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
//项目核准情况。装机容量
	public  List<Map> sfyxmhz2(String xzqhdm){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT  ");
		sql.append("  SUM(CASE WHEN sfyxmhz='是' THEN zjrl ELSE 0 END) as '是', ");
		sql.append("  SUM(CASE WHEN sfyxmhz='否' THEN zjrl ELSE 0 END) as '否',");
		sql.append("  SUM(CASE WHEN sfyxmhz IS NULL OR sfyxmhz='' THEN zjrl ELSE 0 END) as '未知' ");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!xzqhdm.equals("") && xzqhdm != null) {
			String xians = xzqhdm.replace(",", "' , '");
			if (xians != null && !xians.equals("")) {
				sql.append("  AND  xian  IN (' " + xians + "  '  )");
			}
		}
		sql.append("  )as b UNPIVOT (value FOR name in([是],[否],[未知])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public  List<Map> sfyxmhz2_1(String sheng,String shi,String xian){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT  ");
		sql.append("  SUM(CASE WHEN sfyxmhz='是' THEN zjrl ELSE 0 END) as '是', ");
		sql.append("  SUM(CASE WHEN sfyxmhz='否' THEN zjrl ELSE 0 END) as '否',");
		sql.append("  SUM(CASE WHEN sfyxmhz IS NULL OR sfyxmhz='' THEN zjrl ELSE 0 END) as '未知' ");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append("  )as b UNPIVOT (value FOR name in([是],[否],[未知])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}

	 /**
	  * 基础统计项目所在流域电站统计
	  * @param xzqhdm
	  * @return
	  */
	public  List<Map> xmszly(String xzqhdm){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT    ");
		sql.append(" SUM(CASE WHEN xmszly='1' THEN 1 ELSE 0 END) as '珠江流域', ");
		sql.append("   SUM(CASE WHEN xmszly='3' THEN 1 ELSE 0 END) as '长江流域',");
		sql.append("   SUM(CASE WHEN xmszly='4' THEN 1 ELSE 0 END) as '淮河流域', ");
		sql.append("  SUM(CASE WHEN xmszly='2' THEN 1 ELSE 0 END) as '黄河流域',");
	    sql.append("  SUM(CASE WHEN xmszly = '6'   or xmszly = '东北诸河' or xmszly = '雅鲁藏布江及西藏其他河流' THEN 1 ELSE 0 END) as '其他', ");
		sql.append("   SUM(CASE WHEN xmszly='5' THEN 1 ELSE 0 END) as '东南沿海诸河'");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!xzqhdm.equals("") && xzqhdm != null) {
			String xians = xzqhdm.replace(",", "','");
			if (xians != null && !xians.equals("")) {
				sql.append("  AND  xian  IN ('"+xians+"')");
			}
		}
		sql.append("  )as b UNPIVOT (value FOR name in([长江流域],[珠江流域],[淮河流域],[黄河流域],[东南沿海诸河],[其他])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public  List<Map> xmszly_1(String sheng,String shi,String xian){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT    ");
		sql.append(" SUM(CASE WHEN xmszly='1' THEN 1 ELSE 0 END) as '珠江流域', ");
		sql.append("   SUM(CASE WHEN xmszly='3' THEN 1 ELSE 0 END) as '长江流域',");
		sql.append("   SUM(CASE WHEN xmszly='4' THEN 1 ELSE 0 END) as '淮河流域', ");
		sql.append("  SUM(CASE WHEN xmszly='2' THEN 1 ELSE 0 END) as '黄河流域',");
	    sql.append("  SUM(CASE WHEN xmszly = '6'   or xmszly = '东北诸河' or xmszly = '雅鲁藏布江及西藏其他河流' THEN 1 ELSE 0 END) as '其他', ");
		sql.append("   SUM(CASE WHEN xmszly='5' THEN 1 ELSE 0 END) as '东南沿海诸河'");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append("  )as b UNPIVOT (value FOR name in([长江流域],[珠江流域],[淮河流域],[黄河流域],[东南沿海诸河],[其他])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	 /**
	  * 基础统计项目所在流域装机规模统计
	  * @param xzqhdm
	  * @return
	  */
	public  List<Map> xmszly2(String xzqhdm){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT    ");
		sql.append(" SUM(CASE WHEN xmszly='1' THEN zjrl ELSE 0 END) as '珠江流域', ");
		sql.append("   SUM(CASE WHEN xmszly='3' THEN zjrl ELSE 0 END) as '长江流域',");
		sql.append("   SUM(CASE WHEN xmszly='4' THEN zjrl ELSE 0 END) as '淮河流域', ");
		sql.append("  SUM(CASE WHEN xmszly='2' THEN zjrl ELSE 0 END) as '黄河流域',");
		sql.append("  SUM(CASE WHEN xmszly = '6' or xmszly = '东北诸河' or xmszly = '雅鲁藏布江及西藏其他河流' THEN zjrl ELSE 0 END) as '其他', ");
		sql.append("   SUM(CASE WHEN xmszly='5' THEN zjrl ELSE 0 END) as '东南沿海诸河'");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!xzqhdm.equals("") && xzqhdm != null) {
			String xians = xzqhdm.replace(",", "' , '");
			if (xians != null && !xians.equals("")) {
				sql.append("  AND  xian  IN (' " + xians + "  '  )");
			}
		}
		sql.append("  )as b UNPIVOT (value FOR name in([长江流域],[珠江流域],[淮河流域],[黄河流域],[东南沿海诸河],[其他])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public  List<Map> xmszly2_1(String sheng,String shi,String xian){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT name,value FROM(SELECT    ");
		sql.append(" SUM(CASE WHEN xmszly='1' THEN zjrl ELSE 0 END) as '珠江流域', ");
		sql.append("   SUM(CASE WHEN xmszly='3' THEN zjrl ELSE 0 END) as '长江流域',");
		sql.append("   SUM(CASE WHEN xmszly='4' THEN zjrl ELSE 0 END) as '淮河流域', ");
		sql.append("  SUM(CASE WHEN xmszly='2' THEN zjrl ELSE 0 END) as '黄河流域',");
		sql.append("  SUM(CASE WHEN xmszly = '6' or xmszly = '东北诸河' or xmszly = '雅鲁藏布江及西藏其他河流' THEN zjrl ELSE 0 END) as '其他', ");
		sql.append("   SUM(CASE WHEN xmszly='5' THEN zjrl ELSE 0 END) as '东南沿海诸河'");
		sql.append("   from hps_info_sum1  where 1=1   ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append("  )as b UNPIVOT (value FOR name in([长江流域],[珠江流域],[淮河流域],[黄河流域],[东南沿海诸河],[其他])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public  List<Map> tzxz(String xzqhdm){
		StringBuilder sql = new StringBuilder();
		sql.append("   SELECT b.name AS name,COUNT(a.ID) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a    ");
		sql.append("  ON a.tzxz = b.name WHERE b.listnm_sys_dict_cate = 'tzxz'      ");
		if(!xzqhdm.equals("")&&xzqhdm!=null) {
			String xians=	 xzqhdm.replace(",", "' , '");	
			sql.append("  and a. xian  IN (' "+xians+"  '  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public  List<Map> tzxz_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append("   SELECT b.name AS name,COUNT(a.ID) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a    ");
		sql.append("  ON a.tzxz = b.name WHERE b.listnm_sys_dict_cate = 'tzxz'      ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  a.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  a.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  a.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public  List<Map> tzxz2(String xzqhdm){
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT b.name AS name,Round(SUM(ISNULL((a.zjrl/10000) ,0)),2) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a     ");
		sql.append("  ON a.tzxz = b.name WHERE b.listnm_sys_dict_cate = 'tzxz'      ");
		if(!xzqhdm.equals("")&&xzqhdm!=null) {
			String xians=	 xzqhdm.replace(",", "' , '");	
			sql.append("  and a. xian  IN (' "+xians+"  '  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public  List<Map> tzxz2_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT b.name AS name,Round(SUM(ISNULL((a.zjrl/10000) ,0)),2) AS value FROM  sys_dict b LEFT JOIN hps_info_sum1 a     ");
		sql.append("  ON a.tzxz = b.name WHERE b.listnm_sys_dict_cate = 'tzxz'      ");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  a.sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  a.sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  a.sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" GROUP BY b.name     ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<Map> countTbzh(String bj, String xzqhdm) {
		StringBuilder sql = new StringBuilder();
		if(bj.equals("1")) {//基础统计小水电装机类型组成图表切换
			sql.append("  select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  ");
			sql.append("   SUM(CASE WHEN H.zjrl>=10000 AND H.zjrl<=50000  then H.zjrl else 0 end )AS zrl1, ");
			sql.append("   SUM(CASE WHEN  H.zjrl>=10000 AND H.zjrl<=50000  then 1 else 0 end )AS zdz1,  ");
			sql.append("   SUM(CASE WHEN H.zjrl>=500 AND  H.zjrl<10000 then H.zjrl else 0 end )AS zrl2, ");
			sql.append("   SUM(CASE WHEN H.zjrl>=500 AND  H.zjrl<10000   then 1 else 0 end )AS zdz2, ");
			sql.append("  SUM(CASE WHEN H.zjrl<500  or H.zjrl is null  then H.zjrl else 0 end )AS zrl3,  ");
			sql.append("  SUM(CASE WHEN H.zjrl<500  or H.zjrl is null  then 1 else 0 end )AS zdz3   ");
			sql.append("  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  ");
			sql.append("  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}
		else if (bj.equals("a")) {//装机类型组成图表切换,全选
			sql.append("  select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  ");
			sql.append("   SUM(CASE WHEN H.zjrl>10000 AND H.zjrl<=50000  then H.zjrl else 0 end )AS zrl1, ");
			sql.append("   SUM(CASE WHEN  H.zjrl>10000 AND H.zjrl<=50000  then 1 else 0 end )AS zdz1,  ");
			sql.append("   SUM(CASE WHEN H.zjrl>500 AND  H.zjrl<=10000 then H.zjrl else 0 end )AS zrl2, ");
			sql.append("   SUM(CASE WHEN H.zjrl>500 AND  H.zjrl<=10000   then 1 else 0 end )AS zdz2, ");
			sql.append("  SUM(CASE WHEN H.zjrl<=500 then H.zjrl else 0 end )AS zrl3,  ");
			sql.append("  SUM(CASE WHEN   H.zjrl<=500  then 1 else 0 end )AS zdz3   ");
			sql.append("  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  ");
			sql.append("  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  ");
		//	sql.append("  AND A.id IN (" + xzqhdm + ") ");
			sql.append("  ORDER BY A.id ");
		}
		else if (bj.equals("2")) {//基础统计小水电所在流域分布图表切换
			sql.append("  select S.name AS name ,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz, ");
			sql.append(" SUM(CASE WHEN H.zjrl>=10000 AND H.zjrl<=50000 then  H.zjrl else 0 end )AS zrl1,  ");
			sql.append("   SUM(CASE WHEN  H.zjrl>=10000 AND H.zjrl<=50000 then 1 else 0 end )AS zdz1,  ");
			sql.append(" SUM(CASE WHEN H.zjrl>=500 AND  H.zjrl<10000 then H.zjrl else 0 end )AS zrl2, ");
			sql.append("  SUM(CASE WHEN H.zjrl>500 AND  H.zjrl<10000 then 1 else 0 end )AS zdz2, ");
			sql.append(" SUM(CASE WHEN   H.zjrl<500 or H.zjrl is null  then H.zjrl else 0 end )AS zrl3,  ");
			sql.append(" SUM(CASE WHEN H.zjrl<500 or H.zjrl is null  then 1 else 0 end )AS zdz3   ");
			sql.append(" from hps_info_sum1 H  LEFT JOIN Area A  ON  A.id = H.sd_sheng_id   ");
			sql.append("  LEFT JOIN sys_dict S ON H.xmszly = S.nm AND S.listnm_sys_dict_cate = 'xmszly' ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  WHERE A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  GROUP BY S.name,S.nm  ");
			sql.append("  ORDER BY S.nm ");
		}else if (bj.equals("3")) {//建设状态切换,全选
			sql.append(" select name, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name ) as dzz, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=1) as dz1, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=2) as dz2, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=3) as dz3, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=4) as dz4, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=5) as dz5, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=6) as dz6, ");
			sql.append(" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name ) as rlz , ");
			sql.append(" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=1) as rl1, ");
			sql.append(" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=2) as rl2, ");
			sql.append(" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=3) as rl3, ");
			sql.append(" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=4) as rl4, ");
			sql.append(" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=6) as rl6, ");
			sql.append(" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=5) as rl5 ");
			sql.append(" from Area A WHERE codeid LIKE '%0000' AND (SELECT count(id) from hps_info_sum1 where sheng=A.name ) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append(" order by dzz DESC  ");
		}
		else if (bj.equals("31")) {//建设状态不是全国切换
			sql.append("  select A.name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz5, ");
			sql.append(" SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )AS rl4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl5, ");
			sql.append(" SUM(CASE WHEN H.kffs = 6 then H.zjrl else 0 end )AS rl6 ");
			sql.append("  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  ");
			sql.append("  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}
		else if( bj.equals("qg_kffs")){//全国开发方式切换表格 qg_kffs_date
			sql.append(" select name, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name ) as dzz, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=1) as dz1, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=2) as dz2, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=3) as dz3, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=5) as dz4, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name ) as rlz , ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=1) as rl1, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=2) as rl2, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=3) as rl3, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=5) as rl4 ");
			sql.append(" from Area A WHERE codeid LIKE '%0000' AND (SELECT count(id) from hps_info_sum1 where sheng=A.name ) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}
		else if( bj.equals("qg_kffs_date")){//小水电设计年发电量   切换表格 
			sql.append(" select name, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name ) as dzz, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=1) as dz1, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=2) as dz2, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=3) as dz3, ");
			sql.append(" (SELECT count(id) from hps_info_sum1 where sheng=A.name and kffs=5) as dz4, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name ) as rlz , ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=1) as rl1, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=2) as rl2, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=3) as rl3, ");
			sql.append(" (SELECT SUM(zjrl) from hps_info_sum1 where sheng=A.name and kffs=5) as rl4 ");
			sql.append(" from Area A WHERE codeid LIKE '%0000' AND (SELECT count(id) from hps_info_sum1 where sheng=A.name OR shi=A.name ) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}else if (bj.equals("qg_kffs_date1")) {//开发方式不是全国切换
			sql.append("  select A.name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append("  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  ");
			sql.append("  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}
		else if (bj.equals("qg_kffs1")) {//开发方式不是全国切换
			sql.append("  select A.name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz4, ");
			sql.append(" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl1, ");
			sql.append(" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl2, ");
			sql.append(" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3, ");
			sql.append(" SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )AS rl4 ");
			sql.append("  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  ");
			sql.append("  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}else if (bj.equals("5")) {
			sql.append("   select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,    ");
			sql.append("    SUM(CASE WHEN H.tzxz='国有'  then H.zjrl else 0 end )AS zrl1,   ");
			sql.append("  SUM(CASE WHEN H.tzxz='国有'  then 1 else 0 end )AS zdz1,  ");
			sql.append("  SUM(CASE WHEN H.tzxz='集体'  then H.zjrl else 0 end )AS zrl2,  ");
			sql.append("   SUM(CASE WHEN H.tzxz='集体'  then 1 else 0 end )AS zdz2,  ");
			sql.append("  SUM(CASE WHEN H.tzxz='民营'  then H.zjrl else 0 end )AS zrl3,  ");
			sql.append("  SUM(CASE WHEN H.tzxz='民营'  then 1 else 0 end )AS zdz3 ,  ");
			sql.append("  SUM(CASE WHEN H.tzxz='混合' then H.zjrl else 0 end )AS zrl4,  ");
			sql.append("  SUM(CASE WHEN H.tzxz='混合'   then 1 else 0 end )AS zdz4");
			sql.append("    from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id    ");
			sql.append("   GROUP BY A.name,A.id HAVING COUNT(H.id) > 0 ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}else if (bj.equals("6")) {//并网情况切换表格
			sql.append("    select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,     ");
			sql.append("    SUM(CASE WHEN H.bwqk='1'  then H.zjrl else 0 end )AS zrl1, ");
			sql.append("    SUM(CASE WHEN H.bwqk='1'   then 1 else 0 end )AS zdz1,");
			sql.append("     SUM(CASE WHEN H.bwqk='3'   then H.zjrl else 0 end )AS zrl2,  ");
			sql.append("  SUM(CASE WHEN  H.bwqk='3'   then 1 else 0 end )AS zdz2,  ");
			sql.append("  SUM(CASE WHEN  H.bwqk='2'   then H.zjrl else 0 end )AS zrl3,   ");
			sql.append("  SUM(CASE WHEN  H.bwqk='2'  then 1 else 0 end )AS zdz3 ,   ");
			sql.append("   SUM(CASE WHEN H.bwqk='4' then H.zjrl else 0 end )AS zrl4,    ");
			sql.append(" SUM(CASE WHEN H.bwqk='4'   then 1 else 0 end )AS zdz4,   ");
			sql.append("   SUM(CASE WHEN H.bwqk='5' then H.zjrl else 0 end )AS zrl5,    ");
			sql.append(" SUM(CASE WHEN H.bwqk='5'   then 1 else 0 end )AS zdz5   ");
			sql.append("  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id     ");
			sql.append("  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}else if (bj.equals("7")) {
			sql.append("  select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  ");
			sql.append("   SUM(CASE WHEN H.sfyxmhz='是'  then H.zjrl else 0 end )AS zrl1, ");
			sql.append("   SUM(CASE WHEN  H.sfyxmhz='是'  then 1 else 0 end )AS zdz1,  ");
			sql.append("   SUM(CASE WHEN H.sfyxmhz='否' then H.zjrl else 0 end )AS zrl2, ");
			sql.append("   SUM(CASE WHEN H.sfyxmhz='否'   then 1 else 0 end )AS zdz2, ");
			sql.append("  SUM(CASE WHEN H.sfyxmhz is null then H.zjrl else 0 end )AS zrl3,  ");
			sql.append("  SUM(CASE WHEN  H.sfyxmhz is null    then 1 else 0 end )AS zdz3   ");
			sql.append("  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  ");
			sql.append("  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  ");
			if(xzqhdm!=null && !xzqhdm.equals("")) {
				sql.append("  AND A.id IN (" + xzqhdm + ") ");
			}
			sql.append("  ORDER BY A.id ");
		}
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 基本信息小水电装机类型组成统计
	 * @param xzqhdm
	 * @return
	 */
	public List<Map> zjlxzc(String xzqhdm){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT name,value FROM (");
		sql.append("SELECT  SUM(CASE WHEN zjrl< 500 OR zjrl IS NULL  THEN 1 ELSE 0 END) as '500以下',");
		sql.append("  SUM(CASE WHEN zjrl>=500 and zjrl<10000 THEN 1 ELSE 0 END) as '500-1万', ");
		sql.append("  SUM(CASE WHEN zjrl>=10000 and zjrl<=50000 THEN 1 ELSE 0 END) as '1万-5万' from hps_info_sum1 ");
		sql.append("  where 1=1");
		if (!xzqhdm.equals("") && xzqhdm != null) {
			String xians = xzqhdm.replace(",", "' , '");
			if (xians != null && !xians.equals("")) {
				sql.append("  and  xian  IN (' " + xians + "  '  )");
			}
		}
		sql.append(" )  as b UNPIVOT (value FOR name in([500以下],[500-1万],[1万-5万])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}

	public List<Map> zjlxzc_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT name,value FROM (");
		sql.append("SELECT  SUM(CASE WHEN zjrl<500 OR zjrl IS NULL  THEN 1 ELSE 0 END) as '500以下',");
		sql.append("  SUM(CASE WHEN zjrl>=500 and zjrl<10000 THEN 1 ELSE 0 END) as '500-1万', ");
		sql.append("  SUM(CASE WHEN zjrl>=10000 and zjrl<=50000 THEN 1 ELSE 0 END) as '1万-5万' from hps_info_sum1 ");
		sql.append("  where 1=1");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" )  as b UNPIVOT (value FOR name in([500以下],[500-1万],[1万-5万])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 基本信息小水电装机类型组成统计（zjrl）
	 * @param xzqhdm
	 * @return
	 */
	public List<Map> zjlxzc2(String xzqhdm){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT name,value FROM (");
		sql.append("SELECT  SUM(CASE WHEN zjrl<500 OR zjrl IS NULL  THEN zjrl ELSE 0 END) as '500以下',");
		sql.append("  SUM(CASE WHEN zjrl>=500 and zjrl<10000 THEN zjrl ELSE 0 END) as '500-1万', ");
		sql.append("  SUM(CASE WHEN zjrl>=10000 and zjrl<=50000 THEN zjrl ELSE 0 END) as '1万-5万' from hps_info_sum1 ");
		sql.append("  where 1=1");
		if (!xzqhdm.equals("") && xzqhdm != null) {
			String xians = xzqhdm.replace(",", "' , '");
			if (xians != null && !xians.equals("")) {
				sql.append("  and  xian  IN (' " + xians + "  '  )");
			}
		}
		sql.append(" )  as b UNPIVOT (value FOR name in([500以下],[500-1万],[1万-5万])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}

	public List<Map> zjlxzc2_1(String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT name,value FROM (");
		sql.append("SELECT  SUM(CASE WHEN zjrl<=500 OR zjrl IS NULL  THEN zjrl ELSE 0 END) as '500以下',");
		sql.append("  SUM(CASE WHEN zjrl>500 and zjrl<=10000 THEN zjrl ELSE 0 END) as '500-1万', ");
		sql.append("  SUM(CASE WHEN zjrl>10000 and zjrl<=50000 THEN zjrl ELSE 0 END) as '1万-5万' from hps_info_sum1 ");
		sql.append("  where 1=1");
		if (!sheng.equals("") && sheng != null) {
			String shengs = sheng.replace(",", "' , '");
				sql.append("  and  sd_sheng  IN ('"+shengs+"'  )");
		}
		if (!shi.equals("") && shi != null) {
			String shis = shi.replace(",", "' , '");
				sql.append("  and  sd_shi  IN ('"+shis+"'  )");
		}
		if (!xian.equals("") && xian != null) {
			String xians = xian.replace(",", "' , '");
				sql.append("  and  sd_xian  IN ('"+xians+"'  )");
		}
		sql.append(" )  as b UNPIVOT (value FOR name in([500以下],[500-1万],[1万-5万])) t");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public List<SlbInfoBean> selectSlb() { // 查询水利部所有数据
		StringBuilder sql = new StringBuilder();
		sql.append("select * from slb_hps_info order BY id");
		List<SlbInfoBean> list = this.getSession().createSQLQuery(sql.toString()).addEntity(SlbInfoBean.class).list();
		return list;
	}
	public List<HjbHpsInfo> selectHjb() { // 查询环境部所有数据
		StringBuilder sql = new StringBuilder();
		sql.append("select * from hjb_hps_info order BY id");
		List<HjbHpsInfo> list = this.getSession().createSQLQuery(sql.toString()).addEntity(HjbHpsInfo.class).list();
		return list;
	}
	public List<nyjInfoBean> selectnyj() { // 查询环境部所有数据
		StringBuilder sql = new StringBuilder();
		sql.append("select * from nyj_info ");
		List<nyjInfoBean> list = this.getSession().createSQLQuery(sql.toString()).addEntity(nyjInfoBean.class).list();
		return list;
	}
	public List<CeshiSum1> selectCeshi() { // 查询主表所有数据
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ceshi_sum1 order BY id");
		List<CeshiSum1> list = this.getSession().createSQLQuery(sql.toString()).addEntity(CeshiSum1.class).list();
		return list;
	}

	public String updateTab(String table ,String getMethod , Object value , String id){
		
		
		StringBuilder sql = new StringBuilder();
		if (value != null && !"".equals(value)){
			//String method = this.toLowerCase(getMethod);
			sql.append(" UPDATE "+table+" SET "+getMethod+" = '"+value+"' WHERE id = '"+id+"' ;");
			//System.out.println(sql.toString());
			int i = this.exectueSQL(sql.toString());
			/*if (i == 1){
				return true;
			} else {
				return false;
			}*/
			return sql.toString();
		} else {
			return sql.toString();
		}
	}
	
	public int RunSQL(String sql){
		
		return this.exectueSQL(sql);

	}

	private static String toLowerCase(String string){
		string = string.substring(3);
		int b = 0;
		char[] chars = string.toCharArray();
		String str = "";
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] >= 'A' && chars[i] <= 'Z'){
				b++;
				if (b < 2) {
					str += chars[i];
				} else {
					str += "_"+chars[i];
				}
			} else {
				str += chars[i];
			}
		}
		if (b < 2) {
			str = string.toLowerCase();
		} else {
			str = str.toLowerCase();
		}
		return str;
	}

	public boolean insertCeshiSum(nyjInfoBean slbInfoBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ceshi_sum1 (dzbh, xmmc,dj, bw, sheng, shi,xian, szxjxzqbm, xmwz, jsdw, dzlxr, lxrdh,xmszly, yjzl, jthl, "+
				"zjrl, ztz, sjnfdl, sjnfdl_xz, tzxz, tzly, bwqk, kffs,qskhdjl, jszt, dysj, ccyy, sfykzstxf, sjfdl,sjfdl_xz, sfyxmhz, hzwjmcjch, "+
				"xmhzdw, sffhgh, ghmc, ghshsj, ghspbm, sffhghhp,ghhpwj, scwjwh, ghhpscbm,sftgxmhp, spwjmc, spwjwh,hpspsj, ppbm, sftgjghbys,"+
				"yswjmc, yswjwh, ysbm, stllxfcs, stlljkss, gycs,zzflcs, qthbcs, qthbcsms,sfsjzrbhq, zrbhqslyj, zrbhqjj,hxq, hcq, sys, wfq,"+
				"bxsfcztsgk, tshdcd, qtsthjwt,tbr, dh, tbsj, xchcr,ywjmc, dzzs, tcny,zhltqk, zrk, bg, swdj,ghspjwh, jsfaspbmjwh, pzkmbmjwh,"+
				"hpjhbyswh, sbjwh, ydpcjwh,sfjddzzhwxx, ysbmjwh, xmfrmc,zytzr, lxdh, bz, hpsjzytcsj, sfyjgbg, dztcsjybhqslsjxhgx, sd_sheng,"+
				"sd_shi, sd_xian, sd_sheng_id,sd_shi_id, sd_xian_id)");
		sql.append(" values");
		sql.append("('"+slbInfoBean.getDzbh()+"', '"+slbInfoBean.getXmmc()+"',"+slbInfoBean.getDj()+","+slbInfoBean.getBw()+",'"+slbInfoBean.getSheng()+"','"+slbInfoBean.getShi()+"',");
		sql.append("'"+slbInfoBean.getXian()+"',null,'"+slbInfoBean.getXmwz()+"','"+slbInfoBean.getJsdw()+"','"+slbInfoBean.getDzlxr()+"',");
		sql.append("'"+slbInfoBean.getLxrdh()+"','"+slbInfoBean.getXmszly()+"','"+slbInfoBean.getYjzl()+"','"+slbInfoBean.getJthl()+"'," // 第一行结束
				+ ""+slbInfoBean.getZjrl()+","); // 第二行
		sql.append(""+slbInfoBean.getZtz()+","+slbInfoBean.getSjnfdl()+","+slbInfoBean.getSjnfdlXz()+",'"+slbInfoBean.getTzxz()+"','"+slbInfoBean.getTzly()+"',");
		sql.append("'"+slbInfoBean.getBwqk()+"','"+slbInfoBean.getKffs()+"','"+slbInfoBean.getQskhdjl()+"','"+slbInfoBean.getJszt()+"','"+slbInfoBean.getDysj()+"',");
		sql.append("'"+slbInfoBean.getCcyy()+"','"+slbInfoBean.getSfykzstxf()+"',"+slbInfoBean.getSjfdl()+","+slbInfoBean.getSjfdlXz()+",'"+slbInfoBean.getSfyxmhz()+"',");
		sql.append("'"+slbInfoBean.getHzwjmcjch()+"',"
				+ "'"+slbInfoBean.getXmhzdw()+"','"+slbInfoBean.getSffhgh()+"','"+slbInfoBean.getGhmc()+"','"+slbInfoBean.getGhshsj()+"',"); // 第三行
		sql.append("'"+slbInfoBean.getGhspbm()+"','"+slbInfoBean.getSffhghhp()+"','"+slbInfoBean.getGhhpwj()+"','"+slbInfoBean.getScwjwh()+"','"+slbInfoBean.getGhhpscbm()+"',");
		sql.append("'"+slbInfoBean.getSftgxmhp()+"','"+slbInfoBean.getSpwjmc()+"','"+slbInfoBean.getSpwjwh()+"','"+slbInfoBean.getHpspsj()+"','"+slbInfoBean.getPpbm()+"',");
		sql.append("'"+slbInfoBean.getSftgjghbys()+"',"
				+ "'"+slbInfoBean.getYswjmc()+"','"+slbInfoBean.getYswjwh()+"','"+slbInfoBean.getYsbm()+"','"+slbInfoBean.getStllxfcs()+"',"); // 第四行
		sql.append("'"+slbInfoBean.getStlljkss()+"','"+slbInfoBean.getGycs()+"','"+slbInfoBean.getZzflcs()+"','"+slbInfoBean.getQthbcs()+"','"+slbInfoBean.getQthbcsms()+"',");
		sql.append("'"+slbInfoBean.getSfsjzrbhq()+"','"+slbInfoBean.getZrbhqslyj()+"','"+slbInfoBean.getZrbhqjj()+"','"+slbInfoBean.getHxq()+"','"+slbInfoBean.getHcq()+"',");
		sql.append("'"+slbInfoBean.getSys()+"','"+slbInfoBean.getWfq()+"','"+slbInfoBean.getBxsfcztsgk()+"',"+slbInfoBean.getTshdcd()+",'"+slbInfoBean.getQtsthjwt()+"',");
		sql.append("'"+slbInfoBean.getTbr()+"','"+slbInfoBean.getDh()+"','"+slbInfoBean.getTbsj()+"','"+slbInfoBean.getXchcr()+"','"+slbInfoBean.getYwjmc()+"','"+slbInfoBean.getDzzs()+"',");
		sql.append("'"+slbInfoBean.getTcny()+"','"+slbInfoBean.getZhltqk()+"',"+slbInfoBean.getZrk()+","+slbInfoBean.getBg()+","+slbInfoBean.getSwdj()+",'"+slbInfoBean.getGhspjwh()+"',");
		sql.append("'"+slbInfoBean.getJsfaspbmjwh()+"','"+slbInfoBean.getPzkmbmjwh()+"',"
				+ "'"+slbInfoBean.getHpjhbyswh()+"','"+slbInfoBean.getSbjwh()+"','"+slbInfoBean.getYdpcjwh()+"',"); // 第六行
		sql.append("'"+slbInfoBean.getSfjddzzhwxx()+"','"+slbInfoBean.getYsbmjwh()+"','"+slbInfoBean.getXmfrmc()+"','"+slbInfoBean.getZytzr()+"','"+slbInfoBean.getLxdh()+"',");
		sql.append("'"+slbInfoBean.getBz()+"',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)");
		System.out.println(sql);
		int i = 0;
		try {
			i = this.exectueSQL(sql.toString());
		} catch (Exception e) {
		}
		return i>0?true:false;
	}

	/**
	 *  新数据添加成功的时候向数据来源中插入值
	 *  hjbHpsInfo要插入的数据
	 *  dept为部门编号 1为水利部,2为环境部,3为能源局
	 */
	public void insertDzFrom(SlbInfoBean hjb,Integer dept) {
		DzFrom df = new DzFrom();
		StringBuilder sql = new StringBuilder();
		if(hjb.getDzbh() != null && !"".equals(hjb.getDzbh())){ // 电站编号
			df.setDzbh(hjb.getDzbh());
		}
		if(hjb.getXmmc() != null && !"".equals(hjb.getXmmc())){ // 项目名称
			df.setXmmc(hjb.getXmmc());
		}
		if(hjb.getXmwz() != null && !"".equals(hjb.getXmwz())){ // 项目位置
			df.setXmwz(dept);
		}
		if(hjb.getJsdw() != null && !"".equals(hjb.getJsdw())){ // 建设单位
			df.setJsdw(dept);
		}
		if(hjb.getDzlxr() != null && !"".equals(hjb.getDzlxr())){ // 电站联系人
			df.setDzlxr(dept);
		}
		if(hjb.getLxrdh() != null && !"".equals(hjb.getLxrdh())){ // 联系人电话
			df.setLxrdh(dept);
		}
		if(hjb.getXmszly() != null && !"".equals(hjb.getXmszly())){ // 项目所在流域
			df.setXmszly(dept);
		}
		if(hjb.getYjzl() != null && !"".equals(hjb.getYjzl())){ // 一级支流
			df.setYjzl(dept);
		}
		if(hjb.getJthl() != null && !"".equals(hjb.getJthl())){ // 具体河流
			df.setJthl(dept);
		}
		if(hjb.getZjrl()!=null && hjb.getZjrl() != 0.0 && hjb.getZjrl() != 0 && !"".equals(hjb.getZjrl())){ // 装机容量
			df.setZjrl(dept);
		}
		if(hjb.getZtz() != null && hjb.getZtz() != 0.0 && hjb.getZtz() != 0 && !"".equals(hjb.getZtz())){ // 总投资
			df.setZtz(dept);
		}
		if(hjb.getSjnfdl() != null && !"".equals(hjb.getSjnfdl()) && hjb.getSjnfdl() != 0 && hjb.getSjnfdl() != 0.0){ // 设计年发电量
			df.setSjnfdl(dept);
		}
		if(hjb.getSjnfdlXz() != null && !"".equals(hjb.getSjnfdlXz()) && hjb.getSjnfdlXz() != 0 && hjb.getSjnfdlXz() != 0.0){ // 设计年发电量修公正
			df.setSjnfdlXz(dept);
		}
		if(hjb.getTzxz() != null && !"".equals(hjb.getTzxz())){ // 投资性质
			df.setTzxz(dept);
		}
		if(hjb.getTzly() != null && !"".equals(hjb.getTzly())){ // 投资来源
			df.setTzly(dept);
		}
		if(hjb.getTzly() != null && !"".equals(hjb.getTzly())){ // 投资来源
			df.setTzly(dept);
		}
		if(hjb.getBwqk() != null && !"".equals(hjb.getBwqk())){ // 并网情况
			df.setBwqk(dept);
		}
		if(hjb.getKffs() != null && !"".equals(hjb.getKffs())){ // 开发方式
			df.setKffs(dept);
		}
		if (hjb.getQskhdjl() != null && !"".equals(hjb.getQskhdjl())) { // 取水口河道距离
			df.setQskhdjl(dept);
		}
		if (hjb.getJszt() != null && !"".equals(hjb.getJszt())) { // 建设（运营）状态
			df.setJszt(dept);
		}
		if (!"1900-01-01".equals(hjb.getDysj())) { // 对应日期
			df.setDysj(dept);
		}
		if (hjb.getCcyy() != null && !"".equals(hjb.getCcyy())) { // 拆除原因
			df.setCcyy(dept);
		}
		if (hjb.getSfykzstxf() != null && !"".equals(hjb.getSfykzstxf())) { // 是否已开展生态修复
			df.setSfykzstxf(dept);
		}
		if (hjb.getSjnfdl() != null && !"".equals(hjb.getSjnfdl()) && hjb.getSjnfdl() != 0 && hjb.getSjnfdl() != 0.0) { // 设计年发电量
			df.setSjnfdl(dept);
		}
		if (hjb.getSjnfdlXz() != null && !"".equals(hjb.getSjnfdlXz()) && hjb.getSjnfdlXz() != 0 && hjb.getSjnfdlXz() != 0.0) { // 设计年发电量—修正
			df.setSjnfdlXz(dept);
		}
		if (hjb.getSfyxmhz() != null && !"".equals(hjb.getSfyxmhz())) { // 是否有项目核准（审批）
			df.setSfyxmhz(dept);
		}
		if (hjb.getHzwjmcjch() != null && !"".equals(hjb.getHzwjmcjch())) { // 核准（审批）文件名称及文号
			df.setHzwjmcjch(dept);
		}
		if (hjb.getXmhzdw() != null && !"".equals(hjb.getXmhzdw())) { // 项目核准（审批）部门
			df.setXmhzdw(dept);
		}
		if (hjb.getSffhgh() != null && !"".equals(hjb.getSffhgh())) { // 是否符合规划
			df.setSffhgh(dept);
		}
		if (hjb.getGhmc() != null && !"".equals(hjb.getGhmc())) { // 规划名称
			df.setGhmc(dept);
		}
		if (hjb.getGhshsj() != null && !"".equals(hjb.getGhshsj())) { // 规划审批时间
			df.setGhshsj(dept);
		}
		if (hjb.getGhspbm() != null && !"".equals(hjb.getGhspbm())) { // 规划审批部门
			df.setGhspbm(dept);
		}
		if (hjb.getSffhghhp() != null && !"".equals(hjb.getSffhghhp())) { // 是否符合规划环评
			df.setSffhghhp(dept);
		}
		if (hjb.getGhhpwj() != null && !"".equals(hjb.getGhhpwj())) { // 规划环评文件
			df.setGhhpwj(dept);
		}
		if (hjb.getScwjwh() != null && !"".equals(hjb.getScwjwh())) { // 审查文件文号
			df.setScwjwh(dept);
		}
		if (hjb.getGhhpscbm() != null && !"".equals(hjb.getGhhpscbm())) { // 规划环评审查部门
			df.setGhhpscbm(dept);
		}
		if (hjb.getSftgxmhp() != null && !"".equals(hjb.getSftgxmhp())) { // 是否通过项目环评
			df.setSftgxmhp(dept);
		}
		if (hjb.getSpwjmc() != null && !"".equals(hjb.getSpwjmc())) { // 审批文件名称
			df.setSpwjmc(dept);
		}
		if (hjb.getSpwjwh() != null && !"".equals(hjb.getSpwjwh())) { // 审批文件文号
			df.setSpwjwh(dept);
		}
		if (!"1900-01-01".equals(hjb.getHpspsj())) { // 环评审批时间
			df.setHpspsj(dept);
		}
		if (hjb.getPpbm() != null && !"".equals(hjb.getPpbm())) { // 审批部门
			df.setPpbm(dept);
		}
		if (hjb.getSftgjghbys() != null && !"".equals(hjb.getSftgjghbys())) { // 是否通过竣工环保验收
			df.setSftgjghbys(dept);
		}
		if (hjb.getYswjmc() != null && !"".equals(hjb.getYswjmc())) { // 验收文件名称
			df.setYswjmc(dept);
		}
		if (hjb.getYswjwh() != null && !"".equals(hjb.getYswjwh())) { // 验收文件文号
			df.setYswjwh(dept);
		}
		if (hjb.getYsbm() != null && !"".equals(hjb.getYsbm())) { // 验收部门
			df.setYsbm(dept);
		}
		if (hjb.getStllxfcs() != null && !"".equals(hjb.getStllxfcs())) { // 生态流量泄放措施
			df.setStllxfcs(dept);
		}
		if (hjb.getStllxfcs() != null && !"".equals(hjb.getStllxfcs())) { // 生态流量监控设施
			df.setStlljkss(dept);
		}
		if (hjb.getGycs() != null && !"".equals(hjb.getGycs())) { // 过鱼措施
			df.setGycs(dept);
		}
		if (hjb.getZzflcs() != null && !"".equals(hjb.getZzflcs())) { //增殖放流措施
			df.setZzflcs(dept);
		}
		if (hjb.getQthbcs() != null && !"".equals(hjb.getQthbcs())) { // 其他环保措施
			df.setQthbcs(dept);
		}
		if (hjb.getQthbcsms() != null && !"".equals(hjb.getQthbcsms())) { // 其他环保措施描述
			df.setQthbcsms(dept);
		}
		if (hjb.getSfsjzrbhq() != null && !"".equals(hjb.getSfsjzrbhq())) { // 是否涉及自然保护区
			df.setSfsjzrbhq(dept);
		}
		if (!"1900-01-01".equals(hjb.getZrbhqslyj())) { // 自然保护区设立时间
			df.setZrbhqslyj(dept);
		}
		if (hjb.getZrbhqjj() != null && !"".equals(hjb.getZrbhqjj())) { // 自然保护区级别
			df.setZrbhqjj(dept);
		}
		if (hjb.getHxq() != null && !"".equals(hjb.getHxq())) { // 核心区
			df.setHxq(dept);
		}
		if (hjb.getHcq() != null && !"".equals(hjb.getHcq())) { // 缓冲区
			df.setHcq(dept);
		}
		if (hjb.getSys() != null && !"".equals(hjb.getSys())) { // 实验区
			df.setSys(dept);
		}
		if (hjb.getWfq() != null && !"".equals(hjb.getWfq())) { // 未分区
			df.setWfq(dept);
		}
		if (hjb.getBxsfcztsgk() != null && !"".equals(hjb.getBxsfcztsgk())) { // 坝下是否存在脱水干涸
			df.setBxsfcztsgk(dept);
		}
		if (hjb.getTshdcd() != null && !"".equals(hjb.getTshdcd()) && hjb.getTshdcd() != 0 && hjb.getTshdcd() != 0.0) { // 脱水河段长度（公里）
			df.setTshdcd(dept);
		}
		if (hjb.getQtsthjwt() != null && !"".equals(hjb.getQtsthjwt())) { //其他生态环境问题
			df.setQtsthjwt(dept);
		}
		if (hjb.getTbr() != null && !"".equals(hjb.getTbr())) { // 填表人
			df.setTbr(dept);
		}
		if (hjb.getDh() != null && !"".equals(hjb.getDh())) { // 电话
			df.setDh(dept);
		}
		if (!"1900-01-01".equals(hjb.getDysj())) { // 填表日期
			df.setDysj(dept);
		}
		if (hjb.getXchcr() != null && !"".equals(hjb.getXchcr())) { // 现场核查人
			df.setXchcr(dept);
		}
		if (hjb.getYwjmc() != null && !"".equals(hjb.getYwjmc())) { // 源文件名称
			df.setYwjmc(dept);
		}
		if (hjb.getDzzs() != null && !"".equals(hjb.getDzzs())) { // 电站座数
			df.setDzzs(dept);
		}
		if (hjb.getZhltqk() != null && !"".equals(hjb.getZhltqk())) { // 综合利用情况
			df.setZhltqk(dept);
		}
		if (hjb.getZrk() != null && !"".equals(hjb.getZrk()) && hjb.getZrk() != 0 && hjb.getZrk() != 0.0) { // 总库容（万立方米）
			df.setZrk(dept);
		}
		if (hjb.getBg() != null && !"".equals(hjb.getBg()) && hjb.getBg() != 0 && hjb.getBg() != 0.0) { // 坝高（米）
			df.setBg(dept);
		}
		if (hjb.getSwdj() != null && !"".equals(hjb.getSwdj()) && hjb.getSwdj() != 0 && hjb.getSwdj() != 0.0) { // 上网电价（元/千瓦时）
			df.setSwdj(dept);
		}
		if (hjb.getGhspjwh() != null && !"".equals(hjb.getGhspjwh())) { // 规划审批及文号
			df.setGhspjwh(dept);
		}
		if (hjb.getJsfaspbmjwh() != null && !"".equals(hjb.getJsfaspbmjwh())) { // 技术方案审批部门及文号
			df.setJsfaspbmjwh(dept);
		}
		if (hjb.getPzkmbmjwh() != null && !"".equals(hjb.getPzkmbmjwh())) { // 批准开工部门及文号
			df.setPzkmbmjwh(dept);
		}
		if (hjb.getHpjhbyswh() != null && !"".equals(hjb.getHpjhbyswh())) { // 环评及环保验收文号
			df.setHpjhbyswh(dept);
		}
		if (hjb.getHpjhbyswh() != null && !"".equals(hjb.getHpjhbyswh())) { // 水保及文号
			df.setHpjhbyswh(dept);
		}
		if (hjb.getYdpcjwh() != null && !"".equals(hjb.getYdpcjwh())) { // 用地批准及文号
			df.setYdpcjwh(dept);
		}
		if (hjb.getSfjddzzhwxx() != null && !"".equals(hjb.getSfjddzzhwxx())) { // 是否进行地质灾害危险性评估
			df.setSfjddzzhwxx(dept);
		}
		if (hjb.getYsbmjwh() != null && !"".equals(hjb.getYsbmjwh())) { // 验收部门及文号
			df.setYsbmjwh(dept);
		}
		if (hjb.getXmfrmc() != null && !"".equals(hjb.getXmfrmc())) { // 项目法人名称
			df.setXmfrmc(dept);
		}
		if (hjb.getZytzr() != null && !"".equals(hjb.getZytzr())) { // 主要投资方
			df.setZytzr(dept);
		}
		if (hjb.getLxdh() != null && !"".equals(hjb.getLxdh())) { // 联系电话
			df.setLxdh(dept);
		}
		if (hjb.getBz() != null && !"".equals(hjb.getBz())) { // 备注
			df.setBz(dept);
		}
		if(hjb.getHpsjzytcsj() != null && !"".equals(hjb.getHpsjzytcsj())){
			df.setHpsjzytcsj(dept);
		}
		if(hjb.getSfyjgbg() != null && !"".equals(hjb.getSfyjgbg())){
			df.setSfyjgbg(dept);
		}
		if(hjb.getDztcsjybhqslsjxhgx() != null && !"".equals(hjb.getDztcsjybhqslsjxhgx())){
			df.setDztcsjybhqslsjxhgx(dept);
		}
		sql.append("insert into dz_from (dzbh, xmmc, xmwz, jsdw, dzlxr, lxrdh,xmszly, yjzl, jthl, "+
				"zjrl, ztz, sjnfdl, sjnfdl_xz, tzxz, tzly, bwqk, kffs,qskhdjl, jszt, dysj, ccyy, sfykzstxf, sjfdl,sjfdl_xz, sfyxmhz, hzwjmcjch, "+
				"xmhzdw, sffhgh, ghmc, ghshsj, ghspbm, sffhghhp,ghhpwj, scwjwh, ghhpscbm,sftgxmhp, spwjmc, spwjwh,hpspsj, ppbm, sftgjghbys,"+
				"yswjmc, yswjwh, ysbm, stllxfcs, stlljkss, gycs,zzflcs, qthbcs, qthbcsms,sfsjzrbhq, zrbhqslyj, zrbhqjj,hxq, hcq, sys, wfq,"+
				"bxsfcztsgk, tshdcd, qtsthjwt,tbr, dh, tbsj, xchcr,ywjmc, dzzs, tcny,zhltqk, zrk, bg, swdj,ghspjwh, jsfaspbmjwh, pzkmbmjwh,"+
				"hpjhbyswh, sbjwh, ydpcjwh,sfjddzzhwxx, ysbmjwh, xmfrmc,zytzr, lxdh, bz, hpsjzytcsj, sfyjgbg, dztcsjybhqslsjxhgx)");
		//sql.append(" values("+1+")");
		sql.append(" values");
		sql.append("('"+df.getDzbh()+"', '"+df.getXmmc()+"','"+df.getXmwz()+"','"+df.getJsdw()+"','"+df.getDzlxr()+"',"); // 第一行
		sql.append("'"+df.getLxrdh()+"','"+df.getXmszly()+"','"+df.getYjzl()+"','"+df.getJthl()+"',");
		sql.append("'"+df.getZjrl()+"',"); // 第二行
		sql.append("'"+df.getZtz()+"','"+df.getSjnfdl()+"','"+df.getSjnfdlXz()+"','"+df.getTzxz()+"','"+df.getTzly()+"',");
		sql.append("'"+df.getBwqk()+"','"+df.getKffs()+"','"+df.getQskhdjl()+"','"+df.getJszt()+"','"+df.getDysj()+"',");
		sql.append("'"+df.getCcyy()+"','"+df.getSfykzstxf()+"','"+df.getSjfdl()+"','"+df.getSjfdlXz()+"','"+df.getSfyxmhz()+"',");
		sql.append("'"+df.getHzwjmcjch()+"',");
		sql.append("'"+df.getXmhzdw()+"','"+df.getSffhgh()+"','"+df.getGhmc()+"','"+df.getGhshsj()+"',"); // 第三行
		sql.append("'"+df.getGhspbm()+"','"+df.getSffhghhp()+"','"+df.getGhhpwj()+"','"+df.getScwjwh()+"','"+df.getGhhpscbm()+"',");
		sql.append("'"+df.getSftgxmhp()+"','"+df.getSpwjmc()+"','"+df.getSpwjwh()+"','"+df.getHpspsj()+"','"+df.getPpbm()+"',");
		sql.append("'"+df.getSftgjghbys()+"',");
		sql.append("'"+df.getYswjmc()+"','"+df.getYswjwh()+"','"+df.getYsbm()+"','"+df.getStllxfcs()+"',"); // 第四行
		sql.append("'"+df.getStlljkss()+"','"+df.getGycs()+"','"+df.getZzflcs()+"','"+df.getQthbcs()+"','"+df.getQthbcsms()+"',");
		sql.append("'"+df.getSfsjzrbhq()+"','"+df.getZrbhqslyj()+"','"+df.getZrbhqjj()+"','"+df.getHxq()+"','"+df.getHcq()+"',");
		sql.append("'"+df.getSys()+"','"+df.getWfq()+"',");
		sql.append("'"+df.getBxsfcztsgk()+"','"+df.getTshdcd()+"','"+df.getQtsthjwt()+"',"); // 第五行
		sql.append("'"+df.getTbr()+"','"+df.getDh()+"','"+df.getTbsj()+"','"+df.getXchcr()+"','"+df.getYwjmc()+"','"+df.getDzzs()+"',");
		sql.append("'"+df.getTcny()+"','"+df.getZhltqk()+"','"+df.getZrk()+"','"+df.getBg()+"','"+df.getSwdj()+"','"+df.getGhspjwh()+"',");
		sql.append("'"+df.getJsfaspbmjwh()+"','"+df.getPzkmbmjwh()+"',");
		sql.append("'"+df.getHpjhbyswh()+"','"+df.getSbjwh()+"','"+df.getYdpcjwh()+"',"); // 第六行
		sql.append("'"+df.getSfjddzzhwxx()+"','"+df.getYsbmjwh()+"','"+df.getXmfrmc()+"','"+df.getZytzr()+"','"+df.getLxdh()+"',");
		sql.append("'"+df.getBz()+"','"+df.getHpsjzytcsj()+"','"+df.getSfyjgbg()+"','"+df.getDztcsjybhqslsjxhgx()+"')");
		try {
			int i = this.exectueSQL(sql.toString());
			System.out.println("当前id为"+ hjb.getId()+"成功导入");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据电站查询
	public CeshiSum1 likeXmmc(String xmmc,String sheng,String shi,String xian){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ceshi_sum1 where sheng = '"+sheng+"' and shi = '"+shi+"' and xian='"+xian+"' and xmmc like '"+xmmc+"%'");
		List<CeshiSum1> list = this.getSession().createSQLQuery(sql.toString()).addEntity(CeshiSum1.class).list();
		return list.size()>0?list.get(0):null;
	}

	public List<Map> sele(String sql) {
		List<Map> list = this.createSQLQuerybyMap(sql);
		return list;
	}

	public int deletesum(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM hps_info_sum WHERE id = "+id+"");
		return this.exectueSQL(sql.toString());
	}
}
