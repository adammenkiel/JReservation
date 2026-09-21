package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.publicprojects.jreservation.application.helper.CookieHelper;
import pl.publicprojects.jreservation.application.helper.JwtHelper;
import pl.publicprojects.jreservation.application.services.PaymentService;
import pl.publicprojects.jreservation.infrastructure.rest.requests.PaymentRequest;

@RestController
@RequestMapping("/app")
public class PaymentController {

    private final JwtHelper jwtHelper;
    private final CookieHelper cookieHelper;
    private final PaymentService paymentService;

    public PaymentController(
            JwtHelper jwtHelper,
            CookieHelper cookieHelper,
            PaymentService paymentService
    ) {
        this.jwtHelper = jwtHelper;
        this.cookieHelper = cookieHelper;
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payForProduct(
            HttpServletRequest request,
            @Valid @RequestBody PaymentRequest paymentRequest
            ) {
        String token = this.cookieHelper.loadTokenCookieValue(request);
        String username = this.jwtHelper.getTokenContent(token);

        this.paymentService.buyProduct(username, paymentRequest.getProductUUID());
        return ResponseEntity.ok("OK!");
    }
}
