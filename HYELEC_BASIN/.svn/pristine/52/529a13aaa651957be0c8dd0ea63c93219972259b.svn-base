package com.lyht.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/** 获取随机数
 * @author liuamang
 *
 */
public class Randomizer {
	private static final Random rnd = new Random();
	private static final int NORMAL = -1;
	private static final int NUMBER = 0;
	private static final int UPPER_CHARACTER = 1;
	private static final int LOWER_CHARACTER = 2;
	private static final int SIZE = 3;
	private static final int[] MAX_MEMBER = { 10, 26, 26 };
	private static final int[] BASE_ASCII = { 48, 65, 97 };

	/** */
	/**
	 * 随机生成数字和字母组成的字符串。
	 * 
	 * @param length
	 *            字符串长度
	 * @param type
	 *            类型(NORMAL/NUMBER)
	 * @return 字符串
	 */
	private static String nextString(int length, int type) {
		char[] charArray = new char[length];
		int curType = type;
		for (int i = 0; i < length; i++) {
			if (type == NORMAL)
				curType = rnd.nextInt(SIZE);
			charArray[i] = (char) (rnd.nextInt(MAX_MEMBER[curType]) + BASE_ASCII[curType]);
		}
		return new String(charArray);
	}

	/** */
	/**
	 * 随机生成数字和字母组成的字符串。
	 * 
	 * @param length
	 *            字符串长度
	 * @return 字符串
	 */
	public static String nextString(int length) {
		return nextString(length, NORMAL);
	}
	/**
	 * 生成编码
	 * @param length
	 * @return
	 */
	public static String generCode(int length,String comcode){
		String datestr = CommonUtil.getDateTimeString(new Date());
		String code = datestr.replace("-", "");
		code = code.replace(" ", "");
		code = code.replace(":", "");
		code = code+comcode;
		String str = code.substring(2, 6);
		String md5str = MD5.getInstance().getMD5ofStr(code);
		code = str+md5str.substring(0, length);
		return code;
	}
	
	public static String generCode(int length){
		String code = DateUtil.getTimestamp()+Randomizer.nextString(length);
		String md5str = MD5.getInstance().getMD5ofStr(code,length);
		code = md5str.substring(0, length);
		return code;
	}
	
	
	public static String getFormCode(){
		
		String t = "";
        SimpleDateFormat   df   =   new   SimpleDateFormat("yyyyMMdd"); 
        String date = df.format(new   Date());
        String  subDate= date.substring(3);
        int temp2   =   (int) (Math.rint(Math.random()*9000)+1000); 
        t=subDate+temp2;
        return t;
	}
	/** */
	/**
	 * 随机生成数字组成的字符串。
	 * 
	 * @param length
	 *            字符串长度
	 * @return 字符串
	 */
	public static String nextNumber(int length) {
		return nextString(length, NUMBER);
	}
	
	/**
	 * 随机生成数字组成的字符串。
	 * 格式为：字母+数字
	 * @param length
	 * 字符串长度
	 * @return 字符串
	 */
	public static String nextNumber(String letter,int length) {
		String nextNumber=nextString(length, NUMBER);
		if(!"".equals(letter)){
			nextNumber=letter.toUpperCase()+nextNumber;
		}
		return nextNumber;
	}
	
	/**
	 * 随机生成 时间+随机数 共16位
	 */
	public static String getRandomByTime(int length){
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar mCalendar = Calendar.getInstance();
		mCalendar.setTime(new Date());
		String dateTime = mSimpleDateFormat.format(mCalendar.getTime());
		String number = nextNumber(length);
		if(null==number){
			return dateTime;
		}
		return dateTime+number;
	}
	/** */
	/**
	 * 随机生成大写字母组成的字符串。
	 * 
	 * @param length
	 *            字符串长度
	 * @return 字符串
	 */
	public static String nextUpperString(int length) {
		return nextString(length, UPPER_CHARACTER);
	}

	/** */
	/**
	 * 随机生成小写字母组成的字符串。
	 * 
	 * @param length
	 *            字符串长度
	 * @return 字符串
	 */
	public static String nextLowerString(int length) {
		return nextString(length, LOWER_CHARACTER);
	}

	/** */
	/**
	 * 随机生成大于零的整数。
	 * 
	 * @see {@link Random#nextInt()}
	 * @return 整数
	 */
	public static int nextInt() {
		return rnd.nextInt();
	}

	/** */
	/**
	 * 随机生成0<value<max的整数
	 * 
	 * @see {@link Random#nextInt(int)}
	 * @param max
	 *            最大整数
	 * @return 整数
	 */
	public static int nextInt(int max) {
		return rnd.nextInt(max);
	}

	/** */
	/**
	 * 生成布尔值。
	 * 
	 * @see {@link Random#nextBoolean()}
	 * @return 布尔值
	 */
	public static boolean nextBoolean() {
		return rnd.nextBoolean();
	}

	/** */
	/**
	 * 生成字节数组。
	 * 
	 * @see {@link Random#nextBytes(byte[])}
	 * @param bytes
	 *            用来存储生成的字节数组
	 */
	public static void nextBytes(byte[] bytes) {
		rnd.nextBytes(bytes);
	}

	/** */
	/**
	 * 生成浮点数。
	 * 
	 * @see {@link Random#nextDouble()}
	 * @return 双精度浮点数
	 */
	public static double nextDouble() {
		return rnd.nextDouble();
	}

	/** */
	/**
	 * 生成浮点数。
	 * 
	 * @see {@link Random#nextFloat()}
	 * @return 单精度浮点数
	 */
	public static float nextFloat() {
		return rnd.nextFloat();
	}

	/** */
	/**
	 * 生成高斯浮点数。
	 * 
	 * @see {@link Random#nextGaussian()}
	 * @return 双精度浮点数
	 */
	public static double nextGaussian() {
		return rnd.nextGaussian();
	}

	/**
	 * 随机生成带日期的唯一内码值
	 * @param length 长度必须大于20
	 * @return
	 */
	public static String generCodeWidthDate(int length){
		String times = DateUtil.getDateTimes();
		return times+generCode(length-times.length());
	}
	/** */
	/**
	 * 生成长整数。
	 * 
	 * @see {@link Random#nextLong()}
	 * @return 长整数
	 */
	public static long nextLong() {
		return rnd.nextLong();
	}
	
	/**
	 * 生成UUID编码
	 * @param args
	 */
	public static String uuid (){
		
		UUID uuid = UUID.randomUUID();
		
		String replace = uuid.toString().replace("-", ""); 
		
		return replace;
	}
	public static void main(String[] args) {
//		System.out.println(nextLong());
//		System.out.println(nextLong());
//		System.out.println(nextLong());
//		System.out.println(nextLong());
//		System.out.println(nextLong());  
		
//		SimpleDateFormat sDateFormat = new SimpleDateFormat(
//				"yyMMddhhmmss");
//		SimpleDateFormat sDateFormat2 = new SimpleDateFormat(
//				"yyMM");
//		System.out.println(generCode(10,"BX"));
//		System.out.println();

//		System.out.println(nextString(4));
//		System.out.println(nextString(4));
//		System.out.println(nextString(4));
//		System.out.println(nextString(4));
//		System.out.println(nextString(4));
//		System.out.println(nextString(4));
//		System.out.println(nextString(4));
//		System.out.println(nextString(20));
		System.out.println(generCode(10));
	}
}
