package com.situ.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.spi.RegisterableService;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.APIResult;
import com.situ.demo.entity.User;
import com.situ.demo.service.UserService;
import com.situ.demo.util.VerCodeUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired		// Ioc 依赖倒转
	private UserService userService;

	
	// 用户登录的页面
	@GetMapping("/login")
	public String login() {
		return "user/login";	// templates/user/login.html
	}
	
	// 处理用户登录请求
	@PostMapping("/login")
	public String login(User user, String vercode,
			HttpSession session,
			Model model) {
		// 1. 参数
		// 2. 处理逻辑
		// 2.1 验证验证码是否正确 
		
		if ( ! vercode.toUpperCase().equals(session.getAttribute("vercode")) ) {
			model.addAttribute("error", "验证码错误!!!");
			return "user/login";
		}
		
		// 2.2 调用Service层判断
		try {
			
			
			User loginUser = userService.login(user);
			// 登录验证成功
			// 1. 保存登录信息, 
			session.setAttribute("user", loginUser);
			// 2. 跳转主页面, 重定向
		
			return "redirect:/";
			
		} catch (Exception e) {
			e.printStackTrace();
			// 登录失败
			// e.getMessage(); 就是错误信息
			model.addAttribute("error", e.getMessage());
		}
		
		return "user/login";	// 回到登录页面
	}
	
	// 验证码
	@GetMapping("/vercode")
	public void vercode(HttpServletResponse response, HttpSession session) {
		// 调用工具类生成一张验证码
		String vercode = VerCodeUtil.createVerCode(response);
		// 保存验证码
		session.setAttribute("vercode", vercode);
	}
	
	// 用户退出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 1. 获取参数
		// 2. 处理逻辑, 清除Session中保存的用户信息
		// session.removeAttribute("user");
		session.invalidate();
		
		// 3. 返回数据
		return "redirect:/user/login";
	}
	
	// 拦截器, 对用户登录进行验证, 
	// 当用户访问主页时, 先进入拦截器, 
	// 用户登录了, 拦截器放行, 
	// 用户没登录, 拦截器拦截, 跳转到登录页面
	// 1. 创建一个拦截器, 
	//  在com.situ.demo.interceptor包下, 定义一个UserInterceptor类
	// 2. 配置拦截器
	//  在com.situ.demo.config包下, 定义一个WebConfig的类
	@GetMapping("/1")
	public String m(Model model,HttpSession session)
	{
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "/user/1";
	}
	
	//用户注册
	@GetMapping("register")
	public String r() {
		
		return "user/register";
	}
	@PostMapping("/register")
	public String register(User user,Model model) {
		//1.获取参数  
		//2.调用Service层
	
		int res = userService.add(user);
		//3.返回数据
		if(res > 0) {
			
			return "/user/login"; 
		}else {
			if(res == -2) {
				
				model.addAttribute("error", "用户名重复，注册失败");
			    return "/user/register"; 
			}else {
				model.addAttribute("error", "注册失败");
			    return "/user/register"; 
			}
		}
		

	}
	
	
	
	
	/** 增删改查
	 * 
	 */
	@GetMapping("/list")
	public String list() {
		return "user/list";
	}
	@PostMapping("/list")
	@ResponseBody
	public APIResult list(int page,int limit) {
		//分页查询
		PageInfo pageInfo = userService.getByPage(page,limit);
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
		
	}
	
	@GetMapping("/add")
	public String add() {
		return "user/add";
	}
	@PostMapping("/add")
	@ResponseBody
	public APIResult add(User user)
	{
		//1.获取参数  
		//2.调用Service层
		int res = userService.add(user);
		//3.返回数据
		if(res > 0) {
			return APIResult.success(null);
		}else {
			if(res == -2) {
				return APIResult.error("用户名重复，添加失败");
			}else {
				return APIResult.error("添加失败");
			}
		}

	}
	/**
	 * 删除操作
	 * 由于这个操作是通过ajax向服务器发送的，需要返回json格式
	 */
	@PostMapping("/delete")
	@ResponseBody
	public APIResult delete(int id) {
		//1.参数
		//2.调用Service
	int res = userService.delete(id);
	//3.返回结果
	if(res > 0) {
		return APIResult.success(null);
	}else {
		return APIResult.error("删除失败");
		
	}
	
	}
	
	/**
	 * 编辑的操作
	 */
	@PostMapping("/edit")
	@ResponseBody
	public APIResult edit(User user) {
		//1.参数
		//2.调用Service层
		int res = userService.edit(user);
		//3.返回数据
		if(res > 0) {
			return APIResult.success(null);
		}else {
			return APIResult.error("修改失败");
		}
		
	}
	/** 权限管理的页面
	 * 
	 */
	@GetMapping("/rolemanage")
	public String role() {
		return "user/rolemanage";
	}
	
	@PostMapping("/roleedit")
	@ResponseBody
	public APIResult roleedit(User user) {
		//1.参数
		//2.调用Service层
		int res = userService.roleedit(user);
		//3.返回数据
		if(res > 0) {
			return APIResult.success(null);
		}else {
			return APIResult.error("修改失败");
		}
		
	}
	//用户资料的页面
	
	@GetMapping("/userdata")
	public String userdata() {
		return "user/userdata";
	}
	@PostMapping("/userdata")
	@ResponseBody
	public APIResult userdata(int page,int limit,HttpSession session) {
		//分页查询
		User user = (User) session.getAttribute("user");//获取当前用户信息
		
		PageInfo pageInfo = userService.getByPage(page,limit,user.getUsername());
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(pageInfo);
		return result;
		
	}
}




