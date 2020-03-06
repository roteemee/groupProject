package com.fdmgroup.corona;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.SessionAttributes;


import com.fdmgroup.entities.Trade;


import com.fdmgroup.repos.TradeRep;



@Controller
@SessionAttributes("userName")
public class BrokerController {

	@Autowired

	TradeDAO tradeserv = new TradeDAO() ;
	
	Trade t1 = new Trade();
	Trade t2 = new Trade();
	Trade t3 = new Trade();
	Trade t4 = new Trade();
	
	

	private final TradeRep tradeRepository;
	public BrokerController(TradeRep tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	
	@GetMapping("tradeList")
	public String showTradeList(Model model) {
t1.setPriceTotal(24);
t2.setPriceTotal(24);
t3.setPriceTotal(24);
t4.setPriceTotal(24);

tradeserv.addTrade(t1);
tradeserv.addTrade(t2);
tradeserv.addTrade(t3);
tradeserv.addTrade(t4);
		model.addAttribute("trades", tradeserv.listTrades());
		return "BrokerTradePage";
	}
}
