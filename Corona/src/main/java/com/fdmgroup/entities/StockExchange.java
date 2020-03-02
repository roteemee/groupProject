package com.fdmgroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity (name = "stock_exchanges")
public class StockExchange {
	
	@Id
	@Column
	@GeneratedValue
	private int stockExId;
	@Column
	private String stockExName;
	@Column
	private String currency;
	public int getStockExId() {
		return stockExId;
	}
	public void setStockExId(int stockExId) {
		this.stockExId = stockExId;
	}
	public String getStockExName() {
		return stockExName;
	}
	public void setStockExName(String stockExName) {
		this.stockExName = stockExName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public StockExchange(String stockExName, String currency) {
		super();
		this.stockExName = stockExName;
		this.currency = currency;
	}
	public StockExchange() {
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + stockExId;
		result = prime * result + ((stockExName == null) ? 0 : stockExName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockExchange other = (StockExchange) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (stockExId != other.stockExId)
			return false;
		if (stockExName == null) {
			if (other.stockExName != null)
				return false;
		} else if (!stockExName.equals(other.stockExName))
			return false;
		return true;
	}

}
