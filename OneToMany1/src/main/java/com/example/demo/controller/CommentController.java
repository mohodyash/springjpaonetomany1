package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	 @GetMapping("/posts/{postId}/comments")
	 public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable (value = "postId") long postid)
	 {
		List<Comment> list = commentService.getAllCommentsByPostId(postid);
		return new ResponseEntity<List<Comment>>(list, new HttpHeaders(), HttpStatus.OK);
	 }
	
	@PostMapping("/posts/{postId}/comments") 
	public ResponseEntity<Comment> createComment(@PathVariable (value = "postId") long postId,
			                     @Valid @RequestBody Comment comment)
	throws RecordNotFoundException
	{
		Comment comment2 = commentService.createComment(postId, comment);
		return new ResponseEntity<Comment>(comment2, new HttpHeaders(), HttpStatus.OK);
	}
	
	 @PutMapping("/posts/{postId}/comments/{commentId}")
	 public ResponseEntity<Comment> updateComment(@PathVariable(value = "postId") long postid,
			                                      @PathVariable(value = "commentId") long commentid,
			                                      @Valid @RequestBody Comment requestComment
			                                      ) throws RecordNotFoundException
	 {
		Comment comment =  commentService.updateComment(postid, commentid, requestComment);
		 return new ResponseEntity<Comment>(comment , new HttpHeaders(), HttpStatus.OK);
		 
	 }
	 
	 @DeleteMapping("/posts/{postId}/comments/{commentId}")
	 public String deleteComment(@PathVariable (value = "postId") Long postId,
             @PathVariable (value = "commentId") Long commentId) throws RecordNotFoundException
	 {
		 commentService.deleteComment(commentId, postId);
		 return "deleted";
	 }
	
	
	

}
