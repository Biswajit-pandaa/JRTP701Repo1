package com.nt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nt.interceptor.TimeCheckInterceptor;
@Component
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	@Autowired
	TimeCheckInterceptor interceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("MyWebMvcConfigurer.addinterceptors()");
		registry.addInterceptor(interceptor);
	}
}
