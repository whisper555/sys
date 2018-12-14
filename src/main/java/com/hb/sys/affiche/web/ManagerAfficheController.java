/**
 * 
 */
package com.hb.sys.affiche.web;

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

import com.hb.sys.affiche.entity.Affiche;
import com.hb.sys.affiche.service.AfficheService;
import com.hb.sys.tools.gen.BaseController;

/**
 * 公告系统管理员controller
 * @author hanbin
 * @version 2018年04月09日
 */
@Controller
@RequestMapping(value="affiche")
public class ManagerAfficheController extends BaseController{

	@Autowired
	private AfficheService afficheService;
	
	@ModelAttribute
	public Affiche get(@RequestParam(required=false) String id) {
		Affiche entity = null;
		if (id!=null&&!id.equals("")){
			entity = afficheService.get(id);
		}
		if (entity == null){
			entity = new Affiche();
		}
		
		return entity;
	}
	
	/**
	 * 公告基本信息列表页面
	 */
	
	@RequestMapping(value = {"manager/list"})
	public String list(Affiche affiche, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Affiche> affichelist = afficheService.findList(affiche);	
		model.addAttribute("affichelist", affichelist);
		return "modules/affiche/manager/ManagerAfficheList";
	}


	/**
	 *编辑发布公告页面
	 */
	
	@RequestMapping(value = {"manager/add"})
	public String add(Affiche affiche, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/affiche/manager/ManagerAfficheAdd";
	}

	/**
	 * 查看公告基本信息表单页面
	 */
	
	@RequestMapping(value = "manager/edit")
	public String form(Affiche affiche, Model model) {
		model.addAttribute("affiche", affiche);
		return "modules/affiche/manager/ManagerAfficheView";
	}
	

	
	
	/**
	 * 保存公告基本信息
	 */
	
	@RequestMapping(value = "manager/save")
	public String sava(Affiche affiche, Model model, RedirectAttributes redirectAttributes) throws Exception{
			if(afficheService.get(affiche)==null){
				afficheService.insert(affiche);
			}else
			{afficheService.update(affiche);//保存
			}
			

		return "redirect:list";
	}
	
	
	/**
	 * 删除公告基本信息
	 */

	@RequestMapping(value = "manager/delete")
	public String delete(Affiche affiche, RedirectAttributes redirectAttributes) {
		afficheService.delete(affiche);
		return "redirect:list";
	}
	
	/**
	 * 查看公告基本信息表单页面
	 */
	
	@RequestMapping(value = "user/view")
	public String view(Affiche affiche, Model model) {
		model.addAttribute("affiche", affiche);
		return "modules/affiche/user/UserAfficheView";
	}
	/**
	 * 公告基本信息列表页面
	 */
	
	@RequestMapping(value = {"user/list"})
	public String ulist(Affiche affiche, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Affiche> affichelist = afficheService.findList(affiche);	
		model.addAttribute("affichelist", affichelist);
		return "modules/affiche/user/UserAfficheList";
	}
}
