package com.douzone.jblog.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
 	public String login(
 			@RequestParam(value = "id", required = true, defaultValue = "") String id,
 			@RequestParam(value = "password", required = true, defaultValue = "") String password, Model model, HttpSession session) {
 		UserVo authUser = userService.getUser(id, password);
 		if(authUser == null) {
 			model.addAttribute("result", "fail");
 			model.addAttribute("id", id);
 			return "user/login";
 		}
 		session.setAttribute("authUser", authUser);
 		return "redirect:/";
 	}

 	@RequestMapping("/logout")
 	public String logout(HttpSession session) {
 		UserVo authUser = (UserVo)session.getAttribute("authUser");
 		if(authUser == null) {
 			return "redirect:/";
 		}
 		session.removeAttribute("authUser");
 		session.invalidate();
 		return "redirect:/";
 	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo, Model model) {
		userService.join(vo);
		blogService.create(vo.getId());
		categoryService.create(vo.getId());
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
}
