package com.hb.sys.login.entity;

/**
 * 用户登录实体
 * @author hanbin
 *
 */
public class User {

	private String id;//用户id
	private String pw;//用户密码
	private String name;//用户姓名
	private String user;//用户类型
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", user=" + user + "]";
	}
	
	
}
