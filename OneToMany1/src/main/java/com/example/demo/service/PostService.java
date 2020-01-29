package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	
	public List<Post> getAllPost()
	{
		List<Post> employeelist = postRepository.findAll(); 
		if(employeelist.size() > 0 )
		{
			return employeelist;
		}
		else {
			return new ArrayList<Post>();
		}
	}
	
	public Post getPostById(Long id) throws RecordNotFoundException
	{
		Optional<Post> employee = postRepository.findById(id);
		if(employee.isPresent())
		{
			return employee.get();
		}
		else {
			throw new RecordNotFoundException("record not found");
		}
	}
	
	public Post createPost(Post p)
	{
		Post post= postRepository.save(p);
		return post;
	}
	
	public Post updatePost(Long postid,Post p) throws RecordNotFoundException
	{
		Optional<Post> postlist = postRepository.findById(postid);
		
		if(postlist.isPresent())
		{
			Post post = postlist.get();
			post.setTitle(p.getTitle());
			post.setDescription(p.getDescription());
			post.setContent(p.getContent());
		    return postRepository.save(post);
		}
		else {
			throw new RecordNotFoundException("Record not found");
		}
		
		
	}
	
	public void deletePostById(Long id) throws RecordNotFoundException
	{
		Optional<Post> postlist = postRepository.findById(id);
		
		if(postlist.isPresent())
		{
			postRepository.deleteById(id);
		}
		else {
			throw new RecordNotFoundException("record not exit");
		}
	}
	

}
