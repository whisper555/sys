package com.hb.sys.app.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.sys.global.service.GlobalService;
import com.hb.sys.manage.baseinfo.pager.service.PagerInfoService;
import com.hb.sys.tools.json.AjaxJson;

/**
 * 
 * @author hanbin
 * @date 2018年5月18日
 */
@Controller
@RequestMapping(value="app")
public class SimpleListParamForAppController extends BaseForAppController{

	@Autowired
	private PagerInfoService pagerInfoService;
	@Autowired
	private GlobalService globalService;
	
/**
 * 待参数获取简单列表
 * @param param1   类型
 * @param param2   参数
 * @param response
 * @return
 * @throws Exception
 */
	@RequestMapping(value="simplelistparam")
	@ResponseBody
	public String  getSimpleList(@RequestParam("param1") String param1,@RequestParam("param2") String param2, HttpServletResponse response)throws Exception{
		AjaxJson j = new AjaxJson();
		
		if (StringUtils.isEmpty(param1)||StringUtils.isEmpty(param2)) {
			setAjaxJsonError1(j);
		} 
		try{
			if(param1.equals("availablepager")){  //教师安排论文列表
				List<Map<String,String>> list = pagerInfoService.getTeacherPagerList(globalService.tid2id(param2));
				/*List<SimpleAdapterBean> simplelist = new ArrayList<>();
				for (Map<String,String> map : list) {
					SimpleAdapterBean bean = new SimpleAdapterBean();
					bean.setLabel(map.get("label"));
					bean.setValue(map.get("value"));
					simplelist.add(bean);
				}
				j.put("list", simplelist);*/
				j.put("list", list);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			setAjaxJsonError0(j);
		}
		return renderString(response,j.getJsonStr());
	}
}
