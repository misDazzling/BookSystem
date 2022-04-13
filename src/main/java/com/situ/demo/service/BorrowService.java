package com.situ.demo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Borrow;

public interface BorrowService {

	int add(Borrow borrow);

	PageInfo getByPage(int page, int limit,Integer id,String key);
	PageInfo getByPage(int page, int limit,String key);

	int edit(Borrow borrow);
	

}
