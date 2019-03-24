package com.ywq.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywq.entity.Student;
import com.ywq.util.RedisUtil;

@Service
public class LRUServiceImpl implements LRUService {

	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public void addData2Cache(String username, Student student) {
		//TODO @ywq  需要梳理
		redisUtil.zSetAdd(username, username, System.currentTimeMillis());
	}

	@Override
	public Set<Object> getDataFromCache(String key) {
		return redisUtil.zSetRange(key, 0, 5);
	}

	@Override
	public void test4nonsynchronized() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " 运行第 " + i + "次");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
