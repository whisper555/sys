/**
 * 
 */
package com.hb.sys.analysis.teacher.dao;

import java.util.List;
import java.util.Map;

/**
 * 教师统计分析dao
 * @author hanbin
 * @version 2017年10月31日
 */
public interface TeacherAnalysisDao {
	/*
	 * 教师名下学生人数
	 */
	public  List<Map<String,String>> getStuNumBarData();
	/*
	 * 教师名下学生毕设平均分
	 */
	public  List<Map<String,String>> getStuScoreBarData();
	/*
	 * 教师名下学生毕设通过率
	 */
	public  List<Map<String,String>> getStuRateBarData();
	
 
}
