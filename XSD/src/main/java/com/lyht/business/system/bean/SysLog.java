package com.lyht.business.system.bean;

import java.io.Serializable;
import javax.persistence.*;
/**
 *作者： 陈震宇
 *脚本日期:2017年7月29日 23:41:11
 *说明:  系统日志
*/
@Entity
@Table(name = "SYS_LOG")
public class SysLog implements Serializable{

	private static final long serialVersionUID = 1L;
    /**
    *主键
    */
    private Integer id;  
    /**
    *内码
    */
    private String nm;  
    /**
    *操作时间
    */
    private String logtime;  
    /**
    *操作员
    */
    private String name;  
    /**
    *操作员内码
    */
    private String opernm;  
    /**
    *模块唯一标识
    */
    private String menuflag;  
    /**
    *操作类型
    */
    private String dictnmOpttype;  
    /**
    *旧数据
    */
    private String olddata;  
    /**
    *新数据
    */
    private String newdata;  
    /**
    *备注
    */
    private String memo;  
    /**
    *操作状态
    */
    private Integer flag;  
    /**
    *系统标识
    */
    private Integer sysflag;  
    
    /** default constructor */
    public SysLog() {
        
        this.id = 0;
        this.nm = "";
        this.logtime = "";
        this.name = "";
        this.opernm = "";
        this.menuflag = "";
        this.dictnmOpttype = "";
        this.olddata = "";
        this.newdata = "";
        this.memo = "";
        this.flag = 0;
        this.sysflag = 0;
    
    }
    /** full constructor */
    public SysLog(
                  Integer id ,
                  String nm ,
                  String logtime ,
                  String name ,
                  String opernm ,
                  String menuflag ,
                  String dictnmOpttype ,
                  String olddata ,
                  String newdata ,
                  String memo ,
                  Integer flag ,
                  Integer sysflag 
                  ) {
        this.id = id;
        this.nm = nm;
        this.logtime = logtime;
        this.name = name;
        this.opernm = opernm;
        this.menuflag = menuflag;
        this.dictnmOpttype = dictnmOpttype;
        this.olddata = olddata;
        this.newdata = newdata;
        this.memo = memo;
        this.flag = flag;
        this.sysflag = sysflag;
    }
    
    @Override
    public String toString() {
    	String s="";
    	s=	"{";
        s=s+"id="+id;
        s=s+" ,nm="+nm;
        s=s+" ,logtime="+logtime;
        s=s+" ,name="+name;
        s=s+" ,opernm="+opernm;
        s=s+" ,menuflag="+menuflag;
        s=s+" ,dictnmOpttype="+dictnmOpttype;
        s=s+" ,olddata="+olddata;
        s=s+" ,newdata="+newdata;
        s=s+" ,memo="+memo;
        s=s+" ,flag="+flag;
        s=s+" ,sysflag="+sysflag;
    	s=s+"}";
    	return s;
    }
 
    //属性get/set定义  

    //主键
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id"   
      
     , unique = true
     , nullable = false 
           )
    public  Integer getId() {
        return this.id;
    }
    public void setId( Integer id) {
        this.id = id;
    }

    //内码
    
    @Column(name = "nm"   
      ,length=50  
     , unique = true
     , nullable = false 
           )
    public  String getNm() {
        return this.nm;
    }
    public void setNm( String nm) {
        this.nm = nm;
    }

    //操作时间
    
    @Column(name = "logtime"   
      ,length=50  
     
      
           )
    public  String getLogtime() {
        return this.logtime;
    }
    public void setLogtime( String logtime) {
        this.logtime = logtime;
    }

    //操作员
    
    @Column(name = "name"   
      ,length=100  
     
      
           )
    public  String getName() {
        return this.name;
    }
    public void setName( String name) {
        this.name = name;
    }

    //操作员内码
    
    @Column(name = "opernm"   
      ,length=50  
     
      
           )
    public  String getOpernm() {
        return this.opernm;
    }
    public void setOpernm( String opernm) {
        this.opernm = opernm;
    }

    //模块唯一标识
    
    @Column(name = "menuflag"   
      ,length=50  
     
      
           )
    public  String getMenuflag() {
        return this.menuflag;
    }
    public void setMenuflag( String menuflag) {
        this.menuflag = menuflag;
    }

    //操作类型
    
    @Column(name = "dictnm_opttype"   
      ,length=50  
     
      
           )
    public  String getDictnmOpttype() {
        return this.dictnmOpttype;
    }
    public void setDictnmOpttype( String dictnmOpttype) {
        this.dictnmOpttype = dictnmOpttype;
    }

    //旧数据
    
    @Column(name = "olddata"   
      ,length=-1  
     
      
           )
    public  String getOlddata() {
        return this.olddata;
    }
    public void setOlddata( String olddata) {
        this.olddata = olddata;
    }

    //新数据
    
    @Column(name = "newdata"   
      ,length=-1  
     
      
           )
    public  String getNewdata() {
        return this.newdata;
    }
    public void setNewdata( String newdata) {
        this.newdata = newdata;
    }

    //备注
    
    @Column(name = "memo"   
      ,length=-1  
     
      
           )
    public  String getMemo() {
        return this.memo;
    }
    public void setMemo( String memo) {
        this.memo = memo;
    }

    //操作状态
    
    @Column(name = "flag"   
      
     
      
           )
    public  Integer getFlag() {
        return this.flag;
    }
    public void setFlag( Integer flag) {
        this.flag = flag;
    }

    //系统标识
    
    @Column(name = "sysflag"   
      
     
      
           )
    public  Integer getSysflag() {
        return this.sysflag;
    }
    public void setSysflag( Integer sysflag) {
        this.sysflag = sysflag;
    }
}