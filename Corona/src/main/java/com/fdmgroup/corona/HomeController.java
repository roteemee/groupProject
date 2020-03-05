package com.fdmgroup.corona;

import java.util.ArrayList;
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
import com.fdmgroup.entities.Share;
import com.fdmgroup.entities.Shareholder;
import com.fdmgroup.entities.UserFactory;
import com.fdmgroup.entities.UserRequest;
import com.fdmgroup.entities.Wallet;
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
	@Autowired
	private ShareDAO shdao = new ShareDAO();

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
	public String registerNewUser(@ModelAttribute(name = "basicUser") BasicUser bu) {
		bu.setUserType(0);
		buserve.addBasicUser(bu);
		return "ToSendingRequest";
	}

	@PostMapping("/sendRequest")
	public String sendRequest(@ModelAttribute(name = "userRequest") UserRequest ur) {
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

	@PostMapping("/loggedInPage")
	public String loggedInPage(@RequestParam() String username, @RequestParam String password,@ModelAttribute(name="userName") BasicUser user, Model model) {
		BasicUser userFromDatabase = buserve.getBasicUser(username);
		if (userFromDatabase == null) {
			return "home";
		}

		else if (!(userFromDatabase.getPassword().equals(password) && userFromDatabase.getUserType() == 1)) {
			return "acessDenied";
		} else {
			List<UserRequest> allUserRequest = urd.listUserRequests();
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

		List<UserRequest> allUserRequest = urd.listUserRequests();

		model.addAttribute("username", allUserRequest);

		return "ViewUserRequest";
	}
protected final int sysadmin = 1;
protected final int broker = 2;
protected final int shareholder = 3;


	// user
	@PostMapping("/UserRequestResult")
	public String userRequestResult(@RequestParam String[] cb, Model model) {
		List<UserRequest> approvedUserRequest = new ArrayList<UserRequest>();
		for (String i : cb) {
			System.out.println(i);
			UserRequest userRequestObtainedFromDatabase = urd.getUserRequest(i);                                  // Getting the users and user request objects from the database
			BasicUser basicUserObtainedFromDatabase = buserve.getBasicUser(i);
			buserve.removeBasicUser(i);
			basicUserObtainedFromDatabase.setUserType(userRequestObtainedFromDatabase.getUserType());             // Changing the user type with the type from request received from database
			
				 BasicUser bu = UserFactory.factory(userRequestObtainedFromDatabase.getUserType());
			//	 bu.setName(basicUserObtainedFromDatabase.getName());
				 bu.setUsername(basicUserObtainedFromDatabase.getUsername());
				 bu.setPassword(basicUserObtainedFromDatabase.getPassword());
			//	 bu.setCountry(basicUserObtainedFromDatabase.getCountry());
				 buserve.addBasicUser(bu);
				
			
			approvedUserRequest.add(userRequestObtainedFromDatabase);                                             // Temporarily displaying the users added on the page therefore a list
			//buserve.updateBasicUser(basicUserObtainedFromDatabase);
			urd.removeUserRequest(i);      // Removing approved request
//			Shareholder sh = shserve.getShareholder(i);
//			if ( sh != null) {
//				Share share =  new Share();                                         //Add Shares to share holder use it as you like
//				sh.addShares(share, 2);
//				System.out.println("share added");
//				shdao.addShare(share);
//				shserve.updateShareholder(sh);
//			}
		}
		List<UserRequest> allUserRequest = urd.listUserRequests();
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
		broker.setUsername(userid);
		broker.setName(username);
		broker.setCountry(usercountry);
		this.bserve.addBroker(broker);
		return "/home";
	}

	@PostMapping("/addShareholder")
	public String addShareholder(@RequestParam String userid, String username, String usercountry) {
		Shareholder shareholder = new Shareholder();
		Wallet blankWallet = new Wallet(0.0);
		shareholder.setUsername(userid);
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
	public String addToWallet(@ModelAttribute(name = "userName") Shareholder s, @RequestParam String budget) {

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
