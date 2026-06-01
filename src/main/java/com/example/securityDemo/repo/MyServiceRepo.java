package com.example.securityDemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityDemo.model.Users;

@Repository
public interface MyServiceRepo extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);

}
