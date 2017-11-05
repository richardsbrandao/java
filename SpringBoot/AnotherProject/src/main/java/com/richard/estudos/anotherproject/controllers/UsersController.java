package com.richard.estudos.anotherproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.richard.estudos.anotherproject.controllers.requests.UserRequest;
import com.richard.estudos.anotherproject.controllers.responses.UserResponse;
import com.richard.estudos.anotherproject.controllers.responses.UsersResponse;
import com.richard.estudos.anotherproject.models.User;
import com.richard.estudos.anotherproject.services.UsersService;

@RestController
@RequestMapping(value="/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	public ResponseEntity<UsersResponse> list() {
		Iterable<User> users = usersService.findAll();
		return new ResponseEntity<UsersResponse>(new UsersResponse(users), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/")
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
		User userModel = request.toModel();
		User user = usersService.save(userModel);
		return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.CREATED);
	}
}
