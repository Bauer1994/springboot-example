package com.ywq.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 多个拦截器的注册顺序决定了生效的次序
 * 三个方法孰先孰后?---spring拦截器链
 * @author YWQ
 *
 */
@Component
public class AuthenticInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		//TODO: 解析参数：可以从get post put 等场景下的参数中获取
		System.out.println("拦截成功");
		String token = request.getHeader("header");
		/**
		 * 补充校验逻辑：可以从配置、数据库、本地缓存、分布式缓存中获取信息
		 * 思路：ConcurrentHashMap、guava、Redis
		 */
		return true;
	}

}
