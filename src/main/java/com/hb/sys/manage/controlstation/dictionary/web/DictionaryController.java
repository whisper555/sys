package com.hb.sys.manage.controlstation.dictionary.web;

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

import com.hb.sys.manage.controlstation.dictionary.entity.Dictionary;
import com.hb.sys.manage.controlstation.dictionary.service.DictionaryService;

/**
 * 字典管理controller
 * @author hanbin
 * @version 2017年10月27日
 */

@Controller
@RequestMapping(value="manage/controlstation/dictionary")
public class DictionaryController {
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@ModelAttribute
	public Dictionary get(@RequestParam(required=false) String id) {
		Dictionary entity = null;
		if (id!=null&&!id.equals("")){
			entity = dictionaryService.get(id);
		}
		if (entity == null){
			entity = new Dictionary();
		}
		return entity;
	}
	
	/**
	 * 字典信息列表页面
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(Dictionary dictionary, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Dictionary> dictionarylist = dictionaryService.findList(dictionary);	
		model.addAttribute("dictionarylist", dictionarylist);
		return "modules/manage/controlstation/dictionary/DictionaryList";
	}

	/**
	 * 编辑字典信息表单页面
	 */
	
	@RequestMapping(value = "edit")
	public String form(Dictionary dictionary, Model model) {
		model.addAttribute("dictionary", dictionary);
		return "modules/manage/controlstation/dictionary/DictionaryForm";
	}

	/**
	 * 插入字典信息
	 */
	
	@RequestMapping(value = "insert")
	public String insert(Dictionary dictionary, Model model, RedirectAttributes redirectAttributes) throws Exception{
		return "modules/manage/controlstation/dictionary/DictionaryForm";
	}
	
	/**
	 * 更新字典信息
	 */
	
	@RequestMapping(value = "save")
	public String save(Dictionary dictionary, Model model, RedirectAttributes redirectAttributes) {
		//System.out.println("00");
			if(dictionaryService.get(dictionary)==null){
				dictionaryService.insert(dictionary);
			}else
			{dictionaryService.update(dictionary);//保存
			}
			

		return "redirect:list";
	}
	
	
	/**
	 * 删除字典信息
	 */

	@RequestMapping(value = "delete")
	public String delete(Dictionary dictionary, RedirectAttributes redirectAttributes) {
		dictionaryService.delete(dictionary);
		return "redirect:list";
	}

}
