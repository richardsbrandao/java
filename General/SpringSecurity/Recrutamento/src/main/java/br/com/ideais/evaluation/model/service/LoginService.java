package br.com.ideais.evaluation.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginService implements UserDetailsService {

	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		br.com.ideais.evaluation.model.entity.User user = userService.findByName(userName);
		if(user == null) {
			return null;
		}
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		return new User(user.getName(), user.getPassword(), true, true, true, true, roles);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
