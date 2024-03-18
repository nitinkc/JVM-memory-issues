package com.memory.service.efficientcode;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RandomExampleService {

	public static void addUser(User user) {
		
		new User();
		
		List<User> users = new ArrayList<>();		
		users.clear();
		
		int value = 0;
		for (int counter = 1; counter <= 11; ++counter) {
			
			++value;
			users.add(user);
		}
		
		System.out.println(value);
	}
	
	public static void main(String args[]) {

		addUser(null);
	}
}
