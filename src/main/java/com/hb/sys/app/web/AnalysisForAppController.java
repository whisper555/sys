package com.hb.sys.app.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.analysis.classs.service.ClasssAnalysisService;
import com.hb.sys.analysis.student.service.StudentAnalysisService;
import com.hb.sys.analysis.teacher.service.TeacherAnalysisService;
import com.hb.sys.tools.json.AjaxJson;

/**
 * 统计分析
 * @author hanbin
 * @date 2018年4月27日
 */
@RequestMapping(value="/app/analysis")
@Controller
public class AnalysisForAppController extends BaseForAppController{

	@Autowired
	private ClasssAnalysisService classsAnalysisService;
	@Autowired
	private TeacherAnalysisService teacherAnalysisService;
	@Autowired
	private StudentAnalysisService studentAnalysisService;
	/**
	 * 获取班级教师统计
	 * @param type
	 * @param charttype
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="TC")
	@ResponseBody
	public String  TCAnalysis(@RequestParam("type") String type,@RequestParam("charttype") String charttype,HttpServletResponse response)throws Exception{
		AjaxJson j = new AjaxJson();
		Map<String,Object> AnalysisMap=new HashMap<String,Object>();
		if (StringUtils.isEmpty(type)||StringUtils.isEmpty(charttype)) {
			setAjaxJsonError1(j);
		} 
		try{
			if(type.equals("classs")){
				if(charttype.equals("ClaScoreBar")){
					AnalysisMap = classsAnalysisService.getClaScoreBarData();//平均分
				}else if(charttype.equals("ClaRateBar")){
					AnalysisMap = classsAnalysisService.getClaRateBarData();//通过率
				}
			}else if(type.equals("teacher")){
				if(charttype.equals("StuNumBar")){
					AnalysisMap = teacherAnalysisService.getStuNumBarData();//教师名下学生人数
				}else if(charttype.equals("StuScoreBar")){
					AnalysisMap = teacherAnalysisService.getStuScoreBarData();//教师名下学生平均分
				}else if(charttype.equals("StuRateBar")){
					AnalysisMap = teacherAnalysisService.getStuRateBarData();//教师名下学生通过率
				}
			}
			j.put("map", AnalysisMap);
			
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
	}
	/**
	 * 获取学生统计
	 * @param departId
	 * @param classsId
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="S")
	@ResponseBody
	public String  SAnalysis(@RequestParam("departId")String departId,String classsId,HttpServletResponse response)throws Exception{
		AjaxJson j = new AjaxJson();
		Map<String,Object> AnalysisMap=new HashMap<String,Object>();
		
		try{
			if(StringUtils.isEmpty(departId)){
				AnalysisMap = studentAnalysisService.getDepartStuLineData("1");//获取id为1的数据
			}
			if(StringUtils.isEmpty(classsId)){
				AnalysisMap = studentAnalysisService.getDepartStuLineData(departId);
			}else{
				AnalysisMap = studentAnalysisService.getClasssStuLineData(departId,classsId);
				
			}
			j.put("map", AnalysisMap);
			
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
	}
}
