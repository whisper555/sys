/**
 * 
 */
package com.hb.sys.manage.baseinfo.pager.entity;

import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.student.entity.StudentInfo;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;

import java.io.Serializable;

/**
 * 毕设基本信息实体
 * @author hanbin
 * @version 2017年10月27日
 */
public class PagerInfo implements Serializable{
	private String id;//唯一标识
	private String t_id;//导师id
	private String p_name;//论文标题名称
	private TeacherInfo teacher;
	private StudentInfo student;
	private DepartmentInfo department;
	private String p_descripe;//题目详细描述文件
	private String p_type;//毕设类型（设计或者论文）
	private String p_max;//题目允许最大人数
	private int p_now;//毕设当前人数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}	
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}	
	public TeacherInfo getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherInfo teacher) {
		this.teacher = teacher;
	}
	public StudentInfo getStudent() {
		return student;
	}
	public void setStudent(StudentInfo student) {
		this.student = student;
	}
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	public String getP_descripe() {
		return p_descripe;
	}
	public void setP_descripe(String p_descripe) {
		this.p_descripe = p_descripe;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getP_max() {
		return p_max;
	}
	public void setP_max(String p_max) {
		this.p_max = p_max;
	}
	
	
	public int getP_now() {
		return p_now;
	}
	public void setP_now(int p_now) {
		this.p_now = p_now;
	}
	@Override
	public String toString() {
		return "PagerInfo [id=" + id + ", t_id=" + t_id + ", p_name=" + p_name + ", teacher=" + teacher + ", student="
				+ student + ", department=" + department + ", p_descripe=" + p_descripe + ", p_type=" + p_type
				+ ", p_max=" + p_max + "]";
	}
	
	
}
