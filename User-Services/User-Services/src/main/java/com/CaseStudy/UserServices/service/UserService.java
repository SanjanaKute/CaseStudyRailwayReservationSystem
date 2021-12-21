package com.CaseStudy.UserServices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.UserServices.controller.UserController;
import com.CaseStudy.UserServices.model.TicketReservation;
import com.CaseStudy.UserServices.model.Trains;
import com.CaseStudy.UserServices.model.User;
import com.CaseStudy.UserServices.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserService 
{
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserController user;
	
	public UserService(UserController user)
	{
		this.user=user;
	}
	
	@RequestMapping("/user")
	public String hello()
	{
		return "Hello from user service";
	}
	@PostMapping("/addUser") 
	  public String saveUser(@RequestBody User user) 
	  {
		userRepository.save(user); 
		return "Added user with id :" +user.getUserId(); 
	  }
	 
	  @GetMapping("/findAllUsers")
	  public List<User> getAllUsers()
	  {
		    return userRepository.findAll();
	  }

	  @GetMapping("/findAllUsers/{userId}")
	  public Optional<User> getUser(@PathVariable String userId)
	  {
		  	return userRepository.findById(userId);
	  }
	
	  @DeleteMapping("/delete/{userId}")
	  public String deleteUser(@PathVariable String userId)
	  {
		  userRepository.deleteById(userId);
		return "User deleted with id "+userId;	
	  }
//========================================User on  Train Service===================================================
	  
	  public List<Trains> show()
	  {
		  return user.displayAll(); 
	  }
	
	  @GetMapping("/searchTrainByStartDestination/{startStation}")
		public List<Trains> searchTrainByStartDestination(@PathVariable("startStation") String startStation )
		{
			return user.SearchByStartLocation(startStation);
		}
	  
	  @GetMapping("/searchTrainByEndDestination/{endStation}")
		public List<Trains>searchTrainByEndDestination(@PathVariable("endStation") String endStation)
		{
			return user.searchTrainByEndLocation(endStation);	
		}
	  
//========================================User on  Reservation Service===================================================
	  @GetMapping("/getreservation")
	  public List<TicketReservation> getTicketsDetails()
	  {
		return user.getReservation();
		  
	  }
	  
	  @PostMapping("/add")
	  public String add(@RequestBody TicketReservation reserve)
	  {
		  return user.add(reserve);
	  }
	  
	  @DeleteMapping("/deleteReservation/{id}")
	  public String deleteReservation(@PathVariable String id)
	  {
		  return user.deleteReservationByAdmin(id);
	  }
}
