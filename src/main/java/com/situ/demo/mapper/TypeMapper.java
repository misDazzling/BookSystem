package com.situ.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.demo.entity.Type;
import com.situ.demo.entity.User;

@Mapper
public interface TypeMapper {

	// 查询所有
		@Select("select * from type")
		List<Type> selectAll();
        @Update("update type set name=#{name} where id=#{id}")
		int update(Type type);
        @Delete("Delete from type where id=#{id}")
		int delete(int id);
        @Insert("insert into type (name) values(#{name})")
		int add(Type type);
}
