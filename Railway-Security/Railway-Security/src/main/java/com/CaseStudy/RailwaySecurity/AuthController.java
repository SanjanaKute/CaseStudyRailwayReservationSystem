package com.CaseStudy.RailwaySecurity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.RailwaySecurity.model.AuthenticationRequest;
import com.CaseStudy.RailwaySecurity.model.AuthenticationResponse;
import com.CaseStudy.RailwaySecurity.model.TicketReservation;
import com.CaseStudy.RailwaySecurity.model.Trains;
import com.CaseStudy.RailwaySecurity.model.AdminModel;
import com.CaseStudy.RailwaySecurity.repository.AdminRepository;
import com.CaseStudy.RailwaySecurity.services.AdminService;
import com.CaseStudy.RailwaySecurity.utils.JwtUtils;


@RestController
public class AuthController {
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AdminService userService;
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	RestTemplate restTemplate;
	AdminService admin;
	@Autowired
	public AuthController(AdminService admin) {
		this.admin= admin;
	}
	
//train operations
	@PostMapping("/add")
	
	public String addUser(@RequestBody Trains trains)
	{
		return "Admin Added train with: "+admin.add(trains);
	}

	@GetMapping("/")
	public List<Trains> display() 
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
	
//reservation operations
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
	
	
	@GetMapping("/dashboard")
	private String testingToken()
	{
		return "Welcome admin " +SecurityContextHolder.getContext().getAuthentication().getName(); //from jwtfilterresquest
	}
	
	@PostMapping("/subs")
	private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest)
	{
		String username =authenticationRequest.getUsername();
		String password =authenticationRequest.getPassword();
		AdminModel userModel=new AdminModel();
		userModel.setUsername(username);
		userModel.setPassword(password);
		try {
			
			adminRepository.save(userModel);
		} catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during Admin Subscription: "+username));
		}
	
		return ResponseEntity.ok(new AuthenticationResponse("Succesfull sing-in as admin: "+username));
	}
	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest)
	{
		String username =authenticationRequest.getUsername();
		String password =authenticationRequest.getPassword();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (Exception e) //badCrendtiablexception
		{
			return ResponseEntity.ok(new AuthenticationResponse("Error during admin authentication: "+username));
		}		
		//loading user from database
		UserDetails loadedUser=userService.loadUserByUsername(username);
		String generatedToken = jwtUtils.generateToken(loadedUser);
		
		return ResponseEntity.ok(new AuthenticationResponse(generatedToken));
//		String generatedToken=jwtUtils.generateToken(loadedUser);
//		return ResponseEntity.ok(new AuthenticationResponse("Succesfull authentication of user: "+username));
	}

	
}
