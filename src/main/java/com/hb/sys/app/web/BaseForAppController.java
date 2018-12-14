package com.hb.sys.app.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.hb.sys.tools.json.AjaxJson;
import com.hb.sys.tools.json.JsonMapper;



/**
 * APP根控制器
 * @author hanbin
 * @date 2018年4月25日
 */

public class BaseForAppController {

	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object));
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string) {
		try {
			response.reset();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	
	/**
	 * 接口错误
	 * @param string
	 * @return
	 */
	protected void setAjaxJsonError0(AjaxJson j) {
		j.setSuccess(false);
		j.setErrorCode("0");
		j.setMsg("接口异常!");
	}

	/**
	 * 缺少参数
	 * @param string
	 * @return
	 */
	protected void setAjaxJsonError1(AjaxJson j) {
		j.setSuccess(false);
		j.setErrorCode("1");
		j.setMsg("缺少参数");
	}
	
	/**
	 * 时间不正确
	 * @param string
	 * @return
	 */
	protected void setAjaxTimeError(AjaxJson j) {
		j.setSuccess(false);
		j.setErrorCode("1");
		j.setMsg("当前时间不正确！请查看时间！");
	}
	/**
	 * 返回信息
	 * @param string
	 * @return
	 */
	protected void setMessage(AjaxJson j,String mes) {
		j.setSuccess(true);
		j.setMsg(mes);
	}

}
