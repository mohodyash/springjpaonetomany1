package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;

@Service
public class CommentService {
	
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Comment> getAllCommentsByPostId(Long postid){
		
		return commentRepository.findByPostId(postid);
	}
	
	public Comment createComment(long postid, Comment comment) throws RecordNotFoundException
	{
		Optional<Post> postList = postRepository.findById(postid);
		Post post = new Post();
		if(postList.isPresent())
		{
			post = postList.get();
		}
		else {
			throw new RecordNotFoundException("Record not found");
		}
		comment.setPost(post);
		
		return commentRepository.save(comment);
		
		
	}
	
	public Comment updateComment(long postid, long commentid, Comment commentRequest) throws RecordNotFoundException
	{
		if(!postRepository.existsById(postid))
		{
			throw new RecordNotFoundException("PostId " + postid + " not found");
		}
		
		return commentRepository.findById(commentid).map(comment -> {
			comment.setText(commentRequest.getText());
			return commentRepository.save(comment);
		}).orElseThrow(()-> new RecordNotFoundException("CommentId " + commentid + "not found"));
	}
	
	public void deleteComment(long commentid, long postid) throws RecordNotFoundException
	{
		commentRepository.findByIdAndPostId(commentid, postid);
	}
	
	
}
