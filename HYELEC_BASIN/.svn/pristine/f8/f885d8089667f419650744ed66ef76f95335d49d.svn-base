package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SD_LYSXJGX")
@SuppressWarnings("restriction")
public class Ennmcd implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="主键")
	private int id;
	
	@Description(key="primary",value="流域编码")
	private String rvcd;
	
	@Description(key="display",value="流域名称")
	private String rvnm;
	
	@Description(key="display",value="上级编码")
	private String prvcd;
	
	@Description(key="display",value="编码拼接")
	private String path;
	
	@Description(key="display",value="排序")
	private String paixu;
	
	@Description(key="display",value="经度")
	private String lgtd;
	
	@Description(key="display",value="纬度")
	private String lttd;
	
	public Ennmcd() {
		this.id=0;
        this.rvcd = "";
        this.rvnm = "";
        this.prvcd ="";
        this.path = "";
        this.paixu = "";
        this.lgtd = "";
        this.lttd = "";
    }

	public Ennmcd(
			int id,
			String rvcd,
			String rvnm,
			String prvcd,
			String path,
			String paixu,
			String lgtd,
			String lttd){
		this.id=0;
        this.rvcd = rvcd;
        this.rvnm = rvnm;
        this.prvcd =prvcd;
        this.path = path;
        this.paixu = paixu;
        this.lgtd = lgtd;
        this.lttd = lttd;
	}
	
	@Override
	public String toString() {
		String s="";
    	s="{";
    	s=s+"id="+id;
        s=s+",rvcd="+rvcd;
        s=s+",rvnm="+rvnm;
        s=s+",prvcd="+prvcd;
        s=s+",path="+path;
        s=s+",paixu="+paixu;
        s=s+",lgtd="+lgtd;
        s=s+",lttd="+lttd;
    	s=s+"}";
    	return s;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id" )
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Id
	@Column(name = "RVCD",length=12 )
	public String getRvcd() {
		return rvcd;
	}

	public void setRvcd(String rvcd) {
		this.rvcd = rvcd;
	}

	@Column(name = "RVNM",length=40 )
	public String getRvnm() {
		return rvnm;
	}

	public void setRvnm(String rvnm) {
		this.rvnm = rvnm;
	}

	@Column(name = "PRVCD",length=8 )
	public String getPrvcd() {
		return prvcd;
	}

	public void setPrvcd(String prvcd) {
		this.prvcd = prvcd;
	}

	@Column(name = "PATH",length=50 )
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "PAIXU",length=20 )
	public String getPaixu() {
		return paixu;
	}

	public void setPaixu(String paixu) {
		this.paixu = paixu;
	}

	@Column(name = "LGTD",length=255 )
	public String getLgtd() {
		return lgtd;
	}

	public void setLgtd(String lgtd) {
		this.lgtd = lgtd;
	}

	@Column(name = "LTTD",length=255 )
	public String getLttd() {
		return lttd;
	}

	public void setLttd(String lttd) {
		this.lttd = lttd;
	}
	
}
