/**
 * 
 */
package com.hb.sys.analysis.teacher.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.sys.analysis.teacher.dao.TeacherAnalysisDao;

/**
 * 教师统计分析service
 * @author hanbin
 * @version 2017年10月31日
 */
@Service
public class TeacherAnalysisService {

	@Autowired
	private TeacherAnalysisDao teacherAnalysisDao;
	/*
	 * 教师名下学生人数
	 */
	public Map<String,Object> getStuNumBarData(){
		List<Map<String,String>> list=teacherAnalysisDao.getStuNumBarData();
		List<String> NumBarName = new ArrayList<String>();
		List<String> NumBarValue = new ArrayList<String>();
		
		for (Map<String,String> map : list) {
			NumBarName.add(String.valueOf(map.get("TEACHER")));
			NumBarValue.add(String.valueOf(map.get("NUM")));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("xAxis", NumBarName);
		result.put("yAxis", NumBarValue);
		
		return result;
		
	}
	
	/*
	 * 教师名下学生通过平均分数
	 */
	public Map<String,Object> getStuScoreBarData(){
		List<Map<String,String>> list=teacherAnalysisDao.getStuScoreBarData();
		List<String> ScoreBarName = new ArrayList<String>();
		List<String> ScoreBarValue = new ArrayList<String>();
		
		for (Map<String,String> map : list) {
			ScoreBarName.add(String.valueOf(map.get("TEACHER")));
			ScoreBarValue.add(String.valueOf(map.get("SCORE")));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("xAxis", ScoreBarName);
		result.put("yAxis", ScoreBarValue);
		
		return result;
		
	}
	/*
	 * 教师名下学生毕设通过率
	 */
	public Map<String,Object> getStuRateBarData(){
		List<Map<String,String>> list=teacherAnalysisDao.getStuRateBarData();
		List<String> RateBarName = new ArrayList<String>();
		List<String> RateBarValue = new ArrayList<String>();
		
		for (Map<String,String> map : list) {
			RateBarName.add(String.valueOf(map.get("TEACHER")));
			RateBarValue.add(String.valueOf(map.get("RATE")));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("xAxis", RateBarName);
		result.put("yAxis", RateBarValue);
		
		return result;
		
	}
}
