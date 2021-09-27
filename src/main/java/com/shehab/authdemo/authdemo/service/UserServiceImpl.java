package com.shehab.authdemo.authdemo.service;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shehab.authdemo.authdemo.dao.UserRepository;
import com.shehab.authdemo.authdemo.dto.UserRegisteration;
import com.shehab.authdemo.authdemo.entity.Role;
import com.shehab.authdemo.authdemo.entity.User;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl( UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public User Save(UserRegisteration userRegistration) {
		User user = new User();
		user.setFirstName(userRegistration.getFirstName());
		user.setLastName(userRegistration.getLastName());
		user.setEmail(userRegistration.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_EMPLOYEE")));
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new  org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
	}

}
