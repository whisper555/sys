package com.hb.sys.global.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hb.sys.tools.annotation.MyBatisDao;

/**
 * @author hanbin
 * @date 2018年1月8日
 */
@MyBatisDao
public interface GlobalDao {
	//用于全局时间的判断
	public List<Map<String, Date>> judgetime(String period);

	// 用于教师tid和id转换
	public String tid2id(String tid);

	// 用于学生sid和id转换
	public String sid2id(String tid);
}
