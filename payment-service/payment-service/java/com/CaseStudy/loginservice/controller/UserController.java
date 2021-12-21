package com.CaseStudy.loginservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CaseStudy.loginservice.model.User;
import com.CaseStudy.loginservice.repository.UserRepository;

@RestController
public class UserController 
{
	 @Autowired
	 private UserRepository userRepository;
	 
	 @PostMapping("/addUser")
	 public String addUser(@RequestBody User user)
	 {
		 userRepository.save(user);
		 return "Added new User";  
	 }
	 
	 @GetMapping(".findAllUser")
	 public List<User> getUser()
	 {
		return userRepository.findAll();

	 }
	 
	 @DeleteMapping
	 public  String deleteUser(@RequestBody User user)
	 {
		userRepository.delete(user);
		return "Deleted 1 user"; 
	 }

}
