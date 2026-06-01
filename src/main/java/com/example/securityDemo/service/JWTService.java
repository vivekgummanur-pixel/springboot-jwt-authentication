package com.example.securityDemo.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JWTService 
{

	@Value("${jwt.secretkey}")
	private String secretKey;
	
	private SecretKey sk;
	
	@PostConstruct
	public void init()
	{
	   sk=Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
	public String generateToken(String username) {
		
		return Jwts.builder().subject(username).issuedAt(new Date()).
				expiration(new Date(System.currentTimeMillis()+1000*60*60)).signWith(sk).compact();
	}

}
