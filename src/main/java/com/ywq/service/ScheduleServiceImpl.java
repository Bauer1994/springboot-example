package com.ywq.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务+cron表达式
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Value("${schedule.print.cron}")
	private String scheduleTime;
	
	@Override
	@Scheduled(cron = "0 */3 * * * ?" )
	//@Scheduled(fixedRate = 6000)
	public void schedule4Print() {
		System.out.println("通过@value注解获取到的数据： "+scheduleTime);
		System.out.println("这是定时任务：" + DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

	}

}
