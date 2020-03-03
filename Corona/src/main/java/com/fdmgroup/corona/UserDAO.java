package com.fdmgroup.corona;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.entities.User;
import com.fdmgroup.repos.UserRep;

public class UserDAO {
	@Autowired
	private UserRep repp;

	Optional<User> users;
	List<User> allUsers;

	public void addUser(User user) {
		users = repp.findById(user.getUserId());
		if (!users.isPresent()) {

			repp.save(user);
		}
	}

	public User getUser(int userId) {
		users = repp.findById(userId);
		if (users.isPresent()) {

			return users.get();
		} else {
			System.out.print("No users whith this id therefore is ");
			return null;
		}
	}

	public void removeUser(int userId) {
		users = repp.findById(userId);

		if (users.isPresent()) {
			repp.delete(users.get());
			System.out.println("user removed");
		} else {
			System.out.println("No users under this id");
		}

	}

	public void updateUser(User user) {

		users = repp.findById(user.getUserId());
		if (users.isPresent()) {
			repp.save(user);
			System.out.println("user changed");
		}
	}

	public List<User> listUsers() {
		allUsers = repp.findAll();

		return allUsers;
	}

	public UserDAO() {

	}

}
