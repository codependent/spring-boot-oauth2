package com.codependent.oauth2.client

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@SpringBootApplication
class SpringBootOauth2ClientApplication {

	static void main(String[] args) {
		SpringApplication.run SpringBootOauth2ClientApplication, args
	}
}
