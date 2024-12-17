package com.totra.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.totra.memo.common.FileManager;
import com.totra.memo.post.domain.Post;
import com.totra.memo.post.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public boolean addPost(int userId,String title, String contents, MultipartFile file){
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
						.userId(userId)
						.title(title)
						.contents(contents)
						.imagePath(imagePath)
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
	
	public boolean updatePost(int id, String title, String contents) {
		
		Optional<Post> optionalPost = postRepository.findById(id);

		if(optionalPost.isPresent()) {
			
			Post post = optionalPost.get();
			
			post = post.toBuilder().title(title).contents(contents).build();
			
			try {
				postRepository.save(post);
				return true;
			}catch(Exception e){
				return false;
			}
			
		}else {
			return false;
		}
	}
	
	public boolean deletePost(int id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			
			Post post = optionalPost.get();
			
			FileManager.removeFile(post.getImagePath());
			
			postRepository.delete(post);
			
			
			return true;
		}else {
			return false;
		}
	}
}
