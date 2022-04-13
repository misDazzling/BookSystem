package com.situ.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Borrow;
import com.situ.demo.mapper.BorrowMapper;

@Service
public class BorrowServiceImpl implements BorrowService{
    @Autowired
    private BorrowMapper borrowMapper;
	@Override
	public int add(Borrow borrow) {
		return borrowMapper.insert(borrow);
	}
	
	@Override
	public PageInfo getByPage(int page, int limit, Integer id,String key) {
		//PageHelper分页
		//1.开启分页
		PageHelper.startPage(page,limit);
		//2.查询数据库
		List<Borrow> borrows = borrowMapper.select(id,key);
		//3.创建PageInfo对象
		
		return new PageInfo<>(borrows);
	}

	@Override
	public PageInfo getByPage(int page, int limit, String key) {
		//PageHelper分页
				//1.开启分页
				PageHelper.startPage(page,limit);
				//2.查询数据库
				List<Borrow> borrows = borrowMapper.selectALL(key);
				//3.创建PageInfo对象
				
				return new PageInfo<>(borrows);
	}

	@Override
	public int edit(Borrow borrow) {
		return borrowMapper.update(borrow);
	}

	
}
