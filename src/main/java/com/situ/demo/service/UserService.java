package com.situ.demo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.User;

public interface UserService {



	// 登录验证
	User login(User user) throws Exception;

	
	
    
	/**增删改查
	 * 
	 * 
	 */
	PageInfo getByPage(int page, int limit);



    //添加用户
	int add(User user);



   //删除用户
	int delete(int id);
	
	//编辑
	int edit(User user);




	int roleedit(User user);




	PageInfo getByPage(int page, int limit, String username);
}
