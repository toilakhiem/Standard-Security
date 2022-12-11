package com.example.standard.Security.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        User user = (User) userDetailsService.loadUserByUsername(username);
        if(username != null){
            if(passwordEncoder.matches(password,user.getPassword())){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
                return authenticationToken;
            }
        }
        throw new BadCredentialsException("Error in Authentication process!!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
