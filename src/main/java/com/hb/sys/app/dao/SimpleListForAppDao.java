package com.hb.sys.app.dao;

import java.util.List;

import com.hb.sys.app.entity.SimpleAdapterBean;
import com.hb.sys.tools.annotation.MyBatisDao;

/**
 * 获取简单列表
 * @author hanbin
 * @date 2018年4月27日
 */
@MyBatisDao
public interface SimpleListForAppDao {

	public List<SimpleAdapterBean> getDepartList();
	public List<SimpleAdapterBean> getClasssList();
	public List<SimpleAdapterBean> getTeacherList();
	
	//获取字典列表
	public List<SimpleAdapterBean> getDictList(String type);
}
