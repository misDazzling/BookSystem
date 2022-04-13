package com.situ.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Book;
import com.situ.demo.mapper.BookMapper;

@Service
public class BookSeriviceImpl implements BookService{
   @Autowired
   private BookMapper bookMapper;
	@Override
	public List<Book> getByTpye(int type_id) {
		
		return bookMapper.selectByDeptId(type_id);
		
	}
	@Override
	public PageInfo getByPage(int page, int limit,String key,Integer type_id) {
		//PageHelper分页
		//1.开启分页
		PageHelper.startPage(page,limit);
		//2.查询数据库
		List books = bookMapper.selectALL(key,type_id);
		//3.创建PageInfo对象
		
		return new PageInfo<>(books);
	}
	@Override
	public int delete(int id) {
		return bookMapper.delete(id);
	}
	@Override
	public int edit(Book book) {
		return bookMapper.update(book);
	}
	@Override
	public int add(Book book) {
		return bookMapper.insert(book);
	}

}
