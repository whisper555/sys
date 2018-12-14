package com.hb.sys.teacher.downloadstufile.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;

/**
 * 教师端下载学生论文controller
 * @author hanbin
 * @date 2018年4月09日
 */
@Controller
@RequestMapping(value = "teacher/downloadstufile/")
public class DownLoadStuFileController {
	@Autowired
	private StudentInfoService studentInfoService;
	

	@ModelAttribute
	public StudentInfo get(@RequestParam(required = false) String id) {
		StudentInfo entity = null;
		if (id != null && !id.equals("")) {
			entity = studentInfoService.get(id);
		}
		if (entity == null) {
			entity = new StudentInfo();
		}

		return entity;
	}

	/**
	 * 学生基本信息列表页面
	 */

	@RequestMapping(value = { "list", "" })
	public String list(StudentInfo studentInfo, @RequestParam(required = true) String tid, HttpServletRequest request,HttpServletResponse response, Model model){

		List<StudentInfo> studentlist = new ArrayList<>();
		studentlist = studentInfoService.findMyStudentList(tid);
		model.addAttribute("studentlist", studentlist);
		return "modules/teacher/downloadstufile/DownLoadStuFile";

	}

	
}
