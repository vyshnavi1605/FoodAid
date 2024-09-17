package com.FoodAid.Group35.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class JwtUtil {
    private String SECRET_KEY = "secret";

    /*
    *taken in token and returns username for that token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*
     *taken in token and returns the expiration date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /*
    *Takes in token and uses a claims resolver inorder to figure
    * out what the claims are
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /*
    *takes in token ans checks if it expired or not
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /*
    *Takes in userDetails object provided by MyUserDetailsService
    *Creates jwt based of userDetails
    *Takes username from userDetails and making it into the jwt
    *Calls createToken method
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /*
    *Calls jwt api
    * uses a builder pattern and is setting the claims that we are passing through
    * sets a subject who is the person who is being authenticated
    * included the issue date which is the current time and expiration date which is 10 hours later
    * then we are signing the token using the signature algorithm and passing a secret key
    * which is defined at the top
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /*
    *gets the username using extractUsername method
    * checks if that username is the same as the userDetails username that is passed in
    * and checks that the token is not expired
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
