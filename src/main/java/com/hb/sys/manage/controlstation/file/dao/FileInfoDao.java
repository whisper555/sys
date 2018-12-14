package com.hb.sys.manage.controlstation.file.dao;

import com.hb.sys.manage.controlstation.file.entity.FileInfo;
import com.hb.sys.tools.annotation.MyBatisDao;
import com.hb.sys.tools.gen.CrudDao;

/**
 * 
 * @author hanbin
 * @date 2018年3月8日
 */
@MyBatisDao
public interface FileInfoDao extends CrudDao<FileInfo>{

	public void deleteAll();
}
