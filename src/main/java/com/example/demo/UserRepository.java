package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserMaster,String>{
	UserMaster findByEmailId(String emailId);
}
