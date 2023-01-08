package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll(){
	 return	repository.findAll();
	}
	
	public Post findById(String id) {
	  Post post = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(" object not found"));
	  return post;
	}
	
	public List<Post> findByTitle(String text){
		return repository.findByTitle(text);
	}
	
	public List<Post>fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 *60 * 60 * 100);
		return repository.fullSearch(text, minDate, maxDate);
	}
	
	
	public Post insert(Post post) {
		post = repository.save(post);
		return post;
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Post update (String id , Post post) {
		Post newPost = repository.findById(id).get();
		updateData(newPost , post);
		return newPost;
	}
	
	private void updateData(Post newPost, Post post) {
		newPost.setId(post.getId());
		newPost.setTitle(post.getTitle());
		newPost.setData(post.getData());
		newPost.setBody(post.getBody());
	}
	
}
