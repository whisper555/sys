/**
 * 
 */
package com.hb.sys.analysis.teacher.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.analysis.teacher.service.TeacherAnalysisService;


/**
 * 教师统计分析controller
 * @author hanbin
 * @version 2017年10月31日
 */
@Controller
@RequestMapping(value="analysis/teacher")
public class TeacherAnalysisController {
	@Autowired
	private TeacherAnalysisService teacherAnalysisService;
	

	@RequestMapping(value = {""})
	public String index() {
		return "modules/analysis/teacher/TeacherAnalysisList";
	}
	/*
	 * 教师名下学生人数
	 */
	@ResponseBody
	@RequestMapping(value={"StuNumBar"})
	public Map<String,Object> getStuNumBarData(){
		//System.out.println(teacherAnalysisService.getStuNumBarData());
		return teacherAnalysisService.getStuNumBarData();
	}
	/*
	 * 教师名下学生平均分
	 */
	@ResponseBody
	@RequestMapping(value={"StuScoreBar"})
	public Map<String,Object> getStuScoreBarData(){
		//System.out.println(teacherAnalysisService.getStuScoreBarData());
		return teacherAnalysisService.getStuScoreBarData();
	}
	/*
	 * 教师名下学生通过率
	 */
	@ResponseBody
	@RequestMapping(value={"StuRateBar"})
	public Map<String,Object> getStuRateBarData(){
		//System.out.println(teacherAnalysisService.getStuRateBarData());
		return teacherAnalysisService.getStuRateBarData();
	}
	
}
