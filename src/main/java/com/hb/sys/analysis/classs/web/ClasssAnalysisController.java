/**
 * 
 */
package com.hb.sys.analysis.classs.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.analysis.classs.service.ClasssAnalysisService;

/**
 * 班级统计分析controller		
 * @author hanbin
 * @version 2017年11月2日
 */
@Controller
@RequestMapping(value="analysis/classs")
public class ClasssAnalysisController {
	
	@Autowired
	private ClasssAnalysisService classsAnalysisService;
	/*
	 * 转到班级统计分析页面
	 */
	@RequestMapping(value="")
	public String index(){
		return "modules/analysis/classs/ClasssAnalysisList";
	}
	/*
	 * 班级平均分数统计
	 */
	@RequestMapping(value="ClaScoreBar")
    @ResponseBody
	public Map<String,Object> getClaScoreBarData(){
		//System.out.println(classsAnalysisService.getClaScoreBarData());
		return classsAnalysisService.getClaScoreBarData();
	}
	/*
	 * 班级学生通过率统计
	 */
	@RequestMapping(value="ClaRateBar")
    @ResponseBody
	public Map<String,Object> getClaRateBarData(){
		//System.out.println(classsAnalysisService.getClaRateBarData());
		return classsAnalysisService.getClaRateBarData();
	}
}

