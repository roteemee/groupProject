package com.fdmgroup.corona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.repos.Trade;
import com.fdmgroup.repos.TradeRep;

@Service
public class TradesDAO {
	@Autowired
	private TradeRep repp;

	Optional<Trade> trade;
	List<Trade> allTrades;

	public void addTrade(Trade trade) {
		trade = repp.findById(trade.getTradeId());
		if (!trade.isPresent()) {

			repp.save(trade);
		}
	}

	public Trade getTrade(int tradeId) {
		trade = repp.findById(tradeId);
		if (trade.isPresent()) {

			return trade.get();
		} else {
			System.out.print("No trade whith this id therefore is ");
			return null;
		}
	}

	public void removeTrade(int tradeId) {
		trade = repp.findById(tradeId);

		if (trade.isPresent()) {
			repp.delete(trade.get());
			System.out.println("trade removed");
		} else {
			System.out.println("No trade under this id");
		}

	}

	public void updateTrade(Trade trade) {

		trade = repp.findById(trade.getTradeId());
		if (trade.isPresent()) {
			repp.save(trade.get());
			System.out.println("trade changed");
		}
	}

	public List<Trade> listTrades() {
		allTrades = repp.findAll();

		return allTrades;
	}

	public TradesDAO() {

	}

	
}
