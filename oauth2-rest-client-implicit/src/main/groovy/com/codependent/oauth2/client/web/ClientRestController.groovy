package com.codependent.oauth2.client.web

import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientRestController {

	@Autowired
	private OAuth2RestTemplate restTemplate
	
	@ExceptionHandler(InsufficientScopeException.class)
	def exceptionHandler(InsufficientScopeException ex){
		ex
	}
	
	@GetMapping("/users")
	def getUsers(HttpSession session){
		println 'Session id: '+ session.getId()
		
		//TODO Move to after authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		restTemplate.getOAuth2ClientContext().getAccessTokenRequest().setAll(['client_id': 'themostuntrustedclientid', 'response_type': 'token', 'redirect_uri': 'http://localhost:8080/api/accessTokenExtractor'])
		
		HttpHeaders headers = new HttpHeaders()
		ResponseEntity<List<String>> response = restTemplate.exchange('http://localhost:8081/users', HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){}, [])
		response.getBody()
	}
	
	@GetMapping("/users/update")
	def getUsers(@RequestParam String user, HttpSession session){
		println 'Session id: '+ session.getId()
		
		//TODO Move to after authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication()
		restTemplate.getOAuth2ClientContext().getAccessTokenRequest().setAll(['client_id': 'themostuntrustedclientid', 'response_type': 'token', 'redirect_uri': 'http://localhost:8080/api/users'])
		
		HttpHeaders headers = new HttpHeaders()
		ResponseEntity<List<String>> response = restTemplate.exchange('http://localhost:8081/users/'+user, HttpMethod.PUT, null, new ParameterizedTypeReference<List<String>>(){}, [])
		response.getBody()
	}
	
}
