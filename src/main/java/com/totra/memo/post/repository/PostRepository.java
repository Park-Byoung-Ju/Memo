package com.totra.memo.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.totra.memo.post.domain.Post;


public interface PostRepository extends JpaRepository<Post, Integer>{
	public List<Post> findByUserIdOrderByIdDesc(@Param("userId") int userId);
}
