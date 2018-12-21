package com.ywq.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ywq.common.exception.BadRequestException;
import com.ywq.common.exception.ConflictException;
import com.ywq.configuration.CronProperty;

@RestController
public class ExceptionController {

	@Autowired
	private CronProperty cronProperty;

	@RequestMapping(value = "/exception/bad/{id}", method = RequestMethod.GET)
	public void testBadRequest(@PathVariable("id") String id) {
		if (!NumberUtils.isDigits(id)) {
			throw new BadRequestException(id, "参数错误");
		} else {
			System.out.println("正常访问！");
		}
	}

	@RequestMapping(value = "/exception/conflict", method = RequestMethod.GET)
	public void testConflict() {
		if (true) {
			throw new ConflictException("resource", "资源冲突");
		}
	}

	//测试配置文件通过前缀Bean导入
	@RequestMapping(value = "/property", method = RequestMethod.GET)
	public CronProperty testProperty() {

		return cronProperty;
	}
}
