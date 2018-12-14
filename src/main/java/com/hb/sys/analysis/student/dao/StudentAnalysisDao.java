package com.hb.sys.analysis.student.dao;

import java.util.List;
import java.util.Map;

import com.hb.sys.tools.annotation.MyBatisDao;

/**
 * 学生统计分析Dao
 * @author hanbin
 * @date 2018年4月9日
 */
@MyBatisDao
public interface StudentAnalysisDao {

	public List<Map<String,String>> getDepartStuLineData(String did);
	public List<Map<String,String>> getClasssStuLineData(String did,String cid);
}
