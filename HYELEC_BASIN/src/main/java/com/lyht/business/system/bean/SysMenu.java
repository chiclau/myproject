package com.lyht.business.system.bean;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.glassfish.gmbal.Description;

@Entity
@Table(name = "SYS_MENU")
@SuppressWarnings("restriction")
public class SysMenu implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Description(key="primary",value="主键")
	private Integer id;
	
	@Description(key="display",value="菜单编码")
	private String menuCode;
	
	@Description(key="display",value="菜单名称")
	private String menuName;
	
	@Description(key="display",value="全编码")
	private String fCode;
	
	@Description(key="display",value="短编码")
	private String sCode;
	
	@Description(key="display",value="上级编码")
	private String superCode;
	
	@Description(key="display",value="路径")
	private String menuUrl;
	
	@Description(key="display",value="图标")
	private String menuIcon;
	
	@Description(key="display",value="状态")
	private int state;
	
	public SysMenu() {
		this.id=0;
        this.menuCode = "";
        this.menuName = "";
        this.fCode = "";
        this.sCode = "";
        this.superCode = "";
        this.menuUrl = "";
        this.menuIcon = "";
        this.state = 1;
    }
	
	public SysMenu(
			Integer id,
			String menuCode,
			String menuName,
			String fCode,
			String sCode,
			String superCode,
			String menuUrl,
			String menuIcon,
	        int state){
		this.id=id;
		this.menuCode = menuCode;
        this.menuName = menuName;
        this.fCode = fCode;
        this.sCode = sCode;
        this.superCode = superCode;
        this.menuUrl = menuUrl;
        this.menuIcon = menuIcon;
        this.state = state;
	}
	
	@Override
    public String toString() {
    	String s="";
    	s=	"{";
    	s=s+"id="+id;
        s=s+",menuCode="+menuCode;
        s=s+" ,menuName="+menuName;
        s=s+" ,fCode="+fCode;
        s=s+" ,sCode="+sCode;
        s=s+" ,superCode="+superCode;
        s=s+" ,menuUrl="+menuUrl;
        s=s+" ,menuIcon="+menuIcon;
        s=s+" ,state="+state;
    	s=s+"}";
    	return s;
    }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id" )
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "MENU_CODE",length=50 )
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "MENU_NAME",length=50 )
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "FCODE",length=50 )
	public String getfCode() {
		return fCode;
	}

	public void setfCode(String fCode) {
		this.fCode = fCode;
	}

	@Column(name = "SCODE",length=50 )
	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	@Column(name = "SUPER_CODE",length=50 )
	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

	@Column(name = "MENU_URL",length=200 )
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "MENU_ICON",length=50 )
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name = "STATE")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
