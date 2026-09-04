package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/app")
public class WalletController {

    @GetMapping("/balance")
    public ResponseEntity<?> balance() {
        return ResponseEntity.ok("Created!");
    }
}
