package com.fdmgroup.corona;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.entities.Broker;
import com.fdmgroup.entities.Shareholder;

@Controller
@SessionAttributes("userName")
public class HomeController {

	BrokerDAO bserve = new BrokerDAO();
	ShareholderDAO shserve = new ShareholderDAO();

	// general page stuff
	@GetMapping("/")
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

	// user
	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}

	@GetMapping("/manageUser")
	public String manageUser() {
		return "manageUser";
	}

	
	@GetMapping("/Wallet")
	public String Wallet() {
		return "Wallet";
	}


	@PostMapping("/addBroker")
	public String addBroker(@RequestParam String userid, String username, String usercountry) {
		Broker broker = new Broker();
		broker.setUserId(Integer.parseInt(userid));
		broker.setName(username);
		broker.setCountry(usercountry);
		this.bserve.addBroker(broker);
		return "/home";
	}

	@PostMapping("/addShareholder")
	public String addShareholder(@RequestParam String userid, String username, String usercountry) {
		Shareholder shareholder = new Shareholder();
		shareholder.setUserId(Integer.parseInt(userid));
		shareholder.setName(username);
		shareholder.setCountry(usercountry);
		this.shserve.addShareholder(shareholder);
		return "/home";
	}


}
