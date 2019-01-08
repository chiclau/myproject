package com.lyht.business.baseinfo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
/**
 * 小水电站信息（生态环境部）
 * @author 刘魁
 *@创建时间 2018/10/08
 */
public class HjbHpsInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="dzbh")
	private String dzbh;
	
	@Column(name="xmmc")
    private String xmmc;
	
	@Column(name="dj")
    private BigDecimal dj;
	
	@Column(name="bw")
    private BigDecimal bw;
	
	@Column(name="sheng")
    private String sheng;
	
	@Column(name="shi")
    private String shi;
	
	@Column(name="xian")
    private String xian;

	@Column(name="xmwz")
    private String xmwz;

	@Column(name="jsdw")
    private String jsdw;

	@Column(name="dzlxr")
    private String dzlxr;

	@Column(name="lxrdh")
    private String lxrdh;

	@Column(name="xmszly")
    private String xmszly;

	@Column(name="yjzl")
    private String yjzl;

	@Column(name="jthl")
    private String jthl;

	@Column(name="zjrl")
    private BigDecimal zjrl;

	@Column(name="ztz")
    private BigDecimal ztz;

	@Column(name="sjnfdl")
    private BigDecimal sjnfdl;

	@Column(name="sjnfdlXz")
    private BigDecimal sjnfdlXz;

	@Column(name="tzxz")
    private String tzxz;
	
	@Column(name="tzly")
    private String tzly;

	@Column(name="bwqk")
    private String bwqk;

	@Column(name="kffs")
    private String kffs;

	@Column(name="qskhdjl")
    private String qskhdjl;

	@Column(name="jszt")
    private String jszt;

	@Column(name="dysj")
    private Date dysj;

	@Column(name="ccyy")
    private String ccyy;

	@Column(name="sfykzstxf")
    private String sfykzstxf;

	@Column(name="sjfdl")
    private BigDecimal sjfdl;

	@Column(name="sjfdlXz")
    private BigDecimal sjfdlXz;

	@Column(name="sfyxmhz")
    private String sfyxmhz;

	@Column(name="hzwjmcjch")
    private String hzwjmcjch;

	@Column(name="xmhzdw")
    private String xmhzdw;

	@Column(name="sffhgh")
    private String sffhgh;

	@Column(name="ghmc")
    private String ghmc;

	@Column(name="ghshsj")
    private Date ghshsj;

	@Column(name="ghspbm")
    private String ghspbm;

	@Column(name="sffhghhp")
    private String sffhghhp;

	@Column(name="ghhpwj")
    private String ghhpwj;

	@Column(name="scwjwh")
    private String scwjwh;

	@Column(name="ghhpscbm")
    private String ghhpscbm;

	@Column(name="sftgxmhp")
    private String sftgxmhp;

	@Column(name="spwjmc")
    private String spwjmc;

	@Column(name="spwjwh")
    private String spwjwh;

	@Column(name="hpspsj")
    private Date hpspsj;

	@Column(name="ppbm")
    private String ppbm;

	@Column(name="sftgjghbys")
    private String sftgjghbys;

	@Column(name="yswjmc")
    private String yswjmc;

	@Column(name="yswjwh")
    private String yswjwh;

	@Column(name="ysbm")
    private String ysbm;

	@Column(name="stllxfcs")
    private String stllxfcs;

	@Column(name="stlljkss")
    private String stlljkss;

	@Column(name="gycs")
    private String gycs;

	@Column(name="zzflcs")
    private String zzflcs;

	@Column(name="qthbcs")
    private String qthbcs;

	@Column(name="qthbcsms")
    private String qthbcsms;

	@Column(name="sfsjzrbhq")
    private String sfsjzrbhq;

	@Column(name="zrbhqslsj")
    private String zrbhqslsj;

	@Column(name="zrbhqjb")
    private String zrbhqjb;

	@Column(name="hxq")
    private String hxq;

	@Column(name="hcq")
    private String hcq;

	@Column(name="syq")
    private String syq;

	@Column(name="wfq")
    private String wfq;

	@Column(name="bxsfcztsgk")
    private String bxsfcztsgk;

	@Column(name="tshdcd")
    private BigDecimal tshdcd;

	@Column(name="qtsthjwt")
    private String qtsthjwt;

	@Column(name="tbr")
    private String tbr;

	@Column(name="dh")
    private String dh;

	@Column(name="tbsj")
    private Date tbsj;

	@Column(name="xchcr")
    private String xchcr;

	@Column(name="ywjmc")
    private String ywjmc;

	@Column(name="dzzs")
    private Integer dzzs;

	@Column(name="tcny")
    private String tcny;

	@Column(name="zhltqk")
    private String zhltqk;

	@Column(name="zrk")
    private BigDecimal zrk;

	@Column(name="bg")
    private BigDecimal bg;

	@Column(name="swdj")
    private BigDecimal swdj;

	@Column(name="ghspjwh")
    private String ghspjwh;

	@Column(name="jsfaspbmjwh")
    private String jsfaspbmjwh;

	@Column(name="pzkmbmjwh")
    private String pzkmbmjwh;

	@Column(name="hpjhbyswh")
    private String hpjhbyswh;

	@Column(name="sbjwh")
    private String sbjwh;

	@Column(name="ydpcjwh")
    private String ydpcjwh;

	@Column(name="sfjddzzhwxx")
    private String sfjddzzhwxx;

	@Column(name="ysbmjwh")
    private String ysbmjwh;

	@Column(name="xmfrmc")
    private String xmfrmc;

	@Column(name="zytzr")
    private String zytzr;

	@Column(name="lxdh")
    private String lxdh;

	@Column(name="bz")
    private String bz;

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

    public BigDecimal getDj() {
        return dj;
    }

    public void setDj(BigDecimal dj) {
        this.dj = dj;
    }

    public BigDecimal getBw() {
        return bw;
    }

    public void setBw(BigDecimal bw) {
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

    public BigDecimal getZjrl() {
        return zjrl;
    }

    public void setZjrl(BigDecimal zjrl) {
        this.zjrl = zjrl;
    }

    public BigDecimal getZtz() {
        return ztz;
    }

    public void setZtz(BigDecimal ztz) {
        this.ztz = ztz;
    }

    public BigDecimal getSjnfdl() {
        return sjnfdl;
    }

    public void setSjnfdl(BigDecimal sjnfdl) {
        this.sjnfdl = sjnfdl;
    }

    public BigDecimal getSjnfdlXz() {
        return sjnfdlXz;
    }

    public void setSjnfdlXz(BigDecimal sjnfdlXz) {
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

    public String getSfykzstxf() {
        return sfykzstxf;
    }

    public void setSfykzstxf(String sfykzstxf) {
        this.sfykzstxf = sfykzstxf == null ? null : sfykzstxf.trim();
    }

    public BigDecimal getSjfdl() {
        return sjfdl;
    }

    public void setSjfdl(BigDecimal sjfdl) {
        this.sjfdl = sjfdl;
    }

    public BigDecimal getSjfdlXz() {
        return sjfdlXz;
    }

    public void setSjfdlXz(BigDecimal sjfdlXz) {
        this.sjfdlXz = sjfdlXz;
    }

    public String getSfyxmhz() {
        return sfyxmhz;
    }

    public void setSfyxmhz(String sfyxmhz) {
        this.sfyxmhz = sfyxmhz == null ? null : sfyxmhz.trim();
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

    public String getSffhgh() {
        return sffhgh;
    }

    public void setSffhgh(String sffhgh) {
        this.sffhgh = sffhgh == null ? null : sffhgh.trim();
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

    public String getSffhghhp() {
        return sffhghhp;
    }

    public void setSffhghhp(String sffhghhp) {
        this.sffhghhp = sffhghhp == null ? null : sffhghhp.trim();
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

    public String getSftgxmhp() {
        return sftgxmhp;
    }

    public void setSftgxmhp(String sftgxmhp) {
        this.sftgxmhp = sftgxmhp == null ? null : sftgxmhp.trim();
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

    public String getSftgjghbys() {
        return sftgjghbys;
    }

    public void setSftgjghbys(String sftgjghbys) {
        this.sftgjghbys = sftgjghbys == null ? null : sftgjghbys.trim();
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

    public String getStllxfcs() {
        return stllxfcs;
    }

    public void setStllxfcs(String stllxfcs) {
        this.stllxfcs = stllxfcs == null ? null : stllxfcs.trim();
    }

    public String getStlljkss() {
        return stlljkss;
    }

    public void setStlljkss(String stlljkss) {
        this.stlljkss = stlljkss == null ? null : stlljkss.trim();
    }

    public String getGycs() {
        return gycs;
    }

    public void setGycs(String gycs) {
        this.gycs = gycs == null ? null : gycs.trim();
    }

    public String getZzflcs() {
        return zzflcs;
    }

    public void setZzflcs(String zzflcs) {
        this.zzflcs = zzflcs == null ? null : zzflcs.trim();
    }

    public String getQthbcs() {
        return qthbcs;
    }

    public void setQthbcs(String qthbcs) {
        this.qthbcs = qthbcs == null ? null : qthbcs.trim();
    }

    public String getQthbcsms() {
        return qthbcsms;
    }

    public void setQthbcsms(String qthbcsms) {
        this.qthbcsms = qthbcsms == null ? null : qthbcsms.trim();
    }

    public String getSfsjzrbhq() {
        return sfsjzrbhq;
    }

    public void setSfsjzrbhq(String sfsjzrbhq) {
        this.sfsjzrbhq = sfsjzrbhq == null ? null : sfsjzrbhq.trim();
    }

    public String getZrbhqslsj() {
        return zrbhqslsj;
    }

    public void setZrbhqslsj(String zrbhqslsj) {
        this.zrbhqslsj = zrbhqslsj == null ? null : zrbhqslsj.trim();
    }

    public String getZrbhqjb() {
        return zrbhqjb;
    }

    public void setZrbhqjb(String zrbhqjb) {
        this.zrbhqjb = zrbhqjb == null ? null : zrbhqjb.trim();
    }

    public String getHxq() {
        return hxq;
    }

    public void setHxq(String hxq) {
        this.hxq = hxq == null ? null : hxq.trim();
    }

    public String getHcq() {
        return hcq;
    }

    public void setHcq(String hcq) {
        this.hcq = hcq == null ? null : hcq.trim();
    }

    public String getSyq() {
        return syq;
    }

    public void setSyq(String syq) {
        this.syq = syq == null ? null : syq.trim();
    }

    public String getWfq() {
        return wfq;
    }

    public void setWfq(String wfq) {
        this.wfq = wfq == null ? null : wfq.trim();
    }

    public String getBxsfcztsgk() {
        return bxsfcztsgk;
    }

    public void setBxsfcztsgk(String bxsfcztsgk) {
        this.bxsfcztsgk = bxsfcztsgk == null ? null : bxsfcztsgk.trim();
    }

    public BigDecimal getTshdcd() {
        return tshdcd;
    }

    public void setTshdcd(BigDecimal tshdcd) {
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

    public BigDecimal getZrk() {
        return zrk;
    }

    public void setZrk(BigDecimal zrk) {
        this.zrk = zrk;
    }

    public BigDecimal getBg() {
        return bg;
    }

    public void setBg(BigDecimal bg) {
        this.bg = bg;
    }

    public BigDecimal getSwdj() {
        return swdj;
    }

    public void setSwdj(BigDecimal swdj) {
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

    public String getSfjddzzhwxx() {
        return sfjddzzhwxx;
    }

    public void setSfjddzzhwxx(String sfjddzzhwxx) {
        this.sfjddzzhwxx = sfjddzzhwxx == null ? null : sfjddzzhwxx.trim();
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