package com.alinicanedo.ecommerce.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.alinicanedo.ecommerce.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}