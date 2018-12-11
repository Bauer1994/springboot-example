package com.ywq.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ywq.common.AuthenticInterceptor;
import com.ywq.common.PermissionInterceptor;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private AuthenticInterceptor authenticInterceptor;
	@Autowired
	private PermissionInterceptor permissionInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/hello");
       
        registry.addInterceptor(permissionInterceptor)
        .addPathPatterns("/**").excludePathPatterns("/hello");
        //注册
        super.addInterceptors(registry);
       

    }
}
