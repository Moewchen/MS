package com.bht.meditrack.shared;

import com.bht.meditrack.shared.ui.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    // Konstanten für Rollen
    public static final String ROLE_USER_PATIENT = "user_patient";
    public static final String ROLE_USER_ARZT = "user_arzt";
    public static final String ROLE_ADMIN = "admin";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8180/realms/MediTrackRealm/protocol/openid-connect/certs").build();
    }

    @Bean
    @SuppressWarnings("java:S4502")  // CSRF check disabled for stateless JWT API
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configure(http))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/v1/demo/**")
                        .ignoringRequestMatchers("/patients/**")
                        .ignoringRequestMatchers("/aerzte/**")
                        .ignoringRequestMatchers("/vitaldaten/**")
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/demo/user").hasAnyRole(ROLE_USER_PATIENT, ROLE_USER_ARZT)
                        .requestMatchers("/api/v1/demo/admin").hasRole(ROLE_ADMIN)
                        .requestMatchers("/patients").hasAnyRole(ROLE_ADMIN, ROLE_USER_ARZT)
                        .requestMatchers("/patients/upsert").hasAnyRole(ROLE_ADMIN, ROLE_USER_ARZT, ROLE_USER_PATIENT)
                        .requestMatchers("/vitaldaten").hasAnyRole(ROLE_ADMIN, ROLE_USER_ARZT, ROLE_USER_PATIENT)
                        .requestMatchers("/vitaldaten/upsert").hasAnyRole(ROLE_ADMIN, ROLE_USER_ARZT, ROLE_USER_PATIENT)
                        .requestMatchers("/vitaldaten/patient/{patientId}").hasAnyRole(ROLE_ADMIN, ROLE_USER_ARZT, ROLE_USER_PATIENT)
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

        return http.build();
    }
}