package com.codependent.oauth2.resource

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableResourceServer
@SpringBootApplication
class Oauth2ResourceServerApplication {

	static void main(String[] args) {
		SpringApplication.run Oauth2ResourceServerApplication, args
	}
}
