package ru.bzvs.higharc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bzvs.higharc.dto.LoginRequest;
import ru.bzvs.higharc.service.JwtService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.id(), request.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(jwtService.generateJwtToken(authentication));
    }
}
