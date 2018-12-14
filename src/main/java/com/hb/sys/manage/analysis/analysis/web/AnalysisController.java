package com.hb.sys.manage.analysis.analysis.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.manage.analysis.analysis.service.AnalysisService;

/**
 * 
 * @author hanbin
 * @date 2018年4月9日
 */
@Controller
@RequestMapping(value="manage/analysis/analysis")
public class AnalysisController {
/*
	@Autowired
	private AnalysisService analysisService;
	@RequestMapping(value="list")
	public String list(){
		return "modules/manage/analysis/analysis/ChartsList";
	}
	*//**
	 * 论文分数班级情况
	 * @return
	 *//*
	@RequestMapping(value="getDataByClasss")
	@ResponseBody
	public List<Map<String,String>> getDataByClasss(){
		return analysisService.getDataByClasss();
	}
	*//**
	 * 论文分数教师情况
	 * @return
	 *//*
	@RequestMapping(value="getDataByTea")
	@ResponseBody
	public List<Map<String,String>> getDataByTea(){
		return analysisService.getDataByTea();
	}
	*//**
	 * 论文前十名
	 * @return
	 *//*
	@RequestMapping(value="getDataByClasss")
	@ResponseBody
	public List<Map<String,String>> getDataByStu(){
		return analysisService.getDataByStu();
	}*/
}
