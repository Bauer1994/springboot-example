package com.ywq.configuration;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * https://blog.csdn.net/hry2015/article/details/67640534
 * 实现AsyncConfigurer接口对异常线程池更加细粒度的控制
 *  a) 创建线程自己的线程池 (当然也可以使用new ThreadPoolExecutor来。)
 *  b)对void方法抛出的异常处理的类AsyncUncaughtExceptionHandler
 * 
 * @author YWQ
 *
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

	private static final Logger log = LoggerFactory.getLogger(AsyncConfig.class);
	
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
		threadPool.setCorePoolSize(5);
		threadPool.setMaxPoolSize(10);
		threadPool.setWaitForTasksToCompleteOnShutdown(true);
		threadPool.setAwaitTerminationSeconds(60 * 15);
		threadPool.setThreadNamePrefix("MyAsync-");
		threadPool.initialize();

		return threadPool;
	}

	/**
	 * 异步任务异常统一处理
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

		return new AsyncUncaughtExceptionHandler (){
			 @Override  
		        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {  
//		            log.info("Exception message - " + throwable.getMessage());  
//		            log.info("Method name - " + method.getName());  
//		            for (Object param : obj) {  
//		                log.info("Parameter value - " + param);  
//		            }  
				 
				    log.error("Exception occurs in async method", throwable.getMessage());
		        }  

	
		};
	}

}
