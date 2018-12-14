/**
 * 
 */
package com.hb.sys.manage.baseinfo.classs.web;

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

import com.hb.sys.manage.baseinfo.classs.entity.ClasssInfo;
import com.hb.sys.manage.baseinfo.classs.service.ClasssInfoService;
import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.department.service.DepartmentInfoService;
import com.hb.sys.tools.gen.BaseController;



/**
 * 班级基本信息管理controller
 * @author hanbin
 * @version 2017年10月27日
 */

@Controller
@RequestMapping(value="manage/baseinfo/classs")
public class ClasssInfoController extends BaseController{
	
	@Autowired
	private ClasssInfoService classsInfoService;
	@Autowired
	private DepartmentInfoService departmentInfoService;
	
	@ModelAttribute
	public ClasssInfo get(@RequestParam(required=false) String id) {
		ClasssInfo entity = null;
		if (id!=null&&!id.equals("")){
			entity = classsInfoService.get(id);
		}
		if (entity == null){
			entity = new ClasssInfo();
		}
		//System.out.println(entity.toString());
		return entity;
	}
	
	/**
	 * 班级基本信息列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(ClasssInfo classsInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<ClasssInfo> classslist = classsInfoService.findList(classsInfo);	
		model.addAttribute("classslist", classslist);
		return "modules/manage/baseinfo/classs/ClasssInfoList";
	}

	/**
	 * 编辑班级基本信息表单页面
	 */
	
	@RequestMapping(value = "edit")
	public String form(ClasssInfo classsInfo, Model model) {
		model.addAttribute("classsInfo", classsInfo);
		model.addAttribute("departList",departmentInfoService.findList(new DepartmentInfo()));
		return "modules/manage/baseinfo/classs/ClasssInfoForm";
	}

	/**
	 * 插入班级基本信息
	 */
	
	@RequestMapping(value = "insert")
	public String insert(ClasssInfo classsInfo, Model model, RedirectAttributes redirectAttributes) throws Exception{
		return "modules/manage/baseinfo/classs/ClasssInfoForm";
	}
	
	/**
	 * 更新班级基本信息
	 */
	
	@RequestMapping(value = "save")
	public String save(ClasssInfo classsInfo, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("00");
			if(classsInfoService.get(classsInfo)==null){
				classsInfoService.insert(classsInfo);
			}else
			{classsInfoService.update(classsInfo);//保存
			}
			
			addMessage(redirectAttributes, "保存班级基本信息成功");

		return "redirect:list";
	}
	
	
	/**
	 * 删除班级基本信息
	 */

	@RequestMapping(value = "delete")
	public String delete(ClasssInfo classsInfo, RedirectAttributes redirectAttributes) {
		classsInfoService.delete(classsInfo);
		addMessage(redirectAttributes, "删除班级基本信息成功");

		return "redirect:list";
	}
	

}
