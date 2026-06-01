package com.example.securityDemo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securityDemo.model.Users;
import com.example.securityDemo.repo.MyServiceRepo;

@Service
public class UserService 
{

	private BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder(12);
	@Autowired
	private MyServiceRepo repo;
	
	@Autowired
	private JWTService jwt;
	
	@Autowired
	AuthenticationManager am;
	
	public Users register(Users user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		return repo.save(user);
	}

	public String verify(Users user) {
		 Authentication auth = am.authenticate(
		            new UsernamePasswordAuthenticationToken(
		                    user.getUsername(),
		                    user.getPassword()
		            )
		    );
		 
		 if(auth.isAuthenticated()) return jwt.generateToken(user.getUsername());
		
		return "failure";
	}
   
}
