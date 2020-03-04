package com.fdmgroup.corona;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.entities.BasicUser;
import com.fdmgroup.entities.Broker;
import com.fdmgroup.entities.Shareholder;
import com.fdmgroup.entities.UserRequest;

@Controller
@SessionAttributes("userName")
public class HomeController {

	@Autowired
	BasicUserDAO buserve = new BasicUserDAO();
	@Autowired
	BrokerDAO bserve = new BrokerDAO();
	@Autowired
	ShareholderDAO shserve = new ShareholderDAO();
	@Autowired
	UserRequestDAO urd = new UserRequestDAO();
	
	@ModelAttribute("userName")
	private BasicUser usermaking() {
		return new BasicUser();
	}


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
	
	@GetMapping("/registerNewUser")
	public String registerNewUser(@ModelAttribute BasicUser bu) {
		buserve.addBasicUser(bu);
		return "ToSendingRequest";
	}
	@GetMapping("/sendRequest")
	public String sendRequest(@ModelAttribute UserRequest ur) {
		rserve.addUserRequest(ur);
		return "waitForApproval";
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
	
	@GetMapping("/BrokerTradePage")
	public String brokerTradePage() {
		return "BrokerTradePage";
	}

	@GetMapping("/helloAdmin")
	public String helloAdmin() {
		return "helloAdmin";
	}

	UserRequest rq = new UserRequest();
	UserRequest rq1 = new UserRequest();
	UserRequest rq2 = new UserRequest();
		
	
	// user
	@GetMapping("/ViewUserRequest")
	public String addUser(Model model) {
		rq.setType("Broker");
		rq.setUserName("Mark");
		rq1.setType("Admin");
		rq1.setUserName("Tom");
		rq2.setType("Shareholder");
		rq2.setUserName("Ben");
		urd.addUserRequest(rq);
		urd.addUserRequest(rq1);
		urd.addUserRequest(rq2);
		
		List<UserRequest> allUserRequest =  urd.listUserRequests();
		model.addAttribute("username", allUserRequest);

		return "ViewUserRequest";
	}
	// user
	@PostMapping("/UserRequestResult")
	public String userRequestResult(@RequestParam String[] ura) {
		//System.out.println(urd);
		for (String i:ura) {
			System.out.println(i);
			UserRequest lol = urd.getUserRequest(i);
			BasicUser hello = buserve.getBasicUser(i);
			hello.setUserType(lol.getType());
			
		}
		return "ViewUserRequest";
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

	
	@GetMapping("/ShareholderTransactions")
	public String viewTransactions() {
		return "ShareholderTransactions";
	}


}
