package com.CaseStudy.adminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.adminservice.model.TicketReservation;
import com.CaseStudy.adminservice.model.User;
import com.CaseStudy.adminservice.resources.AdminController;
import com.CaseStudy.trainservice.model.Trains;

@RestController
@RequestMapping("/admin")
public class AdminService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AdminController admin;
	
	@Autowired
	public AdminService(AdminController admin)
	{
		this.admin=admin;
	}
//Train Related Operations------------------------------------------

	@PostMapping("/add")
	public String addUser(@RequestBody Trains trains)
	{
		//admin.add(trains);
		return "Admin Added train with: "+admin.add(trains);
	}

	@GetMapping("/")
	public List<Trains> display() //(@RequestBody Trains trains )                            //ResponseEntity<List> consume()
	{
		return admin.displayAll(); 
	}
	
	@GetMapping("/searchTrainByStartDestination/{startStation}")
	public List<Trains> searchTrainByStartDestination(@PathVariable("startStation") String startStation )
	{
		return admin.SearchByStartLocation(startStation);
	}
//-------------------------------	
	@GetMapping("/searchTrainByEndDestination/{endStation}")
	public List<Trains>searchTrainByEndDestination(@PathVariable("endStation") String endStation)
	{
		return admin.searchTrainByEndLocation(endStation);
		
	}
	
	@DeleteMapping("deleteTrain/{trainid}")
	public String deleteTrainInfo(@PathVariable String trainid)//(@PathVariabel ("trianid")String trainId)
	{
		return "Admin deleted train with id :"+admin.deleteTrain(trainid);		
	}	
	
//User Related Operations------------------------------------------
	
	@GetMapping("/getAllUser")
	public List<User>getUser()
	{
		return admin.getAllUser();
	}

	@DeleteMapping("deleteUser/{userId}")
	public String deleteUser(@PathVariable("") String userId)
	{
		return admin.deleteUserByAdmin(userId);
		
	}

//Reservation Related Operations------------------------------------------
	@GetMapping("/getAllReservation")
	public List<TicketReservation> getTicketsDetails()
	{
		return admin.showReservedTickets();
		
	}
	
	@DeleteMapping("deleteReservation/{id}")
	public String deleteReservation(@PathVariable String id)
	{
		return admin.deleteReservationByAdmin(id);
		
	}
	
	
	
}
	
	//--------------------------------/
//	@PutMapping("/updateInfoByAdmin/{trainid}")
//	public String updateTrain(@RequestBody Trains trains)
//	{
//		return "Updated train with id :"+admin.updateTrainByAdmin(trains);
//	}	

