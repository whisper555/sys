package com.hb.sys.manage.controlstation.file.web;

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

import com.hb.sys.manage.controlstation.file.entity.FileInfo;
import com.hb.sys.manage.controlstation.file.service.FileInfoService;
import com.hb.sys.tools.fileupload.FileUtil;

/**
 * 文件管理controller
 * @author hanbin
 * @date 2018年3月8日
 */
@Controller
@RequestMapping(value="manage/controlstation/file")
public class FileInfoController {
	@Autowired
	private FileInfoService fileInfoService;
	
	@ModelAttribute
	public FileInfo get(@RequestParam(required=false) String id) {
		FileInfo entity = null;
		if (id!=null&&!id.equals("")){
			entity = fileInfoService.get(id);
		}
		if (entity == null){
			entity = new FileInfo();
		}
		//System.out.println(entity.toString());
		return entity;
	}
	
	/**
	 * 文件基本信息列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(FileInfo fileInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<FileInfo> filelist = fileInfoService.findList(fileInfo);	
		model.addAttribute("filelist", filelist);
		return "modules/manage/controlstation/file/FileInfoList";
	}

	
	/**
	 * 删除文件基本信息
	 */

	@RequestMapping(value = "delete")
	public String delete(FileInfo fileInfo, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
		fileInfoService.delete(fileInfo);
		FileUtil.delete(fileInfo.getFile_url(),request,response);
		return "redirect:list";
	}
	/**
	 * 删除文件基本信息
	 */

	@RequestMapping(value = "deleteAll")
	public String deleteAll(FileInfo fileInfo, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		fileInfoService.deleteAll();
		FileUtil.delAllFile(request.getSession().getServletContext().getRealPath("/files/"));
		return "redirect:list";
	}
}
