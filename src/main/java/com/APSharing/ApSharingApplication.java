package com.APSharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@EnableScheduling
@SpringBootApplication
public class ApSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApSharingApplication.class, args);
	}

}
