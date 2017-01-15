package com.richard.entity;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class User {

	private String login;
	private String password;
	private Boolean active;
	private List<SimpleGrantedAuthority> roles;
	
	public User(String login, String password, boolean active, List<SimpleGrantedAuthority> roles) {
		this.login = login;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<SimpleGrantedAuthority> getRoles() {
		return roles;
	}
	public void setRoles(List<SimpleGrantedAuthority> roles) {
		this.roles = roles;
	}
}
