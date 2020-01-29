package com.example.demo.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(long postiid);

	Object findByIdAndPostId(long commentid, long postid);
	
	
//	  Page<Comment> findByPostId(Long postId, Pageable pageable);
//	  Optional<Comment> findByIdAndPostId(Long id, Long postId);

}
