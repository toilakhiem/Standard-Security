package com.example.standard.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.geom.RectangularShape;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("thanh cong");
    }
}
