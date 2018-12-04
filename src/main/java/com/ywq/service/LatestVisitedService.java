package com.ywq.service;

/**
 * 使用Redis的Zset实现LRU算法
 * @author YWQ
 *
 */
public interface LatestVisitedService {

	
	void addLatestVisitedContent(String content);
	
	String getLatestVisitedContent();
}
