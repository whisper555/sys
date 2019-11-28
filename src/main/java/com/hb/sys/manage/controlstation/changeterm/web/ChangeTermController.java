package com.hb.sys.manage.controlstation.changeterm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hb.sys.manage.controlstation.changeterm.service.ChangeTermService;

/**
 * 换届管理controller
 * @author hanbin
 * @date 2018年3月9日
 */
@Controller
@RequestMapping(value = "manage/controlstation/changeterm")
public class ChangeTermController {

	@Autowired
	private ChangeTermService changeTermService;
	
	@RequestMapping(value = "list")
	public String list(){
		return "modules/manage/controlstation/changeterm/ChangeTermList";
	}
	@RequestMapping(value = "delstu")
	public String delstu(){
		changeTermService.delstu();
		return "redirect:list";
	}
	@RequestMapping(value = "delpager")
	public String delpager(){
		changeTermService.delpager();
		return "redirect:list";
	}

	@RequestMapping(value = "delteacher")
	public String delteacher(){
		changeTermService.delteacher();
		return "redirect:list";
	}

	@RequestMapping(value = "resetTeacherPw")
	public String resetTeacherPw(){
		changeTermService.resetTeacherPw();
		return "redirect:list";
	}

	@RequestMapping(value = "resetStudentPw")
	public String resetStudentPw(){
		changeTermService.resetStudentPw();
		return "redirect:list";
	}
	
}
