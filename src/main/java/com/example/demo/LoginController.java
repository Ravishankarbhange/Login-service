package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login/user")
public class LoginController {
	@Autowired
	private UserRepository userrepo;
	
	@PostMapping("/signup")
    public UserMaster signup(@RequestBody UserMaster user) {
		return userrepo.save(user);
    }
	//----
	@PostMapping("/login")
	public UserMaster login(@RequestBody UserMaster user) {
		UserMaster u=userrepo.findByEmailId(user.getEmailId());
		System.out.println(user.getEmailId());
		System.out.println(u);
		System.out.println(u.getUserId());
    	if(u!=null && u.getPassword().equals(user.getPassword())) {
    		return u;
    	}else {
    		return user;
    	}
	}
	
	@GetMapping("/userid/{userId}")
	public Optional<UserMaster> getUserDetails(@PathVariable String userId){
		return userrepo.findById(userId);
	}
	
	@GetMapping("/remove/{userId}")
    public boolean removeUserDetail(@PathVariable String userId) {
    	Optional<UserMaster> user=this.getUserDetails(userId);
    	if(user.isPresent()){
        	userrepo.delete(user.get());
        	return true;
    	}else {    		
    		return false;
    	}
    }
}
