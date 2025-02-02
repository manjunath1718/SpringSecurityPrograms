package com.telusko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrfCustomizer->csrfCustomizer.disable());
		http.authorizeHttpRequests(authorizeHttpRequestsCustomizer->authorizeHttpRequestsCustomizer.anyRequest().authenticated());
	    http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(sessionManagementCustomizer->sessionManagementCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
}
