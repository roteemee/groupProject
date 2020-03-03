package com.fdmgroup.corona;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// general page
	@GetMapping("/home")
	public String doWork() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/helloAdmin")
	public String helloAdmin() {
		return "helloAdmin";
	}
	
	
	//user 
	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}
	
	
	
	@GetMapping("/manageUser")
	public String manageUser() {
		return "manageUser";
	}
	
	
}
