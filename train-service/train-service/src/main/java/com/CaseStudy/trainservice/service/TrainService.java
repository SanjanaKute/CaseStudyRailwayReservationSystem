package com.CaseStudy.trainservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CaseStudy.trainservice.model.Trains;
import com.CaseStudy.trainservice.repository.TrainRepository;

@Service
public class TrainService {
	
	@Autowired
	private TrainRepository trainRepository;
	
	public Trains addTrain (Trains train) {
		return trainRepository.save(train);
	}


	public void deleteTrain(Trains train) {
		trainRepository.delete(train);
	}

	public Optional<Trains> getTrainbyId(String id) {
		return trainRepository.findById(id);	}

}