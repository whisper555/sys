package com.hb.sys.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hb.sys.login.entity.User;

/**
 * 登陆拦截器 
 * 场景：用户点击查看的时候，我们进行登陆拦截器操作，判断用户是否登陆？ 登陆，则不拦截，没登陆，则转到登陆界面；
 * @author hanbin
 * @date 2018年1月6日
 */
public class LoginHandlerIntercepter implements HandlerInterceptor {

	private final String urljg ="/";
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		
		String requestURI = request.getRequestURI();
		System.out.println("当前访问的url是----"+requestURI);
		if (requestURI.indexOf("login") > 0||requestURI.indexOf("app") > 0) {
			// 说明处在编辑的页面
			System.out.println("login或者app接口");
			return true;
		} else {
			//判断已经登陆的角色（teacher/student/manager）
			HttpSession session = request.getSession();
			String cha = requestURI.split(urljg)[2];
			//System.out.println(cha);
			User user = null;
			if(cha.equals("manage")){
				user = (User) session.getAttribute("Man_user");
			}else if(cha.equals("teacher")){
				user = (User) session.getAttribute("Tea_user");
			}else if(cha.equals("student")){
				user = (User) session.getAttribute("Stu_user");
			}else {
				return true;
			}
			
			if (user != null) {
				// 登陆成功的用户
				return true;
			} else {
				// 没有登陆或者没有权限，转向登陆界面
				System.out.println("拦截url");
				request.getRequestDispatcher("/webpage/error/PeimitError.jsp").forward(request, arg1);
				return false;
			}
		}
	}

}