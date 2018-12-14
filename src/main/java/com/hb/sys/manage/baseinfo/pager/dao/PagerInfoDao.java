/**
 * 
 */
package com.hb.sys.manage.baseinfo.pager.dao;

import java.util.List;
import java.util.Map;

import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.tools.annotation.MyBatisDao;
import com.hb.sys.tools.gen.CrudDao;

/**
 * 毕设基本信息管理Dao接口
 * 
 * @author hanbin
 * @version 2017年10月23日
 */
@MyBatisDao
public interface PagerInfoDao extends CrudDao<PagerInfo> {
	// 学生端查询教师名下课题列表
	public List<PagerInfo> findPagerListByTeacher(String tid);
	//学生端查询论文列表
	public List<PagerInfo> findPagerForWish();
	//app:学生端查询论文列表(带论文类型和教师)
	public List<PagerInfo> findPagerForWishParam(PagerInfo p);
	//查询当前论文选择人数
	public String countStudent(PagerInfo pagerInfo);
	//查询当前论文已经选择的人数
	public String countPagerStudent(String pid);
	//ajax 获取教师名下可用的论文
	public List<Map<String,String>> getTeacherPagerList(String id);
	//validate 判断论文人数是否超过允许最大人数(获取剩余名额)
	public int checkPager(String id);

}
