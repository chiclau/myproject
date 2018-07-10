package com.lyht.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * dojo ajax json格式转换
 * 
 * @author liuamang
 */
public class CommonFunction {

	/**
	 * dojo ajax 提交，增删改操作回调状态写入 success 或 error
	 * 
	 * @author liuamang
	 * @param response
	 * @param flag
	 */
	public static void writeResponse(HttpServletResponse response, boolean flag) {
		java.io.PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject();
		try {
			writer = response.getWriter();
			if (flag) {
				jsonObject.put("text", "success");
			} else {
				jsonObject.put("text", "error");
			}
			writer.print(jsonObject);
		} catch (Exception e) {
		} finally {
			writer.flush();
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * dojo ajax 提交，增删改操作回调状态写入 success 或 error
	 * 
	 * @author liuamang
	 * @param response
	 * @param flag
	 */
	public static void writeResponseText(HttpServletResponse response,
			String text) {
		java.io.PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject();
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			jsonObject.put("text", text);
			writer.print(jsonObject);
		} catch (Exception e) {
		} finally {
			writer.flush();
			if (writer != null)
				writer.close();
		}
	}

	/**
	 *  ajax 提交，增删改操作回调状态写入 单个对象
	 * 
	 * @param response
	 * @param object
	 */
	public static void writeResponse(HttpServletResponse response, Object object) {
		java.io.PrintWriter writer = null;
		try {
			JSONObject jsonObject = JSONObject.fromObject(object);
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.print(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.flush();
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * dojo ajax 提交，增删改操作回调状态写入 单个对象 集合类型
	 * 
	 * @param response
	 * @param list
	 * @param identifier
	 *            Grid 表示ID
	 */
	@SuppressWarnings("rawtypes")
	public static void writeResponse(HttpServletResponse response,
			Collection list, String identifier) {
		java.io.PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("identifier", identifier);
			JSONArray array = JSONArray.fromObject(list);
			jsonObject.put("items", array.toString());
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.print(jsonObject);
		} catch (Exception e) {
		} finally {
			writer.flush();
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * dojo ajax 提交，增删改操作回调状态写入 单个对象 集合类型
	 * 
	 * @param response
	 * @param list
	 * @param identifier
	 *            Grid 表示ID
	 */
	@SuppressWarnings("rawtypes")
	public static void writeResponse(HttpServletResponse response,
			Collection list, String identifier, String lable) {
		java.io.PrintWriter writer = null;
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("identifier", identifier);
			jsonObject.put("label", lable);
			JSONArray array = JSONArray.fromObject(list);
			jsonObject.put("items", array.toString());
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.print(jsonObject);
		} catch (Exception e) {

		} finally {
			writer.flush();
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * 向前台写入响应信息
	 * 
	 * @author 田泽忠
	 * @param message
	 */
	public static void writeResponse(HttpServletResponse response,
			String message) {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			if (out != null)
				out.close();
		}

	}
	
	@SuppressWarnings({ "static-access", "rawtypes" })
	public static void writeResponse(HttpServletResponse response,
			Hashtable hashtable) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONObject json = new JSONObject().fromObject(hashtable);
			out.print(json.toString());
			System.out.println("-----------"+json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			if (out != null)
				out.close();
		}

	}
	

}