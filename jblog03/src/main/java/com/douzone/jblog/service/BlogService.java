package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository boardRepository;

	public void create(String id) {
		boardRepository.insert(id);
	}

	public BlogVo findTitle(String id) {
		return boardRepository.findTitle(id);
	}
}
