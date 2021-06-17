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
	
	public List<CategoryVo> findByNo(int no) {
		return sqlSession.selectList("category.findByNo", no);
	}

	public Long findNo(String id) {
		return sqlSession.selectOne("category.findNo", id);
	}

	public boolean delete(int no) {
		int count = sqlSession.delete("category.delete", no);
		return count == 1;
	}
}
