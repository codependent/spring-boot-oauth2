package com.codependent.oauth2.client.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AccessTokenExtractorController {

	@Autowired
	private OAuth2ClientContext oauth2ClientContext
	
	@GetMapping("/accessTokenExtractor")
	void accessTokenExtractorPage(){
		
	}
	
	@PostMapping("/accessTokenExtractor")
	def accessTokenExtractorPage(@RequestParam String hash){
		println hash
		println oauth2ClientContext.getAccessTokenRequest().getPreservedState()
		println oauth2ClientContext.getAccessTokenRequest().getCurrentUri()
		OAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(hash.substring(hash.indexOf('=')+1, hash.indexOf('&'))) 
		oauth2ClientContext.setAccessToken(accessToken)
		'redirect:/home'
	}
	
}
