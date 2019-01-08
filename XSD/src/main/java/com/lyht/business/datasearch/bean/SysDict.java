/**
 * 
 */
package com.lyht.business.datasearch.bean;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "zd_sum")
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id private Integer id;
    
    @Column(name = "nm") private String nm;
    
    @Column(name = "code") private String code;
    
    @Column(name = "name") private String name;
    
    @Column(name = "memo") private String memo;
    
    @Column(name = "flag") private Integer flag;
    
    @Column(name = "listnm_sys_dict_cate") private String listnmSysDictCate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getListnmSysDictCate() {
        return listnmSysDictCate;
    }

    public void setListnmSysDictCate(String listnmSysDictCate) {
        this.listnmSysDictCate = listnmSysDictCate;
    }
    
}
