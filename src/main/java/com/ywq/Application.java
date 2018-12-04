package com.ywq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//这个注解有没有都可以    @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan("com.ywq.mapper")
@EnableScheduling  //开启定时任务
@EnableAsync //开启异步调用
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
