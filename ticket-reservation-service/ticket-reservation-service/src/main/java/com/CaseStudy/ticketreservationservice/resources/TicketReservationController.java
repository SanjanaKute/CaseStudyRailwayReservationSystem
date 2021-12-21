package com.CaseStudy.ticketreservationservice.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.CaseStudy.ticketreservationservice.model.TicketReservation;
import com.CaseStudy.ticketreservationservice.repository.TicketReservationRepository;
import com.CaseStudy.ticketreservationservice.service.TicketReservationService;

@RestController
@RequestMapping("/reserve")
public class TicketReservationController {
	
	
	@Autowired
	TicketReservationService service;
	@Autowired
	RestTemplate restTemplate;
	@RequestMapping("/ticketreservation")
	public String hello()
	{
		return "Hello from Ticket Reservation service";
	}
	@PostMapping("/addTicket")
	public TicketReservation saveTicket1(@RequestBody TicketReservation reserve) 
	{
		return service.addReservation(reserve);
		//return "Reserved ticket with id :  " + reserve.getId();
    }
	
	@GetMapping("/getAllTicketsDetails")
	public List<TicketReservation> getTicketsDetails()
	{
		return service.getTicketsDetails();	}
	
	@GetMapping("/{id}")
	public Optional<TicketReservation> getTickets(@PathVariable String id)
	{
		return service.findById(id);
	}
	
	
	@PutMapping("/update/{id}")
	public TicketReservation updateTicket(@RequestBody TicketReservation order ) 
	{
		return service.update(order);
	}
		
	 @DeleteMapping("/del/{id}")
	 public String deleteOrder (@PathVariable("id") String id)
	 {
		 service.deleteTicketById(id);
         return "Ticket deleted with id : "+service.deleteTicketById(id);
	}
}
