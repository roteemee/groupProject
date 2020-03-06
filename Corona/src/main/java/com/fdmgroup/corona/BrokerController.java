package com.fdmgroup.corona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.entities.BasicUser;
import com.fdmgroup.entities.Broker;



import com.fdmgroup.entities.Trade;


import com.fdmgroup.repos.TradeRep;


@Controller
@SessionAttributes("userName")
public class BrokerController {

	@Autowired
	TradeDAO tradeserv = new TradeDAO() ;

	@Autowired
	ShareDAO shareserv = new ShareDAO();

	
	Trade t1 = new Trade();
	Trade t2 = new Trade();
	Trade t3 = new Trade();
	Trade t4 = new Trade();

	
	@GetMapping("tradeList")
	public String showTradeList(@ModelAttribute(name = "userName")BasicUser userName, Model model) {

		model.addAttribute("trades", tradeserv.listTrades());
		return "BrokerTradePage";
	}
	@GetMapping("shareReqList")
	public String showShareReqList(Model model) {
		
		
		return "BrokerRequestPage";
	}

}
