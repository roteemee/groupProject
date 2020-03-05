package com.fdmgroup.entities;

public class UserFactory {

	public UserFactory() {

	}

	public static BasicUser factory(int type) {
		if (type == 1)
			return new Sysadmin();
		else if (type == 2)
			return new Broker();
		else if (type == 3)
			return new Shareholder();

		return null;
	}
}