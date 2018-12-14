package com.hb.sys.app.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;
import com.hb.sys.tools.Utils.StrUtils;
import com.hb.sys.tools.json.AjaxJson;
import com.hb.sys.tools.tlds.dict.dao.DictDao;

/**
 * 手机端教师Controller
 * @author hanbin
 * @date 2018年5月12日
 */
@RequestMapping(value = "/app/teacher")
@Controller
public class TeacherForApController extends BaseForAppController{

	@Autowired
	private TeacherInfoService teacherInfoService;
	/*@Autowired
	private DepartmentInfoService departmentInfoService;*/
	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private PagerInfoService pagerInfoService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private DictDao dictDao;
	
	/**
	 * 我的信息保存
	 * @param userId
	 * @param pw
	 * @param phone
	 */
	@RequestMapping(value="savemyself")
	@ResponseBody
	public String savemyself(@RequestParam("userId")String userId,@RequestParam("pw")String pw,@RequestParam("phone")String phone,
			@RequestParam("email")String email,@RequestParam("departId")String departId,@RequestParam("tMax")String tMax,
			@RequestParam("tPro")String tPro,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(userId)) {
			setAjaxJsonError1(j);
		} 
		try{
			TeacherInfo t = teacherInfoService.get(globalService.tid2id(userId));
			t.setT_pw(pw);
			t.setT_phone(phone);
			t.setT_mail(email);
			t.setT_max(tMax);
			t.setT_pro(dictDao.getDictValue(tPro, "t_pro"));
			teacherInfoService.update(t);
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
		if (StringUtils.isEmpty(userId)) {
			setAjaxJsonError1(j);
		} 
		try{
			TeacherInfo t = teacherInfoService.get(globalService.tid2id(userId));
			t.setT_pro(dictDao.getDictLabel(t.getT_pro(), "t_pro"));
			j.put("teacher", t);
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 我的论文列表
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="mypager")
	@ResponseBody
	public String mypager(@RequestParam("userId")String userId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(userId)) {
			setAjaxJsonError1(j);
		}
		try{
			List<PagerInfo> list = pagerInfoService.findPagerListByTeacher(globalService.tid2id(userId));
			for(PagerInfo p:list){
				p.setP_type(dictDao.getDictLabel(p.getP_type(), "p_type"));
			}
			j.put("list", list);
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 保存我的论文（无pagerId添加 有pagerId编辑）
	 * @param pagerId 论文id
	 * @param pName 论文名称
	 * @param departId 院系id
	 * @param pType
	 * @param tId
	 * @param pMax
	 * @param response
	 * @return
	 */
	@RequestMapping(value="savepager")
	@ResponseBody
	public String savepager(String pagerId,@RequestParam("pName")String pName,@RequestParam("pType")String pType,
			@RequestParam("tId")String tId,@RequestParam("pMax")String pMax,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		PagerInfo p = new PagerInfo();
		TeacherInfo t = teacherInfoService.get(globalService.tid2id(tId));
		p.setDepartment(t.getDepartment());
		p.setId(pagerId);
		p.setP_max(pMax);
		p.setP_name(pName);
		p.setP_type(dictDao.getDictValue(pType, "p_type"));
		p.setTeacher(t);
		if (StringUtils.isEmpty(pagerId)) {
			pagerInfoService.insert(p);
			j.put("result", "插入论文信息成功！");
			//判断论文人数
			/*if(pagerInfoService.checkPager(globalService.tid2id(tId),pMax).equals("1")){
				pagerInfoService.insert(p);
				j.put("result", "插入论文信息成功！");
			}else{
				j.put("result", "论文人数总和超过教师允许最大人数，请合理分配论文人数！");
			}*/
		}else{
			try{
				pagerInfoService.update(p);
				j.put("result", "更新论文信息成功！");
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		} 
		
		return renderString(response,j.getJsonStr());
		
	}
	
	/**
	 * 这个论文
	 * @param pagerId 论文id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="pagerform")
	@ResponseBody
	public String pagerform(@RequestParam("pid")String pid,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		
		if (StringUtils.isEmpty(pid)) {
			setAjaxJsonError1(j);
		}else{
			try{
				PagerInfo p = pagerInfoService.get(pid);
				p.setP_type(dictDao.getDictLabel(p.getP_type(), "p_type"));
				j.put("pagerInfo", p);
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 删除我的论文
	 * @param pagerId 论文id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="delpager")
	@ResponseBody
	public String delpager(@RequestParam("pagerId")String pagerId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		PagerInfo p = new PagerInfo();
		if (StringUtils.isEmpty(pagerId)) {
			setAjaxJsonError1(j);
		}else{
			try{
				p.setId(pagerId);
				pagerInfoService.delete(p);
				j.put("result", "删除论文信息成功");
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 我的学生列表
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="mystudent")
	@ResponseBody
	public String mystudent(@RequestParam("userId")String userId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(userId)) {
			setAjaxJsonError1(j);
		}
		try{
			List<StudentInfo> list = studentInfoService.findMyStudentList(userId);
			for(StudentInfo s:list){
				s.setS_sex(dictDao.getDictLabel(s.getS_sex(), "s_sex"));
			}
			j.put("list", list);
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 我的学生列表保存 分配的论文
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="savePager")
	@ResponseBody
	public String savePager(@RequestParam("sid")String sid,@RequestParam("pid")String pid,@RequestParam("tid")String tid,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(sid)||StringUtils.isEmpty(tid)||StringUtils.isEmpty(pid)) {
			setAjaxJsonError1(j);
		}
		try{
			if(StrUtils.SringToInt(pagerInfoService.get(pid).getP_max())>StrUtils.SringToInt(pagerInfoService.countPagerStudent(pid))){
				studentInfoService.updateTeacher(sid, tid, pid);
				j.put("result", "保存论文成功！");
			}else{
				j.put("result", "论文人数已经达到上限，请修改论文上限人数！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}

	/**
	 * 我的学生列表保存 教师评分
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="saveScore")
	@ResponseBody
	public String saveScore(@RequestParam("stuid")String stuid,@RequestParam("score")String score,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(stuid)||StringUtils.isEmpty(score)) {
			setAjaxJsonError1(j);
		}
		try{
			studentInfoService.updateScore(stuid, score);
			j.put("result", "保存分数成功！");
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 拒绝我的学生
	 * @param pagerId 论文id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="delstu")
	@ResponseBody
	public String delstudent(@RequestParam("sId")String sId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();	
		if (StringUtils.isEmpty(sId)) {
			setAjaxJsonError1(j);
		}else{
			try{
				studentInfoService.updateTeacherNull(sId);// 老师删除学生
				j.put("result", "拒绝学生成功");
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		}
		return renderString(response,j.getJsonStr());
		
	}
	/**
	 * 获取第一第二第三志愿列表 
	 * @param tId 教师tid
	 * @param period 3：第一志愿 4第二志愿 5 第三志愿
	 * @param response
	 * @return
	 */
	@RequestMapping(value="choosestuorderlist")
	@ResponseBody
	public String choosestuorderlist(@RequestParam(required = true) String userId,@RequestParam(required = true) String period,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		List<StudentInfo> studentlist = new ArrayList<>();
		if (StringUtils.isEmpty(period)||StringUtils.isEmpty(userId)) {
			setAjaxJsonError1(j);
		}else{
			try{
				
				if (period.equals("3")) {
					studentlist = studentInfoService.findFirstList(userId);
					for(StudentInfo s:studentlist){
						s.setS_sex(dictDao.getDictLabel(s.getS_sex(), "s_sex"));
					}
				} else if (period.equals("4")) {
					studentlist = studentInfoService.findSecondList(userId);
					for(StudentInfo s:studentlist){
						s.setS_sex(dictDao.getDictLabel(s.getS_sex(), "s_sex"));
					}
				} else {
					studentlist = studentInfoService.findThirdList(userId);
					for(StudentInfo s:studentlist){
						s.setS_sex(dictDao.getDictLabel(s.getS_sex(), "s_sex"));
					}
					
				}
				j.put("list",studentlist);
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		}
		return renderString(response,j.getJsonStr());
	}
	/**
	 * 选择第一第二第三志愿 选择学生
	 * @param tId   是id不是工号
	 * @param sId
	 * @param pId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="choosestuorder")
	@ResponseBody
	public String choosestuorder(@RequestParam(required = true) String tId,@RequestParam(required = true) String sId,@RequestParam(required = true) String pId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(sId)||StringUtils.isEmpty(tId)||StringUtils.isEmpty(pId)) {
			setAjaxJsonError1(j);
		}else{
			try{
				if(studentInfoService.countlimit(globalService.tid2id(tId))){//判断教师名下人数
					if(StrUtils.SringToInt(pagerInfoService.get(pId).getP_max())>StrUtils.SringToInt(pagerInfoService.countPagerStudent(pId))){//判断论文名下人数
						studentInfoService.updateTeacher(sId,tId, pId);//老师选定学生,更新学生论文信息
						j.put("result","选择成功！");		
					}else{
						j.put("result","论文学生数达到上限，请修改论文上限人数！");	
					}
				}else{
					j.put("result","教师名下学生数达到上限，请修改导师上限人数！");	
				}
				
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		}
		return renderString(response,j.getJsonStr());
	}
	/**
	 * 获取剩余学生列表 
	
	 * @param response
	 * @return
	 */
	@RequestMapping(value="choosestulist")
	@ResponseBody
	public String choosestulist(HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		try{
				List<StudentInfo> stulist = studentInfoService.findLackStuList();
				for(StudentInfo s:stulist){
					s.setS_sex(dictDao.getDictLabel(s.getS_sex(), "s_sex"));
				}
				j.put("list",stulist);
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		
		return renderString(response,j.getJsonStr());
	}
	/**
	 * 获取剩余学生列表  带参数
	
	 * @param response
	 * @return
	 */
	@RequestMapping(value="choosestulistparam")
	@ResponseBody
	public String choosestulistParam(@RequestParam(required = true) String cId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(cId)) {
			setAjaxJsonError1(j);
		}else{
			try{
				List<StudentInfo> stulist = studentInfoService.findLackStuListParam(cId);
				for(StudentInfo s:stulist){
					s.setS_sex(dictDao.getDictLabel(s.getS_sex(), "s_sex"));
				}
				j.put("list",stulist);
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
			}
		}
		return renderString(response,j.getJsonStr());
	}
	/**
	 * 选择学生    选择学生 （只保存教师）
	 * @param tId
	 * @param sId
	 * @param pId
	 * @param response
	 * @return
	 */
	@RequestMapping(value="choosestu")
	@ResponseBody
	public String choosestu(@RequestParam(required = true) String tId,@RequestParam(required = true) String sId,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtils.isEmpty(sId)||StringUtils.isEmpty(tId)) {
			setAjaxJsonError1(j);
		}else{
			try{
				if(studentInfoService.countlimit(globalService.tid2id(tId))){//判断教师名下人数
					studentInfoService.selectstu(sId,globalService.tid2id(tId));
					j.put("result","选择成功！");
				}else{
					j.put("result","教师名下学生数达到上限，请修改导师上限人数！");
				}
				
			}catch(Exception e){
				e.printStackTrace();
				setAjaxJsonError0(j);
				j.put("result","选择失败！");
			}
		}
		return renderString(response,j.getJsonStr());
	}
}
