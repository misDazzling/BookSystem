package com.situ.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.situ.demo.entity.APIResult;
import com.situ.demo.entity.User;


/**
 * IndexController是一个Controller层的类
 * 负责主页面的访问
 * 1) 在类上加@Controller注解
 * 	 (1) Spring容器, 会将该类创建出一个对象, 并且放在Spring的容器中
 * 	 (2) SpringMVC在运行时, 会扫描该类中的MVC注解
 * 2) 在类中, 创建处理器方法, 添加url路径映射
 * 		@RequestMapping
 * 		@GetMapping
 * 		@PostMapping
 * 
 * 		@DeleteMapping		RESTFul风格接口编程中会使用
 * 		@PutMapping
 * 
 * 		@OptionsMapping
 * 		@HeadMapping
 * 
 * @author bobzyh
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(HttpSession session) {
		
		
		User user = (User)session.getAttribute("user");
		if(user.getRole()==1) {
		return "index";	// 返回值就是一个视图名
		// 视图解析器在寻找视图, 
		// 前缀+视图名+后缀 = classpath:/templates/index.html
		}
		else {
			return "UserIndex";
		}
	}
	
	

}

