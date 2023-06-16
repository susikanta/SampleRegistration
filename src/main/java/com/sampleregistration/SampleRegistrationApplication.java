package com.sampleregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.sampleregistration" })
@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.sampleregistration.entities")
public class SampleRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleRegistrationApplication.class, args);
	}
}
