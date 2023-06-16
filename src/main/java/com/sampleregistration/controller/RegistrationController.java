package com.sampleregistration.controller;

import com.sampleregistration.dto.request.User;
import com.sampleregistration.dto.response.UserResponse;
import com.sampleregistration.services.RegistrationService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Validated
@RequestMapping(value = "/registration")
public class RegistrationController {

	private RegistrationService userService;

	public RegistrationController(RegistrationService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public String registration() {
		return "registration";
	}

	@PostMapping(value = "/register")
	public UserResponse registration(@RequestBody User user) {
		return userService.processRegistration(user);
	}
}
