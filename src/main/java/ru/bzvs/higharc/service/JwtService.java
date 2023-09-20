package ru.bzvs.higharc.service;

import org.springframework.security.core.Authentication;

public interface JwtService {

    String generateJwtToken(Authentication authentication);

    Long getUserIdFromJwtToken(String token);

    boolean validateJwtToken(String authToken);
}
