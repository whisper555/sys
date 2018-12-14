package com.hb.sys.manage.controlstation.file.service;

import org.springframework.stereotype.Service;

import com.hb.sys.manage.controlstation.file.dao.FileInfoDao;
import com.hb.sys.manage.controlstation.file.entity.FileInfo;
import com.hb.sys.tools.gen.CrudService;

/**
 * 文件管理SErvcice
 * @author hanbin
 * @date 2018年3月8日
 */
@Service
public class FileInfoService extends CrudService<FileInfoDao,FileInfo>{

	public void deleteAll(){
		dao.deleteAll();
	}
}
