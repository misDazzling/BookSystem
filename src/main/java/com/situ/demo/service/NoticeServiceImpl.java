package com.situ.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.demo.entity.Borrow;
import com.situ.demo.entity.Notice;
import com.situ.demo.entity.User;
import com.situ.demo.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
	@Override
	public PageInfo getByPage(int page, int limit) {
		//PageHelper分页
				//1.开启分页
				PageHelper.startPage(page,limit);
				//2.查询数据库
				List<Notice> notices = noticeMapper.select();
				//3.创建PageInfo对象
				
				return new PageInfo<>(notices);
	}

	@Override
	public int edit(Notice notice) {
		return noticeMapper.update(notice);
	}

	@Override
	public int delete(int id) {
		return noticeMapper.delete(id);
	}

	@Override
	public int add(Notice notice) {
		return noticeMapper.insert(notice);
	}

}
