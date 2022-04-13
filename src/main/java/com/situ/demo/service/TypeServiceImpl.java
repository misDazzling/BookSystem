package com.situ.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Book;
import com.situ.demo.entity.Type;
import com.situ.demo.entity.User;
import com.situ.demo.mapper.TypeMapper;

@Service
public class TypeServiceImpl implements TypeService{
   @Autowired
   TypeMapper typeMapper;
	@Override
	public PageInfo getByPage(int page, int limit) {
	 //利用PageHelper 完成查询分页，无代码侵入式的分页方式。
	 		//启动分页
	 		PageHelper.startPage(page,limit);
	 		
	 		List types = typeMapper.selectAll();
	 		
	 		//整合数据
	 		PageInfo pageInfo = new PageInfo<>(types);
	 		
	 		
	 		return pageInfo;
	}
	@Override
	public List getAll() {
		return typeMapper.selectAll();
	}
	@Override
	public int edit(Type type) {
	  return typeMapper.update(type);
	}
	@Override
	public int delete(int id) {
		return typeMapper.delete(id);
	}
	@Override
	public int add(Type type) {
		return typeMapper.add(type);
	}

}
