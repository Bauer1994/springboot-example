package com.ywq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ywq.service.MailSenderService;

/**
 * 
 * @author YWQ
 *
 */
@RestController
public class MailSenderController {


	@Autowired
	private MailSenderService mailSenderService;

	@RequestMapping("/hello-world")
	public String index() {
		mailSenderService.sender();
		return "success";
	}
	
	@RequestMapping("/send-template")
	public String send() {
		mailSenderService.send4Template();
		return "send by template";
	}
}
