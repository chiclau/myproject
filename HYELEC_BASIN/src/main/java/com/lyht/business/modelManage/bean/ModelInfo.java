package com.lyht.business.modelManage.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.org.glassfish.gmbal.Description;
/**
 *作者： 刘魁
 *脚本日期:2018年5月10日 21:41:11
 *说明:  模型信息表
*/
@Entity
@Table(name = "MODEL_INFO")
public class ModelInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="模型编码")
	private String modelCode;
	
	@Description(key="display",value="模型名称")
	private String modelName;
	
	@Description(key="display",value="所属分类")
	private String modelType;
	
	@Description(key="display",value="描述")
	private String remark;
	
	
	@Description(key="display",value="状态")
	private int state;
	
	public ModelInfo(){
        this.modelCode = "";
        this.modelName = "";
        this.modelType = "";
        this.remark = "";       
        this.state = 1;
		
	}
	
	public ModelInfo(
			String modelCode,
			String modelName,
			String modelType,
			String remark,
	        int state){
		this.modelCode = modelCode;
        this.modelName = modelName;
        this.modelType = modelType;
        this.remark = remark;
        this.state = state;
	}
	
	
	@Override
    public String toString() {
    	String s="";
    	s=	"{";
        s=s+"modelCode="+modelCode;
        s=s+" ,modelName="+modelName;
        s=s+" ,modelType="+modelType;
        s=s+" ,remark="+remark;
        s=s+" ,state="+state;
    	s=s+"}";
    	return s;
    }
	
	@Id
	@Column(name = "MODEL_CODE",length=50 )
	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	@Column(name = "MODEL_NAME",length=50 )
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	@Column(name = "MODE_TYPE",length=50 )
	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	@Column(name = "REMARK",length=50 )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "STATE",length=50 )
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
}	
