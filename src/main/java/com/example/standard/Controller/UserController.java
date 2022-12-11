package com.example.standard.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping(value = "/username")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
