package com.hb.sys.manage.controlstation.schedule.service;

import org.springframework.stereotype.Service;

import com.hb.sys.manage.controlstation.schedule.dao.ScheduleDao;
import com.hb.sys.manage.controlstation.schedule.entity.Schedule;
import com.hb.sys.tools.gen.CrudService;

/**
 * 时间控制Service
 * @author hanbin
 * @date 2018年1月8日
 */
@Service
public class ScheduleService extends CrudService<ScheduleDao,Schedule>{

}
