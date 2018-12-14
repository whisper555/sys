package com.hb.sys.manage.baseinfo.department.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.department.service.DepartmentInfoService;
import com.hb.sys.tools.gen.BaseController;



/**
 * 院系基本信息管理controller
 * @author hanbin
 * @version 2018年1月02日
 */

@Controller
@RequestMapping(value="manage/baseinfo/department")
public class DepartmentInfoController extends BaseController{
	
	@Autowired
	private DepartmentInfoService departmentInfoService;
	
	@ModelAttribute
	public DepartmentInfo get(@RequestParam(required=false) String id) {
		DepartmentInfo entity = null;
		if (id!=null&&!id.equals("")){
			entity = departmentInfoService.get(id);
		}
		if (entity == null){
			entity = new DepartmentInfo();
		}
		
		return entity;
	}
	
	/**
	 * 院系基本信息列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(DepartmentInfo departmentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<DepartmentInfo> departmentlist = departmentInfoService.findList(departmentInfo);	
		model.addAttribute("departmentlist", departmentlist);
		return "modules/manage/baseinfo/department/DepartmentInfoList";
	}

	/**
	 * 编辑院系基本信息表单页面
	 */
	
	@RequestMapping(value = "edit")
	public String form(DepartmentInfo departmentInfo, Model model) {
		model.addAttribute("departmentInfo", departmentInfo);
		return "modules/manage/baseinfo/department/DepartmentInfoForm";
	}

	/**
	 * 插入院系基本信息
	 */
	
	@RequestMapping(value = "insert")
	public String insert(DepartmentInfo departmentInfo, Model model, RedirectAttributes redirectAttributes) throws Exception{
		return "modules/manage/baseinfo/department/DepartmentInfoForm";
	}
	
	/**
	 * 更新院系基本信息
	 */
	
	@RequestMapping(value = "save")
	public String update(DepartmentInfo departmentInfo, Model model, RedirectAttributes redirectAttributes) throws Exception{
			if(departmentInfoService.get(departmentInfo)==null){
				departmentInfoService.insert(departmentInfo);
			}else
			{departmentInfoService.update(departmentInfo);//保存
			}
			
			addMessage(redirectAttributes, "保存院系基本信息成功");

		return "redirect:list";
	}
	
	
	/**
	 * 删除院系基本信息
	 */

	@RequestMapping(value = "delete")
	public String delete(DepartmentInfo departmentInfo, RedirectAttributes redirectAttributes) {
		departmentInfoService.delete(departmentInfo);
		addMessage(redirectAttributes, "删除院系基本信息成功");

		return "redirect:list";
	}
	

}