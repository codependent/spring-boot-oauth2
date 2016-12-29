package com.codependent.oauth2.authorization.web

import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class OAuthApprovalController {

	@Autowired
	private OAuth2ClientContext oauth2ClientContext
	
	@RequestMapping("/oauth/confirm_access")
	def confirmAccess(HttpServletRequest request, ModelMap model){
		if (request.getAttribute("_csrf") != null) {
			model.put("_csrf", request.getAttribute("_csrf"));
		}
		'oauthApproval'
	}
	
}
