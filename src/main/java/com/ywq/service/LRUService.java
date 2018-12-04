package com.ywq.service;

import java.util.Set;

import com.ywq.entity.Student;

public interface LRUService {

	void addData2Cache(String username, Student info);
	
	Set<Object> getDataFromCache(String key);
}
