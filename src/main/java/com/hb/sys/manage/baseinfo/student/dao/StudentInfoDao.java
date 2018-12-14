/**
 * 
 */
package com.hb.sys.manage.baseinfo.student.dao;

import java.util.List;

import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.tools.annotation.MyBatisDao;
import com.hb.sys.tools.gen.CrudDao;

/**
 * @author hanbin
 * @version 2017年10月27日
 */
@MyBatisDao
public interface StudentInfoDao extends CrudDao<StudentInfo> {
	// 用于登陆
	public StudentInfo studentLogin(String id, String pw);

	// 学生端设置第一志愿
	public void setFirstOrder(String sid, String tid);

	// 学生端设置第二志愿
	public void setSecondOrder(String sid, String tid);

	// 学生端设置第三志愿
	public void setThirdOrder(String sid, String tid);

	// 用于教师选择学生
	public void updateTeacher(String sid, String tid,String pid);
	
	// app:用于教师给学生打分
		public void updateScore(String sid, String score);

	// 用于教师删除学生
	public void updateTeacherNull(String sid);

	// 用于教师查询我的学生列表
	public List<StudentInfo> findMyStudentList(String tid);

	// 用于第一志愿教师查看学生列表
	public List<StudentInfo> findFirstList(String tid);

	// 用于第二志愿教师查看学生列表
	public List<StudentInfo> findSecondList(String tid);

	// 用于第三志愿教师查看学生列表
	public List<StudentInfo> findThirdList(String tid);
	
	//教师端查询没有被选择的学生列表
	public List<StudentInfo> findLackStuList();
	//教师端查询没有被选择的学生列表 带参数  （班级）
	public List<StudentInfo> findLackStuListParam(String cid);
	
	//教师端选择学生
	public void selectstu(String id,String tid);
	
	//教师端判断学生人数
	public int countlimit(String id);
	
	//导入时判断是否学号已经存在
	public List<StudentInfo> exists(String s_id);
	
	//管理员：分配教师学生列表
	public List<StudentInfo> findAllocationList();
	
	//管理员：获取未分配教师学生列表
	public List<StudentInfo> findNoAllocationList();
	
}
