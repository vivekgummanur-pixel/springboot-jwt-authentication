package com.example.securityDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securityDemo.model.UserPrincipal;
import com.example.securityDemo.model.Users;
import com.example.securityDemo.repo.MyServiceRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MyServiceRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user=repo.findByUsername(username);
		
		if(user == null)
		{
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User Not Found");
		}
		else
		{
		return new UserPrincipal(user);
		}
	}

}
