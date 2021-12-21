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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.RailwaySecurity.model.TicketReservation;
import com.CaseStudy.RailwaySecurity.model.Trains;
import com.CaseStudy.RailwaySecurity.model.AdminModel;
import com.CaseStudy.RailwaySecurity.repository.AdminRepository;


@Service
public class AdminService implements UserDetailsService {

	@Autowired
	private AdminRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminModel foundedUser=userRepository.findByUsername(username);//find user by username in repo//if not 
		if (foundedUser==null)
		return null;
		String name=foundedUser.getUsername();
		String pwd=foundedUser.getPassword();
		
		return new User(name, pwd, new ArrayList<>()); //this User is for securitycore
		
	}

	@Autowired
	RestTemplate restTemplate;
	 public AdminService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	 }
	 
	 
	//admin Operation On Train Service
		
	//------------Adding Train information--------------------------------
			public String add(Trains trains) 
			{
				HttpEntity<Trains> entity=new HttpEntity<>(trains);
				return restTemplate.exchange("http://localhost:8002/train-service/train/addTrain", HttpMethod.POST, entity,String.class).getBody();
			}

	//------------Displaying Train information--------------------------------

			
			public List<Trains> displayAll() {
				return restTemplate.exchange("http://localhost:8002/train/showAllTrains", HttpMethod.GET, null,List.class).getBody();
			}

	//------------------------startstation---------------------------------------------------------
			@RequestMapping("/{startStation}")
			public List<Trains> SearchByStartLocation(@PathVariable("startStation")String startStation) {
				
				return restTemplate.exchange("http://localhost:8002/train/findtrainByStartStation/"+startStation, HttpMethod.GET, null, List.class).getBody();
			}
	//--------------------------------Endstation------------------------------------------------------	
			
			@RequestMapping("/{endStation}")
			public List<Trains> searchTrainByEndLocation(String endStation) {
				
				return restTemplate.exchange("http://localhost:8002/train/findtrainByEndStation/"+endStation, HttpMethod.GET, null, List.class).getBody();
			}
	//----------------------------Delete-------------------------
			@DeleteMapping("/{trainid}")
			public String deleteTrain(String trainid) {
				
				return restTemplate.exchange("http://localhost:8002/train/delete/"+trainid, HttpMethod.DELETE, null, String.class).getBody();
			}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
	//resrvation related operations		
			public List<TicketReservation> showReservedTickets() {
				
				return restTemplate.exchange("http://localhost:8003/reserve/getAllTicketsDetails", HttpMethod.GET, null, List.class).getBody();
			}
			
			@DeleteMapping("/{id}")
			public String deleteReservationByAdmin(@PathVariable String id)
			{
				return restTemplate.exchange("http://localhost:8003/reserve/del/"+id, HttpMethod.DELETE, null,String.class).getBody();
				
			}
			

}
