package com.linhnguyen.springbootbasic.configuration;

import com.nimbusds.jose.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINT = {"/users", "/auth/login", "/auth/introspect"};

    @Value("${jwt.singerKey}")
    private String SIGNED_KEY;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->
            request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
                    .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth ->
                oauth.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
        return httpSecurity.build();

    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNED_KEY.getBytes(), "HS512");
        return  NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512).build();
    }
}
