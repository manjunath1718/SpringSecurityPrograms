package com.telusko.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.telusko.service.JwtService;
import com.telusko.service.MyUserDetailsService;
import com.telusko.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtService jwtService;
	
//	@Autowired
//	private ApplicationContext context;
	
	@Autowired
	MyUserDetailsService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String name = null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer" )) {
			
			token=authHeader.substring(7);
			name=jwtService.extractUserName(token);
		}
		
		if(name!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
//			MyUserDetailsService userDetails = context.getBean(MyUserDetailsService.class);
//			UserDetails userName = userDetails.loadUserByUsername(name);
			
//			context.getBean(MyUserDetailsService.class).loadUserByUsername(name)
			UserDetails userDetails = userService.loadUserByUsername(name);
			
			if(jwtService.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken authToken=
						new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	
	

}
