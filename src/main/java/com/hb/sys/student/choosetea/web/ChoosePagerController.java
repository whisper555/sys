package com.hb.sys.student.choosetea.web;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.tools.Utils.StrUtils;

/**
 * 学生端选择志愿controller
 * 
 * @author hanbin
 * @date 2018年1月23日
 */
@Controller
@RequestMapping(value = "student/choosepager")
public class ChoosePagerController {

	@Autowired
	PagerInfoService pagerInfoService;
	@Autowired
	StudentInfoService studentInfoService;
	@Autowired
	GlobalService globalService;

	@ModelAttribute
	public PagerInfo get(@RequestParam(required = false) String id) {
		PagerInfo entity = null;
		if (id != null && !id.equals("")) {
			entity = pagerInfoService.get(id);
		}
		if (entity == null) {
			entity = new PagerInfo();
		}

		return entity;
	}

	/**
	 * 志愿基本信息列表页面
	 * @throws ParseException 
	 */

	@RequestMapping(value = { "list", "" })
	public String list(PagerInfo pagerInfo, @RequestParam(required = true) String sid,@RequestParam(required = true) String period, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		// 对时间添加判断，如果不再时间范围内，跳转时间错误页面
				if (!globalService.judgetime(period)) {
					return "error/TimeError";
				}
		StudentInfo studentInfo = new StudentInfo();
		studentInfo = studentInfoService.get(globalService.sid2id(sid));
		List<PagerInfo> pagerlist = pagerInfoService.findPagerForWish();// 找出学生志愿中没有的论文列表
		for (int i = 0; i < pagerlist.size(); i++) {
			if(studentInfo.getS_wish1()!=null){
				studentInfo.setS_wish1(pagerInfoService.get(studentInfo.getS_wish1().getId()));
//				if (pagerlist.get(i).getId().equals(StrUtils.hanleNull(studentInfo.getS_wish1().getId()))) {
//					pagerlist.remove(i);
//					
//				}
			}
			if(studentInfo.getS_wish2()!=null){
				studentInfo.setS_wish2(pagerInfoService.get(studentInfo.getS_wish2().getId()));
//				if (pagerlist.get(i).getId().equals(StrUtils.hanleNull(studentInfo.getS_wish2().getId()))) {
//					pagerlist.remove(i);
//					
//				}
			}
			if(studentInfo.getS_wish3()!=null){
				studentInfo.setS_wish3(pagerInfoService.get(studentInfo.getS_wish3().getId()));
//				if (pagerlist.get(i).getId().equals(StrUtils.hanleNull(studentInfo.getS_wish3().getId()))) {
//					pagerlist.remove(i);
//					
//				}
			}
			
			pagerlist.get(i).setP_now(StrUtils.SringToInt(pagerInfoService.countStudent(pagerInfo)));
		}
		model.addAttribute("pagerlist", pagerlist);
		model.addAttribute("studentInfo", studentInfo);
		return "modules/student/choosepager/PagerList";
	}

	/*
	 * 学生端设置志愿顺序
	 */
	@RequestMapping(value = "setorder")
	public String setorder(PagerInfo pagerInfo, @RequestParam(required = true) String sid,
			@RequestParam(required = true) String order, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		switch (order) {
		case "1":
			studentInfoService.setFirstOrder(sid, pagerInfo.getId());
			break;
		case "2":
			studentInfoService.setSecondOrder(sid, pagerInfo.getId());
			break;
		case "3":
			studentInfoService.setThirdOrder(sid, pagerInfo.getId());
			break;
		default:
		}
		return "redirect:list?sid=" + sid+"&period=2";
	}

}
