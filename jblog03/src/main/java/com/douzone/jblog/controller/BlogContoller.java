package com.douzone.jblog.controller;

import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogContoller {
	@Autowired
	private ServletContext application; // application scope 사용
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"}, method = RequestMethod.GET)
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		// 0일 경우 나중에 서비스단에서 디폴트값 설정해줘야 함
		if(pathNo2.isPresent()) { //pathNo2가 있으면, Optional
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) { //pathNo1이 있으면
			categoryNo = pathNo1.get();
		}
			
		BlogVo blogVo = blogService.findTitle(id);
		model.addAttribute("blogVo", blogVo);
		application.setAttribute("title", blogVo.getTitle());
		return "blog/index";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) { // auth에서 id로 admin인지 체크
		return "blog/admin/basic";
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id) { // auth에서 id로 admin인지 체크
		return "blog/admin/category";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id) { // auth에서 id로 admin인지 체크
		return "blog/admin/write";
	}
}
