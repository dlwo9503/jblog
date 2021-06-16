package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogContoller {
	
	@ResponseBody
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		// 0일 경우 나중에 서비스단에서 디폴트값 설정해줘야 함
		if(pathNo2.isPresent()) { //pathNo2가 있으면, Optional
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) { //pathNo1이 있으면
			categoryNo = pathNo1.get();
		}
			
		System.out.println("id:" + id);
		System.out.println("category:" + categoryNo);
		System.out.println("post:" + postNo);
		return "BlogController.index";
	}
	
	@ResponseBody
	@RequestMapping("/adminBasic")
	public String adminBasic(@PathVariable("id") String id) { // auth에서 id로 admin인지 체크
		System.out.println(id);
		return "BlogController.adminBasic";
	}
}
