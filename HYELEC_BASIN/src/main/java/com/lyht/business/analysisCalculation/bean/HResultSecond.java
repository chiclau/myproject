package com.lyht.business.analysisCalculation.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *作者： czy
 *脚本日期:2018年7月12日 22:11:39
 *说明:  
*/
@Entity
@Table(name = "H_RESULT_SECOND")
public class HResultSecond implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String nm;
    private String stcd;
    private String pch;
    private double q;
    private double rs;
    private double tcj;
    private double rsj;
    private double lymj;
    private double l;
    private double j;
    private double js1;
    private double k;
    private double js2;
    private double qmk;
    private double m;
    private double tc;
    private double n;
    private double ertc;
	@Column(name="ERTC")
    public double getErtc() {
		return ertc;
	}
	public void setErtc(double ertc) {
		this.ertc = ertc;
	}
	/** default constructor */
    public HResultSecond() {
    
    }
    /** full constructor */
    
    public HResultSecond(
                  String pch ,
                  double q ,
                  double rs ,
                  double tcj ,
                  double rsj ,
                  double lymj ,
                  double l ,
                  double j ,
                  double js1 ,
                  double k ,
                  double js2 ,
                  double qmk ,
                  double m ,
                  double tc ,
                  double n ,
                  String nm ,
                  String stcd 
                  ) {
        super();
        this.pch = pch;
        this.q = q;
        this.rs = rs;
        this.tcj = tcj;
        this.rsj = rsj;
        this.lymj = lymj;
        this.l = l;
        this.j = j;
        this.js1 = js1;
        this.k = k;
        this.js2 = js2;
        this.qmk = qmk;
        this.m = m;
        this.tc = tc;
        this.n = n;
        this.nm = nm;
        this.stcd = stcd;
    }
 
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
    //属性get/set定义       		

    @Column(name = "PCH"   
      ,length=255  
     
      
           )
    
   
    public  String getPch() {
        return this.pch;
    }
    public void setPch( String pch) {
        this.pch = pch;
    }   

    @Column(name = "Q"   
      
     
      
           )
    
   
    public  double getQ() {
        return this.q;
    }
    public void setQ( double q) {
        this.q = q;
    }   

    @Column(name = "RS"   
      
     
      
           )
    
   
    public  double getRs() {
        return this.rs;
    }
    public void setRs( double rs) {
        this.rs = rs;
    }   

    @Column(name = "TCJ"   
      
     
      
           )
    
   
    public  double getTcj() {
        return this.tcj;
    }
    public void setTcj( double tcj) {
        this.tcj = tcj;
    }   

    @Column(name = "RSJ"   
      
     
      
           )
    
   
    public  double getRsj() {
        return this.rsj;
    }
    public void setRsj( double rsj) {
        this.rsj = rsj;
    }   

    @Column(name = "LYMJ"   
      
     
      
           )
    
   
    public  double getLymj() {
        return this.lymj;
    }
    public void setLymj( double lymj) {
        this.lymj = lymj;
    }   

    @Column(name = "L"   
      
     
      
           )
    
   
    public  double getL() {
        return this.l;
    }
    public void setL( double l) {
        this.l = l;
    }   

    @Column(name = "J"   
      
     
      
           )
    
   
    public  double getJ() {
        return this.j;
    }
    public void setJ( double j) {
        this.j = j;
    }   

    @Column(name = "JS1"   
      
     
      
           )
    
   
    public  double getJs1() {
        return this.js1;
    }
    public void setJs1( double js1) {
        this.js1 = js1;
    }   

    @Column(name = "K"   
      
     
      
           )
    
   
    public  double getK() {
        return this.k;
    }
    public void setK( double k) {
        this.k = k;
    }   

    @Column(name = "JS2"   
      
     
      
           )
    
   
    public  double getJs2() {
        return this.js2;
    }
    public void setJs2( double js2) {
        this.js2 = js2;
    }   

    @Column(name = "QMK"   
      
     
      
           )
    
   
    public  double getQmk() {
        return this.qmk;
    }
    public void setQmk( double qmk) {
        this.qmk = qmk;
    }   

    @Column(name = "M"   
      
     
      
           )
    
   
    public  double getM() {
        return this.m;
    }
    public void setM( double m) {
        this.m = m;
    }   

    @Column(name = "TC"   
      
     
      
           )
    
   
    public  double getTc() {
        return this.tc;
    }
    public void setTc( double tc) {
        this.tc = tc;
    }   

    @Column(name = "N"   
      
     
      
           )
    
   
    public  double getN() {
        return this.n;
    }
    public void setN( double n) {
        this.n = n;
    }   

    @Column(name = "STCD"   
      ,length=255 )
    public  String getStcd() {
        return this.stcd;
    }
    public void setStcd( String stcd) {
        this.stcd = stcd;
    }   
    
    
}
