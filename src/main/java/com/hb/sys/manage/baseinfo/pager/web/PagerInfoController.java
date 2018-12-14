/**
 * 
 */
package com.hb.sys.manage.baseinfo.pager.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.department.service.DepartmentInfoService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;
import com.hb.sys.tools.fileupload.FileUtil;
import com.hb.sys.tools.gen.BaseController;

/**
 * 毕设题目信息管理controller
 * 
 * @author hanbin
 * @version 2017年10月27日
 */

@Controller
@RequestMapping(value = "manage/baseinfo/pager")
public class PagerInfoController extends BaseController {

	@Autowired
	private PagerInfoService pagerInfoService;
	@Autowired
	private DepartmentInfoService departmentInfoService;
	@Autowired
	private TeacherInfoService teacherInfoService;

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
	 * 毕设题目基本信息列表页面
	 */

	@RequestMapping(value = { "list", "" })
	public String list(PagerInfo pagerInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PagerInfo> pagerlist = pagerInfoService.findList(pagerInfo);
		model.addAttribute("pagerlist", pagerlist);
		return "modules/manage/baseinfo/pager/PagerInfoList";
	}

	/**
	 * 编辑毕设题目基本信息表单页面
	 */

	@RequestMapping(value = "edit")
	public String form(PagerInfo pagerInfo, Model model) {
		model.addAttribute("pagerInfo", pagerInfo);
		model.addAttribute("departList",departmentInfoService.findList(new DepartmentInfo()));
		model.addAttribute("teacherList",teacherInfoService.findList(new TeacherInfo()));

		return "modules/manage/baseinfo/pager/PagerInfoForm";
	}

	/**
	 * 插入毕设题目基本信息
	 */

	@RequestMapping(value = "insert")
	public String insert(PagerInfo pagerInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		model.addAttribute("departList",departmentInfoService.findList(new DepartmentInfo()));
		model.addAttribute("teacherList",teacherInfoService.findList(new TeacherInfo()));
		return "modules/manage/baseinfo/pager/PagerInfoForm";
	}

	/**
	 * 更新毕设题目基本信息
	 */

	@RequestMapping(value = "save")
	public String update(PagerInfo pagerInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (pagerInfoService.get(pagerInfo) == null) {
			pagerInfoService.insert(pagerInfo);
		} else {
			pagerInfoService.update(pagerInfo);// 保存
		}

		addMessage(redirectAttributes, "保存题目基本信息成功");

		return "redirect:list";
	}

	/**
	 * 删除毕设题目基本信息
	 */

	@RequestMapping(value = "delete")
	public String delete(PagerInfo pagerInfo, RedirectAttributes redirectAttributes) {
		pagerInfoService.delete(pagerInfo);
		addMessage(redirectAttributes, "删除题目基本信息成功");

		return "redirect:list";
	}
	//文件下载
	@RequestMapping(value = "filedownload")
	@ResponseBody
	public String filedownload(PagerInfo pagerInfo,  @RequestParam String file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		file = new String(file.getBytes("ISO8859-1"),"utf-8");
		FileUtil.downloadfile(file,request, response);
		return "success";
	}

}