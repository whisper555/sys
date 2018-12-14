package com.hb.sys.teacher.choosestu.web;

import java.text.ParseException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;

/**
 * 选择学生Controller
 * 
 * @author hanbin
 * @date 2018年1月22日
 */
@RequestMapping(value = "teacher/choosestu/")
@Controller
public class ChooseStuController {

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

		return entity;
	}

	/**
	 * 学生基本信息列表页面
	 * 
	 * @throws ParseException
	 */

	@RequestMapping(value = { "list", "" })
	public String list(StudentInfo studentInfo, @RequestParam(required = true) String period,
			@RequestParam(required = true) String tid, HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		// 对时间添加判断，如果不再时间范围内，跳转时间错误页面
		if (!globalService.judgetime(period)) {
			return "error/TimeError";
		}
		List<StudentInfo> studentlist = new ArrayList<>();
		if (period.equals("3")) {
			studentlist = studentInfoService.findFirstList(tid);
			model.addAttribute("studentlist", studentlist);
			return "modules/teacher/choosestu/ChooseFStuList";
		} else if (period.equals("4")) {
			studentlist = studentInfoService.findSecondList(tid);
			model.addAttribute("studentlist", studentlist);
			return "modules/teacher/choosestu/ChooseSStuList";
		} else {
			studentlist = studentInfoService.findThirdList(tid);
			model.addAttribute("studentlist", studentlist);
			return "modules/teacher/choosestu/ChooseTStuList";
		}

	}

	/**
	 * 更新学生导师
	 */

	@RequestMapping(value = "choose")
	public String update(StudentInfo studentInfo, Model model, @RequestParam(required = true) String period, @RequestParam(required = true) String pid,@RequestParam(required = true) String tid,
			RedirectAttributes redirectAttributes) throws Exception {
//		String tid = pagerInfoService.get(pid).getTeacher().getId();
		studentInfoService.updateTeacher(studentInfo.getId(),tid, pid);//老师选定学生,更新学生论文信息
		return "redirect:list?period="+period+"&pid=" + pid+"&tid="+tid;
	}

}
