package com.lyht.util;
import java.security.Key;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;



/**
 * DES加密和解密工具,可以对字符串进行加密和解密操作  。 
 */
public class DesUtils {
  
  /** 字符串默认键值     */
  private static String strDefaultKey = "national";

  /** 加密工具     */
  private Cipher encryptCipher = null;

  /** 解密工具     */
  private Cipher decryptCipher = null;
  
  private Logger log = Logger.getLogger(DesUtils.class);

  /**  
   * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]  
   * hexStr2ByteArr(String strIn) 互为可逆的转换过程  
   *   
   * @param arrB  
   *            需要转换的byte数组  
   * @return 转换后的字符串  
   * @throws Exception  
   *             本方法不处理任何异常，所有异常全部抛出  
   */
  public static String byteArr2HexStr(byte[] arrB) throws Exception {
    int iLen = arrB.length;
    // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍   
    StringBuffer sb = new StringBuffer(iLen * 2);
    for (int i = 0; i < iLen; i++) {
      int intTmp = arrB[i];
      // 把负数转换为正数   
      while (intTmp < 0) {
        intTmp = intTmp + 256;
      }
      // 小于0F的数需要在前面补0   
      if (intTmp < 16) {
        sb.append("0");
      }
      sb.append(Integer.toString(intTmp, 16));
    }
    return sb.toString();
  }

  /**  
   * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)  
   * 互为可逆的转换过程  
   *   
   * @param strIn  
   *            需要转换的字符串  
   * @return 转换后的byte数组  
   * @throws Exception  
   *             本方法不处理任何异常，所有异常全部抛出  
   * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>  
   */
  public static byte[] hexStr2ByteArr(String strIn) throws Exception {
    byte[] arrB = strIn.getBytes();
    int iLen = arrB.length;

    // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2   
    byte[] arrOut = new byte[iLen / 2];
    for (int i = 0; i < iLen; i = i + 2) {
      String strTmp = new String(arrB, i, 2);
      arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
    }
    return arrOut;
  }

  /**  
   * 默认构造方法，使用默认密钥  
   *   
   * @throws Exception  
   */
  public DesUtils() throws Exception {
    this(strDefaultKey);
  }
  
  /**  
   * 指定密钥构造方法  
   *   
   * @param strKey  
   *            指定的密钥  
   * @throws Exception  
   */
  public DesUtils(String strKey) {
	  try {
		  Security.addProvider(new com.sun.crypto.provider.SunJCE());
		    Key key = getKey(strKey.getBytes());

		    encryptCipher = Cipher.getInstance("DES");
		    encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		    decryptCipher = Cipher.getInstance("DES");
		    decryptCipher.init(Cipher.DECRYPT_MODE, key);
	} catch (Exception e) {
		log.error("初始化加密和解密，请重试！",e);
	}
  }

  /**  
   * 加密字节数组  
   *   
   * @param arrB  
   *            需加密的字节数组  
   * @return 加密后的字节数组  
   * @throws Exception  
   */
  public byte[] encrypt(byte[] arrB) throws Exception {
    return encryptCipher.doFinal(arrB);
  }

  /**  
   * 加密字符串  
   *   
   * @param strIn  
   *            需加密的字符串  
   * @return 加密后的字符串  
   * @throws Exception  
   */
  public String encrypt(String strIn) throws Exception {
    return byteArr2HexStr(encrypt(strIn.getBytes()));
  }

  /**  
   * 解密字节数组  
   *   
   * @param arrB  
   *            需解密的字节数组  
   * @return 解密后的字节数组  
   * @throws Exception  
   */
  public byte[] decrypt(byte[] arrB) throws Exception {
    return decryptCipher.doFinal(arrB);
  }

  /**  
   * 解密字符串  
   *   
   * @param strIn  
   *            需解密的字符串  
   * @return 解密后的字符串  
   * @throws Exception  
   */
  public String decrypt(String strIn)  {
	  try {
		  return new String(decrypt(hexStr2ByteArr(strIn)));
	} catch (Exception e) {
		log.error("解密字符串失败，请重试！",e);
	}
	  return "";
  }

