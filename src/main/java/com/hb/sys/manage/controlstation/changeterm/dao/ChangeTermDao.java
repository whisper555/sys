package com.hb.sys.manage.controlstation.changeterm.dao;

import com.hb.sys.tools.annotation.MyBatisDao;

/**
 * 换届管理Dao
 * @author hanbin
 * @date 2018年3月9日
 */
@MyBatisDao
public interface ChangeTermDao {

	//删除所有学生
	public void delstu();
	//删除所有论文
	public void delpager();
	//删除所有教师
	public void delteacher();
}
