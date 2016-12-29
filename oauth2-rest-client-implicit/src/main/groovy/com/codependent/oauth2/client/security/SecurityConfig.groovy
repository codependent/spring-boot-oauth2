package com.codependent.oauth2.client.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false)
			.inMemoryAuthentication().withUser("jose").password("mypassword").roles('USER')
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
			.ignoringAntMatchers("/accessTokenExtractor")
		.and()
		.authorizeRequests()
		.anyRequest().hasRole('USER')
		.and()
		.formLogin()
			.loginPage("/login").permitAll()
	}
	
}
