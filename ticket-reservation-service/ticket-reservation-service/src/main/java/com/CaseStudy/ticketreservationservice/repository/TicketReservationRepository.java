package com.CaseStudy.ticketreservationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CaseStudy.ticketreservationservice.model.TicketReservation;

public interface TicketReservationRepository extends MongoRepository<TicketReservation, String> {

}
