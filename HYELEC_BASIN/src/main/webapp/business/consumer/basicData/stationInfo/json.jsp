<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("ID", "1");
	obj1.put("STCD", "BM20180101");
	obj1.put("STNM", "湘江流域第一测站");
	obj1.put("DTMNM", "长沙测站面");
	obj1.put("DTMEL", "50M");
	obj1.put("STTP", "综合测站");
	obj1.put("ADMAUTH", "长沙水文局");
	obj1.put("ESSTYM", "2011-12-11");
	obj1.put("FRGRD", "VI");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>