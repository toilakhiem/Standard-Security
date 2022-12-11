package com.example.standard.Controller;

import com.example.standard.Dto.SignInRequest;
import com.example.standard.Security.JwtUtil.JwtUtils;
import com.example.standard.Security.Provider.JwtAuthenticationProvider;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request){
        JwtUtils jwtUtils = new JwtUtils();
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
//        authenticationManager.authenticate(token);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Map<String,String> tokens = jwtUtils.generateToken(authentication);
        return ResponseEntity.ok(tokens);
    }
}
