package com.codependent.oauth2.authorization.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

	@GetMapping("/login")
	void login(){}
	
}
