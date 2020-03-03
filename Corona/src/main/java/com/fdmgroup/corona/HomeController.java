package com.fdmgroup.corona;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userName")
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
	
	@GetMapping("/ViewShares")
	public String viewShares() {
		return "ViewShares";
	}
	
	@GetMapping("/Shareholder")
	public String shareholder() {
		return "Shareholder";
	}
	
	@GetMapping("/Broker")
	public String broker() {
		return "Broker";
	}
	
	@GetMapping("/BrokerRequestPage")
	public String brokerRequestPage() {
		return "BrokerRequestPage";
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
