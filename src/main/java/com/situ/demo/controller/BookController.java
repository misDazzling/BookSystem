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
import com.situ.demo.service.BookService;

import com.situ.demo.service.TypeService;

@Controller
@RequestMapping("/book")
public class BookController {
   @Autowired
   private TypeService typeService;
   @Autowired
   private BookService bookService;
   @GetMapping("/add")
   public String add(Model model) {
	   //1.先准备好数据，进入到添加页面后，使用thymeleaf模板语言动态生成。
	   //TypeService TypeMapper Type
	   List types = typeService.getAll();
	   model.addAttribute("types",types);
	   
	   return "book/add";
   }
   @PostMapping("/getByType")
   @ResponseBody
   public APIResult getByType(int type_id) {
	   //根据分类获取图书
	   //BookService ,BookMapper ,Book
	   List<Book> books = bookService.getByTpye(type_id);
	   return APIResult.success(books);
	  
   }
   /**
    * 图书列表
    * 1.页面BookController中顶一个get类型的方法，list,返回一个模板book/list.html
    * 2.json数据 BookController中，定义一个分页查询的方法，返回查询到的所有图书信息
    * 
    */
   
   /**
    * 返回图书列表的页面
    */
   @GetMapping("/list")
   public String list(Model model) {
	   //1.先准备好数据，进入到添加页面后，使用thymeleaf模板语言动态生成。
	   //TypeService TypeMapper Type
	   List<Type> types = typeService.getAll();
	   model.addAttribute("types",types);
	   
	   return "book/list";
   }
   /**
    * 获取图书 列表数据
    */
   @PostMapping("/list")
   @ResponseBody
   public APIResult list(int page ,int limit,String key,Integer type_id) {
	   //1.获取参数
	   //2.处理业务调用Service
	   PageInfo pageInfo =bookService.getByPage(page,limit,key,type_id);
	   //3.返回数据
	   
	   return APIResult.success(pageInfo);
   }
   /**
	 * 删除操作
	 * 由于这个操作是通过ajax向服务器发送的, 需要返回json格式
	 */
	@PostMapping("/delete")
	@ResponseBody		// 返回JSON数据
	public APIResult delete(int id) {
		// 1. 参数
		// 2. 调用Service
		int res = bookService.delete(id);
		
		// 3. 返回
		if (res > 0 ) {
			return APIResult.success(null);
		} else {
			return APIResult.error("删除失败");
		}
	}
	/**
	 * 编辑图书
	 * 
	 */
	@PostMapping("/edit")
	@ResponseBody
	public APIResult edit(Book book) {
		// 还需要调用Service层
		int res = bookService.edit(book);
		return APIResult.success(null);
	}
	@PostMapping("/add")
	@ResponseBody
	public APIResult add(Book book) {
		//1.获取参数
		//2.调用service层
		int res = bookService.add(book);
		//3.返回数据
		if(res > 0) {
			return APIResult.success(null);
		}else {
			return APIResult.error("添加失败");
		}
		
	}
}
