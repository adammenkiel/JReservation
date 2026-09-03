package pl.publicprojects.jreservation.application.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.exception.exceptions.AuthException;
import pl.publicprojects.jreservation.domain.exception.exceptions.UserNotExistsException;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.getUserByUsername(username)
                .orElseThrow(
                        () -> new UserNotExistsException(
                                "User not exists, please register your account or correct username."
                        )
                );
    }
}
