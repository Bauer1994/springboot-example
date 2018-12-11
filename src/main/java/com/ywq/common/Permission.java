package com.ywq.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//设置是运行时注解 
@Target({ElementType.METHOD})//设置注解类型是方法类型注解 
public @interface Permission {
	
	public Role Role() ; 
	//public Role Role() default Role.ADMIN; 


}
