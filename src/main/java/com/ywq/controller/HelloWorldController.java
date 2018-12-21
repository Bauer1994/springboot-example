package com.ywq.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ywq.entity.Student;
import com.ywq.pojo.StudentResponse;
import com.ywq.service.AsynService;
import com.ywq.service.LRUService;
import com.ywq.service.StudentService;

/* 作用：url+HTTP类型 映射到方法，并将所有的逻辑处理返回
 * 入门：包含注解、mvc、json、
 * 1、注解：@restcontroller @autowired @requestmapping @getMapping @PathVariable @requestParam @requestbody
 * 2、MVC模式，单一职责。为什么要定义response之类的pojo
 * 3、使用json，当然也可以整合Velocity、thymeleaf模板
 * 4、
 */
@RestController
public class HelloWorldController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private AsynService asyncService;
	
	@Autowired
	private LRUService lRUService;
	
	@Autowired
	private Student s1;//必须使用s1,跟@Bean的名称保持一致才知道注入的是哪一个
	
	@Autowired
	@Qualifier("s2")
	private Student student2;
	
	@Resource(name = "s2")
	private Student student3;        //student2 ==student3   true

	/**
	 * 拦截器的白名单URL，该请求不会被拦截
	 * @return
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String index(@RequestParam(value = "param", required = false) String param) {
		return "Hello World" +  param ;
	}


    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public StudentResponse getById(@PathVariable("id") Integer id) {
        return new StudentResponse(studentService.getById(id));
    }
    
    @RequestMapping(value  = "/aop/{id}", method = RequestMethod.GET)
    public String test4AOP(@PathVariable("id") Integer id) {
    	System.out.println("看到" + id + "，就看下console有没有AOP相关打印。有你就成功啦!");
        return "看到" + id + "，就看下console有没有AOP相关打印,有?你就成功啦!";
    }
    
    @RequestMapping(value = "/test/cache", method = RequestMethod.GET)
    public void testCache() {
       System.out.println("如果第二次没看到，就成功了");
    }
    
    
    @RequestMapping(value = "/async/{time}", method = RequestMethod.GET)
    public void testAsync(@PathVariable("time") Integer time) {
           	asyncService.count(time);
           	System.out.println("你会先看到我，在看到异步任务执行结果！");  //已使用异步任务
    }
    
    @RequestMapping(value = "/async/exception", method = RequestMethod.GET)
    public void testAsync4Exception() {
           	asyncService.testException();
           
    }
    
    @RequestMapping(value = "/redis/lru/{key}", method = RequestMethod.GET,   produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Object> testZset(@PathVariable("key") String key) {
    	lRUService.addData2Cache(key, new Student(1,"ywq", 2 ,3));
        return lRUService.getDataFromCache(key);   
    }
    
    
    //测试 autowired resource 
    @RequestMapping(value = "/annotation/all", method = RequestMethod.GET,   produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject test4Annotation() {
    	JSONObject result = new JSONObject();
    	result.fluentPut("student1", s1).fluentPut("same", s1 == student2).fluentPut("same2", student3 == student2);
        return result;   
    }
    
    
    
   
}