/**
 * 
 */
package com.hb.sys.login.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.login.entity.User;
import com.hb.sys.manage.baseinfo.student.dao.StudentInfoDao;
import com.hb.sys.manage.baseinfo.teacher.dao.TeacherInfoDao;

/**
 * 登录Controller
 * 
 * @author hanbin
 * @version 2017年10月20日
 */
@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	TeacherInfoDao teacherDao;
	@Autowired
	StudentInfoDao studentDao;

	@ModelAttribute
	public User get(String id, String pw, String user, HttpSession httpSession) {
		User entity = new User();
		entity.setId(id);
		entity.setPw(pw);
		entity.setUser(user);
		System.out.println(entity.toString());
		return entity;
	}

	/*
	 * @RequestMapping("") public String index(User user, HttpSession
	 * httpSession) { if (user.getUser().equals("1")) {//如果登录类型是1 if
	 * (studentDao.studentLogin(user.getId(), user.getPw()) == null) { return
	 * "error/LoginError"; } else {
	 * //System.out.println(teacherDao.teacherLogin(user.getId(),
	 * user.getPw()).toString());
	 * user.setName(studentDao.studentLogin(user.getId(),
	 * user.getPw()).getS_name()); httpSession.setAttribute("Stu_user", user);
	 * return "modules/index/Sindex"; } } else if (user.getUser().equals("2"))
	 * {//如果登录类型是2
	 * 
	 * if (teacherDao.teacherLogin(user.getId(), user.getPw()) == null) { return
	 * "error/LoginError"; } else {
	 * System.out.println(teacherDao.teacherLogin(user.getId(),
	 * user.getPw()).toString());
	 * user.setName(teacherDao.teacherLogin(user.getId(),
	 * user.getPw()).getT_name()); httpSession.setAttribute("Tea_user", user);
	 * return "modules/index/Tindex"; } } else if (user.getUser().equals("3"))
	 * {//如果登录类型是3
	 * 
	 * if (teacherDao.teacherLogin(user.getId(), user.getPw()) == null) { return
	 * "error/LoginError"; } else {
	 * System.out.println(teacherDao.teacherLogin(user.getId(),
	 * user.getPw()).toString()); user.setName("管理员");
	 * httpSession.setAttribute("Man_user", user); return "modules/index/index";
	 * } } else {
	 * 
	 * } return "modules/index/loginfail"; }
	 */
	@RequestMapping("")
	@ResponseBody
	public String login(User user, HttpSession httpSession) {
		if (user.getUser().equals("1")) {// 如果登录类型是1
			if (studentDao.studentLogin(user.getId(), user.getPw()) == null) {
				return "false";
			} else {
				// System.out.println(teacherDao.teacherLogin(user.getId(),
				// user.getPw()).toString());
				user.setName(studentDao.studentLogin(user.getId(), user.getPw()).getS_name());
				httpSession.setAttribute("Stu_user", user);
				return "true";
			}
		} else if (user.getUser().equals("2")) {// 如果登录类型是2

			if (teacherDao.teacherLogin(user.getId(), user.getPw()) == null) {
				return "false";
			} else {
				System.out.println(teacherDao.teacherLogin(user.getId(), user.getPw()).toString());
				user.setName(teacherDao.teacherLogin(user.getId(), user.getPw()).getT_name());
				httpSession.setAttribute("Tea_user", user);
				return "true";
			}
		} else if (user.getUser().equals("3")) {// 如果登录类型是3

			if (teacherDao.manageLogin(user.getId(), user.getPw()) == null) {
				return "false";
			} else {
				System.out.println(teacherDao.manageLogin(user.getId(), user.getPw()).toString());
				user.setName("管理员");
				httpSession.setAttribute("Man_user", user);
				return "true";
			}
		} else {

		}
		return "false";
	}

	@RequestMapping("/index")
	public String index(User user, HttpSession httpSession) {
		if ("1".equals(user.getUser())) {
			return "modules/index/Sindex";
		} else if ("2".equals(user.getUser())) {
			return "modules/index/Tindex";
		} else if ("3".equals(user.getUser())) {
			return "modules/index/index";
		} else {
			return "modules/login";
		}
		
	}

	@RequestMapping("/loginout")
	public String clientLoginOut(HttpSession httpSession) {
		httpSession.invalidate();
		return "modules/login";
	}
}
