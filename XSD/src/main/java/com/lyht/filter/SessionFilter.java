package com.lyht.filter;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyht.Constants;
import com.lyht.business.system.bean.SysUser;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONObject;
public class SessionFilter implements Filter {

	private FilterConfig config;

    public SessionFilter() {
        
    }
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

	public void destroy() {
		
	}

	/**
	 *   过滤器
	 */
	public void doFilter(ServletRequest mHttpServletRequest, ServletResponse mHttpServletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) mHttpServletRequest;
		HttpServletResponse response = (HttpServletResponse) mHttpServletResponse;
		
		String path=request.getContextPath()+"/";
		HttpSession session = (HttpSession) request.getSession();
		SysUser mSysUser = (SysUser) session.getAttribute(Constants.SESSION_ACCT);
		
		String login = path+this.config.getInitParameter("login"); //登录
		String validateAccount = path+ this.config.getInitParameter("validateAccount"); //验证账号
		String initAddvcd= path+ this.config.getInitParameter("initAddvcd"); //加载省份
		String loadCityData=path+ this.config.getInitParameter("loadCityData"); //加载市区
		
		String[] array = {login,validateAccount,initAddvcd,loadCityData};
		if (null!=mSysUser || contains(array, request.getRequestURI())) {
			chain.doFilter(mHttpServletRequest, mHttpServletResponse);
		} else{
//			Hashtable hashtable = new Hashtable();
//			hashtable.put("retflag", "SessionTimeOut");
//			hashtable.put("message", "当前系统没有登录信息，需要重新登录！");
//			JSONObject json = new JSONObject().fromObject(hashtable);
//			CommonFunction.writeResponse(response, json.toString());
			
			response.sendRedirect(login);
		}
	}
	
	private boolean contains(String[] array, String searchString) {
		if (array == null || array.length == 0 || searchString == null)
			return false;
		for (String arr : array) {
			if (searchString.equalsIgnoreCase(arr))
				return true;
		}
		return false;
	}


}
