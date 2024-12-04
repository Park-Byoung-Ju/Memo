package com.totra.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.totra.memo.user.domain.User;
import com.totra.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	private UserService userService;
	

	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("/join")
	public Map<String, String> join(@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, name, email)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestParam("loginId") String loginId
									,@RequestParam("password") String password
									,HttpServletRequest request){
		Map<String, String> resultMap = new HashMap<>();
		
		User user = userService.getUser(loginId, password);
			
		if(user != null) {
			HttpSession session = request.getSession();
			// primary key => id, 자주쓰는 name
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
