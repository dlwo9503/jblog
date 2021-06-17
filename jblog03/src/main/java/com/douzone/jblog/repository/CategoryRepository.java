package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(String id) {
		int count = sqlSession.insert("category.insert", id);
		return count == 1;
	}
	
	public boolean insert2(CategoryVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("category.insert2", vo);
		return count == 1;
	}

	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}
}
