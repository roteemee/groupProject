package com.fdmgroup.entities;

import javax.persistence.Entity;

@Entity(name="brokers")
public class Broker extends User{
	
	Broker b = new Broker();
	
	protected void acceptRequest() {
		
	}
	
	protected void denyRequest() {
		
	}
	
	protected void updateFee() {
		
	}
	
	protected int getBrokerId() {
		return b.getUserId();
	}
	
}
