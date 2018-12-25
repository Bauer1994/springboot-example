package com.ywq.configuration;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @EnableScheduling 注解的作用是发现注解@Scheduled的任务并后台执行。
 *  Springboot本身默认的执行方式是串行执行，也就是说无论有多少task，都是一个线程串行执行，并行需手动配置
 * 并行任务：继承SchedulingConfigurer类并重写其方法即可
 * https://www.cnblogs.com/cityspace/p/6638578.html
 * @author YWQ
 *
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());

	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("schedule-task-");
		scheduler.setAwaitTerminationSeconds(60);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}
}
