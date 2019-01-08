package com.lyht.business.datasearch.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.datasearch.formbean.DataSerchFormBean;
import com.lyht.business.datasearch.service.DataSearchService;

/**
 * 数据检索
 * @author 刘魁
 *@创建时间 2018/10/10
 */
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class DataSearchControl {
    
    @Resource
    private DataSearchService service;

    /**
     * 查询所有
     */
    @Optlog(menuflag="sjjs", opttype = "getSjjsMes") 
    public RetMessage listSearch(DataSerchFormBean formBean, PageResults mPageResults,Integer page,Integer limit) {
        RetMessage mRetMessage=new RetMessage();
        try {
            BeanUtils.copyProperties(mPageResults,service.listSearchData(formBean,page,limit));
            mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
            mRetMessage.setMessage("查询数据成功！");
        } catch (Exception e) {
            mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
            mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
        }
        return mRetMessage;
    }

}
