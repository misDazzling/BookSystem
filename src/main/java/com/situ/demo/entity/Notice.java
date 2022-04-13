package com.situ.demo.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

public class Notice {
   private int id;
   private int user_id;
   private String user_name;
   private String content;
   @DateTimeFormat(pattern="yyyy-MM-dd")
   @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
   private Date time;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}

   
}
