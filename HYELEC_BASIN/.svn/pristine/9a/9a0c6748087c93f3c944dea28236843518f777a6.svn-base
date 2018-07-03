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
	obj1.put("RZ", "200m");
	obj1.put("INQ", "20万m³");
	obj1.put("OTQ", "21万m³");
	obj1.put("RWCHRCD", "W");
	obj1.put("RWPTN", "0.123Mpa");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>