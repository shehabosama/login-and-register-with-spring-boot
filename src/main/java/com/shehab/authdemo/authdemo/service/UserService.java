package com.shehab.authdemo.authdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shehab.authdemo.authdemo.dto.UserRegisteration;
import com.shehab.authdemo.authdemo.entity.User;

public interface UserService extends UserDetailsService {

	User Save(UserRegisteration userRegisration);
}
