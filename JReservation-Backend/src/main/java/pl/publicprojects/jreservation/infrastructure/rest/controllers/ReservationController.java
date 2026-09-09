package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.publicprojects.jreservation.application.helper.CookieHelper;
import pl.publicprojects.jreservation.application.helper.JwtHelper;
import pl.publicprojects.jreservation.application.services.ReservationService;
import pl.publicprojects.jreservation.application.services.UserService;
import pl.publicprojects.jreservation.infrastructure.rest.requests.OrderProductRequest;

import java.util.UUID;

@RestController
@RequestMapping(name = "/app")
public class ReservationController {

    private final ReservationService orderService;
    private final JwtHelper jwtHelper;
    private final CookieHelper cookieHelper;

    public ReservationController(
            ReservationService orderService,
            JwtHelper jwtHelper,
            CookieHelper cookieHelper
    ) {
        this.orderService = orderService;
        this.jwtHelper = jwtHelper;
        this.cookieHelper = cookieHelper;
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveProduct(
            HttpServletRequest request,
            @RequestBody OrderProductRequest orderProductRequest
    ) {
        String tokenString = this.cookieHelper.loadTokenCookieValue(request);
        String username = this.jwtHelper.getTokenContent(tokenString);
        UUID productUuid = UUID.fromString(orderProductRequest.getUuidString());

        this.orderService.reserveProduct(username, productUuid);
        return ResponseEntity.ok("OK");
    }
}
