package com.hackathon.sessionratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SessionRateLimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionRateLimiterApplication.class, args);
	}

}
