package com.codependent.oauth2.client.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;;

@RestController
class ClientRestController {

	@Autowired
	private OAuth2RestTemplate restTemplate
	
	@GetMapping("/resources")
	void getResources(){
		restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set
		println restTemplate
	}
	
}
