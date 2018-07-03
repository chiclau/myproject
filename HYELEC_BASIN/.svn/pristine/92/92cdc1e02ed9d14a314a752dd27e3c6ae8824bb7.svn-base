package com.lyht.business.consumer.hydrologicalData.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Pptn;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.business.search.formBean.SearchFormBean;
import com.lyht.util.CommonUtil;
import com.lyht.util.DateUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class PptnDao extends HibernateBaseDao<Pptn> {

	public List<Map> getPptnListByRain(SearchFormBean searchFormBean) {
		// DATEADD函数（时间类型，小时数，时间）
		StringBuffer sql = new StringBuffer(
				"select a.STCD, CONVERT(varchar(100), a.TM, 120) as TM,a.DRP,a.DYP ,b.STNM,b.LGTD1,b.LTTD1 ,b.STTP  "
						+ "from ST_PPTN_R a left join ST_STBPRP_B b on a.STCD = b.STCD where 1=1 ");
		// 显示实时条件
		if (searchFormBean.getTime() > 0) {
			sql.append(" and a.TM >= DATEADD(hh," + searchFormBean.getTime() * -1 + ",GETDATE()) ");
		}

		// 显示实时条件
		if (CommonUtil.getLength(CommonUtil.trim(searchFormBean.getStcd())) > 0) {
			sql.append(" and a.STCD  = '" + searchFormBean.getStcd() + "' ");
		}

		// 查询开始时间
		if (CommonUtil.getLength(CommonUtil.trim(searchFormBean.getStartTime())) > 0) {
			if (searchFormBean.getConditionalMarkup() == 0) {// 判断前一日后一日标记
				sql.append(" and TM >= '" + searchFormBean.getStartTime() + "'  ");
			} else {
				sql.append(" and a.TM >= DATEADD(dd," + (-7 + searchFormBean.getConditionalMarkup()) + ",GETDATE()) ");
			}
		}
		// 查询结束时间
		if (CommonUtil.getLength(CommonUtil.trim(searchFormBean.getEndTime())) > 0) {// "+searchFormBean.getConditionalMarkup()+"
			if (searchFormBean.getConditionalMarkup() == 0) {
				sql.append(" and TM <= '" + searchFormBean.getEndTime() + "'  ");
			} else {
				sql.append(" and a.TM <= DATEADD(dd," + searchFormBean.getConditionalMarkup() + ",GETDATE()) ");
			}
		}
		sql.append("order by STCD,TM desc");
		System.out.println(sql.toString());
		return this.createSQLQuerybyMap(sql.toString());
	}

	/**
	 * CreateBy :chengdw Date:2018-06-07 Func:获得实时水情数据
	 */
	public List<Map> getPptnListByWater(SearchFormBean searchFormBean) {
		// 查询主语句
		StringBuffer strSql = new StringBuffer(
				" SELECT a.STCD,a.Z,a.Q,CONVERT(varchar(100), a.MODITIME, 120) as TM,b.STNM,b.LGTD1,b.LTTD1,b.STLC ,c.NAME, b.STTP "
						+ " FROM ST_ZQRL_B a LEFT JOIN ST_STBPRP_B b on a.STCD = b.STCD LEFT JOIN SD_ADDVCD_DIC c ON b.ADDVCD = c.ADDVCD where 1=1 ");
		// 显示当天数据
		if (searchFormBean.getStcd() == null) {
			strSql.append(" and DATEDIFF(d,a.MODITIME,GETDATE())=0 ");
		}
		// 根据STCD显示ECharts数据
		if (searchFormBean.getStcd() != null) {
			strSql.append(" and b.STCD = '" + searchFormBean.getStcd() + "' ");
		}
		// 根据省或者流域查询数据
		if (searchFormBean.getAdministrativeRegionBasin() != null) {
			if (!searchFormBean.getProvinceBasin().equals("0") && searchFormBean.getAdministrativeRegionBasin() == 1) {
				strSql.append(" and b.ADDVCD = '" + searchFormBean.getProvinceBasin() + "' ");
			}
			if (!searchFormBean.getProvinceBasin().equals("0") && searchFormBean.getAdministrativeRegionBasin() == 2) {
				strSql.append(" and b.BSNM = '" + searchFormBean.getProvinceBasin() + "' ");
			}
		}
		// 根据站名模糊查询数据
		if (searchFormBean.getStationName() != null && !"".equals(searchFormBean.getStationName().trim())) {
			if(!"undefined".equals(searchFormBean.getStationName())){
				strSql.append(" and b.STNM LIKE '%" + searchFormBean.getStationName().trim() + "%'  ");
			}
		}
		// 根据站类查询数据
		if (searchFormBean.getStation() != null && !"".equals(searchFormBean.getStation())) {
			String[] stations = searchFormBean.getStation().split(",");
			for (String station : stations) {
				strSql.append(" and b.STTP = '" + station + "' ");
			}
		}
		// 查询开始时间
		if (CommonUtil.getLength(CommonUtil.trim(searchFormBean.getStartTime())) > 0) {
			if (searchFormBean.getConditionalMarkup() == 0) {// 判断下一日或者上一日标记
				strSql.append(" and a.MODITIME >= '" + searchFormBean.getStartTime() + "'  ");
			} else {
				if (searchFormBean.getConditionalMarkup() > 0) {
					strSql.append(" and a.MODITIME >= DATEADD(dd," + (-7 + searchFormBean.getConditionalMarkup())
							+ ",GETDATE()) ");
				} else {
					strSql.append(" and a.MODITIME >= DATEADD(dd," + (-7 - searchFormBean.getConditionalMarkup())
							+ ",GETDATE()) ");
				}
			}
			// strSql.append( " and a.MODITIME >=
			// '"+searchFormBean.getStartTime()+"' ");
		}
		// 查询结束时间
		if (CommonUtil.getLength(CommonUtil.trim(searchFormBean.getEndTime())) > 0) {
			if (searchFormBean.getConditionalMarkup() == 0) {
				strSql.append(" and a.MODITIME <= '" + searchFormBean.getEndTime() + "'  ");
			} else {
				strSql.append(
						" and a.MODITIME <= DATEADD(dd," + searchFormBean.getConditionalMarkup() + ",GETDATE()) ");
			}
			// strSql.append( " and a.MODITIME <=
			// '"+searchFormBean.getEndTime()+"' ");
		}
		strSql.append("order by STCD, TM desc");
		// 打印SQL语句
		System.out.println(strSql.toString());
		return this.createSQLQuerybyMap(strSql.toString());
	}

	/**
	 * CreateBy :chengdw Date:2018-06-05 Func:获得实时雨情数据
	 */
	public List<Map> getPptnListByCondition(SearchFormBean searchFormBean) {
		// 主查询语句
		StringBuffer strSql = new StringBuffer(
				"select a.STCD, CONVERT(varchar(100), a.TM, 120) as TM,a.DRP,a.DYP ,a.PDR,b.STNM,b.LGTD1,b.LTTD1 ,b.STLC,c.NAME,b.STTP  "
						+ " from ST_PPTN_R a left join ST_STBPRP_B b on a.STCD = b.STCD LEFT JOIN SD_ADDVCD_DIC c ON b.ADDVCD = c.ADDVCD where 1=1 ");
		// 查询当天数据
		if (searchFormBean.getStcd() == null) {
			strSql.append(" and DATEDIFF(d, TM,GETDATE())=0 ");
		}
		// 根据STCD显示ECharts数据
		if (searchFormBean.getStcd() != null) {
			strSql.append(" and b.STCD = '" + searchFormBean.getStcd() + "' ");
		}
		// 根据省查询数据
		if (searchFormBean.getAdministrativeRegionBasin() != null) {
			if (!searchFormBean.getProvinceBasin().equals("0") && searchFormBean.getAdministrativeRegionBasin() == 1) {
				strSql.append(" and b.ADDVCD = '" + searchFormBean.getProvinceBasin() + "' ");
			}
			if (!searchFormBean.getProvinceBasin().equals("0") && searchFormBean.getAdministrativeRegionBasin() == 2) {
				strSql.append(" and b.BSNM = '" + searchFormBean.getProvinceBasin() + "' ");
			}
		}
		// 查询开始时间
		if (CommonUtil.getLength(CommonUtil.trim(searchFormBean.getStartTime())) > 0) {
			if (searchFormBean.getConditionalMarkup() == 0) {
				strSql.append(" and TM >= '" + searchFormBean.getStartTime() + "'  ");
			} else {
				if (searchFormBean.getConditionalMarkup() > 0) {
					strSql.append(
							" and TM >= DATEADD(dd," + (-7 + searchFormBean.getConditionalMarkup()) + ",GETDATE()) ");
				} else {
					strSql.append(
							" and TM >= DATEADD(dd," + (-7 - searchFormBean.getConditionalMarkup()) + ",GETDATE()) ");
				}
			}
			// strSql.append( " and TM >= '"+searchFormBean.getStartTime()+"'
			// ");
		}
		// 查询结束时间
		if (CommonUtil.getLength(CommonUtil.trim(searchFormBean.getEndTime())) > 0) {
			if (searchFormBean.getConditionalMarkup() == 0) {
				strSql.append(" and TM <= '" + searchFormBean.getEndTime() + "'  ");
			} else {
				strSql.append(" and TM <= DATEADD(dd," + searchFormBean.getConditionalMarkup() + ",GETDATE()) ");
			}
			// strSql.append( " and TM <= '"+searchFormBean.getEndTime()+"' ");
		}
		// 根据站名模糊查询
		if (searchFormBean.getStationName() != null && !"".equals(searchFormBean.getStationName().trim())) {
			strSql.append(" and b.STNM LIKE '%" + searchFormBean.getStationName().trim() + "%'  ");
		}
		strSql.append(" order by STCD ,TM desc ");
		// 打印SQL语句
		System.out.println(strSql.toString());
		return this.createSQLQuerybyMap(strSql.toString());
	}

	/**
	 * CreateBy :chengdw Date:2018-06-05 Func:获得省或者区域数据
	 */
	public List getPptnBasinProvince(SearchFormBean searchFormBean) {
		StringBuffer strSql = null;
		if (searchFormBean.getAdministrativeRegionBasin() == 1) {
			strSql = new StringBuffer("SELECT ADDVCD AS id,NAME as RVNM FROM SD_ADDVCD_DIC where PADDVCD = 0 ");
		}
		if (searchFormBean.getAdministrativeRegionBasin() == 2) {
			strSql = new StringBuffer("SELECT id,RVNM FROM SD_LYSXJGX");
		}
		strSql.append(" order by id desc ");
		return this.createSQLQuerybyMap(strSql.toString());
	}

	/**
	 * 获取降水量列表数据
	 */
	public PageResults getPptnListData(PptnFormBean mPptnFormBean) {
		StringBuffer sql = new StringBuffer();
		/*String str = spliceStrPptn(mPptnFormBean);*/
		sql.append(
				"SELECT ppth.STCD,ppth.TM,ppth.DRP,ppth.INTV,ppth.PDR,ppth.DYP,ppth.WTH,b.STNM FROM ST_PPTN_R AS ppth ");
		sql.append(" LEFT JOIN ST_STBPRP_B AS b ON b.STCD = ppth.STCD WHERE 1=1 ");
		//模糊查询
		if(mPptnFormBean.getSearchName() != null && !"".equals(mPptnFormBean.getSearchName().trim())){
			sql.append(" and b.STNM LIKE '%" + mPptnFormBean.getSearchName().trim() + "%'  ");
			String searchName = mPptnFormBean.getSearchName();
			//判断是否包含天气
			if(!searchName.equals("雨夹雪")){
				if(searchName.contains("雪")){
					sql.append(" or WTH = '"+ 5 +"'  ");
				}
				if(searchName.contains("雨")){
					sql.append(" or WTH = '"+ 7 +"'  ");
				}
			}
			if(searchName.contains("阴")){
				sql.append(" or WTH = '"+ 8 +"'  ");
			}
			if(searchName.contains("晴")){
				sql.append(" or WTH = '"+ 9 +"'  ");
			}
			if(searchName.contains("雨") && searchName.contains("雪")){
				sql.append(" or WTH = '"+ 6 +"'  ");
			}
		}
		//根据测站查询
		if(mPptnFormBean.getStcd() != null && !"".equals(mPptnFormBean.getStcd().trim())){
			sql.append(" and b.STCD='"+mPptnFormBean.getStcd()+" '");
		}
		//添加开始查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mPptnFormBean.getStartTime())) > 0){
			sql.append(" and TM >= '" + mPptnFormBean.getStartTime() + "'  ");
		}
		//添加结束查询时间
		if(CommonUtil.getLength(CommonUtil.trim(mPptnFormBean.getEndTime())) > 0){
			sql.append(" and TM <= '" + mPptnFormBean.getEndTime() + "'  ");
		}
		sql.append(" ORDER BY ppth.STCD , TM");
		System.out.println(sql.toString());
		return this.findPageByFetchedSql(sql.toString(), null, mPptnFormBean.getPageBean().getOffset(),
				mPptnFormBean.getPageBean().getLimit(), null);
	}

	/**
	 * 根据主键ID删除降水量实体
	 */
	public void deletPptnInfoByIds(String ids, String ids_) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM ST_PPTN_R WHERE STCD = '" + ids + "' AND TM='" + ids_ + "'");
		this.exectueSQL(sql.toString());
	}

	/**
	 * 增加实体对象
	 */
	public void savePptnInfo(Pptn mPptn) {
		String tm = mPptn.getTm();
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ST_PPTN_R (STCD,TM,DRP,INTV,PDR,DYP,WTH) VALUES ");
		sql.append("('" + CommonUtil.trim(mPptn.getStcd()) + "','" + CommonUtil.trim(DateUtil.ConvertTimestamp(tm))
				+ "',");
		sql.append("'" + CommonUtil.trim(mPptn.getDrp()) + "','" + CommonUtil.trim(mPptn.getIntv()) + "',");
		sql.append("'" + CommonUtil.trim(mPptn.getPdr()) + "','" + CommonUtil.trim(mPptn.getDyp()) + "',");
		sql.append("'" + CommonUtil.trim(mPptn.getWth()) + "')");
		this.exectueSQL(sql.toString());
	}

	/**
	 * 修改实体对象
	 */
	public void updatePptnInfo(Pptn mPptn) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ST_PPTN_R SET STCD='" + mPptn.getStcd() + "',TM='" + mPptn.getTm() + "',");
		sql.append("DRP='" + mPptn.getDrp() + "',INTV='" + mPptn.getIntv() + "',");
		sql.append("PDR='" + mPptn.getPdr() + "',DYP='" + mPptn.getDyp() + "',WTH='" + mPptn.getWth() + "' ");
		sql.append("WHERE STCD='" + mPptn.getStcd() + "' AND TM='" + mPptn.getTm() + "'");
		this.exectueSQL(sql.toString());
	}

	/**
	 * 根据主键ID获取实体
	 */
	@SuppressWarnings("unchecked")
	public Pptn getPptnInfoById(PptnFormBean mPptnFormBean) {
		Pptn mPptn = new Pptn();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ltrim(rtrim(STCD)) AS STCD,TM,DRP,INTV,PDR,");
		sql.append("DYP,WTH FROM ST_PPTN_R WHERE STCD='" + mPptnFormBean.getmPptnInfoBean().getStcd() + "' ");
		sql.append("AND TM='" + mPptnFormBean.getmPptnInfoBean().getTm() + "'");
		List<Pptn> mPptnList = null;
		try {
			mPptnList = this.getSession().createSQLQuery(sql.toString()).addEntity(Pptn.class).list();
			for (int i = 0; i < mPptnList.size(); i++) {
				mPptn = mPptnList.get(0);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return mPptn;
	}

	/**
	 * 根据条件查询降水量
	 */
	private String spliceStrPptn(PptnFormBean mPptnFormBean) {
		StringBuffer sql = new StringBuffer();
		if (null != mPptnFormBean) {
			if (CommonUtil.trim(mPptnFormBean.getSearchName()).length() > 0) {
				sql.append("AND ((ppth.STCD LIKE '%" + CommonUtil.trim(mPptnFormBean.getSearchName()) + "%') ");
				sql.append("OR (ppth.TM LIKE '%" + CommonUtil.trim(mPptnFormBean.getSearchName()) + "%') ");
				sql.append("OR (ppth.DRP LIKE '%" + CommonUtil.trim(mPptnFormBean.getSearchName()) + "%') ");
				sql.append("OR (ppth.INTV LIKE '%" + CommonUtil.trim(mPptnFormBean.getSearchName()) + "%') ");
				sql.append("OR (ppth.PDR LIKE '%" + CommonUtil.trim(mPptnFormBean.getSearchName()) + "%') ");
				sql.append("OR (ppth.DYP LIKE '%" + CommonUtil.trim(mPptnFormBean.getSearchName()) + "%') ");
				sql.append("OR (STNM LIKE '%" + CommonUtil.trim(mPptnFormBean.getSearchName()) + "%')) ");
			}
			if (null != mPptnFormBean.getmPptnInfoBean()) {
				if (CommonUtil.trim(mPptnFormBean.getmPptnInfoBean().getStcd()).length() > 0) {
					sql.append(" AND ppth.STCD LIKE '%" + CommonUtil.trim(mPptnFormBean.getmPptnInfoBean().getStcd())
							+ "%'");
				}
				if (CommonUtil.trim(mPptnFormBean.getmPptnInfoBean().getTm()).length() > 0) {
					sql.append(
							" AND ppth.TM LIKE '%" + CommonUtil.trim(mPptnFormBean.getmPptnInfoBean().getTm()) + "%'");
				}
			}
		}
		return sql.toString();
	}

}
