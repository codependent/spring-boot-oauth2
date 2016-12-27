package com.codependent.oauth2.resource.web

import javax.servlet.http.HttpSession

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersRestController {

	private Set<String> users = ["jose", "ana"]
	
	@GetMapping("/users")
	def getUser(){
		return users
	}
	
	@PutMapping("/users/{user}")
	void postUser(@PathVariable String user){
		users.add(user)
	}
	
}
