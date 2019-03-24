package com.ywq.service;

import java.util.Set;

import com.ywq.entity.Student;

public interface LRUService {

	void addData2Cache(String username, Student info);
	
	Set<Object> getDataFromCache(String key);
	
	/**
	 * 测试在Service中的并发情况
	 */
	void test4nonsynchronized();
}
