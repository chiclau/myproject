package com.lyht.business.policy.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zcfg_fj")
public class ZcfgFj implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String code;	
	
    private String fjmc;
	
    private String fjlx;
	
    private String fjdx;
	
    private String ccdz;

	//政策法规编号
    @Id
	@Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    //政策法规附件名称
  	@Column(name = "fjmc")
    public String getFjmc() {
        return fjmc;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc == null ? null : fjmc.trim();
    }

    //政策法规附件类型
  	@Column(name = "fjlx")
    public String getFjlx() {
        return fjlx;
    }

    public void setFjlx(String fjlx) {
        this.fjlx = fjlx == null ? null : fjlx.trim();
    }
    
    //政策法规附件大小
  	@Column(name = "fjdx")
    public String getFjdx() {
        return fjdx;
    }

    public void setFjdx(String fjdx) {
        this.fjdx = fjdx == null ? null : fjdx.trim();
    }
    
    //政策法规附件存储路径
  	@Column(name = "ccdz")
    public String getCcdz() {
        return ccdz;
    }

    public void setCcdz(String ccdz) {
        this.ccdz = ccdz == null ? null : ccdz.trim();
    }
}