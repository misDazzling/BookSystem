package com.situ.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.demo.entity.User;

/**
 * 这是用来访问User表的接口
 */
@Mapper
public interface UserMapper {
	/*
	 * UserMapper是一个接口, 接口是不能创建对象的
	 * 
	 * Mybatis在扫描这个接口时, 创建这个接口的代理对象
	 * 并且将代理对象交给Spring容器去管理
	 * 当在代理对象上调用方法时, 执行方法前的注解配置的SQL语句
	 * 	
	 */
	
	
	/**
	 * 根据账号查询用户
	 */
	@Select("select * from user where username=#{username}")
	User selectByUsername(String username);
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Select("select user.id,username,password,realname,phone,type from user,role where user.role = role.id")
	List<Map> selectAll();

	//添加用户
	@Insert("insert into user (username,password,realname,phone,head,role) values(#{username},#{password},#{realname},#{phone},#{head},#{role})")
	int insert(User user);

	//根据ID删除用户
	   @Delete("delete from user where id=#{id}")
	   int delete(int id);

	   //修改用户
		@Update("update user set password=#{password},realname=#{realname},phone=#{phone},head=#{head} where id=#{id}")
	   int update(User user);

		//修改权限
	  @Update("update user set role=#{role} where id=#{id}")
		int  edit(User user);
	  
	  @Select("select * from user where username=#{username}")
		List<User> selectByUser(String username);
		
} 
