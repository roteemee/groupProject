package com.fdmgroup.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="users")
public abstract class User {

	@Id
	@Column(name="user_id")
	private int userId;
	@Column(length=50)
	private String name;
	@Column(length=50)
	private String country;
	
	
	
	
	protected User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	protected User(int userId, String name, String country) {
		super();
		this.userId = userId;
		this.name = name;
		this.country = country;
	}
	
	
	
	protected int getUserId() {
		return userId;
	}
	protected void setUserId(int userId) {
		this.userId = userId;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getCountry() {
		return country;
	}
	protected void setCountry(String country) {
		this.country = country;
	}
	
	
	
}
