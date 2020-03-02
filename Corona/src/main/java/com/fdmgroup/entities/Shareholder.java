package com.fdmgroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="shareholders")
public class Shareholder extends User{
	
	@Column(name="balance")
	private double balance;
	
	protected double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void requestTrade() {
		
		// In here goes code for sending a trade request
		// ... when we get there :)
		
	}
	
}
