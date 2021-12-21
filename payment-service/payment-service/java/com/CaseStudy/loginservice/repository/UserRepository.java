package com.CaseStudy.loginservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CaseStudy.loginservice.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {
	

}
