/**
 * 
 */
package com.lyht.business.datasearch.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "zd_sum")
public class ZdSum implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id private Integer id;
    
    @Column(name = "jszt") private String jszt;
    
    @Column(name = "kffs") private String kffs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJszt() {
        return jszt;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt;
    }

    public String getKffs() {
        return kffs;
    }

    public void setKffs(String kffs) {
        this.kffs = kffs;
    }

}
