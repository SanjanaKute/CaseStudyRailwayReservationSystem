package com.CaseStudy.RailwaySecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.RailwaySecurity.model.TicketReservation;
import com.CaseStudy.RailwaySecurity.model.Trains;
import com.CaseStudy.RailwaySecurity.model.UserModel;
import com.CaseStudy.RailwaySecurity.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel foundedUser=userRepository.findByUsername(username);//find user by username in repo//if not 
		if (foundedUser==null)
		return null;
		String name=foundedUser.getUsername();
		String pwd=foundedUser.getPassword();
		
		return new User(name, pwd, new ArrayList<>()); //this User is for securitycore
		
	}

	@Autowired
	RestTemplate restTemplate;
	 public UserService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	 }
	 
	 
//User Operation On Train Service	
	 public List<Trains> displayAll() {
			// TODO Auto-generated method stub
			return restTemplate.exchange("http://localhost:8002/train-service/train/showAllTrains", HttpMethod.GET, null, List.class).getBody();
		}
		
		@RequestMapping("/{startStation}")
		public List<Trains> SearchByStartLocation(String startStation) {
			// TODO Auto-generated method stub
			return restTemplate.exchange("http://localhost:8002/train-service/train/findtrainByStartStation/"+startStation, HttpMethod.GET, null, List.class).getBody();
		}
		
		@RequestMapping("/{endStation}")
		public List<Trains> searchTrainByEndLocation(String endStation) {
			// TODO Auto-generated method stub
			return restTemplate.exchange("http://localhost:8002/train-service/train/findtrainByEndStation/"+endStation, HttpMethod.GET, null, List.class).getBody();
		}

//User Operation On Reservation Service	
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
