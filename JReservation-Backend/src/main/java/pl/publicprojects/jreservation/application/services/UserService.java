package pl.publicprojects.jreservation.application.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.publicprojects.jreservation.domain.exception.exceptions.UserNotExistsException;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.util.Date;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TimeManager timeManager;

    public UserService(
            UserRepository userRepository,
            TimeManager timeManager
    ) {
        this.userRepository = userRepository;
        this.timeManager = timeManager;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username)
                .orElseThrow(
                        () -> new UserNotExistsException(
                                "User not exists, please register your account or correct username."
                        )
                );
    }

    public void createUser(String username, String email, String password) {
        userRepository.save(
                new User(username, email, password, Date.from(this.timeManager.now()))
        );
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
