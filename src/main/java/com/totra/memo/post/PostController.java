package com.totra.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

	@RequestMapping("/list-view")
	public String memoList(){
		return "post/list";
	}
}
