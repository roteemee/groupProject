package com.fdmgroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="sysadmin")
public class Sysadmin extends User {
	
	public void listTrades() {
		
	}
	
	public void listUsers() {
		
	}
	
	public void setFee() {
		
	}
	
	public void updateFee() {
		
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
