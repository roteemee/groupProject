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
import com.fdmgroup.entities.Wallet;
import com.fdmgroup.entities.Shareholder;
import com.fdmgroup.entities.UserRequest;
import com.fdmgroup.repos.WalletRep;

@Controller
@SessionAttributes("userName")
public class HomeController {

	@Autowired
	BasicUserDAO buserve = new BasicUserDAO();
	@Autowired
	UserRequestDAO rserve = new UserRequestDAO();
	@Autowired
	BrokerDAO bserve = new BrokerDAO();
	@Autowired
	ShareholderDAO shserve = new ShareholderDAO();
	@Autowired
	UserRequestDAO urd = new UserRequestDAO();
	@Autowired 
	private WalletRep wallrep;
	
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
	
	@PostMapping("/registerNewUser")
	public String registerNewUser(@ModelAttribute(name="basicUser") BasicUser bu) {
		boolean check=false;
		bu.setUserType(0);
		for(BasicUser user:buserve.listBasicUsers()) {
			if (user.getUsername().equals(bu.getUsername())) {
				System.out.println("username already exist!");
				check = true;
				return "invalidUsername";
			}
		}
		if(!check) {
			buserve.addBasicUser(bu);
			System.out.println("user added!");
		}
		return "ToSendingRequest";
	}




	@PostMapping("/sendRequest")
	public String sendRequest(@ModelAttribute(name="userRequest") UserRequest ur) {
		boolean check=false;
		for(UserRequest req:rserve.listUserRequests()) {
			if (req.getUserName().equals(ur.getUserName())) {
				check=true;
				return "invalidRequest";
			}
		}
		if(!check) {
			rserve.addUserRequest(ur);
		}
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

	
	@PostMapping("/loggedInPage")
	public String loggedInPage(@RequestParam() String username, @RequestParam String password,Model model){
		BasicUser userFromDatabase = buserve.getBasicUser(username);
		if (userFromDatabase==null) {
			return "home";
		}
		
		else if(!(userFromDatabase.getPassword().equals(password)&&userFromDatabase.getUserType()==1)) {
			return "acessDenied";
		}
		else {
			List<UserRequest> allUserRequest =  urd.listUserRequests();
			model.addAttribute("username", allUserRequest);
			return "ViewUserRequest";
		}
		
	}
	UserRequest rq = new UserRequest();
	UserRequest rq1 = new UserRequest();
	UserRequest rq2 = new UserRequest();
		
	
	// user
	@GetMapping("/ViewUserRequest")
	public String addUser(Model model) {
		rq.setUserType(3);
		rq.setUserName("Mark");
		rq1.setUserType(1);
		rq1.setUserName("Tom");
		rq2.setUserType(2);
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
	public String userRequestResult(@RequestParam String[] ura , Model model) {
		List<UserRequest> approvedUserRequest =  new ArrayList<UserRequest>();
		for (String i:ura) {
			System.out.println(i);

			UserRequest userRequestObtainedFromDatabase = urd.getUserRequest(i);
			BasicUser basicUserObtainedFromDatabase = buserve.getBasicUser(i);
			basicUserObtainedFromDatabase.setUserType(userRequestObtainedFromDatabase.getUserType());
			approvedUserRequest.add(userRequestObtainedFromDatabase);
			buserve.updateBasicUser(basicUserObtainedFromDatabase);
			urd.removeUserRequest(i);
		}
		List<UserRequest> allUserRequest =  urd.listUserRequests();
		model.addAttribute("username", allUserRequest);
		model.addAttribute("approvedUsername", approvedUserRequest);
		
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
		Wallet blankWallet = new Wallet(0.0);
		shareholder.setUserId(Integer.parseInt(userid));
		shareholder.setName(username);
		shareholder.setCountry(usercountry);
		shareholder.setWallet(blankWallet);
		this.shserve.addShareholder(shareholder);
		return "/home";
	}
	@GetMapping("/ShareholderTransactions")
	public String viewTransactions() {
		return "ShareholderTransactions";
	}

	@PostMapping("addToWallet")
	public String addToWallet(@ModelAttribute(name="userName") Shareholder s, @RequestParam String budget) {
		
		System.out.println("first line of createWallet method:" + s);
		
		double addBudget = Double.parseDouble(budget);
		double customerBudget = s.getWallet().getBudget();
		customerBudget = customerBudget + addBudget;
		Wallet w = s.getWallet();
		w.setBudget(customerBudget);
		wallrep.save(w);
		return "Wallet";
	}
}


