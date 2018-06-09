<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("ID", "1");
	obj1.put("STCD", "BM20180101");
	obj1.put("MSTM", "2018-01-01 12:24:07");
	obj1.put("VTNO", "001");
	obj1.put("DI", "20m");
	obj1.put("ZB", "200m");
	obj1.put("MODITIME", "2018-01-01 12:24");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>