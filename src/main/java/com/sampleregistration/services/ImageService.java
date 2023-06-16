package com.sampleregistration.services;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sampleregistration.dto.response.DeleteResponse;
import com.sampleregistration.dto.response.Image;
import com.sampleregistration.dto.response.ImageResponse;
import com.sampleregistration.dto.response.Status;
import com.sampleregistration.entities.UserEntity;
import com.sampleregistration.entities.UserImagesMapEntity;
import com.sampleregistration.exception.UploadException;
import com.sampleregistration.mapper.RegistrationMapper;
import com.sampleregistration.repositories.UserRepository;

@Service
public class ImageService {
	private static final Logger LOGGER = LogManager.getLogger(ImageService.class);
	private final DeleteImageService deleteImageService;
	private final UserRepository userRepository;
	private RegistrationMapper registrationMapper;

	public ImageService(DeleteImageService deleteImageService, UserRepository userRepository,
			RegistrationMapper registrationMapper) {
		this.deleteImageService = deleteImageService;
		this.userRepository = userRepository;
		this.registrationMapper = registrationMapper;
	}
	public ImageResponse viewImages(String userId) {
		UserEntity user = userRepository.findByUsername(userId);
		List<UserImagesMapEntity> imagesList = user.getImages();
		Set<Image> images= registrationMapper.mapImages(imagesList);

		return ImageResponse.builder()
				.status(Status.builder().code(200).message("success").build())
				.data(images)
				.build();
	}
	
	public ImageResponse deleteImage(String id, String userId) throws UploadException{
		DeleteResponse deleteResponse = deleteImageService.processDeleteImage(id);
		return ImageResponse.builder()
				.status(Status.builder().code(200).message("success").build())
				.build();
	}	
}
