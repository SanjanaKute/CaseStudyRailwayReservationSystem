package com.CaseStudy.loginservice.HelloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@RequestMapping("/login")
	public String hello()
	{
		return "Hello World";
	}
	
	
}
