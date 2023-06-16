package com.sampleregistration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sampleregistration.entities.UserEntity;
import com.sampleregistration.entities.UserImagesMapEntity;

@Repository
public interface UserImagesMapEntityRepository extends JpaRepository<UserImagesMapEntity, Long> {
	//UserEntity findById(Integer userId);
}
