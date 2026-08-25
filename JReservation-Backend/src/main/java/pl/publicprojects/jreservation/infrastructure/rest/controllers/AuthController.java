package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.publicprojects.jreservation.infrastructure.rest.requests.LoginUserRequest;
import pl.publicprojects.jreservation.infrastructure.rest.requests.RegisterUserRequest;
import pl.publicprojects.jreservation.application.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        ResponseCookie tokenCookie = this.authService.loginUser(
                request.getUsername(),
                request.getPassword()
        );
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                .body("OK");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest request) {
        this.authService.registerUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok("User registered successfully!");
    }
}
