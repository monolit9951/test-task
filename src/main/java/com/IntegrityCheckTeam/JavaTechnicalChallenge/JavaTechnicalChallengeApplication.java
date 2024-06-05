package com.IntegrityCheckTeam.JavaTechnicalChallenge;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@EnableRabbit
@ConfigurationPropertiesScan("com.IntegrityCheckTeam.JavaTechnicalChallenge.config")
public class JavaTechnicalChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTechnicalChallengeApplication.class, args);
	}

}
