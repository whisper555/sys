package com.hb.sys.app.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.tools.json.AjaxJson;

/**
 * 
 * @author hanbin
 * @date 2018年5月18日
 */
@Controller
@RequestMapping(value="app")
public class CheckTimeForAppController extends BaseForAppController{

	@Autowired
	private GlobalService globalService;
	
	@RequestMapping(value = "checktime")
	public String list(@RequestParam(required = true)String period,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		try {
			if (StringUtils.isEmpty(period)) {
				setAjaxJsonError1(j);
			} else {
				if (!globalService.judgetime(period)) {
					j.put("result", "false");
				}else{
					j.put("result", "true");
				}
				

			}
		} catch (Exception e) {
			setAjaxJsonError0(j);
		}
		return renderString(response, j.getJsonStr());
	}
}
