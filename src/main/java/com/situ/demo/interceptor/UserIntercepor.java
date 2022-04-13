package com.situ.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.situ.demo.entity.User;

// 这是一个拦截器
@Component	// 组件 将类的对象交给Spring管理
public class UserIntercepor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler)
			throws Exception {
		// 在处理器方法 执行之前进行拦截操作, 
		// 获取当前登录用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			// 用户没有登录
			// 跳转到登录页面
			response.sendRedirect("/user/login");
			// 拦截
			return false;
		}
		
		return true;	// 放行, 
	}
}
