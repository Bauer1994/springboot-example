package com.ywq.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "schedule.print")
@Component
public class CronProperty {


//配置文件使用“,”  String []    Integer []
	private String cron;

	
	public CronProperty() {
	
	}

	public CronProperty(String cron) {
		super();
		this.cron = cron;
	}

	@Override
	public String toString() {
		return "CronProperty [cron=" + cron + "]";
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}
	
	
}
