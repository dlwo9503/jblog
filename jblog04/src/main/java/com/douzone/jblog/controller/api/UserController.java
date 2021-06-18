package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@RestController("userControllerApi") // 다른 패키지의 유저 컨트롤러와 중복을 피하기위해서 id를 지정해줌
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkid") // GetMapping를 사용하면 뒤에 따로 GET를 써주지 않아도 됨
	public JsonResult checkid(
			@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVo userVo = userService.getUser(id);
		return JsonResult.success(userVo != null);
	}
}
