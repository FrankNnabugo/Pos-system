package com.pos.catalog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    @GetMapping("/user")
    public String userOnly(){
        return "user";
    }

    @GetMapping("/admin")
    public String adminOnly(){
        return "admin";
    }

    @GetMapping("/token-check")
    public ResponseEntity<String> tokenCheck(
            @RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.ok(authHeader);
    }
}
