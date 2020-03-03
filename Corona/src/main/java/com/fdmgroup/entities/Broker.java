package com.fdmgroup.entities;

import javax.persistence.Entity;

@Entity(name="brokers")
public class Broker extends User{
	
	//Broker b = new Broker();
	
	protected void acceptRequest() {
		
	}
	
	protected void denyRequest() {
		
	}
	
	protected void updateFee() {
		
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
