package pl.publicprojects.jreservation.application.services;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.publicprojects.jreservation.domain.authentication.User;
import pl.publicprojects.jreservation.domain.exception.AuthException;
import pl.publicprojects.jreservation.domain.helper.JwtHelper;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtHelper jwtHelper
    ) {

        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }

    private boolean isUserExists(String name, String email) {
        if(this.userRepository.getUserByUsername(name).isPresent())
            return true;
        return this.userRepository.getUserByUsername(email).isPresent();
    }

    public void registerUser(String name, String email, String password) {
        if(this.isUserExists(name, email)) {
            throw new AuthException(
                    "User of this email or username is already registered!" +
                            " Please change email or username."
            );
        }
        this.userRepository.save(
                new User(
                        name,
                        email,
                        this.passwordEncoder.encode(password)
                )
        );
    }

    public ResponseCookie loginUser(String name, String password) {
        Authentication authenticate = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(name, password));

        SecurityContextHolder.getContext()
                .setAuthentication(authenticate);

        User user = (User) authenticate.getPrincipal();
        return this.jwtHelper.generateJwtCookieFromUser(user);
    }
}
