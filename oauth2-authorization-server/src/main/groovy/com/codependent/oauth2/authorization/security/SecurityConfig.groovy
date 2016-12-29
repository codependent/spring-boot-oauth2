package com.codependent.oauth2.authorization.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("jose").password("mypassword").roles('USER').and()
									 .withUser("themostuntrustedclientid").password("themostuntrustedclientsecret").roles('USER')
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
			//
			//XXX Si se usa implicit descomentar
			.ignoringAntMatchers("/oauth/authorize")
		.and()
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
	}
	
}
