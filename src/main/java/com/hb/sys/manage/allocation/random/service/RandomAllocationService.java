package com.hb.sys.manage.allocation.random.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.sys.manage.allocation.random.dao.RandomAllocationDao;

/**
 * 管理员 分配毕业设计
 * @author hanbin
 * @date 2018年3月15日
 */
@Service
public class RandomAllocationService {

	@Autowired
	private RandomAllocationDao randomAllocationDao;
	
	
	public void randomAllocationTeacher(){
		int num = randomAllocationDao.findAllocationStudent();
		for(int i =0;i<num;i++){ 
			randomAllocationDao.randomAllocationTeacher();
		}
		
	}
	public void randomAllocationPager(){
		int num = randomAllocationDao.findAllocationStudent();
		for(int i =0;i<num;i++){
		randomAllocationDao.randomAllocationPager();
		}
	}
}
