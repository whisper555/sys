package com.hb.sys.global.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.sys.global.dao.GlobalDao;

/**
 * 全局服务service
 * 
 * @author hanbin
 * @date 2018年1月8日
 */
@Service
public class GlobalService {

	@Autowired
	GlobalDao gloabalDao;

	/*
	 * 判断当前时间是否在数据库中存储的时间范围内
	 */
	public boolean judgetime(String period) throws ParseException {
		List<Map<String, Date>> list = gloabalDao.judgetime(period);
//		long starttime = Long.parseLong(map.get("starttime").toString());
//		long endtime = Long.parseLong(map.get("endtime").toString());
		Date st = new Date();
		Date et = new Date();
		Date now = new Date();
		for (Map<String,Date> map : list) {
			 for (String key : map.keySet()) {
				   if("starttime".equals(key)){
					  st= map.get(key);
					   //st = DateUtil.StringToDate(map.get(key));
				   }else{
					  et= map.get(key);
					   //et = DateUtil.StringToDate(map.get(key));
				   }
		        } 
		}
		
		// System.out.println(starttime +"++++"+ endtime+"++++"+currenttime);
		if (now.getTime() < et.getTime() && now.getTime() > st.getTime()) {
			return true;
		} else {
			return false;
		}
		
	}

	// 用于教师tid和id转换
	public String tid2id(String tid) {
		return gloabalDao.tid2id(tid);
	};

	// 用于学生sid和id转换
	public String sid2id(String sid) {
		return gloabalDao.sid2id(sid);
	};
}
