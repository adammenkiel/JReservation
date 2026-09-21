package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.publicprojects.jreservation.application.helper.CookieHelper;
import pl.publicprojects.jreservation.application.helper.JwtHelper;
import pl.publicprojects.jreservation.application.services.UserService;
import pl.publicprojects.jreservation.application.services.WalletService;
import pl.publicprojects.jreservation.domain.user.User;

@RestController
@RequestMapping("/app")
public class WalletController {

    private final JwtHelper jwtHelper;
    private final CookieHelper cookieHelper;
    private final UserService userService;

    public WalletController(
            JwtHelper jwtHelper,
            CookieHelper cookieHelper,
            UserService userService
    ) {
        this.jwtHelper = jwtHelper;
        this.cookieHelper = cookieHelper;
        this.userService = userService;
    }

    @GetMapping("/balance")
    public ResponseEntity<?> balance(
            HttpServletRequest request
    ) {
        String tokenString = this.cookieHelper.loadTokenCookieValue(request);
        String username = this.jwtHelper.getTokenContent(tokenString);
        User user = (User) this.userService.loadUserByUsername(username);
        return ResponseEntity.ok(user.getWallets());
    }
}
