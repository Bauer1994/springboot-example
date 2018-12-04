package com.ywq.service;

public interface MailSenderService {

	 void sender();
	 void sender(String content);
	 void send4Template();
	 void send4TemplateContext();
}
