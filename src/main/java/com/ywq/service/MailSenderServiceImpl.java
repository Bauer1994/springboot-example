package com.ywq.service;


import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
/**
 * 邮件发送逻辑处理，包括：直接发送和使用Velocity模板两种
 * @author YWQ
 *
 */
@Service
public class MailSenderServiceImpl implements MailSenderService{
	
	@Autowired
	private JavaMailSenderImpl sender;
	@Autowired
	private VelocityEngine velocityEngine;

	@Override
	public void sender() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("****@163.com");
		message.setTo("****@@163.com");
		message.setSubject("test");
		message.setText("test");
		sender.send(message);
		System.out.println("使用直接发送方式发送消息成功！");
	}

	@Override
	public void sender(String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("****@163.com");
		message.setTo("****@163.com");
		message.setSubject("test");
		message.setText(content);
		sender.send(message);
	}
	
	/**
	 * 使用消息模板
	 */
	@Override
	public void send4Template() {
	
	 String content = VelocityEngineUtils.mergeTemplateIntoString(
			 velocityEngine, "welcome.ftl", "utf-8", getTemplateData());
	 sender(content);
	 System.out.println("使用模板渲染方式发送消息成功！");
	}
	
	/**
	 * 准备用户数据
	 * @return
	 */
	private Map<String, Object> getTemplateData() {
		Map<String, Object> vmModel = new HashMap<String, Object>();
		vmModel.put("userName", "雷猴");
		vmModel.put("dateTime", new Date());
		
		return vmModel;
	}

	@Override
	public void send4TemplateContext() {
		//模板内容
		String  templateContent = "";
		//获得velocity的上下文
		VelocityContext velocityContext = new VelocityContext(getTemplateData());
	    StringWriter stringwriter = new StringWriter();
		velocityEngine.evaluate(velocityContext, stringwriter, "", templateContent);
		String content = stringwriter.toString();
		
		sender(content);
		
	}
	
}
