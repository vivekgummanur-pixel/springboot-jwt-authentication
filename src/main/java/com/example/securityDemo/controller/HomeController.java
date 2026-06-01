package com.example.securityDemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityDemo.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController
{
	List<Student> li= new ArrayList<>(
	        Arrays.asList(
	                new Student(1,"John"),
	                new Student(2,"Mike")
	        )
	);

	
	@RequestMapping("/")
	public String greet(HttpServletRequest req)
	{
		return "Welcome Tiger.........<br>"+req.getSession().getId();
	}
	
	
	@GetMapping("/students")
	public List<Student> getStud()
	{
		return li;
	}
	
	@GetMapping("/csrf")
	public CsrfToken getCSR(HttpServletRequest req)
	{
		return (CsrfToken) req.getAttribute("_csrf");
	}
	
	@PostMapping("/student")
	public Student addStud(@RequestBody Student s)
	{
		li.add(s);
		return s;
	}
}
