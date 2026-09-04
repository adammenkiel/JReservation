package pl.publicprojects.jreservation.infrastructure.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @PostMapping
    public ResponseEntity<?> createWallet() {
        return ResponseEntity.ok("Created!");
    }
}
