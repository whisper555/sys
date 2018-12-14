package com.hb.sys.manage.allocation.random.dao;

import com.hb.sys.tools.annotation.MyBatisDao;

/**
 * 管理员随机分配
 * @author hanbin
 * @date 2018年3月15日
 */
@MyBatisDao
public interface RandomAllocationDao {

	public void randomAllocationTeacher();
	public void randomAllocationPager();
	public int findAllocationStudent();
}
