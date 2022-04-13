package com.situ.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.situ.demo.interceptor.UserIntercepor;

@Configuration	// 将以配置的方式交给Spring
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private UserIntercepor userIntercepor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userIntercepor)
			.addPathPatterns("/**")		// 拦截所有路径
			.excludePathPatterns("/user/login", "/user/vercode",
					"/layui/**", "/images/**","/user/register");	// 放行的路径

	}
}
