/**
 * 
 */
package com.hb.sys.analysis.student.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.analysis.student.service.StudentAnalysisService;
import com.hb.sys.manage.baseinfo.classs.entity.ClasssInfo;
import com.hb.sys.manage.baseinfo.classs.service.ClasssInfoService;
import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.department.service.DepartmentInfoService;
import com.mysql.jdbc.StringUtils;


/**
 * 学生统计分析controller
 * @author hanbin
 * @version 2017年10月31日
 */
@Controller
@RequestMapping(value="analysis/student")
public class StudentAnalysisController {
	@Autowired
	private StudentAnalysisService studentAnalysisService;
	@Autowired
	private ClasssInfoService classsInfoService;
	@Autowired
	private DepartmentInfoService departmentInfoService;
	
	
	

	@RequestMapping(value = {""})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ClasssInfo> classsList = classsInfoService.findList(new ClasssInfo());
		List<DepartmentInfo> departList = departmentInfoService.findList(new DepartmentInfo());
		model.addAttribute("departList", departList);
		model.addAttribute("classsList", classsList);
		return "modules/analysis/student/StudentAnalysisList";
	}
	/*
	 * 每个班级前十名
	 */
	@ResponseBody
	@RequestMapping(value={"StuLineData"})
	public Map<String,Object> getStuData(String departId,String classsId){
		if(StringUtils.isNullOrEmpty(departId)){
			return studentAnalysisService.getDepartStuLineData("1");//获取id为1的数据
		}
		if(StringUtils.isNullOrEmpty(classsId)){
			return studentAnalysisService.getDepartStuLineData(departId);
		}else{
			Map<String,Object> map = new HashMap<>();
			map = studentAnalysisService.getClasssStuLineData(departId,classsId);
			return map;
		}
	}

}
