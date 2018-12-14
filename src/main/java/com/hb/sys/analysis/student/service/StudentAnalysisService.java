package com.hb.sys.analysis.student.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.sys.analysis.student.dao.StudentAnalysisDao;

/**
 * 学生统计分析
 * @author hanbin
 * @date 2018年4月9日
 */
@Service
public class StudentAnalysisService {

	@Autowired
	private StudentAnalysisDao studentAnalysisDao;
	
	public Map<String,Object> getDepartStuLineData(String did){
		List<Map<String,String>> list=studentAnalysisDao.getDepartStuLineData(did);
		List<String> LineName = new ArrayList<String>();
		List<String> LineValue = new ArrayList<String>();
		
		for (Map<String,String> map : list) {
			LineName.add(String.valueOf(map.get("NAME")));
			LineValue.add(String.valueOf(map.get("SCORE")));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("LineName",LineName);
		result.put("LineValue", LineValue);
		
		return result;
	}
	public Map<String,Object> getClasssStuLineData(String did,String cid){
		List<Map<String,String>> list=studentAnalysisDao.getClasssStuLineData(did,cid);
		List<String> LineName = new ArrayList<String>();
		List<String> LineValue = new ArrayList<String>();
		
		for (Map<String,String> map : list) {
			LineName.add(String.valueOf(map.get("NAME")));
			LineValue.add(String.valueOf(map.get("SCORE")));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("LineName",LineName);
		result.put("LineValue", LineValue);
		
		return result; 
	}
}
