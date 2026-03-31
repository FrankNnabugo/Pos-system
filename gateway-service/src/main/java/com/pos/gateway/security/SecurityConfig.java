package com.pos.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(
                                "/eureka/**",
                                "/actuator/**",
                                "/health",
                                "/api-docs",
                                "/swagger",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api/v1/fallback/**"
                        )
                        .permitAll()

                        .pathMatchers("/api/v1/catalog/admin").hasRole("ADMIN")
                        .pathMatchers("/api/v1/catalog/user").hasRole("USER")
                        .pathMatchers("/api/v1/inventory/user").hasRole("USER")
                        .anyExchange()
                        .authenticated())
                .oauth2ResourceServer(jwt-> jwt.jwt(
                        converter-> converter.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    @Bean
    Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());

        return new ReactiveJwtAuthenticationConverterAdapter(jwtConverter);
    }
}
