package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return  repository.findAll();
	}
	
	public User findById(String id) {
		User user = repository.findById(id).get();
		if(user == null) {
			throw new ObjectNotFoundException("object not found ");
		}
		return user;
	}
	
	public User Insert(User obj) {
		return repository.save(obj);
	}
	
	public User update(User obj) {
		User newObj = repository.findById(obj.getId()).get();
		updateData(newObj,obj);
		repository.save(newObj);
		return newObj;
	}
	
	public void deleteById(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDTO(UserDTO userDTO) {
	User user = new User(userDTO.getId(),userDTO.getName(),userDTO.getEmail());
	user = repository.save(user);
	 return user;
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setId(obj.getId());
		newObj.setEmail(obj.getEmail());
		newObj.setName(obj.getName());
	}
}
