package com.codependent.oauth2.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore

@Configuration
class OAuth2Config extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		
		//curl trustedclient:trustedclientsecret@localhost:8082/oauth/token -d grant_type=password -d username=user -d password=cec31d99-e5ee-4f1d-b9a3-8d16d0c6eeb5 -d scope=read
		.withClient("trustedclient")
			.secret("trustedclientsecret")
            .authorizedGrantTypes("password")
            .authorities("ROLE_USER")
            .scopes("read", "write")
            .accessTokenValiditySeconds(60)
	    .and()
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)	throws Exception {
		endpoints.authenticationManager(this.authenticationManager);
	}

}
