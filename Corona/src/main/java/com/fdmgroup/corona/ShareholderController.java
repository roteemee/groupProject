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
import com.fdmgroup.entities.Sysadmin;
import com.fdmgroup.entities.UserFactory;
import com.fdmgroup.entities.UserRequest;
import com.fdmgroup.entities.Wallet;
import com.fdmgroup.repos.WalletRep;

@Controller
@SessionAttributes("userName")

public class ShareholderController {

	@Autowired
	ShareholderDAO shserve = new ShareholderDAO();
	
	@Autowired
	private WalletRep wallrep;
	
	@GetMapping("/ViewShares")
	public String viewShares() {
		return "ViewShares";
	}
	
	@GetMapping("/ShareholderTransactions")
	public String viewTransactions() {
		return "ShareholderTransactions";
	}
	
	@GetMapping("/ViewPortfolio")
	public String viewPortfolio() {
		return "ViewPortfolio";
	}
}
