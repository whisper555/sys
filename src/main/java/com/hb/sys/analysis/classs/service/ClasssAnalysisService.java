/**
 * 
 */
package com.hb.sys.analysis.classs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.sys.analysis.classs.dao.ClasssAnalysisDao;

/**
 * 班级统计分析service
 * @author hanbin
 * @version 2017年11月2日
 */
@Service
public class ClasssAnalysisService {

	@Autowired
	private ClasssAnalysisDao classsAnalysisDao;
	
	/*
	 * 获取班级平均分数据
	 */
	public Map<String,Object> getClaScoreBarData(){
		List<Map<String,String>> list=classsAnalysisDao.getClaScoreBarData();
		List<String> scoreBarName = new ArrayList<String>();
		List<String> scoreBarValue = new ArrayList<String>();
		for(Map<String,String> map : list){
			scoreBarName.add(String.valueOf(map.get("CLASSS")));
			scoreBarValue.add(String.valueOf(map.get("AVERAGE")));
		}
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("xAxis", scoreBarName);
		result.put("yAxis", scoreBarValue);
		return result;
	}
	/*
	 * 获取班级通毕设通过率
	 */
	public Map<String,Object> getClaRateBarData(){
		List<Map<String,String>> list = classsAnalysisDao.getClaRateBarData();
		List<String> rateBarName = new ArrayList<String>();
		List<String> rateBarValue = new ArrayList<String>();
		for(Map<String,String> map:list){
			rateBarName.add(String.valueOf(map.get("CLASSS")));
			rateBarValue.add(String.valueOf(map.get("RATE")));
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("xAxis", rateBarName);
		result.put("yAxis", rateBarValue);
		return result;
	}
}
