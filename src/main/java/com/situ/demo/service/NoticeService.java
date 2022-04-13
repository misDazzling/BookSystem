package com.situ.demo.service;

import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Notice;
import com.situ.demo.entity.User;

public interface NoticeService {

	PageInfo getByPage(int page, int limit);



	int edit(Notice notice);



	int delete(int id);



	int add(Notice notice);

}
