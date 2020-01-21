package com.fardoushlab.javabean.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fardoushlab.javabean.model.User;


public class UserService {

	private  List<User> users;// = new ArrayList<User>();
	
	 public UserService(ArrayList<User> arrayList) {
		this.users = arrayList;
	}
	
	
	@Override
	public String toString() {
		return "UserService []";
	}

	public User createUser(String userName) {
		var user = new User();
		user.setName(userName);
		
		users.add(user);
		
		return user;
	}
	
	public void removeUserByName(String name) {
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().getName().equals(name)) {
				iterator.remove();
			}
		}
	}
	
	public void showAllUser() {
		
			users.forEach(System.out::print);
	}
}
