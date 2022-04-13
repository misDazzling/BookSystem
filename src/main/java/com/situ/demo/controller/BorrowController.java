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
import com.situ.demo.entity.Type;
import com.situ.demo.entity.User;
import com.situ.demo.service.BookService;
import com.situ.demo.service.BorrowService;
import com.situ.demo.service.TypeService;

import net.sf.jsqlparser.util.AddAliasesVisitor;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
	 /**
	    * 返回图书列表的页面
	    */
	  @Autowired
	  private TypeService typeService;
	  @Autowired
	  private BookService bookService;
	   @GetMapping("/list")
	   public String list(Model model,HttpSession session) {
		  
		//1.先准备好数据，进入到添加页面后，使用thymeleaf模板语言动态生成。
		   //TypeService TypeMapper Type
		   List<Type> types = typeService.getAll();
		   User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
		   model.addAttribute("types",types);
		   
		   return "borrow/list";
	   }
	   @Autowired
	   private BorrowService borrowService;
	  
	   @PostMapping("/add")
		@ResponseBody
		public APIResult add(Borrow borrow) {
			//1.获取参数
			//2.调用service层
			int res = borrowService.add(borrow);
			//3.返回数据
			if(res > 0) {
				return APIResult.success(null);
			}else {
				return APIResult.error("添加失败");
			}
			
		}
	   @GetMapping("/add")
		@ResponseBody
		public APIResult add2(Borrow borrow) {
			//1.获取参数
			//2.调用service层
			int res = borrowService.add(borrow);
			//3.返回数据
			if(res > 0) {
				return APIResult.success(null);
			}else {
				return APIResult.error("添加失败");
			}
			
		}
	   @GetMapping("/userlist")
	   public String userlist(Model model,HttpSession session) {
		  
		//1.先准备好数据，进入到添加页面后，使用thymeleaf模板语言动态生成。
		   //TypeService TypeMapper Type
		  
		   User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);

		   
		   return "borrow/userlist";
	   }

	   @PostMapping("/userlist")
		@ResponseBody //返回JSON
		public APIResult userlist(int page, int limit,HttpSession session,String key) {
			//分页查询
			//Service层，Mapper层，entity层
		   
		   User user = (User) session.getAttribute("user"); //取出当前用户的信息
			PageInfo pageInfo = borrowService.getByPage(page, limit, user.getId(),key);
		
			APIResult result = new APIResult();
			result.setCode(0);
			result.setData(pageInfo);
			return result;
		}
	   @GetMapping("/adminlist")
	   public String adminlist(Model model,HttpSession session) {
		  
		//1.先准备好数据，进入到添加页面后，使用thymeleaf模板语言动态生成。
		   //TypeService TypeMapper Type
		  
		   User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);

		   
		   return "borrow/adminlist";
	   }

	   @PostMapping("/adminlist")
		@ResponseBody //返回JSON
		public APIResult adminlist(int page, int limit,String key ){
			//分页查询
			//Service层，Mapper层，entity层
		   
		 
			PageInfo pageInfo = borrowService.getByPage(page, limit ,key);
		
			APIResult result = new APIResult();
			result.setCode(0);
			result.setData(pageInfo);
			return result;
		}

	   @PostMapping("/edit")
		@ResponseBody
		public APIResult edit(Borrow borrow) {
			//1.获取参数
			//2.调用service层
			int res = borrowService.edit(borrow);
			//3.返回数据
			if(res > 0) {
				return APIResult.success(null);
			}else {
				return APIResult.error("添加失败");
			}
			
		}
	  
	  
}