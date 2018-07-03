package com.lyht.business.consumer.hydrologicalData.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "ST_SOIL_R")
@SuppressWarnings("restriction")
public class Soil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Description(key="primary",value="测站编码")
	private String stcd;
	
	@Description(key="display",value="时间")
	private String tm;
	
	@Description(key="display",value="扩展关键字")
	private String exkey;
	
	@Description(key="display",value="垂线平均含水率")
	private double vtavslm;
	
	@Description(key="display",value="表层含水率")
	private double srlslm;

	@Description(key="display",value="10cm深度含水率")
	private double slm10;
	
	@Description(key="display",value="20cm深度含水率")
	private double slm20;
	
	@Description(key="display",value="30cm深度含水率")
	private double slm30;
	
	@Description(key="display",value="40cm深度含水率")
	private double slm40;
	
	@Description(key="display",value="50cm深度含水率")
	private double slm50;
	
	@Description(key="display",value="60cm深度含水率")
	private double slm60;
	
	@Description(key="display",value="作物种类")
	private String crpty;
	
	@Description(key="display",value="作物生长期")
	private String crpgrwprd;
	
	@Description(key="display",value="作物受灾原因")
	private String hitrsn;
	
	@Description(key="display",value="作物受灾程度")
	private String hitext;
	
	@Description(key="display",value="土壤类别")
	private String sltp;
	
	@Description(key="display",value="土壤质地")
	private String slpq;
	
	@Description(key="display",value="干土层厚度")
	private double drsld;
	
	@Description(key="display",value="灌溉相隔天数")
	private double irrintv;
	
	@Description(key="display",value="降雨相隔天数")
	private double pintv;
	
	@Description(key="display",value="土壤含水率测法")
	private String slmmmt;

	public Soil() {
		this.stcd="";
		this.tm="";
		this.exkey="";
		this.vtavslm=0;
		this.srlslm=0;
		this.slm10=0;
		this.slm20=0;
		this.slm30=0;
		this.slm40=0;
		this.slm50=0;
		this.slm60=0;
		this.crpty="";
		this.crpgrwprd="";
		this.hitrsn="";
		this.hitext="";
		this.sltp="";
		this.slpq="";
		this.drsld=0;
		this.irrintv=0;
		this.pintv=0;
		this.slmmmt="";
	}

	public Soil( 
			String stcd, 
			String tm, 
			String exkey, 
			double vtavslm, 
			double srlslm, 
			double slm10, 
			double slm20,
			double slm30, 
			double slm40, 
			double slm50, 
			double slm60, 
			String crpty, 
			String crpgrwprd, 
			String hitrsn,
			String hitext, 
			String sltp, 
			String slpq, 
			double drsld, 
			double irrintv, 
			double pintv, 
			String slmmmt) {
		super();
		this.stcd=stcd;
		this.tm=tm;
		this.exkey=exkey;
		this.vtavslm=vtavslm;
		this.srlslm=srlslm;
		this.slm10=slm10;
		this.slm20=slm20;
		this.slm30=slm30;
		this.slm40=slm40;
		this.slm50=slm50;
		this.slm60=slm60;
		this.crpty=crpty;
		this.crpgrwprd=crpgrwprd;
		this.hitrsn=hitrsn;
		this.hitext=hitext;
		this.sltp=sltp;
		this.slpq=slpq;
		this.drsld=drsld;
		this.irrintv=irrintv;
		this.pintv=pintv;
		this.slmmmt=slmmmt;
	}
	
	@Id
	@Column(name = "STCD",length=8 )
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	@Column(name = "TM" )
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}
	@Column(name = "EXKEY",length=1 )
	public String getExkey() {
		return exkey;
	}

	public void setExkey(String exkey) {
		this.exkey = exkey;
	}
	@Column(name = "VTAVSLM",length=4 )
	public double getVtavslm() {
		return vtavslm;
	}

	public void setVtavslm(double vtavslm) {
		this.vtavslm = vtavslm;
	}
	@Column(name = "SRLSLM",length=4 )
	public double getSrlslm() {
		return srlslm;
	}

	public void setSrlslm(double srlslm) {
		this.srlslm = srlslm;
	}
	@Column(name = "SLM10",length=4 )
	public double getSlm10() {
		return slm10;
	}

	public void setSlm10(double slm10) {
		this.slm10 = slm10;
	}
	
	@Column(name = "SLM20",length=4 )
	public double getSlm20() {
		return slm20;
	}

	public void setSlm20(double slm20) {
		this.slm20 = slm20;
	}
	
	@Column(name = "SLM30",length=4 )
	public double getSlm30() {
		return slm30;
	}

	public void setSlm30(double slm30) {
		this.slm30 = slm30;
	}
	@Column(name = "SLM40",length=4 )
	public double getSlm40() {
		return slm40;
	}

	public void setSlm40(double slm40) {
		this.slm40 = slm40;
	}
	@Column(name = "SLM50",length=4 )
	public double getSlm50() {
		return slm50;
	}

	public void setSlm50(double slm50) {
		this.slm50 = slm50;
	}
	@Column(name = "SLM60",length=4 )
	public double getSlm60() {
		return slm60;
	}

	public void setSlm60(double slm60) {
		this.slm60 = slm60;
	}
	
	@Column(name = "CRPTY",length=1 )
	public String getCrpty() {
		return crpty;
	}

	public void setCrpty(String crpty) {
		this.crpty = crpty;
	}
	@Column(name = "CRPGRWPRD",length=1 )
	public String getCrpgrwprd() {
		return crpgrwprd;
	}

	public void setCrpgrwprd(String crpgrwprd) {
		this.crpgrwprd = crpgrwprd;
	}
	@Column(name = "HITRSN",length=1 )
	public String getHitrsn() {
		return hitrsn;
	}

	public void setHitrsn(String hitrsn) {
		this.hitrsn = hitrsn;
	}
	@Column(name = "HITEXT",length=1 )
	public String getHitext() {
		return hitext;
	}

	public void setHitext(String hitext) {
		this.hitext = hitext;
	}
	@Column(name = "SLTP",length=1 )
	public String getSltp() {
		return sltp;
	}

	public void setSltp(String sltp) {
		this.sltp = sltp;
	}
	@Column(name = "SLPQ",length=2 )
	public String getSlpq() {
		return slpq;
	}

	public void setSlpq(String slpq) {
		this.slpq = slpq;
	}
	@Column(name = "DRSLD",length=4 )
	public double getDrsld() {
		return drsld;
	}

	public void setDrsld(double drsld) {
		this.drsld = drsld;
	}
	@Column(name = "IRRINTV",length=3 )
	public double getIrrintv() {
		return irrintv;
	}

	public void setIrrintv(double irrintv) {
		this.irrintv = irrintv;
	}
	@Column(name = "PINTV",length=3 )
	public double getPintv() {
		return pintv;
	}

	public void setPintv(double pintv) {
		this.pintv = pintv;
	}
	@Column(name = "SLMMMT",length=1 )
	public String getSlmmmt() {
		return slmmmt;
	}

	public void setSlmmmt(String slmmmt) {
		this.slmmmt = slmmmt;
	}

	@Override
	public String toString() {
		return "Soil [stcd=" + stcd + ", tm=" + tm + ", exkey=" + exkey + ", vtavslm=" + vtavslm + ", srlslm=" + srlslm
				+ ", slm10=" + slm10 + ", slm20=" + slm20 + ", slm30=" + slm30 + ", slm40=" + slm40 + ", slm50=" + slm50
				+ ", slm60=" + slm60 + ", crpty=" + crpty + ", crpgrwprd=" + crpgrwprd + ", hitrsn=" + hitrsn
				+ ", hitext=" + hitext + ", sltp=" + sltp + ", slpq=" + slpq + ", drsld=" + drsld + ", irrintv="
				+ irrintv + ", pintv=" + pintv + ", slmmmt=" + slmmmt + "]";
	}

	
}
