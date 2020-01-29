package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;

@Controller
@RequestMapping("/")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/all")
    public ResponseEntity<List<Post>> getAllPost()
    {
    	List<Post> list = postService.getAllPost();
    	return new ResponseEntity<List<Post>>(list , new HttpHeaders(), HttpStatus.OK);
    }
	
	@PutMapping("/post/update/{postid}")
	public ResponseEntity<Post> updatePost(@PathVariable Long postid,
			                               @Valid @RequestBody Post post) throws RecordNotFoundException
	{
		Post postResult = postService.updatePost(postid, post);
		return new ResponseEntity<Post>(postResult, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/post/delete/{postid}")
	public String deletePost(@PathVariable Long postid) throws RecordNotFoundException
	{
		postService.deletePostById(postid);
		
//		MultiMap mMap = new MultiValueMap();				
		return "post deleter";
	}
	
	
	
	@PostMapping("/post")
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
		
		System.err.println(post.getTitle());
		System.err.println(post.getDescription());
		System.err.println(post.getContent());
		
		Post postResult = postService.createPost(post);
		
		return new ResponseEntity<Post>(postResult, new HttpHeaders(), HttpStatus.OK);
	}
	
	
    
    
    

}
