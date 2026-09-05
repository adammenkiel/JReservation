package pl.publicprojects.jreservation.application.services;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.publicprojects.jreservation.domain.payment.Wallet;
import pl.publicprojects.jreservation.domain.user.User;
import pl.publicprojects.jreservation.domain.exception.exceptions.AuthException;
import pl.publicprojects.jreservation.application.helper.JwtHelper;
import pl.publicprojects.jreservation.infrastructure.repositories.UserRepository;
import pl.publicprojects.jreservation.infrastructure.repositories.WalletRepository;
import pl.publicprojects.jreservation.infrastructure.time.TimeManager;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private final TimeManager timeManager;

    public AuthService(
            UserRepository userRepository,
            WalletRepository walletRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtHelper jwtHelper,
            TimeManager timeManager
    ) {

        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
        this.timeManager = timeManager;
    }

    private boolean isUserExists(String name, String email) {
        return this.userRepository.getUserByUsername(name).isPresent() ||
                this.userRepository.getUserByEmail(email).isPresent();
    }

    public void registerUser(String name, String email, String password) {
        if(this.isUserExists(name, email)) {
            throw new AuthException(
                    "User of this email or username is already registered!" +
                            " Please change email or username."
            );
        }
        User user = new User(
                name,
                email,
                this.passwordEncoder.encode(password),
                Date.from(this.timeManager.now())
        );

        Wallet wallet = new Wallet(
                user,
                BigDecimal.ZERO,
                "PLN"
        );

        this.userRepository.save(user);
        this.walletRepository.save(wallet);
    }

    public ResponseCookie loginUser(String name, String password) {
        Authentication authenticate = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(name, password));

        SecurityContextHolder.getContext()
                .setAuthentication(authenticate);

        User user = (User) authenticate.getPrincipal();
        return this.jwtHelper.generateJwtCookieFromUser(user);
    }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
}
