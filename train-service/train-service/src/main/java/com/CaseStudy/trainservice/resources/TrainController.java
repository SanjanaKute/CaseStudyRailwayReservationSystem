package com.CaseStudy.trainservice.resources;

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

import com.CaseStudy.trainservice.model.Trains;
import com.CaseStudy.trainservice.repository.TrainRepository;
import com.CaseStudy.trainservice.service.TrainService;

@RestController
@RequestMapping("/train")
public class TrainController {
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	TrainService trainService;
	
	@RequestMapping("/train")
	public String hello()
	{
		return "Hello from train Service";
	}
	@PostMapping("/addTrain")
	public String addTrain(@RequestBody Trains trainid) {
	trainRepository.save(trainid);
	return "Added train with id :  " + trainid.getTrainid();
    }
	
	@GetMapping("/showAllTrains")
	public List<Trains> getTrains()
	{
		return trainRepository.findAll();
	}

	@GetMapping("/findtrainById/{trainid}")
	public Optional<Trains> getTrainById(@PathVariable String trainid){
		return trainRepository.findById(trainid);
	}
	
	@GetMapping("/findtrainByStartStation/{startStation}")
	public Optional<Trains> getTrainsByStartStation(@PathVariable String startStation)
	{
		return trainRepository.findByStartStation(startStation);
	}

	@GetMapping("/findtrainByEndStation/{endStation}")
	public List<Trains> getTrainsByEndStation(@PathVariable String endStation)
	{
		return trainRepository.findByEndStation(endStation);
	}

	@DeleteMapping("/delete/{trainid}")
	public String deleteTrain (@PathVariable String trainid) {
		trainRepository.deleteById(trainid);
		return "Train deleted with id : "+trainid;
    }
	@PutMapping("/update/{trainid}")
	public Trains updateTrain(@PathVariable("trainid") String trainid,@RequestBody Trains t ) {
		t.setTrainid(trainid);
		trainRepository.save(t);
		return t;

	}
}