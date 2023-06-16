package com.sampleregistration.services;

import org.springframework.stereotype.Service;

import com.sampleregistration.dto.request.User;
import com.sampleregistration.dto.response.Status;
import com.sampleregistration.dto.response.UserResponse;
import com.sampleregistration.entities.UserEntity;
import com.sampleregistration.mapper.RegistrationMapper;
import com.sampleregistration.repositories.UserRepository;

@Service
public class RegistrationService {
	private UserRepository userRepository;
	private RegistrationMapper registrationMapper;

	public RegistrationService(UserRepository userRepository, RegistrationMapper registrationMapper) {
		this.userRepository = userRepository;
		this.registrationMapper = registrationMapper;
	}

	public UserResponse processRegistration(User user) {
		UserEntity userEntity = registrationMapper.mapUser(user);
		userRepository.save(userEntity);

		UserResponse userResponse = registrationMapper.mapUserResponse(userEntity);
		Status status = new Status();
		status.setCode(200);
		status.setMessage("Success");
		userResponse.setStatus(status);

		return userResponse;
	}
	
	
//	public UserResponse login(User user) {
//		UserEntity userEntity = registrationMapper.mapUser(user);
//		userRepository.findByUserName(user.getUserName());
//
//		UserResponse userResponse = registrationMapper.mapUserResponse(userEntity);
//		Status status = new Status();
//		status.setCode("0");
//		status.setMessage("Success");
//		userResponse.setStatus(status);
//
//		return userResponse;
//	}
}