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
	obj1.put("VTAVSLM", "12%");
	obj1.put("CRPTY", "药用作物");
	obj1.put("SLTP", "红壤");
	obj1.put("DRSLD", "2cm");
	obj1.put("SLMMMT", "称重法");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>