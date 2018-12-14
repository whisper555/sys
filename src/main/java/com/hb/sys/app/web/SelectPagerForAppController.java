package com.hb.sys.app.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hb.sys.tools.tlds.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.manage.baseinfo.student.service.StudentInfoService;
import com.hb.sys.manage.baseinfo.teacher.service.TeacherInfoService;
import com.hb.sys.tools.json.AjaxJson;
import com.hb.sys.tools.tlds.dict.dao.DictDao;

/**
 * 手机端设置志愿
 * @author hanbin
 * @date 2018年5月11日
 */
@RequestMapping(value = "/app/choosePager")
@Controller
public class SelectPagerForAppController extends BaseForAppController {

	@Autowired
	PagerInfoService pagerInfoService;
	@Autowired
	StudentInfoService studentInfoService;
	@Autowired
	TeacherInfoService teacherInfoService;
	@Autowired
	GlobalService globalService;
	@Autowired
	DictService dictDao;

	/**
	 * 手机端请求所有志愿列表
	 * @param sid
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public String getPagerList(@RequestParam(required = true) String sid, HttpServletResponse response)
			throws Exception {
		AjaxJson j = new AjaxJson();

		try {
			
			List<PagerInfo> pagerlist = pagerInfoService.findPagerForWish();
			for (PagerInfo p:pagerlist) {
				p.setP_type(dictDao.getDictLabel(p.getP_type(),"p_type"));
			}
			
			j.put("list", pagerlist);

		} catch (Exception e) {
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response, j.getJsonStr());
	}
	/**
	 * 手机端请求所有志愿列表(带参数)
	 * @param tId
	 * @param pType
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listparam")
	@ResponseBody
	public String getPagerListParam(@RequestParam(required = true) String tId,@RequestParam(required = true) String pType, HttpServletResponse response)
			throws Exception {
		AjaxJson j = new AjaxJson();

		try {
			PagerInfo pager = new PagerInfo();
			pager.setP_type(pType);
			pager.setTeacher(teacherInfoService.get(tId));
			List<PagerInfo> pagerlist = pagerInfoService.findPagerForWishParam(pager);// 找出学生志愿中没有的论文列表
			for (PagerInfo p:pagerlist) {
				p.setP_type(dictDao.getDictLabel(p.getP_type(),"p_type"));
			}
			
			j.put("list", pagerlist);

		} catch (Exception e) {
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response, j.getJsonStr());
	}

	/**
	 * 手机端设置学生志愿顺序
	 * @param sid 学生学号
	 * @param pid 论文id
	 * @param order 志愿代码 1-第一志愿 2-第二志愿 3-第三志愿
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "setOrder")
	@ResponseBody
	public String setOrder(@RequestParam(required = true) String sid, @RequestParam(required = true) String sWish1,
			@RequestParam(required = true) String sWish2,@RequestParam(required = true) String sWish3, HttpServletResponse response) throws Exception {
		AjaxJson j = new AjaxJson();
		
		if (StringUtils.isEmpty(sid) || StringUtils.isEmpty(sWish1) || StringUtils.isEmpty(sWish2)||StringUtils.isEmpty(sWish3)) {
			setAjaxJsonError1(j);
		} else {
			try {
				
				studentInfoService.setFirstOrder(sid, sWish1);
				
				studentInfoService.setSecondOrder(sid, sWish2);
				
				studentInfoService.setThirdOrder(sid, sWish3);
				j.put("result", "志愿设置成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			j.put("result", "志愿设置失败！"+e.toString());
			setAjaxJsonError0(j);
		}
		}
		
		return renderString(response, j.getJsonStr());
	}
}
