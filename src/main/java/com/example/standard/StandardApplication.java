package com.example.standard;

import com.example.standard.Core.Entity.User;
import com.example.standard.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StandardApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandardApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(UserService userService){
//        return args -> {
//            userService.saveUser(new User("admin","admin","admin","admin"));
//        };
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
