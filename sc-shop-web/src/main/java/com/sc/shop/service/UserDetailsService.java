package com.sc.shop.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

	public UserDetails loadUserByUsername(String username);
}
