package com.lyht.business.analysisCalculation.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *作者： czy
 *脚本日期:2018年7月13日 16:49:22
 *说明:  
*/
@Entity
@Table(name = "H_RESULT_THRID")
public class HResultThrid implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String nm;
    private String pch;
    private String stcd;
    private Double lymj;
	private Double rsj;
    private Double tcj;
    private Double l;
    private Double j;
    private Double qj;
    private Double tc;
    private Double qm;
    private Double m;
    private Double qf;
	private Double qmf;
	private Double m3;//第三步计算的m值
	
	private Double qmsj;
	
	private Double qms;
	
	private String jsjg;
    
	/** default constructor */
    public HResultThrid() {
    
    }
    /** full constructor */
    
    public HResultThrid(
                  String nm ,
                  String pch ,
                  String stcd ,
                  Double lymj ,
                  Double rsj ,
                  Double tcj ,
                  Double l ,
                  Double j ,
                  Double qj ,
                  Double tc ,
                  Double qm ,
                  Double m ,
                  Double qmf 
                  ) {
        super();
        this.nm = nm;
        this.pch = pch;
        this.stcd = stcd;
        this.lymj = lymj;
        this.rsj = rsj;
        this.tcj = tcj;
        this.l = l;
        this.j = j;
        this.qj = qj;
        this.tc = tc;
        this.qm = qm;
        this.m = m;
        this.qmf = qmf;
    }
 
    //属性get/set定义       		

    @Id
    @Column(name = "NM"   
      ,length=255  
     , unique = true
     , nullable = false 
           )
    
   
    public  String getNm() {
        return this.nm;
    }
    public void setNm( String nm) {
        this.nm = nm;
    }   

    @Column(name = "PCH"   
      ,length=255  
     
      
           )
    
   
    public  String getPch() {
        return this.pch;
    }
    public void setPch( String pch) {
        this.pch = pch;
    }   

    @Column(name = "STCD"   
      ,length=255  
     
      
           )
    
   
    public  String getStcd() {
        return this.stcd;
    }
    public void setStcd( String stcd) {
        this.stcd = stcd;
    }   

    @Column(name = "LYMJ"   
      
     
      
           )
    
   
    public  Double getLymj() {
        return this.lymj;
    }
    public void setLymj( Double lymj) {
        this.lymj = lymj;
    }   

    @Column(name = "RSJ"   
      
     
      
           )
    
   
    public  Double getRsj() {
        return this.rsj;
    }
    public void setRsj( Double rsj) {
        this.rsj = rsj;
    }   

    @Column(name = "TCJ"   
      
     
      
           )
    
   
    public  Double getTcj() {
        return this.tcj;
    }
    public void setTcj( Double tcj) {
        this.tcj = tcj;
    }   

    @Column(name = "L"   
      
     
      
           )
    
   
    public  Double getL() {
        return this.l;
    }
    public void setL( Double l) {
        this.l = l;
    }   

    @Column(name = "J"   
      
     
      
           )
    
   
    public  Double getJ() {
        return this.j;
    }
    public void setJ( Double j) {
        this.j = j;
    }   

    @Column(name = "QJ"   
      
     
      
           )
    
   
    public  Double getQj() {
        return this.qj;
    }
    public void setQj( Double qj) {
        this.qj = qj;
    }   

    @Column(name = "TC"   
      
     
      
           )
    
   
    public  Double getTc() {
        return this.tc;
    }
    public void setTc( Double tc) {
        this.tc = tc;
    }   

    @Column(name = "QM"   
      
     
      
           )
    
   
    public  Double getQm() {
        return this.qm;
    }
    public void setQm( Double qm) {
        this.qm = qm;
    }   

    @Column(name = "M"   
      
     
      
           )
    
   
    public  Double getM() {
        return this.m;
    }
    public void setM( Double m) {
        this.m = m;
    }   

    @Column(name = "QMF"   
      
     
      
           )
    
   
    public  Double getQmf() {
        return this.qmf;
    }
    public void setQmf( Double qmf) {
        this.qmf = qmf;
    }   
    @Column(name="QF")
    public Double getQf() {
		return qf;
	}
	public void setQf(Double qf) {
		this.qf = qf;
	}
	@Column(name="JSJG")
    public String getJsjg() {
		return jsjg;
	}
	public void setJsjg(String jsjg) {
		this.jsjg = jsjg;
	}
	@Column(name="M3")
	public Double getM3() {
		return m3;
	}
	public void setM3(Double m3) {
		this.m3 = m3;
	}
	@Column(name="QMSJ")
    public Double getQmsj() {
		return qmsj;
	}
	public void setQmsj(Double qmsj) {
		this.qmsj = qmsj;
	}
	@Column(name="QMS")
	public Double getQms() {
		return qms;
	}
	public void setQms(Double qms) {
		this.qms = qms;
	}
}
