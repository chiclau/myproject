<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	Hashtable table = new Hashtable();
	JSONObject obj1=new JSONObject();
	List list = new ArrayList();
	obj1.put("ID", "1");
	obj1.put("UPSTCD", "BM20180101");
	obj1.put("DWSTCD", "BM20180102");
	obj1.put("RCHLEN", "120km");
	obj1.put("SFTQ", "4000m³");
	obj1.put("QMGN", "III");
	obj1.put("MNTRTM", "4h");
	obj1.put("MXTRTM", "24h");
	obj1.put("AVTRTM", "11h");
	list.add(obj1);
	
	table.put("rows", list);
	table.put("retflag", "success");
	table.put("totle", 1);
	table.put("total", 1);
	JSONObject json = new JSONObject().fromObject(table);
	out.print(json.toString());
%>