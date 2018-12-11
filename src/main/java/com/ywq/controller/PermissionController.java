package com.ywq.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ywq.common.Permission;
import com.ywq.common.Role;
import com.ywq.pojo.ResourceRequest;

/* 作用：测试自定义注解与拦截器解析
 * 
 */
@RestController
public class PermissionController {


	@RequestMapping(value = "/resource/none", method = RequestMethod.GET)
	public String test4None(@RequestParam(value = "resourceId", required = false) Integer param) {
		return "RequestParam Test: " +  param ;
	}
	
	/**
	 * 拦截器的白名单URL，该请求不会被拦截
	 * @return
	 */
	@RequestMapping(value = "/resource/one", method = RequestMethod.GET)
	@Permission(Role = Role.ADMIN)
	public String test4RequestParam(@RequestParam(value = "resourceId", required = false) Integer param) {
		return "RequestParam Test: " +  param ;
	}

	
    
    @RequestMapping(value  = "/resource/{resourceId}", method = RequestMethod.GET)
	@Permission(Role = Role.ORDINARY)
    public String test4PathVariable(@PathVariable("resourceId") Integer id) {
    	
        return "PathVariable Test :" + id;
    }
    
    @RequestMapping(value = "/resource/one",
    		method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Permission(Role = Role.GUEST)
    public String test4RequestBody(
    		@RequestBody ResourceRequest params) {
    	
    	return "RequestBody Test : " + params.toString();
    }
    

   
}