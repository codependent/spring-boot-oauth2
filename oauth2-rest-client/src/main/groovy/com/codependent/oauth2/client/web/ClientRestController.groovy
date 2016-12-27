package com.codependent.oauth2.client.web

import javax.annotation.PostConstruct
import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class ClientRestController {

	@Autowired
	private OAuth2RestTemplate restTemplate
	
	@GetMapping("/home")
	void getHome(HttpSession session){
		println 'Session id: '+ session.getId()
	}
	
	@GetMapping("/resources")
	void getResources(HttpSession session){
		try{
			println 'Session id: '+ session.getId()
			println 'Access Token: ' + restTemplate.getAccessToken()
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-IBM-Client-Id", "95508582-cfbb-aaaa-a244-3cec65c86aaa");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(null , headers);
			ResponseEntity<Object> response = restTemplate.exchange('http://someurl/api/v1/resources', HttpMethod.GET, entity, Object.class, [])
			println response.getBody()
		}catch(Exception e){
			e.printStackTrace()
		}
	}
	
}
