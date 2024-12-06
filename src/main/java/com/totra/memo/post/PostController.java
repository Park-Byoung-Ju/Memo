package com.totra.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totra.memo.post.domain.Post;
import com.totra.memo.post.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService){
		this.postService = postService;
	}

	@GetMapping("/list-view")
	public String list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		List<Post> postList = postService.getPostList(userId);
		
		if(postList != null) {
			model.addAttribute("result","success");
		}else {
			model.addAttribute("result", "fail");
		}
		
		model.addAttribute("postList", postList);
		
		return "post/list";
	}
	
	@GetMapping("/create-view")
	public String inputMemo() {
		
		return "post/input";
	}
	
	@GetMapping("/detail-view")
	public String detailMemo(@RequestParam("id") int id
							,Model model) {
		
		Post resultPost = postService.getPost(id);
		
		model.addAttribute("memo", resultPost);
		
		return "post/detail";
	}
}
