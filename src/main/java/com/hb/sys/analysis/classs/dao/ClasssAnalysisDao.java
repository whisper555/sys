/**
 * 
 */
package com.hb.sys.analysis.classs.dao;

import java.util.List;
import java.util.Map;

/**
 * 班级统计分析dao
 * @author hanbin
 * @version 2017年11月2日
 */

public interface  ClasssAnalysisDao {
	
	/*
	 * 获取班级平均分数信息
	 */
	public List<Map<String,String>> getClaScoreBarData();
	/*
	 * 获取班级毕设通过率信息
	 */
	public List<Map<String,String>> getClaRateBarData();

}
