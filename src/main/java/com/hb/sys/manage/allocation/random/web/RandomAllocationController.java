package com.hb.sys.manage.allocation.random.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hb.sys.manage.allocation.random.service.RandomAllocationService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;

/**
 * 管理员随机分配
 * @author hanbin
 * @version 2018年03月16日
 */

@Controller
@RequestMapping(value = "manage/allocation/random")
public class RandomAllocationController {

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private RandomAllocationService randomAllocationService;

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
	 * 列表页面
	 */

	@RequestMapping(value = { "list", "" })
	public String list(StudentInfo studentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<StudentInfo> studentlist = studentInfoService.findAllocationList(); 
		model.addAttribute("studentlist", studentlist);
		return "modules/manage/allocation/random/RandomAllocationList";
	}


	
	//随机分配教师
	@RequestMapping(value="randomAllocationTeacher")
	public String randomAllocationTeacher(){
		randomAllocationService.randomAllocationTeacher();
		return "redirect:list";
	}
	//随机分配论文
	@RequestMapping(value="randomAllocationPager")
	public String randomAllocationPager(){
		randomAllocationService.randomAllocationPager();
		return "redirect:list";
	}

}
