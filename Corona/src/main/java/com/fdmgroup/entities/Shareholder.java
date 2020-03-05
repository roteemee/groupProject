package com.fdmgroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



@Entity(name="shareholders")
public class Shareholder extends User{
	
	@OneToOne
	@JoinColumn(name="wallet_id")
	private Wallet wallet; // Give shareholder a wallet
	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	
	
	public void requestTrade() {
		
		// In here goes code for sending a trade request
		// ... when we get there :)
		
	}

	@Override
	public int getUserId() {
		// TODO Auto-generated method stub
		return super.getUserId();
	}

	@Override
	public void setUserId(int userId) {
		// TODO Auto-generated method stub
		super.setUserId(userId);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return super.getCountry();
	}

	@Override
	public void setCountry(String country) {
		// TODO Auto-generated method stub
		super.setCountry(country);
	}
	
	
	
}
