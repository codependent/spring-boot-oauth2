package com.codependent.oauth2.client.web

import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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
	
	@GetMapping("/home")
	def getHome(HttpSession session){
		session.getId()
	}
	
	@GetMapping("/users")
	def getUsers(HttpSession session){
		println 'Session id: '+ session.getId()
		HttpHeaders headers = new HttpHeaders()
		ResponseEntity<List<String>> response = restTemplate.exchange('http://localhost:8081/users', HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){}, [])
		response.getBody()
	}
	
	@PostMapping("/users")
	def postUser(){
		println 'Session id: '+ session.getId()
		HttpHeaders headers = new HttpHeaders()
		ResponseEntity<List<String>> response = restTemplate.exchange('http://localhost:8081/users', HttpMethod.POST, null, new ParameterizedTypeReference<List<String>>(){}, [])
	}
	
	@GetMapping("/users/update")
	def getUsers(@RequestParam String user, HttpSession session){
		println 'Session id: '+ session.getId()
		HttpHeaders headers = new HttpHeaders()
		ResponseEntity<List<String>> response = restTemplate.exchange('http://localhost:8081/users/'+user, HttpMethod.PUT, null, new ParameterizedTypeReference<List<String>>(){}, [])
		response.getBody()
	}
	
}
