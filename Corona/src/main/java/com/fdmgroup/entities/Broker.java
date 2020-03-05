package com.fdmgroup.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="brokers")
@PrimaryKeyJoinColumn(name = "username")
public class Broker extends BasicUser{
	
	
	
	protected void acceptRequest() {
		
	}
	
	protected void denyRequest() {
		
	}
	
	protected void updateFee() {
		
	}

	
	
	




}
