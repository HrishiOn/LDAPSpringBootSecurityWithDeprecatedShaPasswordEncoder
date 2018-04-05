package com.thinking_cursor.springbootiful.ldap.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@EnableGlobalMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.ldapAuthentication()
			.contextSource(userContextResource())
			.userDnPatterns("uid={0}, ou=space cadets")
			.groupSearchBase("ou=groups")
			.passwordCompare()
			.passwordEncoder(new LdapShaPasswordEncoder())
			.passwordAttribute("userPassword");

	}

	@Bean
	public DefaultSpringSecurityContextSource userContextResource() {
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389"),"dc=springframework,dc=org");
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
