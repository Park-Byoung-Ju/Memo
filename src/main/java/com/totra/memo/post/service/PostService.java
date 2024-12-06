package com.totra.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.totra.memo.post.domain.Post;
import com.totra.memo.post.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public boolean addPost(int userId,String title, String contents){
		Post post = Post.builder()
						.userId(userId)
						.title(title)
						.contents(contents)
						.build();
					
		try {		
			postRepository.save(post);
			return true;
		}catch(Exception e) {

			return false;
		}

	}
	
	public List<Post> getPostList(int userId){

		return postRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	public Post getPost(int id) {
		
		Optional<Post> resultPost = postRepository.findById(id);
		Post post = resultPost.orElse(null);
		
		return post;
	}
}
