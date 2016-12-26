package com.codependent.oauth2

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@EnableAuthorizationServer
@SpringBootApplication
class Oauth2AuthorizationServerApplication {

	static void main(String[] args) {
		SpringApplication.run Oauth2AuthorizationServerApplication, args
	}
}
