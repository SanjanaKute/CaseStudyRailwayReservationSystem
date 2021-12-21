 package com.CaseStudy.adminservice.resources;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.adminservice.model.TicketReservation;
import com.CaseStudy.adminservice.model.User;
import com.CaseStudy.adminservice.service.AdminService;
import com.CaseStudy.trainservice.model.Trains;

@RestController
public class AdminController {
	
	RestTemplate restTemplate;
	
	@Autowired
	public AdminController(RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate=restTemplateBuilder.build();
	}
//================================================Train Service Admins Operations=========================================
//------------Adding Train information--------------------------------
	public String add(Trains trains) 
	{
		HttpEntity<Trains> entity=new HttpEntity<>(trains);
		return restTemplate.exchange("http://localhost:8000/train-service/train/addTrain", HttpMethod.POST, entity,String.class).getBody();
	}

//------------Displaying Train information--------------------------------

	
	public List<Trains> displayAll() {
		return restTemplate.exchange("http://localhost:8000/train-service/train/showAllTrains", HttpMethod.GET, null,List.class).getBody();
	}

//------------------------startstation---------------------------------------------------------
	@RequestMapping("/{startStation}")
	public List<Trains> SearchByStartLocation(@PathVariable("startStation")String startStation) {
		
		return restTemplate.exchange("http://localhost:8000/train-service/train/findtrainByStartStation/"+startStation, HttpMethod.GET, null, List.class).getBody();
	}
//--------------------------------Endstation------------------------------------------------------	
	
	@RequestMapping("/{endStation}")
	public List<Trains> searchTrainByEndLocation(String endStation) {
		// TODO Auto-generated method stub
		return restTemplate.exchange("http://localhost:8000/train-service/train/findtrainByEndStation/"+endStation, HttpMethod.GET, null, List.class).getBody();
	}
//----------------------------Delete-------------------------
	@DeleteMapping("/{trainid}")
	public String deleteTrain(String trainid) {
		
		return restTemplate.exchange("http://localhost:8000/train-service/train/delete/"+trainid, HttpMethod.DELETE, null, String.class).getBody();
	}
	
	
	
//================================================User Service Admins Operations=========================================
	
	public List<User> getAllUser() {
		return restTemplate.exchange("http://localhost:8000/User-Services/users/findAllUsers", HttpMethod.GET, null, List.class).getBody();
	}
	
	@DeleteMapping("/{userId}")
	public String deleteUserByAdmin(String userId) {
		return restTemplate.exchange("http://localhost:8000/User-Services/users/delete/"+userId, HttpMethod.DELETE, null, String.class).getBody();
	}
	
	
	
//================================================ Admins Operations on reservation=========================================
	
	public List<TicketReservation> showReservedTickets() {
		// TODO Auto-generated method stub
		return restTemplate.exchange("http://localhost:8000/ticket-reservation-service/reserve/getAllTicketsDetails", HttpMethod.GET, null, List.class).getBody();
	}
	
	@DeleteMapping("/{id}")
	public String deleteReservationByAdmin(@PathVariable String id)
	{
		return restTemplate.exchange("http://localhost:8000/ticket-reservation-service/reserve/del/"+id, HttpMethod.DELETE, null,String.class).getBody();
		
	}
	
	
	
	
}


