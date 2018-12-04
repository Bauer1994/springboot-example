package com.ywq.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ywq.common.AuthenticInterceptor;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private AuthenticInterceptor authenticInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/hello");
       
        //注册
        super.addInterceptors(registry);
    }
}
