/**
 * 
 */
package com.hb.sys.manage.baseinfo.classs.entity;

import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;

import java.io.Serializable;

/**
 * 班级信息实体	
 * @author hanbin
 * @version 2017年10月27日
 */
public class ClasssInfo implements Serializable{
	private String id;//唯一标识
	private String c_name;//班级名称
	private DepartmentInfo department;//所在院系
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public DepartmentInfo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "ClasssInfo [id=" + id + ", c_name=" + c_name + ", department=" + department + "]";
	}
	

}
