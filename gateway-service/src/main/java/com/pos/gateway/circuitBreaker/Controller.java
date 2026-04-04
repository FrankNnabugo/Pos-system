package com.pos.gateway.circuitBreaker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/fallback")
public class Controller {
    @GetMapping("/catalog")
    public Mono<ResponseEntity<String>> catalogFallback() {
        return Mono.just(ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Catalog service is currently unavailable. Try again later."));
    }

    @GetMapping("/inventory")
    public Mono<ResponseEntity<String>> inventoryFallback() {
        return Mono.just(ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Inventory service is currently unavailable. Try again later."));
    }
}
