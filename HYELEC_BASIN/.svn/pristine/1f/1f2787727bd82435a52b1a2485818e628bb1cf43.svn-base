<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("ID", "1");
	obj1.put("STCD", "BM20180101");
	obj1.put("LNNM", "水位流量曲线");
	obj1.put("PTNO", "001");
	obj1.put("Z", "20m");
	obj1.put("Q", "2000m³");
	obj1.put("MNTRTM", "2018-01-01 12:24");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>