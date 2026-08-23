package pl.publicprojects.jreservation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;
import pl.publicprojects.jreservation.infrastructure.rest.requests.LoginUserRequest;
import pl.publicprojects.jreservation.infrastructure.rest.requests.RegisterUserRequest;
import pl.publicprojects.jreservation.infrastructure.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        this.authService.loginUser(
                request.getUsername(),
                request.getPassword()
        );
        return ResponseEntity.ok("");
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
