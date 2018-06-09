package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;

/**
 *作者： 刘魁
 *脚本日期:2018年5月10日 21:41:11
 *说明:  方案参数值表
*/
 @Entity
 @Table(name = "MODEL_PROG_VALUE")
public class ModelProgValue  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Description(key="primary",value="唯一编码")
	private String code;
	
	@Description(key="display",value="方案编码")
	private String progCode;
	
	@Description(key="primary",value="模型编码")
	private String modelCode;
	
	@Description(key="primary",value="参数编码")
	private String paraCode;
	
	@Description(key="primary",value="参数值")
	private String paraValue;
	
	public ModelProgValue(){
		this.code="";
		this.progCode="";
		this.modelCode="";
		this.paraCode="";
		this.paraValue="";
		
	}
	
	public ModelProgValue(
			String code,
			String progCode,
			String modelCode,
			String paraCode,
			String paraValue
			){
		this.code=code;
		this.progCode=progCode;
		this.modelCode=modelCode;
		this.paraCode=paraCode;
		this.paraValue=paraValue;
	}
	@Id
	@Column(name = "CODE",length=50 )
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "PROG_CODE",length=50 )
	public String getProgCode() {
		return progCode;
	}

	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}
	
	@Column(name = "MODEL_CODE",length=50 )
	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	@Column(name = "PARA_CODE",length=50 )
	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	
	@Column(name = "PARA_VALUE",length=50 )
	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	
	
	
	
}
