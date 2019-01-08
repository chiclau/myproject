package com.lyht.business.baseinfo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 小水电信息汇总整理表实体类
 * @author 程东伟
 */
@Entity
@Table(name = "hps_info_sum")
public class HpsInfoSum implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "slb_dzbh")private String slbDzbh;

	@Column(name = "nyj_dzbh")private String nyjDzbh;

	@Column(name = "sthjb_dzbh")private String sthjbDzbh;

	@Id
	@Column(name = "dzbh")private String dzbh;

	@Column(name = "xmmc")private String xmmc;

	@Column(name = "dj")private Float dj;

	@Column(name = "bw")private Float bw;

	@Column(name = "sheng")private String sheng;

	@Column(name = "shi")private String shi;

	@Column(name = "xian")private String xian;

	@Column(name = "szxjxzqbm")private String szxjxzqbm;

	@Column(name = "xmwz")private String xmwz;

	@Column(name = "jsdw")private String jsdw;

	@Column(name = "dzlxr")private String dzlxr;

	@Column(name = "lxrdh")private String lxrdh;

	@Column(name = "xmszly")private String xmszly;

	@Column(name = "yjzl")private String yjzl;

	@Column(name = "jthl")private String jthl;

	@Column(name = "zjrl")private Float zjrl;

	@Column(name = "ztz")private Float ztz;

	@Column(name = "sjnfdl")private Float sjnfdl;

	@Column(name = "sjnfdl_xz")private Float sjnfdlXz;

	@Column(name = "tzxz")private String tzxz;

	@Column(name = "tzly")private String tzly;

	@Column(name = "bwqk")private String bwqk;

	@Column(name = "kffs")private String kffs;

	@Column(name = "qskhdjl")private String qskhdjl;

	@Column(name = "jszt")private String jszt;
	
	@Column(name = "dysj")private Date dysj;

	@Column(name = "ccyy")private String ccyy;

	@Column(name = "sfykzstxf")private Byte sfykzstxf;

	@Column(name = "sjfdl")private Float sjfdl;

	@Column(name = "sjfdl_xz")private Float sjfdlXz;

	@Column(name = "sfyxmhz")private Byte sfyxmhz;

	@Column(name = "hzwjmcjch")private String hzwjmcjch;

	@Column(name = "xmhzdw")private String xmhzdw;

	@Column(name = "sffhgh")private Byte sffhgh;

	@Column(name = "ghmc")private String ghmc;

	@Column(name = "ghshsj")private Date ghshsj;

	@Column(name = "ghspbm")private String ghspbm;

	@Column(name = "sffhghhp")private Byte sffhghhp;

	@Column(name = "ghhpwj")private String ghhpwj;

	@Column(name = "scwjwh")private String scwjwh;

	@Column(name = "ghhpscbm")private String ghhpscbm;

	@Column(name = "sftgxmhp")private Byte sftgxmhp;

	@Column(name = "spwjmc")private String spwjmc;

	@Column(name = "spwjwh")private String spwjwh;

	@Column(name = "hpspsj")private Date hpspsj;

	@Column(name = "ppbm")private String ppbm;

	@Column(name = "sftgjghbys")private Byte sftgjghbys;

	@Column(name = "yswjmc")private String yswjmc;

    @Column(name = "yswjwh")private String yswjwh;

    @Column(name = "ysbm")private String ysbm;

    @Column(name = "stllxfcs")private Byte stllxfcs;

    @Column(name = "stlljkss")private Byte stlljkss;

    @Column(name = "gycs")private Byte gycs;

    @Column(name = "zzflcs")private Byte zzflcs;

    @Column(name = "qthbcs")private Byte qthbcs;

    @Column(name = "qthbcsms")private String qthbcsms;

    @Column(name = "sfsjzrbhq")private Byte sfsjzrbhq;

    @Column(name = "zrbhqslyj")private String zrbhqslyj;

    @Column(name = "zrbhqjj")private String zrbhqjj;

    @Column(name = "hxq")private Byte hxq;

    @Column(name = "hcq") private Byte hcq;

    @Column(name = "sys")private Byte sys;

    @Column(name = "wfq")private Byte wfq;

    @Column(name = "bxsfcztsgk")private Byte bxsfcztsgk;

    @Column(name = "tshdcd")private Float tshdcd;

    @Column(name = "qtsthjwt")private String qtsthjwt;

    @Column(name = "tbr")private String tbr;

    @Column(name = "dh")private String dh;

    @Column(name = "tbsj")private Date tbsj;

    @Column(name = "xchcr")private String xchcr;

    @Column(name = "ywjmc")private String ywjmc;

    @Column(name = "dzzs")private Integer dzzs;

    @Column(name = "tcny")private String tcny;

    @Column(name = "zhltqk")private String zhltqk;

    @Column(name = "zrk")private Float zrk;

    @Column(name = "bg")private Float bg;

    @Column(name = "swdj")private Float swdj;

    @Column(name = "ghspjwh")private String ghspjwh;

    @Column(name = "jsfaspbmjwh")private String jsfaspbmjwh;

    @Column(name = "pzkmbmjwh")private String pzkmbmjwh;

    @Column(name = "hpjhbyswh")private String hpjhbyswh;

    @Column(name = "sbjwh")private String sbjwh;

    @Column(name = "ydpcjwh")private String ydpcjwh;

    @Column(name = "sfjddzzhwxx")private Byte sfjddzzhwxx;

    @Column(name = "ysbmjwh")private String ysbmjwh;

    @Column(name = "xmfrmc")private String xmfrmc;

    @Column(name = "zytzr")private String zytzr;

    @Column(name = "lxdh")private String lxdh;

    @Column(name = "bz")private String bz;

    public String getSlbDzbh() {
        return slbDzbh;
    }

    public void setSlbDzbh(String slbDzbh) {
        this.slbDzbh = slbDzbh == null ? null : slbDzbh.trim();
    }

    public String getNyjDzbh() {
        return nyjDzbh;
    }

    public void setNyjDzbh(String nyjDzbh) {
        this.nyjDzbh = nyjDzbh == null ? null : nyjDzbh.trim();
    }

    public String getSthjbDzbh() {
        return sthjbDzbh;
    }

    public void setSthjbDzbh(String sthjbDzbh) {
        this.sthjbDzbh = sthjbDzbh == null ? null : sthjbDzbh.trim();
    }

    public String getDzbh() {
        return dzbh;
    }

    public void setDzbh(String dzbh) {
        this.dzbh = dzbh == null ? null : dzbh.trim();
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc == null ? null : xmmc.trim();
    }

    public Float getDj() {
        return dj;
    }

    public void setDj(Float dj) {
        this.dj = dj;
    }

    public Float getBw() {
        return bw;
    }

    public void setBw(Float bw) {
        this.bw = bw;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng == null ? null : sheng.trim();
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi == null ? null : shi.trim();
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian == null ? null : xian.trim();
    }

    public String getSzxjxzqbm() {
        return szxjxzqbm;
    }

    public void setSzxjxzqbm(String szxjxzqbm) {
        this.szxjxzqbm = szxjxzqbm == null ? null : szxjxzqbm.trim();
    }

    public String getXmwz() {
        return xmwz;
    }

    public void setXmwz(String xmwz) {
        this.xmwz = xmwz == null ? null : xmwz.trim();
    }

    public String getJsdw() {
        return jsdw;
    }

    public void setJsdw(String jsdw) {
        this.jsdw = jsdw == null ? null : jsdw.trim();
    }

    public String getDzlxr() {
        return dzlxr;
    }

    public void setDzlxr(String dzlxr) {
        this.dzlxr = dzlxr == null ? null : dzlxr.trim();
    }

    public String getLxrdh() {
        return lxrdh;
    }

    public void setLxrdh(String lxrdh) {
        this.lxrdh = lxrdh == null ? null : lxrdh.trim();
    }

    public String getXmszly() {
        return xmszly;
    }

    public void setXmszly(String xmszly) {
        this.xmszly = xmszly == null ? null : xmszly.trim();
    }

    public String getYjzl() {
        return yjzl;
    }

    public void setYjzl(String yjzl) {
        this.yjzl = yjzl == null ? null : yjzl.trim();
    }

    public String getJthl() {
        return jthl;
    }

    public void setJthl(String jthl) {
        this.jthl = jthl == null ? null : jthl.trim();
    }

    public Float getZjrl() {
        return zjrl;
    }

    public void setZjrl(Float zjrl) {
        this.zjrl = zjrl;
    }

    public Float getZtz() {
        return ztz;
    }

    public void setZtz(Float ztz) {
        this.ztz = ztz;
    }

    public Float getSjnfdl() {
        return sjnfdl;
    }

    public void setSjnfdl(Float sjnfdl) {
        this.sjnfdl = sjnfdl;
    }

    public Float getSjnfdlXz() {
        return sjnfdlXz;
    }

    public void setSjnfdlXz(Float sjnfdlXz) {
        this.sjnfdlXz = sjnfdlXz;
    }

    public String getTzxz() {
        return tzxz;
    }

    public void setTzxz(String tzxz) {
        this.tzxz = tzxz == null ? null : tzxz.trim();
    }

    public String getTzly() {
        return tzly;
    }

    public void setTzly(String tzly) {
        this.tzly = tzly == null ? null : tzly.trim();
    }

    public String getBwqk() {
        return bwqk;
    }

    public void setBwqk(String bwqk) {
        this.bwqk = bwqk == null ? null : bwqk.trim();
    }

    public String getKffs() {
        return kffs;
    }

    public void setKffs(String kffs) {
        this.kffs = kffs == null ? null : kffs.trim();
    }

    public String getQskhdjl() {
        return qskhdjl;
    }

    public void setQskhdjl(String qskhdjl) {
        this.qskhdjl = qskhdjl == null ? null : qskhdjl.trim();
    }

    public String getJszt() {
        return jszt;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt == null ? null : jszt.trim();
    }

    public Date getDysj() {
        return dysj;
    }

    public void setDysj(Date dysj) {
        this.dysj = dysj;
    }

    public String getCcyy() {
        return ccyy;
    }

    public void setCcyy(String ccyy) {
        this.ccyy = ccyy == null ? null : ccyy.trim();
    }

    public Byte getSfykzstxf() {
        return sfykzstxf;
    }

    public void setSfykzstxf(Byte sfykzstxf) {
        this.sfykzstxf = sfykzstxf;
    }

    public Float getSjfdl() {
        return sjfdl;
    }

    public void setSjfdl(Float sjfdl) {
        this.sjfdl = sjfdl;
    }

    public Float getSjfdlXz() {
        return sjfdlXz;
    }

    public void setSjfdlXz(Float sjfdlXz) {
        this.sjfdlXz = sjfdlXz;
    }

    public Byte getSfyxmhz() {
        return sfyxmhz;
    }

    public void setSfyxmhz(Byte sfyxmhz) {
        this.sfyxmhz = sfyxmhz;
    }

    public String getHzwjmcjch() {
        return hzwjmcjch;
    }

    public void setHzwjmcjch(String hzwjmcjch) {
        this.hzwjmcjch = hzwjmcjch == null ? null : hzwjmcjch.trim();
    }

    public String getXmhzdw() {
        return xmhzdw;
    }

    public void setXmhzdw(String xmhzdw) {
        this.xmhzdw = xmhzdw == null ? null : xmhzdw.trim();
    }

    public Byte getSffhgh() {
        return sffhgh;
    }

    public void setSffhgh(Byte sffhgh) {
        this.sffhgh = sffhgh;
    }

    public String getGhmc() {
        return ghmc;
    }

    public void setGhmc(String ghmc) {
        this.ghmc = ghmc == null ? null : ghmc.trim();
    }

    public Date getGhshsj() {
        return ghshsj;
    }

    public void setGhshsj(Date ghshsj) {
        this.ghshsj = ghshsj;
    }

    public String getGhspbm() {
        return ghspbm;
    }

    public void setGhspbm(String ghspbm) {
        this.ghspbm = ghspbm == null ? null : ghspbm.trim();
    }

    public Byte getSffhghhp() {
        return sffhghhp;
    }

    public void setSffhghhp(Byte sffhghhp) {
        this.sffhghhp = sffhghhp;
    }

    public String getGhhpwj() {
        return ghhpwj;
    }

    public void setGhhpwj(String ghhpwj) {
        this.ghhpwj = ghhpwj == null ? null : ghhpwj.trim();
    }

    public String getScwjwh() {
        return scwjwh;
    }

    public void setScwjwh(String scwjwh) {
        this.scwjwh = scwjwh == null ? null : scwjwh.trim();
    }

    public String getGhhpscbm() {
        return ghhpscbm;
    }

    public void setGhhpscbm(String ghhpscbm) {
        this.ghhpscbm = ghhpscbm == null ? null : ghhpscbm.trim();
    }

    public Byte getSftgxmhp() {
        return sftgxmhp;
    }

    public void setSftgxmhp(Byte sftgxmhp) {
        this.sftgxmhp = sftgxmhp;
    }

    public String getSpwjmc() {
        return spwjmc;
    }

    public void setSpwjmc(String spwjmc) {
        this.spwjmc = spwjmc == null ? null : spwjmc.trim();
    }

    public String getSpwjwh() {
        return spwjwh;
    }

    public void setSpwjwh(String spwjwh) {
        this.spwjwh = spwjwh == null ? null : spwjwh.trim();
    }

    public Date getHpspsj() {
        return hpspsj;
    }

    public void setHpspsj(Date hpspsj) {
        this.hpspsj = hpspsj;
    }

    public String getPpbm() {
        return ppbm;
    }

    public void setPpbm(String ppbm) {
        this.ppbm = ppbm == null ? null : ppbm.trim();
    }

    public Byte getSftgjghbys() {
        return sftgjghbys;
    }

    public void setSftgjghbys(Byte sftgjghbys) {
        this.sftgjghbys = sftgjghbys;
    }

    public String getYswjmc() {
        return yswjmc;
    }

    public void setYswjmc(String yswjmc) {
        this.yswjmc = yswjmc == null ? null : yswjmc.trim();
    }

    public String getYswjwh() {
        return yswjwh;
    }

    public void setYswjwh(String yswjwh) {
        this.yswjwh = yswjwh == null ? null : yswjwh.trim();
    }

    public String getYsbm() {
        return ysbm;
    }

    public void setYsbm(String ysbm) {
        this.ysbm = ysbm == null ? null : ysbm.trim();
    }

    public Byte getStllxfcs() {
        return stllxfcs;
    }

    public void setStllxfcs(Byte stllxfcs) {
        this.stllxfcs = stllxfcs;
    }

    public Byte getStlljkss() {
        return stlljkss;
    }

    public void setStlljkss(Byte stlljkss) {
        this.stlljkss = stlljkss;
    }

    public Byte getGycs() {
        return gycs;
    }

    public void setGycs(Byte gycs) {
        this.gycs = gycs;
    }

    public Byte getZzflcs() {
        return zzflcs;
    }

    public void setZzflcs(Byte zzflcs) {
        this.zzflcs = zzflcs;
    }

    public Byte getQthbcs() {
        return qthbcs;
    }

    public void setQthbcs(Byte qthbcs) {
        this.qthbcs = qthbcs;
    }

    public String getQthbcsms() {
        return qthbcsms;
    }

    public void setQthbcsms(String qthbcsms) {
        this.qthbcsms = qthbcsms == null ? null : qthbcsms.trim();
    }

    public Byte getSfsjzrbhq() {
        return sfsjzrbhq;
    }

    public void setSfsjzrbhq(Byte sfsjzrbhq) {
        this.sfsjzrbhq = sfsjzrbhq;
    }

    public String getZrbhqslyj() {
        return zrbhqslyj;
    }

    public void setZrbhqslyj(String zrbhqslyj) {
        this.zrbhqslyj = zrbhqslyj == null ? null : zrbhqslyj.trim();
    }

    public String getZrbhqjj() {
        return zrbhqjj;
    }

    public void setZrbhqjj(String zrbhqjj) {
        this.zrbhqjj = zrbhqjj == null ? null : zrbhqjj.trim();
    }

    public Byte getHxq() {
        return hxq;
    }

    public void setHxq(Byte hxq) {
        this.hxq = hxq;
    }

    public Byte getHcq() {
        return hcq;
    }

    public void setHcq(Byte hcq) {
        this.hcq = hcq;
    }

    public Byte getSys() {
        return sys;
    }

    public void setSys(Byte sys) {
        this.sys = sys;
    }

    public Byte getWfq() {
        return wfq;
    }

    public void setWfq(Byte wfq) {
        this.wfq = wfq;
    }

    public Byte getBxsfcztsgk() {
        return bxsfcztsgk;
    }

    public void setBxsfcztsgk(Byte bxsfcztsgk) {
        this.bxsfcztsgk = bxsfcztsgk;
    }

    public Float getTshdcd() {
        return tshdcd;
    }

    public void setTshdcd(Float tshdcd) {
        this.tshdcd = tshdcd;
    }

    public String getQtsthjwt() {
        return qtsthjwt;
    }

    public void setQtsthjwt(String qtsthjwt) {
        this.qtsthjwt = qtsthjwt == null ? null : qtsthjwt.trim();
    }

    public String getTbr() {
        return tbr;
    }

    public void setTbr(String tbr) {
        this.tbr = tbr == null ? null : tbr.trim();
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh == null ? null : dh.trim();
    }

    public Date getTbsj() {
        return tbsj;
    }

    public void setTbsj(Date tbsj) {
        this.tbsj = tbsj;
    }

    public String getXchcr() {
        return xchcr;
    }

    public void setXchcr(String xchcr) {
        this.xchcr = xchcr == null ? null : xchcr.trim();
    }

    public String getYwjmc() {
        return ywjmc;
    }

    public void setYwjmc(String ywjmc) {
        this.ywjmc = ywjmc == null ? null : ywjmc.trim();
    }

    public Integer getDzzs() {
        return dzzs;
    }

    public void setDzzs(Integer dzzs) {
        this.dzzs = dzzs;
    }

    public String getTcny() {
        return tcny;
    }

    public void setTcny(String tcny) {
        this.tcny = tcny == null ? null : tcny.trim();
    }

    public String getZhltqk() {
        return zhltqk;
    }

    public void setZhltqk(String zhltqk) {
        this.zhltqk = zhltqk == null ? null : zhltqk.trim();
    }

    public Float getZrk() {
        return zrk;
    }

    public void setZrk(Float zrk) {
        this.zrk = zrk;
    }

    public Float getBg() {
        return bg;
    }

    public void setBg(Float bg) {
        this.bg = bg;
    }

    public Float getSwdj() {
        return swdj;
    }

    public void setSwdj(Float swdj) {
        this.swdj = swdj;
    }

    public String getGhspjwh() {
        return ghspjwh;
    }

    public void setGhspjwh(String ghspjwh) {
        this.ghspjwh = ghspjwh == null ? null : ghspjwh.trim();
    }

    public String getJsfaspbmjwh() {
        return jsfaspbmjwh;
    }

    public void setJsfaspbmjwh(String jsfaspbmjwh) {
        this.jsfaspbmjwh = jsfaspbmjwh == null ? null : jsfaspbmjwh.trim();
    }

    public String getPzkmbmjwh() {
        return pzkmbmjwh;
    }

    public void setPzkmbmjwh(String pzkmbmjwh) {
        this.pzkmbmjwh = pzkmbmjwh == null ? null : pzkmbmjwh.trim();
    }

    public String getHpjhbyswh() {
        return hpjhbyswh;
    }

    public void setHpjhbyswh(String hpjhbyswh) {
        this.hpjhbyswh = hpjhbyswh == null ? null : hpjhbyswh.trim();
    }

    public String getSbjwh() {
        return sbjwh;
    }

    public void setSbjwh(String sbjwh) {
        this.sbjwh = sbjwh == null ? null : sbjwh.trim();
    }

    public String getYdpcjwh() {
        return ydpcjwh;
    }

    public void setYdpcjwh(String ydpcjwh) {
        this.ydpcjwh = ydpcjwh == null ? null : ydpcjwh.trim();
    }

    public Byte getSfjddzzhwxx() {
        return sfjddzzhwxx;
    }

    public void setSfjddzzhwxx(Byte sfjddzzhwxx) {
        this.sfjddzzhwxx = sfjddzzhwxx;
    }

    public String getYsbmjwh() {
        return ysbmjwh;
    }

    public void setYsbmjwh(String ysbmjwh) {
        this.ysbmjwh = ysbmjwh == null ? null : ysbmjwh.trim();
    }

    public String getXmfrmc() {
        return xmfrmc;
    }

    public void setXmfrmc(String xmfrmc) {
        this.xmfrmc = xmfrmc == null ? null : xmfrmc.trim();
    }

    public String getZytzr() {
        return zytzr;
    }

    public void setZytzr(String zytzr) {
        this.zytzr = zytzr == null ? null : zytzr.trim();
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}