package com.CaseStudy.adminservice.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.CaseStudy.adminservice.model.Admin;

public interface AdminRepository extends MongoRepository<Admin, String>{

	

}
