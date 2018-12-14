package com.hb.sys.global.web;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.global.service.GlobalService;

/**
 * 全局时间判断Controller
 * @author hanbin
 * @date 2018年1月8日
 */
@Controller
@RequestMapping(value="global")
public class GloabalController {
	@Autowired
	GlobalService globalService;
	/**
	 * 判断时间是否正确
	 * @param period
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "judgetime")
	@ResponseBody
	public boolean judgetime(String period) throws ParseException{
		boolean result = globalService.judgetime(period);
		return result;
	}
}
