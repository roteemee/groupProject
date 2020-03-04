<<<<<<< HEAD
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
=======
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
>>>>>>> 5b8c24157236db7d775661a228d1ea22e0c27bd7
