package com.hb.sys.tools.tlds.dict.dao;

import java.util.List;

import com.hb.sys.tools.annotation.MyBatisDao;
import com.hb.sys.tools.tlds.dict.entity.Dict;
/**
 * 字典信息管理Dao
 * @author hanbin
 * @version 2018年1月02日
 */
@MyBatisDao
public interface DictDao {
	public List<Dict> getDictList(String type);//根据类型获取字典列表
	public String getDictLabel(String value,String type);//根据字典值和类型获取字典标签
	public String getDictValue(String label,String type);//根据字典标签和类型获取字典值
}
