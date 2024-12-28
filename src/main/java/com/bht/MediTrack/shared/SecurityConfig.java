package com.bht.MediTrack.shared;

import com.bht.MediTrack.shared.ui.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {


    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8180/realms/MediTrackRealm/protocol/openid-connect/certs").build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/demo/user").hasAnyRole("user_patient", "user_arzt")
                        .requestMatchers("/api/v1/demo/admin").hasRole("admin")
                        .requestMatchers("/patients").hasAnyRole("admin", "user_arzt")
                        .requestMatchers("/patients/upsert").hasAnyRole("admin", "user_arzt")
                        .requestMatchers("/vitaldaten").hasAnyRole("admin", "user_arzt", "user_patient")
                        .requestMatchers("/vitaldaten/upsert").hasAnyRole("admin", "user_arzt", "user_patient")
                        .requestMatchers("/vitaldaten/patient/{patientId}").hasAnyRole("admin", "user_arzt", "user_patient")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

        return http.build();
    }
}