package com.hb.sys.student.information.web;


import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;

/**
 * 学生个人信息修改
 * @author hanbin
 * @date 2018年3月7日
 */
@Controller
@RequestMapping(value="student/infomation")
public class StudentInfomationController {
	@Autowired
	private StudentInfoService StudentInfoService;
	@Autowired
	private GlobalService globalService;

	@ModelAttribute
	public StudentInfo get(@RequestParam(required = false) String s_id) {
		StudentInfo entity = null;
		if (s_id != null && !s_id.equals("")) {
			entity = StudentInfoService.get(globalService.sid2id(s_id));
		}
		if (entity == null) {
			entity = new StudentInfo();
		}

		return entity;
	}



	/**
	 * 编辑学生基本信息表单页面
	 */

	@RequestMapping(value = "list")
	public String form(StudentInfo StudentInfo, Model model) {
		model.addAttribute("StudentInfo", StudentInfo);
		return "modules/student/infomation/StudentInfomationList";
	}


	/**
	 * ajax更新学生基本信息
	 */

	@RequestMapping(value = "save")
	@ResponseBody
	public String save(StudentInfo StudentInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		StudentInfo.setS_name(URLDecoder.decode(StudentInfo.getS_name(), "UTF-8"));	
		StudentInfoService.update(StudentInfo);// 保存
		return "true";
	}



}
