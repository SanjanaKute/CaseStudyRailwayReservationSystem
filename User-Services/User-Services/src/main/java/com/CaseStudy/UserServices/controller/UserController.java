package com.CaseStudy.UserServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.UserServices.model.TicketReservation;
import com.CaseStudy.UserServices.model.Trains;

@RestController
public class UserController {
	
	@Autowired
	RestTemplate restTemplate;
	
	public UserController(RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate=restTemplateBuilder.build();
	}
//User Operation On Train Service
	public List<Trains> displayAll() {
		// TODO Auto-generated method stub
		return restTemplate.exchange("http://localhost:8000/train-service/train/showAllTrains", HttpMethod.GET, null, List.class).getBody();
	}
	
	@RequestMapping("/{startStation}")
	public List<Trains> SearchByStartLocation(String startStation) {
		// TODO Auto-generated method stub
		return restTemplate.exchange("http://localhost:8000/train-service/train/findtrainByStartStation/"+startStation, HttpMethod.GET, null, List.class).getBody();
	}
	
	@RequestMapping("/{endStation}")
	public List<Trains> searchTrainByEndLocation(String endStation) {
		// TODO Auto-generated method stub
		return restTemplate.exchange("http://localhost:8000/train-service/train/findtrainByEndStation/"+endStation, HttpMethod.GET, null, List.class).getBody();
	}
//------------Uuer Service on reservation service----------------------------------------------------
	public List<TicketReservation> getReservation() {
		// TODO Auto-generated method stub
		return restTemplate.exchange("http://localhost:8000/ticket-reservation-service/reserve/getAllTicketsDetails", HttpMethod.GET, null, List.class).getBody();
	}

	public String add(TicketReservation reservation) {
		HttpEntity<TicketReservation> entity=new HttpEntity<>(reservation);
		return restTemplate.exchange("http://localhost:8000/ticket-reservation-service/reserve/addTicket", HttpMethod.POST, entity, String.class).getBody();
	}

	public String deleteReservationByAdmin(String id) {
		// TODO Auto-generated method stub
		return restTemplate.exchange("http://localhost:8000/ticket-reservation-service/reserve/del/"+id, HttpMethod.DELETE, null, String.class).getBody();
	}


	
	
	
	
	
	
	
}
