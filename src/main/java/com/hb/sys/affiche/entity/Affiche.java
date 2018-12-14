/**
 * 
 */
package com.hb.sys.affiche.entity;

import java.util.Date;

/**
 * 公告实体
 * @author hanbin
 * @version 2018年04月09日
 */
public class Affiche {

	private String id;//唯一标识
	private String a_title;//公告标题	
	private String a_text;//公告内容
	private Date a_date;//公告日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getA_title() {
		return a_title;
	}
	public void setA_title(String a_title) {
		this.a_title = a_title;
	}
	public String getA_text() {
		return a_text;
	}
	public void setA_text(String a_text) {
		this.a_text = a_text;
	}
	public Date getA_date() {
		return a_date;
	}
	public void setA_date(Date a_date) {
		this.a_date = a_date;
	}

}
