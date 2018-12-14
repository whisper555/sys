package com.hb.sys.teacher.subject.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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
import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.department.service.DepartmentInfoService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.controlstation.file.dao.FileInfoDao;
import com.hb.sys.manage.controlstation.file.entity.FileInfo;
import com.hb.sys.tools.fileupload.FileUtil;
import com.hb.sys.tools.gen.BaseController;

/**
 * 教师上报课题Controller
 * @author hanbin
 * @date 2018年1月9日
 */
@RequestMapping(value = "teacher/subject/")
@Controller
public class SubjectController extends BaseController{


	@Autowired
	private PagerInfoService pagerInfoService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private DepartmentInfoService departmentInfoService;
	

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
	 * 
	 * @throws ParseException
	 */

	@RequestMapping(value = { "list", "" })
	public String list(PagerInfo pagerInfo,@RequestParam(required = true)String tid, HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		
		List<PagerInfo> pagerlist = pagerInfoService.findPagerListByTeacher(globalService.tid2id(tid));
		model.addAttribute("pagerlist", pagerlist);
		return "modules/teacher/subject/SubjectList";
	}

	/**
	 * 编辑毕设题目基本信息表单页面
	 */

	@RequestMapping(value = "edit")
	public String form(PagerInfo pagerInfo, Model model) {
		model.addAttribute("pagerInfo", pagerInfo);
		List<DepartmentInfo> departList = departmentInfoService.findList(new DepartmentInfo());
		model.addAttribute("departList", departList);
		return "modules/teacher/subject/SubjectForm";
	}

	/**
	 * 插入毕设题目基本信息
	 */

	@RequestMapping(value = "insert")
	public String insert(PagerInfo pagerInfo, Model model,String period, RedirectAttributes redirectAttributes) throws Exception {
		//对时间添加判断，如果不再时间范围内，跳转时间错误页面
		if (!globalService.judgetime(period)){
					return "error/TimeError";
		}
		List<DepartmentInfo> departList = departmentInfoService.findList(new DepartmentInfo());
		model.addAttribute("departList", departList);
		return "modules/teacher/subject/SubjectForm";
	}

	/**
	 * 更新毕设题目基本信息
	 */

	@RequestMapping(value = "save")
	public String update(PagerInfo pagerInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		pagerInfo.getTeacher().setId(globalService.tid2id(pagerInfo.getTeacher().getId()));
		if (pagerInfoService.get(pagerInfo) == null) {
			pagerInfoService.insert(pagerInfo);
		} else {
			pagerInfoService.update(pagerInfo);// 保存
		}

		return "redirect:list?tid="+pagerInfo.getTeacher().getT_id();
	}

	/**
	 * 删除毕设题目基本信息
	 */

	@RequestMapping(value = "delete")
	public String delete(PagerInfo pagerInfo,@RequestParam(required = true)String tid, @RequestParam(required = true)String period,RedirectAttributes redirectAttributes) {
		pagerInfoService.delete(pagerInfo);
		return "redirect:list?tid="+tid+"&period="+period;
	}

	//文件上传
	@RequestMapping(value = "fileupload")
	@ResponseBody
	public Map<String, Object> fileupload(PagerInfo pagerInfo,@RequestParam MultipartFile file, @RequestParam(required = true) String user_id,
			HttpServletRequest request,RedirectAttributes redirectAttributes){
		Map<String, Object> json = new HashMap<String, Object>();
		try{
			String url =FileUtil.upload(file,user_id,request);
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFile_url(url);
			fileInfo.setUser_id(user_id);
			fileInfoDao.insert(fileInfo);
			pagerInfo.setP_descripe(url.substring(url.lastIndexOf("\\")+1)+"|");
			pagerInfoService.update(pagerInfo);

			json.put("response", "文件上传成功");
			
		}catch (Exception e) {
			json.put("response", "文件上传失败");
		}
		return json;
	}
	//文件下载
	@RequestMapping(value = "filedownload")
	@ResponseBody
	public String filedownload(PagerInfo pagerInfo,  @RequestParam String file,HttpServletRequest request,HttpServletResponse response) throws IOException{
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
	public String delfile(PagerInfo pagerInfo,  @RequestParam String file,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
			FileUtil.delete(file, request, response);
			pagerInfo.setP_descripe(null);
			pagerInfoService.update(pagerInfo);
			addMessage(redirectAttributes,"删除文件成功" );
			return "success";
		}

	//文件删除
	@RequestMapping(value = "checkpager")
	@ResponseBody
	public String checkpager(String count,String tid,HttpServletRequest request,HttpServletResponse response){
		String result = pagerInfoService.checkPager(globalService.tid2id(tid),count);
		return result;
	}
}
