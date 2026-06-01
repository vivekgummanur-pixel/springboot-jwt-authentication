package com.example.securityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{


	@Autowired
	private UserDetailsService userDetailsService;
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity hSec) throws Exception
//	{
//		hSec.csrf(customizer -> customizer.disable());
//		hSec.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//		hSec.formLogin(Customizer.withDefaults());
//		return hSec.build();
//		
//		
////		return hSec.csrf(customizer -> customizer.disable()).
////		authorizeHttpRequests(request -> request.anyRequest().authenticated()).
////		formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()).build();
////		
//		
//	}
	
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http)
	            throws Exception {

		    return http
		            .csrf(csrf -> csrf.disable())

		            .authorizeHttpRequests(auth -> auth
		                    .requestMatchers("/h2-console/**").permitAll().
		                    requestMatchers("/login","/register").permitAll()
		                    .anyRequest().authenticated()
		            )

		            .headers(headers ->
		                    headers.frameOptions(frame -> frame.disable())
		            )

		            
		            .httpBasic(Customizer.withDefaults())
		            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

		            .build();
	    }
	
	
	
	
	@Bean 
	public AuthenticationProvider getAuthenticationProvider()
	{
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setPasswordEncoder(new BCryptPasswordEncoder(12));
		dao.setUserDetailsService(userDetailsService);
		return dao;
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
}
