package com.jinius.architecture.clean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.jinius.architecture.clean"})
@EnableJpaRepositories(basePackages = {"com.jinius.architecture.clean"})
@SpringBootApplication
public class JjCleanArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(JjCleanArchitectureApplication.class, args);
	}
}
