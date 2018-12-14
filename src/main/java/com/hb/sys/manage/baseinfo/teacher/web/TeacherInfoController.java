/**
 * 
 */
package com.hb.sys.manage.baseinfo.teacher.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.department.service.DepartmentInfoService;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;
import com.hb.sys.tools.gen.BaseController;

/**
 * 教师基本信息管理controller
 * 
 * @author hanbin
 * @version 2017年10月24日
 */

@Controller
@RequestMapping(value = "manage/baseinfo/teacher")
public class TeacherInfoController extends BaseController {

	@Autowired
	private TeacherInfoService teacherInfoService;
	@Autowired
	private DepartmentInfoService departmentInfoService;

	@ModelAttribute
	public TeacherInfo get(@RequestParam(required = false) String id) {
		TeacherInfo entity = null;
		if (id != null && !id.equals("")) {
			entity = teacherInfoService.get(id);
		}
		if (entity == null) {
			entity = new TeacherInfo();
		}

		return entity;
	}

	/**
	 * 教师基本信息列表页面
	 */

	@RequestMapping(value = { "list", "" })
	public String list(TeacherInfo teacherInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TeacherInfo> teacherlist = teacherInfoService.findList(teacherInfo);
		// System.out.println(teacherInfoService.findList(teacherInfo).toString());
		model.addAttribute("teacherlist", teacherlist);
		return "modules/manage/baseinfo/teacher/TeacherInfoList";
	}

	/**
	 * 编辑教师基本信息表单页面
	 */

	@RequestMapping(value = "edit")
	public String form(TeacherInfo teacherInfo, Model model) {
		List<DepartmentInfo> departList = departmentInfoService.findList(new DepartmentInfo());
		model.addAttribute("departList", departList);
		model.addAttribute("teacherInfo", teacherInfo);
		return "modules/manage/baseinfo/teacher/TeacherInfoForm";
	}

	/**
	 * 插入教师基本信息
	 */

	@RequestMapping(value = "insert")
	public String insert(TeacherInfo teacherInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		List<DepartmentInfo> departList = departmentInfoService.findList(new DepartmentInfo());
		model.addAttribute("departList", departList);
		return "modules/manage/baseinfo/teacher/TeacherInfoForm";
	}

	/**
	 * 更新教师基本信息
	 */

	@RequestMapping(value = "save")
	public String save(TeacherInfo teacherInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("教师基本信息保存--");
		if (teacherInfoService.get(teacherInfo) == null) {
			teacherInfoService.insert(teacherInfo);
		} else {
			teacherInfoService.update(teacherInfo);// 保存
		}
		addMessage(redirectAttributes, "保存教师基本信息成功");

		return "redirect:list";
	}

	/**
	 * 删除教师基本信息
	 */

	@RequestMapping(value = "delete")
	public String delete(TeacherInfo teacherInfo, RedirectAttributes redirectAttributes) {
		// System.out.println(teacherInfo.getT_id());
		teacherInfoService.delete(teacherInfo);
		addMessage(redirectAttributes, "删除教师基本信息成功");

		return "redirect:list";
	}

	/*
	 * 教师获取所在院系信息
	 */
	@RequestMapping(value = "getdepartmentlist")
	@ResponseBody
	public List<Map<String, String>> getdepartmentlist() {
		return teacherInfoService.getdepartmentlist();
	}

	/**
	 * 导出教师信息表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {
		List<TeacherInfo> list = teacherInfoService.findList(new TeacherInfo());
		HSSFWorkbook wb = teacherInfoService.export(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=teacher.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		addMessage(redirectAttributes, "导出教师基本信息成功");

	}

	@RequestMapping("/import")
	public String impotrt(HttpServletRequest request, Model model, @RequestParam(required = true) MultipartFile file,
			RedirectAttributes redirectAttributes) throws Exception {

		InputStream in = file.getInputStream();
		// 数据导入
		teacherInfoService.importExcelInfo(in, file);
		in.close();
		addMessage(redirectAttributes, "导入教师基本信息成功");

		return "redirect:list";
	}

	@RequestMapping("/import/template")
	public void template(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<TeacherInfo> list = new ArrayList<>();
		HSSFWorkbook wb = teacherInfoService.export(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=template.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 管理员：没有提交论文的教师列表
	 * @param teacherInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/nopagerlist")
	public String nopagerList(TeacherInfo teacherInfo, HttpServletRequest request, HttpServletResponse response, Model model){
			List<TeacherInfo> teacherlist = teacherInfoService.findNoPagerList();
			// System.out.println(teacherInfoService.findList(teacherInfo).toString());
			model.addAttribute("teacherlist", teacherlist);
			return "modules/manage/allocation/teachernopager/teacherNoPagerList";

	}

	/**
	 * 管理员：没有学生的教师列表
	 * @param teacherInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/nostudentlist")
	public String nostudentList(TeacherInfo teacherInfo, HttpServletRequest request, HttpServletResponse response, Model model){
		List<TeacherInfo> teacherlist = teacherInfoService.findNoStudentList();
		model.addAttribute("teacherlist", teacherlist);
		return "modules/manage/allocation/teachernostudent/teacherNoStudentList";

	}
}
