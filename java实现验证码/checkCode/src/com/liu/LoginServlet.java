package com.liu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String piccode=(String) req.getSession().getAttribute("piccode");
	String checkcode=req.getParameter("checkcode");
	checkcode=checkcode.toUpperCase();
	resp.setContentType("text/html;charset=gbk");
	PrintWriter out =resp.getWriter();
	 if(checkcode.equals(piccode)) {
		out.println("验证码输入正确！");
	 }else {
		out.print("验证码输入错误！！！！");
	 }
	 out.flush();
	 out.close();
	}

}
