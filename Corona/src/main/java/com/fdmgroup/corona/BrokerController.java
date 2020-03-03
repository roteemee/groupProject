package com.fdmgroup.corona;

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

	private final TradeRep tradeRepository;
	@Autowired
	public BrokerController(TradeRep tradeRepository) {
		this.tradeRepository = tradeRepository;
	}
	
	@GetMapping("tradeList")
	public String showUpdateForm(Model model) {
		
		model.addAttribute("trades", tradeRepository.findAll());
		return "BrokerTradePage";
	}
}
