package com.ywq.service;

/**
 * spring对@Transactional注解时也有类似问题，
 * spring扫描时具有@Transactional注解方法的类时，
 * 是生成一个代理类，由代理类去开启关闭事务，而在同一个类中，
 * 方法调用是在类体内执行的，spring无法截获这个方法调用。
 * 
 * @author YWQ
 *
 */
public interface AsynService {

	void count(int time);
	
	void testException();
}
