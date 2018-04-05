package com.thinking_cursor.springbootiful.ldap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.ldapAuthentication()
			.contextSource().url("ldap://localhost:8282/dc=springframework,dc=org").and()
			.userDnPatterns("uid={0}, ou=people")
			.groupSearchBase("ou=groups")
			.passwordCompare()
			.passwordEncoder(new LdapShaPasswordEncoder())
			.passwordAttribute("user_password");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin();
	}
	
	

}
