package com.fdmgroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserRequest {
@Id
private String userName;
@Column
private String type;

public UserRequest() {
	
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}




}

