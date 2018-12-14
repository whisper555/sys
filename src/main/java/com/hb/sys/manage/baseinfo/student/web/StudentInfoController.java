/**
 * 
 */
package com.hb.sys.manage.baseinfo.student.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.manage.baseinfo.classs.entity.ClasssInfo;
import com.hb.sys.manage.baseinfo.classs.service.ClasssInfoService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.tools.gen.BaseController;

/**
 * @author hanbin
 * @version 2017年10月27日
 */

@Controller
@RequestMapping(value = "manage/baseinfo/student")
public class StudentInfoController extends BaseController{

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private ClasssInfoService classsInfoService;

	@ModelAttribute
	public StudentInfo get(@RequestParam(required = false) String id) {
		
		StudentInfo entity = null;
		if (id != null && !id.equals("")) {
			entity = studentInfoService.get(id);
		}
		if (entity == null) {
			entity = new StudentInfo();
		}
		//System.out.println("Student****");
		return entity;
	}

	/**
	 * 学生基本信息列表页面
	 */

	@RequestMapping(value = { "list", "" })
	public String list(StudentInfo studentInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		//System.out.println("student   list ---");
		List<StudentInfo> studentlist = studentInfoService.findList(studentInfo);
		model.addAttribute("studentlist", studentlist);
		return "modules/manage/baseinfo/student/StudentInfoList";
	}

	/**
	 * 编辑学生基本信息表单页面
	 */

	@RequestMapping(value = "edit")
	public String form(StudentInfo studentInfo, Model model) {
		model.addAttribute("studentInfo", studentInfo);
		List<ClasssInfo> classsList = classsInfoService.findList(new ClasssInfo());
		model.addAttribute("classsList", classsList);
		return "modules/manage/baseinfo/student/StudentInfoForm";
	}

	/**
	 * 插入学生基本信息
	 */

	@RequestMapping(value = "insert")
	public String insert(StudentInfo studentInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		//System.out.println("学生基本信息插入--");
		List<ClasssInfo> classsList = classsInfoService.findList(new ClasssInfo());
		model.addAttribute("classsList", classsList);
		return "modules/manage/baseinfo/student/StudentInfoForm";
	}

	/**
	 * 更新学生基本信息
	 */

	@RequestMapping(value = "save")
	public String save(StudentInfo studentInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("学生基本信息保存--");
		if (studentInfoService.get(studentInfo) == null) {
			studentInfoService.insert(studentInfo);
		} else {
			studentInfoService.update(studentInfo);// 保存
		}
		addMessage(redirectAttributes, "保存学生基本信息成功");
		return "redirect:list";
	}

	/**
	 * 删除学生基本信息
	 */

	@RequestMapping(value = "delete")
	public String delete(StudentInfo studentInfo, RedirectAttributes redirectAttributes) {
		// System.out.println(studentInfo.getT_id());
		studentInfoService.delete(studentInfo);
		addMessage(redirectAttributes, "删除学生基本信息成功");
		return "redirect:list";
	}

	/**
	 * 导出学生信息表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String exportExcel(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception {
		List<StudentInfo> list = studentInfoService.findList(new StudentInfo());
		HSSFWorkbook wb = studentInfoService.export(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=student.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		addMessage(redirectAttributes, "导出学生基本信息成功");
		return "redirect:list";  
	}
	

	@RequestMapping("/import")  
	public String impotrt(HttpServletRequest request, Model model,@RequestParam(required = true)MultipartFile file,RedirectAttributes redirectAttributes) throws Exception {  
		try{
			InputStream in = file.getInputStream();  
		     //数据导入  
		     studentInfoService.importExcelInfo(in,file);  
		     in.close();  
			 addMessage(redirectAttributes, "导入学生基本信息成功");
		}catch(Exception e){
			e.printStackTrace();
			 addMessage(redirectAttributes, "请使用模版进行导入,中间不能有空列");
		}

	     return "redirect:list";  
	}  
	@RequestMapping("/import/template")  
	public void template(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		List<StudentInfo> list = new ArrayList<>();
		HSSFWorkbook wb = studentInfoService.export(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=template.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}  
	/**
	 * 导出学生论文选择表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportresult")
	public String exportResult(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception {
		List<StudentInfo> list = studentInfoService.findList(new StudentInfo());
		HSSFWorkbook wb = studentInfoService.exportResult(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=student.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		addMessage(redirectAttributes, "导出论文分配结果成功");
		return "redirect:list";  
	}
	
}
