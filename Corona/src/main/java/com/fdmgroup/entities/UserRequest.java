package com.fdmgroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRequest {
	@Id
	private String userName;
	@Column(name = "user_type")
	private int userType;

	public UserRequest() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getuserType() {
		return userType;
	}

	public void setType(int userType) {
		this.userType = userType;
	}

}
