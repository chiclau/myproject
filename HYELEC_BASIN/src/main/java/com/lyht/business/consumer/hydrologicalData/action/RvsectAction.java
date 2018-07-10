package com.lyht.business.consumer.hydrologicalData.action;

import java.io.File;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Rvsect;
import com.lyht.business.consumer.hydrologicalData.control.RvsectControl;
import com.lyht.business.consumer.hydrologicalData.formbean.RvsectFormBean;
import com.lyht.business.consumer.hydrologicalData.service.RvsectService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/rvsect")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RvsectAction extends BaseLyhtAction {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("RvsectAction");
	private RvsectFormBean mRvsectFormBean=new RvsectFormBean();
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	@Resource
	private RvsectControl mRvsectControl;
	@Resource
	private RvsectService mRvsectService;

	/**
	 * 获取库（湖）容曲线列表
	 * */
	public String list(){
		log.info("RvsectAction=list:获取库（湖）容曲线列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mRvsectControl.getRvsectMes(mRvsectFormBean, mPageResults);
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
		log.info("RvsectAction=edit:初始化库（湖）容曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mRvsectControl.getRvsectMes(mRvsectFormBean, mPageResults);
		mHashMap.put("mRvsectFormBean", mPageResults.getResults());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存库（湖）容曲线FORM表单数据
	 * */
	public String save(){
		log.info("RvsectAction=save:保存库（湖）容曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		List<Rvsect> mRvsectList = new ArrayList<Rvsect>();
		Rvsect mRvsect = new Rvsect();
		Rvsect mRvsect_ = new Rvsect();
		String[] sj = null; 
		if(mRvsectFormBean.getIdsup()!=null){
			sj = mRvsectFormBean.getIdsup().split(",");
		}
		if(sj!=null){
			mRvsect.setStcd(sj[0]);
			mRvsect.setDi(Double.parseDouble(sj[1]));
			mRvsect.setZb(Double.parseDouble(sj[2]));
			mRvsect.setVtno(sj[3]);
			mRvsect.setComments(sj[4]);
			mRvsect.setMstm(sj[5]);
		}
		mRvsectList=mRvsectService.getRvsectInfoById(mRvsectFormBean.getmRvsectInfoBean().getStcd());
		for(Rvsect Rvsect : mRvsectList){
			mRvsect_ = Rvsect;
		}
		if(!"".equals(mRvsect.getStcd())){
			mRetMessage=mRvsectControl.update(mRvsect,mRvsectFormBean.getmRvsectInfoBean());
		}else{
			mRetMessage=mRvsectControl.create(mRvsectFormBean.getmRvsectInfoBean(),mRvsectFormBean.getmRvsectInfoBean());
		}
		mHashMap.put("mRvsectFormBean", mRvsectFormBean.getmRvsectInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存断面测试成果FORM表单数据
	 * */
	public String saveX(){
		log.info("RvsectAction=save:保存断面测试成果FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String []di = getRequest().getParameterValues("di");
		String []zb = getRequest().getParameterValues("zb");
		String []vtno = getRequest().getParameterValues("vtno");
		mRetMessage=mRvsectControl.createX(mRvsectFormBean,di,zb,vtno);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除列表数据
	 * */
	public String removeids(){
		log.info("批量删除==RvsectAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String flag=this.getRequest().getParameter("flag");
		String []stcd=this.getRequest().getParameterValues("stcd");
		String []mstm=this.getRequest().getParameterValues("mstm");
		String []vtno=this.getRequest().getParameterValues("vtno");
		mRetMessage=mRvsectControl.deletRvsectInfoByIds(stcd,mstm,vtno,flag);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导出库（湖）容曲线
	 * */
	public String export(){
		log.info("RvsectAction=export: ");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mRvsectControl.export(mRvsectFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 导入库（湖）容曲线
	 */
	public String importPptn(){
		log.info("RvsectAction=importPptn: 导入（湖）容曲线");
		RetMessage ret=new RetMessage();
		ret=mRvsectControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	public RvsectFormBean getmRvsectFormBean() {
		return mRvsectFormBean;
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
