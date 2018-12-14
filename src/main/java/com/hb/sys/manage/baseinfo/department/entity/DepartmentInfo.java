/**
 * 
 */
package com.hb.sys.manage.baseinfo.department.entity;

import java.io.Serializable;

/**
 * 院系信息实体
 * @author hanbin
 * @version 2018年1月02日
 */
public class DepartmentInfo implements Serializable{
	private String id;//院系id
	private String d_name;//院系名称
	private String f_name;//所在院
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	@Override
	public String toString() {
		return "DepartmentInfo [id=" + id + ", d_name=" + d_name + ", f_name=" + f_name + "]";
	}
	
	
}
