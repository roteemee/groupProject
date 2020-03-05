package com.fdmgroup.corona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.entities.Trade;






@Controller
@SessionAttributes("userName")
public class BrokerController {

	@Autowired
	TradeDAO tradeserv;
	
	public BrokerController(TradeDAO tradeserv) {
		this.tradeserv = tradeserv;
	}
	
	@GetMapping("tradeList")
	public String showTradeList(Model model) {

		Trade t  = new Trade();
		t.setTradeId(155);
		tradeserv.addTrade(t);
		model.addAttribute("trades", tradeserv.listTrades());
		return "BrokerTradePage";
	}
}
