package com.bht.meditrack.shared.ui;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final JwtGrantedAuthoritiesConverter JWT_GRANTED_AUTHORITIES_CONVERTER = new JwtGrantedAuthoritiesConverter();
    private static final String PRINCIPAL_ATTRIBUTE = "preferred_username";
    private static final String RESOURCE_ID = "meditrack-backend";

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Objects.requireNonNull(jwt, "JWT token cannot be null");

        Collection<GrantedAuthority> authorities = Stream.concat(
                Optional.ofNullable(JWT_GRANTED_AUTHORITIES_CONVERTER.convert(jwt))
                        .stream()
                        .flatMap(Collection::stream),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        return jwt.getClaim(PRINCIPAL_ATTRIBUTE);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if (jwt.getClaim("resource_access") == null) {
            return Set.of();
        }

        resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess.get(RESOURCE_ID) == null) {
            return Set.of();
        }

        resource = (Map<String, Object>) resourceAccess.get(RESOURCE_ID);
        resourceRoles = (Collection<String>) resource.get("roles");

        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}