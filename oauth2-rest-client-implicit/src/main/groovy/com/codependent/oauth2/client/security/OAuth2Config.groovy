package com.codependent.oauth2.client.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitResourceDetails
import org.springframework.security.oauth2.common.AuthenticationScheme

@Configuration
class OAuth2Config {

	@Value('${oauth.resource:http://localhost:8082}')
	private String baseUrl
	
	@Value('${oauth.authorize:http://localhost:8082/oauth/authorize}')
	private String authorizeUrl
	
	@Value('${oauth.token:http://localhost:8082/oauth/token}')
	private String tokenUrl
	
	@Autowired
	private OAuth2ClientContext oauth2Context
	
	@Bean
	OAuth2ProtectedResourceDetails resource() {
		ImplicitResourceDetails resource = new ImplicitResourceDetails()
		resource.setAuthenticationScheme(AuthenticationScheme.header)
		resource.setAccessTokenUri(authorizeUrl)
		resource.setUserAuthorizationUri(authorizeUrl);
		resource.setClientId("themostuntrustedclientid")
		resource.setClientSecret("themostuntrustedclientsecret")
		resource.setScope(['read', 'write'])
		resource
	}

	@Bean
	OAuth2RestTemplate restTemplate() {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource(), oauth2Context)
		//restTemplate.setAuthenticator(new ApiConnectOAuth2RequestAuthenticator())
		restTemplate
	}
	
	/*
	class ApiConnectOAuth2RequestAuthenticator extends DefaultOAuth2RequestAuthenticator {
		@Override
		public void authenticate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext clientContext, ClientHttpRequest request) {
		  OAuth2AccessToken accessToken = clientContext.getAccessToken();
		  String tokenType = OAuth2AccessToken.BEARER_TYPE;
		  request.getHeaders().set("Authorization",
			  String.format("%s %s", tokenType, accessToken.getValue()));
		}
	}
	*/
}
