package com.situ.demo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Book;
import com.situ.demo.entity.Type;
import com.situ.demo.entity.User;

public interface TypeService {

	PageInfo getByPage(int page, int limit);

	List getAll();

	

	int edit(Type type);

	int delete(int id);

	int add(Type type);

	

}
