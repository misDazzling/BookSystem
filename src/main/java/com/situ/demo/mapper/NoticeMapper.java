package com.situ.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.demo.entity.Notice;



@Mapper
public interface NoticeMapper {
    
	@Select("select * from notice")
	List<Notice> select();
	@Update("update notice set content=#{content},time=#{time} where id=#{id}")
	int update(Notice notice);
	  @Delete("delete from notice where id=#{id}")
	int delete(int id);
	  @Insert("insert into notice (user_id,user_name,content,time) values(#{user_id},#{user_name},#{content},#{time})") 
	int insert(Notice notice);

	
	
	


}
