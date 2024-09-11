package com.telusko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrfCustomizer->csrfCustomizer.disable());
		http.authorizeHttpRequests(authorizeHttpRequestsCustomizer->authorizeHttpRequestsCustomizer.anyRequest().authenticated());
//	    http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(sessionManagementCustomizer->sessionManagementCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("virat")
				.password("virat18")
				.roles("USER")
				.build();

		UserDetails admin = User.withDefaultPasswordEncoder()
		.username("alien")
		.password("alien17")
		.roles("ADMIN")
		.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
}
