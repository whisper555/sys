/**
 * 
 */
package com.hb.sys.manage.baseinfo.student.entity;

import com.hb.sys.manage.baseinfo.classs.entity.ClasssInfo;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.manage.baseinfo.teacher.entity.TeacherInfo;

import java.io.Serializable;

/**
 * 学生实体
 * @author hanbin
 * @version 2017年10月26日
 */
public class StudentInfo implements Serializable{
	private String id;//唯一标识
	private String s_id;//学生学号
	private String s_pw;//学生密码
	private String s_name;//学生姓名
	private String s_sex;//学生性别
	private TeacherInfo teacher;//选择的课题的指导教师
	private PagerInfo pager;//选择的论文的名称
	private ClasssInfo classs;//所在班级
	private String s_phone;//联系方式
	private PagerInfo s_wish1;//期望选择的论文1
	private PagerInfo s_wish2;//期望选择的论文2
	private PagerInfo s_wish3;//期望选择的论文3
	private String s_flag;//是否通被选择
	private String s_score;//毕业设计分数
	
	private String s_file;//论文url
	
	private String s_score_t;//指导教师分数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_pw() {
		return s_pw;
	}
	public void setS_pw(String s_pw) {
		this.s_pw = s_pw;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}	
	public String getS_sex() {
		return s_sex;
	}
	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}
	public TeacherInfo getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherInfo teacher) {
		this.teacher = teacher;
	}
	public PagerInfo getPager() {
		return pager;
	}
	public void setPager(PagerInfo pager) {
		this.pager = pager;
	}
	public ClasssInfo getClasss() {
		return classs;
	}
	public void setClasss(ClasssInfo classs) {
		this.classs = classs;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	
	
	public PagerInfo getS_wish1() {
		return s_wish1;
	}
	public void setS_wish1(PagerInfo s_wish1) {
		this.s_wish1 = s_wish1;
	}
	public PagerInfo getS_wish2() {
		return s_wish2;
	}
	public void setS_wish2(PagerInfo s_wish2) {
		this.s_wish2 = s_wish2;
	}
	public PagerInfo getS_wish3() {
		return s_wish3;
	}
	public void setS_wish3(PagerInfo s_wish3) {
		this.s_wish3 = s_wish3;
	}
	
	public String getS_score() {
		return s_score;
	}
	public void setS_score(String s_score) {
		this.s_score = s_score;
	}
	public String getS_flag() {
		return s_flag;
	}
	public void setS_flag(String s_flag) {
		this.s_flag = s_flag;
	}
	
	public String getS_file() {
		return s_file;
	}
	public void setS_file(String s_file) {
		this.s_file = s_file;
	}
	public String getS_score_t() {
		return s_score_t;
	}
	public void setS_score_t(String s_score_t) {
		this.s_score_t = s_score_t;
	}
	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", s_id=" + s_id + ", s_pw=" + s_pw + ", s_name=" + s_name + ", s_sex=" + s_sex
				+ ", teacher=" + teacher + ", pager=" + pager + ", classs=" + classs + ", s_phone=" + s_phone
				+ ", s_wish1=" + s_wish1 + ", s_wish2=" + s_wish2 + ", s_wish3=" + s_wish3 + ", s_flag=" + s_flag
				+ ", s_score=" + s_score + ", s_file=" + s_file + ", s_score_t=" + s_score_t + "]";
	}
	

}
