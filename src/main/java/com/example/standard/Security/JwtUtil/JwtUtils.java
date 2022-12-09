package com.example.standard.Security.JwtUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Getter
public class JwtUtils {
    private static final String key = "keynaylabimatcuakhiem171222@gmail.com";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(key.getBytes());
    private static final int expireTime = 600000; // 10 min

    public Map<String, String> generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(ALGORITHM);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 300000000))
                .sign(ALGORITHM);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        return tokens;
    }
    public Set<SimpleGrantedAuthority> getRoleFromtoken(String token){
        DecodedJWT decodedJWT = decodedJWT(token);
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Arrays.stream(roles).forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role));
        });
        authorities.size();
        return authorities;
    }
    public boolean validateToken(String token) {
        return getUsernameFromToken(token) != null && isTokenExpired(token);
    }

    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = decodedJWT(token);
        return decodedJWT.getSubject();
    }
    public Boolean isTokenExpired(String token) {
        DecodedJWT decodedJWT = decodedJWT(token);
        return decodedJWT.getExpiresAt().after(new Date(System.currentTimeMillis()));
    }
    public DecodedJWT decodedJWT(String token){
        return JWT.require(ALGORITHM).build().verify(token);
    }
}
