package com.hb.sys.student.mysubject.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.manage.controlstation.file.dao.FileInfoDao;
import com.hb.sys.manage.controlstation.file.entity.FileInfo;
import com.hb.sys.tools.fileupload.FileUtil;
import com.hb.sys.tools.gen.BaseController;

/**
 * 我的论文controller
 * 
 * @author hanbin
 * @version 2017年2月27日
 */

@Controller
@RequestMapping(value = "student/mysubject")
public class MySubjectController extends BaseController{

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private PagerInfoService pagerInfoService;
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private GlobalService globalService;

	@ModelAttribute
	public StudentInfo get(@RequestParam(required = false) String id) {
		StudentInfo entity = null;
		if (id != null && !id.equals("")) {
			entity = studentInfoService.get(globalService.sid2id(id));
		}
		if (entity == null) {
			entity = new StudentInfo();
		}

		return entity;
	}

	/**
	 * 编辑我的导师表单页面
	 */

	@RequestMapping(value = "list")
	public String list(StudentInfo studentInfo, Model model, HttpServletRequest request) {
		if (studentInfo != null) {
			if (studentInfo.getPager() == null||"".equals(studentInfo.getPager().getId())) {
				request.setAttribute("error", "提示");
				request.setAttribute("description", "暂时没有论文!");
				return "error/MessageError";
			} else {
				PagerInfo pager = pagerInfoService.get(studentInfo.getPager().getId());
				model.addAttribute("pager", pager);
				model.addAttribute("student", studentInfo);
				return "modules/student/mysubject/MySubject";
			}

		} else {
			request.setAttribute("error", "身份验证错误");
			request.setAttribute("description", "未获取到当前登陆的学生！");
			return "error/MessageError";
		}

	}
		//文件上传
		@RequestMapping(value = "fileupload")
		@ResponseBody
		public Map<String, Object> fileupload(StudentInfo studentInfo,@RequestParam MultipartFile file, @RequestParam(required = true) String user_id,
				HttpServletRequest request,RedirectAttributes redirectAttributes){
			Map<String, Object> json = new HashMap<String, Object>();
			try{
				String url =FileUtil.upload(file,user_id,request);
				FileInfo fileInfo = new FileInfo();
				fileInfo.setFile_url(url);
				fileInfo.setUser_id(user_id);
				fileInfoDao.insert(fileInfo);
				studentInfo.setId(globalService.sid2id(studentInfo.getId()));
				studentInfo.setS_file(url.substring(url.lastIndexOf("\\")+1)+"|");
				studentInfoService.update(studentInfo);

				json.put("response", "文件上传成功");
				
			}catch (Exception e) {
				json.put("response", "文件上传失败");
			}
			return json;
		}
		//文件下载
		@RequestMapping(value = "filedownload")
		@ResponseBody
		public String filedownload(StudentInfo studentInfo,  @RequestParam String file,HttpServletRequest request,HttpServletResponse response) {
			try{
				FileUtil.downloadfile(file,request, response);
				return "文件下载成功！";
			}catch(Exception e){
				return "文件下载出错！";
			}
			
		}
		//文件删除
		@RequestMapping(value = "delfile")
		@ResponseBody
		public String delfile(StudentInfo studentInfo,  @RequestParam String file,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
				FileUtil.delete(file, request, response);
				studentInfo.setId(globalService.sid2id(studentInfo.getId()));
				studentInfo.setS_file(null);
				studentInfoService.update(studentInfo);
				addMessage(redirectAttributes,"删除文件成功" );
				return "success";
			}

}