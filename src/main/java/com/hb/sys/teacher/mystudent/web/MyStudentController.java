package com.hb.sys.teacher.mystudent.web;

import java.util.ArrayList;
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

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;

/**
 * 教师端我的学生controller
 * @author hanbin
 * @date 2018年1月23日
 */
@Controller
@RequestMapping(value = "teacher/mystudent/")
public class MyStudentController {
	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private PagerInfoService pagerInfoService;
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
	 */

	@RequestMapping(value = { "list", "" })
	public String list(StudentInfo studentInfo, @RequestParam(required = true) String tid, HttpServletRequest request,HttpServletResponse response, Model model){

		List<StudentInfo> studentlist = new ArrayList<>();
		studentlist = studentInfoService.findMyStudentList(tid);
		model.addAttribute("studentlist", studentlist);
		return "modules/teacher/mystudent/MyStudentList";

	}

	/**
	 * 更新学生导师状态
	 */

	@RequestMapping(value = "delete")
	public String update(StudentInfo studentInfo, Model model, @RequestParam(required = true) String tid,
			RedirectAttributes redirectAttributes) throws Exception {
		studentInfoService.updateTeacherNull(studentInfo.getId());// 老师删除学生
		return "redirect:list?tid=" + tid;
	}
	/**
	 * ajax 根据输入的论文名称模糊查询教师tid名下的论文
	 */
	
	@RequestMapping(value = "getTeacherPagerList")
	@ResponseBody
	public List<Map<String,String>> getTeacherPagerList(String tid){
		return pagerInfoService.getTeacherPagerList(globalService.tid2id(tid));
	}
	/**
	 * ajax设置学生选择的论文
	 */
	@RequestMapping(value = "setPager")
	@ResponseBody
	public void setPager(StudentInfo studentInfo){
		if(!(studentInfo.getPager().getId()==null||"".equals(studentInfo.getPager().getId()))){
			studentInfoService.update(studentInfo);
			
		}else {
			studentInfo.setPager(null);
			studentInfoService.update(studentInfo);
		}
		
		
	}
}
