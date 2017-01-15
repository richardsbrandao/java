package com.richard.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginService implements UserDetailsService {

	private Map<String, com.richard.entity.User> fakeDb;
	
	public LoginService() {
		fakeDb = new HashMap<String, com.richard.entity.User>();
		fakeDb.put("admin",   new com.richard.entity.User( "admin", "admin", true, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")) ));
		fakeDb.put("richard", new com.richard.entity.User( "richard", "richard", true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")) ));
		fakeDb.put("ralph",   new com.richard.entity.User( "ralph", "ralph", true, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")) ));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.richard.entity.User user = fakeDb.get(username);
		if( fakeDb.get(username) == null ) {
			return null;
		}
		
		return new User(user.getLogin(), user.getPassword(), user.getActive(), true, true, true, user.getRoles());
	}

}
