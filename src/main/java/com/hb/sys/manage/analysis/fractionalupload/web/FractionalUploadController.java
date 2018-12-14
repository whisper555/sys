package com.hb.sys.manage.analysis.fractionalupload.web;

import java.io.InputStream;
import java.io.OutputStream;
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

import com.hb.sys.manage.analysis.fractionalupload.service.FractionalUploadService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.tools.gen.BaseController;

/**
 * 分数上传controller
 * @author hanbin
 * @date 2018年4月9日
 */

@Controller
@RequestMapping(value = "manage/analysis/fractionalupload")
public class FractionalUploadController extends BaseController{

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private  FractionalUploadService fractionalUploadService;
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
		System.out.println("student   list ---");
		List<StudentInfo> studentlist = studentInfoService.findList(studentInfo);
		model.addAttribute("studentlist", studentlist);
		return "modules/manage/analysis/fractionalupload/StuList";
	}



	
	/**
	 * 更新学生基本信息
	 */

	@RequestMapping(value = "save")
	public String save(StudentInfo studentInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (studentInfoService.get(studentInfo) == null) {
			studentInfoService.insert(studentInfo);
		} else {
			studentInfoService.update(studentInfo);// 保存
		}
		addMessage(redirectAttributes, "保存学生基本信息成功");
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
		HSSFWorkbook wb = fractionalUploadService.export(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=student.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		addMessage(redirectAttributes, "导出学生分数成功");
		return "redirect:list";  
	}

	@RequestMapping("/import")  
	public String impotrt(HttpServletRequest request, Model model,@RequestParam(required = true)MultipartFile file,RedirectAttributes redirectAttributes) throws Exception {  
	
			  InputStream in = file.getInputStream();  
			     //数据导入  
			     fractionalUploadService.importExcelInfo(in,file);  
			     in.close();  
				 addMessage(redirectAttributes, "导入学生分数成功");
			     return "redirect:list";  
  
	}  
	@RequestMapping("/import/template")  
	public void template(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		List<StudentInfo> studentlist = studentInfoService.findList(new StudentInfo());
		HSSFWorkbook wb = fractionalUploadService.templete(studentlist);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=template.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}  
	
}
