package dev.group4.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "dev.group4") // Tell spring to scan all code in the dev.ranieri packages
@EntityScan(basePackages = "dev.group4.entities") // tell spring to scan my entities
@EnableJpaRepositories(basePackages = "dev.group4.repos")// tell spring where to find my repos
/**
 * Potluck Tracker - Spring Application
 */
public class PotluckSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PotluckSpringApplication.class, args);
	}

}
