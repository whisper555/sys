package com.hb.sys.teacher.choosestuactive.web;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;

/**
 * 教师端主动选择学生controller
 * @author hanbin
 * @date 2018年3月4日
 */
@Controller
@RequestMapping(value = "teacher/choosestuactive")
public class ChooseStuActiveController {

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private GlobalService globalService;
	
	@ModelAttribute
	public StudentInfo get(@RequestParam(required = false) String id) {
		
		StudentInfo entity = null;
		if (id != null && !id.equals("")) {
			entity = studentInfoService.get(id);
		}
		if (entity == null) {
			entity = new StudentInfo();
		}
		//System.out.println("Student****");
		return entity;
	}
	/**
	 * 查找没有被老师选择的学生列表
	 * @throws ParseException 
	 */

	@RequestMapping(value = "stulist")
	public String list(StudentInfo studentInfo, HttpServletRequest request, HttpServletResponse response, Model model,@RequestParam(required = true) String period) throws ParseException {
		// 对时间添加判断，如果不再时间范围内，跳转时间错误页面
				if (!globalService.judgetime(period)) {
					return "error/TimeError";
				}
		List<StudentInfo> stulist = studentInfoService.findLackStuList();
		model.addAttribute("studentlist", stulist);
		return "modules/teacher/choosestuactive/ChooseStuList";
	}
	/*
	 * 教师端选择学生
	 */
	@RequestMapping(value= "selectstu")
	public String selectstu(StudentInfo studentInfo ,@RequestParam(required = true)String tid,HttpServletRequest request, HttpServletResponse response, Model model){
		studentInfoService.selectstu(studentInfo.getId(),globalService.tid2id(tid));
		return "redirect:stulist?period=6&id="+studentInfo.getId();
	}
	/*
	 * 教师端选择学生，ajax 判断学生人数
	 */
	@ResponseBody
	@RequestMapping(value ="countlimit")
	public boolean countlimit(@RequestParam(required = true)String tid){
		return studentInfoService.countlimit(globalService.tid2id(tid));
	}
	
}
