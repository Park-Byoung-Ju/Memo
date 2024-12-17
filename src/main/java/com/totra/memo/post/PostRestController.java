package com.totra.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
			,@RequestParam(name = "imageFile", required = false) MultipartFile file
			,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		int userId = (int)session.getAttribute("userId");
		
		boolean result = postService.addPost(userId, title, contents, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(result) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PutMapping("/update")
	public Map<String, String> updatedMemo(@RequestParam("id") int id
										,@RequestParam("title") String title
										,@RequestParam("contents") String contents) {
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.updatePost(id, title, contents)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteMamo(@RequestParam("id") int id){
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.deletePost(id)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
