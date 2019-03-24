package com.ywq.controller;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ywq.service.LRUService;

/**
 * 测试并发请求
 * @author YWQ
 *
 */
@RestController
@RequestMapping("/concurrent")
public class ConcurrentController {

	private AtomicInteger atomicInteger = new AtomicInteger(0);
	
	private ReentrantLock lock = new ReentrantLock();
	
	@Autowired
	private LRUService lRUService;
	
	//上面的测试不准确，原因在于浏览器对请求的处理是单线程的，想要测试该场景需要使用两台机器模拟
	@RequestMapping("/browser/test1")
	public String test4BrowserConcurrency1() {
		System.out.println("start: "+System.currentTimeMillis());
		test4nonsynchronized();
		//lRUService.test4nonsynchronized();
		System.out.println("end: " + System.currentTimeMillis());
  
		return "test4BrowserConcurrency";
	}
	
	@RequestMapping("/browser/test2")
	public String test4BrowserConcurrency2() {
		test4synchronized();
		return "test4BrowserConcurrency";
	}
	
	/**
	 * 同一个请求有并发处理，需要自己保证安全性
	 * @return
	 */
	@RequestMapping("/controller")
	public String test4ControllerConcurrency() {
		new Thread(() -> {
			try {
				for (int i = 0; i < 4; i++) {
					System.out.println(Thread.currentThread().getName() + " 运行第 " + atomicInteger.getAndIncrement() + "次");
				    Thread.sleep(1000);	
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		new Thread(() -> {
			try {
				for (int i = 0; i < 4; i++) {
					System.out.println(Thread.currentThread().getName() + " 运行第 " + atomicInteger.getAndIncrement() + "次");
					 Thread.sleep(1000);		
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		return "test4ControllerConcurrency";
	}
	
	private void test4nonsynchronized() {
		try {
			for (int i = 0; i < 4; i++) {
				System.out.println(Thread.currentThread().getName() + " 运行第 " + i + "次");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void test4synchronized() {
		lock.lock();
		try {
			for (int i = 0; i < 4; i++) {
				System.out.println(Thread.currentThread().getName() + " 运行第 " + i + "次");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
