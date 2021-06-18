package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;
	
	public void create(String id) {
		categoryRepository.insert(id);
	}
	
	public void create2(CategoryVo vo) {
		categoryRepository.insert2(vo);
	}

	public List<CategoryVo> findAll(String id) {
		return categoryRepository.findAll(id);
	}

	public Long findNo(String id) {
		return categoryRepository.findNo(id);
	}
	
	public void delete(int no) {
		postRepository.delete(no);
		categoryRepository.delete(no);
	}
}
