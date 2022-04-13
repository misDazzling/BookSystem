package com.situ.demo.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.APIResult;
import com.situ.demo.entity.Book;
import com.situ.demo.entity.Type;
import com.situ.demo.entity.User;
import com.situ.demo.service.TypeService;




@Controller
@RequestMapping("/type")
public class TypeController {
	 
	 
	 @GetMapping("/list")
		public String list(Model model){
		 //1.先准备好数据，进入到添加页面后，使用thymeleaf模板语言动态生成。
		   //TypeService TypeMapper Type
		 
			return "type/list";
		}
		
	 @Autowired
		private TypeService typeService;
		@PostMapping("/list")
		@ResponseBody //返回JSON
		public APIResult list(int page, int limit) {
			//分页查询
			//Service层，Mapper层，entity层
			PageInfo pageInfo = typeService.getByPage(page, limit);
			APIResult result = new APIResult();
			result.setCode(0);
			result.setData(pageInfo);
			return result;
		}
		@PostMapping("/edit")
		@ResponseBody
		public APIResult edit(Type type) {
			// 还需要调用Service层
			int res = typeService.edit(type);
			if(res > 0) {
			return APIResult.success(null);
			}
			else return APIResult.error("请求错误");
		}
		@PostMapping("/delete")
		@ResponseBody		// 返回JSON数据
		public APIResult delete(int id) {
			// 1. 参数
			// 2. 调用Service
			int res = typeService.delete(id);
			
			// 3. 返回
			if (res > 0 ) {
				return APIResult.success(null);
			} else {
				return APIResult.error("删除失败");
			}
		}
		/**
		 * 添加图书
		 * @return
		 */
		@GetMapping("/add")
		   public String add() {
			  
			   return "type/add";
		   }
		@PostMapping("/add")
		@ResponseBody
		public APIResult add(Type type) {
			
			//1.获取参数
			//2.调用service层
			int res = typeService.add(type);
			//3.返回数据
			if(res > 0) {
				return APIResult.success(null);
			}else {
				return APIResult.error("添加失败");
			}
			
		}
		
}
		
		
