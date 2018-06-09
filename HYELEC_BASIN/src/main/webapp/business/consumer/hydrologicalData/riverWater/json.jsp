<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("ID", "1");
	obj1.put("STCD", "BM20180101");
	obj1.put("TM", "2018-11-12 12:12:23");
	obj1.put("Z", "120m");
	obj1.put("Q", "1200m³/s");
	obj1.put("FLWCHRCD", "T");
	obj1.put("WPTN", "0.12MPa");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>