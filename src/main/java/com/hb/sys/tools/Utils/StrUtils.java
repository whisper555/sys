package com.hb.sys.tools.Utils;

/**
 * 字符串处理类
 * @author hanbin
 * @date 2018年3月2日
 */

public class StrUtils {

	public static String hanleNull(String s){
		if(s==null){
			return "";
		}else {
			return s;
		}
	} 
	public static int SringToInt(String s){
		return Integer.parseInt(s);
	}
}
