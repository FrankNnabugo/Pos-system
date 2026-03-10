package com.catalog.Catalog.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/catalog")
public class Catalog {

    @GetMapping
    public String catalog(){
        return "catalog";
    }
}
