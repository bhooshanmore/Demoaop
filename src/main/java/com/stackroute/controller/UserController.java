package com.stackroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.dao.UserDAO;
import com.stackroute.model.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserDAO userDAO;

	@GetMapping("/")
	public String homepage(){
		return "Checking Aspect !!";
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers() {
		return new ResponseEntity<List<User>>(userDAO.list(), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("username") String username) throws Exception {
		
		/*
		User user = userDAO.get(username);
		if (user == null) {
			
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
		*/
		
		throw new Exception();
		
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User u = userDAO.get(user.getUsername());
		if (u != null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}

		userDAO.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	
	@PutMapping(value = "/{username}")
	public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user) {
		User currentUser = userDAO.get(username);

		if (currentUser == null) {

			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setPassword(user.getPassword());

		userDAO.update(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

}
