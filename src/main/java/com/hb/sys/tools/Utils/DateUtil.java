package com.hb.sys.tools.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换工具
 * @author hanbin
 * @date 2018年1月8日
 */
public class DateUtil  extends org.apache.commons.lang3.time.DateUtils{
	
	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
			"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	 /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException{
    	if(s.equals("")||s == null){
    		return "";
    	}
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
    	if(s.equals("")||s == null){
    		return "";
    	}
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * date转换成String类型
     */
    public static String DateToString(Date date){
    	if(date!=null&&!"".equals(date)){
    		return  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    	}else {
    		return "";
    	}
    	
    }
    /*
     * string类型转换成Date类型
     */
    public static Date StringToDate(String str){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	Date d = new Date();
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return d;
    }
    /*
     * 获取当前时间
     */
    public static String getCurrentTime(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	String res = df.format(new Date());
    	return res;
    }
    /*
     * 获取当前时间时间戳
     */
    public static String getCurrentTimeStamp() throws ParseException{
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	String res = df.format(new Date());
    	res = dateToStamp(res);
    	return res;
    }
    
    /**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
}
