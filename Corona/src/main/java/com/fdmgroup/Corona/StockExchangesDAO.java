package com.fdmgroup.Corona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StockExchangesDAO {

	@Autowired
	private StockExchangeRep repp;

	Optional<StockExchange> stockExchange;
	List<StockExchange> allStockExchanges;

	public void addStockExchange(StockExchange stockExchange) {
		stockExchange = repp.findById(stockExchange.getStockExchangeId());
		if (!stockExchange.isPresent()) {

			repp.save(stockExchange);
		}
	}

	public StockExchange getStockExchange(int stockExchangeId) {
		stockExchange = repp.findById(stockExchangeId);
		if (stockExchange.isPresent()) {

			return stockExchange.get();
		} else {
			System.out.print("No stockExchange whith this id therefore is ");
			return null;
		}
	}

	public void removeStockExchange(int stockExchangeId) {
		stockExchange = repp.findById(stockExchangeId);

		if (stockExchange.isPresent()) {
			repp.delete(stockExchange.get());
			System.out.println("stockExchange removed");
		} else {
			System.out.println("No stockExchange under this id");
		}

	}

	public void updateStockExchange(StockExchange stockExchange) {

		stockExchange = repp.findById(stockExchange.getStockExchangeId());
		if (stockExchange.isPresent()) {
			repp.save(stockExchange.get());
			System.out.println("stockExchange changed");
		}
	}

	public List<StockExchange> listStockExchanges() {
		allStockExchanges = repp.findAll();

		return allStockExchanges;
	}

	public StockExchangesDAO() {

	}
	
}
