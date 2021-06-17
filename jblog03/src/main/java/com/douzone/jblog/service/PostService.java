package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public List<PostVo> findAll(Long categoryNo) {
		return postRepository.findAll(categoryNo);
	}

	public Long findNo(Long categoryNo) {
		return postRepository.findNo(categoryNo);
	}

	public PostVo findAll2(Long postNo) {
		return postRepository.findAll2(postNo);
	}
	
	public Long postCount(String id) {
		return postRepository.postCount(id);
	}

	public void insert(PostVo postVo) {
		postRepository.insert(postVo);
	}
}
