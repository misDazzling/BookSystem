package com.situ.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.situ.demo.entity.Role;
@Mapper
public interface RoleMapper {
     @Select("select * from role") 
   List<Role> selectAll();
     
}
