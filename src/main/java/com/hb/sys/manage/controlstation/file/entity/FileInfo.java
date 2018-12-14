package com.hb.sys.manage.controlstation.file.entity;

/**
 * 文件管理的entity
 * @author hanbin
 * @date 2018年3月8日
 */

public class FileInfo {
	private String id;
	private String file_url;
	private String user_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
