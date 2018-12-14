/**
 * 
 */
package com.hb.sys.manage.baseinfo.teacher.entity;

import java.io.Serializable;
import java.util.List;

import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.manage.baseinfo.pager.entity.PagerInfo;
import com.hb.sys.tools.annotation.ExcelField;
/**
 * @author hanbin
 * @version 2017年10月23日
 */
public class TeacherInfo  implements Serializable{
	private	String id;//唯一标识
	private String t_pw;//密码
	private String t_name;//姓名
	private String t_id;//工号
	private String t_phone;//联系方式
	private String t_mail;//联系邮箱
	private String d_id;//所在院系id
	private DepartmentInfo department;//所在院系名称
	private String t_manager;//是够管理员
	private String t_max;//最大带人数量
	private String t_pro;//职称
	
	private List<PagerInfo> pagerlist;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ExcelField(title="教师密码", align=2, sort=2)
	public String getT_pw() {
		return t_pw;
	}
	public void setT_pw(String t_pw) {
		this.t_pw = t_pw;
	}
	@ExcelField(title="是否管理", align=2, sort=5)
	public String getT_manager() {
		return t_manager;
	}
	public void setT_manager(String t_manager) {
		this.t_manager = t_manager;
	}
	@ExcelField(title="最大人数", align=2, sort=6)
	public String getT_max() {
		return t_max;
	}
	public void setT_max(String t_max) {
		this.t_max = t_max;
	}
	@ExcelField(title="教师姓名", align=2, sort=1)
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	@ExcelField(title="联系方式", align=2, sort=4)
	public String getT_phone() {
		return t_phone;
	}
	public void setT_phone(String t_phone) {
		this.t_phone = t_phone;
	}
	public String getT_mail() {
		return t_mail;
	}
	public void setT_mail(String t_mail) {
		this.t_mail = t_mail;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	@ExcelField(title="职称", align=2, sort=7)
	public String getT_pro() {
		return t_pro;
	}
	public void setT_pro(String t_pro) {
		this.t_pro = t_pro;
	}
	@ExcelField(title="所在院系", align=2, sort=3)
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	
	public List<PagerInfo> getPagerlist() {
		return pagerlist;
	}
	public void setPagerlist(List<PagerInfo> pagerlist) {
		this.pagerlist = pagerlist;
	}
	@Override
	public String toString() {
		return "TeacherInfo [id=" + id + ", t_pw=" + t_pw + ", t_name=" + t_name + ", t_id=" + t_id + ", t_phone="
				+ t_phone + ", d_id=" + d_id + ", department=" + department + ", t_manager=" + t_manager + ", t_max="
				+ t_max + ", t_pro=" + t_pro + "]";
	}
	
	
}
