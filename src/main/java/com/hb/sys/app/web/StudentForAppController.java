package com.hb.sys.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.classs.entity.ClasssInfo;
import com.hb.sys.manage.baseinfo.classs.service.ClasssInfoService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;
import com.hb.sys.tools.json.AjaxJson;
import com.hb.sys.tools.tlds.dict.dao.DictDao;

/**
 * 手机学生端controller
 * @author hanbin
 * @date 2018年4月28日
 */
@RequestMapping(value="/app/student")
@Controller
public class StudentForAppController extends BaseForAppController{

	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private TeacherInfoService teacherInfoService;
	@Autowired
	private PagerInfoService pagerInfoService;
	@Autowired
	private ClasssInfoService classsInfoService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private DictDao dictDao;
	
	/**
	 * 我的信息保存
	 * @param userId
	 * @param pw
	 * @param sex
	 * @param phone
	 */
	@RequestMapping(value="savemyself")
	@ResponseBody
	public String savemyself(@RequestParam("userId")String userId,@RequestParam("pw")String pw,@RequestParam("sex")String sex,
			@RequestParam("phone")String phone,@RequestParam("classsId")String classsId,@RequestParam("name")String name,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		ClasssInfo c = classsInfoService.get(classsId);
		try{
			StudentInfo s = studentInfoService.get(globalService.sid2id(userId));
			s.setS_pw(pw);
			s.setS_name(name);
			s.setClasss(c);
			s.setS_sex(dictDao.getDictValue(sex, "s_sex"));
			s.setS_phone(phone);
			studentInfoService.update(s);
			j.put("result", "信息保存成功");
			
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 我的信息
	 * @param userId
	 * @param phone
	 * @param response
	 * @return
	 */
	@RequestMapping(value="myself")
	@ResponseBody
	public String myself(@RequestParam("userId")String userId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		
		try{
			StudentInfo s = studentInfoService.get(globalService.sid2id(userId));
			s.setS_sex(dictDao.getDictLabel(s.getS_sex(), "s_sex"));
			j.put("student", s);
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	
	/**
	 * 我的导师
	 * @param userId
	 * @param phone
	 * @param response
	 * @return 教师信息 教师论文列表
	 */
	@RequestMapping(value="myteacher")
	@ResponseBody
	public String myteacher(@RequestParam("userId")String userId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		//Map<String,Object> map = new HashMap<>();
		TeacherInfo t = new TeacherInfo();
		try{
			StudentInfo s = studentInfoService.get(globalService.sid2id(userId));
			if(s.getTeacher()!=null){
				t = teacherInfoService.get(s.getTeacher().getId());
				t.setT_pro(dictDao.getDictLabel(t.getT_pro(), "t_pro"));
			}
			
			List<PagerInfo> pagerlist= new ArrayList<>();
			pagerlist  = pagerInfoService.findPagerListByTeacher(t.getId());
			for(PagerInfo p:pagerlist){
				p.setP_type(dictDao.getDictLabel(p.getP_type(), "p_type"));
			}
			t.setPagerlist(pagerlist);
			j.put("teacher", t);
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 我的论文
	 * @param userId
	 * @param response
	 * @return 论文对象
	 */
	@RequestMapping(value="mypager")
	@ResponseBody
	public String mypager(@RequestParam("userId")String userId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		Map<String,Object> map = new HashMap<>();
		PagerInfo p =new PagerInfo();
		try{
			StudentInfo s = studentInfoService.get(globalService.sid2id(userId));
			if(s.getPager()!=null){
				p = pagerInfoService.get(s.getPager().getId());
				p.setP_type(dictDao.getDictLabel(p.getP_type(), "p_type"));
				p.setTeacher( teacherInfoService.get(p.getTeacher().getId()));
			}

			map.put("student", s);
			map.put("pager", p);
			j.put("map", map);
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 我的志愿
	 * @param userId
	 * @param phone
	 * @param response
	 * @return
	 */
	@RequestMapping(value="mywish")
	@ResponseBody
	public String mywish(@RequestParam("userId")String userId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		
		try{
			StudentInfo s = studentInfoService.get(globalService.sid2id(userId));
			s.setS_wish1(pagerInfoService.get(s.getS_wish1().getId()));
			s.setS_wish2(pagerInfoService.get(s.getS_wish2().getId()));
			s.setS_wish3(pagerInfoService.get(s.getS_wish3().getId()));
			j.put("student", s);
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	
	
}
