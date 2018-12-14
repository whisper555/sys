package com.hb.sys.teacher.infomation.web;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;

/**
 * 教师基本信息修改
 * @author hanbin
 * @date 2018年3月7日
 */
@Controller
@RequestMapping(value="teacher/infomation")
public class TeacherInfomationController {
	@Autowired
	private TeacherInfoService teacherInfoService;
	@Autowired
	private GlobalService globalService;

	@ModelAttribute
	public TeacherInfo get(@RequestParam(required = false) String t_id) {
		TeacherInfo entity = null;
		if (t_id != null && !t_id.equals("")) {
			entity = teacherInfoService.get(globalService.tid2id(t_id));
		}
		if (entity == null) {
			entity = new TeacherInfo();
		}

		return entity;
	}



	/**
	 * 编辑教师基本信息表单页面
	 */

	@RequestMapping(value = "list")
	public String form(TeacherInfo teacherInfo, Model model) {
		model.addAttribute("teacherInfo", teacherInfo);
		return "modules/teacher/infomation/TeacherInfomationList";
	}


	/**
	 * ajax更新教师基本信息
	 */

	@RequestMapping(value = "save")
	public void save(TeacherInfo teacherInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		teacherInfo.setT_name(URLDecoder.decode(teacherInfo.getT_name(), "UTF-8"));	
		teacherInfoService.update(teacherInfo);// 保存


	}



}
