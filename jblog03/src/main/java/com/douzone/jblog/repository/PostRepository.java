package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> findAll(Long categoryNo) {
		return sqlSession.selectList("post.findAll", categoryNo);
	}

	public Long findNo(Long categoryNo) {
		return sqlSession.selectOne("post.findNo", categoryNo);
	}

	public PostVo findAll2(Long postNo) {
		return sqlSession.selectOne("post.findAll2", postNo);
	}
	
	public Long postCount(String id) {
		return sqlSession.selectOne("post.postCount", id);
	}

	public void insert(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
	}

	public void delete(int no) {
		sqlSession.delete("post.delete", no);
	}
}
