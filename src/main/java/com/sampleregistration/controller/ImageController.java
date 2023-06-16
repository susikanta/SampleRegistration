package com.sampleregistration.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import com.sampleregistration.dto.request.UrlObject;
import com.sampleregistration.dto.response.ImageResponse;
import com.sampleregistration.exception.UploadException;
import com.sampleregistration.services.ImageService;
import com.sampleregistration.services.ImageUploadService;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/images")
public class ImageController {
	private ImageUploadService imageUploadService;
	private ImageService imageService;

	public ImageController(ImageUploadService imageUploadService, ImageService imageService) {
		this.imageUploadService = imageUploadService;
		this.imageService = imageService;
	}

	@GetMapping()
	public ImageResponse getImages(@NotEmpty @RequestHeader String userId) {
		return imageService.viewImages(userId);
	}

	@PostMapping(value = "/upload", consumes = "application/json")
	public ImageResponse uploadImage(@NotEmpty @RequestHeader String userId, @RequestBody UrlObject urlObject)
			throws UploadException {
		return imageUploadService.processImageUpload(urlObject, userId);
	}

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ImageResponse uploadImage(@NotEmpty @RequestHeader String userId,
			@RequestParam("image") MultipartFile multipartFile) throws UploadException {
		return imageUploadService.uploadImage(multipartFile, userId);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ImageResponse deleteImage(@NotEmpty @RequestHeader String userId, @PathVariable String id)
			throws UploadException {
		return imageService.deleteImage(id, userId);
	}
}
