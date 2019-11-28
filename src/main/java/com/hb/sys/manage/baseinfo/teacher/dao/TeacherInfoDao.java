/**
 * 
 */
package com.hb.sys.manage.baseinfo.teacher.dao;

import java.util.List;
import java.util.Map;

import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;
import com.hb.sys.tools.annotation.MyBatisDao;
import com.hb.sys.tools.gen.CrudDao;
/**
 * @author hanbin
 * @version 2017年10月23日
 */
@MyBatisDao
public interface TeacherInfoDao extends CrudDao<TeacherInfo>{
	public List<Map<String,String>> getdepartmentList();
	//用于教师登录
	public TeacherInfo teacherLogin(String id,String pw);
	//用于管理员登录
	public TeacherInfo manageLogin(String id,String pw);
	//学生端选择志愿获取wish中没有的教师列表
	public List<TeacherInfo> findListForWish(StudentInfo student);
	//导入时判断是否已经存在工号
	public List<TeacherInfo> exists (String t_id);
	//管理员端查找当前可以使用的学生
	public List<Map<String,String>> findAllocationTeacherList();
	//管理员获取当前没提交论文的教师列表
	public List<TeacherInfo> findNoPagerList();
	//管理员获取当前没有学生的教师列表
	public List<TeacherInfo> findNoStudentList();
	//管理员教师列表根据字典更新带人数量
	public void resetTmax();
}
