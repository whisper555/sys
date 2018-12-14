package com.hb.sys.app.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hb.sys.manage.baseinfo.student.dao.StudentInfoDao;
import com.hb.sys.manage.baseinfo.teacher.dao.TeacherInfoDao;
import com.hb.sys.tools.json.AjaxJson;


/**
 * APP登陆接口
 * @author hanbin
 * @date 2018年4月25日
 */
@RequestMapping(value = "app/login")
@Controller
public class LoginForAppController extends BaseForAppController{

	@Autowired
	TeacherInfoDao teacherDao;
	@Autowired
	StudentInfoDao studentDao;
	
	@RequestMapping(value = "login")
	public String list(String username, String password,String user,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		try {
			if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)||StringUtils.isEmpty(user)) {
				setAjaxJsonError1(j);
			} else {
				if (user.equals("1")) {// 如果登录类型是1
					if (studentDao.studentLogin(username, password) != null) {
						j.setSuccess(true);
						j.put("user", studentDao.studentLogin(username, password));
						j.setMsg("登录成功");
					}else{
						j.setSuccess(false);
						j.setMsg("小伙子，把帐号和密码检查一下再试试吧~");
					}
				} else if (user.equals("2")) {// 如果登录类型是2
					if (teacherDao.teacherLogin(username, password) != null) {
						j.setSuccess(true);
						j.put("user", teacherDao.teacherLogin(username, password));
						j.setMsg("登录成功");
					}else{
						j.setSuccess(false);
						j.setMsg("登录失败，账号不存在或者密码不正确！");
					}
				} else{
					j.setSuccess(false);
					j.setMsg("登录失败，角色不存在！");
				}
				

			}
		} catch (Exception e) {
			setAjaxJsonError0(j);
		}
		return renderString(response, j.getJsonStr());
	}
}
