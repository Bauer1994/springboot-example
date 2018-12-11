package com.ywq.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ywq.util.BodyReaderHttpServletRequestWrapper;

/**
 * 拦截器实现自定义注解的解析，进行权限控制:https://blog.csdn.net/huakainashi20160705/article/details/79451074
 * http://www.cnblogs.com/nickhan/p/9849693.html
 * https://stackoverflow.com/questions/35549040/failed-to-read-http-message-org-springframework-http-converter-httpmessagenotre
 * 
 * @author YWQ
 *
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	
	private static final String RESOURCE_IDENTIFY = "resourceId";

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object handler) throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;// 没有实例化的方法直接通过
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		// 判断接口是否需要认证
		Permission methodAnnotation = method.getAnnotation(Permission.class);
		// 有 @Permission注解，需要认证
		String resourceId = null;
		if (methodAnnotation != null) {

			logger.info("Role is :{}", methodAnnotation.Role() );
			Map pathVars = (Map) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			if (Objects.nonNull(pathVars) && pathVars.containsKey(RESOURCE_IDENTIFY)) {
				resourceId = (String) (pathVars.get(RESOURCE_IDENTIFY));
				logger.info("get resource from pathVariable: {}", resourceId);
			}

			if (Objects.isNull(resourceId)) {
				// requestParam
				Map<String, String[]> pramMap = httpServletRequest.getParameterMap();
				if (Objects.nonNull(pramMap) && pramMap.containsKey(RESOURCE_IDENTIFY)) {
					resourceId = pramMap.get(RESOURCE_IDENTIFY)[0];
					logger.info("get resource from requestParam: {}", resourceId);
				}
				
			}

			
			//应该可以直接从request中获取到请求的类型，姑且先这样实现！
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

			if (Objects.isNull(resourceId) && (requestMapping.method()[0].equals(RequestMethod.POST)
					|| requestMapping.method()[0].equals(RequestMethod.PUT))) {

				Map<String, Object> bodyData = getParamsFromRequestBody(httpServletRequest);
				resourceId = Objects.nonNull(bodyData) && bodyData.containsKey(RESOURCE_IDENTIFY)
						? (String) bodyData.get(RESOURCE_IDENTIFY) : null;
				logger.info("get resource from requestBody: {}", resourceId);
			}
		} else {
			logger.info("Role is: {}",  "没有添加注解");
		}

		if (Objects.nonNull(resourceId)) {
			// 校验
			if (StringUtils.equals(resourceId, "999"))
				return false;
			else {
				return true;
			}
		}

		return true;
	}

	/**
	 * 获取请求体内容
	 * 
	 * @return
	 * @throws IOException
	 */
	private Map<String, Object> getParamsFromRequestBody(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		ObjectMapper objectMapper = new ObjectMapper();

		StringBuilder builder = new StringBuilder();
		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			String bodyString = builder.toString();
			return objectMapper.readValue(bodyString, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new HashMap<>();
	}
}
