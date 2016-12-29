package com.codependent.oauth2.authorization.web

import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class OAuthApprovalController {

	@Autowired
	private OAuth2ClientContext oauth2ClientContext
	
	@GetMapping("/oauth/confirm_access")
	def confirmAccess(HttpSession session){
		
		Enumeration<String> en = session.getAttributeNames()
		while(en.hasMoreElements()){
			String n = (String)en.nextElement()
			println n
			println session.getAttribute(n)
			println '*********'
		}
		'oauthApproval'
	}
	
}
