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

import com.hb.sys.app.dao.SimpleListForAppDao;
import com.hb.sys.app.entity.SimpleAdapterBean;
import com.hb.sys.tools.json.AjaxJson;

/**
 * 
 * @author hanbin
 * @date 2018年4月27日
 */
@RequestMapping(value="app")
@Controller
public class SimpleListForAppController extends BaseForAppController{
	
	@Autowired
	private SimpleListForAppDao simpleListForAppDao;
	/**
	 * 获取简单列表数据
	 * @param type
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="simplelist")
	@ResponseBody
	public String  getSimpleList(@RequestParam("type") String type,HttpServletResponse response)throws Exception{
		AjaxJson j = new AjaxJson();
		List<SimpleAdapterBean> list = new ArrayList<>();
		if (StringUtils.isEmpty(type)) {
			setAjaxJsonError1(j);
		} 
		try{
			if(type.equals("depart")){
				list = simpleListForAppDao.getDepartList();
			}else if(type.equals("classs")){
				list = simpleListForAppDao.getClasssList();
			}else if(type.equals("teacher")){
				list = simpleListForAppDao.getTeacherList();
			}
			j.put("list", list);
			
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
	}
	/**
	 * 获取字典表数据
	 * @param type
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="dictlist")
	@ResponseBody
	public String  getDictList(@RequestParam("type") String type,HttpServletResponse response)throws Exception{
		AjaxJson j = new AjaxJson();
		List<SimpleAdapterBean> list = new ArrayList<>();
		if (StringUtils.isEmpty(type)) {
			setAjaxJsonError1(j);
		} 
		try{
			list = simpleListForAppDao.getDictList(type);
			j.put("list", list);
			
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
	}
}
