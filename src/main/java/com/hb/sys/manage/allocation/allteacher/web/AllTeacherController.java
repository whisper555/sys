package com.hb.sys.manage.allocation.allteacher.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;

/**
 * 管理员分配教师
 * @author hanbin
 * @version 2017年10月27日
 */

@Controller
@RequestMapping(value = "manage/allocation/allteacher")
public class AllTeacherController {

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private TeacherInfoService teacherInfoService;

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
	public String list(StudentInfo studentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<StudentInfo> studentlist = studentInfoService.findAllocationList(); 
		model.addAttribute("studentlist", studentlist);
		return "modules/manage/allocation/allteacher/AllTeacherList";
	}

	/**
	 * 学生基本信息列表页面
	 */

	@RequestMapping(value = { "nolist" })
	public String nolist(StudentInfo studentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<StudentInfo> studentlist = studentInfoService.findNoAllocationList(); 
		model.addAttribute("studentlist", studentlist);
		return "modules/manage/allocation/allteacher/AllTeacherList";
	}

	/**
	 * 更新学生基本信息
	 */

	@RequestMapping(value = "save")
	public String save(StudentInfo studentInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		studentInfo.setS_flag("1");//分配标志变成1
		System.out.println("学生基本信息保存--");
		if (studentInfoService.get(studentInfo) == null) {
			studentInfoService.insert(studentInfo);
		} else {
			studentInfoService.update(studentInfo);// 保存
		}

		return "redirect:list";
	}
	//获取没有分配完的教师
	@RequestMapping(value="getAllocationTeacherList")
	@ResponseBody
	public List<Map<String,String>> getAllocationTeacherList(){
		return teacherInfoService.findAllocationTeacherList(); 
	}
	//保存分配的教师
	@RequestMapping(value="saveallocationteacher")
	@ResponseBody
	public String saveallocationteacher(StudentInfo studentInfo){
		studentInfoService.update(studentInfo);
		return "success";
	}

}
