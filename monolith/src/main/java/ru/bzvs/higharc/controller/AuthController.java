package ru.bzvs.higharc.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final Counter counter;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, MeterRegistry registry) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.counter = Counter.builder("login_counter").register(registry);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.id(), request.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        counter.increment();

        return ResponseEntity.ok(jwtService.generateJwtToken(authentication));
    }
}
