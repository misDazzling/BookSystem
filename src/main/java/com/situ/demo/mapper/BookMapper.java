package com.situ.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.demo.entity.Book;


@Mapper
public interface BookMapper {
	// 根据部门ID查询员工
		@Select(" select * from book where type_id=#{type_id}")
		List<Book> selectByDeptId(int type_id);

		//查询所有图书
		@Select("select * from book")
		List<Book> selectAll();
		
		
		//动态sql
		@Select("<script>"
				+ "select s1.*, type.name type_name " 
				+ "from book as s1  "
				+ "inner join type on s1.type_id=type.id "
				+"<where>"
				+"<if test='key !=null'>s1.title like '%${key}%'</if>"
				+"<if test='type_id != null '>and s1.type_id=#{type_id}</if>"
				+"</where>"
				+"</script>")
		
			
		List<Map> selectALL(@Param("key")String key, @Param("type_id")Integer type_id);
		//@Param("key")String key, 
		//@Param("type_id")Integer dept_id
        @Delete("delete from book where id = #{id}")
		int delete(int id);
        @Update("update book set title=#{title},price=#{price},publish=#{publish},publish_time=#{publish_time},authors=#{authors},type_id=#{type_id} where id=#{id}")
		int update(Book book);
        //添加图书
    	@Insert("insert into book (title,price,publish,publish_time,authors,type_id) values(#{title},#{price},#{publish},#{publish_time},#{authors},#{type_id})")
    	int insert(Book book);
		
}
