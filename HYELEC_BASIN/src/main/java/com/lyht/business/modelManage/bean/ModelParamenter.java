package com.lyht.business.modelManage.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;
/**
 *作者： 刘魁
 *脚本日期:2018年5月10日 21:41:11
 *说明:  模型参数表
*/
@Entity
@Table(name = "MODEL_PARAMENTER")
public class ModelParamenter  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Description(key="primary",value="参数编码")
	private String paraCode;
	
	@Description(key="display",value="所属模型编码")
	private String modelCode;
	
	@Description(key="primary",value="参数名称")
	private String paraName;
	
	@Description(key="display",value="是否必填")
	private int isRequired;
	
	@Description(key="primary",value="参数符号")
	private String paraSymbol;
	
	@Description(key="primary",value="参数类型（输出输入）")
	private int paraType;
	
	@Description(key="primary",value="最大值")
	private String paraMax;
	
	@Description(key="primary",value="最小值")
	private String paraMin;
	
	public ModelParamenter(){
		this.paraCode="";
		this.modelCode="";
		this.paraName="";
		this.isRequired=0;
		this.paraSymbol="";
		this.paraType=0;
		this.paraMax="";
		this.paraMin="";
	}
	
	public ModelParamenter(
			String paraCode,
			String modelCode,
			String paraName,
			int isRequired,
			String paraSymbol,
			String paraType,
			String paraMax,
			String paraMin){
		this.paraCode=paraCode;
		this.modelCode=modelCode;
		this.paraName=paraName;
		this.isRequired=isRequired;
		this.paraSymbol=paraSymbol;
		this.paraType=0;
		this.paraMax=paraMax;
		this.paraMin=paraMin;
	}
	@Id
	@Column(name = "PARA_CODE",length=50 )
	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	@Column(name = "MODEL_CODE",length=50 )
	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	@Column(name = "PARA_NAME",length=50 )
	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	
	@Column(name = "IS_REQUIRED",length=50 )
	public int getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(int isRequired) {
		this.isRequired = isRequired;
	}
	
	@Column(name = "PARA_SYMBOL",length=50 )
	public String getParaSymbol() {
		return paraSymbol;
	}

	public void setParaSymbol(String paraSymbol) {
		this.paraSymbol = paraSymbol;
	}
	
	@Column(name = "PARA_TYPE",length=50 )
	public int getParaType() {
		return paraType;
	}

	public void setParaType(int paraType) {
		this.paraType = paraType;
	}
	
	@Column(name = "PARA_MAX",length=50 )
	public String getParaMax() {
		return paraMax;
	}

	public void setParaMax(String paraMax) {
		this.paraMax = paraMax;
	}
	
	@Column(name = "PARA_MIN",length=50 )
	public String getParaMin() {
		return paraMin;
	}

	public void setParaMin(String paraMin) {
		this.paraMin = paraMin;
	}
	
	
	
}
