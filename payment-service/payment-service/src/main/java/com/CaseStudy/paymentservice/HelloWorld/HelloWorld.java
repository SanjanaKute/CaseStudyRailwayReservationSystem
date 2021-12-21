package com.CaseStudy.paymentservice.HelloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	@RequestMapping("/payment")
	public String hello()
	{
		return "Hello from Payment service";
	}
}
