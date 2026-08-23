package pl.publicprojects.jreservation.infrastructure.services;

import org.springframework.stereotype.Component;
import pl.publicprojects.jreservation.domain.exception.AuthException;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;

@Component
public class AuthService {
    final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isUserExists(String name, String email) {
        if(this.userRepository.getUserByUsername(name).isPresent())
            return true;
        return this.userRepository.getUserByUsername(email).isPresent();
    }

    public void registerUser(String name, String email, String password) {
        if(this.isUserExists(name, email)) {
            throw new AuthException("User of this email or username is already registered! Please change email or username.");
        }
    }

    public void loginUser(String name, String password) {
    }
}
