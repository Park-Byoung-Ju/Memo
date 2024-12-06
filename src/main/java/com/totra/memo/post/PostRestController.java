package com.totra.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.totra.memo.post.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	private PostService postService;
	
	public PostRestController(PostService postService){
		this.postService = postService;
	}

	
	@PostMapping("/create")
	public Map<String, String> createMemo(@RequestParam("title") String title
			,@RequestParam("contents") String contents
			,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		int userId = (int)session.getAttribute("userId");
		
		boolean result = postService.addPost(userId, title, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(result) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
