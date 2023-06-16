package com.sampleregistration.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.sampleregistration.dto.request.User;
import com.sampleregistration.dto.response.Image;
import com.sampleregistration.dto.response.UserResponse;
import com.sampleregistration.entities.UserEntity;
import com.sampleregistration.entities.UserImagesMapEntity;

@Mapper(componentModel = "Spring", 
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, 
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, 
builder = @Builder(disableBuilder = true))
public interface RegistrationMapper {

	UserEntity mapUser(User user);

	UserResponse mapUserResponse(UserEntity userEntity);
	
	default Set<Image> mapImages(List<UserImagesMapEntity> imagesList ){
		Set<Image> images = new HashSet<Image>();
		for(UserImagesMapEntity userImagesMapEntity : imagesList) {
			images.add(mapImage(userImagesMapEntity));
		}
		return images;
	}
	@Mapping(source="userMapid", target ="id")
//	@Mapping(source="userId", target ="")
	@Mapping(source="link", target ="link")
	@Mapping(source="dateTime", target ="dateTime")
	@Mapping(source="deletehash", target ="deletehash")
	Image mapImage(UserImagesMapEntity userImagesMapEntity);
}
