package com.situ.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.User;
import com.situ.demo.mapper.UserMapper;
import com.situ.demo.util.MD5Util;

/**
 * Service层UserService接口的实现类
 * @author bobzyh
 *
 */
@Service	// 将当前类, 创建对象, 放到Spring容器中
public class UserServiceImpl implements UserService{

	@Autowired // 自动注入, 
	// 当前类的对象被 创建时, 将从Spring容器中, 
	// 寻找与当前属性类型相匹配的对象.
	private UserMapper userMapper;
	
	
	
	@Override
	public User login(User user) throws Exception {
		// 1. 验证账号和密码的格式
		if (user.getUsername().length() < 3 || user.getUsername().length() > 16) {
			// 3-16位
			// 结束这个方法, 通过抛出异常
			throw new Exception("账号格式不正确!");
		}
		if (user.getPassword().length() < 3 || user.getPassword().length() > 16) {
			// 密码格式不对
			throw new Exception("密码格式不正确!");
		}
		
		// 2. 验证用户名
		User selectUser = userMapper.selectByUsername(user.getUsername());
		if (selectUser == null) {
			// 用户名不存在
			throw new Exception("账号不存在!!");
		}
		
		// 3. MD5加密
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		
		// 4. 验证密码
		if ( ! user.getPassword().equals(selectUser.getPassword()) ) {
			// 密码错误
			throw new Exception("密码不正确!");
		}
		// 登录应该成功
		return selectUser;
	}



	
	
	
	/**增删改查
	 * 
	 */
	@Override
	public PageInfo getByPage(int page, int limit) {
		//利用PageHelper 完成查询分页，无代码侵入式的分页方式
		//启动分页
		PageHelper.startPage(page,limit);
		
		List users = userMapper.selectAll();
		
		//整合数据
		PageInfo pageInfo = new PageInfo<>(users);
		return pageInfo;
		
	}





   //添加操作
	@Override
	public int add(User user) {
		//验证用户名是否重复
		User selectUser= userMapper.selectByUsername(user.getUsername());
		if(selectUser != null) {
			return -2;
		}else {
		user.setPassword(MD5Util.getMD5(user.getPassword()));//加密密码
		
		
		return userMapper.insert(user);
		}
	}





   //删除操作
	@Override
	public int delete(int id) {
		
		return userMapper.delete(id);
	}
	//编辑用户
	@Override
	public int edit(User user) {
		user.setPassword(MD5Util.getMD5(user.getPassword()));//加密密码
		return userMapper.update(user);
	}





   //编辑权限
	@Override
	public int roleedit(User user) {
		return userMapper.edit(user);
	}






	@Override
	public PageInfo getByPage(int page, int limit, String username) {
PageHelper.startPage(page,limit);
		
		List<User> user = userMapper.selectByUser(username);
		
		//整合数据
		PageInfo pageInfo = new PageInfo<>(user);
		return pageInfo;
	}

}
