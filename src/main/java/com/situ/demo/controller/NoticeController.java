package com.situ.demo.controller;

import java.util.List;

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
import com.situ.demo.entity.Borrow;
import com.situ.demo.entity.Notice;
import com.situ.demo.entity.Type;
import com.situ.demo.entity.User;
import com.situ.demo.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	 @Autowired
	 private NoticeService noticeService;
	 @GetMapping("/list")
	   public String list() {
		  
		
		   
		   return "notice/list";
	   }
	 @PostMapping("/list")
	   @ResponseBody
	   public APIResult list(int page ,int limit) {
		   //1.获取参数
		   //2.处理业务调用Service
		   PageInfo pageInfo =noticeService.getByPage(page,limit);
		   //3.返回数据
		
		   return APIResult.success(pageInfo);
	   }
	 @PostMapping("/edit")
		@ResponseBody
		public APIResult edit(Notice notice) {
			//1.参数
			//2.调用Service层
			int res = noticeService.edit(notice);
			//3.返回数据
			if(res > 0) {
				return APIResult.success(null);
			}else {
				return APIResult.error("修改失败");
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
		int res = noticeService.delete(id);
		//3.返回结果
		if(res > 0) {
			return APIResult.success(null);
		}else {
			return APIResult.error("删除失败");
			
		}
		
		}
		/**
		 * 添加操作
		 */
		
		@GetMapping("/add")
		public String add(Model model,HttpSession session) {
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
			return "notice/add";
		}
		@PostMapping("/add")
		@ResponseBody
		public APIResult add(Notice notice)
		{
			//1.获取参数  
			//2.调用Service层
			int res = noticeService.add(notice);
			//3.返回数据
			if(res > 0) {
				return APIResult.success(null);
			}else {
				
					return APIResult.error("添加失败");
				}
			

		}
		//用户查看公告
		@GetMapping("/userlist")
		public String userlist() {
			return "notice/userlist";
		}
}
