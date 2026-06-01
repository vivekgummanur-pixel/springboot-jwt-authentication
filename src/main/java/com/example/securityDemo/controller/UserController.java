package com.example.securityDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityDemo.model.Users;
import com.example.securityDemo.service.UserService;

@RestController
public class UserController
{
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user)
	{
		Users u=service.register(user);
		return u;
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user)
	{
		
		return service.verify(user);
	}

}
