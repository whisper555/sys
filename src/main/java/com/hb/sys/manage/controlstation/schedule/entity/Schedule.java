package com.hb.sys.manage.controlstation.schedule.entity;

import java.util.Date;

/**
 * 时间控制实体
 * @author hanbin
 * @date 2018年1月8日
 */
public class Schedule {
	private String id;
	private Date starttime;//开始时间
	private Date endtime;//结束时间
	private String period;//阶段
	private String starttimestr;
	private String endtimestr;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getStarttimestr() {
		return starttimestr;
	}
	public void setStarttimestr(String starttimestr) {
		this.starttimestr = starttimestr;
	}
	public String getEndtimestr() {
		return endtimestr;
	}
	public void setEndtimestr(String endtimestr) {
		this.endtimestr = endtimestr;
	}
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", starttime=" + starttime + ", endtime=" + endtime + ", period=" + period
				+ ", starttimestr=" + starttimestr + ", endtimestr=" + endtimestr + "]";
	}
	
	

}
