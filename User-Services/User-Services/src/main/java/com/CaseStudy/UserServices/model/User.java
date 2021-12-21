package com.CaseStudy.UserServices.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")

public class User {
	
	@Id
	private String userId;
	private String name;
	private String contactNo;
	private String age;
	private String email;

	public User(String userId, String name, String contactNo, String age, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.contactNo = contactNo;
		this.age = age;
		this.email = email;
	}
	
	public User() {
		
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", contactNo=" + contactNo + ", age=" + age + ", email="
				+ email + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
