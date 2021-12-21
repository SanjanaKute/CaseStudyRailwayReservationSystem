package com.CaseStudy.UserServices.model;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reservation")
public class TicketReservation 
{
	
	@Id 
	public String id;
	public String noOfTickets;
	public Collection<Trains> train;
	public TicketReservation(String id, String noOfTickets, Collection<Trains> train) {
		super();
		this.id = id;
		this.noOfTickets = noOfTickets;
		this.train = train;
	}
	public TicketReservation() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(String noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public Collection<Trains> getTrain() {
		return train;
	}
	public void setTrain(Collection<Trains> train) {
		this.train = train;
	}
	@Override
	public String toString() {
		return "TicketReservation [id=" + id + ", noOfTickets=" + noOfTickets + ", train=" + train + "]";
	}
	
	
	
}
