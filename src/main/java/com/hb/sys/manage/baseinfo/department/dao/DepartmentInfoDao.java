package com.hb.sys.manage.baseinfo.department.dao;

import com.hb.sys.manage.baseinfo.department.entity.DepartmentInfo;
import com.hb.sys.tools.annotation.MyBatisDao;
import com.hb.sys.tools.gen.CrudDao;

/**
 * 院系基本信息管理Dao接口
 * @author hanbin
 * @version 2018年1月02日
 */
@MyBatisDao
public interface DepartmentInfoDao extends CrudDao<DepartmentInfo>{

	public DepartmentInfo findDepartmentByName(String name);
}
