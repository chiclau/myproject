package com.lyht.business.consumer.hydrologicalData.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Zqrl;
import com.lyht.business.consumer.hydrologicalData.control.ZqrlControl;
import com.lyht.business.consumer.hydrologicalData.formbean.ZqrlFormBean;
import com.lyht.business.consumer.hydrologicalData.service.ZqrlService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/zqrl")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ZqrlAction extends BaseLyhtAction {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("ZqrlAction");
	private ZqrlFormBean mZqrlFormBean=new ZqrlFormBean();
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	@Resource
	private ZqrlControl mZqrlControl;
	@Resource
	private ZqrlService mZqrlService;

	/**
	 * 获取水位流量关系曲线列表
	 * */
	public String list(){
		log.info("ZqrlAction=list:获取水位流量关系曲线列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mZqrlControl.getZqrlMes(mZqrlFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化水位流量关系曲线FORM表单数据
	 * */
	public String edit(){
		log.info("ZqrlAction=edit:初始化水位流量关系曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mZqrlControl.getZqrlMes(mZqrlFormBean, mPageResults);
		mHashMap.put("mZqrlFormBean", mPageResults.getResults());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存水位流量关系曲线FORM表单数据
	 * */
	public String save(){
		log.info("ZqrlAction=save:保存水位流量关系曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		List<Zqrl> mZqrlList = new ArrayList<Zqrl>();
		Zqrl mZqrl = new Zqrl();
		Zqrl mZqrl_ = new Zqrl();
		String[] sj = null; 
		if(mZqrlFormBean.getIdsup()!=null){
			sj = mZqrlFormBean.getIdsup().split(",");
		}
		if(sj!=null){
			mZqrl.setStcd(sj[0]);
			mZqrl.setLnnm(sj[1]);
			mZqrl.setPtno(Float.parseFloat(sj[2]));
			mZqrl.setZ(Float.parseFloat(sj[3]));
			mZqrl.setQ(Float.parseFloat(sj[4]));
			mZqrl.setModitime(sj[5]);
		}
		mZqrlList=mZqrlService.getZqrlInfoById(mZqrlFormBean);
		for(Zqrl zqrl : mZqrlList){
			mZqrl_ = zqrl;
		}
		if(!"".equals(mZqrl.getStcd())){
			mRetMessage=mZqrlControl.update(mZqrl,mZqrlFormBean.getmZqrlInfoBean());
		}else{
			mRetMessage=mZqrlControl.create(mZqrlFormBean.getmZqrlInfoBean(),mZqrlFormBean.getmZqrlInfoBean());
		}
		mHashMap.put("mZqrlFormBean", mZqrlFormBean.getmZqrlInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存水位流量关系曲线FORM表单数据
	 * */
	public String saveX(){
		log.info("ZqrlAction=save:保存水位流量关系曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String []ptno = getRequest().getParameterValues("ptno");
		String []z = getRequest().getParameterValues("z");
		String []q = getRequest().getParameterValues("q");
		mRetMessage=mZqrlControl.createX(mZqrlFormBean,ptno,z,q);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除列表数据
	 * */
	public String removeids(){
		log.info("批量删除==ZqrlAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String flag=this.getRequest().getParameter("flag");
		String []stcd=this.getRequest().getParameterValues("stcd");
		String []lnnm=this.getRequest().getParameterValues("lnnm");
		String []ptno=this.getRequest().getParameterValues("ptno");
		mRetMessage=mZqrlControl.deletZqrlInfoByIds(stcd,lnnm,ptno,flag);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导出水位流量关系曲线
	 * */
	public String export(){
		log.info("ZqrlAction=export: ");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mZqrlControl.export(mZqrlFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 导入水位流量关系曲线
	 */
	public String importPptn(){
		log.info("ZqrlAction=importPptn: 导入降水量");
		RetMessage ret=new RetMessage();
		ret=mZqrlControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	public ZqrlFormBean getmZqrlFormBean() {
		return mZqrlFormBean;
	}
	
	/**
	 * 上传文件域对象
	 */
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	/**
	 * 上传文件名
	 */	
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	
}
