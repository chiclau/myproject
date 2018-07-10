package com.lyht.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.lyht.Constants;

public class CommonUtil{
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	private final static DecimalFormat outdf = new DecimalFormat("0.00");
	
	/**
	 * 时分秒字符串比较,字符串格式为00:00:00,如果time1>=time2则返回true,否则返回false
	 * @return
	 */
	public static boolean compareTime(String time1,String time2){
		String date = getDateString(new Date());
		Date d1 = CommonUtil.parseDateTimeStringToDate(date+" "+time1);
		Date d2 = CommonUtil.parseDateTimeStringToDate(date+" "+time2);
		return d1.getTime()>=d2.getTime();
	}
	/**
	 * 比较时间格式字符串，带时分秒，dt1>=dt2 返回true
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static boolean compareDateTime(String dt1,String dt2){
		return parseDateTimeStringToDate(dt1).getTime()>=parseDateTimeStringToDate(dt2).getTime();
	}
	/**
	 * 字符串转化位float值
	 * @param str
	 * @return
	 */
	public static float parseFloatValue(String str){
		if(str==null || CommonUtil.trim(str).length()<1){
			return 0;
		}else{
			return Float.valueOf(str);
		}
	}
	/**
	 * 根据年份，周数获取该周开始日期
	 * @param year yyyy
	 * @param weeks
	 * @return
	 */
	public static Date getBeginDateOfWeek(String year,Integer weeks){
		String stand = year+"-01-01";
		Calendar cal = Calendar.getInstance();
		cal.setTime(CommonUtil.parseDateStringToDate(stand));
		int sweek = cal.get(Calendar.WEEK_OF_YEAR);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, (weeks-sweek)*7-dayOfWeek+1);
		return cal.getTime();
	}
	/**
	 * 根据年份，周数获取该周的结束日期
	 * @param year YYYY
	 * @param weeks
	 * @return
	 */
	public static Date getEndDateOfWeek(String year,Integer weeks){
		String stand = year+"-01-01";
		Calendar cal = Calendar.getInstance();
		cal.setTime(CommonUtil.parseDateStringToDate(stand));
		int sweek = cal.get(Calendar.WEEK_OF_YEAR);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, (weeks-sweek+1)*7-dayOfWeek);
		return cal.getTime(); 
	}
	/***
	 * 把字符串前后的空格去掉
	 * @params  要处理的字符串
	 * @return 去除前后前后空格的字符串
	 */
	public static String trim(String s) {
		if (s == null)
			return "";
		return s.trim();
	}
	
	/** 是否大于0 
	 *  大于0 返回 true 否则false
	 * @param value
	 * @return
	 */
	public static boolean isZreo(Integer value){
		if(value != null && value > 0)
			return true;
		return false;
	}
	/***
	 * 把一个Object对象转化为字符串
	 * @param o
	 * @return
	 */
	public static String trim(Object o) {
		if (o == null)
			return "";

		// 如果是数字
		if (o instanceof Number) {
			if (o instanceof Byte) {
				return ((Byte) o).toString();
			} else if (o instanceof Integer) {
				return ((Integer) o).toString();
			} else if (o instanceof Short) {
				return ((Short) o).toString();
			} else if (o instanceof Long) {
				return ((Long) o).toString();
			} else if (o instanceof Float) {
				return ((Float) o).toString();
			} else if (o instanceof Double) {
				return ((Double) o).toString();
			} else if (o instanceof BigInteger) {
				return ((BigInteger) o).toString();
			} else if (o instanceof BigDecimal) {
				return ((BigDecimal) o).toString();
			}
		}
		// 如果是日期
		if (o instanceof java.util.Date) {
			return getDateTimeString((java.util.Date) o);
		}

		if (o instanceof String) {
			return trim((String) o);
		}

		return o.getClass().getName() + "[" + o + "]";
	}

	/****
	 * 把一个日期时间型的字符串转化为日期;
	 * 
	 * @param s 日期时间型的字符串
	 * @return 若参数s为null或为"", 返回null;
	 *         若参数s的格式不能对应"yyyy-MM-dd HH:mm:ss"或不是一个合法的日期, 返回null; 否则返回字符串对应的日期;
	 */
	public static java.util.Date parseDateTimeStringToDate(String s) {
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		if (isEmpty(s = trim(s)))
			return null;
		try {
			return format.parse(s);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public static java.util.Date parseDateTimeStringContainMsToDate(String s) {
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
		if (isEmpty(s = trim(s)))
			return null;
		try {
			return format.parse(s);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***
	 * 把一个日期对象转化为日期时间字符串
	 * 
	 * @param d
	 * @return
	 */
	public static String getDateTimeString(java.util.Date d) {
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			return format.format(d);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/****
	 * 把一个日期型的字符串转化为日期;
	 * 
	 * @param s
	 *            日期型的字符串
	 * @return 若参数s为null或为"", 返回null; 若参数s的格式不能对应"yyyy-MM-dd"或不是一个合法的日期, 返回null;
	 *         否则返回字符串对应的日期;
	 */
	public static java.util.Date parseDateStringToDate(String s) {
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		if (isEmpty(s = trim(s)))
			return null;
		try {
			return format.parse(s);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***
	 * 把一个日期对象转化为日期字符串
	 * 
	 * @param d
	 * @return
	 */
	public static String getDateString(java.util.Date d) {
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			return format.format(d);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public static String filterXml(String source) {
		// '&' --> 字符'&'
		source = source.replaceAll("&", "&amp;");
		// '"' --> 双引号
		source = source.replaceAll("\"", "&quot;");
		// ''' --> 单引号
		source = source.replaceAll("\'", "&apos;");
		// '<' --> 小于号
		source = source.replaceAll("<", "&lt;");
		// '>' --> 大于号
		source = source.replaceAll(">", "&gt;");

		return source;
	}

	public static String filterHtml(String source) {
		// '&' --> 字符'&'
		source = source.replaceAll("&", "&amp;");
		// '<' --> 小于号
		source = source.replaceAll("<", "&lt;");
		// '>' --> 大于号
		source = source.replaceAll(">", "&gt;");
		// '"' --> 双引号
		source = source.replaceAll("\"", "&quot;");
		// ''' --> 单引号
		source = source.replaceAll("\'", "&#39;");
		// ' ' --> 空格
		source = source.replaceAll(" ", "&nbsp;");

		return source;
	}

	public static String isoToGbk(String source) {
		source = trim(source);
		try {
			return new String(source.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			return source;
		}
	}

	/***
	 * 判断一个字符串是否为null或为""
	 * 
	 * @param s
	 *            待判断的字符串
	 * @return 若参数s为null或为"", 返回true; 否则返回false;
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.length() == 0)
			return true;
		return false;
	}

	/****
	 * 返回!isEmpty(s)
	 * 
	 * @see isEmpty(s)
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {

		return !isEmpty(s);
	}

	/***
	 * 判断一个字符串是否超过给定长度
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static boolean isLongerThan(String s, int len) {
		int result = getLength(s);
		return result > len;
	}

	/***
	 * 为给定字符串截取一定长度的子串;
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static String getSubString(String s, int len) {
		if (isEmpty(s))
			return "";
		if (isLongerThan(s, len)) {
			StringBuffer buf = new StringBuffer();
			int cnt = 0;
			for (int i = 0; i < s.length(); i++) {
				int tmp = s.charAt(i);
				if (tmp >= 0 && tmp <= 127) {
					cnt++;
				} else
					cnt += 2;
				buf.append(s.charAt(i));

				if (len - cnt == 1) {
					tmp = s.charAt(i + 1);
					if (tmp < 0 || tmp > 127)
						break;
				}
				if (len - cnt == 0) {
					break;
				}
			}
			return buf.toString();
		} else {
			return s;
		}
	}

	/***
	 * 获得一个字符串的长度
	 * 
	 * @param s
	 * @return
	 */
	public static int getLength(String s) {
		if (isEmpty(s))
			return 0;
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			int tmp = s.charAt(i);
			if (tmp >= 0 && tmp <= 127) {
				result++;
			} else
				result += 2;
		}
		return result;
	}

	/***
	 * 判断是否是大小写字母,数字组成的字符串
	 * 
	 * @param s
	 * @return null or "" return true ;
	 */
	public static boolean isLetterOrDigit(String s) {
		if (s == null)
			return true;

		boolean result = true;
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isDigit(chars[i])
					&& !Character.isUpperCase(chars[i])
					&& !Character.isLowerCase(chars[i])) {
				result = false;
				break;
			}
		}

		return result;
	}

	/***
	 * 把字符串类型的数值转化为整型
	 * 
	 * @param s
	 * @return
	 */
	public static int getIntValue(String s) {
		try {
			s = trim(s);
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}

	/***
	 * 把字符串类型的数值转化为double类型
	 * 
	 * @param s
	 * @return
	 */
	public static double getFloatValue(String s) {
		try {
			s = trim(s);
			return Double.parseDouble(s);
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	/** 转换Object对象为两位小数
	 * @param obj
	 * @return
	 */
	public static String getDoubleValue(Object obj){
		String ss=  outdf.format(trimStr(trim(obj)));
		return ss;
	}
	
	/** 转换成double
	 * @param value
	 * @return
	 */
	public static double trimStr(String value){
		double ret = 0.00;
		try {
			if(CommonUtil.getLength(value) > 0 )
				ret = Double.parseDouble(value);
		} catch (Exception e) {
		}
		
		return ret ;
	}
	
	/** 转换成double
	 * @param value
	 * @return
	 */
	public static float trimStrF(String value){
		float ret = 0;
		try {
			if(CommonUtil.getLength(value) > 0 )
				ret = Float.parseFloat(value);
		} catch (Exception e) {
		}
		return ret ;
	}
	
	/****
	 * 把小数格式化为包含floatNum位小数的字符串
	 * 
	 * @param floatString
	 * @param floatNum
	 * @return
	 */
	public static String formatFloat(String floatString, int floatNum) {
		String pattern = "";
		if (getFloatValue(floatString) < 1) {
			pattern += "0";
		}
		pattern += ".";
		for (; floatNum > 0; floatNum--)
			pattern += "0";
		return formatFloat(getFloatValue(floatString), pattern);
	}

	/***
	 * 根据指定的格式格式化浮点数
	 * 
	 * @param d
	 * @param pattern
	 * @return
	 */
	public static String formatFloat(double d, String pattern) {
		java.text.NumberFormat format = new java.text.DecimalFormat(pattern);
		return format.format(d);
	}

	/**
	* 把数字转成EXCEL的列名
	* @param sum 大于0的整数数字
	* @param sb 用于接收的EXCEL列名的StringBuffer变量
	*/
	public static String getExcelNumName(int col) {
		int a = 65;
		int num = col%26;
		int count = col/26;
		char s = (char)(a+count-1);
		char g = (char)(a+num);
		return count>0 ? s+""+g : g+"";
	}
	
	
   /** 验证是否重复提交
    * <p>
    * 	true ： 是重复提交！<br/>
    *   false: 非重复提交!
    * </p>
	 * @param request
	 * @return boolean  V2briIy096
	 */
	public static boolean token(HttpServletRequest request){
		   String tokenValue = trim(request.getParameter("token"));
		   String old_tokenValue = CommonUtil.trim(request.getSession().getAttribute(Constants.SESSION_TOKEN));
		   if(tokenValue.equals(old_tokenValue)){
			   return true;
		   }else{
			   request.getSession().setAttribute(Constants.SESSION_TOKEN,tokenValue);
		   }
		   
		   return false;
	}
   
	/** 取得文件大小   
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSizes(File f) throws Exception{
		long s=0; 
		if (f.exists()) { 
			FileInputStream fis = null; 
			try {
				fis = new FileInputStream(f); 
				s= fis.available(); 
			} catch (Exception e) {
			}finally{
				if(fis != null)
					fis.close();
			}
		} 
		return s; 
	} 
	
	/** 判断网络文件是否存在
	 * @param httpUrl
	 * @return
	 */
	public static boolean existsHttpFile(String httpUrl){
		InputStream in = null;
		boolean flag = false;
		try {
			URL url = new URL(httpUrl);
			URLConnection uc = url.openConnection();
			in = uc.getInputStream();
			flag = true;
		} catch (Exception e) {
			flag = false;
		}finally{
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return flag;

	}
	
	/**
	 * 计算两个时间的时间差，时间单位为分钟
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static long getDifferenceTime(String beginTime,String endTime){
		Date begin = parseDateTimeStringToDate(beginTime);
		Date end = parseDateTimeStringToDate(endTime);
		return (end.getTime()-begin.getTime())/60000;
	}
	/**
	 * 计算某时间N分分钟后的时间
	 * @return
	 */
	public static String getAfterMinitsDateTime(String dateTime,long minits){
		Date date = parseDateTimeStringToDate(dateTime);
		long times = date.getTime()+minits*60*1000;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(times);
		return getDateTimeString(cal.getTime());
	}
	/**
	 * 计算某天几个月后的日期
	 * @param date
	 * @param months
	 * @return
	 */
	public static String getAfterMonthsDate(String date,int months){
		Date dd = parseDateStringToDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dd);
		cal.add(Calendar.MONTH, months);
		return getDateString(cal.getTime());
	}
    public static void main(String[] args) throws ParseException {
	   int a=2111170000;
	   System.out.println(a);
   }
}