  /**  
   * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位  
   *   
   * @param arrBTmp  
   *            构成该字符串的字节数组  
   * @return 生成的密钥  
   * @throws java.lang.Exception  
   */
  private Key getKey(byte[] arrBTmp) throws Exception {
    // 创建一个空的8位字节数组（默认值为0）   
    byte[] arrB = new byte[8];

    // 将原始字节数组转换为8位   
    for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
      arrB[i] = arrBTmp[i];
    }

    // 生成密钥   
    Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

    return key;
  }
  
  /**
 	 * 去除重复项(str：逗号拼接)
 	 * */
 	@SuppressWarnings({ "unchecked", "rawtypes" })
 	public static String removeDuplicate(String str){      
 		String str_="";
 		if(null!=str && !"".equals(str)){
 			String key[]=str.split(",");
 			ArrayList mArrayList=new ArrayList();
 			for(int i=0;i<key.length;i++){
 				if(!mArrayList.contains(key[i])){
 					mArrayList.add(key[i]);
 				}
 			}
 			if(mArrayList.size()>0){
 				for(int j=0;j<mArrayList.size();j++){
 					str_+=mArrayList.get(j)+",";
 				}
 				if(!"".equals(str_)){
 					if(",".equals(str_.substring(str_.length()-1))){
 						str_=str_.substring(0,str_.length()-1);
 					}
 				}
 			}
 		}
 		return str_;
 	} 
	
	/**
	 * 去除数组重复项
	 * */
	public static Object[] removeByIndex(Object[] oldArr, int index) {
		List<Object> list = new ArrayList<Object>();
		for (Object i : oldArr) {
			list.add(i);
		}
		list.remove(index);
		Object[] newArr = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			newArr[i] = list.get(i);
		}
		return newArr;
	}
	
	/**
	 * 移除List集合元素
	 * */
	public static String removeListElement(String []array,String code){
		String str="";
		List<String> list_=Arrays.asList(array);
		ArrayList<String> mArrayList=new ArrayList<String>(list_);
		for(int i=0;i<mArrayList.size();i++){
			String key=mArrayList.get(i);
			if(key.contains(code)){
				mArrayList.remove(key);
				continue;
			}
			str+=key+",";
		}
		if(null!=str && !"".equals(str)){
			if(",".equals(str.substring(str.length()-1))){
				str=str.substring(0,str.length()-1);
			}
		}
		return str;
	}
  
	
  /**
   * 汉字转换成拼音
   * 需要引入pinyin4j.jar 
   */
  public static String ToPinyin(String chinese){          
	          String pinyinStr = "";  
	           char[] newChar = chinese.toCharArray();  
	           HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
	           defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	           defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	           for (int i = 0; i < newChar.length; i++) {  
	               if (newChar[i] > 128) {  
	                   try {  
	                       pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];  
	                   } catch (BadHanyuPinyinOutputFormatCombination e) {  
	                       e.printStackTrace();  
	                   }  
	               }else{  
	                   pinyinStr += newChar[i];  
	               }  
	          }  
	           return pinyinStr;  
	   }  
  /**
   * 汉字转换成拼音截取汉字首字母
   * 需要引入pinyin4j.jar 
   */
  public static String ToFirstChar(String chinese){         
	           String pinyinStr = "";  
	          char[] newChar = chinese.toCharArray();  //转为单个字符
	          HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
	          defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	           defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	           for (int i = 0; i < newChar.length; i++) {  
	               if (newChar[i] > 128) {  
	                  try {  
	                       pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0].charAt(0);  
	                   } catch (BadHanyuPinyinOutputFormatCombination e) {  
	                       e.printStackTrace();  
	                   }  
	               }else{  
	                   pinyinStr += newChar[i];  
	               }  
	           }  
	          return pinyinStr;  
	       } 
  /**
   * main方法  。
   * @param args
   */
  public static void main(String[] args) {
    try {
      DesUtils des = new DesUtils("lyht");//自定义密钥   
      System.out.println("加密后的字符：" + des.encrypt("abcd"));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}