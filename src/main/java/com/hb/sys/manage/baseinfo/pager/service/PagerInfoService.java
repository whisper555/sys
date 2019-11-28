/**
 * 
 */
package com.hb.sys.manage.baseinfo.pager.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hb.sys.manage.baseinfo.pager.dao.PagerInfoDao;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.tools.gen.CrudService;;

/**
 * 毕设基本信息管理service
 * 
 * @author hanbin
 * @version 2017年10月24日
 */
@Service
public class PagerInfoService extends CrudService<PagerInfoDao, PagerInfo> {
	// 学生端查询教师名下课题列表  教师端查询自己论文
	public List<PagerInfo> findPagerListByTeacher(String tid) {
		return dao.findPagerListByTeacher(tid);
	};
	//学生端查询论文列表
	//@Cacheable(value="sysCache",key="#root.methodName")
	public List<PagerInfo> findPagerForWish(){
		return dao.findPagerForWish();
	}
	
	//app:学生端查询论文列表
	public List<PagerInfo> findPagerForWishParam(PagerInfo p){
		return dao.findPagerForWishParam(p);
	}
	//查询当前论文选择人数
	public String countStudent(PagerInfo pagerInfo){
		return dao.countStudent(pagerInfo);
	}
	//ajax 获取教师当前还可以用的论文
	public List<Map<String,String>> getTeacherPagerList(String id){
		return dao.getTeacherPagerList(id);
	}
	//查询当前论文已经选择的人数
	public String countPagerStudent(String pid){
		return dao.countPagerStudent(pid);
	}
	//教师端提交论文时validate检查论文人数
	public String checkPager(String tid,String count){
		int result = 0;
		if (dao.checkPager(tid)<Integer.parseInt(count)) {
			 result = 0;
		} else {
			result = 1;
		}
		return String.valueOf(result);

	}
}
