package com.ywq.configuration;



import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.ywq.entity.Student;
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

    /*
     * 1、如果两个方案都返回@Bean但不指定名字，@autowired就会报错，因为是按类型注入的。单例
     * 2、如果两个方法都返回@Bean且指定名字但想要使用@autowired，那么可以使用@bean的名字做变量名或者搭配@Qualifier使用
     * 3、可以直接使用@resource
     */
    @Bean("s1")
    public Student getStudent1() {
    	return new Student(1, "ywq", 12, 123);
    }
    

    @Bean("s2")
    public Student getStudent2() {
    	return new Student(2, "qwy", 21, 321);
    }
    
    
}
