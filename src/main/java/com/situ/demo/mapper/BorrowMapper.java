package com.situ.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.demo.entity.Borrow;
@Mapper
public interface BorrowMapper {
    @Insert("insert into borrow (user_id,user_name,book_id,book_name,borrow_time,return_time,state) values(#{user_id},#{user_name},#{book_id},#{book_name},#{borrow_time},#{return_time},#{state})")
	int insert(Borrow borrow);
  //动态sql
  		@Select("<script>"
  				+ "select *" 
  				+ "from borrow "
  				+"<where>"
  				+"<if test='key !=null'> state like'%${key}%'</if>"
  				+"<if test='id != null'>and user_id=#{id}</if>"
  				+"</where>"
  				+"</script>")
	List<Borrow> select(@Param("id")Integer id,@Param("key")String key);
  		@Select("<script>"
  				+ "select *" 
  				+ "from borrow "
  				+"<where>"
  				+"<if test='key !=null'> state like'%${key}%'</if>"
  				+"</where>"
  				+"</script>")
		List<Borrow> selectALL(@Param("key")String key);
  		@Update("update borrow set state=#{state} where id=#{id}")
		int update(Borrow borrow);

}
