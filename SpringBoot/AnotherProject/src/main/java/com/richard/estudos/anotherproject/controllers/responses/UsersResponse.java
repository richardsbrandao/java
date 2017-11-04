package com.richard.estudos.anotherproject.controllers.responses;

import java.util.ArrayList;

import lombok.NoArgsConstructor;

import com.richard.estudos.anotherproject.models.User;

@SuppressWarnings("serial")
@NoArgsConstructor
public class UsersResponse extends ArrayList<UserResponse> {

	public UsersResponse(Iterable<User> users) {
		if( users != null )
			users.forEach(user -> add(new UserResponse(user)));
	}

}
