/**
 * 
 */
package com.hb.sys.manage.baseinfo.classs.dao;

import com.hb.sys.manage.baseinfo.classs.entity.ClasssInfo;
import com.hb.sys.tools.annotation.MyBatisDao;
import com.hb.sys.tools.gen.CrudDao;

/**
 * 班级信息管理Dao
 * @author hanbin
 * @version 2017年10月27日
 */
@MyBatisDao
public interface ClasssInfoDao extends CrudDao<ClasssInfo>{

	public ClasssInfo findClasssByName(String name);//根据名称获取班级信息
}
