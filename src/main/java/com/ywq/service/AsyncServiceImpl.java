package com.ywq.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsynService {

	
	private static final Logger logger =  LoggerFactory.getLogger(AsyncServiceImpl.class);
	
	/**
	 * 注意打印次序 以及执行程序的线程名称
	 */
	@Override
	@Async
	public void count(int time) {
		
		int result = 0;
		for(int i =0; i < 10000; i++) {
			result++;
		}
		
		try {
			Thread.sleep(time* 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		logger.info("async task result :" + result);
	}

	@Override
	@Async
	public void testException() {
		//程序产生异常，测试是否会捕捉!
		Integer.parseInt("abc");
		
	}

	
}
