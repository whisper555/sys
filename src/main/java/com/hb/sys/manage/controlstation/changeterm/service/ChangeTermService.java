package com.hb.sys.manage.controlstation.changeterm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.sys.manage.controlstation.changeterm.dao.ChangeTermDao;

/**
 * 换届管理service
 * @author hanbin
 * @date 2018年3月9日
 */
@Service
public class ChangeTermService {
	@Autowired
	private ChangeTermDao changeTermDao;
	
	public void delstu(){
		changeTermDao.delstu();
	}
	public void delpager(){
		changeTermDao.delpager();
	}

	public void delteacher() {
		changeTermDao.delteacher();
	}

}
