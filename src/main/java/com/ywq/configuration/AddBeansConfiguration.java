package com.ywq.configuration;



import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.ywq.util.BodyReaderFilter;

/**
 * 使用JavaConfig，配置类
 * @author YWQ
 *
 */
@Configuration
public class AddBeansConfiguration {
	
	
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
    	JavaMailSenderImpl sender = new JavaMailSenderImpl();
    	sender.setHost("smtp.163.com");  //设置邮件服务器
    	sender.setUsername("***@163.com");
    	sender.setPassword("******");
    	return sender;
    }
    
    @Bean
    @ConditionalOnMissingBean(VelocityEngine.class)
    public VelocityEngineFactoryBean velocityEngineFactoryBean() {
    	VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
    	velocityEngineFactoryBean.setResourceLoaderPath("classpath:notice");
    	return velocityEngineFactoryBean;
    }
    
    @Bean
    public FilterRegistrationBean Filters() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new BodyReaderFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("koalaSignFilter");
        return registrationBean;
    }

}
