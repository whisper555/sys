package com.hb.sys.manage.controlstation.schedule.web;


import java.text.ParseException;
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

import com.hb.sys.manage.controlstation.schedule.entity.Schedule;
import com.hb.sys.manage.controlstation.schedule.service.ScheduleService;
import com.hb.sys.tools.Utils.DateUtil;



/**
 * 时间控制controller
 * @author hanbin
 * @version 2017年1月08日
 */

@Controller
@RequestMapping(value="manage/controlstation/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@ModelAttribute
	public Schedule get(@RequestParam(required=false) String id) {
		Schedule entity = null;
		if (id!=null&&!id.equals("")){
			entity = scheduleService.get(id);
		}
		if (entity == null){
			entity = new Schedule();
		}
		//System.out.println(entity.toString());
		return entity;
	}
	
	/**
	 * 时间控制列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(Schedule schedule, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Schedule> schedulelist = scheduleService.findList(schedule);	
		/*for(Schedule sche :schedulelist){
			sche.setStarttime(DateUtil.stampToDate(sche.getStarttime()));
			sche.setEndtime(DateUtil.stampToDate(sche.getEndtime()));
		}*/
		model.addAttribute("schedulelist", schedulelist);
		return "modules/manage/controlstation/schedule/ScheduleList";
	}

	/**
	 * 编辑时间控制表单页面
	 */
	
	@RequestMapping(value = "edit")
	public String form(Schedule schedule, Model model) {
		schedule.setStarttimestr(DateUtil.DateToString(schedule.getStarttime()));
		schedule.setEndtimestr(DateUtil.DateToString(schedule.getEndtime()));
		model.addAttribute("schedule", schedule);
		return "modules/manage/controlstation/schedule/ScheduleForm";
	}

	
	
	/**
	 * 更新时间控制
	 * @throws ParseException 
	 */
	
	@RequestMapping(value = "save")
	public String save(Schedule schedule, Model model, RedirectAttributes redirectAttributes) throws ParseException {
		schedule.setStarttime(DateUtil.StringToDate(schedule.getStarttimestr()));
		schedule.setEndtime(DateUtil.StringToDate(schedule.getEndtimestr()));
		/*schedule.setStarttime(DateUtil.dateToStamp(schedule.getStarttime()));
		schedule.setEndtime(DateUtil.dateToStamp(schedule.getEndtime()));*/
			if(scheduleService.get(schedule)==null){
				scheduleService.insert(schedule);
			}else
			{scheduleService.update(schedule);//保存
			}
			

		return "redirect:list";
	}
	
	
	/**
	 * 删除时间控制
	 */

	@RequestMapping(value = "delete")
	public String delete(Schedule schedule, RedirectAttributes redirectAttributes) {
		scheduleService.delete(schedule);
		return "redirect:list";
	}
	
	
}

