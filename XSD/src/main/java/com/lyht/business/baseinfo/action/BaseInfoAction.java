package com.lyht.business.baseinfo.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyht.business.baseinfo.dao.*;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.baseinfo.service.AreaService;
import com.lyht.business.baseinfo.service.BaseInfoService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.RandomValidateCodeUtil;
import com.lyht.util.Randomizer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import static javax.xml.bind.DatatypeConverter.parseTime;

/**
 * 基础信息Action
 *
 * @author 刘魁
 * @创建时间 2018/10/08
 */
@Namespace("/jcxx")
@Controller
@Scope("prototype")
@Action("/jcxx")
@SuppressWarnings("all")
public class BaseInfoAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger("BasceInfoAction");

	@Resource
	private BaseInfoService baseInfoService;
	@Resource
	private AreaService areaService;
	@Resource
	private BaseInfoDao dao;

	private String address;// 查询地址参数

	private String bj;// 参数标记0全国，1省，2,市，3县

	private String css;// 参数标记1倒序，2正序
	private int page;
	private int limit;
	private String name;
	private int tab; // 参数代表第几个表
	
	public String  getVerify() {
		log.info("BaseInfoAction=生成验证码");
			try {
			this.getResponse().setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
			this.getResponse().setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
			this.getResponse().setHeader("Cache-Control", "no-cache");
			this.getResponse().setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			randomValidateCode.getRandcode(getRequest(), getResponse());//输出验证码图片方法
			} catch (Exception e) {
			log.error("获取验证码失败>>>> ", e);
			}
			return null;
		}
	
	// 基础信息表格切换
	public String countTbzh() {
		log.info("BaseInfoAction=countTbzh:基础信息表格切换");
		String xzqhdm = this.getRequest().getParameter("xzlyqh");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		List<Map> list = baseInfoService.countTbzh(bj, xzqhdm);
		mHashMap.put("rows", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String getZj() {
		log.info("BaseInfoAction=getZj:切换表格总计");
		String xzqhdm = this.getRequest().getParameter("xzlyqh");
		String pid = this.getRequest().getParameter("parentId");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			mHashMap = baseInfoService.queryBaseInfo(xzqhdm);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	public String list() {
		log.info("BaseInfoAction=list:点击Echarts查询");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String sheng = this.getRequest().getParameter("sheng");
		String shi = this.getRequest().getParameter("shi");
		String xian = this.getRequest().getParameter("xian");
		String seriesName = this.getRequest().getParameter("seriesName");
		PageResults dzListData = baseInfoService.getDzListData(bj, name, sheng,shi,xian, page, limit, tab,seriesName);
		if (dzListData == null) {
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);
		} else {
			mHashMap.put("code", 0);
			mHashMap.put("msg", "");
			mHashMap.put("count", dzListData.getTotalCount());
			mHashMap.put("data", dzListData.getResults());
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}

	public String countJszt() {
		log.info("BaseInfoAction=countJszt:统计全国建设状态");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		List<Map> list = baseInfoService.countJszt(address, bj);
		mHashMap.put("rows", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String countJszt1() {
		String sheng = this.getRequest().getParameter("sheng");
		String shi = this.getRequest().getParameter("shi");
		String xian = this.getRequest().getParameter("xian");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		List<Map> list = baseInfoService.countJszt(sheng,shi,xian);
		mHashMap.put("rows", list);
		log.info("BaseInfoAction=countJszt:统计建设状态  :省"+sheng+",市："+shi+"，县："+xian+"");
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String countKffs() {
		log.info("BaseInfoAction=countKffs:统计全国开发方式");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		List<Map> list = baseInfoService.countKffs(address, bj);
		mHashMap.put("rows", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String countKffs1() {
		String sheng = this.getRequest().getParameter("sheng");
		String shi = this.getRequest().getParameter("shi");
		String xian = this.getRequest().getParameter("xian");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		List<Map> list = baseInfoService.countKffs(sheng,shi,xian);
		mHashMap.put("rows", list);
		log.info("BaseInfoAction=countJszt:统计开发方式  :省"+sheng+",市："+shi+"，县："+xian+"");
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	public String countDz(){
		log.info("BaseInfoAction=countDz:统计全国电站数量");
		String xzqhdm = this.getRequest().getParameter("xzlyqh");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		List<Map> list = baseInfoService.countDz(xzqhdm,bj);
		mHashMap.put("rows", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 基础查询页面根据用户点击Echarts统计不同数据
	 *
	 * @return
	 */
	public String countSdzByTj() {
		log.info("BaseInfoAction=countSdzByTj:基础查询页面根据用户点击Echarts统计不同数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String xzqhdm = this.getRequest().getParameter("xzlyqh");
		try {
			List<Map> list = baseInfoService.countSdzByTj(xzqhdm, name, bj);
			mHashMap.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("message", "查询数据出错!");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}

	public String queryJcxxData() {
		log.info("BaseInfoAction=list:基础信息");
		String xzqhdm = this.getRequest().getParameter("xzlyqh");
		String pid = this.getRequest().getParameter("parentId");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			mHashMap = baseInfoService.queryBaseInfo(xzqhdm);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	
	
	public String queryJcxxData1() {
		log.info("BaseInfoAction=list:基础信息");
	
		String sheng = this.getRequest().getParameter("sheng");
		String shi = this.getRequest().getParameter("shi");
		String xian = this.getRequest().getParameter("xian");
		// String xzqhdm="";
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			mHashMap = baseInfoService.queryBaseInfo1(sheng,shi,xian);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}
	public String getZtree() {
		log.info("BaseInfoAction=list:基础信息-获取ztree");
		String xzqhdm = this.getRequest().getParameter("xzlyqh");
		String pid = this.getRequest().getParameter("parentId");
		// String xzqhdm="";
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			List list = areaService.getData(pid);
			mHashMap.put("treeData", list);
			mHashMap.put("reflag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			mHashMap.put("reflag", "0");
			mHashMap.put("message", "查询数据出错!");
		}
		JSONObject json = JSONObject.fromObject(mHashMap);
		CommonFunction.writeResponse(getResponse(), json);
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getBj() {
		return bj;
	}

	public String getName() {
		return name;
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	public void test() {
		String string = "getSlbDzz";
		string = string.substring(3);
		int b = 0;
		char[] chars = string.toCharArray();
		String str = "";
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] >= 'A' && chars[i] <= 'Z') {
				b++;
				if (b < 2) {
					str += chars[i];
				} else {
					str += "_" + chars[i];
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
		System.out.println(str.toLowerCase());
	}

	public void Test1() {
		List<nyjInfoBean> hjbs = dao.selectnyj(); // 查询环境部数据
		int success = 0;
		int error = 0;
		int successDzFormId = 0;
		List errorId = new ArrayList<>();
		List updateDzFromId = new ArrayList<>();
		
		int CT= hjbs.size();
		for (int x = 0; x < CT; x++) { // 循环第环境部
			
			
			
			nyjInfoBean hjb = hjbs.get(x); // 获取对象
			String xmmc_fu = hjb.getXmmc(); // 项目名称
			String sheng_fu = hjb.getSheng(); // 获取其他表的省
			String shi_fu = hjb.getShi(); // 获取主其他表的市
			String xian_fu = hjb.getXian(); // 获取其他主表的县
			Float zjrl_fu = hjb.getZjrl(); // 获取其他表装机容量

			if (xmmc_fu != null && !"".equals(xmmc_fu) && xmmc_fu.indexOf("电站") != -1) {
				if (xmmc_fu.substring(0, xmmc_fu.length() - 3).equals("水电站")) {
					xmmc_fu = xmmc_fu.substring(0, xmmc_fu.length() - 3);
				} // 红河水电站 截掉水电站留下红河
				if (xmmc_fu.substring(0, xmmc_fu.length() - 2).equals("电站")) {
					xmmc_fu = xmmc_fu.substring(0, xmmc_fu.length() - 2);
				} // 红河电站 截掉电站留下红河

			}
			CeshiSum1 chongfu = dao.likeXmmc(xmmc_fu, sheng_fu, shi_fu, xian_fu);
			
			String tempstr=x+" / "+CT+ "项目名称："+xmmc_fu+" 查找到：";
			if (chongfu != null) tempstr = tempstr+chongfu.getXmmc();
			
			System.out.println(tempstr);
			
			// 根据省，市，县，电站名称判断电站是否为同一个电站
			if (chongfu == null) {
				hjb.setDzbh(Randomizer.generCode(16));// 证明不是同一个电站（插入操作）
				if (hjb.getDysj() == null || "".equals(hjb.getDysj())) {
					hjb.setDysj("1900-01-01");
				}
				if (hjb.getGhshsj() == null || "".equals(hjb.getGhshsj())) {
					hjb.setGhshsj("1900-01-01");
				}
				if (hjb.getHpspsj() == null || "".equals(hjb.getHpspsj())) {
					hjb.setHpspsj("1900-01-01");
				}
				if (hjb.getZrbhqslyj() == null || "".equals(hjb.getZrbhqslyj())) {
					hjb.setZrbhqslyj("1900-01-01");
				}
				if (hjb.getTbsj() == null || "".equals(hjb.getTbsj())) {
					hjb.setTbsj("1900-01-01");
				}
				if (hjb.getTcny() == null || "".equals(hjb.getTcny())) {
					hjb.setTcny("1900-01-01");
				}

				boolean b = dao.insertCeshiSum(hjb);
				if (b) {
					success++;
				} else {
					error++;
					errorId.add(hjb.getXmmc());
					System.err.println("失败的电站名称是" + hjb.getXmmc());
				}

				System.out.println("当前成功:" + success + "条,失败:" + error + "条");
				System.err.println("错误的电站名称有" + errorId.toString());
			} else {
				// 证明是同一个电站（修改操作）优先判断 装机容量(zjrl)，经度(dj)， 纬度(bw)，总投资(ztz)，
				// 设计年发电量，设计年发电量- 修正，对应时间(dysj)，
				// 设计发电量，设计发电量- 修正，填表时间(tbsj)，投产年月(tcny)
				StringBuilder sql = new StringBuilder();
				
				
				String dzbh = chongfu.getDzbh();
				if (chongfu.getDj() == null || chongfu.getDj() == 0.0) { // 其他表经度(不是null或者0.0就直接覆盖)
					//sql.append(dao.updateTab("ceshi_sum1", "getDj", hjb.getDj(), dzbh));
					sql.append(dao.updateTab("ceshi_sum1", "getDj", hjb.getDj(), dzbh));

				}
				if (chongfu.getBw() == null || "".equals(chongfu.getBw()) || chongfu.getBw() == 0.0) { // 其他表纬度
					//sql.append(dao.updateTab("ceshi_sum1", "getBw", hjb.getBw(), dzbh));
					sql.append(dao.updateTab("ceshi_sum1", "getBw", hjb.getBw(), dzbh));

				}
				if (chongfu.getXmwz() == null || "".equals(chongfu.getXmwz())) { // 项目位置
					sql.append(dao.updateTab("ceshi_sum1", "getXmwz", hjb.getXmwz(), dzbh));
					sql.append(updateDzFrom( dzbh, "getXmwz"));

				}
				if (chongfu.getJsdw() == null || "".equals(chongfu.getJsdw())) { // 建设（运营）单位
					sql.append(dao.updateTab("ceshi_sum1", "getJsdw", hjb.getJsdw(), dzbh));
					sql.append(updateDzFrom( dzbh, "getJsdw"));

				}
				if (chongfu.getDzlxr() == null || "".equals(chongfu.getDzlxr())) { // 电站联系人
					sql.append(dao.updateTab("ceshi_sum1", "getDzlxr", hjb.getDzlxr(), dzbh));
					sql.append(updateDzFrom( dzbh, "getDzlxr"));

				}
				if (chongfu.getLxrdh() == null || "".equals(chongfu.getLxrdh())) { // 联系人电话
					sql.append(dao.updateTab("ceshi_sum1", "getLxrdh", hjb.getLxrdh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getLxrdh"));

				}
				if (chongfu.getXmszly() == null || "".equals(chongfu.getXmszly())) { // 项目所在流域
					sql.append(dao.updateTab("ceshi_sum1", "getXmszly", hjb.getXmszly(), dzbh));
					sql.append(updateDzFrom( dzbh, "getXmszly"));

				}
				if (chongfu.getYjzl() == null || "".equals(chongfu.getYjzl())) { // 一级支流
					sql.append(dao.updateTab("ceshi_sum1", "getYjzl", hjb.getYjzl(), dzbh));
					sql.append(updateDzFrom( dzbh, "getYjzl"));

				}
				if (chongfu.getJthl() == null || "".equals(chongfu.getJthl())) { // 具体河流
					sql.append(dao.updateTab("ceshi_sum1", "getJthl", hjb.getJthl(), dzbh));
					sql.append(updateDzFrom( dzbh, "getJthl"));

				}
				if (chongfu.getZjrl()==null || chongfu.getZjrl() == 0.0) { // 说明主表zjrl小取值大的
					sql.append(dao.updateTab("ceshi_sum1", "getZjrl", zjrl_fu, dzbh));
					sql.append(updateDzFrom( dzbh, "getZjrl"));

				}
				if (chongfu.getZtz() == null || chongfu.getZtz() == 0.0) { // 其他表总投资
					sql.append(dao.updateTab("ceshi_sum1", "getZtz", hjb.getZtz(), dzbh));
					sql.append(updateDzFrom( dzbh, "getZtz"));

				}
				if (chongfu.getTzxz() == null || "".equals(chongfu.getTzxz())) { // 投资性质（所有制）
					sql.append(dao.updateTab("ceshi_sum1", "getTzxz", hjb.getTzxz(), dzbh));
					sql.append(updateDzFrom( dzbh, "getTzxz"));

				}
				if (chongfu.getTzly() == null || "".equals(chongfu.getTzly())) { // 投资来源
					sql.append(dao.updateTab("ceshi_sum1", "getTzly", hjb.getTzly(), dzbh));
					sql.append(updateDzFrom( dzbh, "getTzly"));

				}
				if (chongfu.getBwqk() == null || "".equals(chongfu.getBwqk())) { // 并网情况
					sql.append(dao.updateTab("ceshi_sum1", "getBwqk", hjb.getBwqk(), dzbh));
					sql.append(updateDzFrom( dzbh, "getBwqk"));

				}
				if (chongfu.getKffs() == null || "".equals(chongfu.getKffs())) { // 开发方式
					sql.append(dao.updateTab("ceshi_sum1", "getKffs", hjb.getKffs(), dzbh));
					sql.append(updateDzFrom( dzbh, "getKffs"));

				}
				if (chongfu.getQskhdjl() == null || "".equals(chongfu.getQskhdjl())) { // 取水口河道距离
					sql.append(dao.updateTab("ceshi_sum1", "getQskhdjl", hjb.getQskhdjl(), dzbh));
					sql.append(updateDzFrom( dzbh, "getQskhdjl"));

				}
				if (chongfu.getJszt() == null || "".equals(chongfu.getJszt())) { // 建设（运营）状态
					sql.append(dao.updateTab("ceshi_sum1", "getJszt", hjb.getJszt(), dzbh));
					sql.append(updateDzFrom( dzbh, "getJszt"));

				}
				if ("1900-01-01".equals(chongfu.getDysj())) { // 对应日期
					sql.append(dao.updateTab("ceshi_sum1", "getDysj", hjb.getDysj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getDysj"));

				}
				if (chongfu.getCcyy() == null || "".equals(chongfu.getCcyy())) { // 拆除原因
					sql.append(dao.updateTab("ceshi_sum1", "getCcyy", hjb.getCcyy(), dzbh));
					sql.append(updateDzFrom( dzbh, "getCcyy"));

				}
				if (chongfu.getSfykzstxf() == null || "".equals(chongfu.getSfykzstxf())) { // 是否已开展生态修复
					sql.append(dao.updateTab("ceshi_sum1", "getSfykzstxf", hjb.getSfykzstxf(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSfykzstxf"));

				}
				if (chongfu.getSjnfdl() == null || chongfu.getSjnfdl() == 0.0) { // 设计年发电量
					sql.append(dao.updateTab("ceshi_sum1", "getSjnfdl", hjb.getSjnfdl(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSjnfdl"));

				}
				if (chongfu.getSjnfdlXz() == null || chongfu.getSjnfdlXz() == 0.0) { // 设计年发电量—修正
					sql.append(dao.updateTab("ceshi_sum1", "getSjnfdlXz", hjb.getSjnfdlXz(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSjnfdlXz"));

				}
				if (chongfu.getSfyxmhz() == null || "".equals(chongfu.getSfyxmhz())) { // 是否有项目核准（审批）
					sql.append(dao.updateTab("ceshi_sum1", "getSfyxmhz", hjb.getSfyxmhz(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSfyxmhz"));

				}
				if (chongfu.getHzwjmcjch() == null || "".equals(chongfu.getHzwjmcjch())) { // 核准（审批）文件名称及文号
					sql.append(dao.updateTab("ceshi_sum1", "getHzwjmcjch", hjb.getHzwjmcjch(), dzbh));
					sql.append(updateDzFrom( dzbh, "getHzwjmcjch"));

				}
				if (chongfu.getXmhzdw() == null || "".equals(chongfu.getXmhzdw())) { // 项目核准（审批）部门
					sql.append(dao.updateTab("ceshi_sum1", "getHzwjmcjch", hjb.getHzwjmcjch(), dzbh));
					sql.append(updateDzFrom( dzbh, "getHzwjmcjch"));

				}
				if (chongfu.getSffhgh() == null || "".equals(chongfu.getSffhgh())) { // 是否符合规划
					sql.append(dao.updateTab("ceshi_sum1", "getSffhgh", hjb.getSffhgh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSffhgh"));

				}
				if (chongfu.getGhmc() == null || "".equals(chongfu.getGhmc())) { // 规划名称
					sql.append(dao.updateTab("ceshi_sum1", "getGhmc", hjb.getGhmc(), dzbh));
					sql.append(updateDzFrom( dzbh, "getGhmc"));

				}
				if ("1900-01-01".equals(chongfu.getGhshsj())) { // 规划审批时间
					sql.append(dao.updateTab("ceshi_sum1", "getGhshsj", hjb.getGhshsj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getGhshsj"));

				}
				if (chongfu.getGhspbm() == null || "".equals(chongfu.getGhspbm())) { // 规划审批部门
					sql.append(dao.updateTab("ceshi_sum1", "getGhspbm", hjb.getGhspbm(), dzbh));
					sql.append(updateDzFrom( dzbh, "getGhspbm"));

				}
				if (chongfu.getSffhghhp() == null || "".equals(chongfu.getSffhghhp())) { // 是否符合规划环评
					sql.append(dao.updateTab("ceshi_sum1", "getSffhghhp", hjb.getSffhghhp(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSffhghhp"));

				}
				if (chongfu.getGhhpwj() == null || "".equals(chongfu.getGhhpwj())) { // 规划环评文件
					sql.append(dao.updateTab("ceshi_sum1", "getGhhpwj", hjb.getGhhpwj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getGhhpwj"));

				}
				if (chongfu.getScwjwh() == null || "".equals(chongfu.getScwjwh())) { // 审查文件文号
					sql.append(dao.updateTab("ceshi_sum1", "getScwjwh", hjb.getScwjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getScwjwh"));

				}
				if (chongfu.getGhhpscbm() == null || "".equals(chongfu.getGhhpscbm())) { // 规划环评审查部门
					sql.append(dao.updateTab("ceshi_sum1", "getGhhpscbm", hjb.getGhhpscbm(), dzbh));
					sql.append(updateDzFrom( dzbh, "getGhhpscbm"));

				}
				if (chongfu.getSftgxmhp() == null || "".equals(chongfu.getSftgxmhp())) { // 是否通过项目环评
					sql.append(dao.updateTab("ceshi_sum1", "getSftgxmhp", hjb.getSftgxmhp(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSftgxmhp"));

				}
				if (chongfu.getSpwjmc() == null || "".equals(chongfu.getSpwjmc())) { // 审批文件名称
					sql.append(dao.updateTab("ceshi_sum1", "getSpwjmc", hjb.getSpwjmc(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSpwjmc"));

				}
				if (chongfu.getSpwjwh() == null || "".equals(chongfu.getSpwjwh())) { // 审批文件文号
					sql.append(dao.updateTab("ceshi_sum1", "getSpwjwh", hjb.getSpwjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSpwjwh"));

				}
				if ("1900-01-01".equals(chongfu.getHpspsj())) { // 环评审批时间
					sql.append(dao.updateTab("ceshi_sum1", "getHpspsj", hjb.getHpspsj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getHpspsj"));

				}
				if (chongfu.getPpbm() == null || "".equals(chongfu.getPpbm())) { // 审批部门
					sql.append(dao.updateTab("ceshi_sum1", "getPpbm", hjb.getPpbm(), dzbh));
					sql.append(updateDzFrom( dzbh, "getPpbm"));

				}
				if (chongfu.getSftgjghbys() == null || "".equals(chongfu.getSftgjghbys())) { // 是否通过竣工环保验收
					sql.append(dao.updateTab("ceshi_sum1", "getSftgjghbys", hjb.getSftgjghbys(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSftgjghbys"));

				}
				if (chongfu.getYswjmc() == null || "".equals(chongfu.getYswjmc())) { // 验收文件名称
					sql.append(dao.updateTab("ceshi_sum1", "getYswjmc", hjb.getYswjmc(), dzbh));
					sql.append(updateDzFrom( dzbh, "getYswjmc"));

				}
				if (chongfu.getYswjwh() == null || "".equals(chongfu.getYswjwh())) { // 验收文件文号
					sql.append(dao.updateTab("ceshi_sum1", "getYswjwh", hjb.getYswjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getYswjwh"));

				}
				if (chongfu.getYsbm() == null || "".equals(chongfu.getYsbm())) { // 验收部门
					sql.append(dao.updateTab("ceshi_sum1", "getYsbm", hjb.getYsbm(), dzbh));
					sql.append(updateDzFrom( dzbh, "getYsbm"));

				}
				if (chongfu.getStllxfcs() == null || "".equals(chongfu.getStllxfcs())) { // 生态流量泄放措施
					sql.append(dao.updateTab("ceshi_sum1", "getStllxfcs", hjb.getStllxfcs(), dzbh));
					sql.append(updateDzFrom( dzbh, "getStllxfcs"));

				}
				if (chongfu.getStllxfcs() == null || "".equals(chongfu.getStllxfcs())) { // 生态流量监控设施
					sql.append(dao.updateTab("ceshi_sum1", "getStllxfcs", hjb.getStllxfcs(), dzbh));
					sql.append(updateDzFrom( dzbh, "getStllxfcs"));

				}
				if (chongfu.getGycs() == null || "".equals(chongfu.getGycs())) { // 过鱼措施
					sql.append(dao.updateTab("ceshi_sum1", "getGycs", hjb.getGycs(), dzbh));
					sql.append(updateDzFrom( dzbh, "getGycs"));

				}
				if (chongfu.getZzflcs() == null || "".equals(chongfu.getZzflcs())) { // 增殖放流措施
					sql.append(dao.updateTab("ceshi_sum1", "getZzflcs", hjb.getZzflcs(), dzbh));
					sql.append(updateDzFrom( dzbh, "getZzflcs"));

				}
				if (chongfu.getQthbcs() == null || "".equals(chongfu.getQthbcs())) { // 其他环保措施
					sql.append(dao.updateTab("ceshi_sum1", "getQthbcs", hjb.getQthbcs(), dzbh));
					sql.append(updateDzFrom( dzbh, "getQthbcs"));

				}
				if (chongfu.getQthbcsms() == null || "".equals(chongfu.getQthbcsms())) { // 其他环保措施描述
					sql.append(dao.updateTab("ceshi_sum1", "getQthbcsms", hjb.getQthbcsms(), dzbh));
					sql.append(updateDzFrom( dzbh, "getQthbcsms"));

				}
				if (chongfu.getSfsjzrbhq() == null || "".equals(chongfu.getSfsjzrbhq())) { // 是否涉及自然保护区
					sql.append(dao.updateTab("ceshi_sum1", "getSfsjzrbhq", hjb.getSfsjzrbhq(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSfsjzrbhq"));

				}
				if ("1900-01-01".equals(chongfu.getZrbhqslyj())) { // 自然保护区设立时间
					sql.append(dao.updateTab("ceshi_sum1", "getZrbhqslyj", hjb.getZrbhqslyj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getZrbhqslyj"));

				}
				if (chongfu.getZrbhqjj() == null || "".equals(chongfu.getZrbhqjj())) { // 自然保护区级别
					sql.append(dao.updateTab("ceshi_sum1", "getZrbhqjj", hjb.getZrbhqjj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getZrbhqjj"));

				}
				if (chongfu.getHxq() == null || "".equals(chongfu.getHxq())) { // 核心区
					sql.append(dao.updateTab("ceshi_sum1", "getHxq", hjb.getHxq(), dzbh));
					sql.append(updateDzFrom( dzbh, "getHxq"));

				}
				if (chongfu.getHcq() == null || "".equals(chongfu.getHcq())) { // 缓冲区
					sql.append(dao.updateTab("ceshi_sum1", "getHcq", hjb.getHcq(), dzbh));
					sql.append(updateDzFrom( dzbh, "getHcq"));

				}
				if (chongfu.getSys() == null || "".equals(chongfu.getSys())) { // 实验区
					sql.append(dao.updateTab("ceshi_sum1", "getSys", hjb.getSys(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSys"));

				}
				if (chongfu.getWfq() == null || "".equals(chongfu.getWfq())) { // 未分区
					sql.append(dao.updateTab("ceshi_sum1", "getWfq", hjb.getWfq(), dzbh));
					sql.append(updateDzFrom( dzbh, "getWfq"));

				}
				if (chongfu.getBxsfcztsgk() == null || "".equals(chongfu.getBxsfcztsgk())) { // 坝下是否存在脱水干涸
					sql.append(dao.updateTab("ceshi_sum1", "getBxsfcztsgk", hjb.getBxsfcztsgk(), dzbh));
					sql.append(updateDzFrom( dzbh, "getBxsfcztsgk"));

				}
				if (chongfu.getTshdcd() == null || "".equals(chongfu.getTshdcd())) { // 脱水河段长度（公里）
					sql.append(dao.updateTab("ceshi_sum1", "getTshdcd", hjb.getTshdcd(), dzbh));
					sql.append(updateDzFrom( dzbh, "getTshdcd"));

				}
				if (chongfu.getQtsthjwt() == null || "".equals(chongfu.getQtsthjwt())) { // 其他生态环境问题
					sql.append(dao.updateTab("ceshi_sum1", "getQtsthjwt", hjb.getQtsthjwt(), dzbh));
					sql.append(updateDzFrom( dzbh, "getQtsthjwt"));

				}
				if (chongfu.getTbr() == null || "".equals(chongfu.getTbr())) { // 填表人
					sql.append(dao.updateTab("ceshi_sum1", "getTbr", hjb.getTbr(), dzbh));
					sql.append(updateDzFrom( dzbh, "getTbr"));

				}
				if (chongfu.getDh() == null || "".equals(chongfu.getDh())) { // 电话
					sql.append(dao.updateTab("ceshi_sum1", "getDh", hjb.getDh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getDh"));

				}
				if ("1900-01-01".equals(chongfu.getDysj())) { // 填表日期
					sql.append(dao.updateTab("ceshi_sum1", "getDysj", hjb.getDysj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getDysj"));

				}
				if (chongfu.getXchcr() == null || "".equals(chongfu.getXchcr())) { // 现场核查人
					sql.append(dao.updateTab("ceshi_sum1", "getXchcr", hjb.getXchcr(), dzbh));
					sql.append(updateDzFrom( dzbh, "getXchcr"));

				}
				if (chongfu.getYwjmc() == null || "".equals(chongfu.getYwjmc())) { // 源文件名称
					sql.append(dao.updateTab("ceshi_sum1", "getYwjmc", hjb.getYwjmc(), dzbh));
					sql.append(updateDzFrom( dzbh, "getYwjmc"));

				}
				if (chongfu.getDzzs() == null || "".equals(chongfu.getDzzs())) { // 电站座数
					sql.append(dao.updateTab("ceshi_sum1", "getDzzs", hjb.getDzzs(), dzbh));
					sql.append(updateDzFrom( dzbh, "getDzzs"));

				}
				if (chongfu.getZhltqk() == null || "".equals(chongfu.getZhltqk())) { // 综合利用情况
					sql.append(dao.updateTab("ceshi_sum1", "getZhltqk", hjb.getZhltqk(), dzbh));
					sql.append(updateDzFrom( dzbh, "getZhltqk"));

				}
				if (chongfu.getZrk() == null || chongfu.getZrk() == 0.0) { // 总库容（万立方米）
					sql.append(dao.updateTab("ceshi_sum1", "getZrk", hjb.getZrk(), dzbh));
					sql.append(updateDzFrom( dzbh, "getZrk"));

				}
				if (chongfu.getBg() == null || chongfu.getBg() == 0.0) { // 坝高（米）
					sql.append(dao.updateTab("ceshi_sum1", "getBg", hjb.getBg(), dzbh));
					sql.append(updateDzFrom( dzbh, "getBg"));

				}
				if (chongfu.getSwdj() == null || chongfu.getSwdj() == 0.0) { // 上网电价（元/千瓦时）
					sql.append(dao.updateTab("ceshi_sum1", "getSwdj", hjb.getSwdj(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSwdj"));

				}
				if (chongfu.getGhspjwh() == null || "".equals(chongfu.getGhspjwh())) { // 规划审批及文号
					sql.append(dao.updateTab("ceshi_sum1", "getGhspjwh", hjb.getGhspjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getGhspjwh"));

				}
				if (chongfu.getJsfaspbmjwh() == null || "".equals(chongfu.getJsfaspbmjwh())) { // 技术方案审批部门及文号
					sql.append(dao.updateTab("ceshi_sum1", "getJsfaspbmjwh", hjb.getJsfaspbmjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getJsfaspbmjwh"));

				}
				if (chongfu.getPzkmbmjwh() == null || "".equals(chongfu.getPzkmbmjwh())) { // 批准开工部门及文号
					sql.append(dao.updateTab("ceshi_sum1", "getPzkmbmjwh", hjb.getPzkmbmjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getPzkmbmjwh"));

				}
				if (chongfu.getHpjhbyswh() == null || "".equals(chongfu.getHpjhbyswh())) { // 环评及环保验收文号
					sql.append(dao.updateTab("ceshi_sum1", "getHpjhbyswh", hjb.getHpjhbyswh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getHpjhbyswh"));

				}
				if (chongfu.getHpjhbyswh() == null || "".equals(chongfu.getHpjhbyswh())) { // 水保及文号
					sql.append(dao.updateTab("ceshi_sum1", "getHpjhbyswh", hjb.getHpjhbyswh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getHpjhbyswh"));

				}
				if (chongfu.getYdpcjwh() == null || "".equals(chongfu.getYdpcjwh())) { // 用地批准及文号
					sql.append(dao.updateTab("ceshi_sum1", "getYdpcjwh", hjb.getYdpcjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getYdpcjwh"));

				}
				if (chongfu.getSfjddzzhwxx() == null || "".equals(chongfu.getSfjddzzhwxx())) { // 是否进行地质灾害危险性评估
					sql.append(dao.updateTab("ceshi_sum1", "getSfjddzzhwxx", hjb.getSfjddzzhwxx(), dzbh));
					sql.append(updateDzFrom( dzbh, "getSfjddzzhwxx"));

				}
				if (chongfu.getYsbmjwh() == null || "".equals(chongfu.getYsbmjwh())) { // 验收部门及文号
					sql.append(dao.updateTab("ceshi_sum1", "getYsbmjwh", hjb.getYsbmjwh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getYsbmjwh"));

				}
				if (chongfu.getXmfrmc() == null || "".equals(chongfu.getXmfrmc())) { // 项目法人名称
					sql.append(dao.updateTab("ceshi_sum1", "getXmfrmc", hjb.getXmfrmc(), dzbh));
					sql.append(updateDzFrom( dzbh, "getXmfrmc"));

				}
				if (chongfu.getZytzr() == null || "".equals(chongfu.getZytzr())) { // 主要投资方
					sql.append(dao.updateTab("ceshi_sum1", "getZytzr", hjb.getZytzr(), dzbh));
					sql.append(updateDzFrom( dzbh, "getZytzr"));

				}
				if (chongfu.getLxdh() == null || "".equals(chongfu.getLxdh())) { // 联系电话
					sql.append(dao.updateTab("ceshi_sum1", "getLxdh", hjb.getLxdh(), dzbh));
					sql.append(updateDzFrom( dzbh, "getLxdh"));

				}
				if (chongfu.getBz() == null || "".equals(chongfu.getBz())) { // 备注
					sql.append(dao.updateTab("ceshi_sum1", "getBz", hjb.getBz(), dzbh));
					sql.append(updateDzFrom( dzbh, "getBz"));

				}
				
				if(!"".equals(sql.toString())){
					System.out.println("批量执行：" + dao.RunSQL(sql.toString()));
				}
			}
		}
	}

	private String updateDzFrom( String dzbm, String getMethod) {
		
/*		if (b) {
			boolean dz_from = dao.updateTab("dz_from", getMethod, 2, dzbm);
			if (dz_from) {
				return null;
			} else {
				return dzbm;
			}
		} else {
			return dzbm;
		}*/
		
		return dao.updateTab("dz_from", getMethod, 2, dzbm);
	}

	public void importData() {
		List<SlbInfoBean> hbb = dao.selectSlb(); // 查询环保部的所有数据
		int success = 0;
		int error = 0;
		List errorId = new ArrayList<>();
		for (int i = 0; i < hbb.size(); i++) { // 将环保部的数据遍历，作为默认数据存入综合表中
			SlbInfoBean hjbHpsInfo = hbb.get(i);
			hjbHpsInfo.setDzbh(Randomizer.generCode(16));
			if (hjbHpsInfo.getDysj() == null) {
				hjbHpsInfo.setDysj("1900-01-01");
			}
			if (hjbHpsInfo.getGhshsj() == null) {
				hjbHpsInfo.setGhshsj("1900-01-01");
			}
			if (hjbHpsInfo.getHpspsj() == null) {
				hjbHpsInfo.setHpspsj("1900-01-01");
			}
			if (hjbHpsInfo.getZrbhqslyj() == null) {
				hjbHpsInfo.setZrbhqslyj("1900-01-01");
			}
			if (hjbHpsInfo.getTbsj() == null) {
				hjbHpsInfo.setTbsj("1900-01-01");
			}
			if (hjbHpsInfo.getTcny() == null) {
				hjbHpsInfo.setTcny("1900-01-01");
			}
			/*
			 * boolean b = dao.insertCeshiSum(hjbHpsInfo); if (b) { //
			 * 判断是否导入成功，成功的话将数据插入到数据来源表,传入的数据 1为水利部,2为环境部,3为能源局 success++;
			 * dao.insertDzFrom(hjbHpsInfo, 1); } else { error++;
			 * errorId.add(hbb.get(i).getId()); System.err.println("失败的条数id是" +
			 * hbb.get(i).getId()); }
			 */
			System.out.println("当前成功:" + success + "条,失败:" + error + "条");
			System.out.println("错误的数据都有id有" + errorId.toString());
		}
	}

	private String sql;
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String updateSum(){
		String message = "";
		List<Map> list = null;
		try {
			list = dao.sele(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.size() != 2) {
			message = "不是两条数据，请确认是否是同一个省，市，县内的数据";
			CommonFunction.writeResponse(getResponse(), message);
			return null;
		} else {
			Map map = list.get(0);
			Map map1 = list.get(1);
			if (map.get("dj_flag").equals(0) && !map1.get("dj_flag").equals(0)){
				dao.updateTab("hps_info_sum","dj",map1.get("dj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","dj_flag",map1.get("dj_flag"),map.get("id").toString());
			}
			if (map.get("bw_flag").equals(0) && !map1.get("bw_flag").equals(0)){
				dao.updateTab("hps_info_sum","bw",map1.get("bw"),map.get("id").toString());
				dao.updateTab("hps_info_sum","bw_flag",map1.get("bw_flag"),map.get("id").toString());
			}
			if (map.get("xmwz_flag").equals(0) && !map1.get("xmwz_flag").equals(0)){
				dao.updateTab("hps_info_sum","xmwz",map1.get("xmwz"),map.get("id").toString());
				dao.updateTab("hps_info_sum","xmwz_flag",map1.get("xmwz_flag"),map.get("id").toString());
			}
			if (map.get("jsdw_flag").equals(0) && !map1.get("jsdw_flag").equals(0)){
				dao.updateTab("hps_info_sum","jsdw",map1.get("jsdw"),map.get("id").toString());
				dao.updateTab("hps_info_sum","jsdw_flag",map1.get("jsdw_flag"),map.get("id").toString());
			}
			if (map.get("dzlxr_flag").equals(0) && !map1.get("dzlxr_flag").equals(0)){
				dao.updateTab("hps_info_sum","dzlxr",map1.get("dzlxr"),map.get("id").toString());
				dao.updateTab("hps_info_sum","dzlxr_flag",map1.get("dzlxr_flag"),map.get("id").toString());
			}
			if (map.get("lxrdh_flag").equals(0) && !map1.get("lxrdh_flag").equals(0)){
				dao.updateTab("hps_info_sum","lxrdh",map1.get("lxrdh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","lxrdh_flag",map1.get("lxrdh_flag"),map.get("id").toString());
			}
			if (map.get("xmszly_flag").equals(0) && !map1.get("xmszly_flag").equals(0)){
				dao.updateTab("hps_info_sum","xmszly",map1.get("xmszly"),map.get("id").toString());
				dao.updateTab("hps_info_sum","xmszly_flag",map1.get("xmszly_flag"),map.get("id").toString());
			}
			if (map.get("yjzl_flag").equals(0) && !map1.get("yjzl_flag").equals(0)){
				dao.updateTab("hps_info_sum","yjzl",map1.get("yjzl"),map.get("id").toString());
				dao.updateTab("hps_info_sum","yjzl_flag",map1.get("yjzl_flag"),map.get("id").toString());
			}
			if (map.get("jthl_flag").equals(0) && !map1.get("jthl_flag").equals(0)){
				dao.updateTab("hps_info_sum","jthl",map1.get("jthl"),map.get("id").toString());
				dao.updateTab("hps_info_sum","jthl_flag",map1.get("jthl_flag"),map.get("id").toString());
			}
			if (map.get("zjrl_flag").equals(0) && !map1.get("zjrl_flag").equals(0)){
				dao.updateTab("hps_info_sum","zjrl",map1.get("zjrl"),map.get("id").toString());
				dao.updateTab("hps_info_sum","zjrl_flag",map1.get("zjrl_flag"),map.get("id").toString());
			}
			if (map.get("ztz_flag").equals(0) && !map1.get("ztz_flag").equals(0)){
				dao.updateTab("hps_info_sum","ztz",map1.get("ztz"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ztz_flag",map1.get("ztz_flag"),map.get("id").toString());
			}
			if (map.get("sjnfdl_flag").equals(0) && !map1.get("sjnfdl_flag").equals(0)){
				dao.updateTab("hps_info_sum","sjnfdl",map1.get("sjnfdl"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sjnfdl_flag",map1.get("sjnfdl_flag"),map.get("id").toString());
			}
			if (map.get("sjnfdl_xz_flag").equals(0) && !map1.get("sjnfdl_xz_flag").equals(0)){
				dao.updateTab("hps_info_sum","sjnfdl_xz",map1.get("sjnfdl_xz"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sjnfdl_xz_flag",map1.get("sjnfdl_xz_flag"),map.get("id").toString());
			}
			if (map.get("tzxz_flag").equals(0) && !map1.get("tzxz_flag").equals(0)){
				dao.updateTab("hps_info_sum","tzxz",map1.get("tzxz"),map.get("id").toString());
				dao.updateTab("hps_info_sum","tzxz_flag",map1.get("tzxz_flag"),map.get("id").toString());
			}
			if (map.get("tzly_flag").equals(0) && !map1.get("tzly_flag").equals(0)){
				dao.updateTab("hps_info_sum","tzly",map1.get("tzly"),map.get("id").toString());
				dao.updateTab("hps_info_sum","tzly_flag",map1.get("tzly_flag"),map.get("id").toString());
			}
			if (map.get("bwqk_flag").equals(0) && !map1.get("bwqk_flag").equals(0)){
				dao.updateTab("hps_info_sum","bwqk",map1.get("bwqk"),map.get("id").toString());
				dao.updateTab("hps_info_sum","bwqk_flag",map1.get("bwqk_flag"),map.get("id").toString());
			}
			if (map.get("kffs_flag").equals(0) && !map1.get("kffs_flag").equals(0)){
				dao.updateTab("hps_info_sum","kffs",map1.get("kffs"),map.get("id").toString());
				dao.updateTab("hps_info_sum","kffs_flag",map1.get("kffs_flag"),map.get("id").toString());
			}
			if (map.get("qskhdjl_flag").equals(0) && !map1.get("qskhdjl_flag").equals(0)){
				dao.updateTab("hps_info_sum","qskhdjl",map1.get("qskhdjl"),map.get("id").toString());
				dao.updateTab("hps_info_sum","qskhdjl_flag",map1.get("qskhdjl_flag"),map.get("id").toString());
			}
			if (map.get("jszt_flag").equals(0) && !map1.get("jszt_flag").equals(0)){
				dao.updateTab("hps_info_sum","jszt",map1.get("jszt"),map.get("id").toString());
				dao.updateTab("hps_info_sum","jszt_flag",map1.get("jszt_flag"),map.get("id").toString());
			}
			if (map.get("dysj_flag").equals(0) && !map1.get("dysj_flag").equals(0)){
				dao.updateTab("hps_info_sum","dysj",map1.get("dysj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","dysj_flag",map1.get("dysj_flag"),map.get("id").toString());
			}
			if (map.get("ccyy_flag").equals(0) && !map1.get("ccyy_flag").equals(0)){
				dao.updateTab("hps_info_sum","ccyy",map1.get("ccyy"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ccyy_flag",map1.get("ccyy_flag"),map.get("id").toString());
			}
			if (map.get("sfykzstxf_flag").equals(0) && !map1.get("sfykzstxf_flag").equals(0)){
				dao.updateTab("hps_info_sum","sfykzstxf",map1.get("sfykzstxf"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sfykzstxf_flag",map1.get("sfykzstxf_flag"),map.get("id").toString());
			}
			if (map.get("sjfdl_flag").equals(0) && !map1.get("sjfdl_flag").equals(0)){
				dao.updateTab("hps_info_sum","sjfdl",map1.get("sjfdl"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sjfdl_flag",map1.get("sjfdl_flag"),map.get("id").toString());
			}
			if (map.get("sjfdl_xz_flag").equals(0) && !map1.get("sjfdl_xz_flag").equals(0)){
				dao.updateTab("hps_info_sum","sjfdl_xz",map1.get("sjfdl_xz"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sjfdl_xz_flag",map1.get("sjfdl_xz_flag"),map.get("id").toString());
			}
			if (map.get("sfyxmhz_flag").equals(0) && !map1.get("sfyxmhz_flag").equals(0)){
				dao.updateTab("hps_info_sum","sfyxmhz",map1.get("sfyxmhz"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sfyxmhz_flag",map1.get("sfyxmhz_flag"),map.get("id").toString());
			}
			if (map.get("hzwjmcjch_flag").equals(0) && !map1.get("hzwjmcjch_flag").equals(0)){
				dao.updateTab("hps_info_sum","hzwjmcjch",map1.get("hzwjmcjch"),map.get("id").toString());
				dao.updateTab("hps_info_sum","hzwjmcjch_flag",map1.get("hzwjmcjch_flag"),map.get("id").toString());
			}
			if (map.get("xmhzdw_flag").equals(0) && !map1.get("xmhzdw_flag").equals(0)){
				dao.updateTab("hps_info_sum","xmhzdw",map1.get("xmhzdw"),map.get("id").toString());
				dao.updateTab("hps_info_sum","xmhzdw_flag",map1.get("xmhzdw_flag"),map.get("id").toString());
			}
			if (map.get("sffhgh_flag").equals(0) && !map1.get("sffhgh_flag").equals(0)){
				dao.updateTab("hps_info_sum","sffhgh",map1.get("sffhgh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sffhgh_flag",map1.get("sffhgh_flag"),map.get("id").toString());
			}
			if (map.get("ghmc_flag").equals(0) && !map1.get("ghmc_flag").equals(0)){
				dao.updateTab("hps_info_sum","ghmc",map1.get("ghmc"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ghmc_flag",map1.get("ghmc_flag"),map.get("id").toString());
			}
			if (map.get("ghshsj_flag").equals(0) && !map1.get("ghshsj_flag").equals(0)){
				dao.updateTab("hps_info_sum","ghshsj",map1.get("ghshsj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ghshsj_flag",map1.get("ghshsj_flag"),map.get("id").toString());
			}
			if (map.get("ghspbm_flag").equals(0) && !map1.get("ghspbm_flag").equals(0)){
				dao.updateTab("hps_info_sum","ghspbm",map1.get("ghspbm"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ghspbm_flag",map1.get("ghspbm_flag"),map.get("id").toString());
			}
			if (map.get("sffhghhp_flag").equals(0) && !map1.get("sffhghhp_flag").equals(0)){
				dao.updateTab("hps_info_sum","sffhghhp",map1.get("sffhghhp"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sffhghhp_flag",map1.get("sffhghhp_flag"),map.get("id").toString());
			}
			if (map.get("ghhpwj_flag").equals(0) && !map1.get("ghhpwj_flag").equals(0)){
				dao.updateTab("hps_info_sum","ghhpwj",map1.get("ghhpwj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ghhpwj_flag",map1.get("ghhpwj_flag"),map.get("id").toString());
			}
			if (map.get("scwjwh_flag").equals(0) && !map1.get("scwjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","scwjwh",map1.get("scwjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","scwjwh_flag",map1.get("scwjwh_flag"),map.get("id").toString());
			}
			if (map.get("ghhpscbm_flag").equals(0) && !map1.get("ghhpscbm_flag").equals(0)){
				dao.updateTab("hps_info_sum","ghhpscbm",map1.get("ghhpscbm"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ghhpscbm_flag",map1.get("ghhpscbm_flag"),map.get("id").toString());
			}
			if (map.get("sftgxmhp_flag").equals(0) && !map1.get("sftgxmhp_flag").equals(0)){
				dao.updateTab("hps_info_sum","sftgxmhp",map1.get("sftgxmhp"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sftgxmhp_flag",map1.get("sftgxmhp_flag"),map.get("id").toString());
			}
			if (map.get("spwjmc_flag").equals(0) && !map1.get("spwjmc_flag").equals(0)){
				dao.updateTab("hps_info_sum","spwjmc",map1.get("spwjmc"),map.get("id").toString());
				dao.updateTab("hps_info_sum","spwjmc_flag",map1.get("spwjmc_flag"),map.get("id").toString());
			}
			if (map.get("hpspsj_flag").equals(0) && !map1.get("hpspsj_flag").equals(0)){
				dao.updateTab("hps_info_sum","hpspsj",map1.get("hpspsj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","hpspsj_flag",map1.get("hpspsj_flag"),map.get("id").toString());
			}
			if (map.get("ppbm_flag").equals(0) && !map1.get("ppbm_flag").equals(0)){
				dao.updateTab("hps_info_sum","ppbm",map1.get("ppbm"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ppbm_flag",map1.get("ppbm_flag"),map.get("id").toString());
			}
			if (map.get("sftgjghbys_flag").equals(0) && !map1.get("sftgjghbys_flag").equals(0)){
				dao.updateTab("hps_info_sum","sftgjghbys",map1.get("sftgjghbys"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sftgjghbys_flag",map1.get("sftgjghbys_flag"),map.get("id").toString());
			}
			if (map.get("yswjmc_flag").equals(0) && !map1.get("yswjmc_flag").equals(0)){
				dao.updateTab("hps_info_sum","yswjmc",map1.get("yswjmc"),map.get("id").toString());
				dao.updateTab("hps_info_sum","yswjmc_flag",map1.get("yswjmc_flag"),map.get("id").toString());
			}
			if (map.get("yswjwh_flag").equals(0) && !map1.get("yswjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","yswjwh",map1.get("yswjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","yswjwh_flag",map1.get("yswjwh_flag"),map.get("id").toString());
			}
			if (map.get("ysbm_flag").equals(0) && !map1.get("ysbm_flag").equals(0)){
				dao.updateTab("hps_info_sum","ysbm",map1.get("ysbm"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ysbm_flag",map1.get("ysbm_flag"),map.get("id").toString());
			}
			if (map.get("stllxfcs_flag").equals(0) && !map1.get("stllxfcs_flag").equals(0)){
				dao.updateTab("hps_info_sum","stllxfcs",map1.get("stllxfcs"),map.get("id").toString());
				dao.updateTab("hps_info_sum","stllxfcs_flag",map1.get("stllxfcs_flag"),map.get("id").toString());
			}
			if (map.get("stlljkss_flag").equals(0) && !map1.get("stlljkss_flag").equals(0)){
				dao.updateTab("hps_info_sum","stlljkss",map1.get("stlljkss"),map.get("id").toString());
				dao.updateTab("hps_info_sum","stlljkss_flag",map1.get("stlljkss_flag"),map.get("id").toString());
			}
			if (map.get("gycs_flag").equals(0) && !map1.get("gycs_flag").equals(0)){
				dao.updateTab("hps_info_sum","gycs",map1.get("gycs"),map.get("id").toString());
				dao.updateTab("hps_info_sum","gycs_flag",map1.get("gycs_flag"),map.get("id").toString());
			}
			if (map.get("zzflcs_flag").equals(0) && !map1.get("zzflcs_flag").equals(0)){
				dao.updateTab("hps_info_sum","zzflcs",map1.get("zzflcs"),map.get("id").toString());
				dao.updateTab("hps_info_sum","zzflcs_flag",map1.get("zzflcs_flag"),map.get("id").toString());
			}
			if (map.get("qthbcs_flag").equals(0) && !map1.get("qthbcs_flag").equals(0)){
				dao.updateTab("hps_info_sum","qthbcs",map1.get("qthbcs"),map.get("id").toString());
				dao.updateTab("hps_info_sum","qthbcs_flag",map1.get("qthbcs_flag"),map.get("id").toString());
			}
			if (map.get("qthbcsms_flag").equals(0) && !map1.get("qthbcsms_flag").equals(0)){
				dao.updateTab("hps_info_sum","qthbcsms",map1.get("qthbcsms"),map.get("id").toString());
				dao.updateTab("hps_info_sum","qthbcsms_flag",map1.get("qthbcsms_flag"),map.get("id").toString());
			}
			if (map.get("sfsjzrbhq_flag").equals(0) && !map1.get("sfsjzrbhq_flag").equals(0)){
				dao.updateTab("hps_info_sum","sfsjzrbhq",map1.get("sfsjzrbhq"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sfsjzrbhq_flag",map1.get("sfsjzrbhq_flag"),map.get("id").toString());
			}
			if (map.get("zrbhqslyj_flag").equals(0) && !map1.get("zrbhqslyj_flag").equals(0)){
				dao.updateTab("hps_info_sum","zrbhqslyj",map1.get("zrbhqslyj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","zrbhqslyj_flag",map1.get("zrbhqslyj_flag"),map.get("id").toString());
			}
			if (map.get("zrbhqjj_flag").equals(0) && !map1.get("zrbhqjj_flag").equals(0)){
				dao.updateTab("hps_info_sum","zrbhqjj",map1.get("zrbhqjj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","zrbhqjj_flag",map1.get("zrbhqjj_flag"),map.get("id").toString());
			}
			if (map.get("hxq_flag").equals(0) && !map1.get("hxq_flag").equals(0)){
				dao.updateTab("hps_info_sum","hxq",map1.get("hxq"),map.get("id").toString());
				dao.updateTab("hps_info_sum","hxq_flag",map1.get("hxq_flag"),map.get("id").toString());
			}
			if (map.get("hcq_flag").equals(0) && !map1.get("hcq_flag").equals(0)){
				dao.updateTab("hps_info_sum","hcq",map1.get("hcq"),map.get("id").toString());
				dao.updateTab("hps_info_sum","hcq_flag",map1.get("hcq_flag"),map.get("id").toString());
			}
			if (map.get("sys_flag").equals(0) && !map1.get("sys_flag").equals(0)){
				dao.updateTab("hps_info_sum","sys",map1.get("sys"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sys_flag",map1.get("sys_flag"),map.get("id").toString());
			}
			if (map.get("wfq_flag").equals(0) && !map1.get("wfq_flag").equals(0)){
				dao.updateTab("hps_info_sum","wfq",map1.get("wfq"),map.get("id").toString());
				dao.updateTab("hps_info_sum","wfq_flag",map1.get("wfq_flag"),map.get("id").toString());
			}
			if (map.get("bxsfcztsgk_flag").equals(0) && !map1.get("bxsfcztsgk_flag").equals(0)){
				dao.updateTab("hps_info_sum","bxsfcztsgk",map1.get("bxsfcztsgk"),map.get("id").toString());
				dao.updateTab("hps_info_sum","bxsfcztsgk_flag",map1.get("bxsfcztsgk_flag"),map.get("id").toString());
			}
			if (map.get("tshdcd_flag").equals(0) && !map1.get("tshdcd_flag").equals(0)){
				dao.updateTab("hps_info_sum","tshdcd",map1.get("tshdcd"),map.get("id").toString());
				dao.updateTab("hps_info_sum","tshdcd_flag",map1.get("tshdcd_flag"),map.get("id").toString());
			}
			if (map.get("qtsthjwt_flag").equals(0) && !map1.get("qtsthjwt_flag").equals(0)){
				dao.updateTab("hps_info_sum","qtsthjwt",map1.get("qtsthjwt"),map.get("id").toString());
				dao.updateTab("hps_info_sum","qtsthjwt_flag",map1.get("qtsthjwt_flag"),map.get("id").toString());
			}
			if (map.get("tbr_flag").equals(0) && !map1.get("tbr_flag").equals(0)){
				dao.updateTab("hps_info_sum","tbr",map1.get("tbr"),map.get("id").toString());
				dao.updateTab("hps_info_sum","tbr_flag",map1.get("tbr_flag"),map.get("id").toString());
			}
			if (map.get("dh_flag").equals(0) && !map1.get("dh_flag").equals(0)){
				dao.updateTab("hps_info_sum","dh",map1.get("dh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","dh_flag",map1.get("dh_flag"),map.get("id").toString());
			}
			if (map.get("tbsj_flag").equals(0) && !map1.get("tbsj_flag").equals(0)){
				dao.updateTab("hps_info_sum","tbsj",map1.get("tbsj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","tbsj_flag",map1.get("tbsj_flag"),map.get("id").toString());
			}
			if (map.get("xchcr_flag").equals(0) && !map1.get("xchcr_flag").equals(0)){
				dao.updateTab("hps_info_sum","xchcr",map1.get("xchcr"),map.get("id").toString());
				dao.updateTab("hps_info_sum","xchcr_flag",map1.get("xchcr_flag"),map.get("id").toString());
			}
			if (map.get("ywjmc_flag").equals(0) && !map1.get("ywjmc_flag").equals(0)){
				dao.updateTab("hps_info_sum","ywjmc",map1.get("ywjmc"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ywjmc_flag",map1.get("ywjmc_flag"),map.get("id").toString());
			}
			if (map.get("dzzs_flag").equals(0) && !map1.get("dzzs_flag").equals(0)){
				dao.updateTab("hps_info_sum","dzzs",map1.get("dzzs"),map.get("id").toString());
				dao.updateTab("hps_info_sum","dzzs_flag",map1.get("dzzs_flag"),map.get("id").toString());
			}
			if (map.get("tcny_flag").equals(0) && !map1.get("tcny_flag").equals(0)){
				dao.updateTab("hps_info_sum","tcny",map1.get("tcny"),map.get("id").toString());
				dao.updateTab("hps_info_sum","tcny_flag",map1.get("tcny_flag"),map.get("id").toString());
			}
			if (map.get("zhltqk_flag").equals(0) && !map1.get("zhltqk_flag").equals(0)){
				dao.updateTab("hps_info_sum","zhltqk",map1.get("zhltqk"),map.get("id").toString());
				dao.updateTab("hps_info_sum","zhltqk_flag",map1.get("zhltqk_flag"),map.get("id").toString());
			}
			if (map.get("zrk_flag").equals(0) && !map1.get("zrk_flag").equals(0)){
				dao.updateTab("hps_info_sum","zrk",map1.get("zrk"),map.get("id").toString());
				dao.updateTab("hps_info_sum","zrk_flag",map1.get("zrk_flag"),map.get("id").toString());
			}
			if (map.get("bg_flag").equals(0) && !map1.get("bg_flag").equals(0)){
				dao.updateTab("hps_info_sum","bg",map1.get("bg"),map.get("id").toString());
				dao.updateTab("hps_info_sum","bg_flag",map1.get("bg_flag"),map.get("id").toString());
			}
			if (map.get("swdj_flag").equals(0) && !map1.get("swdj_flag").equals(0)){
				dao.updateTab("hps_info_sum","swdj",map1.get("swdj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","swdj_flag",map1.get("swdj_flag"),map.get("id").toString());
			}
			if (map.get("ghspjwh_flag").equals(0) && !map1.get("ghspjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","ghspjwh",map1.get("ghspjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","swdj_flag",map1.get("ghspjwh_flag"),map.get("id").toString());
			}
			if (map.get("jsfaspbmjwh_flag").equals(0) && !map1.get("jsfaspbmjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","jsfaspbmjwh",map1.get("jsfaspbmjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","jsfaspbmjwh_flag",map1.get("jsfaspbmjwh_flag"),map.get("id").toString());
			}
			if (map.get("pzkmbmjwh_flag").equals(0) && !map1.get("pzkmbmjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","pzkmbmjwh",map1.get("pzkmbmjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","pzkmbmjwh_flag",map1.get("pzkmbmjwh_flag"),map.get("id").toString());
			}
			if (map.get("hpjhbyswh_flag").equals(0) && !map1.get("hpjhbyswh_flag").equals(0)){
				dao.updateTab("hps_info_sum","hpjhbyswh",map1.get("hpjhbyswh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","hpjhbyswh_flag",map1.get("hpjhbyswh_flag"),map.get("id").toString());
			}
			if (map.get("sbjwh_flag").equals(0) && !map1.get("sbjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","sbjwh",map1.get("sbjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sbjwh_flag",map1.get("sbjwh_flag"),map.get("id").toString());
			}
			if (map.get("ydpcjwh_flag").equals(0) && !map1.get("ydpcjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","ydpcjwh",map1.get("ydpcjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ydpcjwh_flag",map1.get("ydpcjwh_flag"),map.get("id").toString());
			}
			if (map.get("sfjddzzhwxx_flag").equals(0) && !map1.get("sfjddzzhwxx_flag").equals(0)){
				dao.updateTab("hps_info_sum","sfjddzzhwxx",map1.get("sfjddzzhwxx"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sfjddzzhwxx_flag",map1.get("sfjddzzhwxx_flag"),map.get("id").toString());
			}
			if (map.get("ysbmjwh_flag").equals(0) && !map1.get("ysbmjwh_flag").equals(0)){
				dao.updateTab("hps_info_sum","ysbmjwh",map1.get("ysbmjwh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","ysbmjwh_flag",map1.get("ysbmjwh_flag"),map.get("id").toString());
			}
			if (map.get("xmfrmc_flag").equals(0) && !map1.get("xmfrmc_flag").equals(0)){
				dao.updateTab("hps_info_sum","xmfrmc",map1.get("xmfrmc"),map.get("id").toString());
				dao.updateTab("hps_info_sum","xmfrmc_flag",map1.get("xmfrmc_flag"),map.get("id").toString());
			}
			if (map.get("zytzr_flag").equals(0) && !map1.get("zytzr_flag").equals(0)){
				dao.updateTab("hps_info_sum","zytzr",map1.get("zytzr"),map.get("id").toString());
				dao.updateTab("hps_info_sum","zytzr_flag",map1.get("zytzr_flag"),map.get("id").toString());
			}
			if (map.get("lxdh_flag").equals(0) && !map1.get("lxdh_flag").equals(0)){
				dao.updateTab("hps_info_sum","lxdh",map1.get("lxdh"),map.get("id").toString());
				dao.updateTab("hps_info_sum","lxdh_flag",map1.get("lxdh_flag"),map.get("id").toString());
			}
			if (map.get("bz_flag").equals(0) && !map1.get("bz_flag").equals(0)){
				dao.updateTab("hps_info_sum","bz",map1.get("bz"),map.get("id").toString());
				dao.updateTab("hps_info_sum","bz_flag",map1.get("bz_flag"),map.get("id").toString());
			}
			if (map.get("hpsjzytcsj_flag").equals(0) && !map1.get("hpsjzytcsj_flag").equals(0)){
				dao.updateTab("hps_info_sum","hpsjzytcsj",map1.get("hpsjzytcsj"),map.get("id").toString());
				dao.updateTab("hps_info_sum","hpsjzytcsj_flag",map1.get("hpsjzytcsj_flag"),map.get("id").toString());
			}
			if (map.get("sfyjgbg_flag").equals(0) && !map1.get("sfyjgbg_flag").equals(0)){
				dao.updateTab("hps_info_sum","sfyjgbg",map1.get("sfyjgbg"),map.get("id").toString());
				dao.updateTab("hps_info_sum","sfyjgbg_flag",map1.get("sfyjgbg_flag"),map.get("id").toString());
			}
			if (map.get("dztcsjybhqslsjxhgx_flag").equals(0) && !map1.get("dztcsjybhqslsjxhgx_flag").equals(0)){
				dao.updateTab("hps_info_sum","dztcsjybhqslsjxhgx",map1.get("dztcsjybhqslsjxhgx"),map.get("id").toString());
				dao.updateTab("hps_info_sum","dztcsjybhqslsjxhgx_flag",map1.get("dztcsjybhqslsjxhgx_flag"),map.get("id").toString());
			}

			int id = dao.deletesum(Integer.parseInt(map1.get("id").toString()));
			if (id == 1) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败");
			}

		}
		CommonFunction.writeResponse(getResponse(), message);
		return null;
	}
}