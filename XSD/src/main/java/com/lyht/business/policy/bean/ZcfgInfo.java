package com.lyht.business.policy.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zcfg_info")
public class ZcfgInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//政策法规编码
	@Id
	@Column(name = "fgbm")
	private String fgbm;
	
	//政策法规来源
	@Column(name = "ly")
    private String ly;
	
	//政策法规所属分类
	@Column(name = "ssfl")
    private String ssfl;
	
	//政策法规制定机关
	@Column(name = "zdjg")
    private String zdjg;
	
	//政策法规文号
	@Column(name = "wh")
    private String wh;
	
	//政策法规颁布日期
	@Column(name = "pbrq")
    private String pbrq;
	
	//政策法规实施日期
	@Column(name = "ssrq")
    private String ssrq;
	
	//政策法规标题
	@Column(name = "bt")
    private String bt;
	
	//政策法规状态（如果是审核状态     ： 0 未审核 ,1审核通过,默认0）
	@Column(name = "zt")
    private Integer zt;
	
	//政策法规备注
	@Column(name = "bz")
    private String bz;

    public String getFgbm() {
        return fgbm;
    }

    public void setFgbm(String fgbm) {
        this.fgbm = fgbm == null ? null : fgbm.trim();
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly == null ? null : ly.trim();
    }

    public String getSsfl() {
        return ssfl;
    }

    public void setSsfl(String ssfl) {
        this.ssfl = ssfl == null ? null : ssfl.trim();
    }

    public String getZdjg() {
        return zdjg;
    }

    public void setZdjg(String zdjg) {
        this.zdjg = zdjg == null ? null : zdjg.trim();
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh == null ? null : wh.trim();
    }

    public String getPbrq() {
        return pbrq;
    }

    public void setPbrq(String pbrq) {
        this.pbrq = pbrq;
    }

    public String getSsrq() {
        return ssrq;
    }

    public void setSsrq(String ssrq) {
        this.ssrq = ssrq; 
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt == null ? null : bt.trim();
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}