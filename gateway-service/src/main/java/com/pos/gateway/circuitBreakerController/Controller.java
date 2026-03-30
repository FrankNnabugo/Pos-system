package com.pos.gateway.circuitBreakerController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fallback")
public class Controller {
    @GetMapping("/catalog")
    public ResponseEntity<String> catalogFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Catalog service is currently unavailable. Try again later.");
    }

    @GetMapping("/inventory")
    public ResponseEntity<String> inventoryFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Inventory service is currently unavailable. Try again later.");
    }
}
