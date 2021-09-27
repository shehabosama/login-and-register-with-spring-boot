package com.shehab.authdemo.authdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shehab.authdemo.authdemo.dto.UserRegisteration;
import com.shehab.authdemo.authdemo.entity.User;
import com.shehab.authdemo.authdemo.service.UserService;



@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	UserService userService;
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user" , new User());
		return "registration";
	}
	

	@PostMapping("/registrationUserAccount")
	public String registrationUserAccount(@ModelAttribute("user") UserRegisteration userRegistration) {
		userService.Save(userRegistration);
		return "redirect:/registration/showRegistrationForm?success";
	}
}
