package com.situ.demo.entity;

public class APIResult {

	private Integer code;
	private String msg;
	private Object data;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	// 点击菜单->Source->Generate Getters and Setters ...
	// 弹出窗口, 点击右上角的Select All -> finish
	// lombok
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	// 静态方法
	public static APIResult success(Object data) {
		APIResult result = new APIResult();
		result.setCode(0);
		result.setData(data);
		return result;
	}
	
	public static APIResult error(String msg) {
		APIResult result = new APIResult();
		result.setCode(-1);
		result.setMsg(msg);
		return result;
	}
}
