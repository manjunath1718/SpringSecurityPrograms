package com.telusko.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.function.Function;

@Service
public class JwtService {

	String secreteKey;
	
	public JwtService(){
		
		secreteKey=getTheSecreteKey();
	}
	
	public String getTheSecreteKey() {
		
		try
		{
			KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
			SecretKey secretKey = key.generateKey();
			return Base64.getEncoder().encodeToString(secretKey.getEncoded());
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error in generating key");
		}
	}
	
	public String generateToken(String name) {
		
		Map<String, Object> claims=new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(name)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1800000))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
				
	}

	private Key getKey() {
		byte[] b = Decoders.BASE64.decode(secreteKey);
		return Keys.hmacShaKeyFor(b);
	}

	public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) 
    {
    	System.out.println("Token in extract claim "+ token);
        final Claims claims = extractAllClaims(token);
        System.out.println("Claims "+ claims);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
    	System.out.println("Token is exacrt all claims "+ token);
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
    	System.out.println("token in validate token "+ token);
        final String userName = extractUserName(token);
       
        System.out.println("validate Token "+ token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
    	System.out.println("Token is ex");
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
