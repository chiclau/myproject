package com.lyht.business.consumer.hydrologicalData.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Zvarl;
import com.lyht.business.consumer.hydrologicalData.control.ZvarlControl;
import com.lyht.business.consumer.hydrologicalData.formbean.ZvarlFormBean;
import com.lyht.business.consumer.hydrologicalData.service.ZvarlService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/zvarl")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ZvarlAction extends BaseLyhtAction {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("ZvarlAction");
	private ZvarlFormBean mZvarlFormBean=new ZvarlFormBean();
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	@Resource
	private ZvarlControl mZvarlControl;
	@Resource
	private ZvarlService mZvarlService;

	/**
	 * 获取库（湖）容曲线列表
	 * */
	public String list(){
		log.info("ZvarlAction=list:获取库（湖）容曲线列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mZvarlControl.getZvarlMes(mZvarlFormBean, mPageResults);
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
	 * 初始化库（湖）容曲线FORM表单数据
	 * */
	public String edit(){
		log.info("ZvarlAction=edit:初始化库（湖）容曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mZvarlControl.getZvarlMes(mZvarlFormBean, mPageResults);
		mHashMap.put("mZvarlFormBean", mPageResults.getResults());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存库（湖）容曲线FORM表单数据
	 * */
	public String save(){
		log.info("ZvarlAction=save:保存库（湖）容曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		List<Zvarl> mZvarlList = new ArrayList<Zvarl>();
		Zvarl mZvarl = new Zvarl();
		Zvarl mZvarl_ = new Zvarl();
		String[] sj = null; 
		if(mZvarlFormBean.getIdsup()!=null){
			sj = mZvarlFormBean.getIdsup().split(",");
		}
		if(sj!=null){
			mZvarl.setStcd(sj[0]);
			mZvarl.setPtno(Double.parseDouble(sj[1]));
			mZvarl.setRz(Double.parseDouble(sj[2]));
			mZvarl.setW(Double.parseDouble(sj[3]));
			mZvarl.setWsfa(Double.parseDouble(sj[4]));
			mZvarl.setMstm(sj[5]);
		}
		mZvarlList=mZvarlService.getZvarlInfoById(mZvarlFormBean.getmZvarlInfoBean().getStcd());
		for(Zvarl Zvarl : mZvarlList){
			mZvarl_ = Zvarl;
		}
		if(!"".equals(mZvarl.getStcd())){
			mRetMessage=mZvarlControl.update(mZvarl,mZvarlFormBean.getmZvarlInfoBean());
		}else{
			mRetMessage=mZvarlControl.create(mZvarlFormBean.getmZvarlInfoBean(),mZvarlFormBean.getmZvarlInfoBean());
		}
		mHashMap.put("mZvarlFormBean", mZvarlFormBean.getmZvarlInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	public String saveX(){
		log.info("ZvarlAction=saveX:保存库（湖）容曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String []ptno = getRequest().getParameterValues("ptno");
		String []rz= getRequest().getParameterValues("rz");
		String []w= getRequest().getParameterValues("w");
		String []wsfa= getRequest().getParameterValues("wsfa");
		mRetMessage=mZvarlControl.createX(mZvarlFormBean,ptno,rz,w,wsfa);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除列表数据
	 * */
	public String removeids(){
		log.info("批量删除==ZvarlAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String flag=this.getRequest().getParameter("flag");
		String []stcd=this.getRequest().getParameterValues("stcd");
		String []mstm=this.getRequest().getParameterValues("mstm");
		String []ptno=this.getRequest().getParameterValues("ptno");
		mRetMessage=mZvarlControl.deletZvarlInfoByIds(stcd,mstm,ptno,flag);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导出库（湖）容曲线
	 * */
	public String export(){
		log.info("ZvarlAction=export: ");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mZvarlControl.export(mZvarlFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 导入库（湖）容曲线
	 */
	public String importPptn(){
		log.info("ZvarlAction=importPptn: 导入（湖）容曲线");
		RetMessage ret=new RetMessage();
		ret=mZvarlControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	public ZvarlFormBean getmZvarlFormBean() {
		return mZvarlFormBean;
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
