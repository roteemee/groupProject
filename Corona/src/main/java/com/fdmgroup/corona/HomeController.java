package com.fdmgroup.corona;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

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
import com.fdmgroup.entities.Sysadmin;
import com.fdmgroup.entities.UserFactory;
import com.fdmgroup.entities.UserRequest;
import com.fdmgroup.entities.Wallet;

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
	private WalletDAO walldao = new WalletDAO();
	@Autowired
	private ShareDAO shdao = new ShareDAO();
	@Autowired
	private SysAdminDAO sydao = new SysAdminDAO();

	
	@ModelAttribute("userName")
	private BasicUser usermaking() {
		return new BasicUser();
	}

	// general page stuff
	@GetMapping("/")
	public String doWork() {
		return "home";
	}

	@GetMapping("/home")
	public String doHome() {
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/pageRedirect")
	public String pageRedirect(@ModelAttribute(name = "userName") BasicUser user) {
		BasicUser bu = buserve.getBasicUser(user.getUsername());
		System.out.println("object type is:" + bu.getClass().getName());

		String page = bu.pageRedirect();
		System.out.println("page is:" + page);
		return page;
	}

	@GetMapping("/register")
	public String register(Model model) {
		Set s = EnumSet.allOf(UserType.class);
		model.addAttribute("enums", s);
		
		return "register";
	}

	@PostMapping("/registerNewUser")

	public String registerNewUser(@ModelAttribute(name = "userRequest") UserRequest ur) {

		if (rserve.listUserRequests().contains(ur)) {
			return "invalidUsername";
		} else {
			rserve.addUserRequest(ur);
			System.out.println("request sent!");
			return "waitForApproval";

		}

	}
	/*
	 * public String registerNewUser(@ModelAttribute(name = "basicUser") BasicUser
	 * bu) { bu.setUserType(0); buserve.addBasicUser(bu); return "ToSendingRequest";
	 * }
	 */

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
	public String loggedInPage(@RequestParam() String username, @RequestParam String password,
			@ModelAttribute(name = "userName") BasicUser user, Model model) {
		Sysadmin userFromDatabase = sydao.getSysadmin(username);
		if (userFromDatabase == null) {
			return "home";
		}

		else if (!userFromDatabase.getPassword().equals(password)) {
			return "acessDenied";
		} else {
			List<UserRequest> allUserRequest = urd.listUserRequests();
			model.addAttribute("username", allUserRequest);
			return "ViewUserRequest";
		}

	}

	// user
	@GetMapping("/ViewUserRequest")
	public String addUser(Model model) {

		List<UserRequest> allUserRequest = urd.listUserRequests();

		model.addAttribute("username", allUserRequest);

		return "ViewUserRequest";
	}

	// user
	@PostMapping("/UserRequestResult")
	public String userRequestResult(@RequestParam String[] cb, Model model) {
		List<UserRequest> approvedUserRequest = new ArrayList<UserRequest>();
		for (String i : cb) {
			System.out.println(i);
			UserRequest userRequestObtainedFromDatabase = urd.getUserRequest(i); // Getting the users and user request
																					// objects from the database

			buserve.removeBasicUser(i);
			BasicUser bu = UserFactory.factory(userRequestObtainedFromDatabase.getUserType()); // Changing the user type
																								// with the type from
																								// request received from
																								// database

			bu.setName(userRequestObtainedFromDatabase.getName());
			bu.setUsername(userRequestObtainedFromDatabase.getUserName());
			bu.setPassword(userRequestObtainedFromDatabase.getPassword());
			bu.setCountry(userRequestObtainedFromDatabase.getCountry());

			bu.initialising();
			buserve.addBasicUser(bu);

			approvedUserRequest.add(userRequestObtainedFromDatabase); // Temporarily displaying the users added on the
																		// page therefore a list

			urd.removeUserRequest(i); // Removing approved request

			// Shareholder sh = shserve.getShareholder(i);
			// if ( sh != null) {
			// Share share = new Share(); //Add Shares to share holder use it as you like
			// sh.addShares(share, 2);
			// System.out.println("share added");
			// shdao.addShare(share);
			// shserve.updateShareholder(sh);
			// }
		}
		shserve.listShareholders().forEach(a -> {
			Wallet w = new Wallet();
			w.setBudget(0);
			if (a.getWallet()==null) {
				a.setWallet(w);
				walldao.addWallet(w);
				shserve.updateShareholder(a);
			}
		});
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
	
	@GetMapping("/ViewPortfolio")
	public String viewPortfolio() {
		return "ViewPortfolio";
	}
	
	@PostMapping("addToWallet")
	public String addToWallet(@ModelAttribute(name = "userName") Shareholder s, @RequestParam String budget) {

		System.out.println("first line of createWallet method:" + s);

		double addBudget = Double.parseDouble(budget);
		double customerBudget = s.getWallet().getBudget();
		customerBudget = customerBudget + addBudget;
		Wallet w = s.getWallet();
		w.setBudget(customerBudget);
		walldao.addWallet(w);
		return "Wallet";
	}
}
