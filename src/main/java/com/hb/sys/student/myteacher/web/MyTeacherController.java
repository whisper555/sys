package com.hb.sys.student.myteacher.web;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;
import com.hb.sys.tools.fileupload.FileUtil;

/**
 * 我的导师controller
 * 
 * @author hanbin
 * @version 2017年2月27日
 */

@Controller
@RequestMapping(value = "student/myteacher")
public class MyTeacherController {

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private PagerInfoService pagerInfoService;
	@Autowired
	private TeacherInfoService teacherInfoService;
	@Autowired
	private GlobalService globalService;

	@ModelAttribute
	public StudentInfo get(@RequestParam(required = false) String id) {
		StudentInfo entity = null;
		if (id != null && !id.equals("")) {
			entity = studentInfoService.get(globalService.sid2id(id));
		}
		if (entity == null) {
			entity = new StudentInfo();
		}

		return entity;
	}

	/**
	 * 编辑我的导师表单页面
	 */

	@RequestMapping(value = "list")
	public String list(StudentInfo studentInfo, Model model, HttpServletRequest request) {
		if (studentInfo != null) {
			if (studentInfo.getTeacher() == null||"".equals(studentInfo.getTeacher().getId())) {
				request.setAttribute("error", "提示");
				request.setAttribute("description", "暂时没有导师!");
				return "error/MessageError";
			} else {
				TeacherInfo teacher = teacherInfoService.get(studentInfo.getTeacher().getId());
				model.addAttribute("teacher", teacher);
				List<PagerInfo> pagerList = new ArrayList<>();
				pagerList = pagerInfoService.findPagerListByTeacher(studentInfo.getTeacher().getId());
				model.addAttribute("pagerList", pagerList);
				return "modules/student/myteacher/MyTeacher";
			}

		} else {
			request.setAttribute("error", "身份验证错误");
			request.setAttribute("description", "未获取到当前登陆的学生！");
			return "error/MessageError";
		}

	}
	/*
	 * 申请更换论文题目
	 */
	@RequestMapping(value = "changepager")
	public String changepager(StudentInfo studentInfo, Model model, HttpServletRequest request){
		return "modules/student/mysubject/MySubject";
	}
	//文件下载
		@RequestMapping(value = "filedownload")
		@ResponseBody
		public String filedownload(PagerInfo pagerInfo,  @RequestParam String file,HttpServletRequest request,HttpServletResponse response) throws IOException{
			try{
				FileUtil.downloadfile(file,request, response);
				return "文件下载成功！";
			}catch(Exception e){
				return "文件下载出错！";
			}
	}
	

}