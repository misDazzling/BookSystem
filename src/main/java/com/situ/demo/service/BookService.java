package com.situ.demo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Book;

public interface BookService {
    //根据分类获取图书
	
	List<Book> getByTpye(int type_id);
	//分页获取数据

	PageInfo getByPage(int page, int limit, String key, Integer type_id);

	int delete(int id);

	int edit(Book book);

	int add(Book book);
}
