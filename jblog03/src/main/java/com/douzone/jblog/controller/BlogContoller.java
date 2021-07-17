package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogContoller {
	@Autowired
	private ServletContext application; // application scope 사용
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;
	
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
			postNo = postService.findNo(categoryNo);
		} else {
			categoryNo = categoryService.findNo(id);
			postNo = postService.findNo(categoryNo);
		}
		BlogVo blogVo = blogService.findTitle(id);
		application.setAttribute("blogVo", blogVo);
		List<CategoryVo> categoryVo = categoryService.findAll(id);
		model.addAttribute("categoryVo", categoryVo);
		List<PostVo> postVo = postService.findAll(categoryNo);
		model.addAttribute("postVo", postVo);
		PostVo postVo1 = postService.findAll2(postNo);
		application.setAttribute("postVo1", postVo1);
		return "blog/index";
	}
	
//	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) { // auth에서 id로 admin인지 체크
		return "blog/admin/basic";
	}
	
//	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) { // auth에서 id로 admin인지 체크
		List<CategoryVo> categoryVo = categoryService.findAll(id);
		model.addAttribute("categoryVo", categoryVo);
		return "blog/admin/category";
	}
	
//	@Auth
	@RequestMapping(value="/admin/write", method = RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) { // auth에서 id로 admin인지 체크
		List<CategoryVo> categoryVo = categoryService.findAll(id);
		model.addAttribute("categoryVo", categoryVo);
		return "blog/admin/write";
	}
	
	@RequestMapping(value="/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo) { // auth에서 id로 admin인지 체크
		postService.insert(postVo);
		return "redirect:/" + id;
	}
	
	@RequestMapping(value="/admin/category/add", method=RequestMethod.POST)
	public String add(@PathVariable("id") String id, CategoryVo vo) {
		vo.setBlogId(id);
		categoryService.create2(vo);
		return "redirect:/" + id + "/admin/category";
	}
	
	@RequestMapping(value="/admin/update", method=RequestMethod.POST)
	public String updateMain(@PathVariable("id") String id, BlogVo blogVo, @RequestParam("file1") MultipartFile file1, Model model) {
		if(file1.isEmpty()) {
			blogService.updateBlog(blogVo);
			application.setAttribute("blogVo", blogVo);
			return "redirect:/" + id + "/admin/basic";
		} else {
			String url = fileUploadService.restore(file1);
			blogVo.setLogo(url);
			blogService.updateBlog(blogVo);
			application.setAttribute("blogVo", blogVo);
			return "redirect:/" + id + "/admin/basic";
		}
	}
	
	@RequestMapping(value="/admin/category/delete/{no}", method=RequestMethod.GET)
	public String delete(
			@PathVariable("id") String id, 
			@PathVariable("no") int no) {
		categoryService.delete(no);
		return "redirect:/" + id + "/admin/category";
	}
}
